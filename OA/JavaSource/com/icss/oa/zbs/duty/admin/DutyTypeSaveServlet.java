package com.icss.oa.zbs.duty.admin;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.common.log.ConnLog;
import com.icss.j2ee.dao.DAOException;
import com.icss.j2ee.services.DBConnectionLocator;
import com.icss.j2ee.services.DBConnectionLocatorException;
import com.icss.j2ee.servlet.ServletBase;
import com.icss.j2ee.util.Globals;
import com.icss.oa.zbs.duty.handler.ZbsWorkInfoHandler;

/**
 * @version 	1.0
 * @author		wangsu
 */
public class DutyTypeSaveServlet extends ServletBase {

	/**
	* @see com.icss.j2ee.servlet.ServletBase#void (javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	*/
	protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//		System.err.println("成功1");
		Connection conn = null;
		try {
			ConnLog.open("DutyTypeSaveServlet");
			conn = DBConnectionLocator.getInstance().getConnection(Globals.DATASOURCEJNDI);
			ZbsWorkInfoHandler handler = new ZbsWorkInfoHandler(conn);

			String name = request.getParameter("type_name");
			String desc = request.getParameter("type_desc");

			String addofnot = request.getParameter("add");
			String modifydutyid = request.getParameter("modifydutyid");

			if ("2".equals(addofnot) && modifydutyid != null && modifydutyid != "") {
				Integer id = handler.updateDutyType(modifydutyid, name, desc);
			} else if ("1".equals(addofnot)) {
				Integer id = handler.newDutyType(name, desc);
			}

			response.sendRedirect(request.getContextPath() + "/servlet/DutyTypeManageListServlet");
		} catch (DBConnectionLocatorException e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		} catch (DAOException e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		} finally {
			try {
				if (conn != null) {
					conn.close();
					ConnLog.close("DutyTypeSaveServlet");
				}
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}

}
