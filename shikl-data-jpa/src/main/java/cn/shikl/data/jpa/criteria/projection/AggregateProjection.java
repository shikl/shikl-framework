package cn.shikl.data.jpa.criteria.projection;


import cn.shikl.data.criteria.Projection;

/**
 * 投影拼写.
 *
 * @author libo .
 * @version 1.0
 */
public class AggregateProjection implements Projection {

    /**
     * 投影属性.
     */
    private final String property;

    /**
     * 投影函数字符串.
     */
    private final String aggregate;

    /**
     * 构造方法.
     *
     * @param aggregate 投影函数字符串.
     * @param property  投影属性.
     */
    protected AggregateProjection(String aggregate, String property) {
        this.aggregate = aggregate;
        this.property = property;
    }

    /* (non-Javadoc)
     * @see cn.com.rexen.rplus.core.criteria.Projection#toSqlString()
     */
    @Override
    public String toSqlString() {
        return aggregate + "(" + property + ")";
    }

}
