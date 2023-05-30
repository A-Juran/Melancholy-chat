package la.iit.service;

import com.baomidou.mybatisplus.extension.service.IService;
import la.iit.common.exception.UserIsExistException;
import la.iit.entity.domain.OwUser;
import la.iit.entity.dto.UserDTO;

import java.util.HashMap;

/**
 * @author JuRan
 * @date 2023/3/3
 */
public interface UserService extends IService<OwUser> {
    //登录
    String login(String username, String password) throws Exception;
    //注册
    void register(UserDTO userDTO) throws UserIsExistException;
    //验证码
    HashMap<String, Object> captcha();
}
