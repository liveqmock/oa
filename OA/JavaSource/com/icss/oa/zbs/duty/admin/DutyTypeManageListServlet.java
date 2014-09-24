package com.icss.oa.zbs.duty.admin;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.common.log.ConnLog;
import com.icss.j2ee.services.DBConnectionLocator;
import com.icss.j2ee.services.DBConnectionLocatorException;
import com.icss.j2ee.servlet.ServletBase;
import com.icss.j2ee.util.Globals;
import com.icss.oa.zbs.duty.handler.ZbsWorkInfoHandler;

/**
 * @version 	1.0
 * @author		wangsu
 */
public class DutyTypeManageListServlet extends ServletBase implements Servlet {

	/**
	* @see com.icss.j2ee.servlet.ServletBase#void (javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	*/
	public void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection conn = null;
		List mainDutyTypeList = new ArrayList();
		try {
			ConnLog.open("DutyTypeManageListServlet");
			conn = DBConnectionLocator.getInstance().getConnection(Globals.DATASOURCEJNDI);
			ZbsWorkInfoHandler handler = new ZbsWorkInfoHandler(conn);

			mainDutyTypeList = handler.getMainDutyTypeListByClause("1=1");
			request.setAttribute("mainDutyTypeList", mainDutyTypeList);
			
			String msgid = request.getParameter("msgid");
			if("1".equals(msgid)){
				request.setAttribute("msgid", msgid);
			}			
			this.forward(request, response, "/zbs/zbs_duty/dutyTypeManageList.jsp");
		} catch (DBConnectionLocatorException e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		} finally {
			try {
				if (conn != null) {
					conn.close();
					ConnLog.close("DutyTypeManageListServlet");
				}
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}

}
