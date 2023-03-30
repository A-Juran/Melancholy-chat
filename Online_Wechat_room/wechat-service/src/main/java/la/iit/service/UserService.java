package la.iit.service;

import com.baomidou.mybatisplus.extension.service.IService;
import la.iit.entity.dto.UserDTO;
import la.iit.entity.domain.OwUser;

import java.util.HashMap;

/**
 * @author JuRan
 * @date 2023/3/3
 */
public interface UserService extends IService<OwUser> {
    //登录
    String login(String username, String password);
    //注册
    void register(UserDTO userDTO);
    //验证码
    HashMap<String, Object> captcha();
}
