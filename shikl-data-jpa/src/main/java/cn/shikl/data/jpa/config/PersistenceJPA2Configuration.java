package cn.shikl.data.jpa.config;

import cn.shikl.data.jpa.datasource.DynamicDataSource;
import cn.shikl.data.jpa.datasource.DynamicDataSourceHolder;
import cn.shikl.data.jpa.transaction.MyJpaTransactionManager;
import cn.shikl.utils.StringUtils;

import com.alibaba.druid.pool.DruidDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Description;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.core.io.ClassPathResource;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.Resource;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * JPA配置启动类.从配置文件中加载相关配置，并启动JPA容器.
 *
 * @author libo
 * @version 1.0
 */
@Configuration
@EnableTransactionManagement
@EnableAspectJAutoProxy
public class PersistenceJPA2Configuration {

    /**
     * logger.
     */
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    private DataConfigure dataConfigure;

//    @Value("${init-db:false}")
//    private String initDatabase;

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource());
        em.setPackagesToScan(dataConfigure.getEntityScanPackages());
        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        em.setJpaProperties(additionalProperties());
//        em.afterPropertiesSet();
        //TODO 引入hibernate.propreties
        return em;
    }

    @Bean
    @Description("配置数据源")
    public DataSource dataSource() {
        //TODO 增加引入的配置文件
        String multiEnable = dataConfigure.getProperty("jdbc.multi.enable");

        boolean enable = false;
        if (org.apache.commons.lang3.StringUtils.isNotBlank(multiEnable)){
            enable =  Boolean.valueOf(multiEnable).booleanValue();
        }

        if (enable){
            DynamicDataSource dynamicDataSource =  new DynamicDataSource();

            //处理 master 为写库， slave 为读库
            int masterDataNode = Integer.parseInt(dataConfigure.getProperty("jdbc.multi.master.datanode"));
            int slaveDataNode = Integer.parseInt(dataConfigure.getProperty("jdbc.multi.slave.datanode"));
            DynamicDataSourceHolder.setMasterNum(masterDataNode);
            DynamicDataSourceHolder.setSlaveNum(slaveDataNode);

            Map targetDataSources = new HashMap();
            for(int i = 0; i < masterDataNode; i++){
                String perfix = "master" + i ;
                DruidDataSource dataSource = new DruidDataSource();
                dataSource.setUrl(dataConfigure.getProperty("jdbc.multi."+perfix+".url"));
                dataSource.setUsername(dataConfigure.getProperty("jdbc.multi."+perfix+".username"));
                dataSource.setPassword(dataConfigure.getProperty("jdbc.multi."+perfix+".password"));
                dataSource.setDriverClassName(dataConfigure.getDriverclass());
                initDataSourceConfig(dataSource);
                targetDataSources.put("master" + (i+1) +"", dataSource);
                if (0 == i){
                    dynamicDataSource.setDefaultTargetDataSource(dataSource);
                }
            }
            for(int i=0; i < slaveDataNode; i++){
                String perfix = "slave" + i ;
                DruidDataSource dataSource = new DruidDataSource();
                dataSource.setUrl(dataConfigure.getProperty("jdbc.multi."+perfix+".url"));
                dataSource.setUsername(dataConfigure.getProperty("jdbc.multi."+perfix+".username"));
                dataSource.setPassword(dataConfigure.getProperty("jdbc.multi."+perfix+".password"));
                dataSource.setDriverClassName(dataConfigure.getDriverclass());
                initDataSourceConfig(dataSource);
                targetDataSources.put("slave" + (i+1) +"", dataSource);
            }
            dynamicDataSource.setTargetDataSources(targetDataSources);

            return dynamicDataSource;
        }else{
            DruidDataSource dataSource = new DruidDataSource();
            dataSource.setDriverClassName(dataConfigure.getDriverclass());
            dataSource.setUrl(dataConfigure.getUrl());
            dataSource.setUsername(dataConfigure.getUser());
            dataSource.setPassword(dataConfigure.getPassword());
            initDataSourceConfig(dataSource);
            return dataSource;
        }
    }

    @Bean
    public JdbcTemplate jdbcTemplate() {
        JdbcTemplate template = new JdbcTemplate(dataSource());
        return template;
    }

    /**
     * 初始化数据连接池配置
     *
     * @param dataSource
     */
    private void initDataSourceConfig(DruidDataSource dataSource) {
        String initialSize = dataConfigure.getProperty("pool.initialSize");
        String minIdle = dataConfigure.getProperty("pool.minIdle");
        String maxActive = dataConfigure.getProperty("pool.maxActive");
        String maxWait = dataConfigure.getProperty("pool.maxWait");

        if (StringUtils.isNotEmpty(initialSize)) {
            dataSource.setMaxActive(Integer.valueOf(initialSize));
        }

        if (StringUtils.isNotEmpty(minIdle)) {
            dataSource.setMinIdle(Integer.valueOf(minIdle));
        }
        if (StringUtils.isNotEmpty(maxActive)) {
            dataSource.setMaxActive(Integer.valueOf(maxActive));
        }
        if (StringUtils.isNotEmpty(maxWait)) {
            dataSource.setMaxWait(Long.valueOf(maxWait));
        }

        //mysql
        dataSource.setPoolPreparedStatements(false);
    }

    Properties additionalProperties() {
        Properties properties = new Properties();
        properties.setProperty("hibernate.hbm2ddl.auto", dataConfigure.getSchemeAuto());
        properties.setProperty("hibernate.dialect", dataConfigure.getDialect());
        String show_sql = dataConfigure.getShowSql();
        if (StringUtils.isNotEmpty(show_sql)) {
            properties.setProperty("hibernate.show_sql", dataConfigure.getShowSql());
        }
        String format_sql = dataConfigure.getFormatSql();
        if (StringUtils.isNotEmpty(format_sql)) {
            properties.setProperty("hibernate.format_sql", dataConfigure.getFormatSql());
        }
//        properties.setProperty("hibernate.archive.autodetection", "class");
        return properties;
    }

    //    @Bean
    public DataSourceInitializer dataSourceInitializer(DataSource dataSource) {
        DataSourceInitializer dataSourceInitializer = new DataSourceInitializer();
        dataSourceInitializer.setDataSource(dataSource);
        ResourceDatabasePopulator databasePopulator = new ResourceDatabasePopulator();
        databasePopulator.addScript(new ClassPathResource("initDataBase.sql"));
        dataSourceInitializer.setDatabasePopulator(databasePopulator);
//        dataSourceInitializer.setEnabled(Boolean.parseBoolean(initDatabase));
        return dataSourceInitializer;
    }

    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory emf) {
        JpaTransactionManager transactionManager = new MyJpaTransactionManager();
        transactionManager.setEntityManagerFactory(emf);
        return transactionManager;
    }

    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
        return new PersistenceExceptionTranslationPostProcessor();
    }
}
