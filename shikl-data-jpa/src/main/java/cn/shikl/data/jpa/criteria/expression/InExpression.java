package cn.shikl.data.jpa.criteria.expression;

import cn.shikl.data.criteria.Criteria;
import cn.shikl.data.criteria.CriteriaTranslator;
import cn.shikl.data.criteria.Criterion;
import cn.shikl.utils.Constant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 包含查询方式.
 *
 * @author libo .
 * @version 1.0
 */
public class InExpression extends BaseExpression implements Criterion {

    /**
     * 日志.
     */
    private final Logger logger = LoggerFactory.getLogger(getClass());
    /**
     * 查询条件属性名.
     */
    private final String propertyName;

    /**
     * 查询条件值.
     */
    private final Object value;


    /**
     * 构造方法.
     *
     * @param propertyName
     * @param value
     */
    public InExpression(String propertyName, Object[] value) {
        this.propertyName = propertyName;
        this.value = value;
    }

    @Override
    public String toSqlString(Criteria criteria, CriteriaTranslator criteriaTranslator) {
        String parameterName = resolveParameterName(criteria, propertyName);
        criteria.getParameter().put(parameterName, value);
        StringBuilder builder = new StringBuilder();
        if (propertyName.indexOf(Constant.PERIOD) == -1) {
            builder.append(criteria.getAlias()).append(Constant.PERIOD);
        }
        builder.append(propertyName).append(Constant.SPACE).append(Operate.IN).append(Constant.SPACE)
                .append(Constant.COLON).append(parameterName);
        logger.info(builder.toString());
        return builder.toString();
    }

    @Override
    public Object getValue() {
        return value;
    }
}
