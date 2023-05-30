package la.iit.exception;

import static la.iit.common.Constant.AUTHENTICATION_FAIL;

/**
 * @author JuRan
 * @date 5/17/2023
 */
public class AuthenticationException extends Exception{
    public AuthenticationException() {
        super(AUTHENTICATION_FAIL.value());
    }
}
