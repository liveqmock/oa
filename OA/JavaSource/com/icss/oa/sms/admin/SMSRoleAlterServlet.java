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
import com.icss.oa.sms.vo.SMSRoleVO;

public class SMSRoleAlterServlet extends ServletBase {

	protected void performTask(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Connection conn = null;
		try {
			try {
				conn = getConnection(Globals.DATASOURCEJNDI);
				ConnLog.open("SMSRoleAlterServlet");
			} catch (ServiceLocatorException e) {
				throw new RuntimeException("取得数据库连接错误");
			}

			Integer id = new Integer(request.getParameter("id"));
			String rolename = request.getParameter("rolename");
			Integer sendnumber = new Integer(request.getParameter("sendnumber"));
			String roledes = request.getParameter("roledes");
			Integer isout = new Integer(request.getParameter("isout"));
			Integer ishistory = new Integer(request.getParameter("ishistory"));

			SMSHandler handler = new SMSHandler(conn);
			if (handler.isRoleNameExist(rolename,id)) {
				request.setAttribute("roleNameExist", "true");
				request.setAttribute("roleName", rolename);
			} else {
				SMSRoleVO vo = new SMSRoleVO();
				vo.setId(id);
				vo.setRolename(rolename);
				vo.setSendnumber(sendnumber);
				vo.setRoledes(roledes);
				vo.setIshistory(ishistory);
				vo.setIsout(isout);
				handler.alterRole(vo);
			}
			this.forward(request, response, "/servlet/SMSRoleServlet");
		} catch (Exception e) {
			handleError(e);
		} finally {
			try {
				if (conn != null) {
					conn.close();
					ConnLog.close("SMSRoleAlterServlet");
				}
			} catch (SQLException e1) {
				throw new RuntimeException("数据库连接关闭错误");
			}
		}
	}

}
