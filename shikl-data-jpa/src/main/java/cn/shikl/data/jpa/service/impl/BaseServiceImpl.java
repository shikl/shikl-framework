package cn.shikl.data.jpa.service.impl;

import cn.shikl.data.criteria.Criteria;
import cn.shikl.data.criteria.Page;
import cn.shikl.data.dao.IDao;
import cn.shikl.data.jpa.criteria.CriteriaFactory;
import cn.shikl.data.jpa.service.IdGeneratorService;
import cn.shikl.data.listener.DeleteEventListener;
import cn.shikl.data.listener.SaveEventListener;
import cn.shikl.data.listener.UpdateEventListener;
import cn.shikl.data.validator.IValidator;
import cn.shikl.utils.IDGeneratorUUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 此类实现一般的增删改查.
 * 自定义service可以继承此类.
 *
 * @param <T> 泛型参数.
 * @author shikl .
 * @version 1.0
 */
@Transactional(readOnly = true)
public class BaseServiceImpl<T> {

    /**
     * 开发日志.
     */
    private final org.slf4j.Logger logger = LoggerFactory.getLogger(getClass());
    /**
     * 应用日志.
     */
    //TODO 应用程序Logger
//    @Autowired
    Logger app_logger;

    /**
     * JSR303 验证实现.
     */
    @Autowired
    @Qualifier(value = "validatorEntity")
    private IValidator<T> validatorEntity;
    /**
     * 验证接口列表.
     */
    //    @Autowired(required = false)
    private List<IValidator<T>> validators = new ArrayList<IValidator<T>>();

    /**
     * 保存事件监听列表.
     */
    @Autowired(required = false)
    private List<SaveEventListener<T>> saveEventListeners = new ArrayList<SaveEventListener<T>>();

    /**
     * 更新事件监听列表.
     */
    @Autowired(required = false)
    private List<UpdateEventListener<T>> updateEventListeners =
            new ArrayList<UpdateEventListener<T>>();

    /**
     * 更新事件监听列表.
     */
    @Autowired(required = false)
    private List<DeleteEventListener<T>> deleteEventListeners =
            new ArrayList<DeleteEventListener<T>>();

    public Logger getAppLogger() {
        return app_logger;
    }

    /**
     * 黙认Dao接口.
     */
    @Resource(name = "basedao")
    private IDao<T> dao;

    /**
     * 主键生成service.
     */
    @Resource(name = IdGeneratorService.BEAN_NAME)
    private IdGeneratorService idGeneratorService;

    /**
     * 获取离线条件对象的工厂.
     */
    @Autowired
    private CriteriaFactory criteriaFactory;

    /**
     * 获取查询条件工厂方法.
     *
     * @return CriteriaFactory.
     */
    public CriteriaFactory getCriteriaFactory() {
        return criteriaFactory;
    }

    /**
     * 获取Dao方法.
     *
     * @return IDao.
     */
    public IDao<T> getDao() {
        return this.dao;
    }

    /**
     * 验证实体.
     *
     * @param t T.
     */
    public void validateEntity(T t) {
        validatorEntity.validate(t);
        for (IValidator<T> validator : validators) {
            validator.validate(t);
        }
    }

    /**
     * 添加验证实现.
     * （默认提供一个验证实现validatorEntity）
     *
     * @param validator 验证实现.
     */
    public void addValidate(IValidator<T> validator) {
        if (validator != null) {
            validators.add(validator);
        }
    }

    public void addSaveEventListener(SaveEventListener<T> listener) {
        if (listener != null) {
            saveEventListeners.add(listener);
        }
    }

    public void addUpdateEventListener(UpdateEventListener<T> listener) {
        if (listener != null) {
            updateEventListeners.add(listener);
        }
    }

    /**
     * 生成编号.
     *
     * @return 生成编号.默认为空.
     */
    @Transactional(readOnly = false)
    public String generateId() {
        return IDGeneratorUUID.generatorId();
    }

    /**
     * 默认的删除实体.
     *
     * @param object 实体对象.
     */
    @Transactional(readOnly = false)
    public void delete(T object) {
        for (DeleteEventListener<T> listener : deleteEventListeners) {
            listener.beforeDelete(object);
        }
        dao.delete(object);
        for (DeleteEventListener<T> listener : deleteEventListeners) {
            listener.afterDelete(object);
        }
    }

    /**
     * 执行保存操作.
     *
     * @param object 实体对象.
     */
    @Transactional(readOnly = false)
    public void save(T object) {
        for (SaveEventListener<T> listener : saveEventListeners) {
            listener.beforeSave(object);
        }
        validateEntity(object);
        validateUnique(object);
        dao.save(object);
        for (SaveEventListener<T> listener : saveEventListeners) {
            listener.afterSave(object);
        }
        if (app_logger != null) {
            app_logger.info("保存对象{} 成功!", object);
        }
    }

    /**
     * 默认的执行修改操作.
     *
     * @param object 实体对象 .
     */
    @Transactional(readOnly = false)
    public void saveOrUpdate(T object) {
        for (UpdateEventListener listener : updateEventListeners) {
            listener.beforeUpdate(object);
        }
        validateEntity(object);
        dao.saveOrUpdate(object);
        for (UpdateEventListener<T> listener : updateEventListeners) {
            listener.afterUpdate(object);
        }
        if (app_logger != null) {
            app_logger.info("更新对象{} 成功!", object);
        }
    }

    /**
     * 默认的执行修改merge操作.
     *
     * @param object 实体对象 .
     */
    @Transactional(readOnly = false)
    public void merge(T object) {
        dao.merge(object);
    }

    /**
     * .
     *
     * @param object .
     * @return boolean.
     */
    public boolean isUse(T object) {
        return false;
    }

    /**
     * 通过id和泛型class获取实体对象.
     * <p/>
     * <p/>
     * <pre>
     * Dictionary dictionary = dictionaryService.get(Dictionary.class, &quot;1&quot;);
     * </pre>
     *
     * @param clazz 泛型class.
     * @param id    主键id.
     * @return T.
     */
    public T get(Class<T> clazz, Serializable id) {
        return dao.get(clazz, id);
    }

    /**
     * 通过条件对象查询实体对象的列表.
     * <p/>
     * <p/>
     * <pre>
     * Criteria criteria = dictionaryService.getCriteriaFactory().getDetachedCriteria(Dictionary.class);
     * List&lt;Dictionary&gt; list = dictionaryService.find(criteria);
     * </pre>
     *
     * @param c 条件对象.
     * @return List.
     */
    public List<T> find(Criteria c) {
        return dao.find(c);
    }

    /**
     * 验证实体唯一性.
     * 如果验证实体不通过则抛出UniqueException异常 .
     *
     * @param object T
     */
    public void validateUnique(T object) {
        //        IValidator<T> v = new ValidatorEntity<T>();
        //        v.validate(object);
    }

    /**
     * 获取主键生成的service.
     *
     * @return IdGeneratorService.
     */
    public IdGeneratorService getIdGeneratorService() {
        return idGeneratorService;
    }

    /**
     * 查询列表，此查询结果被封装在Page对象中.
     * <p/>
     * <p/>
     * <pre>
     * Pageable page = new PageRequest(true, 10);
     * Criteria criteria = dictionaryService.getCriteria(Dictionary.class);
     * criteria.add(Restrictions.or(Restrictions.eq(&quot;name&quot;, &quot;column1&quot;), Restrictions.eq(&quot;name&quot;, &quot;column2&quot;)));
     * criteria.setPageable(page);
     * Page&lt;Dictionary&gt; pageList = dictionaryService.findWithPage(criteria);
     * </pre>
     *
     * @param c 条件参数.
     * @return Page对象.
     */
    public Page<T> findWithPage(Criteria c) {
        return dao.findWithPage(c);
    }

    /**
     * 通过实体class获取Criteria对象.
     * 获取代码如下：
     * <p/>
     * <pre>
     * Criteria c = dictionaryService.getCriteria(Dictionary.class);
     * </pre>
     *
     * @param clazz 实体class.
     * @return Criteria.
     */
    public Criteria getCriteria(Class<T> clazz) {
        if (clazz == null) {
            throw new IllegalArgumentException("class 为空，不能生成查询条件!");
        }
        return getCriteriaFactory().getCriteria(clazz);
    }

    /**
     * 查询符合条件的第一条记录.
     * <p/>
     * <pre>
     * Criteria c = dictionaryService.getCriteriaFactory().getCriteria(Dictionary.class);
     * c.addOrder(Order.asc(&quot;dictId&quot;));
     * Dictionary dictionary = dictionaryService.findTopOne(c);
     * </pre>
     *
     * @param c 查询条件.
     * @return 返回符合条件的记录。如果查询结果为空或多于1条不抛出异常.
     */
    public T findTopOne(Criteria c) {
        return (T) dao.findTopOne(c);
    }

    /**
     * 如果数据中存在集合，只取一个.
     * <p/>
     * <pre>
     * Criteria c = dictionaryService.getCriteriaFactory().getCriteria(Dictionary.class);
     * c.add(Restrictions.eq(&quot;name&quot;, &quot;column1&quot;));
     * Dictionary dictionary = dictionaryService.findOnly(c);
     * </pre>
     *
     * @param c /
     * @return Object
     */
    public T findOnly(final Criteria c) {
        return (T) dao.findOnly(c);
    }


    /**
     * 批量插入
     * @param list
     */
    public void batchInsert(List<T> list){
        dao.batchInsert(list);
    }


    /**
     * 批量更新
     * @param list
     */
    public void batchUpdate(List<T> list){
        dao.batchUpdate(list);
    }

}