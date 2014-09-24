/*
 * Created on 2004-8-11
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.icss.oa.netoffice.onduty.control;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.common.log.ConnLog;
import com.icss.j2ee.servlet.ServletBase;
import com.icss.j2ee.util.Globals;
import com.icss.oa.netoffice.onduty.handler.DutyHandler;

/**
 * @author firecoral
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class GetPhoneServlet extends ServletBase{

	/* (non-Javadoc)
	 * @see com.icss.j2ee.servlet.ServletBase#performTask(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	protected void performTask(HttpServletRequest request, HttpServletResponse response) 
	    throws ServletException, IOException {
	    	
			Connection conn = null;
			
			List phoneList = new ArrayList();
			
			String orgUUid = request.getParameter("orgUUid");
			String personUUid = request.getParameter("personUUid");
			String personName = request.getParameter("personName");
			
			try {
				
				conn = this.getConnection(Globals.DATASOURCEJNDI);
				ConnLog.open("GetPhoneServlet");
				DutyHandler dHandler = new DutyHandler(conn);
				//personName 为要查的用户的名称
				phoneList = dHandler.GetPhone(orgUUid,personUUid);
				
				request.setAttribute("phoneList",phoneList);
				request.setAttribute("personUUid",personUUid);
				request.setAttribute("personName",personName);
				
				this.forward(request,response,"/netoffice/onduty/getPhone.jsp");
				
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				if (conn != null)
					try {
						conn.close();
						ConnLog.close("GetPhoneServlet");
					} catch (Exception e2) {
						e2.printStackTrace();
					}
			}
		
	}

}
