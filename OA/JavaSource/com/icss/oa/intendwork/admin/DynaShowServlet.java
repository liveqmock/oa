/*
 * Created on 2004-4-16
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.icss.oa.intendwork.admin;

import java.io.IOException;
import java.util.List;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.icss.resourceone.sdk.framework.Context;
import com.icss.resourceone.common.logininfo.UserInfo;
import com.icss.j2ee.util.Globals;
import com.icss.j2ee.services.ServiceLocatorException;
import com.icss.j2ee.servlet.ServletBase;
import com.icss.oa.intendwork.handler.IntendWork;
import com.icss.oa.config.IntendWorkConfig;

/**
 * @author Administrator
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class DynaShowServlet extends ServletBase {

	protected void performTask(
		HttpServletRequest request,
		HttpServletResponse response)
		throws ServletException, IOException {
		Connection conn = null;
		try {
			try {
				conn = getConnection(Globals.DATASOURCEJNDI);
			} catch (ServiceLocatorException e) {
				throw new RuntimeException("取得数据库连接错误");
			}
			Context ctx = Context.getInstance();
			UserInfo user = ctx.getCurrentLoginInfo();

			IntendWork intendWork = new IntendWork(conn);
			List workList =
				intendWork.showWorkList(
					user.getUserID(),
					IntendWorkConfig.WORKFLAG_NOTDO);
			request.setAttribute("intendwork", workList);
			this.forward(
				request,
				response,
				"/netoffice/intendWork/dynamicWork.jsp");
		} catch (Exception e) {
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