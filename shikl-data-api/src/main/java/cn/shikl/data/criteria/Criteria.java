package cn.shikl.data.criteria;

import java.util.Map;


/**
 * <p/>
 * 查询条件接口.
 * <p/>
 *
 * @author shikl
 * @version 1.0
 */
public interface Criteria {

    /**
     * 查询对象名称.
     *
     * @return 查询对象名称.
     */
    String getEntityName();

    /**
     * 查询别名.
     *
     * @return 别名.
     */
    String getAlias();

    /**
     * 关联一个属性，进行联合查询.
     *
     * @param property
     *            关联属性.
     * @return Criteria .
     */
    Criteria join(String property);

    /**
     * 关联一个属性，进行联合查询.
     *
     * @param join
     *            关联描述.
     * @return Criteria.
     */
    Criteria join(Join join);

    /**
     * 左关联一个属性.
     *
     * @param proerty
     * @return
     */
    //    Criteria leftJoin(String proerty, boolean fetch);

    /**
     * 查询结果去重操作.
     *
     * @return Criteria.
     */
    Criteria distinct();

    /**
     * <p>
     * 获取分页信息.
     * </p>
     * <p/>
     *
     * <pre>
     *  用以获取分页信息.
     * </pre>
     *
     * @return 分页信息.
     */
    Pageable getPageable();

    /**
     * <p>
     * 设置分页信息.
     * </p>
     * <p/>
     *
     * <pre>
     * Pageable page = new PageRequest(true, 10);
     * criteria.setPageable(page);
     * </pre>
     *
     * @param page
     *            设置分页信息.
     */
    void setPageable(Pageable page);

    /**
     * 是否允许自动分页.
     *
     * @return 是否允许自动分页.
     */
    boolean isAutoPageable();

    /**
     * <p>
     * 添加排序.
     * </p>
     * <p/>
     *
     * <pre>
     *   criteria.addOrder(new Order(Direction.DESC, "name"), new Order(Direction.ASC, "id"));
     *   或
     *   criteria.addOrder(new Order(Direction.DESC, "name")).addOrder(new Order(Direction.ASC,"id"));
     * </pre>
     *
     * @param order
     *            排序对象 .
     * @return Criteria.
     */
    Criteria addOrder(Order... order);

    /**
     * 添加排 序.
     * <p/>
     *
     * <pre>
     * criteria.addOrder(Order.desc(&quot;name&quot;));
     * </pre>
     *
     * @param order
     *            排序对象 .
     * @return Criteria.
     */
    Criteria addOrder(Order order);

    /**
     * <p>
     * 添加查询条件.
     * </p>
     * <p/>
     *
     * <pre>
     *   criteria.add(Restrictions.or(Restrictions.eq("name", "column1"), Restrictions.eq("name", "column2")));
     *   或
     *   criteria.add(Restrictions.eq("name", "column1")).add(Restrictions.eq("name", "column2"));
     * </pre>
     *
     * @param criterion
     *            查询条件.
     * @return Criteria.
     */
    Criteria add(Criterion criterion);

    /**
     * <p>
     * 添加查询条件.
     * </p>
     * <p/>
     *
     * <pre>
     *   criteria.add(Restrictions.or(Restrictions.eq("name", "column1"), Restrictions.eq("name", "column2")));
     *   或
     *   criteria.add(Restrictions.eq("name", "column1")).add(Restrictions.eq("name", "column2"));
     * </pre>
     *
     * @param criterions
     *            查询条件.
     * @return Criteria.
     */
    Criteria add(Criterion... criterions);

    /**
     * <p>
     * 添加投影.
     * </p>
     * <p/>
     *
     * <pre>
     * criteria.addProjection(Projections.sum(field));
     * </pre>
     *
     * @param projection
     *            投影对象.
     * @return Criteria.
     */
    Criteria addProjection(Projection projection);

    /**
     * <p>
     * 获取参数集合.
     * </p>
     * <p/>
     *
     * <pre>
     *
     * </pre>
     *
     * @return Map.
     */
    Map<String, Object> getParameter();

    /**
     * 返回sql.
     *
     * @return String.
     */
    String toSqlString();

    /**
     * 设置是否排序.
     *
     * @param buildOrder
     *            是否排序.
     */
    void buildOrder(boolean buildOrder);

    //    String toSqlStringWithParamters();

    /**
     * 返回实体class.
     * @return Class.
     */
    Class<?> getEntityClass();
}
