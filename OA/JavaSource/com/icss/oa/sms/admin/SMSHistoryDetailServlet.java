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
import com.icss.resourceone.sdk.framework.Context;

public class SMSHistoryDetailServlet extends ServletBase {

	protected void performTask(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		Connection conn = null;
		Context ctx = null;

		try {
			conn = this.getConnection(Globals.DATASOURCEJNDI);
			ConnLog.open("SMSHistoryDetailServlet");
			
			Integer id =new Integer(request.getParameter("id"));
			SMSHandler hl=new SMSHandler(conn);
			List historylist = hl.getHistoryById(id);
			request.setAttribute("historylist", historylist);
			this.forward(request, response, "/sms/smsHistoryDetail.jsp");

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			if (conn != null) {
				try {
					conn.close();
					ConnLog.close("SMSHistoryDetailServlet");
				} catch (SQLException e2) {
					e2.printStackTrace();
				}
			}

		}

	}

}
