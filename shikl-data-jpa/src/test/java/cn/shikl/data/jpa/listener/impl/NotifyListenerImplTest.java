package cn.shikl.data.jpa.listener.impl;

import cn.shikl.data.jpa.entity.Dictionary;
import cn.shikl.data.jpa.service.DictionaryService;
import cn.shikl.data.test.MyPlusTestNGTest;

import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

public class NotifyListenerImplTest extends MyPlusTestNGTest {

    @Autowired
    private DictionaryService service;

    @Test
    public void testSave() {
        Dictionary d = new Dictionary();
        d.setId("d1");
        d.setName("dictName1");
        d.setGroup("g1");
        d.setValue("value");
        d.setDefaultValue(false);
        service.save(d);
//        service.save1(d);
//        service.save2();
        //throw new RuntimeException("");
    }
}