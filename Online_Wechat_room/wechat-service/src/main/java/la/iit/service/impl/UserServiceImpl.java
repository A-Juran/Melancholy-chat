package la.iit.service.impl;

import cn.hutool.core.lang.Assert;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import la.iit.config.WxAppIdConfig;
import la.iit.dto.UserDTO;
import la.iit.mapper.UserMapper;
import la.iit.pojo.SysUser;
import la.iit.service.UserService;
import la.iit.utils.GlobalParamsUtils;
import la.iit.utils.OkHttpUtils;
import la.iit.utils.RedisUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

import static la.iit.common.Constant.*;

/**
 * @author JuRan
 * @date 2023/3/3
 */
@Service
@Transactional
public class UserServiceImpl extends ServiceImpl<UserMapper, SysUser>
        implements UserService {

    private final long LOGIN_EXPIRE_TIME = 3600 * 3;  //3小时免登录。

    private RedisUtils redisUtils;
    private GlobalParamsUtils globalParamsUtils;
    private WxAppIdConfig wxAppIdConfig;

    public UserServiceImpl(WxAppIdConfig wxAppIdConfig,
                           RedisUtils redisUtils,
                           GlobalParamsUtils globalParamsUtils) {
        this.wxAppIdConfig = wxAppIdConfig;
        this.redisUtils = redisUtils;
        this.globalParamsUtils = globalParamsUtils;
    }

    @Override
    public boolean getUserInfoImproveStatus() {
        String openId = (String) redisUtils.get(LOGIN_USER_OPEN_ID.value()
                + globalParamsUtils.getToken());

        SysUser currentUser =
                getOne(Wrappers.<SysUser>lambdaQuery()
                        .eq(SysUser::getOpenId, openId));

        return currentUser.isImprove();
    }

    @Override
    public String saveUserInfo(String code) {
        JSONObject wxParams = getWxParams(code);
        String OpenId = wxParams.get("openid").toString();
        String unionId = wxParams.get("unionid").toString();
        SysUser currentUser =
                getOne(Wrappers.<SysUser>lambdaQuery().eq(SysUser::getOpenId, OpenId));
        String token = UUID.randomUUID().toString().replace("-", "");
        SysUser sysUser = null;
        if (currentUser == null) {
            sysUser = new SysUser().setOpenId(OpenId)
                    .setUnionId(unionId);
            save(sysUser);
        }
        redisUtils.set(LOGIN_USER.value() + token,
                currentUser == null ? sysUser : currentUser,
                LOGIN_EXPIRE_TIME);
        redisUtils.set(LOGIN_USER_OPEN_ID.value() + token
                , OpenId, LOGIN_EXPIRE_TIME);
        return token;
    }

    @Override
    public void updateUserInfo(UserDTO userDTO) {
        SysUser currentLoginUser =
                (SysUser) redisUtils.get(LOGIN_USER.value() + globalParamsUtils.getToken());
        Long userId = currentLoginUser.getId();
        SysUser one = getOne(Wrappers.lambdaQuery(SysUser.class)
                .eq(SysUser::getId, userId));
        BeanUtils.copyProperties(userDTO,
                one);
        currentLoginUser.setImprove(true);
        updateById(one);
        redisUtils.del(LOGIN_USER.value() + globalParamsUtils.getToken());
        String openid =
                (String) redisUtils.get(LOGIN_USER_OPEN_ID.value() + globalParamsUtils.getToken());
        SysUser currentUser =
                getOne(Wrappers.<SysUser>lambdaQuery().eq(SysUser::getOpenId, openid));
        redisUtils.set(LOGIN_USER.value() + globalParamsUtils.getToken(),
                currentUser,
                LOGIN_EXPIRE_TIME);
    }

    @Override
    public SysUser getCurrentUserInfo() {
        boolean userInfoImproveStatus =
                getUserInfoImproveStatus();
        Assert.isTrue(userInfoImproveStatus,
                "Please improve user information!");
        String openId =
                (String) redisUtils.get(LOGIN_USER_OPEN_ID.value() + globalParamsUtils.getToken());
        Assert.notEmpty(openId, "openId is not empty!");
        return getOne(Wrappers.lambdaQuery(SysUser.class).eq(SysUser::getOpenId, openId));
    }

    //getOPenId and Other
    public JSONObject getWxParams(String code) {
        String reponseContent = null;
        try {
            reponseContent = OkHttpUtils.builder()
                    .addParams("appid", wxAppIdConfig.getAppid())
                    .addParams("secret", wxAppIdConfig.getSecret())
                    .addParams("js_code", code)
                    .addParams("grant_type", "authorization_code")
                    .url("https://api.weixin.qq.com/sns/jscode2session")
                    .get().async();
        } catch (Exception e) {
            throw new RuntimeException(REQUEST_EXCEPTION.value());
        }
        return JSONObject.parseObject(reponseContent);
    }
}
