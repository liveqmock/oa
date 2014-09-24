/*
 * Created on 2004-4-12
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package com.icss.oa.netoffice.memo.admin;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.common.log.ConnLog;
import com.icss.j2ee.servlet.ServletBase;
import com.icss.j2ee.util.Globals;
import com.icss.oa.netoffice.memo.handler.*;

/**
 * @author Administrator
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public class DelMemoServlet extends ServletBase {

	protected void performTask(
		HttpServletRequest request,
		HttpServletResponse response)
		throws ServletException, IOException {
		Connection conn = null;

		String mid[] = request.getParameterValues("mc");
		String selectDay = request.getParameter("selectDay");

		try {
			conn = this.getConnection(Globals.DATASOURCEJNDI);
ConnLog.open("DelMemoServlet");
			for (int j = 0; j < mid.length; j++) {
				Integer muuid = Integer.valueOf(mid[j]);
				MemoHandler mHandler = new MemoHandler(conn);
				mHandler.deleteMemo(muuid);
			}
			if (selectDay == null || "".equals(selectDay)) {
				this.forward(request, response, "/servlet/ShowMemoServlet");
			} else {
				request.setAttribute("date", selectDay);
				this.forward(
					request,
					response,
					"/servlet/CalendarChoiceMemoServlet");
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
			
		} finally {
			try {
				if (conn != null){
					conn.close();
					ConnLog.close("DelMemoServlet");
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		//response.sendRedirect(request.getContextPath()+"/servlet/ShowServiceTypeServlet");
		// response.sendRedirect("ShowMemoServlet");		

	}
}
