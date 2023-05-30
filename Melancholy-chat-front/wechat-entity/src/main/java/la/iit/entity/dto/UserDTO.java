package la.iit.entity.dto;

import la.iit.entity.domain.OwUser;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


/**
 * @author JuRan
 * @date 2/12/2023
 */

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDTO {

    /**
     * 用户名
     */
    @NotNull(message = "用户名不能为空", groups = {UserLogin.class, UserRegister.class})
    @Size(min = 2)
    private String username;

    /**
     * 用户名
     */
    @NotNull(message = "密码不能小于6位|不能为空", groups = {UserLogin.class,
            UserRegister.class})
    @Size(min = 6)
    private String password;

    /**
     * 头像
     */
    @NotNull(message = "头像不能为空", groups = {UserInfoUpdate.class})
    @Size(min = 2)
    private String avatar;

    /**
     * 昵称
     */
    @Size(min = 2)
    @NotNull(message = "昵称不能为空", groups = UserRegister.class)
    private String nickName;


    /**
     * 性别
     */
    @Size(min = 2)
    @NotNull(message = "性别不能为空", groups = {UserInfoUpdate.class})
    private OwUser.Gender sex;

    @Size(min = 6)
    @NotNull(message = "验证Key不能为空", groups = {UserLogin.class})
    private String verKey;

    @Size(min = 5)
    @NotNull(message = "验证码不能为空", groups = {UserLogin.class})
    private String verifyCode;


    /**
     * 登录
     */
    public interface UserLogin {

    }

    /**
     * 登录
     */
    public interface UserRegister {

    }


    /**
     * 注册
     */
    public interface UserInfoUpdate {

    }
}


