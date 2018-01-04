package cn.shikl.data.test;

import org.dbunit.DatabaseUnitException;
import org.dbunit.operation.DatabaseOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import cn.shikl.utils.StringUtils;

import java.io.FileNotFoundException;
import java.sql.SQLException;

/**
 * 测试基类.
 *
 * @author shikl .
 * @version 1.0
 */
@ContextConfiguration(classes = {MyPlusDataTest.class})
public abstract class MyPlusTestNGTest extends AbstractTestNGSpringContextTests {

    /**
     * 测试数据目录.
     */
    private String testDataDir = "/";

    /**
     * 是否初始化数据.
     */
    private boolean initData = true;

    /**
     * 是否清除数据.
     */
    private boolean cleanData = true;
    /**
     * dbunit.
     */
    @Autowired
    private DBUnitTemplate dbtemp;

    /**
     * @param testDataDir the testDataDir to set.
     */
    public void setTestDataDir(String testDataDir) {
        this.testDataDir = testDataDir;
    }

    /**
     * testng初始化数据方法.
     *
     * @param tableNames 表名对应的xml数据.(可以为列表形式).
     */
    @Test(enabled = false)
    public void initDbData(String... tableNames) {
        if (initData) {
            for (String tableName : tableNames) {
                this.initDbData(tableName, DatabaseOperation.INSERT);
            }
        }
    }

    /**
     * 测试初始化方法.
     *
     * @param tableName         表名.
     * @param databaseOperation 操作方式.
     */
    @Test(enabled = false)
    private void initDbData(String tableName, DatabaseOperation databaseOperation) {
        if (StringUtils.isEmpty(tableName)) {
            return;
        }
        try {
            String fileNameL = tableName.toLowerCase();
            StringBuilder sb = new StringBuilder();
            sb.append(testDataDir).append(fileNameL);
            if (!fileNameL.endsWith("xml")) {
                sb.append(".xml");
            }
            String path = getClass().getResource(sb.toString()).getPath();
            dbtemp.execute(path, databaseOperation);
        } catch (DatabaseUnitException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * testng清除数据方法.
     *
     * @param tableNames 表名对应的xml数据.(可以为列表形式).
     */
    @Test(enabled = false)
    public void cleanDbData(String... tableNames) {
        if (cleanData) {
            for (String tableName : tableNames) {
                this.initDbData(tableName, DatabaseOperation.DELETE_ALL);
            }
        }
    }

    public void setCleanData(boolean cleanData) {
        this.cleanData = cleanData;
    }

    public void setInitData(boolean initData) {
        this.initData = initData;
    }
}
