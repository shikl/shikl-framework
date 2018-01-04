package cn.shikl.model;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 翻页包装.
 *
 * @author libo .
 * @version 1.0
 */

public class PageVO extends BaseVO{

    /**
     * 当前页.
     */
    @JSONField(serialize = false)
    private int currentPage;
    /**
     * 页码
     */
    private int pageIndex;
    /**
     * 每页显示记录数
     */
    @JSONField(serialize = false)
    private int pageSize;

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public int getPageSize() {
        return pageSize;
    }

    /**
     * 返回currentPage.
     *
     * @return the currentPage
     */
    public int getCurrentPage() {
        return currentPage;
    }

    /**
     * 设置currentPage.
     *
     * @param currentPage the currentPage to set
     */
    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
