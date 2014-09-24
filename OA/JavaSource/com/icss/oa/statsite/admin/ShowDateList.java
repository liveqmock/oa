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

public class ShowDateList extends ServletBase{
	protected void performTask(HttpServletRequest request,
			HttpServletResponse response)
			throws ServletException, IOException{
		Connection conn = null;
		try{	
        
		conn = this.getConnection(Globals.DATASOURCEJNDI);	
		ConnLog.open("ShowDateList");
		String time2 = request.getParameter("Time");
		if(time2!=null)
		{	this.forward(request, response, "/servlet/ShowHourList");}
		long time = System.currentTimeMillis();
		StatSiteHandler logHandler = new StatSiteHandler(conn);
		String time1 = logHandler.getTimeByLong(new Long(time));
		Long count[] = new Long[25];
		
		/*for(int i=0;i<25;i++)
		{	if( count[i]==null )
		         {  
					count[i]=new Long(0);
		         }
		}*/
		
		count=logHandler.getNumber_hour(time1);

		request.setAttribute("count",count);
		this.forward(request, response, "/statsite/showSiteListDate.jsp");
		}
		catch(Exception e){
			System.out.println(e.toString());
			handleError(e);
		}finally {
			try {
				if (conn != null){
					conn.close();
					ConnLog.close("ShowDateList");
				}
			} catch (SQLException e1) {
				
				e1.printStackTrace();
			}
		}
	  	
		
	}
	
}
