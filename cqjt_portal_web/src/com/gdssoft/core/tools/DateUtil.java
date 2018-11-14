package com.gdssoft.core.tools;
/*
 *   捷达世软件(深圳)有限公司  重庆交委内网门户
 *   Copyright (C) 2010-2015 ShenZhen JieDaShi Software Limited Company.
 */
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class DateUtil {
	
	private static final Log logger = LogFactory.getLog(DateUtil.class);

	/**
	 * 应用程序的格式化符
	 */
	public static final String DATE_FORMAT_FULL="yyyy-MM-dd HH:mm:ss";
	/**
	 * 短日期格式
	 */
	public static final String DATE_FORMAT_YMD="yyyy-MM-dd"; 
	/**
	 * CST日期格式
	 */
	public static final String DATE_FORMAT_CST="EEE MMM dd HH:mm:ss 'CST' yyyy";

	private static String[] parsePatterns = {"yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", "yyyy-MM", "yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss",
			"yyyy/MM/dd HH:mm", "yyyy/MM", "yyyy.MM.dd", "yyyy.MM.dd HH:mm:ss", "yyyy.MM.dd HH:mm", "yyyy.MM",
			/*"EEE MMM dd HH:mm:ss 'CST' yyyy",*/} ;
	
	/**
	 * 设置当前时间为当天的最初时间（即00时00分00秒）
	 * 
	 * @param cal
	 * @return
	 */
	public static Calendar setStartDay(Calendar cal) {
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		return cal;
	}

	public static Calendar setEndDay(Calendar cal) {
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		return cal;
	}

	/**
	 * 把源日历的年月日设置到目标日历对象中
	 * @param destCal
	 * @param sourceCal
	 */
	public static void copyYearMonthDay(Calendar destCal,Calendar sourceCal){
		destCal.set(Calendar.YEAR, sourceCal.get(Calendar.YEAR));
		destCal.set(Calendar.MONTH, sourceCal.get(Calendar.MONTH));
		destCal.set(Calendar.DAY_OF_MONTH, sourceCal.get(Calendar.DAY_OF_MONTH));
	}

	/**
	 * 格式化日期为
	 * 
	 * @return
	 */
	public static String formatEnDate(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss a");

		return sdf.format(date).replaceAll("上午", "AM").replaceAll("下午", "PM");
	}

	public static Date parseDate(String dateString) {
		Date date = null;
		try {
			date = DateUtils.parseDate(dateString, new String[]{DATE_FORMAT_FULL, DATE_FORMAT_YMD});
		} catch (Exception ex) {
			logger.error("Pase the Date(" + dateString + ") occur errors:"
					+ ex.getMessage());
		}
		return date;
	}
	
	/**
	 * 日期格式化
	 * @author F3229233 2013-1-17 上午8:53:44 
	 * @param d 日期 Date
	 * @return String
	 */
	public static String dateFormat(Date d){
		return dateFormat(d, DATE_FORMAT_FULL);
	}
	/**
	 * 日期格式化
	 * @author F3229233 2013-1-17 上午8:53:44 
	 * @param d 日期 Date
	 * @param pattern 如：'yyyy-MM' ...
	 * @return String
	 */
	public static String dateFormat(Date d,String pattern){
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		return sdf.format(d);
	}

	/**
	 * 字符串转换成日期
	 * @author F3229233 2013-1-17 上午8:55:38 
	 * @param stringDate
	 * @return Date
	 */
	public static Date dateParse(String stringDate){
		return dateParse(stringDate, DATE_FORMAT_FULL);
	}
	/**
	 * 字符串转换成日期
	 * @author F3229233 2013-1-17 上午8:55:38 
	 * @param stringDate
	 * @param pattern
	 * @return Date
	 */
	public static Date dateParse(String stringDate,String pattern){
		Date d = null;
		try {
			if(StringUtils.isNotBlank(stringDate)){
				d = new SimpleDateFormat(pattern).parse(stringDate);
				return d;
			}
		} catch (ParseException e1) {
			logger.error("将"+stringDate+"转换成"+pattern+"时发生错误！", e1);
		} catch (Exception e2) {
			logger.error("日期格式转换错误", e2);
		}
		return null;
	}
	
	/**
	 * 将SCT日期格式的字符串转换成日期格式
	 * @author zhangpeng 20140623
	 * @return
	 */
	public static Date formatCSTDate(String stringDate) {
		
		//CST格式的字符串
		Date date = null;
		try {
			date = new SimpleDateFormat(DATE_FORMAT_CST,Locale.US).parse(stringDate);
		} catch (ParseException e) {
			logger.error("日期格式转换错误", e);
		}
		return date;
	}
	
	/**
	 * 取得当前服务器时间
	 * @return
	 */
	public static Date getCurrentDate() {
		return Calendar.getInstance().getTime();
	}
	/**
	 * 取得当前服务器时间
	 * @return 
	 */
	public static String getCurrentDateStr() {
		return getCurrentDateStr(DATE_FORMAT_FULL);
	}
	/**
	 * 取得当前服务器时间
	 * @param pattern 格式字串
	 * @return 
	 */
	public static String getCurrentDateStr(String pattern) {
		return dateFormat(getCurrentDate(), pattern);
	}
	
	public static Date getCurrentMouthOneDate(Date date){
		Date result = null;
		 SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd"); 
	     //获取前月的第一天
	     Calendar   calendar=Calendar.getInstance();//获取当前日期 
	     calendar.setTime(date);
	     calendar.add(Calendar.MONTH, 0);
	     calendar.set(Calendar.DAY_OF_MONTH,1);//设置为1号,当前日期既为本月第一天 
	     String firstDay = format.format(calendar.getTime());
	     System.out.println("-----1------firstDay:"+firstDay);
	     try {
	    	 result = format.parse(firstDay);
	     } catch (ParseException e) {
			e.printStackTrace();
	     }
	     return result;
	}
	public static Date getMouthOneDate(int value, Date date){
		Date result = null;
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd"); 
		//获取前月的第一天
		Calendar   calendar=Calendar.getInstance();//获取当前日期 
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, value);
		calendar.set(Calendar.DAY_OF_MONTH,1);//设置为1号,当前日期既为本月第一天 
		String firstDay = format.format(calendar.getTime());
		System.out.println("-----1------firstDay:"+firstDay);
		try {
			result = format.parse(firstDay);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	//获得本年第一天的日期  
    public static Date getYearFirst(int value, Date date){  
//        int yearPlus = getYearPlus();  
//        GregorianCalendar currentDate = new GregorianCalendar();  
//        currentDate.add(GregorianCalendar.DATE,yearPlus);  
//        Date yearDay = currentDate.getTime();  
    	Calendar   calendar=Calendar.getInstance();//获取当前日期 
		calendar.setTime(date);
		calendar.add(Calendar.YEAR, value);
		calendar.set(Calendar.DAY_OF_YEAR, 1);//设置为1号,当前日期既为本月第一天 
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd"); 
        String dateStr = format.format(calendar.getTime());
        System.out.println("年："+dateStr);
        Date result = null;
        try {
        	result = format.parse(dateStr);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return result;  
    }  
    public static int getMonth(Date date){ 
    	int result = 0;
    	Calendar   calendar=Calendar.getInstance();//获取当前日期 
		calendar.setTime(date);
		result = calendar.get(Calendar.MONTH) + 1;
		System.out.println("result:"+result);
		return result;
    }

	/**
	 *
	 * @param date
	 * @return
	 */
	public static String getFormatDateString(Date date) {
		if(date == null) return "";
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
	}

	/**
	 * 日期型字符串转化为日期 格式
	 * { "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm",
	 *   "yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm",
	 *   "yyyy.MM.dd", "yyyy.MM.dd HH:mm:ss", "yyyy.MM.dd HH:mm" }
	 */
	public static Date parseAllDate(String str) {
		if (str == null) {
			return null;
		}
		try {
			Date date = DateUtils.parseDate(str.toString(), parsePatterns);
			return date;
		} catch (ParseException e) {
			try {
				return new SimpleDateFormat("EEE MMM dd hh:mm:ss z yyyy", Locale.ENGLISH).parse(str);
			} catch (ParseException ex) {
			}
		}
		return null;
	}

	public static void main(String[] args) throws Exception {
//		Date date = getCurrentMouthOneDate(dateParse("2016-04-30 12:12:12"));
//		Date date1 = getMouthOneDate(-2, date);
//		System.out.println("--->"+date1);
//		System.out.println(getYearFirst(-2,dateParse("2016-04-30 12:12:12")));
//		System.out.println(getMonth(dateParse("2016-1-30 12:12:12")));
//		Date date2 = getYearFirst(0, getCurrentDate());
//		System.out.println("---->date2:"+(date2));
//
//
//		System.out.println("========================");
//
//		System.out.println(parseDate("2016-04-30 12:12:12"));
//		System.out.println(new Date());

		SimpleDateFormat sf = new SimpleDateFormat("EEE MMM dd hh:mm:ss z yyyy", Locale.ENGLISH);
		String datestr = "Wed Jul 11 16:56:15 CST 2018";
		Date date = sf.parse(datestr);
		Date dt = parseAllDate(datestr);
		//System.out.println(dateFormat(date));
		System.out.println(dateFormat(dt));



	}
}
