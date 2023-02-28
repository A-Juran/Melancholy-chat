package la.iit.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

/**
 * IP Utils
 * @author rs
 *
 */
public class IPUtils {
	private static Logger logger = LoggerFactory.getLogger(IPUtils.class);

	/**
	 * 获取IP地址
	 * 
	 * 使用Nginx等反向代理软件， 则不能通过request.getRemoteAddr()获取IP地址
	 * 如果使用了多级反向代理的话，X-Forwarded-For的值并不止一个，而是一串IP地址，X-Forwarded-For中第一个非unknown的有效IP字符串，则为真实IP地址
	 */
	public static String getIpAddr(HttpServletRequest request) {
    	String ip = null;
        try {
            ip = request.getHeader("x-forwarded-for");
	         if (ip != null && ip.length() != 0 && !"unknown".equalsIgnoreCase(ip)) {
	             // 多次反向代理后会有多个ip值，第一个ip才是真实ip
	             if( ip.indexOf(",")!=-1 ){
	                 ip = ip.split(",")[0];
	             }
	         }
	         if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
	             ip = request.getHeader("Proxy-Client-IP");
	         }
	         if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
	             ip = request.getHeader("WL-Proxy-Client-IP");
	         }
	         if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
	             ip = request.getHeader("HTTP_CLIENT_IP");
	         }
	         if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
	             ip = request.getHeader("HTTP_X_FORWARDED_FOR");
	         }
	         if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
	             ip = request.getHeader("X-Real-IP");
	         }
	         if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
	             ip = request.getRemoteAddr();
	         }
        } catch (Exception e) {
        	logger.error("IPUtils ERROR ", e);
        }
        
        return ip;
    }
	
}