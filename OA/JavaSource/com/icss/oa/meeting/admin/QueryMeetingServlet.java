/*
 * Created on 2003-12-17
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */

package com.icss.oa.meeting.admin;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.common.log.ConnLog;
import com.icss.j2ee.services.DBConnectionLocator;
import com.icss.j2ee.services.DBConnectionLocatorException;
import com.icss.j2ee.servlet.ServletBase;
import com.icss.j2ee.util.Globals;
import com.icss.oa.meeting.handler.HandlerException;
import com.icss.oa.meeting.handler.MeetingroomManagerHandler;

/**
 * @author Administrator
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class QueryMeetingServlet extends ServletBase {

	protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Connection conn = null;

		String content = request.getParameter("content");
		String MeetingType = request.getParameter("MeetingType");
		String MeetingStatus = request.getParameter("MeetingStatus");
		String meetingroom = request.getParameter("meetingroom");

		boolean havesql = false;
		StringBuffer sql = new StringBuffer();

		if (content != null && !content.equals("")) {
			sql.append(" MEETING_NAME LIKE '%" + content + "%'");
			havesql = true;
		}

		if (MeetingType != null && !MeetingType.equals("") && !("全部会议").equals(MeetingType)) {
			if (havesql)
				sql.append("AND ");
			sql.append(" MEETING_TYPE ='" + MeetingType + "'");
			havesql = true;
		}

		if (MeetingStatus != null && !MeetingStatus.equals("") && !("全部").equals(MeetingStatus)) {
			if (havesql)
				sql.append("AND ");
			sql.append(" MEETING_SATUS ='" + MeetingStatus + "'");
			havesql = true;
		}

		if (meetingroom != null && !meetingroom.equals("")) {
			if (havesql)
				sql.append("AND ");
			sql.append(" MEETING_ROOM ='" + meetingroom + "'");
			havesql = true;
		}

		try {
			conn = DBConnectionLocator.getInstance().getConnection(Globals.DATASOURCEJNDI);
			ConnLog.open("QueryMeetingServlet");
			MeetingroomManagerHandler handler = new MeetingroomManagerHandler(conn);
			if ("Not".equals(request.getParameter("flag"))) {
				if (havesql)
					sql.append("AND ");
				sql.append(" MEETING_PUTPERSON ='" + handler.getUserId() + "'");
			}

			List list = handler.getMeetingPutList1(sql.toString());

			request.setAttribute("list", list);

			if ("Not".equals(request.getParameter("flag")))
				this.forward(request, response, "/meeting/MeetingPut.jsp");
			else
				this.forward(request, response, "/meeting/MeetingPutP.jsp");

		} catch (DBConnectionLocatorException e) {
			e.printStackTrace();
			handleError(e);
		} catch (HandlerException e) {
			e.printStackTrace();
			handleError(e);
		} finally {

			try {
				if (conn != null) {
					conn.close();
					ConnLog.close("QueryMeetingServlet");
				}
			} catch (SQLException e2) {
				e2.printStackTrace();
			}

		}

	}
}
