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
import com.icss.resourceone.common.logininfo.UserInfo;
import com.icss.resourceone.sdk.framework.Context;

/**
 * 
 * To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Generation - Code and Comments
 */
public class SendFileSelectCommonGroupServlet extends ServletBase {
	protected void performTask(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException, java.io.IOException {
		Connection conn = null;
		try {
			if ((this.getServletContext().getInitParameter(AddressConfig.ADDRESSJNDI) == null) || (this.getServletContext().getInitParameter(AddressConfig.ADDRESSJNDI).equals(""))) {
				conn = getConnection(com.icss.j2ee.util.Globals.DATASOURCEJNDI);
				ConnLog.open("SelectCommonGroupServlet");
			} else {
				conn = DBConnectionLocator.getInstance().getConnection(this.getServletContext().getInitParameter(AddressConfig.ADDRESSJNDI));
				ConnLog.open("SelectCommonGroupServlet");
			}
			String flag = request.getParameter("flag");

			//取公共分组列表信息
			Context ctx = Context.getInstance();
			UserInfo user = ctx.getCurrentLoginInfo();

			Group group = new Group(conn);
			List commonGroupList = group.commonGroupList(user.getPersonUuid());
			request.setAttribute("flag", flag);
			request.setAttribute("commonGroup", commonGroupList);
			this.forward(request, response, "/address/sendfile/selectcommongroup.jsp");
		} catch (Exception ex) {
			handleError(ex);
		} finally {
			try {
				if (conn != null) {
					conn.close();
					ConnLog.close("SelectCommonGroupServlet");
				}
			} catch (Exception e) {

			}
		}
	}
}
