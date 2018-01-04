package cn.shikl.data.jpa.criteria.projection;


import cn.shikl.data.criteria.Projection;

/**
 * count投影.
 *
 * @author libo .
 * @version 1.0
 */
public class CountProjection implements Projection {

    /**
     * 设置是否去重.
     */
    private boolean distinct;

    /**
     * 设置count属性.
     */
    private final String property;

    /**
     * 默认的构造方法.
     *
     * @param prop 属性.
     */
    protected CountProjection(String prop) {
        this.property = prop;
    }

    /* (non-Javadoc)
     * @see cn.com.rexen.rplus.core.criteria.Projection#toSqlString()
     */
    @Override
    public String toSqlString() {

        String sql = "count(";
        if (distinct) {
            sql += "distinct ";
        }
        return sql += property + ")";
    }

    /**
     * 设置去重.
     *
     * @return CountProjection.
     */
    public CountProjection setDistinct() {
        distinct = true;
        return this;
    }
}
