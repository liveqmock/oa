/*
 * Created on 2004-4-13
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.icss.oa.address.admin;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.common.log.ConnLog;
import com.icss.j2ee.util.Globals;
import com.icss.j2ee.services.ServiceLocatorException;
import com.icss.j2ee.servlet.ServletBase;

import com.icss.oa.address.handler.Group;
import com.icss.oa.address.vo.AddressCommongroupVO;

/**
 * @author Administrator
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class ListGrouprightServlet extends ServletBase {

	protected void performTask(
		HttpServletRequest request,
		HttpServletResponse response)
		throws ServletException, IOException {
		Connection conn = null;
		try {
			try {
				conn = getConnection(Globals.DATASOURCEJNDI);
				ConnLog.open("ListGrouprightServlet");
			} catch (ServiceLocatorException e) {
				throw new RuntimeException("取得数据库连接错误");
			}
			String groupid = request.getParameter("groupid");

			Group group = new Group(conn);

			List grouprightList = group.getAccreditList(new Integer(groupid));
			String groupName = getGroupName(groupid, group);

			request.setAttribute("groupright", grouprightList);
			request.setAttribute("groupname", groupName);
			request.setAttribute("groupid", groupid);

			this.forward(request, response, "/address/groupright.jsp");
		} catch (NumberFormatException ne) {
			handleError(ne);
		} catch (Exception e) {
			handleError(e);
		} finally {
			try {
				if (conn != null) {
					conn.close();
					ConnLog.close("ListGrouprightServlet");
				}
			} catch (SQLException e1) {
				throw new RuntimeException("数据库连接关闭错误");
			}
		}
	}

	private String getGroupName(String groupid, Group group) {

		AddressCommongroupVO vo = group.getCommonGroup(new Integer(groupid));
		return vo.getGroupname();

	}

}
