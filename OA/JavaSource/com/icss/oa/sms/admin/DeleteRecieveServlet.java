package com.icss.oa.sms.admin;

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
import com.icss.oa.sms.handler.SMSHandler;

public class DeleteRecieveServlet extends ServletBase {
	protected void performTask(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Connection conn = null;
		try {
			conn = getConnection(Globals.DATASOURCEJNDI);
			ConnLog.open("DeleteRecieveServlet");

			String[] historyid = request.getParameterValues("historyid");
			SMSHandler handler = new SMSHandler(conn);
			for (int i = 0; i < historyid.length; i++) {
				handler.delSMSRecieve(new Integer(historyid[i]));

			}

			this.forward(request, response, "/servlet/ViewRecieveServlet");
		} catch (ServiceLocatorException e) {
			throw new RuntimeException("ȡ�����ݿ����Ӵ���");
		} catch (Exception e) {
			e.printStackTrace();
			handleError(e);

		} finally {
			try {
				if (conn != null) {
					conn.close();
					ConnLog.close("DeleteRecieveServlet");
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}

	}
}
