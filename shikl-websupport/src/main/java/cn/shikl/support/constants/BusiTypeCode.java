package cn.shikl.support.constants;

/**
 * Created by shikl on 2017/1/24.
 */
public enum  BusiTypeCode {

    Authc("A"), DEFAULT("");

    private final String code;

    private BusiTypeCode(String code) {
        this.code = code;
    }

    public String code() {
        return this.code;
    }

}
