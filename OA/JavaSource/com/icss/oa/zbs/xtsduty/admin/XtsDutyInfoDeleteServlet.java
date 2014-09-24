package com.icss.oa.zbs.xtsduty.admin;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.common.log.ConnLog;
import com.icss.j2ee.services.DBConnectionLocator;
import com.icss.j2ee.services.DBConnectionLocatorException;
import com.icss.j2ee.servlet.ServletBase;
import com.icss.j2ee.util.Globals;
import com.icss.oa.zbs.xtsduty.handler.XtsWorkInfoHandler;

/**
 * @version 	1.0
 * @author		liupei
 */
public class XtsDutyInfoDeleteServlet extends ServletBase implements Servlet {

	/**
	* @see com.icss.j2ee.servlet.ServletBase#void (javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	*/
	protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection conn = null;
		try {
			ConnLog.open("YjsDutyInfoDeleteServlet");
			conn = DBConnectionLocator.getInstance().getConnection(Globals.DATASOURCEJNDI);
			XtsWorkInfoHandler handler = new XtsWorkInfoHandler(conn);
			boolean flag1 = false;
			boolean flag2 = false;
			String[] wimids = request.getParameterValues("wimid");
			if (wimids.length != 0) {
				for (int i = 0; i < wimids.length; i++) {
					flag1 = handler.deleteDutyInfoByMainId(wimids[i]);
					if (flag1 == false) {
						System.err.println("删除MainId为'" + wimids[i] + "'的XtsDutyInfo出错!");
						break;
					}
					flag2 = handler.deleteDutyMainById(wimids[i]);
					if (flag2 == false) {
						System.err.println("删除MainId为'" + wimids[i] + "'的XtsDutyMain出错!");
						break;
					}
				}
			}
			response.sendRedirect(request.getContextPath() + "/servlet/XtsMainDutyManageListServlet");
		} catch (DBConnectionLocatorException e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		} finally {
			try {
				if (conn != null) {
					conn.close();
					ConnLog.close("XtsDutyInfoDeleteServlet");
				}
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}

}
