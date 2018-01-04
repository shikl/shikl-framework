package cn.shikl.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期工具类.
 * 
 * @author shikl
 * 
 */
public final class DateUtils {

	/**
	 * 比较两个日期的日期部分，即比较年月日是否相等.
	 * 
	 * @param date1
	 * @param date2
	 * @return 如果两个日期的年月日都相等返回true.
	 */
	public static boolean compareDate(final Date date1, final Date date2) {
		Calendar c1 = Calendar.getInstance();
		c1.setTime(date1);
		Calendar c2 = Calendar.getInstance();
		c2.setTime(date2);
		return c1.get(Calendar.YEAR) == c2.get(Calendar.YEAR)
				&& c1.get(Calendar.MONTH) == c2.get(Calendar.MONTH)
				&& c1.get(Calendar.DATE) == c2.get(Calendar.DATE);
	}

	/**
	 * 将util 的子类转换为java.uitl.date (如:java.sql.date)
	 * 
	 * @param date
	 * @return java.util.date
	 */
	public static Date dateToDate(Date date) {
		if (date == null)
			return null;
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c.getTime();
	}

	/**
	 * 在指定的日期上加上一个天数.
	 * 
	 * @param date
	 *            日期参数
	 * @param day
	 *            天数值
	 * @return 加上天数值后的日期
	 */
	public static Date addDays(final Date date, final int day) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DATE, day);
		return calendar.getTime();
	}

	/**
	 * 格式化给定的日期, 参见org.apache.commons.lang.time.DateFormatUtils.format(date,
	 * pattern).
	 * 
	 * @param date
	 *            被格式化的日期.
	 * @param pattern
	 *            日期格式,yyyy表示年,MM表示月,dd表示日,
	 * @return 格式化后的字符串的日期格式,如:20070101,2007-01-01
	 */
	public static String format(final Date date, final String pattern) {
		return new SimpleDateFormat(pattern).format(date);
	}

	/**
	 * 返回给定日期的天数.
	 * 
	 * @param date
	 *            操作日期.
	 * @return 日期的天数.
	 */
	public static int getDay(final Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c.get(Calendar.DATE);
	}

	/**
	 * 返回给定的月份,从1开始.
	 * 
	 * @param date
	 *            日期参数
	 * @return 日期的月份数值
	 */
	public static Integer getMonth(final Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c.get(Calendar.MONTH) + 1;
	}

	/**
	 * 返回给定日期的星期数.
	 * 
	 * @param date
	 *            操作日期.
	 * @return 返回日期的星期数.
	 */
	public static int getWeek(final Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c.get(Calendar.DAY_OF_WEEK);
	}

	/**
	 * 返回给定日期的年度.
	 * 
	 * @param date
	 *            日期参数.
	 * @return 日期的年度数值.
	 */
	public static int getYear(final Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c.get(Calendar.YEAR);
	}

	/**
	 * 计算两个日期之间间隔的天数.
	 * 
	 * @param calendar1
	 *            日期1
	 * @param calendar2
	 *            日期2
	 * @return 返回两个日期之间间隔的天数.
	 */
	public static int interval(final Calendar calendar1,
			final Calendar calendar2) {
		Calendar c1 = calendar1;
		Calendar c2 = calendar2;
		c1.set(Calendar.HOUR, 0);
		c1.set(Calendar.MINUTE, 0);
		c1.set(Calendar.MILLISECOND, 0);
		c2.set(Calendar.HOUR, 0);
		c2.set(Calendar.MINUTE, 0);
		c2.set(Calendar.MILLISECOND, 0);
		long c = Math.abs(c1.getTimeInMillis() - c2.getTimeInMillis());
		c = c / 1000 / 60 / 60 / 24;
		return Integer.valueOf(String.valueOf(c));
	}

	/**
	 * 计算两个日期之间间隔的天数.
	 * 
	 * @param date1
	 *            日期1
	 * @param date2
	 *            日期2
	 * @return 返回两个日期间间隔的天数.
	 */
	public static int interval(final Date date1, final Date date2) {
		Calendar calendar1 = Calendar.getInstance();
		calendar1.setTime(date1);
		Calendar calendar2 = Calendar.getInstance();
		calendar2.setTime(date2);
		return interval(calendar1, calendar2);
	}

	/**
	 * 计算两个时间之间相差的小时数.
	 * 
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static int intervalHour(final Date date1, final Date date2) {
		Calendar c1 = Calendar.getInstance();
		c1.setTime(date1);
		c1.set(Calendar.MINUTE, 0);
		c1.set(Calendar.MILLISECOND, 0);
		Calendar c2 = Calendar.getInstance();
		c2.set(Calendar.MINUTE, 0);
		c2.set(Calendar.MILLISECOND, 0);
		c2.setTime(date2);
		if (c1.after(c2)) {
			Calendar swap = c1;
			c1 = c2;
			c2 = swap;
		}
		long h = c2.getTimeInMillis() - c1.getTimeInMillis();
		return Integer.valueOf(String.valueOf(h / 1000 / 60 / 60));
	}

	/**
	 * 解析日期型字符串.解析的格式为yyyy-MM-dd,只取日期部分.
	 * 
	 * @param date
	 *            日期型字符串,解析的格式为yyyy-MM-dd.
	 * @return 解析成功的日期
	 * @throws ParseException
	 *             字符串不符合规则.
	 */
	public static Date parseDate(final String date) throws ParseException {
		Calendar c = Calendar.getInstance();
		c.setTime(parseDate(date, "yyyy-MM-dd"));
//		c.set(Calendar.HOUR, 0);
//		c.set(Calendar.MINUTE, 0);
//		c.set(Calendar.SECOND, 0);
		return c.getTime();
	}

	/**
	 * 解析日期时间型字符串.解析的格式为yyyy-MM-dd HH:mm:ss.
	 * 
	 * @param dateTime
	 *            日期时间型字符串.解析的格式为yyyy-MM-dd HH:mm:ss.
	 * @return 解析成功的日期
	 * @throws ParseException
	 *             字符串不符合规则.
	 */
	public static Date parseDateTime(final String dateTime)
			throws ParseException {
		return parseDate(dateTime, "yyyy-MM-dd HH:mm:ss");
	}
		

	/**
	 * 解析日期型字符串.解析的格式为yyyyMMdd,只取日期部分，时间部分为0:00:00.
	 * 
	 * @param date
	 *            日期型字符串,解析的格式为yyyyMMdd.
	 * @return 解析成功的日期
	 * @throws ParseException
	 */
	public static Date parseDateNoPatten(final String date)
			throws ParseException {
		if (date.length() != 8) {
			throw new ParseException("data [" + date
					+ "] format error . eg:20090101", 0);
		}
		Calendar c = Calendar.getInstance();
		c.set(Calendar.YEAR, Integer.valueOf(date.substring(0, 4)));
		c.set(Calendar.MONTH, Integer.valueOf(date.substring(4, 6)) - 1);
		c.set(Calendar.DATE, Integer.valueOf(date.substring(6, 8)));
		return c.getTime();
	}

	/**
	 * <p>
	 * 将一定格式的字符串生成Date实例.
	 * <p>
	 * 
	 * @param date
	 *            解析的字符串.
	 * @param pattern
	 *            格式.如yyyy-MM-dd
	 * @return 解析成功的日期 .
	 * @throws ParseException
	 *             字符串不符合规则.
	 */
	public static Date parseDate(final String date, final String pattern)
			throws ParseException {
		if (StringUtils.isEmpty(date)) {
			throw new IllegalArgumentException(date);
		}
		return new SimpleDateFormat(pattern).parse(date);
	}

	/**
	 * 私有构造方法.
	 */
	private DateUtils() {
		super();
	}

	/**
	 * 判断两个日期是否相等.不包括时间.
	 * 
	 * @param date
	 *            日期1
	 * @param date2
	 *            日期2
	 * @return 如果两个日期相等返回true. 否则返回false.
	 */
	public static boolean equalsDate(Date date, Date date2) {
		if (date == null || date == null) {
			throw new IllegalArgumentException();
		}
		Calendar c1 = Calendar.getInstance();
		c1.setTime(date);
		Calendar c2 = Calendar.getInstance();
		c2.setTime(date2);
		if (c1.get(Calendar.YEAR) != c2.get(Calendar.YEAR)) {
			return false;
		}
		if (c1.get(Calendar.MONTH) != c2.get(Calendar.MONTH)) {
			return false;
		}
		if (c1.get(Calendar.DATE) != c2.get(Calendar.DATE)) {
			return false;
		}
		return true;

	}

	/**
	 * 判断两个日期是否相等.不包括时间.
	 * 
	 * @param date
	 *            日期1
	 * @param date2
	 *            日期2
	 * @return 如果两个日期相等返回true. 否则返回false.
	 */
	public static boolean equalsDateTime(Date date, Date date2) {
		if (date == null || date == null) {
			throw new IllegalArgumentException();
		}
		Calendar c1 = Calendar.getInstance();
		c1.setTime(date);
		Calendar c2 = Calendar.getInstance();
		c2.setTime(date2);

		if (c1.get(Calendar.YEAR) != c2.get(Calendar.YEAR)) {
			return false;
		}
		if (c1.get(Calendar.MONTH) != c2.get(Calendar.MONTH)) {
			return false;
		}
		if (c1.get(Calendar.DATE) != c2.get(Calendar.DATE)) {
			return false;
		}
		if (c1.get(Calendar.HOUR) != c2.get(Calendar.HOUR)) {
			return false;
		}
		if (c1.get(Calendar.MINUTE) != c2.get(Calendar.MINUTE)) {
			return false;
		}
		if (c1.get(Calendar.SECOND) != c2.get(Calendar.SECOND)) {
			return false;
		}
		return true;

	}
}
