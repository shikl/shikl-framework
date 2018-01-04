package cn.shikl.data.jpa.criteria.expression;

/**
 * 操作符类型.
 * 
 * @author libo <br/>
 *         date:2014-04-16
 * @version 1.0.0
 */
public enum Operate {
    /**
     * 操作符.
     */
    EQ("="), NE("<>"), GT(">"), LT("<"), GE(">="), LE("<="), LIKE("like"), OR(" or "), AND(" and "),IN(" in ");

    /**
     * 操作符.
     */
    private String op;

    /**
     * 操作的默认构造.
     * 
     * @param op
     *            操作参数.
     */
    private Operate(String op) {
        this.op = op;
    }

    /**
     * 操作符.
     * 
     * @return 操作符.
     */
    public String value() {
        return op;
    }
}
