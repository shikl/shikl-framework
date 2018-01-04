package cn.shikl.data.listener;

/**
 * 保存监听接口.支持保存前和保存后的扩展.
 */
public interface SaveEventListener<T> {

    /**
     * 在调用实际保存前执行.
     *
     * @param t 保存的对象.
     */
    void beforeSave(T t);

    /**
     * 在调用实际保存后，返回前执行.
     *
     * @param t 保存的对象.
     */
    void afterSave(T t);
}
