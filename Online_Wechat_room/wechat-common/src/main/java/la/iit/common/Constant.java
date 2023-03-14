package la.iit.common;

/**
 * @author JuRan
 * @date 2/12/2023
 */
public enum Constant {
    LOG_RECORD_FAIL("日志记录失败"),
    LOG_RECORD_SUCCESS("日志记录失败"),
    REQUEST_EXCEPTION("日志记录失败"),
    LOGIN_USER("LOGIN-USER"),
    LOGIN_USER_OPEN_ID("LOGIN-USER-OPEN-ID");
    private String msg;

    Constant(String msg) {
        this.msg = msg;
    }

    public String value() {
        return msg;
    }
}
