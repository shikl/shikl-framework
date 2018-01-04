package cn.shikl.data.jpa.service.impl;

import cn.shikl.data.criteria.*;
import cn.shikl.data.jpa.criteria.Restrictions;
import cn.shikl.data.jpa.entity.Dictionary;
import cn.shikl.data.jpa.service.DictionaryService;
import cn.shikl.data.test.MyPlusTestNGTest;
import cn.shikl.utils.IDGeneratorUUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

/**
 * 字典表测试类.
 * jpa方式实现.
 * 增删改查.
 *
 * @author libo .
 */
public class DictionaryServiceImplJpaTest extends MyPlusTestNGTest {

    /**
     * 日志.
     */
    private Logger LOG = LoggerFactory.getLogger(DictionaryServiceImplJpaTest.class);
    /**
     * 自动注入字典Service.
     */
    @Autowired
    private DictionaryService dictionaryService;

    /**
     * 查询列表.
     */
    @Test
    public void testDictionaryList() {
        List<Dictionary> dictionaryList = dictionaryService.getDictionaryList();
        Assert.assertNotNull(dictionaryList);
        Assert.assertEquals(dictionaryList.size() < 0, false);
    }

    @Test(dependsOnMethods = {"testSave","testDelete"})
    public void testListPage2() {
        Criteria criteria = dictionaryService.getCriteria(Dictionary.class);
        Pageable pageable = new PageRequest(1, 5);
        criteria.setPageable(pageable);
        Page<Dictionary> dis = dictionaryService.findWithPage(criteria);
        Assert.assertNotNull(dis);
        Assert.assertEquals(1, dis.getNumber());

        Criteria criteria1 = dictionaryService.getCriteria(Dictionary.class);
        Pageable pageable1 = new PageRequest(2, 5);
        criteria1.setPageable(pageable1);
        Page<Dictionary> dis1 = dictionaryService.findWithPage(criteria1);
        Assert.assertNotNull(dis1);
        Assert.assertEquals(2, dis1.getNumber());

//        Assert.assertEquals(12, dis.getTotalElements());

        Assert.assertEquals(3, dis.getTotalPages());

//        Page<Dictionary> dis2 =dictionaryService.findWithPage(criteria);


    }

    @Test
    public void testSave() {
        Dictionary dictionary = new Dictionary();
        String generatorId = IDGeneratorUUID.generatorId();
        dictionary.setId(generatorId);
        dictionary.setName("新增的对象");
        dictionary.setValue("1");
        dictionary.setVersion(1);
        dictionary.setGroup("g");
        dictionary.setDefaultValue(true);
        dictionaryService.save(dictionary);
        Dictionary dictionary2 = dictionaryService.get(Dictionary.class, generatorId);
        Assert.assertNotNull(dictionary2);
    }

    @Test
    public void testUpdate() {
        Dictionary dictionary = dictionaryService.get(Dictionary.class, "4");
        dictionary.setName("修改的对象");
        dictionaryService.merge(dictionary);
    }

    @Test
    public void testDelete() {
        Dictionary dictionary = dictionaryService.get(Dictionary.class, "5");
        Assert.assertNotNull(dictionary);
        dictionaryService.delete(dictionary);
    }

    @Test
    public void testFind() {
        Criteria c = dictionaryService.getCriteria(Dictionary.class);
        c.add(Restrictions.eq("name", "column1"));
        List<Dictionary> list = dictionaryService.find(c);
        Assert.assertNotNull(list);
        Assert.assertEquals(1, list.size());
        for (Dictionary dictionary : list) {
            LOG.info(dictionary.getName());
        }

    }

    @Test
    public void testFindPage() {
        Criteria c = dictionaryService.getCriteria(Dictionary.class);
        PageRequest pr = new PageRequest(1);
        c.setPageable(pr);
        c.add(Restrictions.eq("name", "column1"));
        Page<Dictionary> page = dictionaryService.findWithPage(c);
        Assert.assertNotNull(page);
        Assert.assertEquals(10, page.getSize());
        Assert.assertNotNull(page.getContent());
        Assert.assertEquals(1, page.getContent().size());
        LOG.info(String.valueOf(page.getTotalElements()));
        for (Dictionary dictionary : page.getContent()) {
            LOG.info(dictionary.getName());
        }
    }

    @Test
    public void testFindTopOne() {
        Criteria c = dictionaryService.getCriteria(Dictionary.class);
        c.addOrder(Order.asc("dictId"));
        Dictionary dictionary = dictionaryService.findTopOne(c);
        Assert.assertNotNull(dictionary);
        Assert.assertEquals("1", dictionary.getId());
        LOG.info(dictionary.getName());
    }

    @Test
    public void testFindOnlyOne() {
        Criteria c = dictionaryService.getCriteria(Dictionary.class);
        c.add(Restrictions.eq("name", "column1"));
        Dictionary dictionary = dictionaryService.findOnly(c);
        Assert.assertNotNull(dictionary);
        LOG.info(dictionary.getName());
    }


    @Test
    public void testAndFind() {
        Criteria c = dictionaryService.getCriteria(Dictionary.class);
        c.add(Restrictions.or(Restrictions.eq("id", "6"), Restrictions.and(Restrictions.eq("name", "column10"), Restrictions.eq("value", "10"))));

        List<Dictionary> ds = dictionaryService.find(c);
        LOG.debug("dictionary list:{}",ds);
        Assert.assertNotNull(ds);

//        c.add(Restrictions.or(Restrictions.eq("dictName","")))
    }

    /**
     * 初始化方法.
     */
    @BeforeClass
    public void init() {
        super.setTestDataDir("/data/");
        super.initDbData(Dictionary.class.getSimpleName());
    }

}
