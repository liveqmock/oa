package com.icss.oa.statsite.admin;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.common.log.ConnLog;
import com.icss.j2ee.servlet.ServletBase;
import com.icss.j2ee.util.Globals;
import com.icss.oa.statsite.handler.StatSiteHandler;

public class ShowWeekList extends ServletBase{
	protected void performTask(HttpServletRequest request,
			HttpServletResponse response)
			throws ServletException, IOException{
		Connection conn = null;
		try{	

		conn = this.getConnection(Globals.DATASOURCEJNDI);	
		ConnLog.open("ShowWeekList");
		long time = System.currentTimeMillis();
		StatSiteHandler logHandler = new StatSiteHandler(conn);
		String time1 = logHandler.getTimeByLong(new Long(time));
		
		Long week[] = new Long[8];
		week=logHandler.getNumber_week(time1);
		Long count4[] = new Long[25];
		count4=logHandler.getNumber_hour(time1);
		try {
			if (conn != null)
				conn.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		int week1 = java.util.Calendar.getInstance().get(java.util.Calendar.DAY_OF_WEEK)-1;
		
		long dateArray[] = new long[7];  
		
		
		for(int p=0;p<7;p++){
			dateArray[p] = time+(2-week1+p)*(24*60*60*1000);
		
			}
		
		
		request.setAttribute("week",week);
		request.setAttribute("count4",count4);
		request.setAttribute("week1",new Integer(week1));
		request.setAttribute("date",dateArray);
		
		this.forward(request, response, "/statsite/showSiteListWeek.jsp");
		}
		catch(Exception e){
			System.out.println(e.toString());
			handleError(e);
		}finally {
			try {
				if (conn != null){
					conn.close();
					ConnLog.close("ShowWeekList");
				}
			} catch (SQLException e1) {
				
				e1.printStackTrace();
			}
		}
	  	
		
	}
	
}
