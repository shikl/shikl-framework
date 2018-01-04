package cn.shikl.data.criteria;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * 分页返回结果实现类.
 */
public class PageImpl<T> implements Page<T> {

    /**
     * serialVersionUID.
     */
    private static final long serialVersionUID = 867755909294344406L;

    /**
     * 当前页内容.
     */
    private final List<T> content;
    /**
     * 总记录数.
     */
    private final int totalElement;

    /**
     * 总页数.
     */
    private int totalPages;

    /**
     * 每页记录数.
     */
    private int pageSize;

    /**
     * 当前页号.
     */
    private int pageNubmer;

    /**
     * Constructor of {@code PageImpl}.
     *
     * @param content      the content of this page
     * @param pageable     the paging information
     * @param totalElement the total amount of items available
     */
    public PageImpl(List<T> content, Pageable pageable, int totalElement) {
        if (null == content) {
            throw new IllegalArgumentException("Content must not be null!");
        }
        this.content =content;
        this.totalElement = totalElement;
        cal(content, pageable, totalElement);
    }

    private void cal(List<T> content, Pageable pageable, int totalElement) {
        this.pageSize = pageable.getPageSize();
        this.pageNubmer =pageable.getPageNumber();
        if (0 == (totalElement % getSize())) {
            this.totalPages = totalElement / getSize();
        } else {
            this.totalPages = (totalElement / getSize()) + 1;
        }
    }

    public PageImpl() {
        this.content = null;
        this.totalElement = 0;
    }

    /**
     * the created {@link Page} being identical
     * to the entire {@link List}.
     *
     * @param content list.
     */
    public PageImpl(List<T> content) {
        if (null == content) {
            throw new IllegalArgumentException("Content must not be null!");
        }
        this.content =content;
        this.totalElement = content.size();
        cal(content, new PageRequest(1), content.size());
    }

    @Override
    public int getNumber() {
        return pageNubmer;
    }

    @Override
    public int getSize() {
        return pageSize;
    }

    @Override
    public int getTotalPages() {
        return totalPages;
    }

    @Override
    public int getTotalElements() {
        return totalElement;
    }

    @Override
    public boolean hasPreviousPage() {

        return getNumber() > 1;
    }

    @Override
    public boolean isFirstPage() {

        return !hasPreviousPage();
    }

    @Override
    public boolean hasNextPage() {

        return getTotalPages() > (getNumber() + 1);
    }

    @Override
    public boolean isLastPage() {

        return !hasNextPage();
    }

    @Override
    public Iterator<T> iterator() {

        return content.iterator();
    }

    @Override
    public List<T> getContent() {
        return Collections.unmodifiableList(content);
    }

    @Override
    public boolean hasContent() {
        return !content.isEmpty();
    }

    @Override
    public String toString() {

        String contentType = "UNKNOWN";

        if (content.size() > 0) {
            contentType = content.get(0).getClass().getName();
        }

        return String.format("PageComponent %s of %d containing %s instances", getNumber(), getTotalPages(),
                contentType);
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj) {
            return true;
        }

        if (!(obj instanceof PageImpl<?>)) {
            return false;
        }

        PageImpl<?> that = (PageImpl<?>) obj;

        boolean totalEqual = this.totalElement == that.totalElement;
        boolean contentEqual = this.content.equals(that.content);

        return totalEqual && contentEqual;
    }

    @Override
    public int hashCode() {

        int result = 17;

        result = 31 * result + (int) (totalElement ^ totalElement >>> 32);
        result = 31 * result + content.hashCode();

        return result;
    }
}
