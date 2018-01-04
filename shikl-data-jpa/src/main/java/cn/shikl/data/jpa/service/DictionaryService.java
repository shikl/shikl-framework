package cn.shikl.data.jpa.service;

import cn.shikl.data.criteria.Page;
import cn.shikl.data.jpa.entity.Dictionary;
import cn.shikl.data.service.IService;

import java.util.List;

/**
 * 字典服务接口.
 *
 * @author libo
 * @version 1.0
 */
public interface DictionaryService extends IService<Dictionary> {

    /**
     * service名称.
     */
    String BEAN_NAME = "dictionaryService";

    /**
     * 获取字典表记录列表.
     *
     * @return list.
     */
    List<Dictionary> getDictionaryList();

    /**
     * 获取字典表记录列表.
     *
     * @return list.
     */
    Page<Dictionary> getDictionaryWithPage();

    /**
     * 获取字典表记录列表.
     *
     * @return list.
     */
    List<Dictionary> getDictionaryByGroup(String group);

}
