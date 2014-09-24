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
import com.icss.oa.meeting.vo.MeetingRoominformationVO;

public class MeetingUseingServlet extends ServletBase {

	protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Connection conn = null;

		try {

			conn = this.getConnection(Globals.DATASOURCEJNDI);
			ConnLog.open("MeetingUseingServlet");
			MeetingroomManagerHandler handler = new MeetingroomManagerHandler(conn);

			List list = handler.getMeetingroomList();

			if (list != null) {
				Iterator it = list.iterator();
				while (it.hasNext()) {
					MeetingRoominformationVO vo = (MeetingRoominformationVO) it.next();
					request.setAttribute(vo.getMeetName(), handler.getMeetingPutListByMeeting(vo.getMeetName()));
				}
			}

			request.setAttribute("list", list);

			MeetingRoominformationVO vo1;
			if (request.getParameter("MeetingId1") != null) {
				Integer id = new Integer(request.getParameter("MeetingId1"));
				vo1 = handler.getMeetingroomVO(id);
				request.setAttribute("list1", handler.getMeetingPutListByMeeting(vo1.getMeetName()));
				request.setAttribute("vo1", vo1);
			}

			this.forward(request, response, "/meeting/MeetingUseing.jsp?doFuntion=" + request.getParameter("doFuntion"));

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
					ConnLog.close("MeetingUseingServlet");
				}
			} catch (SQLException sqle) {
			}
		}

	}

}
