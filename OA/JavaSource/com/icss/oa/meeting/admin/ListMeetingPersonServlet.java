/*
 * Created on 2004-4-13
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

import com.icss.j2ee.services.ServiceLocatorException;
import com.icss.j2ee.servlet.ServletBase;
import com.icss.j2ee.util.Globals;
import com.icss.oa.meeting.handler.HandlerException;
import com.icss.oa.meeting.handler.MeetingroomManagerHandler;
import com.icss.oa.meeting.vo.MeetingPutVO;

/**
 * @author Administrator
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class ListMeetingPersonServlet extends ServletBase {

	protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection conn = null;

		try {
			try {
				conn = getConnection(Globals.DATASOURCEJNDI);
			} catch (ServiceLocatorException e) {
				throw new RuntimeException("取得数据库连接错误");
			}

			String putId = request.getParameter("putId");
			MeetingroomManagerHandler handler = new MeetingroomManagerHandler(conn);
			List list = handler.getMeetingPersonList(new Integer(putId));
			MeetingPutVO vo = handler.getMeetingPutVO(new Integer(putId));
			String meeting_status = vo.getMeetingSatus();

			request.setAttribute("list", list);
			request.setAttribute("num", new Integer(list.size()));
			request.setAttribute("meeting_status", meeting_status);
			request.setAttribute("putid", putId);
			System.out.println("putId in ListMeetingServlet " + putId);

			if ("kk".equals(request.getParameter("type")))
				this.forward(request, response, "/meeting/MeetingPerson1.jsp");
			else
				this.forward(request, response, "/meeting/MeetingPerson.jsp");

		} catch (NumberFormatException ne) {
			handleError(ne);
		} catch (HandlerException e) {
			e.printStackTrace();
			handleError(e);
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e1) {
				throw new RuntimeException("数据库连接关闭错误");
			}
		}
	}

}
