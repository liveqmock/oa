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

public class UpdatePersonIdServlet extends ServletBase {

	protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Connection conn = null;

		try {

			conn = this.getConnection(Globals.DATASOURCEJNDI);
			ConnLog.open("UpdatePersonIdServlet");
			MeetingroomManagerHandler handler = new MeetingroomManagerHandler(conn);


			Integer uuid = new Integer(request.getParameter("kkk"));
			Integer personid = new Integer(request.getParameter("kkk2"));
			handler.updatePersonId(personid, uuid);

			//this.forward(request,response,"/servlet/ListMeetingPersonServlet?putId="+request.getParameter("putid"));

		} catch (ServiceLocatorException e) {
			e.printStackTrace();
			handleError(e);
		} catch (HandlerException e) {
			e.printStackTrace();
			handleError(e);
		}  finally {
			try {
				if (conn != null) {
					conn.close();
					ConnLog.close("UpdatePersonIdServlet");
				}
			} catch (SQLException sqle) {
			}
		}

	}

}
