package la.iit.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import la.iit.annotation.SysLogin;
import la.iit.annotation.VisitLimit;
import la.iit.server.WebSocketServer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * @author JuRan
 * @date 2023/1/12
 */
@RestController
@RequestMapping("/websocket")
@Tag(name = "信息管理")
public class WebSocketController {
    @PostMapping("/push/{toUID}")
    @Operation(summary = "指定好友发送信息")
    @SysLogin
    @VisitLimit(sec = 60,limit = 10)
    public ResponseEntity<String> pushToClient(String message, @PathVariable String toUID) throws IOException {
        WebSocketServer.sendInfo(message, toUID);
        return ResponseEntity.ok("Send Success!");
    }
}
