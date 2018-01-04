package cn.shikl.support.constants;

/**
 * Created by yangcm on 2017/1/23.
 */
public enum  ExceptionCode {

    NullPointerException("1001", "[服务器]空值异常"),
    ClassCastException("1002", "[服务器]数据类型转换异常"),
    IOException("1003", "[服务器]IO异常"),
    NoSuchMethodException("1004", "[服务器]未知方法异常"),
    AuthenticationException("1000", "登录异常"),
    IndexOutOfBoundsException("1005", "[服务器]数组越界异常");



    private final String code;
    private final String message;

    private ExceptionCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String code() {
        return this.code;
    }

    public String message() {
        return message;
    }
}
