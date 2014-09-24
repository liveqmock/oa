/*
 * Created on 2004-8-11
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.icss.oa.netoffice.onduty.control;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.common.log.ConnLog;
import com.icss.j2ee.services.ServiceLocatorException;
import com.icss.j2ee.servlet.ServletBase;
import com.icss.j2ee.util.Globals;
import com.icss.oa.netoffice.onduty.handler.DutyHandler;
import com.icss.oa.util.CommUtil;

/**
 * @author firecoral
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class DelPeriodServlet extends ServletBase {

	/* (non-Javadoc)
	 * @see com.icss.j2ee.servlet.ServletBase#performTask(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	protected void performTask(
		HttpServletRequest request,
		HttpServletResponse response)
		throws ServletException, IOException {

		String orgUUid = request.getParameter("orgUUid");
		String periodStr = request.getParameter("periodStr").trim();
		String weekStart = request.getParameter("weekStart");

		int pindex = periodStr.indexOf("~");
		String startTime = periodStr.substring(0, pindex).trim();
		int sindex = startTime.indexOf(":");
		String time1 = startTime.substring(0, sindex);
		String time2 = startTime.substring(sindex + 1);
		String endTime = periodStr.substring(pindex + 1).trim();
		int eindex = endTime.indexOf(":");
		String time3 = endTime.substring(0, eindex);
		String time4 = endTime.substring(eindex + 1);

		long starttime =
			Long.parseLong(time1) * 3600000 + Long.parseLong(time2) * 60000;
		long endtime =
			Long.parseLong(time3) * 3600000 + Long.parseLong(time4) * 60000;

		Connection conn = null;

		try {
			conn = this.getConnection(Globals.DATASOURCEJNDI);
			ConnLog.open("DelPeriodServlet");
			DutyHandler dHandler = new DutyHandler(conn);

			String substring = weekStart.substring(0, weekStart.indexOf(" "));
			System.out.println("the substring is:" + substring);
			long weekstime = CommUtil.getLongByTime(substring);

			dHandler.delPeriod(orgUUid, starttime, endtime, weekstime);

			this.forward(
				request,
				response,
				"/servlet/SetDutyServlet?orgid="
					+ orgUUid
					+ "&startDate="
					+ weekStart);

		} catch (ServiceLocatorException e1) {
			e1.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (conn != null)
				try {
					conn.close();
					ConnLog.close("DelPeriodServlet");
				} catch (Exception e2) {
					e2.printStackTrace();
				}
		}

	}

}
