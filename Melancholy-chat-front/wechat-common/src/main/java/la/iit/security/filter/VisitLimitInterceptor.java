package la.iit.security.filter;

import la.iit.annotation.RequiresAuthentication;
import la.iit.annotation.VisitLimit;
import la.iit.entity.domain.OwUser;
import la.iit.exception.AuthenticationException;
import la.iit.utils.GlobalParamsUtils;
import la.iit.utils.IPUtils;
import la.iit.utils.RedisUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.util.ObjectUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

import static la.iit.common.Constant.LOGIN_USER;
import static la.iit.common.Constant.UN_AUTHENTICATION;

/**
 * @author JuRan
 * @date 2023/3/1
 */
@Component
public class VisitLimitInterceptor implements HandlerInterceptor {
    private RedisUtils redisServer;
    private GlobalParamsUtils globalParamsUtils;

    public VisitLimitInterceptor(RedisUtils redisServer, GlobalParamsUtils globalParamsUtils) {
        this.redisServer = redisServer;
        this.globalParamsUtils = globalParamsUtils;
    }

    //预处理
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("token");
        Assert.notNull(token, UN_AUTHENTICATION.value());
        OwUser owUser = null;
        owUser = (OwUser) redisServer.get(LOGIN_USER.value() + token);
        if (!ObjectUtils.isEmpty(owUser)) {
            //设置token
            globalParamsUtils.setToken(token);
            //设置当前登录用户信息
            globalParamsUtils.setCurrentUser(owUser);
        }
        //根据自定义注解判定 是否需要认证|判断是否需要权限等。
        HandlerMethod method = (HandlerMethod) handler;
        boolean hasMethodAnnotation = method.hasMethodAnnotation(RequiresAuthentication.class);
        if (hasMethodAnnotation && !ObjectUtils.isEmpty(token)) {
            if (ObjectUtils.isEmpty(token) || ObjectUtils.isEmpty(owUser))
                throw new AuthenticationException();
        }
        //接口限制
        requestFrequencyLimit(request, response, handler);
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }

    private void requestFrequencyLimit(HttpServletRequest request, HttpServletResponse response, Object handler) {
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            Method method = handlerMethod.getMethod();
            //如果请求方法中没有VisitLimit注解直接放行请求
            if (!method.isAnnotationPresent(VisitLimit.class)) {
                return;
            }
            VisitLimit accessLimit = method.getAnnotation(VisitLimit.class);
            if (accessLimit == null) {
                return;
            }
            //取出接口中所设置的频率
            int limit = accessLimit.limit();
            long sec = accessLimit.sec();
            //获取请求的用户IP及Url
            String key = IPUtils.getIpAddr(request) + request.getRequestURI();
            //获取请求的次数
            Integer maxLimit = null;
            //主要逻辑处理
            Object limitObject = redisServer.get(key);
            if (limitObject != null && !limitObject.equals("")) {
                //初始化将字符串转换具体值。
                maxLimit = Integer.valueOf(String.valueOf(limitObject));
            }
            if (maxLimit == null) {
                redisServer.set(key, "1", sec);
            } else if (maxLimit < limit) {
                Integer i = maxLimit + 1;
                redisServer.set(key, i, sec);
            } else {
                throw new RuntimeException("频繁请求操作，请稍后再进行操作！");
            }
        }
    }


    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
