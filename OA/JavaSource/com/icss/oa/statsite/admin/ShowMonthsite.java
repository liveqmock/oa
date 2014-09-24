package com.icss.oa.statsite.admin;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.*;
import java.text.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;  

import com.icss.common.log.ConnLog;
import com.icss.j2ee.servlet.ServletBase;
import com.icss.j2ee.util.Globals;
import com.icss.oa.statsite.handler.StatSiteHandler;

public class ShowMonthsite extends ServletBase{
	protected void performTask(HttpServletRequest request,
			HttpServletResponse response)
			throws ServletException, IOException{
		Connection conn = null;
		List list1 = new ArrayList();
		
		Integer months=null;
		try{
		  conn = this.getConnection(Globals.DATASOURCEJNDI);
		  ConnLog.open("ShowMonthsite");
		  StatSiteHandler logHandler = new StatSiteHandler(conn);	
		  
		  String year = request.getParameter("monthYear");
		  String month = request.getParameter("MonthMonth");
		  
		  Long time=new Long(System.currentTimeMillis());
		  String time1=logHandler.getTimeByLong(time);
		  String date2 =time1.substring(8,10);
		  Long count4[] = new Long[25];
		  count4=logHandler.getNumber_hour(time1);
		  Long flag = new Long(0);
		  
		  long time111=System.currentTimeMillis();
		  java.util.Date date=new java.util.Date(time111);
	      SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM");
		  String time2 = formatter.format(date);
		  String time11 ="";
		  
		  if(year != null && month != null) 
		  {
		  		if(Integer.parseInt(month)<10){month="0"+month;}
		  	    time1=year+"-"+month+"-"+"01"; 
		  	    time11=year+"-"+month;
		  	    
		  }else{flag =new Long(1);}
		        
		  		if(time2.equals(time11)){flag =new Long(1);}
		        list1 = logHandler.getNumber_monthList(time1);
		  		Long count=logHandler.getNumber_month(time1);	
		  
		  		if (flag.longValue()==1)
		  		 {	count=new Long(count.longValue()+count4[24].longValue());}
		  
		  		request.setAttribute("list",list1);
		  		request.setAttribute("count",count);
		  		request.setAttribute("count4",count4);
		  		request.setAttribute("flag",flag);
		  		request.setAttribute("date2",date2);
		  
		  		try {
					if (conn != null)
						conn.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
		  this.forward(request, response, "/statsite/showSiteListMonth.jsp");
		}
		catch(Exception e){
			System.out.println(e.toString());
			handleError(e);
		}finally {
			try {
				if (conn != null){
					conn.close();
					ConnLog.close("ShowMonthsite");
				}
			} catch (SQLException e1) {
				
				e1.printStackTrace();
			}
		}
	  	
		
	}
	
}
