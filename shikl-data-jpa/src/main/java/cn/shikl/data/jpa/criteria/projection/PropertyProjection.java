package cn.shikl.data.jpa.criteria.projection;


import cn.shikl.data.criteria.Projection;

/**
 * 属性投影.
 *
 * @author libo .
 * @version 1.0
 */
public class PropertyProjection implements Projection {

    /**
     * 属性.
     */
    private String property;

    /**
     * 默认的构造 方法.
     *
     * @param property 属性.
     */
    protected PropertyProjection(String property) {
        this.property = property;
    }

    /* (non-Javadoc)
     * @see cn.com.rexen.rplus.core.criteria.Projection#toSqlString()
     */
    @Override
    public String toSqlString() {
        return property;
    }

}
