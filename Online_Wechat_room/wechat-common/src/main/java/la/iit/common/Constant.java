package la.iit.common;

/**
 * @author JuRan
 * @date 2/12/2023
 */
public enum Constant {
    LOG_RECORD_FAIL("日志记录失败"),
    LOG_RECORD_SUCCESS("日志记录失败"),
    REQUEST_EXCEPTION("日志记录失败"),
    REGISTER_SUCCESS("注册成功"),
    REGISTER_FAILED("注册失败"),
   LOGIN_SUCCESS("登录成功"),
    LOGIN_FAILED("登录失败"),
    LOGIN_USER("LOGIN-USER:"),
    LOGIN_USER_OPEN_ID("LOGIN-USER-OPEN-ID:"),
    USER_INFO_COMPLETE_OK("用户信息已完善"),
    USER_INFO_COMPLETE_BEGIN("用户信息未完善"),
    NONE_FIND_FRIEND("没有该好友"),
    ADD_USER_SUCCESS("添加好友成功"),
    ADD_USER_FAILED("添加好友失败"),
    DEL_USER_SUCCESS("删除好友成功"),
    DEL_USER_FAILED("删除好友失败");

    private String msg;

    Constant(String msg) {
        this.msg = msg;
    }

    public String value() {
        return msg;
    }
}
