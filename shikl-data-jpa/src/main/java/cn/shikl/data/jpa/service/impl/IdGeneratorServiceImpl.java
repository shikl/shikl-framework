package cn.shikl.data.jpa.service.impl;

import cn.shikl.data.criteria.Criteria;
import cn.shikl.data.jpa.criteria.Restrictions;
import cn.shikl.data.jpa.entity.IdGenerator;
import cn.shikl.data.jpa.service.IdGeneratorService;
import cn.shikl.utils.DateUtils;
import cn.shikl.utils.IDGeneratorUUID;
import cn.shikl.utils.StringUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.Date;

/**
 * 编号生成服务实现类.
 * <p/>
 * <pre>
 * 可以根据数据库表配置按一定规则生成顺序号。<br/>
 * <li>
 * 按自然顺序生成，固定长度，不足位数前补0，如：01,02.
 * </li>
 * <li>
 * 按日期+序号的方式生成编号.如:20100101001,20100101002.
 * </li>
 *
 * 需建表:idGenerator
 *
 * 下面msyql下的建表语句.
 * {@code
 * create table idGenerator (
 * autoId int(11) not null auto_increment,
 * tableName varchar(20) not null,
 * NextValue int(11) default 0,
 * assValue varchar(20),
 * version int(10) default 0,
 * primary key (autoId)
 * );
 * }
 * </pre>
 *
 * @author libo
 */
@Service(value = IdGeneratorService.BEAN_NAME)
@Transactional(readOnly = true)
public class IdGeneratorServiceImpl extends BaseServiceImpl<IdGenerator> implements IdGeneratorService {
    /**
     * idGenerator表中tableName字段名.
     */
    private static final String TABLENAME = "tableName";
    /**
     * 日志.
     */
    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final int DEFAULT_LEN = 10;

    @Override
    @Transactional(readOnly = false)
    public String generateIdBySequence(String tableName) {
        logger.debug("generatorIdBySequence ,TableName = {}", tableName);
        if (StringUtils.isEmpty(tableName)) {
            throw new IllegalArgumentException("生成编号时表名为空.");
        }
        IdGenerator idGenerator = currentIdGenerator(tableName, null);
        String id = saveGenerator(idGenerator);
        logger.debug("generator id is : {}", id);
        return id;
    }

    /**
     * 查询当前表名的id生成器。如果不存在记录则创建一个新的实例.
     *
     * @param tableName 表名.
     * @param assValue  辅助值.
     * @return 当前对象.
     */
    private IdGenerator currentIdGenerator(String tableName, String assValue) {
        return currentIdGenerator(tableName, assValue, DEFAULT_LEN);
    }

    private IdGenerator currentIdGenerator(String tableName, String assValue, int len) {
        Criteria c = getCriteriaFactory().getCriteria(IdGenerator.class);
        c.add(Restrictions.eq(TABLENAME, tableName));
        if (StringUtils.isNotEmpty(assValue)) {
            c.add(Restrictions.eq("assValue", assValue));
        }
        IdGenerator idGenerator = null;
        try {
            idGenerator = getDao().findTopOne(c);
        } catch (RuntimeException e) {
            logger.info("查询 {} ID生成器为空，将创建新的ID生成器.", tableName);
            idGenerator = new IdGenerator();
            idGenerator.setId(IDGeneratorUUID.generatorId());
            idGenerator.setTableName(tableName);
            idGenerator.setAssValue(assValue);
            idGenerator.setLen(len);
            idGenerator.setValue(1);
        }
        return idGenerator;
    }

    /**
     * 保存生成器并生成当前生成的ID.
     *
     * @param idGenerator
     * @return
     */
    private String saveGenerator(IdGenerator idGenerator) {
        int value = idGenerator.getValue();
        int valueLen = String.valueOf(value).length();
        StringBuilder buffer = new StringBuilder();
        if (StringUtils.isNotEmpty(idGenerator.getAssValue())) {
            buffer.append(idGenerator.getAssValue());
        }
        for (int i = 0; i < idGenerator.getLen() - valueLen; i++) {
            buffer.append(IdGeneratorService.PREFIX);
        }
        buffer.append(value);
        idGenerator.setValue(value + 1);
        getDao().saveOrUpdate(idGenerator);
        return buffer.toString();
    }

    @Override
    public String generateIdByDate(String tableName) {
        return generateIdByDate(Calendar.getInstance().getTime(), tableName);
    }

    @Override
    @Transactional(readOnly = false)
    public String generateIdByDate(Date date, String tableName) {
        if (StringUtils.isEmpty(tableName)) {
            throw new IllegalArgumentException("method generateIdByDate 生成编号时表名为空.");
        }
        logger.debug("generator id by date ,table ={} ", tableName);
        IdGenerator generator = currentIdGenerator(tableName, null);
        // 判断日期是否相同
        int value = 0;
        String sdate = null;
        if (date == null) {
            sdate = DateUtils.format(Calendar.getInstance().getTime(), "yyyyMMdd");
        } else {
            sdate = DateUtils.format(date, "yyyyMMdd");
        }
        if (generator.getAssValue() != null && sdate.equals(generator.getAssValue())) {
            value = generator.getValue();
        } else {
            generator.setAssValue(sdate);
            value = 1;
        }
        String id = saveGenerator(generator);
        logger.debug("generator id by {} ,value= {}", tableName, id);
        return id;
    }

    @Override
    @Transactional(readOnly = false)
    public String generateIdAssValueAndSequence(String tableName, String assValue, int len) {

        if (StringUtils.isEmpty(tableName) || StringUtils.isEmpty(assValue)) {
            throw new IllegalArgumentException();
        }
        IdGenerator generator = currentIdGenerator(tableName, assValue, len);
        String id = saveGenerator(generator);

        logger.debug("generate id by table {} and assValue {},value ={}", tableName, assValue, id);
        return id;
    }

    @Override
    @Transactional(readOnly = false)
    public String generateIdByAssValue(String tableName, String assValue) {
        if (StringUtils.isEmpty(tableName) || StringUtils.isEmpty(assValue)) {
            throw new IllegalArgumentException();
        }
        IdGenerator generator = currentIdGenerator(tableName, assValue);
        String id = saveGenerator(generator);
        logger.debug("generate id by table {} and assValue {},value ={}", tableName, assValue, id);
        return id;
    }

}
