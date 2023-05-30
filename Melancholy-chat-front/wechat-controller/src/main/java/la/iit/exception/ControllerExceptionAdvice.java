package la.iit.exception;

import la.iit.response.AjaxResult;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice("la.iit.controller")
public class ControllerExceptionAdvice {
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public AjaxResult handlerMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        BindingResult res = e.getBindingResult();
        Map<String, String> errorMap = new HashMap<>();
        res.getFieldErrors().forEach((item) -> {
            errorMap.put(item.getField(), item.getDefaultMessage());
        });
        return AjaxResult.error(errorMap.toString());
    }
}

