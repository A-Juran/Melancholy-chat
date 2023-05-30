package la.iit.common.exception;

import static la.iit.common.Constant.ACCOUNT_DISABLED;

/**
 * @author JuRan
 * @date 4/8/2023
 */
public class UserAccountDisabledException extends Exception {
    public UserAccountDisabledException() {
        super(ACCOUNT_DISABLED.value());
    }
}
