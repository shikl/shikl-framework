package cn.shikl.utils;

import org.apache.commons.codec.binary.Hex;
import org.springframework.util.ObjectUtils;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * 字符串工具类.
 *
 * @author shikl
 * @version 1.0
 */
public final class StringUtils {

    /**
     * <p/>
     * 判断字符串是否为NULL 或者"".
     * <p/>
     * <p/>
     * <pre>
     * StringUtils.isEmpty(null) = true
     * StringUtils.isEmpty(&quot;&quot;) = true
     * StringUtils.isEmpty(&quot; &quot;) = true
     * StringUtils.isEmpty(&quot;str&quot;) = false
     * StringUtils.isEmpty(&quot;  str  &quot;) = false
     *
     * </pre>
     *
     * @param str 判断的字符串.
     * @return 如果参数 str 为Null或" ",返回true,否则返回false.
     */
    public static boolean isEmpty(final String str) {
        return (str == null || "".equals(str.trim()));
    }

    /**
     * <p/>
     * 判断字符串是否为非空，包括Null 和"".
     * <p/>
     * <p/>
     * <pre>
     * StringUtils.isEmpty(null) = false
     * StringUtils.isEmpty(&quot;&quot;) = false
     * StringUtils.isEmpty(&quot; &quot;) = false
     * StringUtils.isEmpty(&quot;str&quot;) = true
     * StringUtils.isEmpty(&quot;  str  &quot;) = true
     * </pre>
     *
     * @param str 被判断的字符串.
     * @return 如果参数 str 为非Null或非" ",返回true,否则返回false.
     */
    public static boolean isNotEmpty(final String str) {
        return !isEmpty(str);
    }

    /**
     * <p/>
     * 判断一个字符串是否为数字字符串.
     * <p/>
     * <p/>
     * <pre>
     * StringUtils.isNumeric(null)   = false
     * StringUtils.isNumeric(&quot;&quot;)     = false
     * StringUtils.isNumeric(&quot;  &quot;)   = false
     * StringUtils.isNumeric(&quot;12 3&quot;) = false
     * StringUtils.isNumeric(&quot;ab2c&quot;) = false
     * StringUtils.isNumeric(&quot;12-3&quot;) = false
     * tringUtils.isNumeric(&quot;12.3.4&quot;) = false
     * StringUtils.isNumeric(&quot;12.3&quot;) = true
     * StringUtils.isNumeric(&quot;123&quot;)  = true
     *
     * </pre>
     *
     * @param str 被检查的字符串.
     * @return 如果被检查的字符串是一个数字字符串则返回true.否则返回false.
     */
    public static boolean isNumeric(final String str) {
        if (isEmpty(str)) {
            return false;
        }
        char[] cc = str.toCharArray();
        int sz = cc.length;
        // 统计'.'在字符中出现的次数
        int c = 0;
        for (int i = 0; i < sz; i++) {
            if (cc[i] == '.') {
                cc[i] = '0';
                c++;
            }
            if (c > 1) {
                return false;
            }
            if (!Character.isDigit(cc[i])) {
                return false;
            }
        }
        return true;
    }


    /**
     * 默认的构造方法.
     */
    private StringUtils() {
        super();
    }

    /**
     * 连接字符串.
     * <p/>
     * <pre>
     * StringUtils.connect(&quot;a&quot;, &quot;b&quot;, &quot;c&quot;) = &quot;abc&quot;;
     * StringUtils.connect(&quot;&quot;, &quot;&quot;, &quot;&quot;) = &quot;&quot;;
     *
     * </pre>
     *
     * @param str 多个字符串或对象.
     * @return 将多个字符中连接后的字符串.
     */
    public static String connect(Object... str) {
        StringBuilder builder = new StringBuilder(str.length);
        for (Object s : str) {
            builder.append(s);
        }
        return builder.toString();
    }

    public static String connect(String... str) {
        StringBuilder builder = new StringBuilder(str.length);
        for (String s : str) {
            builder.append(s);
        }
        return builder.toString();
    }

    /**
     * Replace all occurrences of a substring within a string with
     * another string.
     *
     * @param inString   String to examine
     * @param oldPattern String to replace
     * @param newPattern String to insert
     * @return a String with the replacements
     */
    public static String replace(String inString, String oldPattern, String newPattern) {
        if (!hasLength(inString) || !hasLength(oldPattern) || newPattern == null) {
            return inString;
        }
        StringBuilder sb = new StringBuilder();
        int pos = 0; // our position in the old string
        int index = inString.indexOf(oldPattern);
        // the index of an occurrence we've found, or -1
        int patLen = oldPattern.length();
        while (index >= 0) {
            sb.append(inString.substring(pos, index));
            sb.append(newPattern);
            pos = index + patLen;
            index = inString.indexOf(oldPattern, pos);
        }
        sb.append(inString.substring(pos));
        // remember to append any characters to the right of a match
        return sb.toString();
    }

    /**
     * Check that the given CharSequence is neither {@code null} nor of length 0.
     * Note: Will return {@code true} for a CharSequence that purely consists of whitespace.
     * <p><pre class="code">
     * StringUtils.hasLength(null) = false
     * StringUtils.hasLength("") = false
     * StringUtils.hasLength(" ") = true
     * StringUtils.hasLength("Hello") = true
     * </pre>
     *
     * @param str the CharSequence to check (may be {@code null})
     * @return {@code true} if the CharSequence is not null and has length
     * @see #hasText(String)
     */
    public static boolean hasLength(CharSequence str) {
        return (str != null && str.length() > 0);
    }

    /**
     * Check that the given String is neither {@code null} nor of length 0.
     * Note: Will return {@code true} for a String that purely consists of whitespace.
     *
     * @param str the String to check (may be {@code null})
     * @return {@code true} if the String is not null and has length
     * @see #hasLength(CharSequence)
     */
    public static boolean hasLength(String str) {
        return hasLength((CharSequence) str);
    }

    /**
     * Check whether the given CharSequence has actual text.
     * More specifically, returns {@code true} if the string not {@code null},
     * its length is greater than 0, and it contains at least one non-whitespace character.
     * <p><pre class="code">
     * StringUtils.hasText(null) = false
     * StringUtils.hasText("") = false
     * StringUtils.hasText(" ") = false
     * StringUtils.hasText("12345") = true
     * StringUtils.hasText(" 12345 ") = true
     * </pre>
     *
     * @param str the CharSequence to check (may be {@code null})
     * @return {@code true} if the CharSequence is not {@code null},
     * its length is greater than 0, and it does not contain whitespace only
     * @see Character#isWhitespace
     */
    public static boolean hasText(CharSequence str) {
        if (!hasLength(str)) {
            return false;
        }
        int strLen = str.length();
        for (int i = 0; i < strLen; i++) {
            if (!Character.isWhitespace(str.charAt(i))) {
                return true;
            }
        }
        return false;
    }

    /**
     * Check whether the given String has actual text.
     * More specifically, returns {@code true} if the string not {@code null},
     * its length is greater than 0, and it contains at least one non-whitespace character.
     *
     * @param str the String to check (may be {@code null})
     * @return {@code true} if the String is not {@code null}, its length is
     * greater than 0, and it does not contain whitespace only
     * @see #hasText(CharSequence)
     */
    public static boolean hasText(String str) {
        return hasText((CharSequence) str);
    }


    /**
     * <p/>
     * 返回MD5加密摘要.
     * <p/>
     *
     * @param str 需要加密的字符串
     * @return 加密码后的字符串
     * @throws NoSuchAlgorithmException
     */
    public static String MD5(String str) throws NoSuchAlgorithmException {
        if (StringUtils.isEmpty(str)) {
            throw new IllegalArgumentException();
        }
        java.security.MessageDigest messageDigest;
        messageDigest = java.security.MessageDigest.getInstance("MD5");
        byte[] digest = messageDigest.digest(str.trim().getBytes());
        return new String(Hex.encodeHex(digest));
    }


    /**
     * 首字母转小写.
     *
     * @param s 需要转换的字符串.
     * @return 转换后的字符串.
     */
    public static String toLowerCaseFirstOne(String s) {
        if (Character.isLowerCase(s.charAt(0)))
            return s;
        else
            return (new StringBuilder()).append(Character.toLowerCase(s.charAt(0))).append(s.substring(1)).toString();
    }

    /**
     * 改变单首字母大小写.
     *
     * @param str        需要改变的单词.
     * @param capitalize 是否大写.
     * @return 改变后的单词.
     */
    public static String changeFirstCharacterCase(String str, boolean capitalize) {
        if (str == null || str.length() == 0) {
            return str;
        }
        StringBuilder sb = new StringBuilder(str.length());
        if (capitalize) {
            sb.append(Character.toUpperCase(str.charAt(0)));
        } else {
            sb.append(Character.toLowerCase(str.charAt(0)));
        }
        sb.append(str.substring(1));
        return sb.toString();
    }

    /**
     * Capitalize a {@code String}, changing the first letter to
     * upper case as per {@link Character#toUpperCase(char)}.
     * No other letters are changed.
     * @param str the String to capitalize, may be {@code null}
     * @return the capitalized String, {@code null} if null
     */
    public static String capitalize(String str) {
        return changeFirstCharacterCase(str, true);
    }

    /**
     * Uncapitalize a {@code String}, changing the first letter to
     * lower case as per {@link Character#toLowerCase(char)}.
     * No other letters are changed.
     * @param str the String to uncapitalize, may be {@code null}
     * @return the uncapitalized String, {@code null} if null
     */
    public static String uncapitalize(String str) {
        return changeFirstCharacterCase(str, false);
    }
    /**
     * Merge the given String arrays into one, with overlapping
     * array elements only included once.
     * <p>The order of elements in the original arrays is preserved
     * (with the exception of overlapping elements, which are only
     * included on their first occurrence).
     *
     * @param array1 the first array (can be {@code null})
     * @param array2 the second array (can be {@code null})
     * @return the new array ({@code null} if both given arrays were {@code null})
     */
    public static String[] mergeStringArrays(String[] array1, String[] array2) {
        if (ObjectUtils.isEmpty(array1)) {
            return array2;
        }
        if (ObjectUtils.isEmpty(array2)) {
            return array1;
        }
        List<String> result = new ArrayList<String>();
        result.addAll(Arrays.asList(array1));
        for (String str : array2) {
            if (!result.contains(str)) {
                result.add(str);
            }
        }
        return toStringArray(result);
    }

    /**
     * Turn given source String array into sorted array.
     *
     * @param array the source array
     * @return the sorted array (never {@code null})
     */
    public static String[] sortStringArray(String[] array) {
        if (ObjectUtils.isEmpty(array)) {
            return new String[0];
        }
        Arrays.sort(array);
        return array;
    }

    /**
     * Copy the given Collection into a String array.
     * The Collection must contain String elements only.
     *
     * @param collection the Collection to copy
     * @return the String array ({@code null} if the passed-in
     * Collection was {@code null})
     */
    public static String[] toStringArray(Collection<String> collection) {
        if (collection == null) {
            return null;
        }
        return collection.toArray(new String[collection.size()]);
    }
}
