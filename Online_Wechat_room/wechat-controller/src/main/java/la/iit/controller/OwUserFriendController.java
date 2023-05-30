package la.iit.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import la.iit.annotation.RequiresAuthentication;
import la.iit.annotation.SysLogin;
import la.iit.annotation.VisitLimit;
import la.iit.entity.domain.OwUser;
import la.iit.entity.vo.GetFriendVO;
import la.iit.entity.vo.UserFriendListVO;
import la.iit.response.AjaxResult;
import la.iit.service.OwUserFriendService;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

import static la.iit.common.Constant.*;

/**
 * @author JuRan
 * @date 3/17/2023
 */
@RestController
@RequestMapping("/friend")
@Tag(name = "好友管理")
public class OwUserFriendController {
    final OwUserFriendService owUserFriendService;

    public OwUserFriendController(OwUserFriendService owUserFriendService) {
        this.owUserFriendService = owUserFriendService;
    }

    @PostMapping("/addFriend")
    @Operation(summary = "发送添加好友请求")
    @SysLogin
    @VisitLimit(sec = 60, limit = 3)
    @RequiresAuthentication
    public AjaxResult addFriend(@NotNull(message = "未填写好友Id")
                                Long id) {
        try {
            owUserFriendService.addFriend(id);
        } catch (Exception e) {
            return AjaxResult.failed(ADD_USER_FAILED.value());
        }
        return AjaxResult.success(ADD_USER_SUCCESS.value());
    }

    @GetMapping("/getOneFriend")
    @Operation(summary = "搜索好友信息")
    @SysLogin
    @VisitLimit(sec = 60, limit = 3)
    @RequiresAuthentication
    public AjaxResult getOneFriend(@Valid @NotNull(message = "请输入添加用户信息")
                                   String userNameOrId) {
        OwUser friend = null;
        try {
            friend = owUserFriendService
                    .findFriend(userNameOrId);
        } catch (Exception e) {
            return AjaxResult.success(ADD_USER_FAILED.value());
        }
        GetFriendVO getFriendVO = new GetFriendVO();
        BeanUtils.copyProperties(friend, getFriendVO);
        return AjaxResult.success(ADD_USER_SUCCESS.value(), getFriendVO);
    }

    @DeleteMapping("/delFriend")
    @Operation(summary = "删除好友")
    @SysLogin
    @VisitLimit(sec = 60, limit = 3)
    public AjaxResult delFriend(@NotNull(message = "未填写好友Id")
                                Long id) {
        try {
            owUserFriendService.delFriend(id);
        } catch (Exception e) {
            return AjaxResult.success(DEL_USER_FAILED);
        }
        return AjaxResult.success(DEL_USER_SUCCESS);
    }

    @GetMapping("/getUserFriendInfo")
    @Operation(summary = "获取好友列表信息")
    @SysLogin
    @VisitLimit(sec = 60, limit = 3)
    @RequiresAuthentication
    public AjaxResult getUserFriendList() {
        List<UserFriendListVO> userFriendList =
                owUserFriendService.getUserFriendList();
        return AjaxResult.success()
                .put("friendList", userFriendList);
    }
}
