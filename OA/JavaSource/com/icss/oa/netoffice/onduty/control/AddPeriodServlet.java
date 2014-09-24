/*
 * Created on 2004-8-10
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.icss.oa.netoffice.onduty.control;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.common.log.ConnLog;
import com.icss.j2ee.services.ServiceLocatorException;
import com.icss.j2ee.servlet.ServletBase;
import com.icss.j2ee.util.Globals;
import com.icss.oa.netoffice.onduty.handler.DutyHandler;

/**
 * @author firecoral
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class AddPeriodServlet extends ServletBase{

	/* (non-Javadoc)
	 * @see com.icss.j2ee.servlet.ServletBase#performTask(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	protected void performTask(HttpServletRequest request, HttpServletResponse response) 
	    throws ServletException, IOException {
	    	
			Connection conn = null;
			
			String startDate = request.getParameter("startDate").trim();
			String orgUUid = request.getParameter("orgUUid");
			
			String time1 = request.getParameter("time1").trim();
			String time2 = request.getParameter("time2").trim();
			String time3 = request.getParameter("time3").trim();
			String time4 = request.getParameter("time4").trim();
		
			try {
				conn = this.getConnection(Globals.DATASOURCEJNDI);
				ConnLog.open("AddPeriodServlet");
				if("24".equals(time1.trim())) time1 = "0";
				long starttime = Long.parseLong(time1)*3600000 + Long.parseLong(time2)*60000;
				long endtime = Long.parseLong(time3)*3600000 + Long.parseLong(time4)*60000;
			
				DutyHandler dHandler = new DutyHandler(conn);
				dHandler.addPeriod(orgUUid,starttime,endtime);
			
				this.forward(request,response,"/servlet/SetDutyServlet?startDate="+startDate);
			
			} catch (ServiceLocatorException e) {
				e.printStackTrace();
			} finally{
				try {
					if(!conn.isClosed()){
						conn.close();
						ConnLog.close("AddPeriodServlet");
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		
	}

}
