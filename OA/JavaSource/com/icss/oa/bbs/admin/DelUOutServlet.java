package com.icss.oa.bbs.admin;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.j2ee.dao.DAOException;
import com.icss.j2ee.dao.DAOFactory;
import com.icss.j2ee.services.ServiceLocatorException;
import com.icss.oa.bbs.handler.BBSBoardHandler;
import com.icss.oa.sms.handler.SMSHandler;

public class DelUOutServlet extends com.icss.j2ee.servlet.ServletBase implements
		javax.servlet.Servlet {

	protected void performTask(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		Connection conn = null;
		String uuids[] = request.getParameterValues("cheperson");

		try {
			conn = getConnection(com.icss.j2ee.util.Globals.DATASOURCEJNDI);
			BBSBoardHandler handler = new BBSBoardHandler(conn);
			for (int i = 0; i < uuids.length; i++) {
				handler.delUserOUT(uuids[i]);
			}
			
			response.sendRedirect("/oabase/servlet/UserOutServlet");
		} catch (ServiceLocatorException e) {
			e.printStackTrace();
		} catch (DAOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (conn != null && !conn.isClosed()) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}
}