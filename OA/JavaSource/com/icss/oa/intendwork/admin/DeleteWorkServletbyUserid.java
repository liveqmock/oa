/*
 * Created on 2005-7-4
 *
 */
package com.icss.oa.intendwork.admin;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.common.log.ConnLog;
import com.icss.j2ee.services.DBConnectionLocator;
import com.icss.j2ee.servlet.ServletBase;
import com.icss.oa.intendwork.handler.IntendWork;
import com.icss.resourceone.common.logininfo.UserInfo;
import com.icss.resourceone.sdk.framework.Context;

/**
 * @author sunchuanting
 *
 */
public class DeleteWorkServletbyUserid extends ServletBase {

	protected void performTask(
		HttpServletRequest arg0,
		HttpServletResponse arg1)
		throws ServletException, IOException {

		Connection conn = null;
		UserInfo user = null;

		try {
			conn =
				DBConnectionLocator.getInstance().getConnection(
					this.getServletContext().getInitParameter("addressjndi"));
			ConnLog.open("DeleteWorkServletbyUserid");
			IntendWork intendhandler = new IntendWork(conn);

			Context ctx = Context.getInstance();
			user = ctx.getCurrentLoginInfo();

			String loginname = user.getUserID();

			if (!"".equals(loginname) || loginname != null) {
				intendhandler.deleteWorkByUser(loginname);
			}

			this.forward(
					arg0,
					arg1,
					"/servlet/AllIntendWorkServlet");
		} catch (Exception e) {
			e.printStackTrace();

		} finally {

			if (conn != null) {
				try {
					conn.close();
					ConnLog.close("DeleteWorkServletbyUserid");
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}

		}

	}

}
