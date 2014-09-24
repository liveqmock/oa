package com.icss.oa.sms.admin;

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
import com.icss.oa.sms.handler.SMSHandler;

public class DelRolePersonServlet extends ServletBase {
	protected void performTask(
		HttpServletRequest request,
		HttpServletResponse response)
		throws ServletException, IOException {
		Connection conn = null;
		try {
			try {
				conn = getConnection(Globals.DATASOURCEJNDI);
				ConnLog.open("DelRolePersonServlet");
			} catch (ServiceLocatorException e) {
				throw new RuntimeException("取得数据库连接错误");
			}
			String[] personid = request.getParameterValues("personid");
			Integer id = new Integer(request.getParameter("id"));
			SMSHandler handler = new SMSHandler(conn);
			for (int i = 0; i < personid.length; i++) {
				handler.delRolePerson(personid[i], id);
				
			}
			
			this.forward(request, response, "/servlet/ListRoleInfoServlet");			
		} catch (Exception e) {
			e.printStackTrace();
			handleError(e);
			
		} finally {
			try {
				if (conn != null) {
					conn.close();
					ConnLog.close("DelRolePersonServlet");
				}
			} catch (SQLException e1) {
				e1.printStackTrace();				
			}
		}

	}
}
