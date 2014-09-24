package com.icss.regulation.admin;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.j2ee.servlet.ServletBase;
import com.icss.j2ee.util.Globals;
import com.icss.regulation.handler.RegulationHandler;
import com.icss.regulation.vo.RegulationOrgVO;
import com.icss.resourceone.common.logininfo.UserInfo;
import com.icss.resourceone.sdk.framework.Context;

public class RegulationOrgListServlet extends ServletBase {


	protected void performTask(HttpServletRequest request,
			HttpServletResponse response) {
		Connection conn = null;
		try {
			Context ctx = null;
			ctx = Context.getInstance();
			UserInfo user = ctx.getCurrentLoginInfo();
			conn = getConnection(Globals.DATASOURCEJNDI);
			RegulationHandler handler  = new RegulationHandler(conn);
			
			List orglist = handler.getRegulationOrg();
			request.setAttribute("orglist", orglist);
			this.forward(request, response, "/regulation/orgManage.jsp");

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