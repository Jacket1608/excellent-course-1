package io.shuqi.graduation.utils;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class DateUtil {

	public static String formatDate(Timestamp time){
		return formatDate(new java.util.Date(time.getTime()));
	}
	public static String formatDate(java.sql.Date date){
		return formatDate(new java.util.Date(date.getTime()));
	}
	public static String formatDate(Date date){
		return formatDate(date, "yyyy-MM-dd HH:mm:ss");
	}
	public static String formatDate(java.util.Date date,String pattern){
		if(date==null){
			return "";
		}
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		return format.format(date);
	}
	public static Date parseStringToDate(String Date,String pattern) throws ParseException{
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		return format.parse(Date);
	}
}
