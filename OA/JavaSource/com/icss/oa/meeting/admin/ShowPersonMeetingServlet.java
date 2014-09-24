package com.icss.oa.meeting.admin;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.common.log.ConnLog;
import com.icss.j2ee.services.ServiceLocatorException;
import com.icss.j2ee.servlet.ServletBase;
import com.icss.j2ee.util.Globals;
import com.icss.oa.meeting.handler.HandlerException;
import com.icss.oa.meeting.handler.MeetingroomManagerHandler;

public class ShowPersonMeetingServlet extends ServletBase {

	protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Connection conn = null;

		try {
			conn = this.getConnection(Globals.DATASOURCEJNDI);
			ConnLog.open("ShowPersonMeetingServlet");
			MeetingroomManagerHandler handler = new MeetingroomManagerHandler(conn);

			List list = handler.getPersonMeetingList(handler.getUserId());
			request.setAttribute("list", list);

			if (conn != null)
				try {
					conn.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}

			this.forward(request, response, "/meeting/showPersonMeetingList.jsp");

		} catch (ServiceLocatorException e) {
			e.printStackTrace();
			handleError(e);
		} catch (HandlerException e) {
			e.printStackTrace();
			handleError(e);
		} finally {
			try {
				if (conn != null) {
					conn.close();
					ConnLog.close("ShowPersonMeetingServlet");
				}
			} catch (SQLException sqle) {
			}
		}

	}

}
