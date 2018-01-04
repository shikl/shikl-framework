package cn.shikl.data.jpa.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import cn.shikl.utils.StringUtils;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * <p>@author : shikl </p>
 *
 * @version 1.0.0
 */
@Service
@PropertySource(value = {"classpath:datasource.properties"})
public class DataConfigureImpl implements DataConfigure {

    final String DEFAULT_SCAN_PACKAGES = "cn.shikl";
    /**
     * 系统环境变量.
     */
    @Autowired
    private Environment environment;

    @Override
    public Map<String, String> getPoolConfig() {
        return null;
    }

    @Override
    public String getDialect() {
        return getProperty(DIALECT);
    }

    @Override
    public String getUser() {
        return getProperty(JDBC_USER);
    }

    @Override
    public String getPassword() {
        return getProperty(JDBC_PASSWORD);
    }

    @Override
    public String getUrl() {
        return getProperty(JDBC_URL);
    }

    @Override
    public String getDriverclass() {
        return getProperty(JDBC_DRIVERCLASS);
    }

    @Override
    public String getSchemeAuto() {
        return getProperty(SCHEME_AUTO);
    }

    @Override
    public String[] getEntityScanPackages() {

        String scan_packages = getProperty(ENTITY_SCAN_PACKAGES);
        List<String> packages = new LinkedList<String>();
        packages.add(DEFAULT_SCAN_PACKAGES);
        if (scan_packages != null && !"".equals(scan_packages)) {
            String[] _ps = scan_packages.split(" ");
            for (String _s : _ps) {
                packages.add(_s);
            }
        }
        String[] scanPackages = new String[packages.size()];
        packages.toArray(scanPackages);

        return scanPackages;
    }

    @Override
    public String getShowSql() {
        return getProperty(SHOW_SQL);
    }

    @Override
    public String getFormatSql() {
        return getProperty(FORMAT_SQL);
    }

    @Override
    public String getProperty(String key) {
        if (StringUtils.isEmpty(key)) {
            throw new IllegalArgumentException("获取属性key 为空.");
        }

        return environment.getProperty(key, "");
    }

}
