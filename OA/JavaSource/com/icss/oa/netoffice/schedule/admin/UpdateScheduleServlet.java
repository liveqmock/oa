/*
 * Created on 2004-4-26
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package com.icss.oa.netoffice.schedule.admin;

import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.j2ee.servlet.ServletBase;
import com.icss.j2ee.util.Globals;
import com.icss.oa.config.TypeConfig;
import com.icss.oa.netoffice.schedule.handler.ScheduleHandler;
import com.icss.oa.netoffice.schedule.vo.OfficeScheduleVO;
import com.icss.oa.util.CommUtil;

/**
 * @author Administrator
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public class UpdateScheduleServlet extends ServletBase {

	protected void performTask(
		HttpServletRequest request,
		HttpServletResponse response)
		throws ServletException {
		Connection conn = null;
		String id = request.getParameter("sid");
		System.out.println("wwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwww" + id);
		String headline = request.getParameter("headline");
		String place = request.getParameter("place");
		String type_os = request.getParameter("type_select");
		String reminderTime = request.getParameter("tixing");
		String year = request.getParameter("startyear");
		String month = request.getParameter("startmon");
		String day = request.getParameter("startday");
		String type_event = request.getParameter("eventtime");
		String startHour = request.getParameter("StartHour");
		String StartMin = request.getParameter("StartMin");
		String DurHour = request.getParameter("DurHour");
		String DurMin = request.getParameter("DurMin");
		String notes = request.getParameter("notes");

		try {
			//对时间进行处理
			StringBuffer date1 = new StringBuffer();
			date1.append(year);
			date1.append("-");
			if (month.length() > 1) {
				date1.append(month);
			} else {
				date1.append("0");
				date1.append(month);
			}

			date1.append("-");
			if (day.length() > 1) {
				date1.append(day);
			} else {
				date1.append("0");
				date1.append(day);
			}
			String os_date = date1.toString();
			System.out.println("date is " + os_date);
			long date = CommUtil.getLongByTime(os_date);

			int beginTime =
				new Integer(startHour).intValue() * 3600000
					+ new Integer(StartMin).intValue() * 60000;
			int durTime =
				new Integer(DurHour).intValue() * 3600000
					+ new Integer(DurMin).intValue() * 60000;
			int endTime = beginTime + durTime;
			int AlertTime = (new Integer(reminderTime)).intValue() * 3600000;
			OfficeScheduleVO svo = new OfficeScheduleVO();

			conn = getConnection(Globals.DATASOURCEJNDI);
			if (type_event.equals(TypeConfig.SCHEDULE_TASK_ALLDAY)) {
				//int time=0;
				svo.setOsBegin(new Integer("0"));
				//int end=3600000*24;
				svo.setOsEnd(new Integer("86400000"));
			} else {
				svo.setOsBegin(new Integer(beginTime));
				svo.setOsEnd(new Integer(endTime));
			}
			svo.setOsId(new Integer(id));
			svo.setOsTopic(headline);
			svo.setOsDate(new Long(date));
			svo.setOsAlertbuffer(new Integer(reminderTime));
			svo.setOsDes(notes);
			svo.setOsPlace(place);
			svo.setOsTimetype(type_event);
			svo.setOsType(type_os);
			//svo.setPersonid("1111");
			ScheduleHandler sHandler = new ScheduleHandler(conn);
			String personUUID = new String();
			personUUID = sHandler.getUserId();
			svo.setPersonid(personUUID);
			sHandler.updateSchedule(svo);
			//			定向要区分来源
			//toDo
			//暂时定向到/servlet/ShowScheduleOnedayServlet
			//this.forward(request,response,"/servlet/ShowScheduleOnedayServlet");
			this.forward(
				request,
				response,
				"/servlet/CalendarSelectScheduleServlet?date=" + os_date);
		} catch (Exception e) {
			System.out.println("aaaaaaaaaaaaaaa" + e.getMessage());
			handleError(e);

		} finally {
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
}
