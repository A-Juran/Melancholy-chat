package la.iit.aop;

import com.alibaba.fastjson.JSONObject;
import la.iit.common.Constant;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @author JuRan
 * @date 2/12/2023
 */
@Aspect
@Component
@Slf4j
public class LogAspect {

    @Pointcut("@annotation(la.iit.annotation.SysLogin)")
    public void sysLogin(){
    }

    @Around("sysLogin()")
    public Object loginLogAround(ProceedingJoinPoint point){
        Object proceed;
        try {
            proceed = point.proceed();
        } catch (Throwable e) {
            //错误日志
            saveLog(point, Constant.LOG_RECORD_FAIL,e);
            throw new RuntimeException(e);
        }
        saveLog(point, Constant.LOG_RECORD_SUCCESS,null);
        return proceed;
    }

    private void saveLog(ProceedingJoinPoint point, String logRecordSuccess, Object o) {
        //获取request请求对象。
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        //记录ip|请求url|请求参数.
        String host = getRemoteHost(request);
        String url = request.getRequestURI();
        String reqParam = Arrays.toString(Arrays.stream(point.getArgs()).toArray());
        log.info("请求IP:【{}】,URL:【{}】,参数:【{}】",host,url,reqParam);
    }

    /**
     * 获取请求参数
     */
    public String preHandle(ProceedingJoinPoint point,HttpServletRequest request){
        String reqParam = "";
        //todo? I don't know?
        Signature signature = point.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method method = methodSignature.getMethod();
        Annotation[] annotations = method.getAnnotations();
        //遍历请求参数
        for (Annotation annotation : annotations) {
            //判断请求方法注解是否为RequestMapping
            if (annotation.annotationType().equals(RequestMapping.class)){
                reqParam = JSONObject.toJSONString(request.getParameterMap());
                break;
            }
        }
        return reqParam;
    }

    /**
     * 获取目标主机的ip
     * @param request
     * @return
     */
    private String getRemoteHost(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return "0:0:0:0:0:0:0:1".equals(ip) ? "127.0.0.1" : ip;
    }
}
