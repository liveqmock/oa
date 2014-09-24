package com.icss.oa.sendfile.admin;

import java.sql.Connection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.common.log.ConnLog;
import com.icss.j2ee.services.DBConnectionLocator;
import com.icss.j2ee.servlet.ServletBase;
import com.icss.oa.address.handler.Group;
import com.icss.oa.config.AddressConfig;
import com.icss.oa.hr.handler.HRGroupHandler;
import com.icss.resourceone.common.logininfo.UserInfo;
import com.icss.resourceone.sdk.framework.Context;

/**
 * 
 * To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Generation - Code and Comments
 */
public class SendFileSelectCommonPersonServlet extends ServletBase {
	protected void performTask(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException, java.io.IOException {
		String groupid = "";
		String groupid1 = "";
		String flag ="";
		if (request.getParameter("groupid") != "") {
			groupid = request.getParameter("groupid");
		}
		if (request.getParameter("groupid1") != "") {
			groupid1 = request.getParameter("groupid1");
		}
		if (request.getParameter("flag") != "") {
			flag = request.getParameter("flag");
		}
		Connection conn = null;
		try {
			if ((this.getServletContext().getInitParameter(AddressConfig.ADDRESSJNDI) == null) || (this.getServletContext().getInitParameter(AddressConfig.ADDRESSJNDI).equals(""))) {
				conn = getConnection(com.icss.j2ee.util.Globals.DATASOURCEJNDI);
				ConnLog.open("SelectCommonPersonServlet");
			} else {
				conn = DBConnectionLocator.getInstance().getConnection(this.getServletContext().getInitParameter(AddressConfig.ADDRESSJNDI));
				ConnLog.open("SelectCommonPersonServlet");
			}
			if("oa".equalsIgnoreCase(flag))
			{
				//取公共分组列表信息
				Context ctx = Context.getInstance();
				UserInfo user = ctx.getCurrentLoginInfo();

				Group group = new Group(conn);
				List personlist = group.personInGroupbyName(new Integer(groupid), "1");

				request.setAttribute("personlist", personlist);
				request.setAttribute("groupid1", groupid1);
				this.forward(request, response, "/address/sendfile/selectcommonperson2.jsp");
			}
			else
			{

				//取公共分组列表信息
			HRGroupHandler handler = new HRGroupHandler(conn);
			List personlist = handler.getAllUuidByGroupid(groupid);
				
			System.out.println("#######################"+personlist.toString());
			
			request.setAttribute("personlist", personlist);
			request.setAttribute("groupid", request.getParameter("groupid1"));
			}
			
			
			this.forward(request, response, "/address/sendfile/selectcommonperson.jsp");
		} catch (Exception ex) {
			handleError(ex);
		} finally {
			try {
				if (conn != null) {
					conn.close();
					ConnLog.close("SelectCommonPersonServlet");
				}
			} catch (Exception e) {

			}
		}
	}
}
