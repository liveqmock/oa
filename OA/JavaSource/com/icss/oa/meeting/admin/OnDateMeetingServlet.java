/*
 * Created on 2004-7-12
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.icss.oa.meeting.admin;

import java.io.IOException;
import java.sql.Connection;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.common.log.ConnLog;
import com.icss.j2ee.services.ServiceLocatorException;
import com.icss.j2ee.servlet.ServletBase;
import com.icss.j2ee.util.Globals;
import com.icss.oa.meeting.handler.MeetingroomManagerHandler;
import com.icss.oa.meeting.vo.MeetingRoominformationVO;
import com.icss.oa.netoffice.onduty.handler.DutyHandler;
import com.icss.oa.util.CommUtil;

/**
 * @author firecoral
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class OnDateMeetingServlet extends ServletBase {

	/* (non-Javadoc)
	 * @see com.icss.j2ee.servlet.ServletBase#performTask(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Connection conn = null;

		try {

			conn = this.getConnection(Globals.DATASOURCEJNDI);
			ConnLog.open("OnDateMeetingServlet");
			DutyHandler dHandler = new DutyHandler(conn);
			MeetingroomManagerHandler handler = new MeetingroomManagerHandler(conn);

			//得到本组织下的本周有值班的人员的信息
			long nowtime = System.currentTimeMillis();

			//得到当前时间所在星期的开始时间
			long weekStart = dHandler.getWeekStart(nowtime);

			//当前月的最后一天是本月的第几个星期
			Calendar cal = Calendar.getInstance();
			cal.setTime(new Date(nowtime));
			int year = cal.get(Calendar.YEAR);
			int month = cal.get(Calendar.MONTH) + 2; //下月
			int week = cal.get(Calendar.WEEK_OF_MONTH);
			long nextMonthStart = 0;
			if (month < 10) {
				nextMonthStart = CommUtil.getLongByTime(year + "-0" + (month) + "-01");
			} else {
				nextMonthStart = CommUtil.getLongByTime(year + "-" + (month) + "-01");
			}
			cal.setTime(new Date(nextMonthStart - 86400000));
			long weekth = new Integer(cal.get(Calendar.WEEK_OF_MONTH)).longValue();

			request.setAttribute("weekth", new Long(weekth));

			request.setAttribute("year", String.valueOf(year));
			request.setAttribute("month", String.valueOf(month - 1));
			request.setAttribute("week", String.valueOf(week));
			request.setAttribute("weekStart", new Long(weekStart));

			List list = handler.getMeetingroomList();
			request.setAttribute("List", list);

			if (list != null) {
				Iterator it = list.iterator();
				while (it.hasNext()) {
					MeetingRoominformationVO vo = (MeetingRoominformationVO) it.next();
					for (int i = 1; i <= 7; i++) {

						request.setAttribute(vo.getMeetName() + i, handler.getMeetingPutListByMeeting(vo.getMeetName(), new Long(weekStart + i * 24 * 60 * 60 * 1000)));
					}
				}
			}

			this.forward(request, response, "/meeting/OnDateMeeting.jsp");

		} catch (ServiceLocatorException e1) {

			e1.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.close();
					ConnLog.close("OnDateMeetingServlet");
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		}

	}

}
