package cn.shikl.data.listener;

/**
 * 删除事件监听接口.
 *
 * @author shikl。
 */
public interface DeleteEventListener<T> {

    /**
     * 在调用实际删除前执行.
     *
     * @param t 删除的对象.
     */
    void beforeDelete(T t);

    /**
     * 在调用实际删除后，返回前执行.
     *
     * @param t 删除的对象.
     */
    void afterDelete(T t);
}
