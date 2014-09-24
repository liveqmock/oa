/*
 * Created on 2004-4-22
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package com.icss.oa.netoffice.schedule.admin;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.common.log.ConnLog;
import com.icss.j2ee.servlet.ServletBase;
import com.icss.j2ee.util.Globals;
import com.icss.oa.config.TypeConfig;
import com.icss.oa.netoffice.schedule.handler.ScheduleHandler;
import com.icss.oa.netoffice.setnetoffice.handler.SetNetofficeHandler;
import com.icss.oa.util.CommUtil;
/**
 * @author Administrator
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public class ShowScheduleOnedayServlet extends ServletBase {

	protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Connection conn = null;
		List list1 = new ArrayList();
		Integer months = null;
		try {
			conn = this.getConnection(Globals.DATASOURCEJNDI);
			ConnLog.open("ShowScheduleOnedayServlet");
			ScheduleHandler scheduleHandler = new ScheduleHandler(conn);
			long now = System.currentTimeMillis();
			String today = CommUtil.getTime(now, 1);
			long today0 = CommUtil.getLongByTime(today);
			String personid = scheduleHandler.getUserId();

			list1 = scheduleHandler.getScheduleListBydate(new Long(today0), personid);
			request.setAttribute("list", list1);
			System.out.println("get list success!...........");

			SetNetofficeHandler setHandler = new SetNetofficeHandler(conn);

			months = setHandler.getMonthsByType(TypeConfig.SCHEDULE_TYPE);

			System.out.println("months is...........=" + months);
			this.forward(request, response, "/netoffice/schedule/schedule.jsp?months=" + months);
			//
		} catch (Exception e) {
			System.out.println(e.toString());
			handleError(e);
		} finally {
			try {
				if (conn != null){
					conn.close();
					ConnLog.close("ShowScheduleOnedayServlet");
				}
			} catch (SQLException e1) {

				e1.printStackTrace();
			}
		}

	}

}
