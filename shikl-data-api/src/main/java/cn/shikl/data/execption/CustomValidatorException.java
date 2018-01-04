/**
 * 
 */
package cn.shikl.data.execption;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.support.PropertiesLoaderUtils;

/**
 * 自定义异常.
 * 
 * @author shikl
 * 
 */
public class CustomValidatorException extends RuntimeException {
    /**
     * .
     */
    private static final long serialVersionUID = -2926246418884294599L;
    /**
     * 验证信息文件.
     */
    private static final String VALIDATION_FILENAME = "ValidationMessages.properties";

    /**
     * 验证信息的properties文件对象.
     */
    private static Properties validationMessages;
    /**
     * 声明log处理.
     */
    private final Logger log = LoggerFactory.getLogger(getClass());

    /**
     * 自定义异常构造方法.
     */
    public CustomValidatorException() {
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
    public CustomValidatorException(final String arg0, final Throwable arg1) {
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
    private CustomValidatorException(final String arg0) {
        super(arg0);
    }

    /**
     * 显示默认异常信息.
     * 
     * @param arg0
     *            throwbale.
     */
    public CustomValidatorException(final Throwable arg0) {
        super(arg0);
    }

    static {
        try {
            validationMessages = PropertiesLoaderUtils.loadAllProperties(VALIDATION_FILENAME);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * .
     * 
     * @param arg0
     *            参数 properties key.
     * @param arg1
     *            参数用于占位符替换如 {0}和{1}不能一致. 需传入"用户名","密码".
     */
    public CustomValidatorException(String arg0, Object... arg1) {
        String message = (String) validationMessages.get(arg0);
        if (arg1.length <= 0) {
            throw new CustomValidatorException(message);
        }
        String messageFormat = MessageFormat.format(message, arg1);
        throw new CustomValidatorException(messageFormat);
    }
}
