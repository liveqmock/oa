package com.icss.oa.meeting.admin;

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
import com.icss.oa.meeting.handler.HandlerException;
import com.icss.oa.meeting.handler.MeetingroomManagerHandler;

public class AttendPersonServlet extends ServletBase {

	protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Connection conn = null;

		String personid[] = request.getParameterValues("personid");
		String type = request.getParameter("type");

		try {
			conn = this.getConnection(Globals.DATASOURCEJNDI);
			ConnLog.open("AttendPersonServlet");
			MeetingroomManagerHandler handler = new MeetingroomManagerHandler(conn);

			if ("canjia".equals(type)) {
				for (int j = 0; j < personid.length; j++) {
					handler.attendMeetingPerson(Integer.valueOf(personid[j]));
				}
			}

			if ("Notcanjia".equals(type)) {
				for (int j = 0; j < personid.length; j++) {
					handler.UnattendMeetingPerson(Integer.valueOf(personid[j]));
				}
			}

			this.forward(request, response, "/servlet/ListMeetingPersonServlet?page_num=" + request.getParameter("_page_num"));

		} catch (ServiceLocatorException e) {
			e.printStackTrace();
			handleError(e);
		} catch (NumberFormatException e) {
			e.printStackTrace();
			handleError(e);
		} catch (HandlerException e) {
			e.printStackTrace();
			handleError(e);
		} finally {
			try {
				if (conn != null) {
					conn.close();
					ConnLog.close("AttendPersonServlet");
				}
			} catch (SQLException sqle) {
				sqle.printStackTrace();
			}
		}

	}

}
