package com.icss.oa.address.admin;

import java.sql.Connection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.common.log.ConnLog;
import com.icss.j2ee.services.DBConnectionLocator;
import com.icss.j2ee.servlet.ServletBase;
import com.icss.oa.address.handler.SysOrgpersonHandler;
import com.icss.oa.config.AddressConfig;
/**
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public class FindPersonInOrgServlet extends ServletBase {
	protected void performTask(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException, java.io.IOException {
		Connection conn = null;
		try {
			if ((this.getServletContext().getInitParameter(AddressConfig.ADDRESSJNDI) == null) || (this.getServletContext().getInitParameter(AddressConfig.ADDRESSJNDI).equals(""))) {
				conn = getConnection(com.icss.j2ee.util.Globals.DATASOURCEJNDI);
				ConnLog.open("FindPersonInOrgServlet");
			} else {
				conn = DBConnectionLocator.getInstance().getConnection(this.getServletContext().getInitParameter(AddressConfig.ADDRESSJNDI));
				ConnLog.open("FindPersonInOrgServlet");
			}

			String cnname = request.getParameter("cnname");
			String sessionname = request.getParameter("sessionname");
			String doFunction = request.getParameter("doFunction");
			String orguuid = request.getParameter("orguuid");

			SysOrgpersonHandler handler = new SysOrgpersonHandler(conn);
			List list = handler.getinorgbycnname(cnname, orguuid);
			if (!list.isEmpty())
				request.setAttribute("personlist", list);

			request.setAttribute("doFunction", doFunction);
			request.setAttribute("sessionname", sessionname);
			this.forward(request, response, "/address/pubaddress/findperson.jsp");
		} catch (Exception ex) {
			handleError(ex);
		} finally {
			try {
				if (conn != null){
					conn.close();
					ConnLog.close("FindPersonInOrgServlet");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
