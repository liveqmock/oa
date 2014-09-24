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

public class ShowAllMonthList extends ServletBase{
	protected void performTask(HttpServletRequest request,
			HttpServletResponse response)
			throws ServletException, IOException{
		Connection conn = null;
		List list1 = new ArrayList();
		
		Integer months=null;
		try{
		  conn = this.getConnection(Globals.DATASOURCEJNDI);
		  ConnLog.open("ShowAllMonthList");
		 
		  StatSiteHandler logHandler = new StatSiteHandler(conn);	
		  
		  Long allmonth []= new Long[12];
		  allmonth = logHandler.getAll_monthList();
		//  Long counter = logHandler.getcount();

		
		  request.setAttribute("allmonth",allmonth);
		//  request.setAttribute("counter",counter);
		//  System.out.println("99wwwwwww** "+time2);
		  
		  this.forward(request, response, "/statsite/showSiteListAllMonth.jsp");
		}
		catch(Exception e){
			System.out.println(e.toString());
			handleError(e);
		}finally {
			try {
				if (conn != null){
					conn.close();
					ConnLog.close("ShowAllMonthList");
				}
			} catch (SQLException e1) {
				
				e1.printStackTrace();
			}
		}
	  	
		
	}
	
}
