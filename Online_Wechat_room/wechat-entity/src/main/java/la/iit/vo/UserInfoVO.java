package la.iit.vo;

import la.iit.pojo.SysUser;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author JuRan
 * @date 2023/3/16
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfoVO {

    private String nickName;

    private String avatar;

    private SysUser.Gender sex;

    public static UserInfoVO builder(){
        return new UserInfoVO();
    }
}
