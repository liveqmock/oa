/*
 * Created on 2004-7-18
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
import com.icss.oa.meeting.handler.HandlerException;
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
public class ShowDateMeetingServlet extends ServletBase {

	/* (non-Javadoc)
	 * @see com.icss.j2ee.servlet.ServletBase#performTask(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Connection conn = null;

		String yearStr = request.getParameter("year_select");
		String monthStr = request.getParameter("month_select");
		String weekStr = request.getParameter("week_select");

		int year = new Integer(yearStr).intValue();
		int month = new Integer(monthStr).intValue();
		long week = new Integer(weekStr).longValue();

		//得到开始时间
		Calendar cal = Calendar.getInstance();
		cal.set(year, month - 1, 1); //设置每月的开始日期
		long weeknum = new Integer(cal.get(Calendar.DAY_OF_WEEK)).longValue(); //取得每月的第一天是一个星期的第几天
		long monthStart = 0;

		try {

			conn = this.getConnection(Globals.DATASOURCEJNDI);
			ConnLog.open("ShowDateMeetingServlet");
			DutyHandler dHandler = new DutyHandler(conn);
			MeetingroomManagerHandler handler = new MeetingroomManagerHandler(conn);

			if (month < 10) {
				monthStart = CommUtil.getLongByTime(yearStr + "-0" + monthStr + "-01");
			} else {
				monthStart = CommUtil.getLongByTime(yearStr + "-" + monthStr + "-01");
			}

			//得到所选星期的星期天的开始时间
			long weekStart = monthStart - (weeknum - 1) * 86400000 + (week - 1) * 7 * 86400000;
			request.setAttribute("weekStart", new Long(weekStart));

			long nextMonthStart = 0;
			if (month + 1 < 10) {
				nextMonthStart = CommUtil.getLongByTime(year + "-0" + (month + 1) + "-01");
			} else {
				nextMonthStart = CommUtil.getLongByTime(year + "-" + (month + 1) + "-01");
			}

			//当前月的最后一天是本月的第几个星期
			cal.setTime(new Date(nextMonthStart - 86400000));
			long weekth = new Integer(cal.get(Calendar.WEEK_OF_MONTH)).longValue();

			request.setAttribute("weekth", new Long(weekth));

			request.setAttribute("year", yearStr);
			request.setAttribute("month", monthStr);
			request.setAttribute("week", weekStr);

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

			this.forward(request, response, "/meeting/ShowDateMeeting.jsp");

		} catch (ServiceLocatorException e1) {
			e1.printStackTrace();
			handleError(e1);
		} catch (HandlerException e) {
			e.printStackTrace();
			handleError(e);
		} catch (Exception e) {
			e.printStackTrace();
			handleError(e);
		} finally {
			if (conn != null)
				try {
					conn.close();
					ConnLog.close("ShowDateMeetingServlet");
				} catch (Exception e2) {
					e2.printStackTrace();
				}
		}

	}

}
