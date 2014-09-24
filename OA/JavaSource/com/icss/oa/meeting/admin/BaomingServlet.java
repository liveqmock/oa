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
import com.icss.resourceone.common.logininfo.UserInfo;
import com.icss.resourceone.sdk.framework.Context;
import com.icss.resourceone.sdk.framework.EntityException;

public class BaomingServlet extends ServletBase {

	protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Connection conn = null;

		String putId = request.getParameter("putId");

		try {
			conn = this.getConnection(Globals.DATASOURCEJNDI);
			ConnLog.open("BaomingServlet");
			Context ctx = Context.getInstance();
			UserInfo user = ctx.getCurrentLoginInfo();

			MeetingroomManagerHandler handler = new MeetingroomManagerHandler(conn);

			handler.BaomingMeetingPerson(new Integer(putId), user.getPersonUuid());

			response.sendRedirect(request.getContextPath() + "/meeting/error.jsp");

		} catch (ServiceLocatorException e) {
			e.printStackTrace();
			handleError(e);
		} catch (EntityException e) {
			e.printStackTrace();
			handleError(e);
		} catch (NumberFormatException e) {
			e.printStackTrace();
			handleError(e);
		} catch (HandlerException e) {
			e.printStackTrace();
			handleError(e);
		}finally {
			try {
				if (conn != null) {
					conn.close();
					ConnLog.close("BaomingServlet");
				}
			} catch (SQLException sqle) {
				sqle.printStackTrace();
			}
		}

	}

}
