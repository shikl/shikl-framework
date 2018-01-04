package cn.shikl.controller;

import cn.shikl.core.config.Configure;
import cn.shikl.editor.CustomSqlDateEditor;
import cn.shikl.editor.CustomTimestampEditor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.CustomNumberEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.ByteArrayMultipartFileEditor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 基础Controller类.
 *
 * @author libo .
 * @version 1.0
 */
public class ControllerSupport extends AbstractController {

    /**
     * 日志.
     */
    private final Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 配置实例.
     */
    @Autowired
    private Configure configure;

    /**
     * 绑定日期格式.
     *
     * @param binder WebDataBinder
     */
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Integer.class, new CustomNumberEditor(Integer.class, true));
        binder.registerCustomEditor(Double.class, new CustomNumberEditor(Double.class, true));
        binder.registerCustomEditor(MultipartFile.class, new ByteArrayMultipartFileEditor());

        binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true));

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        SimpleDateFormat datetimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        datetimeFormat.setLenient(false);
        binder.registerCustomEditor(java.sql.Date.class, new CustomSqlDateEditor(dateFormat, true));
        binder.registerCustomEditor(java.sql.Timestamp.class, new CustomTimestampEditor(datetimeFormat, true));
    }

    @Override
    protected ModelAndView handleRequestInternal(HttpServletRequest httpServletRequest, HttpServletResponse response) throws Exception {
        setCacheSeconds(0);
        return null;
    }

    /*@ExceptionHandler(Exception.class)
    @ResponseBody
    public void exception(ServletResponse response, Exception e) throws IOException {
        e.printStackTrace();
        logger.error("{}", e);
        handlerEexception(response, e, HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(AuthenticationException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public void authenticationException(ServletResponse response, AuthenticationException e) throws IOException {
        e.printStackTrace();
        logger.error("{}", e);
        handlerEexception(response, e, HttpServletResponse.SC_UNAUTHORIZED);
    }

    @ExceptionHandler(BindException.class)
    @ResponseBody
    public void bindException(ServletResponse response, BindException e) throws IOException {
        e.printStackTrace();
        logger.error("{}", e);
        handlerEexception(response, e, HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
    }


    @ResponseBody
    private void handlerEexception(ServletResponse response, Exception e, int httpStatus) throws IOException {
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        httpResponse.setCharacterEncoding("UTF-8");
        httpResponse.setStatus(httpStatus);
        Messages messages = Messages.failure(e.getMessage());
        messages.setHttpCode(httpStatus);
        String jsonString = JSON.toJSONString(messages);
        httpResponse.getWriter().write(jsonString);
    }*/

    public Configure getConfigure() {
        return configure;
    }
}
