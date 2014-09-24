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

public class MeetingPutServletP extends ServletBase {

	protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Connection conn = null;

		try {
			conn = this.getConnection(Globals.DATASOURCEJNDI);
			ConnLog.open("MeetingPutServletP");
			MeetingroomManagerHandler handler = new MeetingroomManagerHandler(conn);
			List list = handler.getMeetingPutList();
			MeetingPutVO vo = new MeetingPutVO();
			Integer num = new Integer(0);

			if (request.getParameter("putId") != null) {
				Integer id = new Integer(request.getParameter("putId"));
				vo = handler.getMeetingPutVO(id);
				num = new Integer(handler.MeetingPersonNum(id));
			}

			request.setAttribute("list", list);
			request.setAttribute("vo", vo);
			request.setAttribute("num", num);

			if (request.getParameter("putId") != null) {

				this.forward(request, response, "/meeting/MeetingPutEditP.jsp");

			}

			this.forward(request, response, "/meeting/MeetingPutP.jsp");

		} catch (ServiceLocatorException e) {
			e.printStackTrace();
			handleError(e);
		} catch (HandlerException e) {

			e.printStackTrace();
			handleError(e);
		}finally {
			try {
				if (conn != null) {
					conn.close();
					ConnLog.close("MeetingPutServletP");
				}
			} catch (SQLException sqle) {
			}
		}

	}

}
