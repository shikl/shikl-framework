package cn.shikl.data.execption;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 当构造查询条件出现错误时抛出此异常.
 * 
 * @author shikl
 * 
 */
public class CriteriaException extends RuntimeException {
	private final Logger log = LoggerFactory.getLogger(getClass());
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CriteriaException() {
		super();
	}

	public CriteriaException(String message, Throwable cause) {
		super(message, cause);
		log.error(message, cause);
	}

	public CriteriaException(String message) {
		super(message);
		log.error(message);
	}

	public CriteriaException(Throwable cause) {
		super(cause);
		log.error(cause.toString());
	}

}
