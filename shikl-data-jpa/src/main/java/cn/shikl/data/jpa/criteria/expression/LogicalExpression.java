package cn.shikl.data.jpa.criteria.expression;


import cn.shikl.core.exeception.NotSupportedException;
import cn.shikl.data.criteria.Criteria;
import cn.shikl.data.criteria.CriteriaTranslator;
import cn.shikl.data.criteria.Criterion;
import cn.shikl.utils.Constant;
import cn.shikl.utils.StringUtils;

/**
 * 两个条件指定不同的关系进行组合. AND OR .
 *
 * @author shikl <br/>
 *         date:2014-04-16
 * @version 1.0.0
 */
public class LogicalExpression implements Criterion {
    /**
     * 左联条件.
     */
    private final Criterion lExpression;
    /**
     * 右联条件.
     */
    private final Criterion rExpression;

    /**
     * 联接符.
     */
    private final Operate op;

    /**
     * 生成一个逻辑连接的查询条件.
     *
     * @param lExpression 左联条件.
     * @param rExpression 右联条件.
     * @param op          连接符.
     */
    public LogicalExpression(Criterion lExpression, Criterion rExpression, Operate op) {
        this.lExpression = lExpression;
        this.rExpression = rExpression;
        this.op = op;
    }

    /**
     * 左联查询条件.
     *
     * @return 左联查询条件.
     */
    public Criterion getlExpression() {
        return lExpression;
    }

    /**
     * 右联查询条件.
     *
     * @return 右联查询条件.
     */
    public Criterion getrExpression() {
        return rExpression;
    }

    @Override
    public String toSqlString(Criteria criteria, CriteriaTranslator criteriaTranslator) {
        return StringUtils.connect(Constant.LEFT_PARENTHESES, lExpression.toSqlString(criteria, criteriaTranslator),
                op.value(), rExpression.toSqlString(criteria, criteriaTranslator), Constant.RIGHT_PARENTHESES);
    }

    @Override
    public Object getValue() {
        throw new NotSupportedException();
    }

}