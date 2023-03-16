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
     * code
     */
    @NotNull(message = "code不能为空", groups = UserLogin.class)
    @Size(min = 2)
    private String code;

    /**
     * 头像
     */
    @NotNull(message = "头像不能为空", groups = UserInfoUpdate.class)
    @Size(min = 2)
    private String avatar;

    /**
     * 昵称
     */
    @Size(min = 2)
    @NotNull(message = "昵称不能为空", groups = UserInfoUpdate.class)
    private String nickName;


    /**
     * 性别
     */
    @Size(min = 2)
    @NotNull(message = "性别不能为空",
            groups = UserInfoUpdate.class)
    private OwUser.Gender sex;


    /**
     * 登录
     */
    public interface UserLogin {

    }

    /**
     * 注册
     */
    public interface UserInfoUpdate {

    }
}


