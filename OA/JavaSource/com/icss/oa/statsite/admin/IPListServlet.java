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

public class IPListServlet extends ServletBase {

	
	protected void performTask(
		HttpServletRequest request,
		HttpServletResponse response)
		throws ServletException, IOException {

			Connection conn=null;
					
			try {
				conn = this.getConnection(Globals.DATASOURCEJNDI);
				ConnLog.open("IPListServlet");		
				ipManagerHandler handler = new ipManagerHandler(conn);
				List list = handler.getIPList();
				List list2 = handler.getIPList1();

				request.setAttribute("list",list);
				request.setAttribute("list2",list2);
				this.forward(request,response,"/statsite/IPmanager.jsp");
			} catch (Exception e) {
				handleError(e);
			} finally {
				try {
					if (conn != null) {
						conn.close();
						ConnLog.close("IPListServlet");
					}
				} catch (SQLException sqle) {
					sqle.printStackTrace();
				}
			}

	}

}
