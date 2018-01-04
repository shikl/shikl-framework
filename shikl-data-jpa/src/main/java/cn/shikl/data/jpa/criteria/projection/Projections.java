/**
 * 
 */
package cn.shikl.data.jpa.criteria.projection;


import cn.shikl.data.criteria.Projection;

/**
 * 投影设置类.
 * 
 * @author zhanghai
 * 
 */
public final class Projections {
    /**
     * 私有构造函数.
     */
    private Projections() {
    }

    /**
     * 构造count(property).
     * 
     * @param property
     *            属性.
     * @return Projection .
     */
    public static Projection count(String property) {
        return new CountProjection(property);
    }

    /**
     * 构造sum(property).
     * 
     * @param property
     *            属性.
     *            .
     * @return projection
     */
    public static Projection sum(String property) {
        return new AggregateProjection("sum", property);
    }

    /**
     * 构造count(distinct(property)).
     * 
     * @param property
     *            属性
     * @return Projection.
     */
    public static Projection countDistinct(String property) {
        return new CountProjection(property).setDistinct();
    }

    /**
     * 构造max(property).
     * 
     * @param property
     *            属性
     * @return Projection.
     */
    public static Projection max(String property) {
        return new AggregateProjection("max", property);
    }

    /**
     * 构造min(property).
     * 
     * @param property
     *            属性
     * @return Projection.
     */
    public static Projection min(String property) {
        return new AggregateProjection("min", property);
    }

    /**
     * 构造avg(property).
     * 
     * @param property
     *            属性
     * @return Projection.
     */
    public static Projection avg(String property) {
        return new AggregateProjection("avg", property);
    }

    /**
     * 构造property.
     * 
     * @param property
     *            属性
     * @return Projection.
     */
    public static Projection property(String property) {
        return new PropertyProjection(property);
    }
}
