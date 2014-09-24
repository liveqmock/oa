package com.icss.oa.sendfile.admin;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.common.log.ConnLog;
import com.icss.j2ee.services.DBConnectionLocator;
import com.icss.j2ee.services.ServiceLocatorException;
import com.icss.j2ee.servlet.ServletBase;
import com.icss.j2ee.util.Globals;
import com.icss.oa.address.handler.HandlerException;
import com.icss.oa.address.handler.SysOrgHandler;
import com.icss.oa.config.AddressConfig;

public class SendFileSelectOrgServlet extends ServletBase {
	
protected void performTask(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException, java.io.IOException {
		
		//取组织列表信息
		String orgname = request.getParameter("orgname");
		String orguuid = request.getParameter("orgid");
		String addressjndi = getServletContext().getInitParameter(AddressConfig.ADDRESSJNDI);
		Connection conn = null;
		try {
			if ( addressjndi == null || "".equals(addressjndi) ) {
				conn = getConnection(Globals.DATASOURCEJNDI);
				ConnLog.open("SelectOrgServlet");
			} else {
				conn = DBConnectionLocator.getInstance().getConnection(addressjndi);
				ConnLog.open("SelectOrgServlet");
			}
			//-------------------------------------------------------------------------------------
			SysOrgHandler handler = new SysOrgHandler(conn);
			
			List list = handler.getChildOrgs(orguuid);
			
			//-------------------------------------------------------------------------------------
			request.setAttribute("orglist", list);
			super.forward(request, response, "/address/sendfile/selectorg.jsp?orguuid=" + orguuid + "&orgname=" + orgname);
		} catch (IOException e) {
			e.printStackTrace();
			handleError(e);
			
		} catch (ServiceLocatorException e) {
			e.printStackTrace();
			handleError(e);
			
		} catch (Exception e) {
			e.printStackTrace();
			handleError(e);
			
		} finally {
			try {
				if (conn != null){
					conn.close();
					ConnLog.close("SelectOrgServlet");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		
	}
	
}

