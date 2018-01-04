package cn.shikl.data.jpa.criteria;

import cn.shikl.data.criteria.CriteriaTranslator;
import cn.shikl.data.criteria.Criterion;
import cn.shikl.data.jpa.criteria.expression.BetweenExpression;

/**
 * JPA 参数类型处理类.
 * 
 * @author shikl <br/>
 * @version 1.0
 */
public class CriteriaTranslatorJPA2Impl implements CriteriaTranslator {
    @Override
    public Object getValueType(Criterion criterion) {
        //TODO 需要根据属性的类型来转换值 的类型.
        Object value = criterion.getValue();
        if (criterion instanceof BetweenExpression) {
            Object[] vs = (Object[]) value;
            Object[] rvs = { processValue(vs[0]), processValue(vs[1]) };
            return rvs;
        }
        return processValue(value);
    }

    /**
     * 类型判断.
     * 
     * @param value
     *            值.
     * @return Object.
     */
    private Object processValue(Object value) {
        if (value instanceof String) {
            return processStringValue(value);
        }
        return value;
    }

    /**
     * 类型处理.
     * 
     * @param value
     *            值.
     * @return string.
     */
    private String processStringValue(Object value) {
        return String.valueOf(value);
    }
}
