package cn.shikl.data.jpa.config;

import java.util.Map;

/**
 * 数据配置类.
 * <p>@author : shikl </p>
 *
 * @version 1.0.0
 */
public interface DataConfigure {

    /**
     * 配置扫描数据库实体包路径.
     */
    String ENTITY_SCAN_PACKAGES = "entity.scan.packages";

    String JDBC_DRIVERCLASS = "jdbc.driverClassName";

    String JDBC_URL = "jdbc.url";

    String JDBC_USER = "jdbc.username";

    String JDBC_PASSWORD = "jdbc.password";

    String DIALECT = "hibernate.dialect";

    String SCHEME_AUTO = "scheme.auto";

    String SHOW_SQL = "hibernate.show_sql";

    String FORMAT_SQL = "hibernate.format_sql";
    
    /**
     * 连接池配置.
     *
     * @return
     */
    Map<String, String> getPoolConfig();

    String getDialect();

    String getUser();

    String getPassword();

    String getUrl();

    String getDriverclass();

    String getSchemeAuto();

    String[] getEntityScanPackages();

    String getShowSql();

    String getFormatSql();

    /**
     * 读取配置文件中值.
     *
     * @param key 配置文件key.
     * @return 配置的值.
     */
    String getProperty(String key);

}
