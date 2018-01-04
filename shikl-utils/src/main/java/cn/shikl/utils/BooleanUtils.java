package cn.shikl.utils;


/**
 * 布尔型工具类.
 *
 * @author shikl
 * @since 1.0.0
 */
public final class BooleanUtils {

    /**
     * 私有的构造方法.
     */
    private BooleanUtils() {
        super();
    }

    /**
     * <p>
     * 将String 型字符转换成布尔型.
     * <p>
     * <p>
     * <pre>
     * 		BooleanUtils.isBoolean(&quot;true&quot;)==true
     *  	BooleanUtils.isBoolean(&quot;false&quot;)==false
     *  	BooleanUtils.isBoolean(&quot;on&quot;)==true
     *  	BooleanUtils.isBoolean(&quot;off&quot;)==false
     *  	BooleanUtils.isBoolean(&quot;yes&quot;)==true
     *  	BooleanUtils.isBoolean(&quot;no&quot;)==false
     *  	BooleanUtils.isBoolean(&quot;TRUE&quot;)==true
     *  	BooleanUtils.isBoolean(&quot;FALSE&quot;)==false
     *  	BooleanUtils.isBoolean(&quot;ON&quot;)==true
     *  	BooleanUtils.isBoolean(&quot;OFF&quot;)==false
     *  	BooleanUtils.isBoolean(&quot;YES&quot;)==true
     *  	BooleanUtils.isBoolean(&quot;NO&quot;)==false
     *  	BooleanUtils.isBoolean(&quot;1&quot;)==true
     * 		BooleanUtils.isBoolean(&quot;0&quot;)==false
     *    	BooleanUtils.isBoolean(&quot;2&quot;)== throw RuntimeException
     *  	BooleanUtils.isBoolean(&quot;abc&quot;)==throw RuntimeException
     * </pre>
     *
     * @param str 即将转换的字符串.
     * @return boolean 型值.
     * @throws RuntimeException
     */
    public static boolean valueOfBoolean(final String str) {
        if (StringUtils.isEmpty(str)) {
            throw new IllegalArgumentException();
        }
        if (str.equalsIgnoreCase("true") || str.equalsIgnoreCase("on")
                || str.equalsIgnoreCase("yes") || str.equals("1")) {
            return true;
        } else if (str.equalsIgnoreCase("false") || str.equalsIgnoreCase("off")
                || str.equalsIgnoreCase("no") || str.equals("0")) {
            return false;
        } else {
            throw new RuntimeException("参数 [" + str + "] 不能转换成boolean 型 .");
        }
    }

    /**
     * <p>
     * 判断一个字符串是能够转换为boolean型.
     * <p>
     * <p>
     * <pre>
     * 	BooleanUtils.isBoolean(&quot;true&quot;)==true
     *  	BooleanUtils.isBoolean(&quot;false&quot;)==true
     *  	BooleanUtils.isBoolean(&quot;on&quot;)==true
     *  	BooleanUtils.isBoolean(&quot;off&quot;)==true
     *  	BooleanUtils.isBoolean(&quot;yes&quot;)==true
     *  	BooleanUtils.isBoolean(&quot;no&quot;)==true
     *  	BooleanUtils.isBoolean(&quot;TRUE&quot;)==true
     *  	BooleanUtils.isBoolean(&quot;FALSE&quot;)==true
     *  	BooleanUtils.isBoolean(&quot;ON&quot;)==true
     *  	BooleanUtils.isBoolean(&quot;OFF&quot;)==true
     *  	BooleanUtils.isBoolean(&quot;YES&quot;)==true
     *  	BooleanUtils.isBoolean(&quot;NO&quot;)==true
     *  	BooleanUtils.isBoolean(&quot;abc&quot;)==false
     *  	BooleanUtils.isBoolean(&quot;1&quot;)==true
     * 	BooleanUtils.isBoolean(&quot;0&quot;)==true
     *    BooleanUtils.isBoolean(&quot;2&quot;)==false
     * </pre>
     *
     * @param str 被判断的字符串.
     * @return 如果能够转换为boolean 型 返回true,否则返回false.
     */
    public static boolean isBoolean(final String str) {
        if (StringUtils.isEmpty(str)) {
            return false;
        }
        if (str.equalsIgnoreCase("true") || str.equalsIgnoreCase("false")
                || str.equalsIgnoreCase("on") || str.equalsIgnoreCase("off")
                || str.equalsIgnoreCase("yes") || str.equalsIgnoreCase("no")
                || str.equals("1") || str.equals("0")) {
            return true;
        }
        return false;
    }

    /**
     * <p>
     * 判断一个整型是否能够转换为boolean型.
     * <p>
     * <p>
     * <pre>
     * 	BooleanUtils.isBoolean(1)==true
     *  	BooleanUtils.isBoolean(0)==true
     *  	BooleanUtils.isBoolean(2)==false
     * </pre>
     *
     * @param i 被判断的整数.
     * @return 如果能够转换为boolean 型 返回true,否则返回false.
     */
    public static boolean isBoolean(final int i) {
        if (i == 0 || i == 1) {
            return true;
        }
        return false;
    }

    /**
     * <p>
     * 返回一个布尔型的数字表示方法. true=1,false=0.
     * </p>
     *
     * @param b 需要转换的布尔型.
     * @return true=1,false=0.
     */
    public static String formateString(final boolean b) {
        if (b) {
            return "1";
        }
        return "0";
    }
}
