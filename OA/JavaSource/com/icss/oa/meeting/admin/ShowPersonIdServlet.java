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

public class ShowPersonIdServlet extends ServletBase {

	protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Connection conn = null;

		try {

			conn = this.getConnection(Globals.DATASOURCEJNDI);
			ConnLog.open("ShowPersonIdServlet");
			MeetingroomManagerHandler handler = new MeetingroomManagerHandler(conn);

			System.out.println("id  " + request.getParameter("Id"));

			Integer uuid = new Integer(request.getParameter("Id"));
			Integer id = handler.getPersonId(uuid);
			System.out.println("putId in ShowPersonIdServlet " + request.getParameter("putid"));
			request.setAttribute("id", id);
			request.setAttribute("personID", uuid);
			request.setAttribute("putId", request.getParameter("putid"));

			this.forward(request, response, "/meeting/personId.jsp");

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
					ConnLog.close("ShowPersonIdServlet");
				}
			} catch (SQLException sqle) {
				sqle.printStackTrace();
			}
		}

	}

}
