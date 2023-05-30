package la.iit.exception.handler;

import com.google.common.collect.ImmutableMap;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;


/**
 * @author JuRan
 * @date 5/17/2023
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    public static Logger logger = LoggerFactory.getLogger("error");

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Map<String, String> defaultErrorHandler(final HttpServletRequest request, final Exception ex) {
        String requestURI = request.getRequestURI();
        String exceptionStackTrace = ExceptionUtils.getMessage(ex);
        logger.error("uri: {}, ex: {}", requestURI, exceptionStackTrace);
        return ImmutableMap.of("uri", requestURI, "ex", exceptionStackTrace);
    }
}
