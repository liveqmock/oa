/*
 * Created on 2004-4-15
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package com.icss.oa.netoffice.setnetoffice.admin;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.common.log.ConnLog;
import com.icss.j2ee.servlet.ServletBase;
import com.icss.j2ee.util.Globals;
import com.icss.oa.netoffice.setnetoffice.handler.SetNetofficeHandler;
/**
 * @author Administrator
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public class ShowSetNetofficePageServlet extends ServletBase {
	protected void performTask(
		HttpServletRequest request,
		HttpServletResponse response)
		throws ServletException, IOException {
		Connection conn = null;
		List list1 = new ArrayList();
		try {
			conn = this.getConnection(Globals.DATASOURCEJNDI);
			ConnLog.open("ShowSetNetofficePageServlet");
			SetNetofficeHandler setHandler = new SetNetofficeHandler(conn);

			list1 = setHandler.getSetofficeList();
			request.setAttribute("list", list1);
			System.out.println("get list success!...........");

		} catch (Exception e) {
			System.out.println(e.toString());
			handleError(e);
		} finally {
			try {
				if (conn != null){
					conn.close();
					ConnLog.close("ShowSetNetofficePageServlet");
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		this.forward(request, response, "/netoffice/config.jsp"); //

	}
}
