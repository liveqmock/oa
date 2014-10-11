package com.icss.oa.zbs.duty.admin;

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
import com.icss.oa.zbs.duty.handler.ZbsWorkInfoHandler;

/**
 * @version 	1.0
 * @author		wangsu
 */
public class DutyInfoDeleteServlet extends ServletBase implements Servlet {

	/**
	* @see com.icss.j2ee.servlet.ServletBase#void (javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	*/
	protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection conn = null;
		try {
			ConnLog.open("DutyInfoDeleteServlet");
			conn = DBConnectionLocator.getInstance().getConnection(Globals.DATASOURCEJNDI);
			ZbsWorkInfoHandler handler = new ZbsWorkInfoHandler(conn);
			boolean flag1 = false;
			boolean flag2 = false;
			String[] wimids = request.getParameterValues("wimid");
			if (wimids.length != 0) {
				for (int i = 0; i < wimids.length; i++) {
					flag1 = handler.deleteDutyInfoByMainId(wimids[i]);
					if (flag1 == false) {
						System.err.println("ɾ��MainIdΪ'" + wimids[i] + "'��DutyInfo����!");
						break;
					}
					flag2 = handler.deleteDutyMainById(wimids[i]);
					if (flag2 == false) {
						System.err.println("ɾ��MainIdΪ'" + wimids[i] + "'��DutyMain����!");
						break;
					}
				}
			}
			response.sendRedirect(request.getContextPath() + "/servlet/MainDutyManageListServlet");
		} catch (DBConnectionLocatorException e) {
			// TODO �Զ����� catch ��
			e.printStackTrace();
		} finally {
			try {
				if (conn != null) {
					conn.close();
					ConnLog.close("DutyInfoDeleteServlet");
				}
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}

}