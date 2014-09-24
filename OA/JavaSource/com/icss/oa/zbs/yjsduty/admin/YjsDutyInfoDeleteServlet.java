package com.icss.oa.zbs.yjsduty.admin;

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
import com.icss.oa.zbs.yjsduty.handler.YjsWorkInfoHandler;

/**
 * @version 	1.0
 * @author		liupei
 */
public class YjsDutyInfoDeleteServlet extends ServletBase implements Servlet {

	/**
	* @see com.icss.j2ee.servlet.ServletBase#void (javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	*/
	protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection conn = null;
		try {
			ConnLog.open("YjsDutyInfoDeleteServlet");
			conn = DBConnectionLocator.getInstance().getConnection(Globals.DATASOURCEJNDI);
			YjsWorkInfoHandler handler = new YjsWorkInfoHandler(conn);
			boolean flag1 = false;
			boolean flag2 = false;
			String[] wimids = request.getParameterValues("wimid");
			if (wimids.length != 0) {
				for (int i = 0; i < wimids.length; i++) {
					flag1 = handler.deleteDutyInfoByMainId(wimids[i]);
					if (flag1 == false) {
						System.err.println("删除MainId为'" + wimids[i] + "'的YjsDutyInfo出错!");
						break;
					}
					flag2 = handler.deleteDutyMainById(wimids[i]);
					if (flag2 == false) {
						System.err.println("删除MainId为'" + wimids[i] + "'的YjsDutyMain出错!");
						break;
					}
				}
			}
			response.sendRedirect(request.getContextPath() + "/servlet/YjsMainDutyManageListServlet");
		} catch (DBConnectionLocatorException e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		} finally {
			try {
				if (conn != null) {
					conn.close();
					ConnLog.close("YjsDutyInfoDeleteServlet");
				}
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}

}
