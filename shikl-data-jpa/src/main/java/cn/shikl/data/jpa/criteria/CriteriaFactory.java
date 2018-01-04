package cn.shikl.data.jpa.criteria;

import org.springframework.stereotype.Component;

import cn.shikl.data.criteria.Criteria;

/**
 * 查询条件生成工厂类.
 * 
 * @author shikl
 * @version 2.0
 */

@Component
public final class CriteriaFactory {

    /**
     * 异常说明.
     */
    private static final String EXCEPTION_MSG = "类名为空，不能生成 DetachedCriteria 查询条件.";

    /**
     * 生成对象查询.
     * 
     * @param clazz
     *            实体对象class.
     * @return 返回条件查询对象.
     */
    public Criteria getCriteria(Class<?> clazz) {
        if (clazz == null) {
            throw new IllegalArgumentException(EXCEPTION_MSG);
        }
        Criteria ci = (Criteria) new CriteriaJPA2Impl(clazz);
        return ci;
    }
}
