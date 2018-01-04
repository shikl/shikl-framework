package cn.shikl.data.criteria;

/**
 * 单个的查询条件接口.
 *
 * @author shikl
 * @version 1.0.0
 */
public interface Criterion {

    /**
     * 生成单个查询条件sql.
     * @param criteria           查询条件实现.
     * @param criteriaTranslator 类型处理实例.
     * @return sql语句.
     */
    String toSqlString(Criteria criteria, CriteriaTranslator criteriaTranslator);

    /**
     * 查询条件值.
     *
     * @return 条件值.
     */
    Object getValue();
}
