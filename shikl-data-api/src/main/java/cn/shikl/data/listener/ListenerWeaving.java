package cn.shikl.data.listener;

import org.aspectj.lang.JoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * @author shikl <br/>
 *         date:2014-04-23
 * @version 1.0.0
 */
//@Aspect
//@Service
public class ListenerWeaving {

    private final Logger logger = LoggerFactory.getLogger(getClass());

//    @Autowired
    private List<SaveEventListener> saveListeners;

//    @Before(value = "args(object) && @annotation(BeforeSave)")
    public void fireBeforeSave(JoinPoint joinPoint, Object object) {
        logger.debug("{},beforSave with parameter :{}", joinPoint, object);
        if (saveListeners != null) {
            for (SaveEventListener saveListener : saveListeners) {
                saveListener.beforeSave(object);
            }
        }
    }
}
