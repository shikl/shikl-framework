package cn.shikl.data.jpa.listener;

import cn.shikl.data.jpa.entity.Dictionary;
import cn.shikl.data.listener.SaveEventListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @author shikl <br/>
 *         date:2014-04-23
 * @version 1.0.0
 */
@Service
public class DictionarySaveEventListenerImpl implements SaveEventListener<Dictionary> {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void beforeSave(Dictionary dictionary) {
        logger.debug("DictionarySaveEventListenerImpl.beforeSave ,dictionar:{}",dictionary);
    }

    @Override
    public void afterSave(Dictionary dictionary) {
        logger.debug("DictionarySaveEventListenerImpl.afterSave ,dictionar:{}",dictionary);
    }
}
