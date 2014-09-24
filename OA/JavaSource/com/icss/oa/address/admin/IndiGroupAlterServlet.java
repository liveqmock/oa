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
import com.icss.resourceone.sdk.framework.Context;
import com.icss.resourceone.common.logininfo.UserInfo;
import com.icss.common.log.ConnLog;
import com.icss.j2ee.util.Globals;
import com.icss.j2ee.services.ServiceLocatorException;
import com.icss.j2ee.servlet.ServletBase;

import com.icss.oa.address.handler.Group;
import com.icss.oa.address.vo.AddressGroupVO;

/**
 * @author Administrator
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class IndiGroupAlterServlet extends ServletBase {

	protected void performTask(
		HttpServletRequest request,
		HttpServletResponse response)
		throws ServletException, IOException {
		Connection conn = null;
		try {
			try {
				conn = getConnection(Globals.DATASOURCEJNDI);
				ConnLog.open("IndiGroupAlterServlet");
			} catch (ServiceLocatorException e) {
				throw new RuntimeException("取得数据库连接错误");
			}
			String groupName = request.getParameter("groupName");
			Group group = new Group(conn);
			
			Context ctx = Context.getInstance();
			UserInfo user = ctx.getCurrentLoginInfo();
			
			if (group.isIndiGroupNameExist1(groupName,user.getPersonUuid(),new Integer(request.getParameter("groupid")))) {
				request.setAttribute("groupNameExist", "true");
				request.setAttribute("groupName", groupName);
			} else {
				

				AddressGroupVO addressGroupVO = new AddressGroupVO();
				addressGroupVO.setId(
					new Integer(request.getParameter("groupid")));
				addressGroupVO.setGroupname(groupName);
				addressGroupVO.setGroupdes(request.getParameter("groupDes"));
				addressGroupVO.setGroupuser(user.getPersonUuid());

				group.alterGroup(addressGroupVO);
			}
			this.forward(request, response, "/servlet/ListGroupServlet");
		} catch (Exception e) {
			handleError(e);
		} finally {
			try {
				if (conn != null) {
					conn.close();
					ConnLog.close("IndiGroupAlterServlet");
				}
			} catch (SQLException e1) {
				throw new RuntimeException("数据库连接关闭错误");
			}
		}
	}

}
