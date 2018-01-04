package cn.shikl.data.jpa.criteria;

import cn.shikl.data.criteria.*;
import cn.shikl.data.jpa.criteria.expression.LogicalExpression;
import cn.shikl.data.jpa.criteria.expression.Operate;
import cn.shikl.utils.Constant;
import cn.shikl.utils.StringUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.Entity;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Criteria jpa2 实现类.
 * 将查询条件转换成JPQL查询语句.
 */
public class CriteriaJPA2Impl implements Criteria {

    /**
     * 日志.
     */
    private final Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 类型转换器.
     */
    private CriteriaTranslator criteriaTranslator = new CriteriaTranslatorJPA2Impl();
    /**
     * 查询去重.
     */
    private String distinct = "";

    /**
     * 查询对象名称.
     */
    private String entityName;

    /**
     * 查询别名.
     */
    private String alias;

    /**
     * 分页实例.
     */
    private Pageable pageInstance;

    /**
     * 生成SQL时，是否生成排序部分的语句.默认为TRUE生成，FALSE 时不生成.
     */
    private boolean buildOrder = true;
    /**
     * 在没有指定分页时是否自动化分页.默认为true，自动分页.
     */
    private boolean buildPage = true;

    /**
     * 排序条件.
     */
    private List<Order> orders = new ArrayList<Order>();

    /**
     * 查询条件.
     */
    private List<Criterion> criterions = new ArrayList<Criterion>();

    /**
     * 查询条件参数值.
     */
    private Map<String, Object> parameters = new HashMap<String, Object>();

    /**
     * 查询列表选择.
     */
    private List<Projection> projections = new ArrayList<Projection>();

    /**
     * 关联查询对象.
     */
    private List<Join> joins = new ArrayList<Join>();

    private Class entityClass;

    @Override
    public Class getEntityClass() {
        return entityClass;
    }

    /**
     * 构造方法.
     *
     * @param entityName 实体类名称.
     * @param alias      别名.
     */
    public CriteriaJPA2Impl(String entityName, String alias) {
        this.entityName = entityName;
        this.alias = alias;
    }

    /**
     * 构造方法.
     *
     * @param entityName 实体类名称.
     */
    public CriteriaJPA2Impl(String entityName) {
        this.entityName = entityName;
    }

    /**
     * 构造方法.
     *
     * @param clazz 实体类class.
     */
    @SuppressWarnings("rawtypes")
	public CriteriaJPA2Impl(Class clazz) {
        this.entityClass = clazz;
        this.entityName = getEntityName(clazz);
    }

    @Override
    public String getEntityName() {
        return entityName;
    }

    /**
     * 通过实体class获取名称.
     *
     * @param type 实体类class.
     * @return String.
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
	private String getEntityName(Class type) {
        Entity entity = (Entity) type.getAnnotation(Entity.class);
        if (entity == null || entity.name() == null || entity.name().length() == 0) {
            entityName = type.getSimpleName();
        } else {
            entityName = type.getName();
        }
        return entityName;
    }

    @Override
    public String getAlias() {
        if (StringUtils.isEmpty(alias)) {
            alias = entityName.substring(entityName.lastIndexOf(Constant.PERIOD) + 1).toLowerCase();
        }
        return alias;
    }

    @Override
    public Criteria join(Join join) {
        if (join == null) {
            throw new IllegalArgumentException("联合查询对象为空");
        }
        joins.add(join);
        return this;
    }

    @Override
    public Criteria join(String property) {
        if (StringUtils.isEmpty(property)) {
            throw new IllegalArgumentException("联合查询关联属性为空.");
        }
        Join join = new Join(property);
        joins.add(join);
        return this;
    }

    @Override
    public Criteria distinct() {
        this.distinct = "distinct ";
        return this;
    }

    @Override
    public Pageable getPageable() {
        return pageInstance;
    }

    @Override
    public void setPageable(Pageable page) {
        this.pageInstance = page;
    }

    @Override
    public boolean isAutoPageable() {
        return buildPage;
    }

    @Override
    public Criteria addOrder(Order... order) {
        if (order == null) {
            throw new IllegalArgumentException("排序条件为空。");
        }
        for (Order o : order) {
            this.orders.add(o);
            logger.debug("add order {}", o);
        }
        return this;
    }

    @Override
    public Criteria addOrder(Order order) {
        if (order == null) {
            throw new IllegalArgumentException("排序条件为空。");
        }
        orders.add(order);
        logger.debug("add order {}", order);
        return this;
    }

    @Override
    public Criteria add(Criterion criterion) {
        if (criterion == null) {
            throw new IllegalArgumentException("criterion is null");
        }
        criterions.add(criterion);
        logger.debug("add criterion:{}", criterion);
        return this;
    }

    @Override
    public Criteria add(Criterion... criterions) {
        if (criterions == null) {
            throw new IllegalArgumentException("criterions is null");
        }
        for (Criterion c : criterions) {
            this.criterions.add(c);
            logger.debug("add criterion:{}", c);
        }
        return this;
    }

    @Override
    public Criteria addProjection(Projection projection) {
        this.projections.add(projection);
        return this;
    }

    @Override
    public Map<String, Object> getParameter() {
        return parameters;
    }

    @Override
    public String toSqlString() {
        parameters = new HashMap<String, Object>();
        StringBuilder buffer = new StringBuilder();
        buffer.append(buildFromSql());
        buffer.append(buildJoinSql());
        buffer.append(buildWhereSql());
        if (buildOrder) {
            buffer.append(buildOrderSql());
        }
        String sql = buffer.toString();
        logger.debug("build sql:{}", sql);
        logger.debug("sql paramerter:{}", getParameter());
        return sql;
    }

    @Override
    public void buildOrder(boolean buildOrder) {
        this.buildOrder = buildOrder;
    }

    /**
     * 生成查询对象部分sql.
     *
     * @return 查询对象sql.
     */
    private String buildFromSql() {
        StringBuilder buffer = new StringBuilder();
        buffer.append("select ").append(distinct);
        if (projections == null || projections.size() == 0) {
            buffer.append(getAlias());
        } else {
            buffer.append(buildProjectionSql());
        }
        buffer.append(" from ").append(entityName).append(Constant.SPACE).append(getAlias()).append(Constant.SPACE);
        return buffer.toString();
    }

    /**
     * 生成join 关联查询语句.
     *
     * @return 关联语句.
     */
    private String buildJoinSql() {
        if (joins == null || joins.size() == 0) {
            return "";
        }
        StringBuilder builder = new StringBuilder();
        for (Join join : joins) {
            builder.append(join.toSqlString(this));
        }
        return builder.toString();
    }

    private String buildProjectionSql() {
        if (projections == null || projections.size() == 0) {
            return "";
        }
        int len = projections.size();
        StringBuilder buffer = new StringBuilder();
        for (int i = 0; i < len; i++) {
            Projection projection = projections.get(i);
            buffer.append(projection.toSqlString());
            if (i < len - 1) {
                buffer.append(Constant.COMMA);
            }
        }

        return buffer.toString();
    }

    /**
     * 生成查询条件sql.
     *
     * @return 查询条件sql.
     */
    private String buildWhereSql() {
        if (criterions == null || criterions.size() == 0) {
            return "";
        }
        StringBuilder buffer = new StringBuilder();
        buffer.append("where ");
        int len = criterions.size();
        for (int i = 0; i < len; i++) {
            Criterion criterion = criterions.get(i);
            if (criterion instanceof LogicalExpression) {
                LogicalExpression le = (LogicalExpression) criterion;
                buffer.append(le.toSqlString(this, criteriaTranslator));
//                addParameter(le.getlExpression());
//                addParameter(le.getrExpression());
            } else {
                buffer.append(criterion.toSqlString(this, criteriaTranslator));
                // addParameter(criterion);
            }
            if (i < len - 1) {
                buffer.append(Operate.AND.value());
            }
        }
        return buffer.toString();
    }

    /**
     * 生成排序sql.
     *
     * @return 排序sql.
     */
    private String buildOrderSql() {
        logger.debug("orders :{}", orders);
        if (orders == null || orders.size() == 0) {
            return "";
        }
        StringBuilder buffer = new StringBuilder();
        buffer.append(" order by ");
        for (Order order : orders) {
            buffer.append(order.toSqlString()).append(Constant.COMMA);
        }
        String orderSql = buffer.substring(0, buffer.length() - 1);
        return orderSql;

    }
}
