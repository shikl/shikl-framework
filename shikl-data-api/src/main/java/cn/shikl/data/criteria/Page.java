package cn.shikl.data.criteria;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;

/**
 * 整个查询结果的一个分页实例，包含整个查询结果的信息.
 *
 * @param <T> 泛型类.
 * @author shikl.
 * @version 2.0
 */
public interface Page<T> extends Iterable<T>,Serializable {

    /**
     * Returns the number of the current page. Is always positive and less that {@code PageComponent#getTotalPages()}.
     *
     * @return the number of the current page
     */
    int getNumber();

    /**
     * Returns the size of the page.
     *
     * @return the size of the page
     */
    int getSize();

    /**
     * Returns the number of total pages.
     *
     * @return the number of toral pages
     */
    int getTotalPages();

    /**
     * Returns the total amount of elements.
     *
     * @return the total amount of elements
     */
    int getTotalElements();

    /**
     * Returns if there is a previous page.
     *
     * @return if there is a previous page
     */
    boolean hasPreviousPage();

    /**
     * Returns whether the current page is the first one.
     *
     * @return boolean.
     */
    boolean isFirstPage();

    /**
     * Returns if there is a next page.
     *
     * @return if there is a next page
     */
    boolean hasNextPage();

    /**
     * Returns whether the current page is the last one.
     *
     * @return boolean.
     */
    boolean isLastPage();


    @Override
    Iterator<T> iterator();

    /**
     * Returns the page content as {@link List}.
     *
     * @return list.
     */
    List<T> getContent();

    /**
     *
     * @return boolean.
     */
    boolean hasContent();

}