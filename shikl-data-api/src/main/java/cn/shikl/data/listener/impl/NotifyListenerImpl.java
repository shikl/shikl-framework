package cn.shikl.data.listener.impl;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import cn.shikl.data.listener.NotifyListener;

/**
 * @author shikl <br/>
 *         date:2017-04-10
 * @version 1.0.0
 */
@Service
@Aspect
public class NotifyListenerImpl implements NotifyListener {

    private final Logger logger = LoggerFactory.getLogger(getClass());


    @Pointcut(value = "execution(void cn.shikl.data.save()) OR (execution(* cn.shikl.data.dao.save.saveOrUpdate(..)))")
    private void beforPoint() {
        logger.debug("this is in before Point...");
    }

    @Pointcut(value = "")
    public void afterPoint() {
        logger.debug("this is in after point ...");
    }

    @Override
//    @Before(value = "execution(void cn.com.rexen.rplus.core.service.DictionaryService.save2())")    //正确可运行
    @Before(value = "@annotation(BeforeSave)")       //ok
   public void handlerNotify(JoinPoint joinPoint) {

    }

    @Override
    @Before("(execution(* cn.shikl.data.dao.save(..))) OR (execution(* cn.shikl.data.dao.save.saveOrUpdate(..)))")
//    @After(value = "beforPoint()")
    @After(value = "afterPoint()")
    //@After(value = "notify")
//    @Before(value = "args(object) && execution(void cn.com.rexen.rplus.core.service.DictionaryService.save1(*))") //ok
//    @Before(value = "args(object) && @annotation(BeforeSave)") //ok
    public void handlerNotify(JoinPoint joinPoint,Object object) {
//        System.out.println("cn.com.rexen.NotifyListener hahhahhaha run. @" + Calendar.getInstance().getTime());
        logger.debug("this is handlerNotiy with paramerter {} at {}",object, joinPoint);
    }
}
