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
import java.util.List;

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
/**
 * @author sunchuanting
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class ListGroupServlet extends ServletBase {

	protected void performTask(
		HttpServletRequest request,
		HttpServletResponse response)
		throws ServletException, IOException {
		Connection conn = null;
		try {

			Context ctx = Context.getInstance();
			UserInfo user = ctx.getCurrentLoginInfo();

			try {
				conn = getConnection(Globals.DATASOURCEJNDI);
				ConnLog.open("ListGroupServlet");
			} catch (ServiceLocatorException e) {
				throw new RuntimeException("取得数据库连接错误");
			}
			Group group = new Group(conn);
			//List commonGroupList = group.commonGroupList(user.getUserID());
			List indiGroupList = group.individualGroupList(user.getPersonUuid());

			//request.setAttribute("commonGroup", commonGroupList);
			request.setAttribute("IndiGroup", indiGroupList);

			this.forward(request, response, "/mail/PersonalGroup_Body.jsp");
		} catch (Exception e) {
			handleError(e);
		} finally {
			try {
				if (conn != null) {
					conn.close();
					ConnLog.close("ListGroupServlet");
				}
			} catch (SQLException e1) {
				throw new RuntimeException("数据库连接关闭错误");
			}
		}
	}

}
