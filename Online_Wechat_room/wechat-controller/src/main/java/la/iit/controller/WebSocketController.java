package la.iit.controller;

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
public class WebSocketController {

    @PostMapping("/push/{toUID}")
    public ResponseEntity<String>  pushToClient(String message, @PathVariable String toUID) throws IOException {
        WebSocketServer.sendInfo(message,toUID);
        return ResponseEntity.ok("Send Success!");
    }


}
