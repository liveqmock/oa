package com.icss.oa.statsite.admin;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.common.log.ConnLog;
import com.icss.j2ee.servlet.ServletBase;
import com.icss.j2ee.util.Globals;
import com.icss.oa.statsite.handler.ipManagerHandler;

public class addressQueryServlet extends ServletBase {

	protected void performTask(
		HttpServletRequest request,
		HttpServletResponse response)
		throws ServletException, IOException {

		Connection conn = null;
		String address = request.getParameter("address");
		try {
			conn = this.getConnection(Globals.DATASOURCEJNDI);
			ConnLog.open("addressQueryServlet");
			ipManagerHandler handler = new ipManagerHandler(conn);
			
			List list = null;
			if (address.equals("all")) {
				list = handler.getIPList();
			} else {
				list = handler.getIPList2(address);
			}

			List list1 = handler.getIPList();
			List list2 = handler.getIPList1();

			request.setAttribute("list1", list1);
			request.setAttribute("list", list);
			request.setAttribute("list2", list2);
			this.forward(request, response, "/statsite/IPmanager.jsp");

		} catch (Exception e) {
			e.printStackTrace();
			handleError(e);
		} catch (Throwable e) {
			e.printStackTrace();
		} finally {
			try {
				if (conn != null) {
					conn.close();
					ConnLog.close("addressQueryServlet");
				}
			} catch (SQLException sqle) {
				sqle.printStackTrace();
			}
		}

	}

}
