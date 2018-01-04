package cn.shikl.data.jpa.criteria.expression;


import cn.shikl.data.criteria.Criteria;
import cn.shikl.data.criteria.CriteriaTranslator;
import cn.shikl.data.criteria.Criterion;
import cn.shikl.data.execption.CriteriaException;
import cn.shikl.utils.Constant;
import cn.shikl.utils.StringUtils;

/**
 * 区间查询条件.
 * 
 * @author libo <br/>
 * @version 1.0.0
 */
public class BetweenExpression extends BaseExpression implements Criterion {

    /**
     * 查询属性名称.
     */
    private final String propertyName;

    /**
     * 区间低值.
     */
    private final Object lo;

    /**
     * 区间高值.
     */
    private final Object hi;

    /**
     * 生成一个区间查询条件实例.
     * 
     * @param propertyName
     *            查询属性名称.
     * @param lo
     *            区间低值.
     * @param hi
     *            区间高值.
     */
    public BetweenExpression(String propertyName, Object lo, Object hi) {
        if (StringUtils.isEmpty(propertyName)) {
            throw new CriteriaException();
        }
        this.propertyName = propertyName;
        this.lo = lo;
        this.hi = hi;
    }

    @Override
    public String toSqlString(Criteria criteria, CriteriaTranslator criteriaTranslator) {
        String parameterName = resolveParameterName(criteria, propertyName);
        String lo_parameterName = StringUtils.connect(parameterName, "_lo");
        String hi_parameterName = StringUtils.connect(parameterName, "_hi");
        criteria.getParameter().put(lo_parameterName, lo);
        criteria.getParameter().put(hi_parameterName, hi);
        return StringUtils.connect(criteria.getAlias(), Constant.PERIOD, propertyName, " between :", lo_parameterName,
                " and :", hi_parameterName);
    }

    @Override
    public Object getValue() {
        Object[] vs = { lo, hi };
        return vs;
    }
}
