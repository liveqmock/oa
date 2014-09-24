/*
 * Created on 2004-4-16
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.icss.oa.address.admin;

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
import com.icss.oa.address.handler.Group;
import com.icss.oa.config.AddressConfig;

/**
 * @author Administrator
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class DeletePersonInfoServlet extends ServletBase {
	protected void performTask(
		HttpServletRequest request,
		HttpServletResponse response)
		throws ServletException, IOException {
		Connection conn = null;
		try {
			try {
				conn = getConnection(Globals.DATASOURCEJNDI);
				ConnLog.open("DeletePersonInfoServlet");
			} catch (ServiceLocatorException e) {
				throw new RuntimeException("取得数据库连接错误");
			}
			String type = request.getParameter("type");
			String userID = request.getParameter("userID");
			String[] groupid = request.getParameterValues("groupid");
			Group group = new Group(conn);
			if (type.equals("groupinfo")) { //删除分组中的user
				for (int i = 0; i < groupid.length; i++) {
					group.delPersonInGroup(
						userID,
						new Integer(groupid[i]),
						AddressConfig.GROUPTYPE_COMMOM);
				}
			} else if (type.equals("accredinfo")) { //删除个人使用权限
				for (int i = 0; i < groupid.length; i++) {
					group.delAccreditPerson(new Integer(groupid[i]), userID);
				}
			}

			this.forward(request, response, "/servlet/SearchPersonServlet");
		} catch (Exception e) {
			e.printStackTrace();
			handleError(e);
		} finally {
			try {
				if (conn != null) {
					conn.close();
					ConnLog.close("DeletePersonInfoServlet");
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
				//throw new RuntimeException("数据库连接关闭错误");
			}
		}

	}
}
