package com.icss.oa.bbs.admin;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.j2ee.dao.DAOException;
import com.icss.j2ee.services.ServiceLocatorException;
import com.icss.j2ee.servlet.ServletBase;
import com.icss.oa.bbs.handler.BBSBoardHandler;

public class ModifyUOutServlet extends ServletBase {

	@Override
	protected void performTask(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		Connection conn = null;
		String uuids[] = request.getParameterValues("cheperson");

		try {
			conn = getConnection(com.icss.j2ee.util.Globals.DATASOURCEJNDI);
			BBSBoardHandler handler = new BBSBoardHandler(conn);
			for (int i = 0; i < uuids.length; i++) {
				int day = Integer.valueOf(request.getParameter(uuids[i]))
						.intValue();
				Calendar rightNow = Calendar.getInstance();
				rightNow.add(Calendar.DAY_OF_MONTH, day);
				Timestamp t = new Timestamp(rightNow.getTimeInMillis());
				handler.uptUserOUT(uuids[i], t);
			}

			response.sendRedirect("/oabase/servlet/UserOutServlet");
		} catch (ServiceLocatorException e) {
			e.printStackTrace();
		} catch (DAOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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