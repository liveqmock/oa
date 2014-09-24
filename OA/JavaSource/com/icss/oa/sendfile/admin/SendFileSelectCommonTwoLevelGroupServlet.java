package com.icss.oa.sendfile.admin;

import java.sql.Connection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
public class SendFileSelectCommonTwoLevelGroupServlet extends ServletBase {
	protected void performTask(HttpServletRequest request,
			HttpServletResponse response)
			throws javax.servlet.ServletException, java.io.IOException {
		String groupid = "";
		String parentid = "";
		String flag = "";
		if (request.getParameter("groupid")!=null ) {
			groupid = request.getParameter("groupid");
		}

		if (request.getParameter("parentid")!=null) {
			parentid = request.getParameter("parentid");
		}
		
		if (request.getParameter("flag")!=null) {
			flag = request.getParameter("flag");
		}


		List commonTwoGroupList = null;
		if("oa".equalsIgnoreCase(flag))
		{
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

			    commonTwoGroupList = group.listCommonTwoGroup(new Integer(groupid));
			    request.setAttribute("groupid", groupid);
				request.setAttribute("commonTwoGroupList", commonTwoGroupList);
				this.forward(request, response,
						"/address/sendfile/selectcommontwolevelgroup2.jsp?groupid="
								+ groupid + "&amp;parentid=" + parentid);
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
		else
		{
			if ("".endsWith(groupid)) {
				HttpSession session = request.getSession();
				commonTwoGroupList =(List)session.getAttribute("toplist");
				
			}else{
			HRGroupHandler handler = new HRGroupHandler();
			commonTwoGroupList = handler.getGroupByParentid(groupid);
			}
			request.setAttribute("commonTwoGroupList", commonTwoGroupList);
			this.forward(request, response,
					"/address/sendfile/selectcommontwolevelgroup.jsp?groupid="
							+ groupid + "&amp;parentid=" + parentid);
		}

	}
}
