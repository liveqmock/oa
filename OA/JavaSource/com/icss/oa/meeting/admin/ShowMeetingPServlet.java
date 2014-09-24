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
import com.icss.oa.meeting.vo.MeetingPutVO;

public class ShowMeetingPServlet extends ServletBase {

	protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Connection conn = null;

		String putId = request.getParameter("putId");

		try {
			conn = this.getConnection(Globals.DATASOURCEJNDI);
			ConnLog.open("ShowMeetingPServlet");
			MeetingroomManagerHandler handler = new MeetingroomManagerHandler(conn);
			MeetingPutVO vo = new MeetingPutVO();

			vo = handler.getMeetingPutVO(new Integer(putId));

			request.setAttribute("vo", vo);
			this.forward(request, response, "/meeting/ShowMeetingP.jsp");
			//response.sendRedirect(request.getContextPath()+"ShowMeetingP.jsp");

		} catch (NumberFormatException e) {
			e.printStackTrace();
			handleError(e);
		} catch (HandlerException e) {
			e.printStackTrace();
			handleError(e);
		} catch (ServiceLocatorException e) {
			e.printStackTrace();
			handleError(e);
		} finally {
			try {
				if (conn != null) {
					conn.close();
					ConnLog.close("ShowMeetingPServlet");
				}
			} catch (SQLException sqle) {
				sqle.printStackTrace();
			}
		}

	}

}
