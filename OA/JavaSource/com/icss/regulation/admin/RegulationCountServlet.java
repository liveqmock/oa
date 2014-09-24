package com.icss.regulation.admin;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.j2ee.servlet.ServletBase;
import com.icss.j2ee.util.Globals;
import com.icss.regulation.handler.RegulationHandler;
import com.icss.resourceone.common.logininfo.UserInfo;
import com.icss.resourceone.sdk.framework.Context;

public class RegulationCountServlet extends ServletBase {

	/**
	 * 
	 */
	private static final long serialVersionUID = -90113184585799351L;

	protected void performTask(HttpServletRequest request,
			HttpServletResponse response) {
		Connection conn = null;
		try {
			Context ctx = null;
			ctx = Context.getInstance();
			UserInfo user = ctx.getCurrentLoginInfo();
			String uuid = user.getPersonUuid();
			conn = getConnection(Globals.DATASOURCEJNDI);

			String sTime = request.getParameter("timef");
			String eTime = request.getParameter("timee");
			
			
			RegulationHandler handler  = new RegulationHandler(conn);
			List countList = handler.countRecord(sTime,eTime);


			request.setAttribute("countList", countList);
			this.forward(request, response, "/regulation/orgCount.jsp");

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException sqle) {
				sqle.printStackTrace();
			}
		}

	}

}