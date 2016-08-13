package com.mystore.business.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DateUtils {
	
	/**
	 * 得年份
	 * 
	 * */
	public static int getYear(Date date){
		
		Calendar cal = Calendar.getInstance();
		
		if(date != null){
			cal.setTime(date);
		}
		
		return cal.get(Calendar.YEAR);
	}
	
	/**
	 * 得月份
	 * 
	 * */
	public static int getMonth(Date date){

		Calendar cal = Calendar.getInstance();
		
		if(date != null){
			cal.setTime(date);
		}
		
		return cal.get(Calendar.MONTH)+1;
	}

	/**
	 * 得天号
	 * 
	 * */
	public static int getDay(Date date){
		
		Calendar cal = Calendar.getInstance();
		
		if(date != null){
			cal.setTime(date);
		}
		
		return cal.get(Calendar.DATE);
	}
	/**
	 * 得一月总天数
	 * 
	 * */
	public static int getDayOfMonth(Date date){
		
		Calendar cal = Calendar.getInstance();
		
		if(date != null){
			cal.setTime(date);
		}
		
		return cal.getActualMaximum(Calendar.DAY_OF_MONTH);
	}
	/**
	 * 得星期几
	 * 
	 * */
	public static int getDayOfWeek(Date date){
		Calendar cal = Calendar.getInstance();
		
		if(date != null){
			cal.setTime(date);
		}
		
		return  cal.get(Calendar.DAY_OF_WEEK)-1;
	}
	
	/**
	 * 得两个日期之间的所有日期(不含开头结尾)
	 * 
	 * 
	 * */
	public static List<Date> getDates(Date startDate, Date endDate) {
	    
		java.util.Calendar calstart = java.util.Calendar.getInstance();
		java.util.Calendar calend = java.util.Calendar.getInstance();
		calstart.setTime(startDate);
		calend.setTime(endDate);
		// 设置时间为0时
		calstart.set(java.util.Calendar.HOUR_OF_DAY, 0);
		calstart.set(java.util.Calendar.MINUTE, 0);
		calstart.set(java.util.Calendar.SECOND, 0);
		calend.set(java.util.Calendar.HOUR_OF_DAY, 0);
		calend.set(java.util.Calendar.MINUTE, 0);
		calend.set(java.util.Calendar.SECOND, 0);
		long start = calstart.getTimeInMillis();
		long end = calend.getTimeInMillis();
		List<Date> date = new ArrayList<Date>();   
		while ((start += 1000 * 60 * 60 * 24) <= end){
			Date time=new Date(start);
			date.add(time);
		} 

		return date;    //返回天数 eg: 01、02、03

	}
	
	public static void main(String[] args) throws ParseException{
		SimpleDateFormat  formate = new SimpleDateFormat ("yyyy-MM-dd");
		Date date_start = formate.parse("2015-01-10");
		Date date_end = formate.parse("2015-01-11");
		List<Date> list = getDates(date_start,date_end);
		if(list != null && list.size() > 0){
			for(Date date:list){
				System.out.println(formate.format(date));
			}
		}
//		Date date_start_copy = new Date(date_start.getTime());
//		System.out.println(formate.format(date_start));
//		System.out.println(formate.format(date_start_copy));
//		System.out.println(date_start ==date_start_copy);
		
//		Calendar calstartnoon = Calendar.getInstance();
//		calstartnoon.setTime(date_start);
//		
//		System.out.println(date_start.getTime());
//		System.out.println(calstartnoon.getTimeInMillis());
	}
}
