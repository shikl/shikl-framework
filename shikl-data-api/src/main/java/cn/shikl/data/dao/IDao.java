package cn.shikl.data.dao;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

import cn.shikl.data.criteria.Criteria;
import cn.shikl.data.criteria.Page;
import cn.shikl.data.criteria.Pageable;

/**
 * DAO接口.
 *
 * @param <T>
 * @author shikl .
 * @version 1.0
 */
public interface IDao<T> {

    /**
     * 保存一个实例.
     *
     * @param object 需要保存的实例.
     */
    void save(final T object);

    /**
     * 更新一个实例.
     *
     * @param object 需要更新的实例.
     */
    void saveOrUpdate(T object);

    /**
     * 删除一个实例.
     *
     * @param object 需要删除的实例.
     */
    void delete(T object);

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

    /**
     * 根据ID进行查询对象.
     *
     * @param id    需要查询的ID.
     * @param clazz 查询类.
     * @return 符合条件的对象.
     */
    T get(Class<T> clazz, final Serializable id);

    /**
     * 根据ID装载对象.
     *
     * @param clazz 类.
     * @param id    对象ID.
     * @return Object.
     */
    T load(Class<T> clazz, final Serializable id);

    /**
     * 合并保存一个实例.
     *
     * @param object 合并的实例.
     */
    void merge(T object);

    /**
     * 从数据库根据条件查询记录 。符合条件的记录 有且只有一条.
     * <p/>
     * <pre>
     * Criteria criteria = dictionaryService.getCriteria(Dictionary.class);
     * Dictionary dictionary = (Dictionary) getDao().findOnlyByCriteria(criteria);
     * </pre>
     *
     * @param c 查询条件.
     * @return 返回符合条件的对象.如果查询结果为空或多于1条则抛出异常EmptyValueException.
     */
    T findOnly(final Criteria c);

    /**
     * 查询符合条件的第一条记录.
     * <p/>
     * <pre>
     * Criteria criteria = dictionaryService.getCriteria(Dictionary.class);
     * Dictionary dictionary = (Dictionary) dictionaryService.findTopOne(criteria);
     * </pre>
     *
     * @param c 查询条件.
     * @return 返回符合条件的记录。如果查询结果为空或多于1条不抛出异常.
     */
    T findTopOne(Criteria c);

    /**
     * 从数据库查询符合条件的记录.
     * <p/>
     * <pre>
     * Criteria criteria = dictionaryService.getCriteria(Dictionary.class);
     * List list = dictionaryService.find(criteria);
     * </pre>
     *
     * @param criteria 查询条件.
     * @return 返回符合条件的结果.
     */
    List<T> find(Criteria criteria);

    /**
     * 统计记录总数.
     *
     * @param c 查询对象.
     * @return int.
     */
    long count(Criteria c);

    /**
     * 执行HSQL语句.
     *
     * @param hql string.
     * @return 执行结果.
     */
    Object execute(final String hql);

    /**
     * query查询执行hql .
     *
     * @param hql string.
     * @return List<T>
     */
    List<T> query(final String hql);

    /**
     * query查询执行hql .
     *
     * @param ql     string.
     * @param object [] Object.
     * @return List<T>
     */
    List<T> query(final String ql, final Object[] object);

    /**
     * query查询执行hql .
     *
     * @param ql     string.
     * @param object [] Object.
     * @return List 返回非泛型列表.
     */
    List<?> queryObject(final String ql, final Object[] object);
    /**
     * 执行存储过程.
     *
     * @param proName 存储过程名.
     * @param params  参数列表.
     * @throws SQLException .
     */
    @Deprecated
    void executeProcedure(final String proName, final Object... params) throws SQLException;

    /**
     * 对指定条件的记录集的指定字段求和.
     *
     * @param c      查询条件.
     * @param fields 求和字段列表.
     * @return 求和后结果, 数组内的顺序与参数中的顺序一致.
     */
    float[] sum(Criteria c, final String[] fields);

    /**
     * 对指定条件的记录集的指定字段求最大值.
     * @param c 查询条件.
     * @param fields 求最大值字段列表.
     * @return 求最大值后结果, 数组内的顺序与参数中的顺序一致.
     */
    //Object max(Criteria c);

    /**
     * 返回hiberntae当前session.
     *
     * @return hibernate session.
     */
//    Session getSession();

    /**
     * 通过条件查询数据.
     *
     * @param c 构造条件对象.
     * @return 返回查询结果 其中包括数据和数据条数count.
     */
    Page<T> findWithPage(Criteria c);

    /**
     * 通过ql查询分页数据.有性能问题，建议使用findWithPage(String ql, String countSql,Pageable pageable, Object[] queryParams)方法.
     * @param ql          查询语句.
     * @param pageable    分页信息.
     * @param queryParams 查询参数列表.
     * @return 分页信息.
     */
    Page<T> findWithPage(String ql, Pageable pageable, Object[] queryParams);

    /**
     * 通过ql查询分页数据.
     *
     * @param ql          查询语句.
     * @param pageable    分页信息.
     * @param queryParams 查询参数列表.
     * @return 分页信息.
     */
    Page<T> findWithPage(String ql, String countSql, Pageable pageable, Object[] queryParams);
    /**
     * 统计记录总数.
     *
     * @param ql      查询ql语句.
     * @param objects 参数列表 .
     * @return int.
     */
    long count(String ql, Object... objects);


}