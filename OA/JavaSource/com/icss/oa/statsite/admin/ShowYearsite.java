package com.icss.oa.statsite.admin;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.common.log.ConnLog;
import com.icss.j2ee.servlet.ServletBase;
import com.icss.j2ee.util.Globals;
import com.icss.oa.statsite.handler.StatSiteHandler;

public class ShowYearsite extends ServletBase{
	protected void performTask(HttpServletRequest request,
			HttpServletResponse response)
			throws ServletException, IOException{  
		Connection conn = null;
		List list1 = new ArrayList();
		
		Integer months=null;
		try{
		  conn = this.getConnection(Globals.DATASOURCEJNDI);
		  ConnLog.open("ShowYearsite");
		  StatSiteHandler logHandler = new StatSiteHandler(conn);	
		  
		  String year = request.getParameter("onlyYear");
		  Long time=new Long(System.currentTimeMillis());
		  String time1=logHandler.getTimeByLong(time);
		  String month2 =time1.substring(5,7);
		  Long count4[] = new Long[25];
		  count4=logHandler.getNumber_hour(time1);
		  Long flag = new Long(0);
		  String time2=(year==null)?logHandler.getCurruntyear(time1):(year+"-01-01");
		  
		 // System.out.println("fdsfdsfsdfds"+time2);
		 // String time2=logHandler.getCurruntyear(time1);
		 // System.out.println("99wwwwwww**aa "+time2);
		  String qq []=new String[13];
		  String qq1 []=new String[13];
		  long count=0;
		  
		  for(int i=1;i<13;i++){
		  	count+=logHandler.getNumber_month(time2).longValue();
		  	qq[i]=String.valueOf(logHandler.getNumber_month(time2).longValue());
		 // 	System.out.println("99wwwwwww**aa "+i+"  "+qq[i]);  
		  	qq1[i]=time2;
		//  	System.out.println("99wwwwwww**bb "+i+"  "+qq1[i]);
		  	time2=logHandler.getNextMonth(time2);
		  	}

		  if(year==null)
		  {   
		  	 flag =new Long(1);
		  	 count+=count4[24].longValue();
		  }
		 
		  request.setAttribute("qq",qq);
		  request.setAttribute("qq1",qq1);
		  request.setAttribute("count",new Long(count));
		  request.setAttribute("flag",flag);
		  request.setAttribute("count4",count4);
		  request.setAttribute("month2",month2);
		  
		//  System.out.println("99wwwwwww** "+time2);
		  
		  this.forward(request, response, "/statsite/showSiteListYear.jsp");
		}
		catch(Exception e){
			System.out.println(e.toString());
			handleError(e);
		}finally {
			try {
				if (conn != null){
					conn.close();
					ConnLog.close("ShowYearsite");
				}
			} catch (SQLException e1) {
				
				e1.printStackTrace();
			}
		}
	  	
		
	}
	
}
