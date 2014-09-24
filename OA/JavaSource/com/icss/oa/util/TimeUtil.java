package com.icss.oa.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TimeUtil {

	public static String getCurrentDate(String format){
		return new SimpleDateFormat(format).format(new Date());
	}
	
	public static long dayBetween(long start,long end){
		long between=(end-start)/1000;
		return between/(60*60*24);
	}
	
	public static String getDate(Date data){
		return new SimpleDateFormat("yyyy年MM月dd日 hh:mm:ss").format(data);
	}
	
	public static String getCurrentWeek(){
        Calendar cd=Calendar.getInstance();   
        int dayofWeek=cd.get(Calendar.DAY_OF_WEEK); 
        System.out.println(dayofWeek);
        switch(dayofWeek-1){
        	case 1: return "星期一";
        	case 2: return "星期二";
        	case 3: return "星期三";
        	case 4: return "星期四";
        	case 5: return "星期五";
        	case 6: return "星期六";
        	case 0: return "星期日";
        	default: return "未知"; 
        }
		
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
//		try {
//			Date times = new SimpleDateFormat("yyyy年MM月dd日 hh:mm:ss").parse("2008年5月7日 12:01:01");
//			System.out.println(times.getTime());
//		} catch (ParseException e) {
//			e.printStackTrace();
//		}

		System.out.println(getDate(new Date(Long.parseLong("1211425174968"))));
	}

}
