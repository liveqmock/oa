package com.icss.oa.meeting.admin;

import java.io.FileInputStream;
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
import com.icss.oa.meeting.vo.MeetingPutVO;
import com.icss.resourceone.common.logininfo.UserInfo;
import com.icss.resourceone.sdk.framework.Context;
import com.icss.resourceone.sdk.framework.EntityException;

public class AddMeetingPutServlet extends ServletBase {

	protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Connection conn = null;

		String MeetingName = request.getParameter("MeetingName");
		String MeetingType = request.getParameter("MeetingType");
		String MeetingStratTime = request.getParameter("MeetingStartTime");
		String MeetingEndTime = request.getParameter("MeetingEndTime");
		String MeetingRoom = request.getParameter("MeetingRoom");
		String MeetingUtil = request.getParameter("MeetingUtil");
		String MeetingMeno = request.getParameter("MeetingMeno");
		String StartHour = request.getParameter("MeetingStartTimeHour");
		String StartMin = request.getParameter("MeetingStartTimeMinit");
		String EndHour = request.getParameter("MeetingEndTimeHour");
		String EndMin = request.getParameter("MeetingEndTimeMinit");

		try {
			conn = this.getConnection(Globals.DATASOURCEJNDI);
			ConnLog.open("AddMeetingPutServlet");
			MeetingroomManagerHandler handler = new MeetingroomManagerHandler(conn);
			MeetingPutVO vo = new MeetingPutVO();

			Context ctx = Context.getInstance();
			UserInfo user = ctx.getCurrentLoginInfo();

			vo.setMeetingName(MeetingName);
			vo.setMeetingType(MeetingType);
			vo.setStarttimeday(handler.getLongByTime(MeetingStratTime));
			vo.setEndtimeday(handler.getLongByTime(MeetingEndTime));
			vo.setMeetingRoom(MeetingRoom);
			vo.setMeetingNeedingutil(MeetingUtil);
			vo.setPutMeno(MeetingMeno);
			vo.setStartimehour(StartHour + ":" + StartMin);
			vo.setEndtimehour(EndHour + ":" + EndMin);
			vo.setMeetingSatus("…Í«Î÷–");
			vo.setMeetingPutperson(user.getPersonUuid());

			vo.setMeetingPutdep(handler.getPeopleDepartment(user.getPersonUuid()));
			vo.setPutTime(new Long(System.currentTimeMillis()));

			FileInputStream inputStream = null;
			if (!request.getParameter("access1").equals("default")) {
				String fileFillName = getUploadFileFullName(request, "access");
				inputStream = new FileInputStream(fileFillName);
				vo.setMeetingSitting(inputStream);
				vo.setSittingName(this.getUploadFileName(request, "access"));
			}

			handler.addMeetingPut(vo);

			if (!request.getParameter("access1").equals("default")) {
				inputStream.close();
			}

			response.sendRedirect("MeetingPutServlet?page_num=" + request.getParameter("_page_num"));

		} catch (HandlerException e) {
			e.printStackTrace();
			handleError(e);
		} catch (ServiceLocatorException e) {
			e.printStackTrace();
			handleError(e);
		} catch (EntityException e) {
			e.printStackTrace();
			handleError(e);
		} finally {
			try {
				if (conn != null) {
					conn.close();
					ConnLog.close("AddMeetingPutServlet");
				}
			} catch (SQLException sqle) {
				sqle.printStackTrace();
			}
		}

	}

}
