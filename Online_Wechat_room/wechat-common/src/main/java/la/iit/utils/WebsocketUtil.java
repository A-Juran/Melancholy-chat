package la.iit.utils;

import com.alibaba.fastjson.JSON;
import la.iit.response.AjaxResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import javax.websocket.RemoteEndpoint;
import javax.websocket.Session;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Websocket工具类
 * 记录当前在线的链接对链接进行操作
 */
public class WebsocketUtil {
    /**
     * 日志信息
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(WebsocketUtil.class);
    /**
     * 记录当前在线的Session
     */
    private static final Map<String, Session> ONLINE_SESSION = new ConcurrentHashMap<>();
    public static final String sessionKey = "deviceId";

    /**
     * 添加session
     *
     * @param userId
     * @param session
     */
    public static void addSession(String userId, Session session) {
        // 此处只允许一个用户的session链接。一个用户的多个连接，我们视为无效。
        ONLINE_SESSION.putIfAbsent(userId, session);
    }

    /**
     * 关闭session
     *
     * @param userId
     */
    public static void removeSession(String userId) {
        ONLINE_SESSION.remove(userId);
    }

    /**
     * 给单个用户推送消息
     *
     * @param session
     * @param message
     */
    public static void sendMessage(Session session, String message) {
        if (session == null) {
            return;
        }

        // 同步
        RemoteEndpoint.Async async = session.getAsyncRemote();
        async.sendText(message);
    }

    /**
     * 向所有在线人发送消息
     *
     * @param message
     */
    public static void sendMessageForAll(String message) {
        //jdk8 新方法
        ONLINE_SESSION.forEach((sessionId, session) -> {
            if (session.isOpen()) {
                sendMessage(session, message);
            }
        });
    }

    /**
     * 根据用户ID发送消息
     *
     * @param result
     */
    public static void sendMessage(String sessionId, AjaxResult result) {
        sendMessage(sessionId, JSON.toJSONString(result));
    }

    /**
     * 根据用户ID发送消息
     *
     * @param message
     */
    public static void sendMessage(String sessionId, String message) {
        Session session = ONLINE_SESSION.get(sessionId);
        //判断是否存在该用户的session，判断是否还在线
        if (session == null || !session.isOpen()) {
            return;
        }
        sendMessage(session, message);
    }

    /**
     * 根据ID获取Session
     *
     * @param sessionId
     */
    public static Session getSession(String sessionId) {
        Session session = ONLINE_SESSION.get(sessionId);
        return session;
    }

    /**
     * 根据传过来的key获取session中的参数
     * @param key
     * @param session
     * @return
     */
    public static String getParam(String key, Session session) {
        Map map = session.getRequestParameterMap();
        Object userId1 = map.get(key);
        if (userId1 == null) {
            return null;
        }

        String s = userId1.toString();
        s = s.replaceAll("\\[", "").replaceAll("]", "");

        if (!StringUtils.isEmpty(s)) {
            return s;
        }
        return null;
    }

}
