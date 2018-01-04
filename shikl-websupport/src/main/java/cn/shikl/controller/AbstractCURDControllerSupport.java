package cn.shikl.controller;

import cn.shikl.data.criteria.Page;
import cn.shikl.data.service.IService;
import cn.shikl.model.BaseVO;
import cn.shikl.model.Messages;
import cn.shikl.utils.StringUtils;

import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 增删改查基础Controller.
 *
 * @param <T> 泛型支持.
 * @author shikl.
 * @version 1.0.0
 */
public abstract class AbstractCURDControllerSupport<T extends Serializable, V extends BaseVO> extends ControllerSupport {

    /**
     * 根节点ID.
     */
    public final static String ROOT = "_root";

    /**
     * 默认增加视图访问路径.
     */
    protected final String DEFAULT_CREATE_PATH = "/create";
    /**
     * 默认编辑视图访问路径.
     */
    protected final String DEFAULT_EDIT_PATH = "/edit";
    /**
     * 默认列表视图访问路径.
     */
    protected final String DEFAULT_LIST_PATH = "/list";
    /**
     * 默认显示详细信息视图访问路径.
     */
    protected final String DEFAULT_SHOW_PATH = "/show";
    /**
     * 默认删除访问路径.
     */
    protected final String DEFAULT_DELETE_PATH = "/delete";
    /**
     * 默认增加视图名称.
     */
    protected final String DEFAULT_CREATE_VIEW = "create";
    /**
     * 默认编辑视图名称.
     */
    protected final String DEFAULT_EDIT_VIEW = "edit";
    /**
     * 默认列表视图名称.
     */
    protected final String DEFAULT_LIST_VIEW = "list";
    /**
     * 默认显示详细信息视图名称.
     */
    protected final String DEFAULT_SHOW_VIEW = "show";
    /**
     * 默认显示视图名称.
     */
    protected final String DEFAULT_VIEW = "main";

    /**
     * 验证器接口.
     */
//    @Resource
//    private Validator validator;

    /**
     * 抽象的实体对象.
     *
     * @return Object.
     */
    public abstract T getEntity();

    /**
     * 获取实体服务类的实例(返回Service一个接口).
     *
     * @return IService.
     */
    protected abstract IService getEntityService();


    /**
     * list查询方法的实现(目前没有做成通用的，由子类方法实现).
     *
     * @param request  http请求.
     * @param response http响应.
     * @return Page 分页信息.
     */
    protected abstract Page doList(HttpServletRequest request, HttpServletResponse response, V vo);

    /**
     * 查询实体信息(show.do)回调函数，在返回视图之前调用用。可以继续添加返回信息.
     *
     * @param request  http请求.
     * @param response http响应.
     * @param id       实体对象主键ID
     * @param modelMap ModelMap.
     * @return VO对象.
     */
    protected abstract T doShow(HttpServletRequest request, HttpServletResponse response, Serializable id, ModelMap modelMap);


    /**
     * 分页查询(list.do)回调函数，该方法在执行查询之前调用。可以继续添加过滤条件和排序条件.
     *
     * @param request  http请求.
     * @param response http响应.
     */
    protected void startList(HttpServletRequest request, HttpServletResponse response) {
    }

    /**
     * 分页查询(list.do)回调函数，该方法在返回视图之前调用。可以继续添加返回信息.
     *
     * @param request  http请求.
     * @param response http响应.
     */
    protected void endList(HttpServletRequest request, HttpServletResponse response, Messages messages) {
    }


    /**
     * 保存实体回调函数，在执行实体与Request参数绑定之前调用用。 注意：由于entity可能是托管对象，对entity所做的修改都将反映到数据库。
     * 所以有必要在此方法中进行前期的数据校验，以免发生意外.
     *
     * @param request http请求.
     * @param entity  VO对象.
     * @param mm      modelmap.
     */
    protected void beforeBindSaveRequestEntity(HttpServletRequest request, HttpServletResponse response, T entity, ModelMap mm) {
    }

    /**
     * 修改实体回调函数，在执行实体与Request参数绑定之前调用用。 注意：由于entity可能是托管对象，对entity所做的修改都将反映到数据库。
     * 所以有必要在此方法中进行前期的数据校验，以免发生意外.
     *
     * @param request http请求.
     * @param entity  VO对象.
     * @param mm      modelmap.
     */
    protected void beforeBindUpdateRequestEntity(HttpServletRequest request, HttpServletResponse response, T entity, ModelMap mm) {
    }

    /**
     * 保存实体(save)回调函数，在执行保存之前调用用。可以进行数据校验.
     *
     * @param request HttpServletRequest
     * @param entity  实体对象
     * @param mm      ModelAndView
     */
    protected void startSave(HttpServletRequest request, HttpServletResponse response, V entity, Map<String, Object> mm) {
    }

    /**
     * 保存实体(save)回调函数，在返回视图之前调用用。可以继续添加返回信息.
     *
     * @param request  http请求.
     * @param response http响应.
     * @param mm       ModelMap.
     * @param entity   实体对象.
     */
    protected void endSave(HttpServletRequest request, HttpServletResponse response, Map<String, Object> mm, T entity) {
    }


    /**
     * 保存实体(update)回调函数，在执行保存之前调用用。可以进行数据校验.
     *
     * @param request
     * @param response
     * @param mm
     * @param id       编辑的实体ID.
     */
    protected void startUpdate(HttpServletRequest request, HttpServletResponse response, Map<String, Object> mm, String id, V vo) {
    }

    /**
     * 保存实体(update)回调函数，在返回视图之前调用用。可以继续添加返回信息.
     *
     * @param request  http请求.
     * @param response http响应.
     * @param mm       ModelMap.
     * @param entity   VO对象.
     */
    protected void endUpdate(HttpServletRequest request, HttpServletResponse response, Map<String, Object> mm, T entity) {
    }


    /**
     * 删除实体(remove.do)回调函数，在执行保存之前调用用。可以进行数据校验.
     *
     * @param request  http请求.
     * @param response http响应.
     * @param entity   实体对象
     */
    protected void startDelete(HttpServletRequest request, HttpServletResponse response, T entity) {
    }

    /**
     * 删除实体(remove.do)回调函数，在返回视图之前调用用。可以继续添加返回信息.
     *
     * @param request  http请求.
     * @param response http响应.
     * @param mm       ModelMap.
     * @param entity   删除的实体.
     */
    protected void endDelete(HttpServletRequest request, HttpServletResponse response, HashMap<String, Object> mm, T entity) {
    }


    /**
     * 将视图层的vo 转换为 数据实体类.
     *
     * @param vo 视图层vo
     * @return 数据实体.
     */
    protected T voConvertEntity(V vo) {
        return null;
    }

    /**
     * 将数据库实体转换成视图层vo.
     *
     * @param entity 数据库实体.
     * @return 视图层vo.
     */
    protected V entityConvertVo(T entity) {
        return null;
    }

    /**
     * 将视图层的vo 转换为 数据实体类.
     *
     * @param vo 视图层vo
     * @return 数据实体.
     */
    protected List<T> voConvertEntityInList(List<V> vo) {
        return null;
    }

    /**
     * 将数据库实体转换成视图层vo.
     *
     * @param entitys 数据库实体.
     * @return 视图层vo.
     */
    protected List<V> entityConvertVoInList(List<T> entitys) {
        return null;
    }

    /**
     * 新增保存验证实体.
     *
     * @param entity
     */
    protected void validatorEntity4Save(T entity) {
    }

    /**
     * 编辑前验证实体.
     *
     * @param entity
     */
    protected void validatorEntity4Update(T entity) {
    }

    /**
     * 验证实体.
     *
     * @param entity
     */
    protected void validatorEntity(T entity) {
    }

    /**
     * 组件创建跳转之前执行
     *
     * @param request
     * @param response
     * @param mav
     */
    protected void startCreate(HttpServletRequest request, HttpServletResponse response, ModelAndView mav) {

    }

    /**
     * 组件创建跳转之前执行
     *
     * @param request
     * @param response
     * @param mav
     */
    protected void startEdit(HttpServletRequest request, HttpServletResponse response, ModelAndView mav) {

    }

    /**
     * 组件创建跳转之后执行
     *
     * @param request
     * @param response
     * @param mav
     */
    protected void endCreate(HttpServletRequest request, HttpServletResponse response, ModelAndView mav) {

    }

    /**
     * 组件编辑跳转之后执行
     *
     * @param request
     * @param response
     * @param mav
     */
    protected void endEdit(HttpServletRequest request, HttpServletResponse response, ModelAndView mav) {

    }

//    /**
//     * 组件列表跳转之前执行
//     *
//     * @param request
//     * @param response
//     * @param mav
//     */
//    protected void startList(HttpServletRequest request, HttpServletResponse response, ModelAndView mav) {
//
//    }

//    /**
//     * 组件列表跳转之后执行
//     *
//     * @param request
//     * @param response
//     * @param mav
//     */
//    protected void endList(HttpServletRequest request, HttpServletResponse response, ModelAndView mav) {
//
//    }

    /**
     * 显示详细信息之前执行.
     *
     * @param request
     * @param response
     * @param mav
     */
    protected void startShow(HttpServletRequest request, HttpServletResponse response, ModelAndView mav) {

    }


    /**
     * 进入显示列表页面之前执行的方法.
     *
     * @param request
     * @param response
     * @param modelAndView
     */
    protected void startListView(HttpServletRequest request, HttpServletResponse response, ModelAndView modelAndView) {

    }

    /**
     * 进入显示列表页面之后执行的方法.
     *
     * @param request
     * @param response
     * @param modelAndView
     */
    protected void endListView(HttpServletRequest request, HttpServletResponse response, ModelAndView modelAndView) {
    }

    /**
     * 显示详细信息页面之后执行.
     *
     * @param request
     * @param response
     * @param mav
     */
    protected void endShow(HttpServletRequest request, HttpServletResponse response, ModelAndView mav) {

    }

    /**
     * 获得编辑视图名称
     *
     * @return 增加视图名称.
     */
    protected String getListViewName() {
        return processViewPathName(DEFAULT_LIST_VIEW);
    }

    /**
     * 处理请求路径与视图名称.
     *
     * @param viewName 视图名称.
     * @return
     */
    protected String processViewPathName(String viewName) {
        StringBuilder builder = new StringBuilder();
        builder.append(StringUtils.isEmpty(getRequestPath()) ? "" : getRequestPath()).append("/").append(viewName);
        return builder.toString();
    }

    /**
     * 访问请求路径.由具体业务Controller实现,一般是requestMapping中配置的value.
     *
     * @return 访问请求路径.
     */
    protected String getRequestPath() {
        return null;
    }

    /**
     * 获得增加视图名称
     *
     * @return 增加视图名称.
     */
    protected String getCreateViewName() {
        return processViewPathName(DEFAULT_CREATE_VIEW);
    }

    /**
     * 返回视图.如果子类没有实现，则返回默认视图名称.
     *
     * @return 视图名称.
     */
    protected String getResponseView() {
        return DEFAULT_VIEW;
    }

    /**
     * 获得编辑视图名称
     *
     * @return 增加视图名称.
     */
    protected String getEditViewName() {
        return processViewPathName(DEFAULT_EDIT_VIEW);
    }

    /**
     * 获得详细信息视图名称
     *
     * @return 增加视图名称.
     */
    protected String getShowViewName() {
        return processViewPathName(DEFAULT_SHOW_VIEW);
    }

}
