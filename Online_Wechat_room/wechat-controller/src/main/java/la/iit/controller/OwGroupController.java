package la.iit.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import la.iit.annotation.SysLogin;
import la.iit.annotation.VisitLimit;
import la.iit.response.AjaxResult;
import org.springframework.web.bind.annotation.*;

/**
 * @author JuRan
 * @date 3/16/2023
 */
@RestController
@RequestMapping("/chatRoom")
@Tag(name = "聊天室管理")
public class OwGroupController {
    @PostMapping("/saveChatRoom")
    @Operation(summary = "创建聊天室")
    @SysLogin
    @VisitLimit(sec = 60, limit = 3)
    public AjaxResult addChatRoom(){
        return null;
    }

    @DeleteMapping("/delChatRoom")
    @Operation(summary = "删除聊天室")
    @SysLogin
    @VisitLimit(sec = 60, limit = 3)
    public AjaxResult delChatRoom(){
        return null;
    }
}
