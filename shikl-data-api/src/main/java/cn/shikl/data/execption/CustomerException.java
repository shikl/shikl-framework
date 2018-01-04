package cn.shikl.data.execption;


/**
 * @author shikl <br/>
 *         date:2014-04-21
 * @version 1.0.0
 */
public class CustomerException extends RuntimeException {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CustomerException() {
        super();
    }

    public CustomerException(String message) {
        super(message);
    }

    public CustomerException(String message, Throwable cause) {
        super(message, cause);
    }

    public CustomerException(Throwable cause) {
        super(cause);
    }


}
