/*
 * Created on 2004-4-21
 * 
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
*/
package com.icss.oa.myphonebook.admin;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List; 

import java.io.*; 
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.common.log.ConnLog;
import com.icss.j2ee.services.ServiceLocatorException;
import com.icss.j2ee.servlet.ServletBase;
import com.icss.j2ee.util.Globals;
import com.icss.orgtree.handler.HandlerException;
import com.icss.orgtree.handler.PhoneOrgHandler;
import com.icss.orgtree.vo.SysOrgVO;

/**
 * @author Administrator
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public class MyOrgTreeServlet extends ServletBase {
	/* (non-Javadoc)
	 * @see com.icss.j2ee.servlet.ServletBase#performTask(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	protected void performTask(
		HttpServletRequest request,
		HttpServletResponse response)
		throws ServletException,IOException {
		PrintWriter out = response.getWriter(); 
		out.println("<html>");
		out.println("<body>");
		out.println("Hello World afsdaf");
		out.println("</body>");
		out.println("</html>");
		//this.forward(request, response,"/phonebook/4.jsp");
		Connection conn=null;
		try{
			conn=this.getConnection(Globals.DATASOURCEJNDI);
			ConnLog.open("OrgTreeServlet");
			PhoneOrgHandler handler=new PhoneOrgHandler(conn);
			SysOrgVO topOrgVO=handler.getOrg("0");
	
			List list=handler.getOrgTreeList(topOrgVO.getOrguuid());
			request.setAttribute("list",list);
			request.setAttribute("topOrgVO",topOrgVO);
			try {
				if (conn != null) conn.close();
			} catch (SQLException sqle) {}
			this.forward(request, response,"/orgtree/myOrgTree.jsp?nodeUrl=#");
		} catch (ServiceLocatorException e) {
			e.printStackTrace();
			handleError(e);
		} catch (HandlerException e) {
			e.printStackTrace();
			handleError(e);
		} finally { 
			try {
				if (conn != null) {
					conn.close();
					ConnLog.close("OrgTreeServlet");
				}
			} catch (SQLException sqle) {
			}
		}
	}
}


