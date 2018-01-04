package cn.shikl.data.jpa.service;

import cn.shikl.data.jpa.entity.IdGenerator;
import cn.shikl.data.service.IService;

import java.util.Date;

/**
 * <p>
 * 编号生成服务接口.
 * </p>
 *
 * @author shikl.
 */
public interface IdGeneratorService extends IService<IdGenerator> {

    /**
     * spring bean 名称.
     */
    String BEAN_NAME = "idGeneratorService";
    /**
     * 编号前缀.
     */
    String PREFIX = "0";
    /**
     * 根据日期顺序生成编号.
     */
    int GENERATOR_BY_DATE = 2;

    /**
     * 根据自然顺序生成编号.
     */
    int GENERATOR_BY_SEQUENCE = 1;

    /**
     * 手动输入编号,返回空值.
     */
    int GENERATOR_BY_MANUAL = 0;

    /**
     * 根据自然顺序生成编号.
     *
     * @param tableName 表名.
     * @return 顺序号.
     */
    String generateIdBySequence(String tableName);

    /**
     * 根据当前日期生成编号.
     *
     * @param tableName 表名.
     * @return yyyyMMdd+顺序号
     */
    String generateIdByDate(String tableName);

    /**
     * 根据指定日期生成编号.
     *
     * @param date      日期
     * @param tableName 表名
     * @return 生成的编号.
     */
    String generateIdByDate(Date date, String tableName);

    /**
     * 根据表名和辅助值生成编号.
     *
     * @param tableName 表名.
     * @param assValue  辅助值.
     * @return 生成的编号.
     */
    String generateIdByAssValue(String tableName, String assValue);

    /**
     * 根据表名和辅助值生成编号.
     *
     * @param tableName 表名
     * @param assValue  辅助值.
     * @param len       长度.
     * @return 辅助值+顺序号.
     */
    String generateIdAssValueAndSequence(String tableName, String assValue, int len);
}
