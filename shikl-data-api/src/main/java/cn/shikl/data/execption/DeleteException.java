package cn.shikl.data.execption;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>
 * 当删除一个对象时，该对象正在被其他对象使用抛出此异常.
 * <p>
 * 
 * @author shikl
 * @version 1.0
 * 
 */
public class DeleteException extends RuntimeException {
    /**
	 * 
	 */
    private final Logger log = LoggerFactory.getLogger(getClass());
    /**
	 * 
	 */
    private static final long serialVersionUID = 6975296141859277747L;

    /**
	 * 
	 */
    public DeleteException() {
        super();
    }

    public DeleteException(final String code, final String message) {
        StringBuilder sb = new StringBuilder();
        sb.append("error code: ").append(code).append(" message : ").append(message);
    }

    /**
     * <p>
     * 显示用户提供的异常信息.
     * <p>
     * 
     * @param arg0
     *            用户提供的异常信息.
     * @param arg1
     *            throwable 实例.
     */
    public DeleteException(final String arg0, final Throwable arg1) {
        super(arg0, arg1);
        log.error(arg0, arg1);
    }

    /**
     * <p>
     * 用户自定义显示异常的信息.
     * <p>
     * 
     * @param arg0
     *            异常信息.
     */
    public DeleteException(final String arg0) {
        super(arg0);
        log.error(arg0);
    }

    public DeleteException(final String mes, Object... args) {
        super(mes);
        log.error(mes, args);
    }
}
