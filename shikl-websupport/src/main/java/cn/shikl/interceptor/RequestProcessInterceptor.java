package cn.shikl.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 处理Request相关操作。
 * <p>@author : shikl </p>
 * @version 1.0.0
 */

public class RequestProcessInterceptor extends HandlerInterceptorAdapter {

    private Logger logger = LoggerFactory.getLogger(getClass());

    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        //logger.debug("RequestProcessInterceptor.preHandle @ {},{}", httpServletRequest.getRequestURL(),o);
        long startTime = System.currentTimeMillis();
        httpServletRequest.setAttribute("startTime", startTime);
        return true;
    }

    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        long startTime = (Long)httpServletRequest.getAttribute("startTime");
        long endTime = System.currentTimeMillis();
        long executeTime = endTime - startTime;

        logger.info("RequestProcessInterceptor.postHandle.@ {}, {}", httpServletRequest.getRequestURI(), executeTime);
        //logger.debug("RequestProcessInterceptor.postHandle.@ {}", httpServletRequest.getRequestURI());
        //拦截处理每次请求的requestPath.
//        if (o instanceof HandlerMethod) {
//            HandlerMethod handlerMethod = (HandlerMethod) o;
//            Object object = handlerMethod.getBean();
//            if (object instanceof CURDFormControllerSupport) {
//                CURDFormControllerSupport controller = (CURDFormControllerSupport) object;
//            RequestMapping annotation = object.getClass().getAnnotation(RequestMapping.class);
//            if (annotation != null) {
//                String[] mappingValue = annotation.value();
//                modelAndView.addObject("requestPath", httpServletRequest.getContextPath() + mappingValue[0]);
//            }

//            }
//        }
    }

    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
//        logger.debug("RequestProcessInterceptor.afterCompletion @ {}", httpServletRequest.getRequestURI());
    }
}
