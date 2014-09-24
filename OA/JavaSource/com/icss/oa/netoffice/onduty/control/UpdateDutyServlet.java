/*
 * Created on 2004-7-16
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.icss.oa.netoffice.onduty.control;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.icss.common.log.ConnLog;
import com.icss.j2ee.services.ServiceLocatorException;
import com.icss.j2ee.servlet.ServletBase;
import com.icss.j2ee.util.Globals;
import com.icss.oa.address.handler.AddressHelp;
import com.icss.oa.address.handler.HandlerException;
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
public class UpdateDutyServlet extends ServletBase{

	/* (non-Javadoc)
	 * @see com.icss.j2ee.servlet.ServletBase#performTask(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	protected void performTask(HttpServletRequest request, HttpServletResponse response) 
	    throws ServletException, IOException {
		//添加值班人的方式
		String toSessionMethod = request.getParameter("toSessionMethod");

		String orgUuid = request.getParameter("orgUuid");
		String startdate = request.getParameter("startDate").trim();
		String time1 = request.getParameter("time1").trim();
		String time2 = request.getParameter("time2").trim();
		String enddate = request.getParameter("endDate").trim();
		String time3 = request.getParameter("time3").trim();
		String time4 = request.getParameter("time4").trim();
		Integer dutyid = Integer.valueOf(request.getParameter("dutyid"));
		
		Connection conn = null;
		HttpSession session = request.getSession();
		
		try {
			conn = this.getConnection(Globals.DATASOURCEJNDI);
			ConnLog.open("UpdateDutyServlet");
			long date1 = CommUtil.getLongByTime(startdate);
			long date2 = CommUtil.getLongByTime(enddate);
			long starttime = date1+Long.parseLong(time1)*3600000+Long.parseLong(time2)*60000;
			long endtime = date2+Long.parseLong(time3)*3600000+Long.parseLong(time4)*60000;
			
			String personUUid = "";
			String orgUUid = "";
			
			DutyHandler dHandler = new DutyHandler(conn);
			List personList = new ArrayList();
			try {
				//新添加一条记录
				if("isSession".equals(toSessionMethod)){
					AddressHelp helper = new AddressHelp(conn);
					personList =
						helper.getperson(
							(List) session.getAttribute("dutyperson"),
							request,
							"dutyperson");
				}else{
					personList.add(request.getParameter("personUUid"));
				}
				
				if(personList.size()>0){
					Iterator personIterator = personList.iterator();
					int i = 0;
					while(personIterator.hasNext()){
						if("isSession".equals(toSessionMethod)){
							SelectOrgpersonVO selectOrgpersonVO =
								(SelectOrgpersonVO) personIterator.next();
							personUUid = selectOrgpersonVO.getUseruuid();
						}else{
							personUUid = (String)personIterator.next();
						}
						OfficeDutyVO dutyVo = new OfficeDutyVO();
						dutyVo.setOrguuid(orgUuid);
						dutyVo.setPersonuuid(personUUid);
						
						if(i==0){
							dHandler.UpdateDuty(dutyid,personUUid,(new Long(starttime)),(new Long(endtime)));
						}else{
							dutyVo.setStarttime(new Long(starttime));
							dutyVo.setEndtime(new Long(endtime));
							dHandler.newDuty(dutyVo);
						}
						
						i++;
						
					}
				}
			} catch (HandlerException e1) {
				e1.printStackTrace();
			}
			this.forward(request,response,"/servlet/SetDutyServlet?startDate="+startdate);
			
		} catch (ServiceLocatorException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			try {
				if(!conn.isClosed()){
					conn.close();
					ConnLog.close("UpdateDutyServlet");
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		
	}

}
