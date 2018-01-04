package cn.shikl.core.exeception;

/**
 * @author shikl.
 * @date 2016/12/19.
 */
public class NotSupportedException extends ApplicationException {

    private static final long serialVersionUID = 1L;

    public NotSupportedException() {
    }

    public NotSupportedException(String arg0) {
        super(arg0);
    }

    public NotSupportedException(Throwable arg0) {
        super(arg0);
    }

    public NotSupportedException(String arg0, Throwable arg1) {
        super(arg0, arg1);
    }
}
