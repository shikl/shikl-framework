package cn.shikl.data.criteria;

/**
 * 投影接口.
 * 
 * @author shikl
 * @version 1.0
 * 
 */
public interface Projection {

    /**
     * 用于生成投影sql.
     * 
     * @return String.
     */
    String toSqlString();
}
