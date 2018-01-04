package cn.shikl.data.jpa.dao.impl;

import java.util.List;

import javax.persistence.Query;

import cn.shikl.data.criteria.Criteria;
import cn.shikl.data.jpa.dao.DictionaryDao;
import cn.shikl.data.jpa.dao.impl.jpa2.DefaultJpa2DaoImpl;
import cn.shikl.data.jpa.entity.Dictionary;

import org.springframework.stereotype.Repository;

/**
 * 自定义的字典dao实现.
 * 
 * @author zhanghai.
 * 
 */
@Repository
public class DictionaryDaoImpl extends DefaultJpa2DaoImpl<Dictionary> implements DictionaryDao {
    @Override
    public List<Dictionary> getDictionaryList() {
        Query createQuery = em().createQuery("from Dictionary");
        return createQuery.getResultList();
    }
}
