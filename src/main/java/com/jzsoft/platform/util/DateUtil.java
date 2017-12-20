package com.jzsoft.platform.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.apache.commons.lang.StringUtils;

/**
 * 日期工具类
 */
public class DateUtil {
    public static final String FORMAT_NORM_DAY = "yyyy-MM-dd";
    public static final String FORMAT_NORM_HOUR = "yyyy-MM-dd HH";
    public static final String FORMAT_NORM_MINUTE = "yyyy-MM-dd HH:mm";
    public static final String FORMAT_NORM_SECOND = "yyyy-MM-dd HH:mm:ss";
    public static final String FORMAT_NORM_MILLISECOND = "yyyy-MM-dd HH:mm:ss:SSS";
    public static final String FORMAT_SERIAL_DAY = "yyyyMMdd";
    public static final String FORMAT_SERIAL_HOUR = "yyyyMMddHH";
    public static final String FORMAT_SERIAL_MINUTE = "yyyyMMddHHmm";
    public static final String FORMAT_SERIAL_SECOND = "yyyyMMddHHmmss";
    public static final String FORMAT_SERIAL_MILLISECOND = "yyyyMMddHHmmssSSS";
    public static final String FORMAT_CHINA_MONTH = "yyyy年MM月";
    public static final String FORMAT_CHINA_DAY = "yyyy年MM月dd日";
    public static final String FORMAT_CHINA_HOUR = "yyyy年MM月dd日 HH";
    public static final String FORMAT_CHINA_MINUTE = "yyyy年MM月dd日 HH:mm";
	/**
	 * 根据自定义的格式获取日期字符串
	 * @param customFormat 自定义的格式
	 * @return
	 */
	public static String getCurrDateString(String customFormat) {
		return new SimpleDateFormat(customFormat).format(new Date());
	}

	/**
	 * 得到当前日期字符串，格式为 yyyy-MM-dd
	 * @return 当前日期的字符串
	 */
	public static String getCurrDateString() {
		return new SimpleDateFormat(FORMAT_NORM_DAY).format(new Date());
	}

	/**
	 * 按日期得到字符串，格式为 yyyy-MM-dd
	 * @param date 日期
	 * @return
	 */
	public static String getDateString(Date date) {
		return new SimpleDateFormat(FORMAT_NORM_DAY).format(date);
	}

	/**
	 * 得到当前日期字符串，格式为 yyyy年MM月dd日
	 * @return 当前日期的字符串
	 */
	public static String getCurrDateStringChina() {
		return new SimpleDateFormat(FORMAT_CHINA_DAY).format(new Date());
	}

	/**
	 * 按日期得到字符串，格式为 yyyy年MM月dd日
	 * @param date 日期
	 * @return
	 */
	public static String getDateStringChina(Date date) {
		return new SimpleDateFormat(FORMAT_CHINA_DAY).format(date);
	}

	/**
	 * 得到当前精确到分的日期字符串，格式为 yyyy-MM-dd HH:mm
	 * @return 当前日期的字符串
	 */
	public static String getCurrDateMinString() {
		return new SimpleDateFormat(FORMAT_NORM_MINUTE).format(new Date());
	}

	/**
	 * 按日期得到精确到秒的字符串，格式为 yyyy-MM-dd HH:mm
	 * @param date
	 * @return
	 */
	public static String getDateMinString(Date date) {
		return new SimpleDateFormat(FORMAT_NORM_MINUTE).format(date);
	}

	/**
	 * 得到当前精确到秒的日期字符串，格式为 yyyy-MM-dd HH:mm:ss
	 * @return 当前日期的字符串
	 */
	public static String getCurrDateSecondString() {
		return new SimpleDateFormat(FORMAT_NORM_SECOND).format(new Date());
	}

	/**
	 * 按日期得到精确到秒的日期字符串，格式为 yyyy-MM-dd HH:mm:ss
	 * @param date
	 * @return
	 */
	public static String getDateSecondString(Date date) {
		return new SimpleDateFormat(FORMAT_NORM_SECOND).format(date);
	}

	/**
	 * 得到当前精确到毫秒的日期字符串，格式为yyyy-MM-dd HH:mm:ss:SSS
	 * 
	 * @return 当前日期的字符串
	 */
	public static String getCurrDateMillisecondString() {
		return new SimpleDateFormat(FORMAT_NORM_MILLISECOND).format(new Date());
	}

	/**
	 * @param date
	 * @param format
	 * @return 日期的字符串
	 */
	public static String getDateString(Date date, String format) {
		return new SimpleDateFormat(format).format(date);
	}
	
	/**
	 * 按字符串得到日期对象，格式为 yyyy-MM-dd
	 * @param dateStr 日期字符串
	 * @return
	 */
	public static Date getDate(String dateStr) {
		if (StringUtils.isEmpty(dateStr)) {
			return null;
		}
		SimpleDateFormat df = new SimpleDateFormat(FORMAT_NORM_DAY);
		Date date = null;
		try {
			date = df.parse(dateStr);
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
		return date;
	}

	public static Date getDate(String dateStr, String customFormat) {
		if (StringUtils.isEmpty(dateStr) || StringUtils.isEmpty(customFormat)) {
			return null;
		}
		SimpleDateFormat df = new SimpleDateFormat(customFormat);
		Date date = null;
		try {
			date = df.parse(dateStr);
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
		return date;
	}

	/**
	 * 按字符串得到日期对象，格式为 yyyy-MM-dd HH:mm
	 * @param str
	 * @return
	 */
	public static Date getDateMin(String str) {
		if (StringUtils.isEmpty(str)) {
			return null;
		}
		SimpleDateFormat df = new SimpleDateFormat(FORMAT_NORM_MINUTE);
		Date date = null;
		try {
			date = df.parse(str);
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
		return date;
	}

	/**
	 * 按字符串得到日期对象，格式为 yyyy-MM-dd HH:mm:ss
	 * @param str
	 * @return
	 */
	public static Date getDateSecond(String str) {
		if (StringUtils.isEmpty(str)) {
			return null;
		}
		SimpleDateFormat df = new SimpleDateFormat(FORMAT_NORM_SECOND);
		Date date = null;
		try {
			date = df.parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
	

	/**
	 * 得到当前月的第一天
	 * @param date
	 * @return
	 */
	public static Date getCurrentMonthFirstDay(Date date) {
		return getMonthFirstDay(date,0);
	}
	

	/**
	 * 得到当前月的最后一天
	 * @param date
	 * @return 
	 */
	public static Date getCurrentMonthLastDay(Date date) {
		return getMonthLastDay(date,0);
	}
	
	/**
	 * 得到上个月的第一天
	 * @param date
	 * @return
	 */
	public static Date getPrevMonthFirstDay(Date date) {
		return getMonthFirstDay(date,-1);
	}
	
	/**
	 * 得到上个月的最后一天
	 * @param date
	 * @return
	 */
	public static Date getPrevMonthLastDay(Date date) {
		return getMonthLastDay(date,-1);
	}
	/**
	 * 得到下个月的第一天
	 * @param date
	 * @return
	 */
	public static Date getNextMonthFirstDay(Date date) {
		return getMonthFirstDay(date,1);
	}

	/**
	 * 得到下个月的最后一天
	 * @param date
	 * @return
	 */
	public static Date getNextMonthLastDay(Date date) {
		return getMonthLastDay(date,1);
	}

	
	/**
	 * 得到月份的第一天
	 * @param date
	 * @incrementalOfMonth 相对当前date日期所属月份的增量，例如（1或-1）
	 * @return
	 */
	public static Date getMonthFirstDay(Date date,int incrementalOfMonth) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.MONTH, incrementalOfMonth);
		c.set(Calendar.DAY_OF_MONTH, 1);
		Date d = c.getTime();
		return d;
	}
	
	/**
	 * 得到月份的最后一天
	 * @param date
	 * @incrementalOfMonth 相对当前date日期所属月份的增量，例如（1或-1）
	 * @return
	 */
	public static Date getMonthLastDay(Date date,int incrementalOfMonth) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.MONTH, incrementalOfMonth);
		c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));
		Date d = c.getTime();
		return d;
	}
	
	/**
	 * 是否过期
	 * @param dateString 开始日期字符串，格式 yyyy-MM-dd HH:mm
	 * @param limit 有效天数
	 * @return
	 */
	public static boolean isTimeOut(String dateString, int limit) {
		Date dueDate = getDateMin(dateString);
		Date nowDate = getDateMin(DateUtil.getCurrDateMinString());
		if ((nowDate.getTime() - dueDate.getTime()) < limit * 24 * 60 * 60 * 1000) {
			return false;
		}
		return true;
	}

	/**
	 * 比较时间大小
	 * @param d1 格式为 yyyy-MM-dd HH:mm:ss
	 * @param d2 格式为 yyyy-MM-dd HH:mm:ss
	 * @return
	 */
	public static boolean compareTime(String d1, String d2) {
		Date date1 = getDateSecond(d1);
		Date date2 = getDateSecond(d2);
		if (date1.getTime() - date2.getTime() >= 0) {
			return true;
		}
		return false;
	}

	/**
	 * 比较日期大小
	 * @param d1 格式为 yyyy-MM-dd
	 * @param d2 格式为 yyyy-MM-dd
	 * @return
	 */
	public static boolean compareDay(String d1, String d2) {
		Date date1 = getDate(d1);
		Date date2 = getDate(d2);
		if (date1.getTime() - date2.getTime() >= 24 * 60 * 60 * 1000) {
			return true;
		}
		return false;
	}

	/**
	 * 求两个日期相差天数
	 * @param startDateStr 开始日期字符串，格式yyyy-MM-dd
	 * @param endDateStr 结束日期字符串，格式yyyy-MM-dd
	 * @return 天数，整数
	 */
	public static long getIntervalDays(String startDateStr, String endDateStr) {
		Date d1 = getDate(startDateStr);
		Date d2 = getDate(endDateStr);
		return (d2.getTime() - d1.getTime()) / (3600 * 24 * 1000);
	}

	/**
	 * 
	 * 取得给定日期加上一定天数后的日期对象.
	 * 
	 * @param date
	 * 
	 * 给定的日期对象
	 * 
	 * @param amount
	 * 
	 * 需要添加的天数，如果是向前的天数，使用负数就可以.
	 * 
	 * @param format
	 * 
	 * 输出格式.
	 * 
	 * @return Date 加上一定天数以后的Date对象.
	 * 
	 */
	public static String getFormatDateAdd(Date date, int amount) {
		return getDateString(getDateAdd(date, amount));
	}
	
	/**
	 * 
	 * 取得给定日期加上一定天数后的日期对象.
	 * 
	 * @param date
	 * 
	 * 给定的日期对象
	 * 
	 * @param amount
	 * 
	 * 需要添加的天数，如果是向前的天数，使用负数就可以.
	 * 
	 * @param format
	 * 
	 * 输出格式.
	 * 
	 * @return Date 加上一定天数以后的Date对象.
	 * 
	 */
	public static Date getDateAdd(Date date, int amount) {
		Calendar cal = new GregorianCalendar();
		cal.setTime(date);
		cal.add(GregorianCalendar.DATE, amount);
		return cal.getTime();
	}

	/**
	 * 
	 * 返回指定日期的前一天。<br>
	 * @param sourceDate
	 * 
	 */
	public static String getYestoday(String sourceDate) {
		return getFormatDateAdd(getDate(sourceDate), -1);
	}

	/**
	 * 返回指定日期的后一天。<br>
	 * @param sourceDate
	 * 
	 */
	public static String getFormatDateTommorrow(String sourceDate) {
		return getFormatDateAdd(getDate(sourceDate), 1);
	}

    /**
     * 获得两个日期之差
     */
    public static long getDiff(String dateStart,String dateEnd){
        long value=1;
        SimpleDateFormat format = new SimpleDateFormat(FORMAT_SERIAL_SECOND);
        Date d1;
        Date d2;
        try {
            d1 = format.parse(dateStart);
            d2 = format.parse(dateEnd);
            //毫秒ms
            long diff = d2.getTime() - d1.getTime();
            value = diff / (60 * 60 * 1000);
            long lBase=diff%(60 * 60 * 1000);
            if(lBase>0)
                value=value+1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return value;
    }
	
	/*****************************************week start************************************************/
	/**
	 * 计算以某天开始的第几周星期几是几月几日
	 * 
	 * @param startDate
	 *            开始日期
	 * @param weekNum
	 *            第几周
	 * @param dayOfWeek
	 *            星期几 周日传7
	 * @return
	 */
	public static String getDateWithTWeek(String startDate, Integer weekNum, Integer dayOfWeek) {
		Date date = getDateWithWeek(startDate, weekNum, dayOfWeek);
		return new SimpleDateFormat(FORMAT_NORM_DAY).format(date);
	}
	/**
	 * 计算以某天开始的第几周星期几是几月几日
	 * 
	 * @param startDate
	 *            开始日期
	 * @param weekNum
	 *            第几周
	 * @param dayOfWeek
	 *            星期几 周日传7
	 * @return
	 */
	public static Date getDateWithWeek(String startDate, Integer weekNum, Integer dayOfWeek) {
		Calendar c = Calendar.getInstance();
		c.setFirstDayOfWeek(Calendar.MONDAY);
		c.setTime(DateUtil.getDate(startDate));
		c.add(Calendar.DATE, (weekNum - 1) * 7);
		c.set(Calendar.DAY_OF_WEEK, (dayOfWeek + 1) % 7);
		return c.getTime();
	}

	/**
	 * 获取某天是以哪天为开始的第几周星期几
	 * 
	 * @param startDate
	 *            开始日期
	 * @param searchDate
	 *            要查询的日期
	 * @return array[0] 第几周， 如果开始日期大于要查询的日期，则返回负数周
	 * 		   array[1] 星期几， 如果开始日期大于要查询的日期，则正常返回星期几
	 */
	public static Integer[] getTWeekWithDate(String startDate, String searchDate) {
		Calendar c = Calendar.getInstance();
		c.setFirstDayOfWeek(Calendar.MONDAY);
		c.setTime(DateUtil.getDate(startDate));
		c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		long nd = 1000 * 24 * 60 * 60 * 7;
		Integer[] tweek = new Integer[2];
		long spaceTime = DateUtil.getDate(searchDate).getTime() - c.getTime().getTime()+1;
		Integer weekNum = Integer.parseInt(String.valueOf(spaceTime / nd));
		c.setTime(DateUtil.getDate(searchDate));
		if (spaceTime<0){
			tweek[0] = weekNum - 1;
		}else{
			tweek[0] = weekNum + 1;
		}
		Integer dayOfWeek = c.get(Calendar.DAY_OF_WEEK) - 1;
		if (dayOfWeek == 0)
			tweek[1] = 7;
		else
			tweek[1] = dayOfWeek;
		return tweek;
	}

	/**
	 * 获取日期范围内(绝对值)有多少周
	 * 
	 * @desc 例如：2012-11-06(周二)到2012-11-19(周一)范围内，有3周
	 * 
	 * @param startDate
	 *            开始日期
	 * @param endDate
	 *            结束日期
	 * @return 天数
	 */
	public static int getWeekNumWithDateScope(String startDate, String endDate) {
		int result = 0;
		Date sdate = DateUtil.getDate(startDate);
		long LEN = Math.abs(DateUtil.getIntervalDays(startDate, endDate));//间隔多少天
		//		if (LEN < 0) return 0;
		int dayOfWeek = 0;
		for (int i = 0; i <= LEN; i++) {
			//遍历开始日期后的每一天
			String dayAfterStartDate = DateUtil.getFormatDateAdd(sdate, i);
			dayOfWeek = DateUtil.getDayOfWeek(dayAfterStartDate);//判断日期是周几，周日至周六分别是0到6
			if (dayOfWeek == 0) { //到周日就算一周
				result++;
				long rest = LEN - i;
				if (rest > 0 && rest < 7) {//到周日后还有剩余天数且不足7天，再加一周
					result++;
					break;
				}
			}
		}
		return result == 0 ? 1 : result;//不足7天的算一周
	}

	/**
	 * 获取日期范围内的哪些天
	 * 
	 * @param startDate
	 *            开始日期
	 * @param endDate
	 *            结束日期
	 * @return array[] 日期的集合
	 */
	@SuppressWarnings("boxing")
	public static String[] getDaysWithDateScope(String startDate, String endDate) {
		if (DateUtil.compareDay(startDate, endDate)) {
			throw new RuntimeException("开始日期必须小于于结束日期");
		}
		if (startDate.equals(endDate)) {
			return new String[] { startDate };
		}
		Date sdate = DateUtil.getDate(startDate);
		Long LEN = DateUtil.getIntervalDays(startDate, endDate);// 用来计算两日期之间总共有多少天
		String[] dateResult = new String[LEN.intValue() + 1];
		dateResult[0] = startDate;
		for (int i = 1; i < LEN + 1; i++) {
			dateResult[i] = DateUtil.getFormatDateAdd(sdate, i);
		}
		return dateResult;
	}

	/**
	 * 获取日期范围内的第几周有哪些天
	 * 
	 * @desc 例如：2012-09-05(周三)到2013-01-18(周五)范围内，第1周有5天(周三到周日)，第2周有7天(周一到周日)
	 * 
	 * @param startDate
	 *            开始日期
	 * @param endDate
	 *            结束日期
	 * @param weekNum
	 *            第几周
	 * @return array[] 日期的集合
	 */
	@SuppressWarnings("boxing")
	public static String[] getDaysWithWeekNum(String startDate, String endDate, int weekNum) {
		List<String> list = Arrays.asList(getDaysWithDateScope(startDate, endDate));
		List<String> days = new ArrayList<String>();
		for (int i = 1; i <= 7; i++) {
			String dateWithTWeek = getDateWithTWeek(startDate, weekNum, i);
			if (list.contains(dateWithTWeek)) {
				days.add(dateWithTWeek);
			}
		}
		return days.toArray(new String[days.size()]);
	}
	
	/**
	 * 获取当前是星期几。
	 * @return 返回一个代表当期日期是星期几的数字。0表示星期天、1表示星期一、6表示星期六。
	 */
	public static int getDayOfWeek() {
		return getDayOfWeek(new Date());
	}
	
	/**
	 * 根据指定的年、月、日返回当前是星期几。
	 * @param date(yyyy-mm-dd)
	 * @return 返回一个代表当期日期是星期几的数字。0表示星期天、1表示星期一、6表示星期六。
	 */
	public static int getDayOfWeek(String dateStr) {
		return getDayOfWeek(getDate(dateStr));
	}
	
	/**
	 * 根据指定的日期对象返回当前是星期几。
	 * @param date
	 * @return 返回一个代表当期日期是星期几的数字。0表示星期天、1表示星期一、6表示星期六。
	 */
	public static int getDayOfWeek(Date date){
		Calendar cal = new GregorianCalendar();
		cal.setTime(date);
		int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
		if (w < 0) {
			w = 0;
		}
		return w;
	}

	/**
	 * 返回当前日期是当前月的第几天。
	 * @return 返回一个代表当期日期是当前月的第几天的数字。
	 */
	public static int getDayOfMonth() {
		return getDayOfMonth(new Date());
	}
	
	/**
	 * 根据指定的年、月、日返回当前日期是当前月的第几天。
	 * @param date(yyyy-mm-dd)
	 * @return 返回一个代表当期日期是当前月的第几天的数字。
	 */
	public static int getDayOfMonth(String dateStr) {
		return getDayOfMonth(getDate(dateStr));
	}
	
	/**
	 * 根据指定的日期对象返回当前日期是当前月的第几天。
	 * @param date
	 * @return 返回一个代表当期日期是当前月的第几天的数字。
	 */
	public static int getDayOfMonth(Date date) {
		Calendar cal = new GregorianCalendar();
		cal.setTime(date);
		int d = cal.get(Calendar.DAY_OF_MONTH);
		if (d < 0) {
			d = 0;
		}
		return d;
	}
	
	/**
	 * 得到星期显示值
	 * @param dateStr 日期字符串，格式yyyy-MM-dd
	 * @return
	 */
	public static String getWeekStr(String dateStr) {
		String[] weekDays = { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" };
		return weekDays[getDayOfWeek(dateStr)];
	}
	
	/*****************************************week end************************************************/
	
	public static void main(String[] args) {
//		System.out.println(getWeekStr("2016-11-01"));
//		Date d = getNextMonthFirstDay(new Date());
//		SimpleDateFormat sf2 = new SimpleDateFormat("yyyy-MM-dd");
//		System.out.println(sf2.format(d));
//		System.out.println(getDayOfMonth("2016-10-01"));
	}
}