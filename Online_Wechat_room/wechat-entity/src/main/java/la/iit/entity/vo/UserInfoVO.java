package la.iit.entity.vo;

import la.iit.entity.domain.OwUser;
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

    private OwUser.Gender sex;

    public static UserInfoVO builder(){
        return new UserInfoVO();
    }
}
