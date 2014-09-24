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
import java.util.Iterator;
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
public class CommonGroupDelServlet extends ServletBase {

	protected void performTask(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Connection conn = null;
		try {
			try {
				conn = getConnection(Globals.DATASOURCEJNDI);
				ConnLog.open("CommonGroupDelServlet");
				conn.setAutoCommit(false);
			} catch (ServiceLocatorException e) {
				throw new RuntimeException("取得数据库连接错误");
			}

			Group group = new Group(conn);

			List list = group.listCommonTwoGroup(new Integer(request
					.getParameter("groupid")));
			if (list != null) {
				Iterator it1 = list.iterator();
				while (it1.hasNext()) {
					AddressCommongroupVO vo = (AddressCommongroupVO) it1.next();
					group.delPersonInGroup(null, vo.getId(), request
							.getParameter("type"));
					group.delCommonGroup(vo.getId());
				}
			}
			/*
			 * group.delPersonInGroup( null, new
			 * Integer(request.getParameter("groupid")),
			 * request.getParameter("type"));
			 */
			group.delCommonGroup(new Integer(request.getParameter("groupid")));
			conn.commit();
			this.forward(request, response, "/servlet/ListCommonGroupServlet");
		} catch (Exception e) {
			try {
				if (conn != null) {
					conn.rollback();
					ConnLog.close("CommonGroupDelServlet");
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
				//throw new RuntimeException("回滚错误");
			}
			e.printStackTrace();
			handleError(e);
			
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
				//throw new RuntimeException("数据库连接关闭错误");
			}
		}
	}
}
