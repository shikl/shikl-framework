/**
 *
 */
package cn.shikl.data.validator;

/**
 * 验证接口，用于验证的实现必须实现此接口.
 *
 * @param <T> 泛型参数 .
 * @author libo .
 * @version 1.0
 */
public interface IValidator<T> {

    /**
     * 验证器.
     *
     * @param t <T> .
     */
    void validate(T t);

}
