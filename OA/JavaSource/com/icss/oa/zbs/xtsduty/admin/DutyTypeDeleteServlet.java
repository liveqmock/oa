package com.icss.oa.zbs.xtsduty.admin;

import java.io.IOException;
import java.sql.Connection;

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
public class DutyTypeDeleteServlet extends ServletBase {

	/**
	* @see com.icss.j2ee.servlet.ServletBase#void (javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	*/
	public void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection conn = null;
		try {
			ConnLog.open("DutyTypeDeleteServlet");
			conn = DBConnectionLocator.getInstance().getConnection(Globals.DATASOURCEJNDI);
			ZbsWorkInfoHandler handler = new ZbsWorkInfoHandler(conn);
			boolean flag1 = false;
			String witid = request.getParameter("witid");
			String msgid = "";
			if (witid != null) {
				flag1 = handler.deleteDutyTypeById(witid);
				if(flag1==false){
					msgid = "1";
					System.err.println("删除TypeId为'" + witid + "'的DutyType出错!");
				}
			}
			response.sendRedirect(request.getContextPath() + "/servlet/DutyTypeManageListServlet?msgid="+msgid);
		}
		
		catch (DBConnectionLocatorException e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		} finally {
			try {
				if (conn != null) {
					conn.close();
					ConnLog.close("DutyTypeDeleteServlet");
				}
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}

	}
}