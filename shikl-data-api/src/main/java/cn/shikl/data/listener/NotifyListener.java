package cn.shikl.data.listener;

import org.aspectj.lang.JoinPoint;
/**
 * 
 * @author shikl
 *
 */
public interface NotifyListener {

    void handlerNotify(JoinPoint joinPoint);

    void handlerNotify(JoinPoint joinPoint, Object object);
}
