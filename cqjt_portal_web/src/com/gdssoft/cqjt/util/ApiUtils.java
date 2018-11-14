package com.gdssoft.cqjt.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class ApiUtils {

	/**
     * 默认日期格式
     */
    public static String DEFAULT_FORMAT = "yyyy-MM-dd";
	
	/**
     * 格式化日期
     * @param date 日期对象
     * @return String 日期字符串
     */
    public static String formatDate(Date date){
        SimpleDateFormat f = new SimpleDateFormat(DEFAULT_FORMAT);
        String sDate = f.format(date);
        return sDate;
    }
     
    /**
     * 获取当年的第一天
     * @param year
     * @return
     */
    public static Date getCurrYearFirst(){
        Calendar currCal=Calendar.getInstance();  
        int currentYear = currCal.get(Calendar.YEAR);
        return getYearFirst(currentYear);
    }
     
    /**
     * 获取当年的最后一天
     * @param year
     * @return
     */
    public static Date getCurrYearLast(){
        Calendar currCal=Calendar.getInstance();  
        int currentYear = currCal.get(Calendar.YEAR);
        return getYearLast(currentYear);
    }
     
    /**
     * 获取某年第一天日期
     * @param year 年份
     * @return Date
     */
    public static Date getYearFirst(int year){
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(Calendar.YEAR, year);
        Date currYearFirst = calendar.getTime();
        return currYearFirst;
    }
     
    /**
     * 获取某年最后一天日期
     * @param year 年份
     * @return Date
     */
    public static Date getYearLast(int year){
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(Calendar.YEAR, year);
        calendar.roll(Calendar.DAY_OF_YEAR, -1);
        Date currYearLast = calendar.getTime();
         
        return currYearLast;
    }
    
    public static Date getFristDayYear(Date date){
    	Calendar currCal=Calendar.getInstance();  
    	if(date != null){
    		currCal.setTime(date);
		}
    	int currentYear = currCal.get(Calendar.YEAR);
    	return getYearFirst(currentYear);
    }
    public static Date getLastDayYear(Date date){
    	Calendar currCal=Calendar.getInstance();  
    	if(date != null){
    		currCal.setTime(date);
		}
    	int currentYear = currCal.get(Calendar.YEAR);
        return getYearLast(currentYear);
    }
	
	public static void main(String[] args) {
		Date date;
		try {
			date = new SimpleDateFormat("yyyy-mm-dd").parse("2014-12-12");
			System.out.println(formatDate(getFristDayYear(date)));
			System.out.println(formatDate(getLastDayYear(date)));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
