package cn.shikl.data.criteria;



import java.util.Locale;

import cn.shikl.utils.StringUtils;


/**
 * 查询排序条件.
 * 
 * @author shikl
 * @version 2.0
 */
public class Order {
    /**
     * 排序方式枚举类.
     * 
     * @author shikl .
     * 
     */
    public static enum Direction {
        /**
         * 升序.
         */
        ASC,
        /**
         * 降序.
         */
        DESC;

        /**
         * 返回排序 {@link Direction} 列表值 {@link String} .
         * 
         * @param value
         *            值
         * @return 排序枚举.
         */
        public static Direction fromString(String value) {

            try {
                return Direction.valueOf(value.toUpperCase(Locale.US));
            } catch (RuntimeException e) {
                throw new IllegalArgumentException(String.format("排序值 '%s' 不正确，必须是 'desc' 或者 'asc' (不区分大小写).", value),
                        e);
            }
        }
    }

    /**
     * 排序方式类.
     */
    private final Direction direction;
    /**
     * 属性.
     */
    private final String property;

    /**
     * 默认排序构造方法(默认升序).
     * 
     * <pre>
     * Order order = new Order(&quot;property1&quot;);
     * </pre>
     * 
     * @param property
     *            属性.
     */
    public Order(String property) {
        this(Direction.ASC, property);
    }

    /**
     * 排序构造.
     * 
     * <pre>
     * Order order = new Order(Direction.ASC, &quot;property1&quot;);
     * </pre>
     * 
     * @param direction
     *            排序方式.
     * @param property
     *            排序属性.
     */
    private Order(Direction direction, String property) {
        if (!StringUtils.hasText(property)) {
            throw new IllegalArgumentException("排序属性不能为空!");
        }

        if (direction == null) {
            this.direction = Direction.ASC;
        } else {
            this.direction = direction;
        }
        this.property = property;
    }

    /**
     * 设置降序排序.
     * 
     * @param property
     *            排序属性.
     * @return Order.
     */
    public static Order desc(String property) {
        return new Order(Direction.DESC, property);
    }

    /**
     * * 设置 升序排序.
     * 
     * @param property
     *            排序属性.
     * @return Order.
     */
    public static Order asc(String property) {
        return new Order(Direction.ASC, property);
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {

        if (this == obj) {
            return true;
        }

        if (!(obj instanceof Order)) {
            return false;
        }

        Order that = (Order) obj;

        return this.direction.equals(that.direction) && this.property.equals(that.property);
    }

    /**
     * 获取排序规则对象.
     * 
     * @return the direction.
     */
    public Direction getDirection() {
        return direction;
    }

    /**
     * 获取属性.
     * 
     * @return the property
     */
    public String getProperty() {
        return property;
    }

    @Override
    public int hashCode() {

        int result = 17;

        result = 31 * result + direction.hashCode();
        result = 31 * result + property.hashCode();

        return result;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return String.format("%s: %s", property, direction);
    }

    /**
     * 排序生成sql片段.
     * 
     * @return String.
     */
    public String toSqlString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append(this.property).append(" ").append(this.direction);
        return buffer.toString();
    }

}
