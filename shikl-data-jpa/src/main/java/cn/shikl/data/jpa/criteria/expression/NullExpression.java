package cn.shikl.data.jpa.criteria.expression;


import cn.shikl.core.exeception.NotSupportedException;
import cn.shikl.data.criteria.Criteria;
import cn.shikl.data.criteria.CriteriaTranslator;
import cn.shikl.data.criteria.Criterion;
import cn.shikl.utils.StringUtils;

/**
 * 空值条件.
 * 
 * @author libo <br/>
 * @version 1.0.0
 */
public class NullExpression implements Criterion {

    /**
     * 查询条件属性名称.
     */
    private final String propertyName;

    /**
     * 创建空值查询条件.
     * 
     * @param propertyName
     *            属性名称.
     */
    public NullExpression(String propertyName) {

        this.propertyName = propertyName;
    }

    /**
     * @inheritDoc
     */
    /* (non-Javadoc)
     * @see cn.com.rexen.rplus.core.criteria.Criterion#toSqlString
     * (cn.com.rexen.rplus.core.criteria.Criteria, cn.com.rexen.rplus.core.criteria.CriteriaTranslator)
     */
    @Override
    public String toSqlString(Criteria criteria, CriteriaTranslator criteriaTranslator) {
        return StringUtils.connect(propertyName, " is null");
    }

    @Override
    public Object getValue() {
        throw new NotSupportedException();
    }

}
