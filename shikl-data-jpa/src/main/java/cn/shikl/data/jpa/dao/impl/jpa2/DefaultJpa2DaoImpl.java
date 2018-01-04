package cn.shikl.data.jpa.dao.impl.jpa2;

import cn.shikl.data.criteria.Criteria;
import cn.shikl.data.criteria.Page;
import cn.shikl.data.criteria.PageImpl;
import cn.shikl.data.criteria.PageRequest;
import cn.shikl.data.criteria.Pageable;
import cn.shikl.data.dao.IDao;
import cn.shikl.data.jpa.criteria.projection.Projections;
import cn.shikl.utils.StringUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.NonUniqueResultException;
import javax.persistence.Query;
import java.lang.reflect.ParameterizedType;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * 默认的JPA2dao实现类.
 *
 * @author libo <br/>
 * @version 1.0.0
 */
@Repository(value = "basedao")
public class DefaultJpa2DaoImpl<T> extends AbstractJpa2Dao<T> implements IDao<T> {

    /**
     *
     */
    private final Logger logger = LoggerFactory.getLogger(getClass());

    /*
     * (non-Javadoc)
     *
     * @see
     * cn.com.rexen.rplus.core.dao.IDao#findOnly(cn.com.rexen.rplus.core.criteria
     * .Criteria)
     */
    @Override
    public T findOnly(Criteria criteria) {
        if (criteria == null) {
            throw new IllegalArgumentException("Criteria 不能为空.");
        }
        String qlString = criteria.toSqlString();
        Query query = em().createQuery(qlString);
        // query.setHint("org.hibernate.cacheable", true);
        setQueryParams(query, criteria);
        Object singleResult = query.getSingleResult();
        logger.debug("find Only :{},result:{}", criteria, singleResult);
        return (T) singleResult;
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * cn.com.rexen.rplus.core.dao.IDao#findTopOne(cn.com.rexen.rplus.core.criteria
     * .Criteria)
     */
    @Override
    public T findTopOne(Criteria criteria) {
        if (criteria == null) {
            throw new IllegalArgumentException("Criteria 不能为空.");
        }
        Pageable pageable = new PageRequest(1);
        criteria.setPageable(pageable);
        List<T> list = find(criteria);
        if (list == null || list.size() == 0) {
            throw new RuntimeException("结果集为空.");
        }
        logger.debug("find Top one count :{}", list.size());
        return list.get(0);
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * cn.com.rexen.rplus.core.dao.IDao#find(cn.com.rexen.rplus.core.criteria
     * .Criteria)
     */
    @Override
    public List<T> find(Criteria criteria) {
        if (criteria == null) {
            throw new IllegalArgumentException("Criteria 不能为空.");
        }
        String qlString = criteria.toSqlString();
        Query query = em().createQuery(qlString);
        setQueryParams(query, criteria);
        List resultList = query.getResultList();
        return resultList;
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * cn.com.rexen.rplus.core.dao.IDao#count(cn.com.rexen.rplus.core.criteria
     * .Criteria)
     */
    @Override
    public long count(Criteria criteria) {
        if (criteria == null) {
            throw new IllegalArgumentException("Criteria 不能为空.");
        }
        criteria.addProjection(Projections.count(criteria.getAlias()));
        criteria.buildOrder(false);
        String qlString = criteria.toSqlString();
        Query query = em().createQuery(qlString);
        setQueryParams(query, criteria);
        Object singleResult = query.getSingleResult();
        if (singleResult == null) {
            throw new NonUniqueResultException("统计记录数时出错，查询结果为空.");
        }
        logger.debug("count records :{}", singleResult);
        return (Long) singleResult;
    }

    /*
     * (non-Javadoc)
     *
     * @see cn.com.rexen.rplus.core.dao.IDao#execute(java.lang.String)
     */
    @Override
    public Object execute(String jpql) {
        Query query = em().createQuery(jpql);
        int executeUpdate = query.executeUpdate();
        return executeUpdate;
    }

    /*
     * (non-Javadoc)
     *
     * @see cn.com.rexen.rplus.core.dao.IDao#query(java.lang.String)
     */
    @Override
    public List<T> query(String jpql) {
        Query query = em().createQuery(jpql);
        List<T> result = query.getResultList();
        return result;
    }

    /*
     * (non-Javadoc)
     *
     * @see cn.com.rexen.rplus.core.dao.IDao#executeProcedure(java.lang.String,
     * java.lang.Object[])
     */
    @Override
    public void executeProcedure(String proName, Object... params) throws SQLException {
        throw new RuntimeException();
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * cn.com.rexen.rplus.core.dao.IDao#sum(cn.com.rexen.rplus.core.criteria
     * .Criteria, java.lang.String[])
     */
    @Override
    public float[] sum(Criteria criteria, String[] fields) {
        if (criteria == null) {
            throw new IllegalArgumentException("Criteria 不能为空.");
        }
        if (fields.length == 0) {
            throw new IllegalArgumentException("fields 不能为空.");
        }

        for (String field : fields) {
            criteria.addProjection(Projections.sum(field));
        }
        String qlString = criteria.toSqlString();
        Query query = em().createQuery(qlString);
        // 设置查询缓存
        // query.setHint("org.hibernate.cacheable", true);
        setQueryParams(query, criteria);
        List resultList = query.getResultList();
        float ff[] = null;
        if (resultList != null && resultList.size() > 0) {
            Object[] objects = new Object[fields.length];
            if (fields.length > 1) {
                objects = (Object[]) resultList.get(0);
            } else {
                Object object = resultList.get(0);
                objects[0] = object;
            }
            ff = new float[objects.length];
            int i = 0;
            for (Object object : objects) {
                ff[i++] = Float.valueOf(String.valueOf(object));
            }
        }
        return ff;
    }

    /*
     * (non-Javadoc)
     * 
     * @see cn.com.rexen.rplus.core.dao.IDao#getSession()
     */
    // @Override
    // public Session getSession() {
    // throw new
    // NotSupportedException(ExceptionCode.SYS_NOT_SUPPORT.getMessage());
    // }

    /*
     * (non-Javadoc)
     *
     * @see
     * cn.com.rexen.rplus.core.dao.IDao#findWithPage(cn.com.rexen.rplus.core
     * .criteria.Criteria)
     */
    @Override
    public Page<T> findWithPage(Criteria criteria) {
        if (criteria == null) {
            throw new IllegalArgumentException("Criteria 不能为空.");
        }
        Pageable pageable = criteria.getPageable();
        if (pageable == null) {
            throw new IllegalArgumentException("pageable 不能为空.");
        }
        int startIndex = pageable.getStartRowIndex();
        int pageSize = pageable.getPageSize();

        if (pageSize < 1) {
            throw new IllegalArgumentException("pageSize 小于1 ，不能查询出结果.");
        }

        String qlString = criteria.toSqlString();

        long total = count(criteria);
        if (total > Integer.MAX_VALUE) {
            logger.warn("统计记录数超过 {} 条,将只显示 {} 条以内的数据.", Integer.MAX_VALUE, Integer.MAX_VALUE);
        }
        Query query = em().createQuery(qlString);
        setQueryParams(query, criteria);
        if (startIndex > -1 && pageSize > 0) {
            query.setFirstResult(startIndex).setMaxResults(pageSize);
            List<T> resultList = query.getResultList();
            Page<T> page = new PageImpl<T>(resultList, pageable, Integer.valueOf(String.valueOf(total)));
            return page;
        }
        logger.warn("开始记录数小于-1 或每页显示记录数小于0，不能显示数据.");
        return null;
    }

    /**
     * 设置参数.
     *
     * @param query    查询对象.
     * @param criteria 查询参数封装对象.
     */
    private void setQueryParams(Query query, Criteria criteria) {
        Map<String, Object> parameter = criteria.getParameter();
        if (!parameter.isEmpty()) {
            for (String key : parameter.keySet()) {
                query.setParameter(key, parameter.get(key));
            }
        }
    }

    /*
     * (non-Javadoc)
     *
     * @see cn.com.rexen.rplus.core.dao.IDao#findWithPage(java.lang.String)
     */
    @Override
    public Page<T> findWithPage(String ql, Pageable pageable, Object[] queryParams) {
        if (pageable == null) {
            throw new IllegalArgumentException("pageable 不能为空.");
        }
        int startRowIndex = pageable.getStartRowIndex();
        int pageSize = pageable.getPageSize();
        Query query = em().createQuery(ql);
        setQueryParams(query, queryParams);
        //TODO 统计记录的方式
        int total = query.getResultList().size();
        if (startRowIndex != -1 && pageSize != -1) {
            query.setFirstResult(startRowIndex).setMaxResults(pageSize);
        }
        List<T> resultList = query.getResultList();
        // String countQl = "select count(*) from (" + ql + ")";

        Page<T> page = new PageImpl<T>(resultList, pageable, total);
        return page;
    }

    /*
    * (non-Javadoc)
    *
    * @see cn.com.rexen.rplus.core.dao.IDao#findWithPage(java.lang.String)
    */
    //@Override
    public Page<T> findWithPage(String ql, String countSql, Pageable pageable, Object[] queryParams) {
        if (pageable == null) {
            throw new IllegalArgumentException("pageable 不能为空.");
        }
        if (StringUtils.isEmpty(countSql)) {
            throw new IllegalArgumentException("countSql is null");
        }

        if (StringUtils.isEmpty(ql)) {
            throw new IllegalArgumentException("sql is null");
        }
        Query query1 = em().createQuery(countSql);
        setQueryParams(query1, queryParams);
        Long total = (Long) query1.getSingleResult();

        int startRowIndex = pageable.getStartRowIndex();
        int pageSize = pageable.getPageSize();


        Query query = em().createQuery(ql);
        setQueryParams(query, queryParams);
        if (startRowIndex != -1 && pageSize != -1) {
            query.setFirstResult(startRowIndex).setMaxResults(pageSize);
        }
        List<T> resultList = query.getResultList();
        // String countQl = "select count(*) from (" + ql + ")";

        Page<T> page = new PageImpl<T>(resultList, pageable, total.intValue());
        return page;
    }

    /*
     * (non-Javadoc)
     *
     * @see cn.com.rexen.rplus.core.dao.IDao#count(java.lang.String)
     */
    @Override
    public long count(String ql, Object... objects) {
        Query query = em().createQuery(ql);
        setQueryParams(query, objects);
        Long singleResult = (Long) query.getSingleResult();
        return singleResult;
    }

    /**
     * 设计ql参数方法.
     *
     * @param query       * 查询语句.
     * @param queryParams 参数列表.
     */
    private void setQueryParams(Query query, Object[] queryParams) {
        if (queryParams != null && queryParams.length > 0) {
            for (int i = 0; i < queryParams.length; i++) {
                query.setParameter(i + 1, queryParams[i]);
            }
        }
    }

    //    private Class <T> entityClass;
    /*
     * (non-Javadoc)
     *
     * @see cn.com.rexen.rplus.core.dao.IDao#query(java.lang.String,
     * java.lang.Object[])
     */
    @Override
    public List<T> query(String ql, Object[] object) {
        ParameterizedType type = (ParameterizedType) this.getClass().getGenericSuperclass();
        Class<T> entityClass = (Class) type.getActualTypeArguments()[0];
        Query query = em().createQuery(ql, entityClass);
        setQueryParams(query, object);
        return (List<T>) query.getResultList();
    }


    public List queryObject(String ql, Object[] object) {
        Query query = em().createQuery(ql);
        setQueryParams(query, object);
        return (List) query.getResultList();
    }

}
