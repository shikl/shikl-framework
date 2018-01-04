package cn.shikl.data.execption;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>
 * 当检查一个对象不符合业务规则时,抛出该异常.
 * <p>
 * 
 * @author shikl
 * @version 1.0 <br/>
 *          2009-7-19.
 */
@SuppressWarnings("serial")
public class ValidatorException extends RuntimeException {
    /**
     * 日志.
     */
    private final Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 无参数的构造方法.
     */
    public ValidatorException() {
        super();
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
    public ValidatorException(final String arg0, final Throwable arg1) {
        super(arg0, arg1);
        logger.error(arg0, arg1);
    }

    /**
     * <p>
     * 用户自定义显示异常的信息.
     * <p>
     * 
     * @param arg0
     *            异常信息.
     */
    public ValidatorException(final String arg0) {
        super(arg0);
        logger.error(arg0);
    }

    /**
     * 显示默认异常信息.
     * 
     * @param arg0
     *            throwbale.
     */
    public ValidatorException(final Throwable arg0) {
        super(arg0);
    }

    /**
     * 异常信息.
     * 
     * @param arg0
     *            .
     * @param arg1
     *            .
     */
    public ValidatorException(final String arg0, Object... arg1) {
        super(arg0);
        logger.error(arg0, arg1);
    }
}
