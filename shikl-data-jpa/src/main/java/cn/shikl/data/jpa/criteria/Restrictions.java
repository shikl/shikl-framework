package cn.shikl.data.jpa.criteria;

import cn.shikl.data.criteria.Criterion;
import cn.shikl.data.jpa.criteria.expression.*;

import java.util.Collection;
import java.util.Map;

/**
 * 快速生成查询条件.
 *
 * @author shikl .
 */
public class Restrictions {

    /**
     * 私有的构造方法.
     */
    private Restrictions() {
        super();
    }

    /**
     * 或者.
     *
     * @param lhs .
     * @param rhs .
     * @return Criterion.
     */
    public static Criterion or(Criterion lhs, Criterion rhs) {
        return new LogicalExpression(lhs, rhs, Operate.OR);
    }

    /**
     * 相等.
     *
     * @param propertyName 属性名.
     * @param value        值.
     * @return 返回 Criterion.
     */
    public static Criterion eq(String propertyName, Object value) {
        return new SimpleExpression(propertyName, Operate.EQ, value);
    }

    /**
     * ne .
     *
     * @param propertyName 属性名.
     * @param value        值.
     * @return Criterion.
     */
    public static Criterion ne(String propertyName, Object value) {
        return new SimpleExpression(propertyName, Operate.NE, value);
    }

    /**
     * like.
     *
     * @param propertyName 属性名.
     * @param value        值.
     * @param matchMode    方式.
     * @return Criterion.
     */
    public static Criterion like(String propertyName, String value, MatchMode matchMode) {
        return new SimpleExpression(propertyName, Operate.LIKE, matchMode.toMatchString(value));
    }

    /**
     * like 操作.
     *
     * @param propertyName 属性名.
     * @param value        值.
     * @return Criterion.
     */
    public static Criterion like(String propertyName, String value) {
        return new SimpleExpression(propertyName, Operate.LIKE, MatchMode.START.toMatchString(value));
    }

    /**
     * gt 操作.
     *
     * @param propertyName 属性名.
     * @param value        值.
     * @return Criterion.
     */
    public static Criterion gt(String propertyName, Object value) {
        return new SimpleExpression(propertyName, Operate.GT, value);
    }

    /**
     * lt操作.
     *
     * @param propertyName 属性名.
     * @param value        值.
     * @return Criterion。
     */
    public static Criterion lt(String propertyName, Object value) {
        return new SimpleExpression(propertyName, Operate.LT, value);
    }

    /**
     * le操作.
     *
     * @param propertyName 属性名.
     * @param value        值.
     * @return Criterion.
     */
    public static Criterion le(String propertyName, Object value) {
        return new SimpleExpression(propertyName, Operate.LE, value);
    }

    /**
     * ge操作.
     *
     * @param propertyName 属性名.
     * @param value        值.
     * @return Criterion.
     */
    public static Criterion ge(String propertyName, Object value) {
        return new SimpleExpression(propertyName, Operate.GE, value);
    }

    /**
     * between操作.
     *
     * @param propertyName 属性名.
     * @param lo           小值
     * @param hi           大值
     * @return Criterion.
     */
    public static Criterion between(String propertyName, Object lo, Object hi) {
        return new BetweenExpression(propertyName, lo, hi);
    }

    /**
     * in操作.
     *
     * @param propertyName 属性名.
     * @param values       值数组.
     * @return Criterion.
     */
    public static Criterion in(String propertyName, Object[] values) {
        return new InExpression(propertyName, values);
    }

    /**
     * in操作.
     *
     * @param propertyName 属性名.
     * @param values       值集合.
     * @return Criterion.
     */
    public static Criterion in(String propertyName, Collection<Object> values) {
        return new InExpression(propertyName, values.toArray());
    }

    /**
     * isNull操作.
     *
     * @param propertyName 属性名.
     * @return Criterion .
     */
    public static Criterion isNull(String propertyName) {
        return new NullExpression(propertyName);
    }

    /**
     * isNotNull操作.
     *
     * @param propertyName 属性名.
     * @return Criterion.
     */
    public static Criterion isNotNull(String propertyName) {
        return new NotNullExpression(propertyName);
    }

    /**
     * eqProperty操作.
     *
     * @param propertyName      属性名 .
     * @param otherPropertyName 属性名.
     * @return Criterion.
     */
    public static Criterion eqProperty(String propertyName, String otherPropertyName) {
        return null;
    }

    /**
     * neProperty 操作.
     *
     * @param propertyName      属性名.
     * @param otherPropertyName 属性名 .
     * @return 返回hibernate Criterion.
     */
    public static Criterion neProperty(String propertyName, String otherPropertyName) {
        return null;
    }

    /**
     * ltProperty操作.
     *
     * @param propertyName      属性名.
     * @param otherPropertyName 属性名.
     * @return Criterion .
     */
    public static Criterion ltProperty(String propertyName, String otherPropertyName) {
        return null;
    }

    /**
     * leProperty操作.
     *
     * @param propertyName      属性名.
     * @param otherPropertyName 属性名.
     * @return Criterion.
     */
    public static Criterion leProperty(String propertyName, String otherPropertyName) {
        return null;
    }

    /**
     * gtProperty操作.
     *
     * @param propertyName      属性名.
     * @param otherPropertyName 属性名.
     * @return hibernate Criterion.
     */
    public static Criterion gtProperty(String propertyName, String otherPropertyName) {
        return null;
    }

    /**
     * geProperty操作.
     *
     * @param propertyName      属性名.
     * @param otherPropertyName 属性名.
     * @return Criterion.
     */
    public static Criterion geProperty(String propertyName, String otherPropertyName) {
        return null;
    }

    /**
     * and操作.
     *
     * @param lhs 条件,.
     * @param rhs 条件.
     * @return Criterion.
     */
    public static Criterion and(Criterion lhs, Criterion rhs) {
        return new LogicalExpression(lhs, rhs, Operate.AND);
    }

    /**
     * allEq操作.
     *
     * @param propertyNameValues .
     * @return hibernate Criterion.
     */
    public static Criterion allEq(Map<String, ?> propertyNameValues) {
        return null;
    }

    /**
     * isEmpty操作.
     *
     * @param propertyName 属性名.
     * @return hibernate Criterion.
     */
    public static Criterion isEmpty(String propertyName) {
        return null;
    }

    /**
     * isNotEmpty操作.
     *
     * @param propertyName 属性名.
     * @return hibernate Criterion.
     */
    public static Criterion isNotEmpty(String propertyName) {
        return null;
    }

    /**
     * sizeEq操作.
     *
     * @param propertyName 属性名.
     * @param size         值.
     * @return hibernate Criterion.
     */
    public static Criterion sizeEq(String propertyName, int size) {
        return null;
    }

    /**
     * sizeNe操作.
     *
     * @param propertyName 属性名.
     * @param size         值.
     * @return Criterion.
     */
    public static Criterion sizeNe(String propertyName, int size) {
        return null;
    }

    /**
     * sizeGt操作.
     *
     * @param propertyName 属性名.
     * @param size         值.
     * @return Criterion.
     */
    public static Criterion sizeGt(String propertyName, int size) {
        return null;
    }

    /**
     * sizeLt 操作.
     *
     * @param propertyName 属性名.
     * @param size         值.
     * @return hibernate Criterion.
     */
    public static Criterion sizeLt(String propertyName, int size) {
        return null;
    }

    /**
     * sizeGe操作.
     *
     * @param propertyName 属性名.
     * @param size         值.
     * @return Criterion.
     */
    public static Criterion sizeGe(String propertyName, int size) {
        return null;
    }

    /**
     * sizeLe操作.
     *
     * @param propertyName 属性名.
     * @param size         值.
     * @return Criterion.
     */
    public static Criterion sizeLe(String propertyName, int size) {
        return null;
    }

}
