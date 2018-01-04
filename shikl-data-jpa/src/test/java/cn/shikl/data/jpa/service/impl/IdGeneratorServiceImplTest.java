package cn.shikl.data.jpa.service.impl;

import cn.shikl.data.jpa.service.IdGeneratorService;
import cn.shikl.data.test.MyPlusTestNGTest;

import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * 主键生成service测试.
 *
 * @author libo .
 */
public class IdGeneratorServiceImplTest extends MyPlusTestNGTest {

    /**
     * 主键生成service.
     */
    @Autowired
    private IdGeneratorService service;

    //
    //	@Test(dependsOnMethods="updateTest")
    //	public void deleteTest() {
    //
    //		IdGenerator idg = new IdGenerator();
    //		idg.setId("99990");
    //		idg.setLen(4);
    //		idg.setTableName("test");
    //		idg.setValue(1);
    //		service.delete(idg);
    //
    //	}
    //
    //	@Test
    //	public void testQuery() {
    //		// criteriaFactory 已经声明为Spring Bean.
    //		// String sql = "from " + IdGenerator.class.getName()
    //		// + " where id > ? and id < ?";
    ////		CriteriaQueryImpl cqi = criteriaFactory
    ////				.getCriteriaQuery(IdGenerator.class);
    ////		// cqi.setWhereSql("id > ?");
    ////		// cqi.addParameter("100");
    ////		// cqi.addParameter("110");
    ////		// cqi.setSql(sql);
    ////		cqi.addOrderDesc("tableName");
    ////		cqi.addGroup("tableName");
    ////		List<?> list = service.query(cqi);
    ////		System.out.println("list =" + list);
    ////		for (int i = 0; i < list.size(); i++) {
    ////			IdGenerator idg = (IdGenerator) list.get(i);
    ////			System.out.println(idg.getId() + "," + idg.getTableName());
    ////		}
    //	}

    /**
     * 测试修改.
     */
    @Test
    public void testUpdate() {
        //        IdGenerator idg = new IdGenerator();
        //        idg.setId("99990");
        //        idg.setLen(3);
        //        idg.setTableName("test");
        //        idg.setValue(2);
        //        service.saveOrUpdate(idg);
    }

    @Test
    public void generateIdAssValueAndSequence() {
        String sequence = service.generateIdAssValueAndSequence("dictionary", "1", 6);
        Assert.assertNotNull(sequence, "生成ID为空.");
        Assert.assertNotNull(sequence);
    }

    @Test
    public void generateIdByAssValue() {
        String sequence = service.generateIdByAssValue("dictionary", "1");
        Assert.assertNotNull(sequence);
    }

    @Test
    public void generateIdByDateString() {
    }

    @BeforeClass
    public void init() {
        setTestDataDir("/data/");
        super.initDbData("idGenerator");
    }

//    @BeforeTest
    public void beforeTest() {
        Assert.assertNotNull(service, "IdGeneratorService is null");
    }

    @Test
    public void generateIdByDateDateString() {
    }

    @Test
    public void generateIdBySequence() {
    }

}
