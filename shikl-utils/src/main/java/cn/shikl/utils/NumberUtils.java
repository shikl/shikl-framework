package cn.shikl.utils;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * 数字工具类.
 *
 * @author shikl
 */
public final class NumberUtils {

    /**
     * 私有构造方法.
     */
    private NumberUtils() {
        super();
    }

    /**
     * <p/>
     * 返回一个数字字符串形式.#####.##
     * <p/>
     * <p/>
     * <pre>
     * NumberUtils.formatToString(123.4) = 123.40
     * NumberUtils.formatToString(-123.4)=-123.40
     *
     * </pre>
     *
     * @param value 转换前的数值.
     * @return 返回指定格式的字符串.
     */
    public static String formatToString(final float value) {
        return String.format("%.2f", value);
    }

    /**
     * <p/>
     * 返回一个数字的绝对值.
     * <p/>
     * <p/>
     * <pre>
     * NumberUtils.abs(123.4) = 123.4
     * NumberUtils.abs(-123.4)=123.4
     *
     * </pre>
     *
     * @param value 转换前的数值.
     * @return 返回绝对值
     */
    public static float abs(final float value) {
        return Math.abs(value);
    }

    /**
     * <p/>
     * 将一个double值转换为Float值,保留两位小数.
     * <p/>
     * <p/>
     * <pre>
     * NumberUtils.doubleToFloat(123.445) = 123.45
     * NumberUtils.doubleToFloat(123.454) = 123.45
     *
     * </pre>
     *
     * @param dvalue 转换前double型数值.
     * @return 转换后float数值.
     */
    public static float doubleToFloat(final double dvalue) {
        return new BigDecimal(String.valueOf(dvalue)).divide(BigDecimal.ONE, 2,
                RoundingMode.HALF_UP).floatValue();
    }

    /**
     * <p/>
     * 将一个double值转换为Float值,保留两位小数.
     * <p/>
     * <p/>
     * <pre>
     * NumberUtils.doubleToFloat(123.445,3) = 123.445
     * NumberUtils.doubleToFloat(123.45567,3) = 123.456
     * NumberUtils.doubleToFloat(123.445,-1) = 123.45
     * NumberUtils.doubleToFloat(123.445,0) = 123
     *
     * </pre>
     *
     * @param dvalue 转换前double型数值.
     * @param scale  保留小数位数.
     * @return 转换后float数值.
     */
    public static float doubleToFloat(final double dvalue, final int scale) {
        BigDecimal d = new BigDecimal(String.valueOf(dvalue));
        if (scale < 0) {
            return d.divide(BigDecimal.ONE, 2, RoundingMode.HALF_UP)
                    .floatValue();
        } else {
            return d.divide(BigDecimal.ONE, scale, RoundingMode.HALF_UP)
                    .floatValue();
        }
    }

    /**
     * <p/>
     * 四舍五入，按指定位数取小数.
     * <p/>
     * <p/>
     * <pre>
     * NumberUtils.round(123.445,3) = 123.445
     * NumberUtils.round(123.45567,3) = 123.456
     * NumberUtils.round(123.445,0) = 123
     *
     * </pre>
     *
     * @param value 操作前数值.
     * @param scale 保留小数位数.
     * @return 返回四舍五入后的结果
     */
    public static float round(final float value, final int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException("参数 scale 必須是一个大于或等于零的整数.");
        } else {
            return new BigDecimal(String.valueOf(value)).divide(BigDecimal.ONE,
                    scale, RoundingMode.HALF_UP).floatValue();
        }
    }

    /**
     * <p/>
     * 四舍五入，取两位小数.
     * <p/>
     * <p/>
     * <pre>
     * NumberUtils.round(123.445) = 123.45
     * NumberUtils.round(123.451) = 123.45
     * </pre>
     *
     * @param value 操作前数值.
     * @return 返回四舍五入后的结果
     */
    public static float round(final float value) {
        return round(value, 2);
    }

    /**
     * 四舍五入，按指定位数取小数.
     * <p/>
     * <p/>
     * <pre>
     * NumberUtils.round(123.445,3) = 123.445
     * NumberUtils.round(123.45567,3) = 123.456
     * NumberUtils.round(123.445,0) = 123
     *
     * </pre>
     *
     * @param value 操作前数值.
     * @param scale 保留小数位数.
     * @return 返回四舍五入后的结果
     */
    public static double round(final double value, final int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException("参数 scale 必須是一个大于或等于零的整数.");
        } else {
            return new BigDecimal(String.valueOf(value)).divide(BigDecimal.ONE,
                    scale, RoundingMode.HALF_UP).doubleValue();
        }
    }

    /**
     * <p/>
     * 四舍五入，取两位小数.
     * <p/>
     * <p/>
     * <pre>
     * NumberUtils.round(123.445) = 123.45
     * NumberUtils.round(123.451) = 123.45
     * </pre>
     *
     * @param value 操作前数值.
     * @return 返回四舍五入后的结果
     */
    public static double round(final double value) {
        return round(value, 2);
    }

    /**
     * 是否允许负数表示.
     */
    public static final boolean NEGATIVE = true;

    /**
     * 转换大写的数据数组.
     */
    public static final String[][] CAPITAL;

    static {
        CAPITAL = new String[][]{
                {"零", "壹", "贰", "叁", "肆", "伍", "陆", "柒", "捌", "玖"}, // 0-9
                {"拾", "佰", "仟"}, // 拾,佰,仟
                {"万"}, {"亿"}, {"元"}, {"角", "分"}, // 小数位,{"角","分","厘"}
                {"", "负"} // 正负数的前缀形式,当NEGATIVE=true时生效
        };
    }

    /**
     * 转换成大写金额.
     *
     * @param strMoney 规范的数字货币形式字符串
     * @return 转换后的大写金额.
     */
    private static String toFrmString(final String strMoney) {
        StringBuffer sb = new StringBuffer(100);

        String[] tmp = strMoney.trim().split("\\.");
        // --------整数位处理---------------
        char[] ci = tmp[0].toCharArray();

        char theBit = '0'; // 当前数字位
        int zeroBit = 0; // 开始零位
        int bitLen = 0; // 当前位所处的位置(从个位开始0计数)
        boolean flag = false; // 是否具有有效的整数位
        for (int index = 0; index < ci.length; index++) {
            theBit = ci[index]; // 取出当前处理的整数位
            bitLen = ci.length - index - 1; // 计算当前处理的数处于什么位置

            if (zeroBit > 0 && theBit != '0') {
                sb.append(CAPITAL[0][0]); // 加前导零
            }
            if (theBit != '0') {
                sb.append(CAPITAL[0][theBit - '0']); // 大写数字
            }

            if (bitLen % 8 == 0) {
                if (bitLen == 0) {
                    // 元
                    if (ci.length > 1 || theBit != '0')
                        sb.append(CAPITAL[4][0]); // 元
                } else {
                    // 亿
                    sb.append(CAPITAL[3][0]); // 亿
                }

            } else if (bitLen % 4 == 0) {
                // 万
                if (theBit != '0' || zeroBit == 0 || zeroBit - bitLen < 3) {
                    sb.append(CAPITAL[2][0]); // 万
                }
            } else // 仟佰拾
                if (theBit != '0') {
                    sb.append(CAPITAL[1][bitLen % 4 - 1]); // 仟佰拾
                }

            // 检查并条件更新零位
            if (theBit == '0') {
                zeroBit = zeroBit == 0 ? bitLen : zeroBit;
            } else {
                zeroBit = 0;
                flag = true;
            }

        }
        // --------小数位处理---------------
        char[] cf = null;
        if (tmp.length > 1) {
            cf = tmp[1].toCharArray();
            for (int index = 0; index < cf.length; index++) {
                theBit = cf[index]; // 取出当前处理的小数位
                if (zeroBit > 0 && theBit != '0' && flag) {
                    sb.append(CAPITAL[0][0]); // 加前导零
                }
                if (theBit != '0') {
                    sb.append(CAPITAL[0][theBit - '0']); // 大写数字
                }
                if (theBit != '0') {
                    sb.append(CAPITAL[5][index]); // 角分
                }

                zeroBit = theBit == '0' ? 1 : 0;
            }
        }

        return sb.length() == 0 ? (CAPITAL[0][0] + CAPITAL[4][0]) : sb
                .toString();
    }

    /**
     * 货币数字形式转换成大写.
     *
     * @param strMoney 货币的数字形式字符串
     * @return 转后大写金额
     * @throws NumberFormatException 当格式不正确时抛出异常.
     */
    public static String numberToChinese(final String strMoney)
            throws NumberFormatException {
        if (StringUtils.isEmpty(strMoney)) {
            return null;
        }
        BigDecimal bd = new BigDecimal(strMoney);
        // 输入检查
        // if (bd.signum() == -1 && !NEGATIVE) {
        // throw new NumberFormatException("货币金额不能为负数");
        // }
        try {
            bd.setScale(CAPITAL[5].length);
        } catch (ArithmeticException e) {
            throw new NumberFormatException("只能为" + CAPITAL[5].length + "位小数"
                    + e);
        }
        // 大写金额转换
        if (NEGATIVE && bd.signum() != 0) {
            return CAPITAL[6][bd.signum() == -1 ? 1 : 0]
                    + toFrmString(bd.abs().toString());
        } else {
            return toFrmString(bd.toString());
        }
    }


    private static final BigInteger LONG_MIN = BigInteger.valueOf(Long.MIN_VALUE);

    private static final BigInteger LONG_MAX = BigInteger.valueOf(Long.MAX_VALUE);

    /**
     * Standard number types (all immutable):
     * Byte, Short, Integer, Long, BigInteger, Float, Double, BigDecimal.
     */
    public static final Set<Class<?>> STANDARD_NUMBER_TYPES;

    static {
        Set<Class<?>> numberTypes = new HashSet<Class<?>>(8);
        numberTypes.add(Byte.class);
        numberTypes.add(Short.class);
        numberTypes.add(Integer.class);
        numberTypes.add(Long.class);
        numberTypes.add(BigInteger.class);
        numberTypes.add(Float.class);
        numberTypes.add(Double.class);
        numberTypes.add(BigDecimal.class);
        STANDARD_NUMBER_TYPES = Collections.unmodifiableSet(numberTypes);
    }


    /**
     * Convert the given number into an instance of the given target class.
     *
     * @param number      the number to convert
     * @param targetClass the target class to convert to
     * @return the converted number
     * @throws IllegalArgumentException if the target class is not supported
     *                                  (i.e. not a standard Number subclass as included in the JDK)
     * @see Byte
     * @see Short
     * @see Integer
     * @see Long
     * @see BigInteger
     * @see Float
     * @see Double
     * @see BigDecimal
     */
    @SuppressWarnings("unchecked")
    public static <T extends Number> T convertNumberToTargetClass(Number number, Class<T> targetClass)
            throws IllegalArgumentException {

        org.springframework.util.Assert.notNull(number, "Number must not be null");
        org.springframework.util.Assert.notNull(targetClass, "Target class must not be null");

        if (targetClass.isInstance(number)) {
            return (T) number;
        } else if (targetClass.equals(Byte.class)) {
            long value = number.longValue();
            if (value < Byte.MIN_VALUE || value > Byte.MAX_VALUE) {
                raiseOverflowException(number, targetClass);
            }
            return (T) new Byte(number.byteValue());
        } else if (targetClass.equals(Short.class)) {
            long value = number.longValue();
            if (value < Short.MIN_VALUE || value > Short.MAX_VALUE) {
                raiseOverflowException(number, targetClass);
            }
            return (T) new Short(number.shortValue());
        } else if (targetClass.equals(Integer.class)) {
            long value = number.longValue();
            if (value < Integer.MIN_VALUE || value > Integer.MAX_VALUE) {
                raiseOverflowException(number, targetClass);
            }
            return (T) new Integer(number.intValue());
        } else if (targetClass.equals(Long.class)) {
            BigInteger bigInt = null;
            if (number instanceof BigInteger) {
                bigInt = (BigInteger) number;
            } else if (number instanceof BigDecimal) {
                bigInt = ((BigDecimal) number).toBigInteger();
            }
            // Effectively analogous to JDK 8's BigInteger.longValueExact()
            if (bigInt != null && (bigInt.compareTo(LONG_MIN) < 0 || bigInt.compareTo(LONG_MAX) > 0)) {
                raiseOverflowException(number, targetClass);
            }
            return (T) new Long(number.longValue());
        } else if (targetClass.equals(BigInteger.class)) {
            if (number instanceof BigDecimal) {
                // do not lose precision - use BigDecimal's own conversion
                return (T) ((BigDecimal) number).toBigInteger();
            } else {
                // original value is not a Big* number - use standard long conversion
                return (T) BigInteger.valueOf(number.longValue());
            }
        } else if (targetClass.equals(Float.class)) {
            return (T) new Float(number.floatValue());
        } else if (targetClass.equals(Double.class)) {
            return (T) new Double(number.doubleValue());
        } else if (targetClass.equals(BigDecimal.class)) {
            // always use BigDecimal(String) here to avoid unpredictability of BigDecimal(double)
            // (see BigDecimal javadoc for details)
            return (T) new BigDecimal(number.toString());
        } else {
            throw new IllegalArgumentException("Could not convert number [" + number + "] of type [" +
                    number.getClass().getName() + "] to unknown target class [" + targetClass.getName() + "]");
        }
    }

    /**
     * Raise an overflow exception for the given number and target class.
     *
     * @param number      the number we tried to convert
     * @param targetClass the target class we tried to convert to
     */
    private static void raiseOverflowException(Number number, Class<?> targetClass) {
        throw new IllegalArgumentException("Could not convert number [" + number + "] of type [" +
                number.getClass().getName() + "] to target class [" + targetClass.getName() + "]: overflow");
    }

    /**
     * Parse the given text into a number instance of the given target class,
     * using the corresponding {@code decode} / {@code valueOf} methods.
     * <p>Trims the input {@code String} before attempting to parse the number.
     * Supports numbers in hex format (with leading "0x", "0X" or "#") as well.
     *
     * @param text        the text to convert
     * @param targetClass the target class to parse into
     * @return the parsed number
     * @throws IllegalArgumentException if the target class is not supported
     *                                  (i.e. not a standard Number subclass as included in the JDK)
     * @see Byte#decode
     * @see Short#decode
     * @see Integer#decode
     * @see Long#decode
     * @see #decodeBigInteger(String)
     * @see Float#valueOf
     * @see Double#valueOf
     * @see BigDecimal#BigDecimal(String)
     */
    @SuppressWarnings("unchecked")
    public static <T extends Number> T parseNumber(String text, Class<T> targetClass) {
        org.springframework.util.Assert.notNull(text, "Text must not be null");
        org.springframework.util.Assert.notNull(targetClass, "Target class must not be null");
        String trimmed = org.springframework.util.StringUtils.trimAllWhitespace(text);

        if (targetClass.equals(Byte.class)) {
            return (T) (isHexNumber(trimmed) ? Byte.decode(trimmed) : Byte.valueOf(trimmed));
        } else if (targetClass.equals(Short.class)) {
            return (T) (isHexNumber(trimmed) ? Short.decode(trimmed) : Short.valueOf(trimmed));
        } else if (targetClass.equals(Integer.class)) {
            return (T) (isHexNumber(trimmed) ? Integer.decode(trimmed) : Integer.valueOf(trimmed));
        } else if (targetClass.equals(Long.class)) {
            return (T) (isHexNumber(trimmed) ? Long.decode(trimmed) : Long.valueOf(trimmed));
        } else if (targetClass.equals(BigInteger.class)) {
            return (T) (isHexNumber(trimmed) ? decodeBigInteger(trimmed) : new BigInteger(trimmed));
        } else if (targetClass.equals(Float.class)) {
            return (T) Float.valueOf(trimmed);
        } else if (targetClass.equals(Double.class)) {
            return (T) Double.valueOf(trimmed);
        } else if (targetClass.equals(BigDecimal.class) || targetClass.equals(Number.class)) {
            return (T) new BigDecimal(trimmed);
        } else {
            throw new IllegalArgumentException(
                    "Cannot convert String [" + text + "] to target class [" + targetClass.getName() + "]");
        }
    }

    /**
     * Parse the given text into a number instance of the given target class,
     * using the given NumberFormat. Trims the input {@code String}
     * before attempting to parse the number.
     *
     * @param text         the text to convert
     * @param targetClass  the target class to parse into
     * @param numberFormat the NumberFormat to use for parsing (if {@code null},
     *                     this method falls back to {@code parseNumber(String, Class)})
     * @return the parsed number
     * @throws IllegalArgumentException if the target class is not supported
     *                                  (i.e. not a standard Number subclass as included in the JDK)
     * @see NumberFormat#parse
     * @see #convertNumberToTargetClass
     * @see #parseNumber(String, Class)
     */
    public static <T extends Number> T parseNumber(String text, Class<T> targetClass, NumberFormat numberFormat) {
        if (numberFormat != null) {
            org.springframework.util.Assert.notNull(text, "Text must not be null");
            org.springframework.util.Assert.notNull(targetClass, "Target class must not be null");
            DecimalFormat decimalFormat = null;
            boolean resetBigDecimal = false;
            if (numberFormat instanceof DecimalFormat) {
                decimalFormat = (DecimalFormat) numberFormat;
                if (BigDecimal.class.equals(targetClass) && !decimalFormat.isParseBigDecimal()) {
                    decimalFormat.setParseBigDecimal(true);
                    resetBigDecimal = true;
                }
            }
            try {
                Number number = numberFormat.parse(org.springframework.util.StringUtils.trimAllWhitespace(text));
                return convertNumberToTargetClass(number, targetClass);
            } catch (ParseException ex) {
                throw new IllegalArgumentException("Could not parse number: " + ex.getMessage());
            } finally {
                if (resetBigDecimal) {
                    decimalFormat.setParseBigDecimal(false);
                }
            }
        } else {
            return parseNumber(text, targetClass);
        }
    }

    /**
     * Determine whether the given value String indicates a hex number, i.e. needs to be
     * passed into {@code Integer.decode} instead of {@code Integer.valueOf} (etc).
     */
    private static boolean isHexNumber(String value) {
        int index = (value.startsWith("-") ? 1 : 0);
        return (value.startsWith("0x", index) || value.startsWith("0X", index) || value.startsWith("#", index));
    }

    /**
     * Decode a {@link BigInteger} from a {@link String} value.
     * Supports decimal, hex and octal notation.
     *
     * @see BigInteger#BigInteger(String, int)
     */
    private static BigInteger decodeBigInteger(String value) {
        int radix = 10;
        int index = 0;
        boolean negative = false;

        // Handle minus sign, if present.
        if (value.startsWith("-")) {
            negative = true;
            index++;
        }

        // Handle radix specifier, if present.
        if (value.startsWith("0x", index) || value.startsWith("0X", index)) {
            index += 2;
            radix = 16;
        } else if (value.startsWith("#", index)) {
            index++;
            radix = 16;
        } else if (value.startsWith("0", index) && value.length() > 1 + index) {
            index++;
            radix = 8;
        }

        BigInteger result = new BigInteger(value.substring(index), radix);
        return (negative ? result.negate() : result);
    }
}
