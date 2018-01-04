package cn.shikl.data.criteria;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * 排序设置类.
 *
 * @author shikl
 * @version 1.0
 */
public class Sort implements Iterable<Order>,Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
     * 排序列表.
     */
    private final List<Order> orders;

    /**
     * 构造排序列表.
     *
     * @param orders 排序对象.
     */
    public Sort(Order... orders) {
        this(Arrays.asList(orders));
    }

    /**
     * 构造排序列表.
     *
     * @param orders 排序集合.
     */
    public Sort(List<Order> orders) {
        if (null == orders || orders.isEmpty()) {
            throw new IllegalArgumentException("排序条件不能为空 .");
        }
        this.orders = orders;
    }

    /**
     * 添加排序对象.
     *
     * @param order 排序对象.
     */
    public void addOrder(Order order) {
        if (null == order) {
            throw new IllegalArgumentException("排序条件不能为空.");
        }
        this.orders.add(order);
    }

    @Override
    public Iterator<Order> iterator() {
        return this.orders.iterator();
    }

    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Sort)) {
            return false;
        }

        Sort that = (Sort) obj;

        return this.orders.equals(that.orders);
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + orders.hashCode();
        return result;
    }

}
