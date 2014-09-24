package com.icss.oa.statsite.admin;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.common.log.ConnLog;
import com.icss.j2ee.servlet.ServletBase;
import com.icss.j2ee.util.Globals;
import com.icss.oa.statsite.handler.StatSiteHandler;
import com.icss.oa.statsite.vo.*;

public class ShowAveaList extends ServletBase{
	protected void performTask(HttpServletRequest request,
			HttpServletResponse response)
			throws ServletException, IOException{
		Connection conn = null;
		List list1 = new ArrayList();
		Integer months=null;
		try{
	      
		  //System.out.println("-----------------sunchuanting'hero1---------------------------------");
		  conn = this.getConnection(Globals.DATASOURCEJNDI);
		  ConnLog.open("ShowAveaList");
		  StatSiteHandler logHandler = new StatSiteHandler(conn);	
		  String starttime = logHandler.getTimeByLong(logHandler.getMin_time());
		  long counterdate = (logHandler.getMax_time().longValue()
		  		            -logHandler.getMin_time().longValue()+1)/(24*60*60*1000);
		  Long counter = logHandler.getcount();
		  
		  long time = System.currentTimeMillis();
		  String time1 = logHandler.getTimeByLong(new Long(time));
		  
		  Long count[] = new Long[25];
		  count=logHandler.getNumber_hour(time1);
		  
		  monthVO month = new monthVO();
		  month =logHandler.getMaxNumber_month(); 
		  
		  dateVO date = new dateVO();
		  date =logHandler.getMaxNumber_date(); 
		  
		  HourVO hour = new HourVO();
		  hour = logHandler.getMaxNumber_hour();
		  if(counter.longValue()==0){counter = new Long(1);}
		  if(counterdate==0){counterdate=1;}
		  
		  String maxAddress=logHandler.getMax_address();
		
		  request.setAttribute("starttime",starttime);              
		  request.setAttribute("counterdate",new Long(counterdate)); 
		  request.setAttribute("counter",counter);                  
		  request.setAttribute("count",count);
		  request.setAttribute("month",month);
		  request.setAttribute("date",date);
		  request.setAttribute("hour",hour);
		  request.setAttribute("maxAddress",maxAddress);
		  
		  //System.out.println("get list success!...........");
	      this.forward(request, response, "/statsite/showAveaList.jsp");
		}
		catch(Exception e){
			System.out.println(e.toString());
			handleError(e);
		}finally {
			try {
				if (conn != null){
					conn.close();
					ConnLog.close("ShowAveaList");
				}
			} catch (SQLException e1) {
				
				e1.printStackTrace();
			}
		}
	  	
		
	}
	
}
