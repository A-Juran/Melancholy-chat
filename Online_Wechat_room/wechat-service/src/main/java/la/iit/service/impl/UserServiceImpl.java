package la.iit.service.impl;

import cn.hutool.core.lang.Assert;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import la.iit.config.WxAppIdConfig;
import la.iit.entity.dto.UserDTO;
import la.iit.mapper.UserMapper;
import la.iit.entity.domain.OwUser;
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
public class UserServiceImpl extends ServiceImpl<UserMapper, OwUser>
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
        //String openId = (String) redisUtils.get(LOGIN_USER_OPEN_ID.value()
        //+ globalParamsUtils.getToken());
        String openId = globalParamsUtils.getCurrentUser().getOpenId();

        OwUser currentUser =
                getOne(Wrappers.<OwUser>lambdaQuery()
                        .eq(OwUser::getOpenId, openId));

        return currentUser.isImprove();
    }

    @Override
    public String saveUserInfo(String code) {
        JSONObject wxParams = getWxParams(code);
        String OpenId = wxParams.get("openid").toString();
        String unionId = wxParams.get("unionid").toString();
        OwUser currentUser =
                getOne(Wrappers.<OwUser>lambdaQuery().eq(OwUser::getOpenId, OpenId));
        String token = UUID.randomUUID().toString().replace("-", "");
        OwUser owUser = null;
        if (currentUser == null) {
            owUser = new OwUser().setOpenId(OpenId)
                    .setUnionId(unionId);
            save(owUser);
        }
        redisUtils.set(LOGIN_USER.value() + token,
                currentUser == null ? owUser : currentUser,
                LOGIN_EXPIRE_TIME);
        redisUtils.set(LOGIN_USER_OPEN_ID.value() + token
                , OpenId, LOGIN_EXPIRE_TIME);
        return token;
    }

    @Override
    public void updateUserInfo(UserDTO userDTO) {
        /*
            查询用户信息保证数据正常
         */
        OwUser currentLoginUser = globalParamsUtils.getCurrentUser();
        OwUser one = getOne(Wrappers.lambdaQuery(OwUser.class)
                .eq(OwUser::getId, currentLoginUser.getId()));
        //通过BeanUtils进行参数拷贝。
        BeanUtils.copyProperties(userDTO, one);
        currentLoginUser.setImprove(true);
        /*
            1、更新用户信息
            2、删除redis缓存、再将redis中用户数据进行更新。
         */
        updateById(one);
        redisUtils.del(LOGIN_USER.value() + globalParamsUtils.getToken());
        String openid =
                (String) redisUtils.get(LOGIN_USER_OPEN_ID.value() + globalParamsUtils.getToken());
        OwUser currentUser =
                getOne(Wrappers.<OwUser>lambdaQuery().eq(OwUser::getOpenId, openid));
        redisUtils.set(LOGIN_USER.value() + globalParamsUtils.getToken(),
                currentUser,
                LOGIN_EXPIRE_TIME);
    }

    @Override
    public OwUser getCurrentUserInfo() {
        /*
            判断用户信息完善状态
            1、未完成抛出异常
            2、完成从缓存取出用户信息/缓存中没有数据则从数据库中获取。
         */
        boolean userInfoImproveStatus =
                getUserInfoImproveStatus();
        Assert.isTrue(userInfoImproveStatus,
                "Please improve user information!");
        OwUser currentUser = null;
        currentUser = globalParamsUtils.getCurrentUser();
        if (ObjectUtils.isEmpty(currentUser)) {
            String openId =
                    (String) redisUtils.get(LOGIN_USER_OPEN_ID.value() + globalParamsUtils.getToken());
            Assert.notEmpty(openId,
                    "openId is not empty!");
            currentUser = getOne(Wrappers.lambdaQuery(OwUser.class).eq(OwUser::getOpenId, openId));
        }

        return currentUser;
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
