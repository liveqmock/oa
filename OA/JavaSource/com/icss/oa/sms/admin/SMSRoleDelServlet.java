package com.icss.oa.sms.admin;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.common.log.ConnLog;
import com.icss.j2ee.util.Globals;
import com.icss.j2ee.services.ServiceLocatorException;
import com.icss.j2ee.servlet.ServletBase;

import com.icss.oa.sms.handler.SMSHandler;

public class SMSRoleDelServlet extends ServletBase {

	protected void performTask(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Connection conn = null;
		try {
			try {
				conn = getConnection(Globals.DATASOURCEJNDI);
				ConnLog.open("SMSRoleDelServlet");
				conn.setAutoCommit(false);
			} catch (ServiceLocatorException e) {
				throw new RuntimeException("取得数据库连接错误");
			}

			Integer id = new Integer(request.getParameter("id"));
			SMSHandler handler = new SMSHandler(conn);
			handler.delRole(id);
			conn.commit();
			this.forward(request, response, "/servlet/SMSRoleServlet");
		} catch (Exception e) {
			try {
				if (conn != null) {
					conn.rollback();
				}
			} catch (SQLException e1) {
				throw new RuntimeException("回滚错误");
			}
			handleError(e);
		} finally {
			try {
				if (conn != null) {
					conn.close();
					ConnLog.close("SMSRoleDelServlet");
				}
			} catch (SQLException e1) {
				throw new RuntimeException("数据库连接关闭错误");
			}
		}
	}
}
