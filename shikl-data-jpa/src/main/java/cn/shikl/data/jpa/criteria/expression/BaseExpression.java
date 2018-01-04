package cn.shikl.data.jpa.criteria.expression;

import cn.shikl.data.criteria.Criteria;
import cn.shikl.utils.Constant;
import cn.shikl.utils.StringUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * 查询条件基础类.实现所有查询条件的公共方法.
 *
 * @author libo <br/>
 * @version 1.0
 */
public abstract class BaseExpression {

    /**
     * 日志.
     */
    private final Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 处理生成的语句中的参数名称.将参数中的"."替换成"_",并将同名参数分别处理.
     *
     * @param criteria     crireria 实例.
     * @param propertyName 查询属性名称.
     * @return 处理后的参数名.
     */
    protected String resolveParameterName(Criteria criteria, String propertyName) {
        String proName = propertyName.replace(Constant.PERIOD, Constant.UNDERLINE);
        String parameterName = containsKey(criteria.getParameter(), proName, 0);
        logger.debug("resolve parameterName:{}", parameterName);
        return parameterName;
    }

    /**
     * 处理同名参数的标号.
     *
     * @param paramerter   所有参数MAP.
     * @param propertyName 当前参数名.
     * @param index        出现次数.
     * @return 参数名.
     */
    private String containsKey(Map<String, Object> paramerter, String propertyName, int index) {
        int _index = index;
        String parameterName = StringUtils.connect(propertyName, Constant.UNDERLINE, _index++);
        while (paramerter.containsKey(parameterName)) {
            return containsKey(paramerter, propertyName, _index++);
        }
        return parameterName;
    }
}
