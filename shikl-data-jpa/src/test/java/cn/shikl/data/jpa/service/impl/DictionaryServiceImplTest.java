package cn.shikl.data.jpa.service.impl;


import cn.shikl.data.criteria.*;
import cn.shikl.data.jpa.criteria.Restrictions;
import cn.shikl.data.jpa.entity.Dictionary;
import cn.shikl.data.jpa.service.DictionaryService;
import cn.shikl.data.test.MyPlusTestNGTest;

import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

/**
 * 字典表测试类.
 *
 * @author shikl .
 */
public class DictionaryServiceImplTest extends MyPlusTestNGTest {

    /**
     * 自动注入字典Service.
     */
    @Autowired
    private DictionaryService dictionaryService;

    //    @BeforeTest
    public void beforeTest() {
        Assert.assertNotNull(dictionaryService, "dictionaryService is null");
    }

    /**
     * 查询列表.
     */
    @Test
    public void testDictionaryList() {
        Pageable page = new PageRequest(1);
        Criteria criteria = dictionaryService.getCriteria(Dictionary.class);

        criteria.setPageable(page);
        Page<Dictionary> result = dictionaryService.findWithPage(criteria);
        Assert.assertEquals(result.getContent().size(), 10);
        //        Assert.assertEquals(result.getTotalElements(), 12);
    }

    /**
     * 查询列表排序.
     */
    @Test
    public void testDictionaryListSort() {
        Criteria criteria = dictionaryService.getCriteria(Dictionary.class);
        criteria.addOrder(Order.desc("name"), Order.asc("id"));
        //        criteria.addOrder(new Order(Direction.DESC, "name")).addOrder(new Order(Direction.ASC,"id"));
        Pageable pageable = new PageRequest(1);
        criteria.setPageable(pageable);
        Page<Dictionary> page = dictionaryService.findWithPage(criteria);
        List<Dictionary> result = page.getContent();
        Assert.assertNotNull(result);
    }

    /**
     * .
     */
    @Test
    public void testDirection() {
        //        Order order = new Order(Direction.ASC, "value");
        //        boolean b = cn.com.rexen.rplus.core.criteria.Order.Direction.ASC.toString().equals(order.getDirection().name());
        //        Assert.assertEquals(true, b);
        Order order = Order.asc("value");
        boolean b = Order.Direction.ASC.toString().equals(order.getDirection().name());
        Assert.assertEquals(true, b);
    }

    /**
     * 字典表查询.
     */
    @Test
    public void testDirectionList() {
        Criteria criteria = dictionaryService.getCriteria(Dictionary.class);
        List<Dictionary> list = dictionaryService.find(criteria);
        Assert.assertNotNull(list);
    }

    /**
     * 测试离线查询分页.
     */
    @Test
    public void testDirectionCriteriaPage() {
        Pageable page = new PageRequest(1);
        Criteria criteria = dictionaryService.getCriteria(Dictionary.class);
        criteria.add(Restrictions.or(Restrictions.eq("name", "column1"), Restrictions.eq("name", "column2")));
        criteria.setPageable(page);
        Page<Dictionary> pageList = dictionaryService.findWithPage(criteria);
        Assert.assertNotNull(pageList);
        Assert.assertEquals(pageList.getSize(), 10);
//        Assert.assertEquals(pageList.getTotalPages(), 2);
    }

    /**
     * 测试分页.
     */
    @Test
    public void testDirectionHqlPage() {
        List<Dictionary> list = dictionaryService.getDictionaryList();
        Assert.assertNotNull(list);

    }

    @Test
    public void testCumterDao() {
        List<Dictionary> dictionaryList = dictionaryService.getDictionaryList();
    }

    @Test
    public void testDelete() {
        Dictionary dictionary = dictionaryService.get(Dictionary.class, "9");
        dictionaryService.delete(dictionary);
        Dictionary dictionary2 = dictionaryService.get(Dictionary.class, "9");
        Assert.assertNull(dictionary2);
    }

    @Test
    public void testUpdate() {
        Dictionary dictionary = dictionaryService.get(Dictionary.class, "1");
        dictionary.setName("column_update");
        dictionaryService.saveOrUpdate(dictionary);
        Dictionary dictionary2 = dictionaryService.get(Dictionary.class, "1");
        Assert.assertEquals(dictionary2.getName(), "column_update");
    }

    @Test
    public void testSave() {
        String generateId = dictionaryService.generateId();
        Dictionary dictionary = new Dictionary();
        dictionary.setId(generateId);
        dictionary.setName("testDictionary");
        dictionary.setGroup("group");
        dictionaryService.save(dictionary);
    }

    @Test
    public void testTopOne() {
        Criteria criteria = dictionaryService.getCriteria(Dictionary.class);
        Dictionary dictionary = (Dictionary) dictionaryService.findTopOne(criteria);
        Assert.assertNotNull(dictionary);
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
