package la.iit.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import la.iit.entity.domain.OwUser;
import la.iit.entity.domain.OwUserFriend;

import java.util.List;

/**
* @author Juran
* @createDate 2023-03-22 16:17:15
* @Entity generator.domain.OwUserFriend
*/
public interface OwUserFriendMapper extends BaseMapper<OwUserFriend> {
    //获取好友列表
    List<OwUser> getUserFriendList(Long currentLoginUserId);
}




