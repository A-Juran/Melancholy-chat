package la.iit.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private String code;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 昵称
     */
    private String nickName;

}
