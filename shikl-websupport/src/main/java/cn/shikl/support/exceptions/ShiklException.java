package cn.shikl.support.exceptions;

import cn.shikl.support.constants.BusiTypeCode;

/**
 * 公共异常
 *
 * @author shikl
 * @version v1.0    2016/12/27.
 */
public class ShiklException extends Exception {

    private String code = "1";
    /**
     * 业务类型
     */
    private String busi = "";

    public ShiklException() {
    }

    public ShiklException(String message) {
        super(message);
    }

    public ShiklException(BusiTypeCode busi, String code, String message) {
        super(message);
        this.code = code;
        this.busi = busi.code();
    }

    public ShiklException(String message, Throwable cause) {
        super(message, cause);
    }

    public ShiklException(Throwable cause) {
        super(cause);
    }

    public ShiklException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getBusi() {
        return busi;
    }

    public void setBusi(String busi) {
        this.busi = busi;
    }
}
