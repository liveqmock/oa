package com.icss.oa.meeting.admin;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Iterator;
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
import com.icss.oa.meeting.vo.MeetingPersonmeetVO;
import com.icss.oa.meeting.vo.MeetingPutVO;

public class ShowMeetingServlet extends ServletBase {

	protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Connection conn = null;

		String putId = request.getParameter("putId");

		try {
			conn = this.getConnection(Globals.DATASOURCEJNDI);
			ConnLog.open("ShowMeetingServlet");
			MeetingroomManagerHandler handler = new MeetingroomManagerHandler(conn);

			MeetingPutVO vo = new MeetingPutVO();

			vo = handler.getMeetingPutVO(new Integer(putId));

			request.setAttribute("vo", vo);

			if (vo == null) {
				List list = handler.getPersonMeetingList(new Integer(putId), handler.getUserId());
				if (list != null) {
					Iterator it = list.iterator();
					if (it.hasNext()) {
						MeetingPersonmeetVO vo1 = (MeetingPersonmeetVO) it.next();
						request.setAttribute("vo", vo1);
					}
				}

				this.forward(request, response, "/meeting/SingleMeeting1.jsp");
			}

			this.forward(request, response, "/meeting/SingleMeeting.jsp?putId=" + putId + "&type=" + request.getParameter("type"));

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
					ConnLog.close("ShowMeetingServlet");
				}
			} catch (SQLException sqle) {
				sqle.printStackTrace();
			}
		}

	}

}
