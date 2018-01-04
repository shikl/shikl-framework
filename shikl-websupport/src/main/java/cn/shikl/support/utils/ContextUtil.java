package cn.shikl.support.utils;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by shikl on 2017/2/10.
 */
public class ContextUtil {

    public static <T> T getBean(Class<T> clazz) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        return  WebApplicationContextUtils.getWebApplicationContext(request.getServletContext()).getBean(clazz);
    }
}
