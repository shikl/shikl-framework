package cn.shikl.data.jpa.service.impl;

import cn.shikl.data.criteria.Criteria;
import cn.shikl.data.criteria.Page;
import cn.shikl.data.jpa.entity.Dictionary;
import cn.shikl.data.jpa.service.DictionaryService;
import cn.shikl.utils.IDGeneratorUUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 字典服务实现类.
 *
 * @author shikl
 * @version 1.0
 */
@Service(value = DictionaryService.BEAN_NAME)
public class DictionaryServiceImpl extends BaseServiceImpl<Dictionary> implements DictionaryService {

    private Logger logger = LoggerFactory.getLogger(getClass());

    /* (non-Javadoc)
         * @see BaseServiceImpl#generateId()
         */
    @Override
    public String generateId() {
        return IDGeneratorUUID.generatorId();
    }

    @Override
    public List<Dictionary> getDictionaryList() {
        Criteria criteria = getCriteria(Dictionary.class);
        return find(criteria);
    }

    @Override
    public Page<Dictionary> getDictionaryWithPage() {
        Criteria criteria = getCriteria(Dictionary.class);
        return null;
    }

    @Override
    public List<Dictionary> getDictionaryByGroup(String group) {
        return null;
    }
}
