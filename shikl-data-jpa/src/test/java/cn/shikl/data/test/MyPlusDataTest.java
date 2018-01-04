package cn.shikl.data.test;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import cn.shikl.data.jpa.config.PersistenceJPA2Configuration;

/**
 * Data-Jpa 测试时启动基础类.
 * <p>@author : shikl </p>
 *
 * @version 1.0.0
 */
@Configuration
@ComponentScan(basePackages = "cn.shikl", excludeFilters = @ComponentScan.Filter(Controller.class), includeFilters = @ComponentScan.Filter({Service.class, Component.class, Repository.class,Aspect.class}))
@Import( value = {PersistenceJPA2Configuration.class})
public class MyPlusDataTest {
}
