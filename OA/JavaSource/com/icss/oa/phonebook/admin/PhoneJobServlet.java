/*
 * Created on 2004-8-23
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.icss.oa.phonebook.admin;

import java.io.IOException;
import java.sql.Connection;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.common.log.ConnLog;
import com.icss.j2ee.servlet.ServletBase;
import com.icss.j2ee.util.Globals;
import com.icss.oa.phonebook.handler.PhoneHandler;

/**
 * @author firecoral
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class PhoneJobServlet extends ServletBase{

	protected void performTask(HttpServletRequest request, HttpServletResponse response) 
	   throws ServletException, IOException {
	   	
		Connection conn = null;
		List jobList = new ArrayList();
		try {
			conn = this.getConnection(Globals.DATASOURCEJNDI);
			ConnLog.open("PhoneJobServlet");
			PhoneHandler phandler = new PhoneHandler(conn);
			jobList = phandler.GetAllJobGeneral();
			
			request.setAttribute("jobList",jobList);
			
			this.forward(request,response,"/phonebook/phonejob.jsp");
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if (conn != null)
				try {
					conn.close();
					ConnLog.close("PhoneJobServlet");
				} catch (Exception e2) {
					e2.printStackTrace();
				}
		}
	}
}
