package cn.shikl.data.listener;

/**
 * @author shikl <br/>
 *         date:2017-04-23
 * @version 1.0.0
 */
public interface UpdateEventListener<T> {

    /**
     * 在调用实际保存前执行.
     *
     * @param t 保存的对象.
     */
    void beforeUpdate(T t);

    /**
     * 在调用实际保存后，返回前执行.
     *
     * @param t 保存的对象.
     */
    void afterUpdate(T t);
}
