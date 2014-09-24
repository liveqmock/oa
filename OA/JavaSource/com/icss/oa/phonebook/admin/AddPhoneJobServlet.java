/*
 * Created on 2004-8-23
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.icss.oa.phonebook.admin;

import java.io.IOException;
import java.sql.Connection;

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
public class AddPhoneJobServlet extends ServletBase {

	protected void performTask(
		HttpServletRequest request,
		HttpServletResponse response)
		throws ServletException, IOException {

		Connection conn = null;
		Integer jobLevel = new Integer(request.getParameter("jobLevel"));
		String jobName = request.getParameter("jobName");

		try {
			conn = this.getConnection(Globals.DATASOURCEJNDI);
			ConnLog.open("AddPhoneJobServlet");
			PhoneHandler phandler = new PhoneHandler(conn);
			phandler.NewJob(jobLevel, jobName);

			this.forward(request, response, "/phonebook/addPhoneClose.jsp");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (conn != null)
				try {
					conn.close();
					ConnLog.close("AddPhoneJobServlet");
				} catch (Exception e2) {
					e2.printStackTrace();
				}
		}

	}

}
