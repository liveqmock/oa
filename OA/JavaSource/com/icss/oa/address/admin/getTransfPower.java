/*
 * Created on 2004-4-26
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
import javax.servlet.http.HttpSession;
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
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class getTransfPower extends ServletBase {

	protected void performTask(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Connection conn = null;
		HttpSession session = null;
		  try {
		     	try {
				conn = getConnection(Globals.DATASOURCEJNDI);
				ConnLog.open("getTransfPower");
			} catch (ServiceLocatorException e) {
				throw new RuntimeException("取得数据库连接错误");
			}

			  Group group = new Group(conn);

	        List list1 = group.getAppRolePersonList("info.admin0");
			
	       // List list2 = group.getAppRolePersonList("info.admin1");

	        List list2 = group.getAppRolePersonList("oa.deptphonemanager");
			request.setAttribute("list1", list1);
			request.setAttribute("list2", list2);

			this.forward(request, response, "/address/PowerTransf.jsp");

		} catch (NumberFormatException ne) {
			handleError(ne);
		} catch (Exception e) {
			handleError(e);
		} finally {
			try {
				if (conn != null) {
					conn.close();
					ConnLog.close("getTransfPower");
				}
			} catch (SQLException e1) {
				throw new RuntimeException("数据库连接关闭错误");
			}
		}
	}

}