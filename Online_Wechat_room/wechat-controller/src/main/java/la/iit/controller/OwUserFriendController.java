package la.iit.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import la.iit.annotation.SysLogin;
import la.iit.annotation.VisitLimit;
import la.iit.response.AjaxResult;
import org.springframework.web.bind.annotation.*;

/**
 * @author JuRan
 * @date 3/17/2023
 */
@RestController
@RequestMapping("/friend")
@Tag(name = "好友管理")
public class OwUserFriendController {
    @PostMapping("/addFriend")
    @Operation(summary = "添加好友")
    @SysLogin
    @VisitLimit(sec = 60, limit = 3)
    public AjaxResult addFriend() {
        return null;
    }

    @DeleteMapping("/delFriend")
    @Operation(summary = "删除好友")
    @SysLogin
    @VisitLimit(sec = 60, limit = 3)
    public AjaxResult delFriend() {
        return null;
    }

    @GetMapping("/getUserFriendInfo")
    @Operation(summary = "获取好友列表信息")
    @SysLogin
    @VisitLimit(sec = 60, limit = 3)
    public AjaxResult getUserFriendInfo() {
        return null;
    }
}
