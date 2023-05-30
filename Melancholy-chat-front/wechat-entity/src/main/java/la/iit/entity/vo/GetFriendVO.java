package la.iit.entity.vo;

import lombok.Data;

/**
 * @author JuRan
 * @date 3/23/2023
 */
@Data
public class GetFriendVO {
    //用户id
    private Long id;
    //用户名
    private String nick_name;
    //用户头像
    private String avatar_Url;
}
