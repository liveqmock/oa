package com.icss.oa.address.admin;

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
import com.icss.oa.address.handler.SysOrgpersonHandler;
import com.icss.oa.config.AddressConfig;

public class SelectPersonServlet extends ServletBase {
	
	protected void performTask(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException, java.io.IOException {
		
		//取组织列表信息
		String orgname = request.getParameter("orgname");
		String orguuid = request.getParameter("orgid");
		String addressjndi = getServletContext().getInitParameter(AddressConfig.ADDRESSJNDI);
		
		System.out.println("SelectPersonServlet orgname = " + orgname);
		System.out.println("SelectPersonServlet orguuid = " + orguuid);
		System.out.println("SelectPersonServlet addressjndi = " + addressjndi);
		
		Connection conn = null;
		try {
			if ( addressjndi == null || "".equals(addressjndi) ) {
				conn = getConnection(Globals.DATASOURCEJNDI);
				ConnLog.open("SelectPersonServlet");
				
			} else {
				conn = DBConnectionLocator.getInstance().getConnection(addressjndi);
				ConnLog.open("SelectPersonServlet");
			}


			//-------------------------------------------------------------------------------------
			SysOrgpersonHandler handler = new SysOrgpersonHandler(conn);
			List list = handler.getList(orguuid);
			//-------------------------------------------------------------------------------------



			request.setAttribute("personlist", list);
			
			String oname = (orgname == null)?"":new String(orgname.getBytes("GBK"),"ISO-8859-1").toString();
			super.forward(request, response, "/address/pubaddress/selectperson.jsp?orguuid=" + orguuid + "&orgname="+oname);
			
			
		} catch (IOException e) {
			e.printStackTrace();
			handleError(e);
			
		} catch (ServiceLocatorException e) {
			e.printStackTrace();
			handleError(e);
			
		} catch (HandlerException e) {
			e.printStackTrace();
			handleError(e);
			
		} finally {
			try {
				if (conn != null){
					conn.close();
					ConnLog.close("SelectPersonServlet");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		
	}
	
}


