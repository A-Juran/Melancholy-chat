package la.iit.server;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import la.iit.utils.GlobalParamsUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author JuRan
 * @date 2023/1/11
 * webSocket服务端
 */
@Component
@ServerEndpoint("/websocket/server")
@Slf4j
public class WebSocketServer {

    //记录当前连接数量，设置为线程安全。
    private static int onlineClientCount = 0;

    // concurrentHashMap包是线程安全的set，用来存放每个客户端对应的webSocket对象。
    private static ConcurrentHashMap<String, WebSocketServer> webSocketServerConcurrentHashMap = new ConcurrentHashMap<>();

    //与每个客户端连接的会话，通过它来给客户端发送数据。
    private Session session;

    //接收客户信息的Uid。
    private String uid = "";

    private GlobalParamsUtils globalParamsUtils;

    public WebSocketServer(GlobalParamsUtils globalParamsUtils) {
        this.globalParamsUtils = globalParamsUtils;
    }

    /**
     * 与客户端建立成功返回信息
     *
     * @param session 当前登录客户连接
     */
    @OnOpen
    public void onOpen(Session session) {
        this.session = session;
        this.uid = globalParamsUtils
                .getCurrentUser().getId().toString();
        if (webSocketServerConcurrentHashMap.contains(uid)) {
            webSocketServerConcurrentHashMap.remove(uid);
            webSocketServerConcurrentHashMap.put(uid, this);
        } else {
            webSocketServerConcurrentHashMap.put(uid, this);
        }

        log.info("连接用户{}，当前在线人数{}", uid, onlineClientCount);
        //连接后返回连接状态。
        try {
            sendMsg("连接成功");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 信息发送
     *
     * @param message 发送报文。
     * @param session 当前登录用户连接。
     */
    @OnMessage
    public void onMessage(String message, Session session) {
        log.info("接收到的信息：{},所收到的报文：{}", message, session);
        if (StringUtils.hasText(message)) {
            try {
                //解析需要发送的报文
                JSONObject jsonObject = JSON.parseObject(message);
                //追加发送人，防止被串改。
                jsonObject.put("sendUid", uid);
                String toUID = jsonObject.getString("toUID");
                if (StringUtils.hasText(toUID) && webSocketServerConcurrentHashMap.containsKey(toUID)) {
                    webSocketServerConcurrentHashMap.get(toUID).sendMsg(jsonObject.toJSONString());
                } else {
                    //若用户不在，则发送至mysql或者是redis，用户可进行二次查询。
                    log.error("当前用户：{}，发送信息对象：{}已下线", uid, toUID);
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

        }
    }

    //todo 消息群发。


    /**
     * 处理错误
     */
    @OnError
    public void onError(Session session, Throwable error) {
        log.error("用户错误:{}，原因：{}", this.uid, error.getMessage());
        error.printStackTrace();
    }

    /**
     * 连接关闭。
     */
    @OnClose
    public void onClose() {
        if (webSocketServerConcurrentHashMap.contains(uid)) {
            //从set中进行删除
            webSocketServerConcurrentHashMap.remove(uid);
            //减少在线人数
            subOnlineClientCount();
        }
        log.info("用户：{}退出登录，当前在线人数：{}人", uid, onlineClientCount);
    }

    /**
     * 发送自定义消息
     */
    public static void sendInfo(String message, @PathParam("uid") String uid) throws IOException {
        log.info("发送消息到：{},发送的报文:{}" + uid, message);
        if (StringUtils.hasText(uid) && webSocketServerConcurrentHashMap.containsKey(uid)) {
            webSocketServerConcurrentHashMap.get(uid).sendMsg(message);
        } else {
            log.error("用户：{}不在线！", uid);
        }
    }


    /**
     * 向当前登录用户返回信息。
     *
     * @param msg 返回信息
     */
    private void sendMsg(String msg) throws IOException {
        this.session.getBasicRemote().sendText(msg);
    }

    /**
     * 在线人数处理函数。
     * 1、获取在线人数。
     * 2、增加在线人数。
     * 3、减少在线人数。
     */
    private static synchronized int getOnlineClientCount() {
        return onlineClientCount;
    }

    private static synchronized void addOnlineClientCount() {
        WebSocketServer.onlineClientCount++;
    }

    private static synchronized void subOnlineClientCount() {
        WebSocketServer.onlineClientCount--;
    }

}
