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
import com.icss.oa.intendwork.handler.IntendWork;
import com.icss.oa.meeting.handler.HandlerException;
import com.icss.oa.meeting.handler.MeetingroomManagerHandler;

public class DelPersonMeetingServlet extends ServletBase {

	protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Connection conn = null;

		String jc[] = request.getParameterValues("jc");

		try {
			conn = this.getConnection(Globals.DATASOURCEJNDI);
			ConnLog.open("DelPersonMeetingServlet");
			MeetingroomManagerHandler handler = new MeetingroomManagerHandler(conn);
			IntendWork delIntendHandler = new IntendWork(conn);

			for (int j = 0; j < jc.length; j++) {
				//System.out.println("hhhhhhhhhhhhhhhhh    "+jc[j] );
				handler.delPersonMeeting(new Integer(jc[j]));
				delIntendHandler.deleteWork(IntendWork.getCodeValue("msg_pubmeeting"), jc[j]);

			}

			this.forward(request, response, "/servlet/ShowPersonMeetingServlet?page_num=" + request.getParameter("_page_num"));

		} catch (ServiceLocatorException e) {
			e.printStackTrace();
			handleError(e);
		} catch (NumberFormatException e) {
			e.printStackTrace();
			handleError(e);
		} catch (HandlerException e) {
			e.printStackTrace();
			handleError(e);
		} catch (SQLException e) {
			e.printStackTrace();
			handleError(e);
		}  finally {
			try {
				if (conn != null) {
					conn.close();
					ConnLog.close("DelPersonMeetingServlet");
				}
			} catch (SQLException sqle) {
				sqle.printStackTrace();
			}
		}

	}

}
