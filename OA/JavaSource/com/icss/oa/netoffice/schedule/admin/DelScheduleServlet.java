/*
 * Created on 2004-4-23
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

import com.icss.common.log.ConnLog;
import com.icss.j2ee.servlet.ServletBase;
import com.icss.j2ee.util.Globals;
import com.icss.oa.intendwork.handler.IntendWork;
import com.icss.oa.netoffice.schedule.handler.ScheduleHandler;
import com.icss.oa.netoffice.schedule.vo.OfficeScheduleVO;

/**
 * @author Administrator
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public class DelScheduleServlet extends ServletBase {
	protected void performTask(
		HttpServletRequest request,
		HttpServletResponse response)
		throws ServletException {
		Connection conn = null;
		String year = new String();
		String month = new String();
		OfficeScheduleVO sVo = new OfficeScheduleVO();

		String sid[] = request.getParameterValues("jc");
		String direct = request.getParameter("direct");
		String date_sel = request.getParameter("date");

		if (direct.equals("calDest")) {
			year = request.getParameter("year");
			month = request.getParameter("month");
		}
		try {
			conn = this.getConnection(Globals.DATASOURCEJNDI);
			ConnLog.open("DelScheduleServlet");
			for (int j = 0; j < sid.length; j++) {
				Integer scheduleid = Integer.valueOf(sid[j]);
				ScheduleHandler sHandler = new ScheduleHandler(conn);
				sHandler.deleteSchedule(scheduleid);
				IntendWork intendHandler = new IntendWork(conn);
				intendHandler.deleteWork("oa.schedule", scheduleid.toString());
			}
			//定向到多个页面，根据参数决定

			if (direct.equals("calMain")) {
				this.forward(request, response, "/servlet/ScheduleMainServlet");
			} else if (direct.equals("calDest")) {
				this.forward(
					request,
					response,
					"/netoffice/schedule/schedule_calendarDest.jsp?year_select="
						+ year
						+ "&&month_select="
						+ month);
			} else {
				this.forward(
					request,
					response,
					"/servlet/CalendarSelectScheduleServlet?date=" + date_sel);
			}

		} catch (Exception e) {
			System.err.println(e.getMessage());
		} finally {
			try {
				if (conn != null){
					conn.close();
					ConnLog.close("DelScheduleServlet");
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}

	}
}
