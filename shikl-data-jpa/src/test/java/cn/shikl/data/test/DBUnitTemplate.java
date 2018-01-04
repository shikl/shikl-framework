/**
 * 
 */
package cn.shikl.data.test;

import org.dbunit.DatabaseUnitException;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.SQLException;

/**
 * dbunit连接类.
 * 
 * @author shikl
 * @version v1.0 2014-3-21
 */
@Service
public class DBUnitTemplate {
    /**
     * 从spring中获取数据源.
     */
    @Resource
    private DataSource dataSource;
    /**
     * 数据连接.
     */
    private IDatabaseConnection connection;

    /**
     * 构造方法中创建连接.
     * 
     * @param dds
     *            .
     * @throws DatabaseUnitException .
     * @throws SQLException .
     */
    //    public DBUnitTemplate(DriverManagerDataSource dds) throws DatabaseUnitException, SQLException {
    //        this.dataSource = dds;
    //        connection = new DatabaseConnection(this.dataSource.getConnection());
    //        //        connection.getConnection().prepareStatement("set @@session.foreign_key_checks = 0").execute();
    //    }

    /**
     * 执行文件中的初始化数据.
     * 
     * @param path
     *            文件路径.
     * @param oper
     *            操作.
     * @throws FileNotFoundException .
     * @throws DatabaseUnitException .
     * @throws SQLException .
     */
    public void execute(String path, DatabaseOperation oper) throws FileNotFoundException, DatabaseUnitException,
            SQLException {
        IDataSet dataset = new FlatXmlDataSetBuilder().build(new FileInputStream(path));
        connection = new DatabaseConnection(this.dataSource.getConnection());
        oper.execute(connection, dataset);
        connection.close();
    }
}
