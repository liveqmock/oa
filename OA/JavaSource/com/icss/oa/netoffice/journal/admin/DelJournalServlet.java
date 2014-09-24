/*
 * Created on 2004-4-5
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package com.icss.oa.netoffice.journal.admin;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.common.log.ConnLog;
import com.icss.j2ee.servlet.ServletBase;
import com.icss.j2ee.util.Globals;
import com.icss.oa.netoffice.journal.handler.JournalHandler;

/**
 * @author Administrator
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public class DelJournalServlet extends ServletBase {

	protected void performTask(
		HttpServletRequest request,
		HttpServletResponse response)
		throws ServletException, IOException {
		Connection conn = null;

		String jid[] = request.getParameterValues("jc");
		String date = request.getParameter("date");

		try {
			conn = this.getConnection(Globals.DATASOURCEJNDI);
			ConnLog.open("DelJournalServlet");
			for (int j = 0; j < jid.length; j++) {
				Integer juuid = Integer.valueOf(jid[j]);
				JournalHandler jHandler = new JournalHandler(conn);
				jHandler.deleteJournal(juuid);
			}
			if (date == null) {
				this.forward(request, response, "/servlet/ShowJournalServlet");
			} else {
				this.forward(
					request,
					response,
					"/servlet/CalendarChoiceServlet2?date=" + date);
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
			e.printStackTrace();

		} finally {
			try {
				if (conn != null){
					conn.close();
					ConnLog.close("DelJournalServlet");
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		//response.sendRedirect(request.getContextPath()+"/servlet/ShowServiceTypeServlet");
		// response.sendRedirect("ShowJournalServlet");

	}

}
