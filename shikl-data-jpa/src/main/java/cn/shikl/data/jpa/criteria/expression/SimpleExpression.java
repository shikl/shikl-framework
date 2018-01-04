package cn.shikl.data.jpa.criteria.expression;

import cn.shikl.data.criteria.Criteria;
import cn.shikl.data.criteria.CriteriaTranslator;
import cn.shikl.data.criteria.Criterion;
import cn.shikl.utils.Constant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 基本查询条件类.
 *
 * @author libo <br/>
 *         date:2014-04-16
 * @version 1.0.0
 */
public class SimpleExpression extends BaseExpression implements Criterion {

    /**
     * 查询条件属性名.
     */
    private final String propertyName;

    /**
     * 查询条件值.
     */
    private final Object value;

    /**
     * 查询操作.
     */
    private final Operate op;

    /**
     * 日志.
     */
    private final Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 生成查询条件实例.
     *
     * @param propertyName
     *            查询条件属性名.
     * @param op
     *            查询操作符.
     * @param value
     *            查询条件值.
     */
    public SimpleExpression(String propertyName, Operate op, Object value) {
        this.propertyName = propertyName;
        this.value = value;
        this.op = op;
    }

    @Override
    public String toSqlString(Criteria criteria, CriteriaTranslator criteriaTranslator) {
        String parameterName = resolveParameterName(criteria, propertyName);
        criteria.getParameter().put(parameterName, value);
        StringBuilder builder = new StringBuilder();
        if (propertyName.indexOf(Constant.PERIOD) == -1) {
            builder.append(criteria.getAlias()).append(Constant.PERIOD);
        }
        builder.append(propertyName).append(Constant.SPACE).append(op.value()).append(Constant.SPACE)
                .append(Constant.COLON).append(parameterName);
        logger.info(builder.toString());
        return builder.toString();
    }

    @Override
    public Object getValue() {
        return this.value;
    }

}
