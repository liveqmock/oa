/*
 * Created on 2004-4-12
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


/**
 * @author Administrator
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class IndiGroupDelServlet extends ServletBase {

	protected void performTask(
		HttpServletRequest request,
		HttpServletResponse response)
		throws ServletException, IOException {
		Connection conn = null;
		try {
			try {
				conn = getConnection(Globals.DATASOURCEJNDI);
				ConnLog.open("IndiGroupDelServlet");
				conn.setAutoCommit(false);
			} catch (ServiceLocatorException e) {
				throw new RuntimeException("取得数据库连接错误");
			}

			Group group = new Group(conn);
			group.delPersonInGroup(
				null,
				new Integer(request.getParameter("groupid")),
				request.getParameter("type"));
			group.delGroup(new Integer(request.getParameter("groupid")));

			conn.commit();
			this.forward(request, response, "/servlet/ListGroupServlet");
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
					ConnLog.close("IndiGroupDelServlet");
				}
			} catch (SQLException e1) {
				throw new RuntimeException("数据库连接关闭错误");
			}
		}
	}
}
