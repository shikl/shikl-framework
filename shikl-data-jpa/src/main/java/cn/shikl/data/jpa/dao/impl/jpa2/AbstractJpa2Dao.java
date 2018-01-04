package cn.shikl.data.jpa.dao.impl.jpa2;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;
import java.util.List;

/**
 * 默认的jpa实现.
 *
 * @param <T> 泛型参数.
 * @author shikl.
 * @version 1.0
 */
public abstract class AbstractJpa2Dao<T> {

    /**
     * jpa对象.
     */
    @PersistenceContext
    private EntityManager entityManager;

    /**
     * 获取EntityManager .
     *
     * @return the entityManager .
     */
    public EntityManager em() {
        return entityManager;
    }

    /* (non-Javadoc)
     * @see cn.com.rexen.rplus.core.dao.IDao#save(java.lang.Object)
     */
    public void save(T object) {
        if (object == null) {
            throw new IllegalArgumentException("参数不能为空.");
        }
        em().persist(object);
    }

    /* (non-Javadoc)
     * @see cn.com.rexen.rplus.core.dao.IDao#saveOrUpdate(java.lang.Object)
     */
    public void saveOrUpdate(T object) {
        if (object == null) {
            throw new IllegalArgumentException("参数不能为空.");
        }
        em().merge(object);
    }

    /* (non-Javadoc)
     * @see cn.com.rexen.rplus.core.dao.IDao#delete(java.lang.Object)
     */
    public void delete(T object) {
        if (object == null) {
            throw new IllegalArgumentException("参数不能为空.");
        }
        em().remove(em().merge(object));
    }

    /**
     * 批量插入
     * @param list
     */
    public void batchInsert(List<T> list){
        for (int i = 0; i < list.size(); i++) {
            em().persist(list.get(i));
            if (i % 50 == 0) {
                em().flush();
                em().clear();
            }
        }
    }


    /**
     * 批量更新
     * @param list
     */
    public void batchUpdate(List<T> list){
        for (int i = 0; i < list.size(); i++) {
            em().merge(list.get(i));
            if (i % 50 == 0) {
                em().flush();
                em().clear();
            }
        }
    }

    /* (non-Javadoc)
     * @see cn.com.rexen.rplus.core.dao.IDao#get(java.lang.Class, java.io.Serializable)
     */
    public T get(Class<T> clazz, Serializable id) {
        if (id == null) {
            throw new IllegalArgumentException("id 不能为空.");
        }
        return (T) em().find(clazz, id);
    }

    /* (non-Javadoc)
     * @see cn.com.rexen.rplus.core.dao.IDao#load(java.lang.Class, java.io.Serializable)
     */
    public T load(Class<T> clazz, Serializable id) {
        throw new RuntimeException();
    }

    /* (non-Javadoc)
     * @see cn.com.rexen.rplus.core.dao.IDao#merge(java.lang.Object)
     */
    public void merge(T object) {
        if (object == null) {
            throw new IllegalArgumentException("参数不能为空.");
        }
        em().merge(object);
    }
}
