package cn.shikl.controller;

import cn.shikl.data.criteria.Criteria;
import cn.shikl.data.criteria.Page;
import cn.shikl.data.criteria.PageRequest;
import cn.shikl.data.criteria.Pageable;
import cn.shikl.model.BaseVO;
import cn.shikl.model.Messages;
import cn.shikl.model.PageMessages;
import cn.shikl.utils.StringUtils;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindException;
import org.springframework.validation.Validator;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 增删改查基础Controller.
 *
 * @param <T> 泛型支持.
 * @author shikl .
 * @version 1.0.0
 *
 */
public abstract class CURDWithJsonControllerSupport<T extends Serializable, V extends BaseVO> extends AbstractCURDControllerSupport<T, V> {

    public static final String PRODUCES_JSON = "application/json;charset=UTF-8";

    /**
     * 默认的每页显示条数.
     */
    public static final int DEFAULT_PAGESIZE = 3;


    /**
     * 日志器.
     */
    protected static final Logger LOGGER = LoggerFactory.getLogger(CURDWithJsonControllerSupport.class);
    /**
     * 根结点标志.
     */
    protected static final String ROOT = "_root";

    /**
     * 实体名称.
     */
    private String entityName;

    /**
     * 验证器接口.
     */
    @Resource
    private Validator validator;

    /**
     * 删除.
     *
     * @param request  http请求.
     * @param response http响应.
     * @param id       url地址上用于删除的对象的id.
     * @return ModelMap.
     */
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    @ResponseBody
    public Messages delete(HttpServletRequest request, HttpServletResponse response, @PathVariable String id) throws Exception {
        HashMap<String, Object> map = new HashMap<>();
        if (StringUtils.isEmpty(id)) {
            throw new RuntimeException("删除时编号为空.");
        }
        T object = getEntityById((Serializable) convertId(id));
        startDelete(request, response, object);
        doDelete(object);
        endDelete(request, response, map, object);
        Messages messages = Messages.success();
        return messages;
    }

    /**
     * 根据主键查询数据对象.
     *
     * @param id 主键ID.
     * @return 数据库中对象.
     */
    protected T getEntityById(Serializable id) {
        T object = (T) getEntityService().get(getEntityClass(), id);

        if (object == null) {
            throw new RuntimeException("没有此对象.");
        }
        return object;
    }

    /**
     * 转换ID.
     *
     * @param id
     * @return
     */
    //TODO　将ID的类型统一
    private Serializable convertId(String id) {

        if (StringUtils.isNotEmpty(id)) {
            Field[] fields = getEntityClass().getDeclaredFields();
            if (fields != null && fields.length > 0) {
                for (Field field : fields) {
                    if (field != null) {
                        Annotation[] annotations = field.getAnnotations();
                        if (annotations != null && annotations.length > 0) {
                            for (Annotation annotation : annotations) {
                                if ("@javax.persistence.Id()".equals(annotation.toString())) {
                                    String type = field.getType().getName();
                                    if ("java.lang.Integer".equals(type)) {
                                        return Integer.parseInt(id);
                                    } else if ("java.lang.String".equals(type)) {
                                        return id;
                                    } else if ("java.lang.Long".equals(type)) {
                                        return Long.parseLong(id);
                                    } else if ("java.lang.Double".equals(type)) {
                                        return Double.parseDouble(id);
                                    } else if ("java.lang.Float".equals(type)) {
                                        return Float.parseFloat(id);
                                    } else if ("java.lang.Byte".equals(type)) {
                                        return Byte.parseByte(id);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return null;
    }

    /**
     * 执行删除操作.
     *
     * @param entity 删除对象的实体.
     * @throws Exception 异常.
     */
    public void doDelete(T entity) throws Exception {
        getEntityService().delete(entity);
    }

    /**
     * 获取实体的Class.
     *
     * @return class.
     */
    protected Class<T> getEntityClass() {
        return (Class<T>) getEntity().getClass();
    }

    /**
     * 返回视图,
     *
     * @return 增加视图名称.
     */
    protected String getResponseView() {
        return "view";
    }

    /**
     * 根据默认的请求参数进行分页查询。 回调函数：beforeDoList(...), afterDoList(...).
     *
     * @param request  http请求.
     * @param response http响应.
     * @return ModelMap.
     */
    @RequestMapping(value = {"/"}, method = RequestMethod.GET, produces = {"application/json"})
    @ResponseBody
    public PageMessages<T, V> list(HttpServletRequest request, HttpServletResponse response , V vo) {

        startList(request, response);
        Page<T> page = doList(request, response, vo);
        //转换实体类型
        //Map<String, Object> _map = new HashMap<>();
        PageMessages<T, V> messages = new PageMessages<T, V>(Messages.SUCCESS) {
            @Override
            protected List<V> entityListToVOList(List<T> entityList) {
                List<V> voList = entityConvertVoInList(entityList);
                return voList;
            }
        };
        messages.setData(page);
        endList(request, response, messages);

        return messages;
    }

    /**
     * 查询所有实体，不进行分页。
     *
     * @param request  http请求.
     * @param response http响应.
     * @return ModelMap.
     */
    @RequestMapping(value = {""}, method = RequestMethod.GET, produces = {"application/json"})
    @ResponseBody
    public Messages listAll(HttpServletRequest request, HttpServletResponse response) {

        Messages messages = Messages.success();

        Criteria criteria = getEntityService().getCriteria(getEntityClass());
        List<T> enties = getEntityService().find(criteria);
        //转换实体类型
        List<V> voList = entityConvertVoInList(enties);
        messages.setData(voList);
        return messages;
    }

    protected Page<T> doList(HttpServletRequest request, HttpServletResponse response, V vo) {
        Criteria criteria = getEntityService().getCriteria(getEntityClass());

        String pageSize = "";
        String currentPage = "";
        try {
            pageSize = BeanUtils.getProperty(vo, "pageSize");
            currentPage = BeanUtils.getProperty(vo, "currentPage");
        } catch (Exception  e) {
            LOGGER.error("VO 中必须定义 pageSize 与 currentPage");
        }
        Pageable pageable = new PageRequest(currentPage == null ? 0 : Integer.parseInt(currentPage), pageSize == null ? DEFAULT_PAGESIZE
                : Integer.parseInt(pageSize));
        criteria.setPageable(pageable);

        //设置查询条件，由子类扩展实现.
        buildCriteria(criteria, request, vo);

        Page<T> pageResult = getEntityService().findWithPage(criteria);

        return pageResult;
    }

    /**
     * 查询实体信息(show.do)回调函数，在返回视图之前调用用。可以继续添加返回信息.
     *
     * @param request  http请求.
     * @param response http响应.
     * @param id       实体对象
     * @param modelMap ModelMap.
     * @return VO对象.
     */
    @Override
    protected T doShow(HttpServletRequest request, HttpServletResponse response, Serializable id, ModelMap modelMap) {
        T t = (T) getEntityService().get(getEntityClass(), id);
        return t;
    }

    @Override
    protected List<V> entityConvertVoInList(List<T> entitys) {
        List<V> voList = new ArrayList<V>();
        for (T entity : entitys) {
            voList.add(entityConvertVo(entity));
        }
        return voList;
    }

    /**
     * page 转换成pageVo
     *
     * @param page
     * @return
     */
    /*protected PageVO page2Vo(Page<T> page) {
        PageVO pageVO = new PageVO(page);
        return pageVO;
    }*/

    /**
     * 由子类扩展实现的查询条件.可以在此方法中增加查询的条件.
     *
     * @param criteria 查询条件，已存在分页信息.
     * @param request  HttpServletRequest
     */
    protected void buildCriteria(Criteria criteria, HttpServletRequest request, V vo) {

    }

    /**
     * 保存实体<br/>
     * 回调函数：beforeDoSave(...), afterDoSave(...). (目前还没有做jsr303验证)
     *
     * @param request  http请求.
     * @param response http响应.
     * @param vo       页面传入的VO对象.
     * @return vo .
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST, headers = {"Content-type=application/json"})
    @ResponseBody
    public Messages save(HttpServletRequest request, HttpServletResponse response, @RequestBody V vo) throws BindException {
        Messages messages = Messages.success();
        Map<String, Object> mm = new HashMap<String,Object>();
        V returnVo = null;

        startSave(request, response, vo, mm);

        bindRequestEntity(request, vo);

        T entity = voConvertEntity(vo);

        validatorEntity(entity);

        validatorEntity4Save(entity);

        doSave(request, response, entity);

        endSave(request, response, mm, entity);

        returnVo = entityConvertVo(entity);

        messages.setData(returnVo);

        return messages;
    }

    /**
     * 从Request中绑定对象并进行校验.
     *
     * @param request http请求.
     * @param entity  VO对象.
     * @return BindException .
     * @throws Exception 异常.
     */
    protected void bindRequestEntity(HttpServletRequest request, V entity) throws BindException {
        ServletRequestDataBinder binder = new ServletRequestDataBinder(entity);
        initBinder(binder);
        binder.bind(request);
        BindException errors = new BindException(binder.getBindingResult());
//        validator.validate(entity, errors);
//        if (errors.hasErrors()) {
//            logger.error(errors.getMessages());
//            throw errors;
//        }
    }

    /**
     * 执行save方法.
     *
     * @param request  http请求.
     * @param response http响应.
     * @param entity
     * @throws Exception 异常.
     */
    public void doSave(HttpServletRequest request, HttpServletResponse response, T entity) {
        getEntityService().save(entity);
    }

    /**
     * 查询实体信息<br/>
     *
     * @param request  http请求.
     * @param response http响应.
     * @param id       显详细信息的id.
     * @return 视图模型 .
     */
    @RequestMapping(value = "/show/{id}", produces = {"application/json;charset=UTF-8"}, method = RequestMethod.GET)
    @ResponseBody
    public Messages show(HttpServletRequest request, HttpServletResponse response, @PathVariable String id) {
        ModelMap mav = new ModelMap();
        T object = doShow(request, response, id, mav);
        V t = entityConvertVo(object);
        Messages messages = Messages.success("");
        messages.setData(t);
        return messages;
    }


    /**
     * 修改实体<br/>
     * 回调函数：beforeUpdate(...), afterUpdate(...).
     *
     * @param request  http请求.
     * @param response http响应.
     * @param vo       页面传入的VO对象.
     * @param id       url地址id参数.
     * @return T vo对象.
     */
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST, headers = {"Content-type=application/json"})
    @ResponseBody
    public Messages update(HttpServletRequest request, HttpServletResponse response, @PathVariable String id, @RequestBody V vo) throws BindException {
        Messages messages = Messages.success();
        Map<String, Object> map = new HashMap<>();
        V returnVo = null;
        if (StringUtils.isEmpty(id)) {
            throw new RuntimeException("id 不能为空.");
        }
        startUpdate(request, response, map, id, vo);

        bindRequestEntity(request, vo);

        T entity = voConvertEntity(vo);

        validatorEntity(entity);

        validatorEntity4Update(entity);

        doUpdate(request, response, entity);

        endUpdate(request, response, map, entity);

        returnVo = entityConvertVo(entity);
        messages.setData(returnVo);
        return messages;
    }

    /**
     * 执行修改操作.
     *
     * @param request  http请求.
     * @param response http响应.
     * @throws Exception 异常.
     */
    public void doUpdate(HttpServletRequest request, HttpServletResponse response, T entity) {
        getEntityService().saveOrUpdate(entity);
    }
}
