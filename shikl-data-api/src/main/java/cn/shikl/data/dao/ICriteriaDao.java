package cn.shikl.data.dao;

import java.util.List;

import cn.shikl.data.criteria.Criteria;
import cn.shikl.data.criteria.Page;

/**
 * 离线查询接口.
 *
 * @param <T> T类型.
 * @author shikl .
 * @version 1.0
 */
public interface ICriteriaDao<T> {

    /**
     * 通过离线查询.
     * <p/>
     * <pre>
     * Criteria c = dictionaryService.getCriteriaFactory().getCriteria(Dictionary.class);
     * c.add(Restrictions.eq(&quot;name&quot;, &quot;column1&quot;));
     * List&lt;Dictionary&gt; list = dictionaryService.find(c);
     * </pre>
     *
     * @param c 离线查询条件.
     *          .
     * @return 返回List列表.
     */
    List<T> find(final Criteria c);

    /**
     * 通过离线查询唯一记录.
     * <p/>
     * <pre>
     *
     * Criteria c = dictionaryService.getCriteriaFactory().getCriteria(Dictionary.class);
     * c.addOrder(Order.asc(&quot;dictId&quot;));
     * Dictionary dictionary = dictionaryService.findTopOne(c);
     * </pre>
     *
     * @param c 离线查询条件.
     * @return T 实体对象.
     */
    T findTopOne(Criteria c);

    /**
     * 通过离线查询条件查询count.
     * <p/>
     * <pre>
     * </pre>
     *
     * @param c 查询条件.
     * @return int.
     */
    int countByCirteria(Criteria c);

    /**
     * 通过离线查询数据求和.
     * <p/>
     * <pre>
     * </pre>
     *
     * @param c      查询条件对象.
     * @param fields 参数列表.
     * @return float.
     */
    float[] sumByCirteria(Criteria c, String[] fields);

    /**
     * 通过离线查询查询唯一.
     * <p/>
     * <pre>
     *
     * </pre>
     *
     * @param c 查询条件对象.
     * @return Object.
     */
    Object findOnlyByCriteria(Criteria c);

    /**
     * 通过条件查询数据.
     * <p/>
     * <pre>
     *
     * Pageable page = new PageRequest(true, 10);
     * Criteria criteria = dictionaryService.getCriteria(Dictionary.class);
     * criteria.add(Restrictions.or(Restrictions.eq(&quot;name&quot;, &quot;column1&quot;), Restrictions.eq(&quot;name&quot;, &quot;column2&quot;)));
     * criteria.setPageable(page);
     * Page&lt;Dictionary&gt; pageList = dictionaryService.findWithPage(criteria);
     * </pre>
     *
     * @param c 构造条件对象.
     * @return 返回查询结果 其中包括数据和数据条数count.
     */
    Page<T> findWithPage(Criteria c);
}
