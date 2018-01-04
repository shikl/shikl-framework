package cn.shikl.data.jpa.criteria.expression;

import cn.shikl.core.exeception.NotSupportedException;
import cn.shikl.data.criteria.Criteria;
import cn.shikl.data.criteria.CriteriaTranslator;
import cn.shikl.data.criteria.Criterion;
import cn.shikl.utils.StringUtils;

/**
 * 生成非空查询条件.
 *
 * @author libo <br/>
 * @version 1.0.0
 */
public class NotNullExpression implements Criterion {

    /**
     * 查询条件属性名称.
     */
    private final String propertyName;

    /**
     * 非空查询条件实例.
     *
     * @param propertyName 查询条件名称.
     */
    public NotNullExpression(String propertyName) {
        this.propertyName = propertyName;
    }

    @Override
    public String toSqlString(Criteria criteria, CriteriaTranslator criteriaTranslator) {
        return StringUtils.connect(propertyName, " is not null");
    }

    @Override
    public Object getValue() {
        throw new NotSupportedException();
    }

}
