package com.icss.oa.meeting.admin;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
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
import com.icss.oa.meeting.vo.MeetingPutVO;
import com.icss.resourceone.common.logininfo.UserInfo;
import com.icss.resourceone.sdk.framework.Context;
import com.icss.resourceone.sdk.framework.EntityException;

public class MeetingPutServlet extends ServletBase {

	protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Connection conn = null;

		try {
			conn = this.getConnection(Globals.DATASOURCEJNDI);
			ConnLog.open("MeetingPutServlet");
			MeetingroomManagerHandler handler = new MeetingroomManagerHandler(conn);

			Context ctx = Context.getInstance();
			UserInfo user = ctx.getCurrentLoginInfo();

			List list = handler.getMeetingPutList(user.getPersonUuid());
			MeetingPutVO vo = new MeetingPutVO();

			if (request.getParameter("putId") != null) {
				Integer id = new Integer(request.getParameter("putId"));
				vo = handler.getMeetingPutVO(id);
			}

			request.setAttribute("list", list);
			request.setAttribute("vo", vo);

			if (request.getParameter("putId") != null) {

				this.forward(request, response, "/meeting/MeetingPutEdit.jsp");

			}

			this.forward(request, response, "/meeting/MeetingPut.jsp");

		} catch (ServiceLocatorException e) {
			e.printStackTrace();
			handleError(e);
		} catch (EntityException e) {
			e.printStackTrace();
			handleError(e);
		} catch (HandlerException e) {
			e.printStackTrace();
			handleError(e);
		} finally {
			try {
				if (conn != null) {
					conn.close();
					ConnLog.close("MeetingPutServlet");
				}
			} catch (SQLException sqle) {
			}
		}

	}

}
