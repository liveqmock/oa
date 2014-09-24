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
import com.icss.resourceone.common.logininfo.UserInfo;
import com.icss.resourceone.sdk.framework.Context;
/**
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public class SelectCommonTwoLevelGroupServlet extends ServletBase {
	protected void performTask(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException, java.io.IOException {
		String doFunction = "";
		if (request.getParameter("doFunction") != "") {
			doFunction = request.getParameter("doFunction");
		}
		//			System.out.println("SelectCommonGroupServlet_doFunction="+doFunction);
		String sessionname = "";
		if (request.getParameter("sessionname") != "") {
			sessionname = request.getParameter("sessionname");
		}
		String groupid = "";
		if (request.getParameter("groupid") != "") {
			groupid = request.getParameter("groupid");
		}
		//			System.out.println("SelectCommonGroupServlet_sessionname="+sessionname);

		Connection conn = null;
		try {
			if ((this.getServletContext().getInitParameter(AddressConfig.ADDRESSJNDI) == null) || (this.getServletContext().getInitParameter(AddressConfig.ADDRESSJNDI).equals(""))) {
				conn = getConnection(com.icss.j2ee.util.Globals.DATASOURCEJNDI);
				ConnLog.open("SelectCommonTwoLevelGroupServlet");
			} else {
				conn = DBConnectionLocator.getInstance().getConnection(this.getServletContext().getInitParameter(AddressConfig.ADDRESSJNDI));
				ConnLog.open("SelectCommonTwoLevelGroupServlet");
			}

			//取公共分组列表信息
			Context ctx = Context.getInstance();
			UserInfo user = ctx.getCurrentLoginInfo();

			Group group = new Group(conn);

			if (("1").equals(request.getParameter("shouquan"))) {
				if (!group.isAccreded(new Integer(groupid), user.getPersonUuid())) {
					this.forward(request, response, "/address/errorString.jsp?errorS=您没有被授权查看该根分组信息！");
				}

			}

			List commonTwoGroupList = group.listCommonTwoGroup(new Integer(groupid));

			request.setAttribute("commonTwoGroupList", commonTwoGroupList);
			System.out.println("commonGroupList大小=" + commonTwoGroupList.size());
			this.forward(request, response, "/address/pubaddress/selectcommontwolevelgroup.jsp?doFunction=" + doFunction + "&sessionname=" + sessionname + "&groupid=" + groupid);
		} catch (Exception ex) {
			handleError(ex);
		} finally {
			try {
				if (conn != null){
					conn.close();
					ConnLog.close("SelectCommonTwoLevelGroupServlet");
				}
			} catch (Exception e) {

			}
		}
	}
}
