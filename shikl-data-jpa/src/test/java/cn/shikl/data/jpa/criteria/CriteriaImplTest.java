package cn.shikl.data.jpa.criteria;


import cn.shikl.data.criteria.Criteria;
import cn.shikl.data.jpa.criteria.CriteriaJPA2Impl;
import cn.shikl.data.jpa.criteria.Restrictions;
import cn.shikl.data.jpa.entity.Dictionary;

import org.junit.Test;

/**
 * @author libo <br/>
 *         date:2014-04-16
 * @version 1.0.0
 */
public class CriteriaImplTest {
    @Test
    public void testToSqlString() throws Exception {
        Criteria criteria = new CriteriaJPA2Impl(Dictionary.class);
        criteria.add(Restrictions.eq("name", "zhangfei"));
        criteria.join("cccc");
//        criteria.add(Restrictions.eq("age", 20));
//        criteria.add(Restrictions.or(Restrictions.like("name", "fei", MatchMode.ANYWHERE), Restrictions.or(Restrictions.eq("name","li"), Restrictions.eq("name", "zhang"))));
//        criteria.add(Restrictions.like("name", "zhang"));
//        criteria.add(Restrictions.like("name","hai", MatchMode.ANYWHERE));
//        criteria.add(Restrictions.isNull("name"));

//        criteria.addOrder(Order.desc("age"), Order.asc("name"));
//        criteria.distinct();

        String sql = criteria.toSqlString();
        System.out.println("sql:" + sql);
        System.out.println("parameter:" + criteria.getParameter());

//        String sqlPara =criteria.toSqlStringWithParamters();
//        System.out.println("sql with parameter:"+sqlPara);
    }

    @Test
    public void testToSqlStringWithParamters() throws Exception {

    }
}
