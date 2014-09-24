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
public class DelMeetingRoomServlet extends ServletBase {

	protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Connection conn = null;
		Integer MeetingId = new Integer(request.getParameter("MeetingId"));

		try {
			conn = this.getConnection(Globals.DATASOURCEJNDI);
			ConnLog.open("DelMeetingRoomServlet");
			MeetingroomManagerHandler handler = new MeetingroomManagerHandler(conn);

			handler.delMeetingroom(MeetingId);

			response.sendRedirect("MeetingRoomManagerServlet?page_num=" + request.getParameter("_page_num"));

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
					ConnLog.close("DelMeetingRoomServlet");
				}
			} catch (SQLException sqle) {
				sqle.printStackTrace();
			}
		}

	}

}
