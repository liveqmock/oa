/*
 * Created on 2004-4-14
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package com.icss.oa.netoffice.setnetoffice.admin;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.j2ee.servlet.ServletBase;
import com.icss.j2ee.util.Globals;
import com.icss.oa.config.TypeConfig;
import com.icss.oa.netoffice.setnetoffice.handler.SetNetofficeHandler;
import com.icss.oa.netoffice.setnetoffice.vo.OfficeSetnetofficeVO;
/**
 * @author Administrator
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public class SetScheduleServlet extends ServletBase {
	protected void performTask(
		HttpServletRequest request,
		HttpServletResponse response)
		throws ServletException, IOException {
		Connection conn = null;
		String scheduleId = request.getParameter("sche_id");
		Integer scheId = new Integer(scheduleId);
		String resMonths = request.getParameter("selSchedule");
		Integer reserveFormonths = new Integer(resMonths);
		System.out.println("ssssssssssssssssssss" + reserveFormonths);
		String remind_hours = request.getParameter("remind_hours");
		Integer remindBeforHours = new Integer(remind_hours);
		Integer type = new Integer(TypeConfig.SCHEDULE_TYPE);
		OfficeSetnetofficeVO vo = new OfficeSetnetofficeVO();
		try {
			conn = getConnection(Globals.DATASOURCEJNDI);
			SetNetofficeHandler setHandler = new SetNetofficeHandler(conn);

			vo.setSetId(scheId);
			vo.setMonthsReserve(reserveFormonths);
			vo.setHoursRemind(remindBeforHours);
			vo.setSetType(type);
			System.out.println("ffffffffffffff");

			setHandler.setNetOffice(vo);


			this.forward(
				request,
				response,
				"/servlet/ShowSetNetofficePageServlet");
		} catch (Exception e) {
			System.out.println(e.toString());
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
