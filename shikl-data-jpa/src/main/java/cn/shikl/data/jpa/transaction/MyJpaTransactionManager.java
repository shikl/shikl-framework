package cn.shikl.data.jpa.transaction;

import cn.shikl.data.jpa.config.DataConfigure;
import cn.shikl.data.jpa.datasource.DynamicDataSourceHolder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.TransactionDefinition;

/**
 *  扩展事务  加入数据库连接切逻辑， 当开启多数据源时有效， 一般指数据库读写分离的场景。
 *  当不存在事务，并且这个事务是只读的时候 那么切换到读数据库。
 *  当不存在事务，并且这个事务是写的时候，切换到写数据库。
 * Created by yangcm on 2017/3/1.
 */
public class MyJpaTransactionManager extends JpaTransactionManager {

    @Autowired
    private DataConfigure dataConfigure;

    @Override
    protected void doBegin(Object transaction, TransactionDefinition definition) {

        String multiEnable = dataConfigure.getProperty("jdbc.multi.enable");
        boolean enable =  Boolean.valueOf(multiEnable).booleanValue();

        if (enable){
            boolean isExistingTransaction = super.isExistingTransaction(transaction);
            if ( (!isExistingTransaction) && definition.isReadOnly()) {
                DynamicDataSourceHolder.setCustomerType(DynamicDataSourceHolder.SLAVE);
            } else if ((!isExistingTransaction) &&  (!definition.isReadOnly()) ){
                DynamicDataSourceHolder.setCustomerType(DynamicDataSourceHolder.MASTER);
            }
        }
        super.doBegin(transaction, definition);
    }
}
