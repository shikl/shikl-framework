package cn.shikl.data.listener.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.shikl.data.listener.SaveEventListener;

/**
 * 保存事件监听方式实现.
 *
 * @author shikl <br/>
 *         date:2017-04-23
 * @version 1.0.0
 */
//@Service
public class SaveListenerImpl implements SaveEventListener {

    /**
     * logger.
     */
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void beforeSave(Object t) {
        logger.debug("this is saveListerImpl {}", t);
    }

    @Override
    public void afterSave(Object t) {

    }
}
