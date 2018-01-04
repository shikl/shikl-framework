package cn.shikl.core.config;

import org.springframework.core.env.Environment;

import java.util.List;

/**
 * 全局配置实现类.可以读取global-defal.properties配置文件中的配置项.
 * <p>@author : shikl </p>
 *
 * @version 1.0.0
 */
public interface Configure {

    /**
     * 组件加载时扫描包路径.
     */
    final String COMPONENT_SCAN_PACKAGES = "component-scan-packages";

    /**
     * 默认日期格式.
     */
    final String DATE_FORMAT = "date-format";

    /**
     * 默认时间格式.
     */
    final String TIME_FORMAT = "time-format";

    /**
     * 默认日期时间格式.
     */
    final String DATE_TIME_FORMAT = "date_time-format";

    /**
     * 数据列表时默认每页显示记录数.
     */
    final String PAGE_SIZE = "page-size";

    /**
     * 字符编码格式配置项.
     */
    final String ENCODING = "encoding";

    /**
     * 运行环境.
     *
     * @return 运行环境变量.
     */
    public Environment getEnvironment();

    /**
     * 组件扫包配置.
     *
     * @return 组件扫包的配置路径.
     */
    List<String> getComponentSacnPackages();

    /**
     * 读取配置文件中值.
     *
     * @param key 配置文件key.
     * @return 配置的值.
     */
    String getProperty(String key);

    /**
     * 默认日期格式.
     *
     * @return 默认日期格式.
     */
    String getDateFormat();

    /**
     * 默认时间格式.
     *
     * @return 默认时间格式.
     */
    String getTimeFormat();

    /**
     * 默认日期时间格式.
     *
     * @return
     */
    String getDateTimeFormat();

    /**
     * 列表显示时默认显示记录数.如果没有配置此项参数，默认显示10条记录.
     *
     * @return 默认记录数.
     */
    int getPageSize();

    /**
     * 判断是否存在配置项.
     *
     * @param key
     * @return
     */
    boolean containsProperty(String key);

    /**
     * 读取配置文件中值.如果配置文件中没该项配置,则使用默认值.
     *
     * @param key          配置文件key.
     * @param defaultValue 默认值.
     * @return 配置的值.
     */
    String getProperty(String key, String defaultValue);

    /**
     * @return 默认的字符编码格式.
     */
    String getEncoding();
}
