package cn.shikl.data.criteria;

/**
 * 分页请求信息接口..
 *
 * @author shikl.
 * @version 1.0
 */
public interface Pageable {

    /**
     * 当前页号.
     *
     * @return 页号.
     */
    int getPageNumber();

    /**
     * 每页显示记录数.
     *
     * @return 每页显示记录数.
     */
    int getPageSize();

    /**
     * 当前页开始记录数.
     *
     * @return 当前页开始记录数.
     */
    int getStartRowIndex();

}
