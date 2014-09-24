/*
 * Created on 2004-7-16
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
public class DeleteDutyServlet extends ServletBase{

	/* (non-Javadoc)
	 * @see com.icss.j2ee.servlet.ServletBase#performTask(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	protected void performTask(HttpServletRequest request, HttpServletResponse response) 
	    throws ServletException, IOException{
		
		String orgUUid = request.getParameter("orgUUid");
		Integer dutyid = Integer.valueOf(request.getParameter("dutyid"));
		String startDate = (String)request.getParameter("startDate");
		
		Connection conn = null;
		
		try {
			conn = this.getConnection(Globals.DATASOURCEJNDI);
			ConnLog.open("DeleteDutyServlet");
			DutyHandler dHandler = new DutyHandler(conn);
			dHandler.delDuty(dutyid);
			
			this.forward(request,response,"/servlet/SetDutyServlet?orgid="+orgUUid+"&startDate="+startDate);
			
		} catch (ServiceLocatorException e) {
			e.printStackTrace();
			
		} finally{
			try {
				if(conn!=null){
				    conn.close();
				    ConnLog.close("DeleteDutyServlet");
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		
	}

}
