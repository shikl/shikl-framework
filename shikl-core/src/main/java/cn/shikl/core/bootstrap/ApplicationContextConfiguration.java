package cn.shikl.core.bootstrap;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Spring 核心启动类.在web.xml 或 web 启动类中进行调用.
 *
 * @author : shikl
 * @version 1.0
 * @date:2016-10-17
 */
@Configuration
@ComponentScan(basePackages = {"cn.shikl"})
public class ApplicationContextConfiguration {
	
    /*@Inject
    private Environment environment;*/
}
