package la.iit.service.impl;


import cn.hutool.core.lang.Assert;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import la.iit.entity.domain.OwUser;
import la.iit.entity.domain.OwUserFriend;
import la.iit.entity.vo.UserFriendListVO;
import la.iit.mapper.OwUserFriendMapper;
import la.iit.mapper.UserMapper;
import la.iit.service.OwUserFriendService;
import la.iit.utils.GlobalParamsUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static la.iit.common.Constant.NONE_FIND_FRIEND;

/**
 * @author Juran
 * @createDate 2023-03-22 16:17:15
 */
@Service
public class OwUserFriendServiceImpl extends ServiceImpl<OwUserFriendMapper, OwUserFriend>
        implements OwUserFriendService {

    private UserMapper userMapper;

    private OwUserFriendMapper owUserFriendMapper;

    private GlobalParamsUtils globalParamsUtils;

    public OwUserFriendServiceImpl(UserMapper userMapper,
                                   GlobalParamsUtils globalParamsUtils,
                                   OwUserFriendMapper owUserFriendMapper) {
        this.userMapper = userMapper;
        this.globalParamsUtils = globalParamsUtils;
        this.owUserFriendMapper = owUserFriendMapper;
    }

    @Override
    public OwUser findFriend(String userNameOrId) {
        LambdaQueryWrapper<OwUser> friendWrapper =
                Wrappers.<OwUser>lambdaQuery().eq(OwUser::getNickName, userNameOrId)
                        .or().eq(OwUser::getId, userNameOrId);
        return userMapper.selectOne(friendWrapper);
    }

    @Override
    public void addFriend(Long id) {
        /*
            进行好友查询避免插入空数据。
         */
        OwUser friend = findFriend(String.valueOf(id));
        Assert.notNull(friend, NONE_FIND_FRIEND.value());
        //获取当前登录用户信息
        OwUser currentUser = globalParamsUtils.getCurrentUser();
        OwUserFriend owUserFriend =
                new OwUserFriend()
                        .setFriend_id(id)
                        .setUser_id(currentUser.getId());
        save(owUserFriend);
    }

    @Override
    public void delFriend(Long id) {
        /**
         * 删除好友表数据
         */
        LambdaQueryWrapper<OwUserFriend> delFriendEq =
                Wrappers.<OwUserFriend>lambdaQuery()
                .eq(OwUserFriend::getFriend_id, id)
                .eq(OwUserFriend::getUser_id, globalParamsUtils.getCurrentUser().getId());
        owUserFriendMapper.delete(delFriendEq);
    }

    @Override
    public List<UserFriendListVO> getUserFriendList() {
        Long currentLoginUserId =
                globalParamsUtils.getCurrentUser().getId();
        List<OwUser> userFriendList =
                owUserFriendMapper.getUserFriendList(currentLoginUserId);
        List<UserFriendListVO> userFriendListVO =
                new ArrayList<>();
        BeanUtils.copyProperties(userFriendList,userFriendListVO);
        return userFriendListVO;
    }
}




