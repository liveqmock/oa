/*
 * Created on 2004-4-14
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package com.icss.oa.config;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;

//import java.lang.*;
/**
 * @author Administrator
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public class TypeConfig{
	  //个人办公室内的栏目类型
		public static final int SCHEDULE_TYPE =1;
		public static final int JOURNAL_TYPE =2;
		public static final int MEMO_TYPE =3;
		
	 //日程安排中的事件类型
		public static final String SCHEDULE_TASK_ALLDAY ="0";
		public static final String SCHEDULE_TASK_SEGMENT ="1";
		
	  public void calendConfig(){
		
	  }	
		/**
		 * 每月天数,用于日程安排首页
		 */
	 public int daysOfMon(int mon, int year){
		int days = 0;
		switch(mon){
			case 1:
			case 3:
			case 5:
			case 7:
			case 8:
			case 10:
			case 12:
				days = 31;
				break;
			case 2:
				if((year % 4 == 0 && year % 100 != 0) || year % 400 == 0)
					days = 29;
				else days = 28;
				break;
			case 4:
			case 6:
			case 9:
			case 11:
				days = 30;
				break;
		}
		return days;
	}
	 
	 /**
		 * 将字符串转换为long
		 * @param time
		 * @return long
		 */
		public static long getLongByTime(String time) throws Exception {
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			ParsePosition pos = new ParsePosition(0);
			java.util.Date date = formatter.parse(time, pos);
			if (date == null)
				throw new Exception("the date/time string can not parse");
			return date.getTime();
		}

}
