package cn.shikl.data.jpa.datasource;

import java.util.Random;

/**
 * 向线程设置和获取数据库标识符
 * Created by shikl on 2017/2/28.
 */
public class DynamicDataSourceHolder {

    public static final String MASTER = "master";
    public static final String SLAVE = "slave";

    public static int masterNum = 0;
    public static int slaveNum = 0;

    private static final ThreadLocal<String> contextHolder = new ThreadLocal<String>();

    public static void setCustomerType(String customerType) {

        if (MASTER.equals(customerType)){
            customerType = MASTER + getRandom( masterNum , 1);
        }
        if (SLAVE.equals(customerType)){
            customerType = SLAVE + getRandom( slaveNum , 1);
        }

        contextHolder.set(customerType);
    }
    public static String getCustomerType() {
        return contextHolder.get();
    }
    public static void clearCustomerType() {
        contextHolder.remove();
    }

    private static int getRandom( int max , int min ){
        Random random = new Random();
        int s = random.nextInt(max)%(max-min+1) + 1;
        return s;
    }

    public static int getMasterNum() {
        return masterNum;
    }

    public static void setMasterNum(int masterNum) {
        DynamicDataSourceHolder.masterNum = masterNum;
    }

    public static int getSlaveNum() {
        return slaveNum;
    }

    public static void setSlaveNum(int slaveNum) {
        DynamicDataSourceHolder.slaveNum = slaveNum;
    }
}
