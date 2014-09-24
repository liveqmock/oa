package com.icss.oa.popup;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.j2ee.servlet.ServletBase;
import com.icss.j2ee.util.Globals;
import com.icss.oa.intendwork.handler.IntendWork;
import com.icss.resourceone.common.logininfo.UserInfo;
import com.icss.resourceone.sdk.framework.Context;

public class IntendWorkPopupServlet extends ServletBase {

	protected void performTask(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		Connection conn = null;
		try {
			conn = getConnection(Globals.DATASOURCEJNDI);
			
			Context ctx = Context.getInstance();
			UserInfo user = ctx.getCurrentLoginInfo();
			String userid = user.getUserID();

			IntendWork intendWork = new IntendWork(conn);
			List workList = intendWork.getTotalList(userid);

			if (workList.size() > 10) {
				workList = workList.subList(0, 10);
			}
			request.setAttribute("workList", workList);

			this.forward(request, response, "/popup/tsxx.jsp");

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
			}
		}
	}
}
