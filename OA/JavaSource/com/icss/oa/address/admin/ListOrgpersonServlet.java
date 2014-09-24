package com.icss.oa.address.admin;

import javax.servlet.http.*;
import java.util.*;
import java.sql.Connection;

import com.icss.common.log.ConnLog;
import com.icss.j2ee.servlet.ServletBase;
import com.icss.resourceone.common.logininfo.UserInfo;
import com.icss.resourceone.sdk.framework.Context;

import com.icss.oa.address.handler.*;
/**
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public class ListOrgpersonServlet extends ServletBase {
	protected void performTask(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException, java.io.IOException {
		Connection conn = null;
		try {
			conn = getConnection(com.icss.j2ee.util.Globals.DATASOURCEJNDI);
			ConnLog.open("ListOrgpersonServlet");
			//取组织列表信息
			String orguuid = request.getParameter("orgid");
			String orgname = request.getParameter("orgname");

			SysOrgpersonHandler handler = new SysOrgpersonHandler(conn);
			List list = handler.getList(orguuid);

			request.setAttribute("personlist", list);
			this.forward(request, response, "/address/pubaddress/PubAddress.jsp?orgName=" + orgname);
		} catch (Exception ex) {
			handleError(ex);
		} finally {
			try {
				if (conn != null) {
					conn.close();
					ConnLog.close("ListOrgpersonServlet");
				}
			} catch (Exception e) {

			}
			//
		}
	}
}
