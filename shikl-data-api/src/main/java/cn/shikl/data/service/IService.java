package cn.shikl.data.service;

import cn.shikl.data.criteria.Criteria;
import cn.shikl.data.criteria.Page;
import cn.shikl.data.listener.SaveEventListener;
import cn.shikl.data.validator.IValidator;

import java.io.Serializable;
import java.util.List;

/**
 * service接口.
 *
 * @param <T>
 * @author shikl.
 * @version 1.0
 */
public interface IService<T> {

    /**
     * 删除一个实例.
     *
     * @param object 删除实例.
     */
    void delete(final T object);

    /**
     * 保存一个实例.
     *
     * @param object 保存对象.
     */
    void save(final T object);

    /**
     * 更新一个实例.
     *
     * @param object 更新对象.
     */
    void saveOrUpdate(final T object);

    /**
     * 更新一个实例 .
     *
     * @param object .
     */
    void merge(final T object);

    /**
     * 检查一个对象是否被其他对象使用.
     *
     * @param object 需要检查的对象.
     * @return 如果这个对象被其他对象使用则返回True.
     */
    boolean isUse(T object);

    /**
     * 通过主键查询对象.
     *
     * @param id     主键值.
     * @param class1 查询类.
     * @return 符合条件的对象.
     */
    T get(Class<T> class1, Serializable id);

    /**
     * 检查一个对象是否重复.如果对象重复则抛出 UniqueException 异常.
     *
     * @param object 需要检查的对象.
     */
    void validateUnique(T object);

    /**
     * 生成编号.
     *
     * @return 生成编号.
     */
    String generateId();

    /**
     * 添加验证实现.
     *
     * @param validator 验证实现类.
     */
    void addValidate(IValidator<T> validator);

    /**
     * 保存事件监听.
     *
     * @param listener 监听实现类.
     */
    void addSaveEventListener(SaveEventListener<T> listener);

    /**
     * 用于验证实体对象（默认提供了一个实现 ValidatorEntity）.
     *
     * @param t T
     */
    void validateEntity(T t);

    /**
     * 根据查询条件查询列表.
     * <p/>
     * <pre>
     * Criteria c = dictionaryService.getCriteriaFactory().getCriteria(Dictionary.class);
     * c.add(Restrictions.eq(&quot;name&quot;, &quot;column1&quot;));
     * List&lt;Dictionary&gt; list = dictionaryService.find(c);
     * </pre>
     *
     * @param c 条件。
     * @return list.
     */
    List<T> find(Criteria c);

    /**
     * 通过实体class获取Criteria对象.
     * 在service中可以如下调用.
     * <p/>
     * <pre>
     * Criteria criteria = getCriteria(Dictionary.class);
     * </pre>
     *
     * @param clazz 实体class.
     * @return Criteria.
     */
    Criteria getCriteria(Class<T> clazz);

    /**
     * 通过条件查询数据.
     * <p/>
     * <pre>
     * Criteria criteria = dictionaryService.getCriteria(Dictionary.class);
     * Pageable pageable = new PageRequest(1, 5);
     * criteria.setPageable(pageable);
     * Page&lt;Dictionary&gt; dis = dictionaryService.findWithPage(criteria);
     * </pre>
     *
     * @param c 构造条件对象.
     * @return 返回查询结果 其中包括数据和数据条数count.
     */
    Page<T> findWithPage(Criteria c);

    /**
     * 查询符合条件的第一条记录.
     * <p/>
     * <pre>
     * Criteria c = dictionaryService.getCriteriaFactory().getCriteria(Dictionary.class);
     * c.addOrder(Order.asc(&quot;dictId&quot;));
     * Dictionary dictionary = dictionaryService.findTopOne(c);
     * </pre>
     *
     * @param c 查询条件.
     * @return 返回符合条件的记录。如果查询结果为空抛出异常.
     */
    T findTopOne(Criteria c);

    /**
     * 如果数据中存在集合，只取一个.
     * <p/>
     * <pre>
     * Criteria c = dictionaryService.getCriteriaFactory().getCriteria(Dictionary.class);
     * c.add(Restrictions.eq(&quot;name&quot;, &quot;column1&quot;));
     * Dictionary dictionary = dictionaryService.findOnly(c);
     * </pre>
     *
     * @param c .
     * @return T.
     */
    T findOnly(final Criteria c);


    /**
     * 批量插入
     * @param list
     */
    void batchInsert(List<T> list);


    /**
     * 批量更新
     * @param list
     */
    void batchUpdate(List<T> list);

}