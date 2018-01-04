package cn.shikl.core.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.LinkedList;
import java.util.List;

/**
 * 全局配置实现类.
 * <p>@author : shikl </p>
 *
 * @version 1.0.0
 */
@Component
@PropertySource(value = {"classpath:shikl-config.properties"})
public class ConfigureImpl implements Configure {

    private final Logger logger = LoggerFactory.getLogger(getClass());
    /**
     * 组件扫包默认路径.
     */
    private final String DEFAULT_SCAN_PACKAGES = "cn.shikl";

    private final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd";
    private final String DEFAULT_TIME_FORMAT = "HH:mm:ss";
    private final String DEFAULT_DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    private final String DEFAULT_ENCODING = "UTF-8";
    private final String DEFAULT_PAGE_SIZE = "10";

    /**
     * 系统环境变量.
     */
    @Autowired
    private Environment environment;

    /**
     * @return 系统环境变量.
     */
    public Environment getEnvironment() {
        return environment;
    }

    public List<String> getComponentSacnPackages() {
        String componentPackages = this.getProperty(Configure.COMPONENT_SCAN_PACKAGES);
        List<String> packages = new LinkedList<String>();
        if (componentPackages != null && !"".equals(componentPackages)) {
            String[] _ps = componentPackages.split(" ");
            for (String _s : _ps) {
                packages.add(_s);
            }
        } else {
            packages.add(DEFAULT_SCAN_PACKAGES);
        }
        return packages;
    }


    public String getProperty(String key, String defaultValue) {
        if (StringUtils.isEmpty(key)) {
            throw new IllegalArgumentException("读取配置文件key为空.");
        }
        if (environment.containsProperty(key)) {
            return environment.getProperty(key);
        } else {
            return defaultValue;
        }
    }

    public String getProperty(String key) {
        if (StringUtils.isEmpty(key)) {
            throw new IllegalArgumentException("读取配置文件key为空.");
        }
        if (environment.containsProperty(key)) {
            return environment.getProperty(key);
        }
        logger.warn("在配置中没有发现 key 为 {} 的配置项.", key);
        return null;
    }

    public String getDateFormat() {
        return getProperty(Configure.DATE_FORMAT, DEFAULT_DATE_FORMAT);
    }

    public String getTimeFormat() {
        return getProperty(Configure.TIME_FORMAT, DEFAULT_TIME_FORMAT);
    }

    @Override
    public String getDateTimeFormat() {
        return getProperty(Configure.DATE_TIME_FORMAT, DEFAULT_DATE_TIME_FORMAT);
    }

    @Override
    public int getPageSize() {
        String _pageSize = getProperty(Configure.PAGE_SIZE, DEFAULT_PAGE_SIZE);
        return StringUtils.isEmpty(_pageSize) ? 0 : Integer.valueOf(_pageSize);
    }

    public boolean containsProperty(String key) {
        return this.environment.containsProperty(key);
    }

    @Override
    public String getEncoding() {
        return getProperty(ENCODING, DEFAULT_ENCODING);
    }
}
