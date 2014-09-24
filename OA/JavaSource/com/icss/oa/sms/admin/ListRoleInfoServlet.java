package com.icss.oa.sms.admin;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.common.log.ConnLog;
import com.icss.j2ee.services.ServiceLocatorException;
import com.icss.j2ee.servlet.ServletBase;
import com.icss.j2ee.util.Globals;
import com.icss.oa.sms.handler.SMSHandler;
import com.icss.resourceone.common.logininfo.UserInfo;
import com.icss.resourceone.sdk.framework.Context;

public class ListRoleInfoServlet extends ServletBase {

	protected void performTask(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Connection conn = null;

		try {
			try {
				conn = getConnection(Globals.DATASOURCEJNDI);
				ConnLog.open("ListRoleInfoServlet");
			} catch (ServiceLocatorException e) {
				throw new RuntimeException("取得数据库连接错误");
			}
			Integer id = new Integer(request.getParameter("id"));
			String rolename= request.getParameter("name");
			
			Context ctx = Context.getInstance();
			UserInfo user = ctx.getCurrentLoginInfo();

			SMSHandler handler = new SMSHandler(conn);

			List roleinfoList = handler.getPersonInRole(id);
			
			request.setAttribute("roleinfoList", roleinfoList);
			request.setAttribute("id", id);
			request.setAttribute("rolename", rolename);
			this.forward(request, response, "/sms/roleInfo.jsp");
		

		} catch (NumberFormatException ne) {
			handleError(ne);
		} catch (Exception e) {
			handleError(e);
		} finally {
			try {
				if (conn != null) {
					conn.close();
					ConnLog.close("ListRoleInfoServlet");
				}
			} catch (SQLException e1) {
				throw new RuntimeException("数据库连接关闭错误");
			}
		}
	}

}
