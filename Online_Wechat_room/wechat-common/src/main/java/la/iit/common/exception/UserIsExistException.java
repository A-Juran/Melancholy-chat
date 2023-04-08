package la.iit.common.exception;

/**
 * @author JuRan
 * @date 4/3/2023
 */
public class UserIsExistException extends Throwable {
    public UserIsExistException(String msg) {
        super(msg);
    }
}
