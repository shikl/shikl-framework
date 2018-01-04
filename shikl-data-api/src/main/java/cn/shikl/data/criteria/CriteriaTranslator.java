package cn.shikl.data.criteria;

/**
 * 查询条件特定类型翻译接口.
 * 
 * @author shikl <br/>
 * @version 1.0.0
 */
public interface CriteriaTranslator {

    /**
     * 转换值 的类型方法.
     * 
     * @param criterion
     *            条件接口.
     * @return Object .
     */
    Object getValueType(Criterion criterion);
}
