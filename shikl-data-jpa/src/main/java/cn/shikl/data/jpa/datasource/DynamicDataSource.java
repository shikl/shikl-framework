package cn.shikl.data.jpa.datasource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * 根据key 动态路由数据库连接
 * Created by yangcm on 2017/2/28.
 */
public class DynamicDataSource extends AbstractRoutingDataSource {


    @Override
    protected Object determineCurrentLookupKey() {
        return  DynamicDataSourceHolder.getCustomerType();
    }
}
