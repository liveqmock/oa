/*
 * Created on 2004-7-13
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.icss.oa.netoffice.onduty.control;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.icss.common.log.ConnLog;
import com.icss.j2ee.servlet.ServletBase;
import com.icss.j2ee.util.Globals;
import com.icss.oa.address.handler.AddressHelp;
import com.icss.oa.address.vo.SelectOrgpersonVO;
import com.icss.oa.netoffice.onduty.handler.DutyHandler;
import com.icss.oa.netoffice.onduty.vo.OfficeDutyVO;
import com.icss.oa.util.CommUtil;

/**
 * @author firecoral
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class AddDutyPersonServlet extends ServletBase {

	/* (non-Javadoc)
	 * @see com.icss.j2ee.servlet.ServletBase#performTask(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	protected void performTask(
		HttpServletRequest request,
		HttpServletResponse response)
		throws ServletException, IOException {

		Connection conn = null;

		String orgUUid = request.getParameter("orgUUid");
		String showTime = request.getParameter("showTime").trim(); //只有年、月、日
		String dutyName = request.getParameter("dutyName");

		String periodStr = request.getParameter("periodStr").trim();
		int pindex = periodStr.indexOf("~");
		String startTime = periodStr.substring(0, pindex).trim();
		int sindex = startTime.indexOf(":");
		String time1 = startTime.substring(0, sindex);
		String time2 = startTime.substring(sindex + 1);
		String endTime = periodStr.substring(pindex + 1).trim();
		int eindex = endTime.indexOf(":");
		String time3 = endTime.substring(0, eindex);
		if ("00".equals(time3)) {
			time3 = "24";
		}
		String time4 = endTime.substring(eindex + 1);

		HttpSession session = request.getSession();
		String userUUid = "";

		List personList = new ArrayList();
		try {

			conn = this.getConnection(Globals.DATASOURCEJNDI);
			ConnLog.open("AddDutyPersonServlet");
			AddressHelp helper = new AddressHelp(conn);
			List templist = (List) session.getAttribute("dutyperson");
			if (templist != null) {
				personList = helper.getperson(templist, request, "dutyperson");
				if (personList.size() > 0) {
					Iterator personIterator = personList.iterator();
					while (personIterator.hasNext()) { //可一次加多条记录
						SelectOrgpersonVO selectOrgpersonVO =
							(SelectOrgpersonVO) personIterator.next();
						userUUid = selectOrgpersonVO.getUseruuid();
						OfficeDutyVO dutyVo = new OfficeDutyVO();
						dutyVo.setOrguuid(orgUUid);
						dutyVo.setPersonuuid(userUUid);

						long date1 = CommUtil.getLongByTime(showTime);
						long starttime =
							Long.parseLong(time1) * 3600000
								+ Long.parseLong(time2) * 60000;
						long sdate = date1 + starttime;

						long endtime =
							Long.parseLong(time3) * 3600000
								+ Long.parseLong(time4) * 60000;
						long edate = date1 + endtime;

						dutyVo.setStarttime(new Long(sdate));
						dutyVo.setEndtime(new Long(edate));

						DutyHandler dHandler = new DutyHandler(conn);

						String orgName = dHandler.GetOrgName(orgUUid);
						dutyVo.setDutyname(dutyName);
						dHandler.newDuty(dutyVo);
					}
				}
			}

			this.forward(
				request,
				response,
				"/servlet/SetDutyServlet?orgid="
					+ orgUUid
					+ "&startDate="
					+ showTime);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (conn != null)
				try {
					conn.close();
					ConnLog.close("AddDutyPersonServlet");
				} catch (Exception e2) {
					e2.printStackTrace();
				}
		}
	}

}
