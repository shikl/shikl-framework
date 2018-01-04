package cn.shikl.data.criteria;


import cn.shikl.utils.Constant;

/**
 * 联合查询.
 * 
 * @author shikl <br/>
 * @version 1.0.0
 */
public class Join {

    /**
     * 联合查询关联属性.
     */
    private final String propertyName;

    /**
     * 别名.
     */
    private final String alias;

    /**
     * 联合方式 .
     */
    private final JoinType joinType;

    /**
     * 是否是fetch.
     */
    private final boolean fetch;

    /**
     * 构造联合对象.
     * 
     * @param propertyName
     *            联合属性名称.
     * @param alias
     *            别名.
     * @param joinType
     *            联合类型.
     * @param fetch
     *            是否是fetch.
     */
    public Join(final String propertyName, String alias, JoinType joinType, boolean fetch) {
        this.propertyName = propertyName;
        this.alias = alias;
        this.joinType = joinType;
        this.fetch = fetch;
    }

    /**
     * 构造联合对象.
     * 
     * @param propertyName
     *            联合属性名称.
     */
    public Join(String propertyName) {
        this.propertyName = propertyName;
        this.alias = propertyName;
        this.joinType = JoinType.INNER;
        this.fetch = false;
    }

    /**
     * 构造联合对象.
     * 
     * @param propertyName
     *            属性名称.
     * @param fetch
     *            是否是fetch.
     */
    public Join(String propertyName, boolean fetch) {
        this.propertyName = propertyName;
        this.fetch = fetch;
        this.alias = propertyName;
        this.joinType = JoinType.INNER;
    }

    /**
     * 构造联合对象.
     * 
     * @param propertyName
     *            联合属性名称.
     * @param alias
     *            别名.
     */
    public Join(String propertyName, String alias) {
        this.propertyName = propertyName;
        this.alias = alias;
        this.joinType = JoinType.INNER;
        this.fetch = false;
    }

    /**
     * 构造联合对象.
     * 
     * @param propertyName
     *            联合属性名称.
     * @param alias
     *            别名.
     * @param joinType
     *            联合类型.
     */
    public Join(String propertyName, String alias, JoinType joinType) {
        this.propertyName = propertyName;
        this.alias = alias;
        this.joinType = joinType;
        this.fetch = false;
    }

    /**
     * 联合查询的连接方式. INNER JOIN 、 LEFT JOIN
     */
    public enum JoinType {

        /**
         * 连接类型.
         */
        LEFT("left"), INNER("inner");

        /**
         * 连接类型.
         */
        private String type;

        /**
         * 默认构造方法.
         * 
         * @param type
         *            连接类型.
         */
        private JoinType(String type) {
            this.type = type;
        }

        /**
         * 返回连接类型.
         * 
         * @return String .
         */
        public String value() {
            return type;
        }
    }

    /**
     * 返回sql.
     * 
     * @param criteria
     *            条件对象.
     * @return String .
     */
    public String toSqlString(Criteria criteria) {
        StringBuilder builder = new StringBuilder();
        builder.append(this.joinType.value()).append(" join ");
        if (fetch) {
            builder.append("fetch ");
        }
        builder.append(criteria.getAlias()).append(Constant.PERIOD).append(propertyName).append(Constant.SPACE)
                .append(alias).append(Constant.SPACE);
        return builder.toString();
    }
}
