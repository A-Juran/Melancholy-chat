package la.iit.service;


import com.baomidou.mybatisplus.extension.service.IService;
import la.iit.entity.domain.OwUser;
import la.iit.entity.domain.OwUserFriend;
import la.iit.entity.vo.UserFriendListVO;

import java.util.List;

/**
* @author Juran
* @createDate 2023-03-22 16:17:15
*/
public interface OwUserFriendService extends IService<OwUserFriend> {
    //查询好友
    OwUser findFriend(String userNameOrId);
    //添加好友
    void addFriend(Long id);
    //删除用户
    void delFriend( Long id);
    //获取好友列表
    List<UserFriendListVO> getUserFriendList();
}
