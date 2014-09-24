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
		return new SimpleDateFormat("yyyy��MM��dd�� hh:mm:ss").format(data);
	}
	
	public static String getCurrentWeek(){
        Calendar cd=Calendar.getInstance();   
        int dayofWeek=cd.get(Calendar.DAY_OF_WEEK); 
        System.out.println(dayofWeek);
        switch(dayofWeek-1){
        	case 1: return "����һ";
        	case 2: return "���ڶ�";
        	case 3: return "������";
        	case 4: return "������";
        	case 5: return "������";
        	case 6: return "������";
        	case 0: return "������";
        	default: return "δ֪"; 
        }
		
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
//		try {
//			Date times = new SimpleDateFormat("yyyy��MM��dd�� hh:mm:ss").parse("2008��5��7�� 12:01:01");
//			System.out.println(times.getTime());
//		} catch (ParseException e) {
//			e.printStackTrace();
//		}

		System.out.println(getDate(new Date(Long.parseLong("1211425174968"))));
	}

}
