package com.icss.oa.statsite.admin;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.common.log.ConnLog;
import com.icss.j2ee.servlet.ServletBase;
import com.icss.j2ee.util.Globals;
import com.icss.oa.statsite.handler.ipManagerHandler;
import com.icss.oa.statsite.vo.*;

public class delIPServlet extends ServletBase {

	protected void performTask(
		HttpServletRequest request,
		HttpServletResponse response)
		throws ServletException, IOException {

		Connection conn = null;
		Integer ipId = new Integer(request.getParameter("ipId"));

		try {
			conn = this.getConnection(Globals.DATASOURCEJNDI);
			ConnLog.open("delIPServlet");
			ipManagerHandler handler = new ipManagerHandler(conn);
			StatSiteIpcontentVO vo = new StatSiteIpcontentVO();

			handler.delete(ipId);

			response.sendRedirect(
				"IPListServlet?_page_num=" + request.getParameter("_page_num"));

		} catch (Exception e) {
			e.printStackTrace();
			handleError(e);

		} finally {
			try {
				if (conn != null) {
					conn.close();
					ConnLog.close("delIPServlet");
				}
			} catch (SQLException sqle) {
				sqle.printStackTrace();
			}
		}

	}

}
