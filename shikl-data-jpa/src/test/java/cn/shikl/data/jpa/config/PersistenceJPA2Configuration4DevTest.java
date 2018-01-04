package cn.shikl.data.jpa.config;

import cn.shikl.data.jpa.config.PersistenceJPA2Configuration;
import cn.shikl.data.test.MyPlusTestNGTest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * <p>@author : shikl </p>
 *
 * @version 1.0.0
 */
@ContextConfiguration(classes = {PersistenceJPA2Configuration.class})
//@ComponentScan(basePackages = "cn.myplus", excludeFilters = @ComponentScan.Filter(Controller.class), includeFilters = @ComponentScan.Filter({Service.class, Component.class, Repository.class}))
public class PersistenceJPA2Configuration4DevTest extends MyPlusTestNGTest {

    @Autowired
    private PersistenceJPA2Configuration persistenceJPA2Configuration;

    @Test
    public void testGetDbConfig() {
        Assert.assertNotNull(persistenceJPA2Configuration);
    }
}