package cn.shikl.model;

import java.util.List;

/**
 * DataTable组件实体类
 *
 * @param <V>
 * @author libo .
 */
public class DataTable<V> extends BaseVO {

    /**
     * 当前页.
     */
    private int pageNumber;
    /**
     * 记录.
     */
    private List<V> records;
    /**
     * 总页数.
     */
    private int totalPage;
    /**
     * 数据总数.
     */
    private int totalCount;
    /**
     * 每页显示数据.
     */
    private int pageSize;


    /**
     * 获得每页显示数据.
     *
     * @return
     */
    public int getPageSize() {
        return pageSize;
    }

    /**
     * 设置每页显示数据.
     *
     * @param pageSize
     */
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    /**
     * 获得当前页.
     *
     * @return
     */
    public int getPageNumber() {
        return pageNumber;
    }

    /**
     * 设置当前页.
     *
     * @param pageNumber
     */
    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    /**
     * 获得记录.
     *
     * @return
     */
    public List<V> getRecords() {
        return records;
    }

    /**
     * 设置记录.
     *
     * @param records
     */
    public void setRecords(List<V> records) {
        this.records = records;
    }

    /**
     * 获得总页数.
     *
     * @return
     */
    public int getTotalPage() {
        return totalPage;
    }

    /**
     * 设置总页数.
     *
     * @param totalPage
     */
    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    /**
     * 获得数据总数.
     *
     * @return
     */
    public int getTotalCount() {
        return totalCount;
    }

    /**
     * 设置数据总数.
     *
     * @param totalCount
     */
    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

}
