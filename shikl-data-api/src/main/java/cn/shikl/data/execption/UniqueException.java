package cn.shikl.data.execption;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>
 * 当要保存的记录,在数据库已存在(业务单据编号重复,不一定是数据库主键重复)时,抛出该异常.
 * <p>
 * 
 * @author shikl
 * @version 1.0
 * @since 1.0
 */
public class UniqueException extends RuntimeException {
	/**
	 * 
	 */
	private final Logger log = LoggerFactory.getLogger(getClass());
	/**
	 *
	 */
	private static final long serialVersionUID = -2926246418884294599L;

	/**
	 * 
	 */
	public UniqueException() {
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
	public UniqueException(final String arg0, final Throwable arg1) {
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
	public UniqueException(final String arg0) {
		super(arg0);
		log.error(arg0);
	}

	/**
	 * 显示默认异常信息.
	 * 
	 * @param arg0
	 *            throwbale.
	 */
	public UniqueException(final Throwable arg0) {
		super(arg0);
	}


	public UniqueException(final String arg0, Object... arg1) {
		super(arg0);
		log.error(arg0, arg1);
	}
}
