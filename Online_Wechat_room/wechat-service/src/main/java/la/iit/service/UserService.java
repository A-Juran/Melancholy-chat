package la.iit.service;

import com.baomidou.mybatisplus.extension.service.IService;
import la.iit.entity.dto.UserDTO;
import la.iit.entity.domain.OwUser;

/**
 * @author JuRan
 * @date 2023/3/3
 */
public interface UserService extends IService<OwUser> {
    //用户信息是否完善
    boolean getUserInfoImproveStatus();
    //登录/注册
    String saveUserInfo(String code);
    //更新用户信息
    void updateUserInfo(UserDTO userDTO);
    //获取当前登录用户信息
    OwUser getCurrentUserInfo();
}
