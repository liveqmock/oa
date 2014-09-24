/*
 * Created on 2004-4-16
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.icss.oa.oftenperson.admin;

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
import com.icss.oa.oftenperson.handler.OftenHandler;
import com.icss.resourceone.common.logininfo.UserInfo;
import com.icss.resourceone.sdk.framework.Context;

/**
 * @author WANGJIANG
 *
 */
public class DelOftenPersonServlet extends ServletBase {
	protected void performTask(
		HttpServletRequest request,
		HttpServletResponse response)
		throws ServletException, IOException {
		Connection conn = null;
		try {
			//取个人分组列表信息
			Context ctx = Context.getInstance();
			UserInfo user = ctx.getCurrentLoginInfo();
			String userid = user.getUserID();			
			System.out.println("DelOftenPersonServlet-->userid = " + userid);

			
			try {
				conn = getConnection(Globals.DATASOURCEJNDI);
				ConnLog.open("DelOftenPersonServlet");
			} catch (ServiceLocatorException e) {
				throw new RuntimeException("取得数据库连接错误");
			}
			String[] personid = request.getParameterValues("personid");
			OftenHandler handler = new OftenHandler(conn);
			for (int i = 0; i < personid.length; i++) {
				handler.delOftenPerson(userid,personid[i]);
			}

			response.sendRedirect(request.getContextPath()+"/servlet/OftenLinkmanListServlet");
			
		} catch (Exception e) {
			e.printStackTrace();
			handleError(e);
			
		} finally {
			try {
				if (conn != null) {
					conn.close();
					ConnLog.close("DeletePersonServlet");
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
				//throw new RuntimeException("数据库连接关闭错误");
			}
		}

	}
}
