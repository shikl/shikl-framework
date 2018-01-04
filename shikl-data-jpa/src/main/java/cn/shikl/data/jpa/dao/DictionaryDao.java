package cn.shikl.data.jpa.dao;

import java.util.List;

import cn.shikl.data.dao.IDao;
import cn.shikl.data.jpa.entity.Dictionary;

public interface DictionaryDao extends IDao<Dictionary> {

    List<Dictionary> getDictionaryList();

}
