/*
 * Created on 2004-8-10
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.icss.oa.netoffice.onduty.control;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.common.log.ConnLog;
import com.icss.j2ee.services.ServiceLocatorException;
import com.icss.j2ee.servlet.ServletBase;
import com.icss.j2ee.util.Globals;
import com.icss.oa.netoffice.onduty.handler.DutyHandler;
import com.icss.oa.netoffice.onduty.vo.OfficeDutyVO;
import com.icss.oa.util.CommUtil;

/**
 * @author firecoral
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class EditDutyNameServlet extends ServletBase{

	/* (non-Javadoc)
	 * @see com.icss.j2ee.servlet.ServletBase#performTask(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	protected void performTask(HttpServletRequest request, HttpServletResponse response) 
	   throws ServletException, IOException {
	   	
		String orgUUid = request.getParameter("orgUUid");
		String dutyName = request.getParameter("dutyName");
		String weekStart = request.getParameter("weekStart");
		
		Connection conn = null;
		
		try {
			conn = this.getConnection(Globals.DATASOURCEJNDI);
			ConnLog.open("EditDutyNameServlet");
			DutyHandler dHandler = new DutyHandler(conn);
			//查看数据库中在这一周是否有记录
			long startDate = CommUtil.getLongByTime(weekStart);
			long endDate = startDate + 7*86400000;
			boolean isHave = false;
			isHave = dHandler.isHaveRecord(dutyName,orgUUid,startDate,endDate);
			
			if(isHave==false){  //在数据库中给这一周加一条缺省记录
				OfficeDutyVO dutyVO = new OfficeDutyVO();
				dutyVO.setOrguuid(orgUUid);
				dutyVO.setPersonuuid("a");
				dutyVO.setStarttime(new Long(startDate));
				dutyVO.setEndtime(new Long(startDate));
				dutyVO.setDutyname(dutyName);
				
				dHandler.newDuty(dutyVO);
			}
			
			this.forward(request,response,"/servlet/SetDutyServlet?orgid="+orgUUid+"&startDate="+weekStart);
			
		} catch (ServiceLocatorException e) {
			e.printStackTrace();
			
		} catch (Exception e) {
			e.printStackTrace();
			
		} finally{
			try {
				if(conn!=null){
					conn.close();
					ConnLog.close("EditDutyNameServlet");
				}
					
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		
	}

}
