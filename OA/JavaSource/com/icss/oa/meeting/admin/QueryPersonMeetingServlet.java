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
public class QueryPersonMeetingServlet extends ServletBase {

	protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Connection conn = null;

		String content = request.getParameter("content");
		String MeetingType = request.getParameter("MeetingType");

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
		}

		try {
			conn = DBConnectionLocator.getInstance().getConnection(Globals.DATASOURCEJNDI);
			ConnLog.open("QueryPersonMeetingServlet");
			MeetingroomManagerHandler handler = new MeetingroomManagerHandler(conn);

			List list = handler.getPersonMeetingList1(sql.toString());

			request.setAttribute("list", list);

			this.forward(request, response, "/meeting/showPersonMeetingList.jsp");

		} catch (DBConnectionLocatorException e) {
			e.printStackTrace();

		} catch (HandlerException e) {
			e.printStackTrace();

		} finally {
			try {
				if (conn != null) {
					conn.close();
					ConnLog.close("QueryPersonMeetingServlet");
				}
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}

	}
}
