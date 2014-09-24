package com.icss.oa.sms.admin;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.common.log.ConnLog;
import com.icss.j2ee.servlet.ServletBase;
import com.icss.j2ee.util.Globals;
import com.icss.oa.sms.handler.SMSHandler;
import com.icss.resourceone.common.logininfo.UserInfo;
import com.icss.resourceone.sdk.framework.Context;

public class ViewRecieveServlet extends ServletBase {

	@Override
	protected void performTask(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Connection conn = null;
		Context ctx = null;

		try {

			List recieveList = null;
			conn = this.getConnection(Globals.DATASOURCEJNDI);
			ConnLog.open("ViewRecieveServlet");
			// 取得查看人信息
			ctx = Context.getInstance();
			UserInfo user = ctx.getCurrentLoginInfo();
			if (user != null) {
				String userid = user.getPersonUuid();
				// System.out.println("userid"+userid);
				SMSHandler handler = new SMSHandler(conn);

				recieveList = handler.getRList(userid);
			}

			request.setAttribute("recieveList", recieveList);
			this.forward(request, response, "/sms/receiveList.jsp");

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			if (conn != null) {
				try {
					conn.close();
					ConnLog.close("ViewSMSHistoryServlet");
				} catch (SQLException e2) {
					e2.printStackTrace();
				}
			}

		}
	}
}
