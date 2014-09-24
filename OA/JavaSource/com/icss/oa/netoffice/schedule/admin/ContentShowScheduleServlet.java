/*
 * Created on 2004-4-23
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package com.icss.oa.netoffice.schedule.admin;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.j2ee.servlet.ServletBase;
import com.icss.j2ee.util.Globals;
import com.icss.oa.netoffice.schedule.handler.ScheduleHandler;
import com.icss.oa.netoffice.schedule.vo.OfficeScheduleVO;
/**
 * @author Administrator
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public class ContentShowScheduleServlet extends ServletBase {
	protected void performTask(
		HttpServletRequest request,
		HttpServletResponse response)
		throws ServletException, IOException {

		Connection conn = null;
		String id = request.getParameter("sid");
		String date_sel = request.getParameter("date_sel");
		System.out.println("$$$$$$$$$" + date_sel);
		Integer scheduleId = new Integer(id);
		OfficeScheduleVO svo = new OfficeScheduleVO();

		try {
			conn = getConnection(Globals.DATASOURCEJNDI);
			ScheduleHandler sHandler = new ScheduleHandler(conn);
			svo = sHandler.getById(scheduleId);
			request.setAttribute("vo", svo);
			String choice = request.getParameter("doChoice");
			//			if(choice.equals("content")){
			if ("content".equals(choice)) {
				this.forward(
					request,
					response,
					"/netoffice/schedule/content_schedule.jsp");
			} else {
				this.forward(
					request,
					response,
					"/netoffice/schedule/content_update.jsp?date_del="
						+ date_sel);
			}

		} catch (Exception e) {
			e.printStackTrace();
			handleError(e);
		} finally {
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}

	}

}
