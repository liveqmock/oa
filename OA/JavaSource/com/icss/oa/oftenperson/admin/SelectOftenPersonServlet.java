package com.icss.oa.oftenperson.admin;

import java.sql.Connection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.common.log.ConnLog;
import com.icss.j2ee.services.DBConnectionLocator;
import com.icss.j2ee.servlet.ServletBase;
import com.icss.oa.config.AddressConfig;
import com.icss.oa.oftenperson.handler.OftenHandler;
import com.icss.resourceone.common.logininfo.UserInfo;
import com.icss.resourceone.sdk.framework.Context;

/*
 * 
 * @author wangjiang
 * @time 2005-08-24
 *
 */

public class SelectOftenPersonServlet extends ServletBase {
	protected void performTask(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException, java.io.IOException {
		String doFunction = "";
		if (request.getParameter("doFunction") != "") {
			doFunction = request.getParameter("doFunction");
		}
		String sessionname = "";
		if (request.getParameter("sessionname") != "") {
			sessionname = request.getParameter("sessionname");
		}

		Connection conn = null;
		try {
			if ((this.getServletContext().getInitParameter(AddressConfig.ADDRESSJNDI) == null) || (this.getServletContext().getInitParameter(AddressConfig.ADDRESSJNDI).equals(""))) {
				conn = getConnection(com.icss.j2ee.util.Globals.DATASOURCEJNDI);
				ConnLog.open("SelectIndiPersonServlet");
			} else {
				conn = DBConnectionLocator.getInstance().getConnection(this.getServletContext().getInitParameter(AddressConfig.ADDRESSJNDI));
				ConnLog.open("SelectIndiPersonServlet");
			}

			//取个人分组列表信息
			Context ctx = Context.getInstance();
			UserInfo user = ctx.getCurrentLoginInfo();
			String userid = user.getUserID();
			
			System.out.println("SelectOftenPersonServlet-->userid = " + userid);

			OftenHandler handler = new OftenHandler(conn);
			//取得当前用户经常联系人(Person对象的集合
			List personlist = handler.getOftenPerson(userid);

			request.setAttribute("personlist", personlist);
			this.forward(request, response, "/address/sendfile/selectOftenperson.jsp?doFunction=" + doFunction + "&sessionname=" + sessionname);
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
