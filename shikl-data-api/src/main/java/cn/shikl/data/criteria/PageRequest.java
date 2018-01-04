package cn.shikl.data.criteria;

import cn.shikl.data.execption.ValidatorException;
import cn.shikl.utils.StringUtils;

import java.io.Serializable;

/**
 * 分页实现. {@code Pageable}.
 *
 * @author shikl.
 * @version 1.0
 */
@SuppressWarnings("serial")
public class PageRequest implements Pageable, Serializable {
    /**
     * 每页默认的显示记录数.
     */
    public static final int DEFAULT_PAGE_SIZE = 10;
    //    /**
    //     * 日志logger.
    //     */
    //    private final Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 1.
     */
    private final int ONE = 1;

    /**
     * 0.
     */
    private final int ZERO = 0;

    //    /**
    //     * 第一页变量.默认为1.
    //     */
    //    private int firstPage = ONE;
    //
    //    /**
    //     * 前一页变量.默认为1.
    //     */
    //    private int previousPage = ONE;
    //
    //    /**
    //     * 后一页变量.默认为1.
    //     */
    //    private int nextPage = ONE;
    //
    //    /**
    //     * 最后页变量.默认为1.
    //     */
    //    private int lastPage = ONE;

    /**
     * 每页记录数.
     */
    private int pageSize = DEFAULT_PAGE_SIZE;

    /**
     * 当前页数.
     */
    private int pageNubmer = ZERO;

    /**
     * 开始记录数索引.
     */
    private int startRowIndex = ZERO;

    /**
     * 请求一页每页显示记录数为默认为10条的分页.
     *
     * @param pageNubmer 当前页号.
     */
    public PageRequest(final int pageNubmer) {
        this.pageNubmer = pageNubmer;
        calculate();
    }

    /**
     * 通过指定页号和每页显示记录数分页.
     *
     * @param pageNubmer 页号.
     * @param pageSize   每页显示记录数
     */
    public PageRequest(final int pageNubmer, final int pageSize) {
        this.pageNubmer = pageNubmer;
        this.pageSize = pageSize;
        calculate();
    }

    /**
     * 计算相关变量.
     */
    private void calculate() {
        //        if (totalRecords == ZERO) {
        //            logger.warn("total records is zero. can`t build page.");
        //            return;
        //        }
        if (pageSize < ZERO) {
            throw new ValidatorException("pageSize 不能小于0.");
        }
        if (pageNubmer <= ZERO) {
            pageNubmer = ONE;
        }
        //        previousPage = pageNubmer - 1;
        //        if (ZERO >= previousPage) {
        //            previousPage = ONE;
        //        }
        //        nextPage = pageNubmer + 1;
        //        if (nextPage > totalPages) {
        //            nextPage = totalPages;
        //        }
        //        lastPage = totalPages;
        startRowIndex = (pageNubmer - 1) * pageSize;
    }

    /*
             * (non-Javadoc)
             *
             * @see java.lang.Object#equals(java.lang.Object)
             */
    @Override
    public boolean equals(final Object obj) {

        if (this == obj) {
            return true;
        }

        if (!(obj instanceof PageRequest)) {
            return false;
        }

        PageRequest that = (PageRequest) obj;

        boolean pageEqual = this.pageNubmer == that.pageNubmer;
        boolean sizeEqual = this.pageSize == that.pageSize;

        return pageEqual && sizeEqual;
    }

    /**
     * 返回首页.
     *
     * @return int
     */
    //    public int getFirstPage() {
    //        return firstPage;
    //    }

    /**
     * 返回最后一页.
     *
     * @return int.
     */
    //    public int getLastPage() {
    //        return lastPage;
    //    }

    /**
     * 返回下一页.
     *
     * @return 下一页号.
     */
    //    public int getNextPage() {
    //        return nextPage;
    //    }
    @Override
    public int getPageNumber() {
        return pageNubmer;
    }

    @Override
    public int getPageSize() {
        return pageSize;
    }

    /**
     * 上一页号.
     *
     * @return 上一页号.
     */
    //    public int getPreviousPage() {
    //        return previousPage;
    //    }

    /**
     * 当前页开始记录索引.
     *
     * @return 根据页号计算出的当前页显示的记录索引.
     */
    public int getStartRowIndex() {
        return startRowIndex;
    }

    /*
             * (non-Javadoc)
             *
             * @see java.lang.Object#hashCode()
             */
    @Override
    public int hashCode() {

        int result = 17;

        result = 31 * result + pageNubmer;
        result = 31 * result + pageSize;
        return result;
    }

    @Override
    public String toString() {
        return StringUtils.connect("pageNumber =", pageNubmer, "pageSize  =", pageSize);
    }
}
