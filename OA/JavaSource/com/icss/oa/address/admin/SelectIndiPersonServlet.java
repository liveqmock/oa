package com.icss.oa.address.admin;

import java.sql.Connection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.common.log.ConnLog;
import com.icss.j2ee.services.DBConnectionLocator;
import com.icss.j2ee.servlet.ServletBase;
import com.icss.oa.address.handler.Group;
import com.icss.oa.config.AddressConfig;

/**
 * 
 * To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Generation - Code and Comments
 */
public class SelectIndiPersonServlet extends ServletBase {
	protected void performTask(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException, java.io.IOException {
		String doFunction = "";
		if (request.getParameter("doFunction") != "") {
			doFunction = request.getParameter("doFunction");
		}
		//			System.out.println("SelectIndiGroupServlet_doFunction="+doFunction);
		String sessionname = "";
		if (request.getParameter("sessionname") != "") {
			sessionname = request.getParameter("sessionname");
		}
		String groupid = "";
		if (request.getParameter("groupid") != "") {
			groupid = request.getParameter("groupid");
		}
		//			System.out.println("SelectIndiGroupServlet_sessionname="+sessionname);

		Connection conn = null;
		try {
			if ((this.getServletContext().getInitParameter(AddressConfig.ADDRESSJNDI) == null) || (this.getServletContext().getInitParameter(AddressConfig.ADDRESSJNDI).equals(""))) {
				conn = getConnection(com.icss.j2ee.util.Globals.DATASOURCEJNDI);
				ConnLog.open("SelectIndiPersonServlet");
			} else {
				conn = DBConnectionLocator.getInstance().getConnection(this.getServletContext().getInitParameter(AddressConfig.ADDRESSJNDI));
				ConnLog.open("SelectIndiPersonServlet");
			}

			//				取个人分组列表信息
			//				Context ctx = Context.getInstance();
			//				UserInfo user = ctx.getCurrentLoginInfo();

			Group group = new Group(conn);
			List personlist = group.personInGroupbyName(new Integer(groupid), "2");

			//				System.out.println("user.getPersonUuid() =
			// "+user.getPersonUuid());

			request.setAttribute("personlist", personlist);
			this.forward(request, response, "/address/pubaddress/selectindiperson.jsp?doFunction=" + doFunction + "&sessionname=" + sessionname);
		} catch (Exception ex) {
			handleError(ex);
		} finally {
			try {
				if (conn != null){
					conn.close();
					ConnLog.close("SelectIndiPersonServlet");
				}
			} catch (Exception e) {

			}
		}
	}
}
