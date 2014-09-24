/*
 * Created on 2004-5-22
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.icss.oa.netoffice.journal.admin;

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
import com.icss.oa.config.TypeConfig;
import com.icss.oa.netoffice.journal.handler.JournalHandler;
import com.icss.oa.netoffice.setnetoffice.handler.SetNetofficeHandler;

/**
 * @author firecoral
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class CalendarChoiceServlet2 extends ServletBase{

	/* (non-Javadoc)
	 * @see com.icss.j2ee.servlet.ServletBase#performTask(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	protected void performTask(HttpServletRequest request, HttpServletResponse response) 
	throws ServletException, IOException {
		Connection conn = null;
		List list1 = new ArrayList();
		Integer months=null;
		String date1=request.getParameter("date");
		request.setAttribute("date",date1);
		try{
		  conn = this.getConnection(Globals.DATASOURCEJNDI);		  
		  	ConnLog.open("CalendarChoiceServlet2");
		  long date2=JournalHandler.getLongByTime(date1);
		  //long date3=date2+86400000;
		  Long date22=new Long(date2);
		  //Long date33=new Long(date3);
			
		//  String time2=JournalHandler.getTime(date2);
		 // String time3=JournalHandler.getTime(date3);
		  //System.out.print("======================================="+(date2%86400000));
		  //System.out.print("======================================="+time2);
		  //System.out.print("======================================="+time3);
		  JournalHandler journalHandler = new JournalHandler(conn);	
		  list1=journalHandler.getListInTimeSegment(date22);
		  System.out.println(list1.size());
		  request.setAttribute("list",list1);
		  //System.out.println("get list success!...........");
		  
		  SetNetofficeHandler setHandler=new SetNetofficeHandler(conn);
				  months=setHandler.getMonthsByType(TypeConfig.JOURNAL_TYPE);
		  
		  //System.out.println("months is...........="+months);
		  this.forward(request, response, "/netoffice/journal/journal2.jsp?months="+months); //
		}
		catch(Exception e){
			e.printStackTrace();
			System.out.println(e.toString());
			handleError(e);
		}finally {
			try {
				if (conn != null){
					conn.close();
					ConnLog.close("CalendarChoiceServlet2");
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		
	}

}
