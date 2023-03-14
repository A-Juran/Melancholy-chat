package la.iit.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import la.iit.dto.UserDTO;
import la.iit.mapper.UserMapper;
import la.iit.pojo.SysUser;
import la.iit.service.UserService;
import la.iit.utils.GlobalParamsUtils;
import la.iit.utils.RedisUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

import static la.iit.common.Constant.LOGIN_USER;
import static la.iit.common.Constant.LOGIN_USER_OPEN_ID;

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

    public UserServiceImpl(RedisUtils redisUtils,
                           GlobalParamsUtils globalParamsUtils) {
        this.redisUtils = redisUtils;
        this.globalParamsUtils = globalParamsUtils;
    }

    @Override
    public boolean getUserInfoImproveStatus() {
        String openId = (String) redisUtils.get(LOGIN_USER_OPEN_ID.value() + globalParamsUtils.getToken());
        SysUser currentUser =
                getOne(lambdaQuery()
                        .eq(SysUser::getOpenId, openId));
        return currentUser.isImprove();
    }

    @Override
    public String saveUserInfo(String openId, String unionId) {
        SysUser currentUser =
                getOne(lambdaQuery()
                        .eq(SysUser::getOpenId, openId));
        String token = UUID.randomUUID().toString();
        SysUser sysUser = null;
        if (currentUser == null) {
            sysUser = new SysUser().setOpenId(openId)
                    .setUnionId(unionId);
            save(sysUser);
        }
        redisUtils.set(LOGIN_USER.value() + token,
                currentUser == null ? sysUser : currentUser,
                LOGIN_EXPIRE_TIME);
        redisUtils.set(LOGIN_USER_OPEN_ID.value() + token
                , openId, LOGIN_EXPIRE_TIME);
        return token;
    }

    @Override
    public void updateUserInfo(UserDTO userDTO) {
        SysUser currentLoginUser =
                (SysUser) redisUtils.get(LOGIN_USER.value() + globalParamsUtils.getToken());
        BeanUtils.copyProperties(userDTO,
                currentLoginUser);
        currentLoginUser.setImprove(true);
        updateById(currentLoginUser);
    }
}
