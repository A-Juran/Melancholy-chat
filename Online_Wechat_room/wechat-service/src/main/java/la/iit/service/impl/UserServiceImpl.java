package la.iit.service.impl;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.lang.UUID;
import cn.hutool.crypto.digest.MD5;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wf.captcha.SpecCaptcha;
import la.iit.common.exception.UserAccountDisabledException;
import la.iit.common.exception.UserIsExistException;
import la.iit.config.WxAppIdConfig;
import la.iit.entity.domain.OwUser;
import la.iit.entity.dto.UserDTO;
import la.iit.mapper.UserMapper;
import la.iit.service.UserService;
import la.iit.utils.GlobalParamsUtils;
import la.iit.utils.OkHttpUtils;
import la.iit.utils.RedisUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;

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
    private final long CODE_EXPIRE_TIME = 300;  //3小时免登录。

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
    public String login(String username,
                        String password) throws Exception {
        LambdaQueryWrapper<OwUser> login = Wrappers.<OwUser>lambdaQuery()
                .eq(OwUser::getUsername, username)
                .eq(OwUser::getPassword, MD5.create().digestHex(password));
        OwUser currentUser = getOne(login);
        Assert.notNull(currentUser, LOGIN_FAILED.value());
        if (!currentUser.isActive()){
            throw new UserAccountDisabledException();
        }
        //redis中存储当前登录用户数据
        String token =
                UUID.randomUUID().toString().replace("-", "");
        redisUtils.set(LOGIN_USER + token, currentUser, LOGIN_EXPIRE_TIME);
        globalParamsUtils.setCurrentUser(currentUser);
        return token;
    }

    @Override
    public void register(UserDTO userDTO) throws UserIsExistException {
        //判断用户是否注册
        String username = userDTO.getUsername();
        LambdaQueryWrapper<OwUser> checkUserIsExist = Wrappers.<OwUser>lambdaQuery()
                .eq(OwUser::getUsername, username);
        OwUser one = getOne(checkUserIsExist);
        if (one != null) {
            throw new UserIsExistException("用户名已经存在");
        }
        //进行用户注册
        OwUser owUser = new OwUser();
        BeanUtils.copyProperties(userDTO, owUser);
        owUser.setPassword(MD5.create().digestHex(owUser.getPassword()));
        save(owUser);
    }

    @Override
    public HashMap<String, Object> captcha() {
        SpecCaptcha specCaptcha = new SpecCaptcha(130, 48, 5);
        String verCode = specCaptcha.text().toLowerCase();
        String key = java.util.UUID.randomUUID().toString();
        // 存入redis并设置过期时间为30分钟
        redisUtils.set(key, verCode, CODE_EXPIRE_TIME);
        HashMap<String, Object> stringObjectHashMap
                = new HashMap<>();
        stringObjectHashMap.put("key", key);
        stringObjectHashMap.put("image", specCaptcha.toBase64());
        return stringObjectHashMap;
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
