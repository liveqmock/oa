/*
 * Created on 2004-4-21
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package com.icss.oa.myphonebook.admin;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.common.log.ConnLog;
import com.icss.j2ee.services.DBConnectionLocator;
import com.icss.j2ee.services.ServiceLocatorException;
import com.icss.j2ee.servlet.ServletBase;
import com.icss.oa.address.handler.Group;
import com.icss.oa.config.AddressConfig;
import com.icss.orgtree.handler.HandlerException;
import com.icss.oa.myphonebook.handler.OrgHandler;
import com.icss.oa.myphonebook.vo.SysOrgVO;
import com.icss.resourceone.common.logininfo.UserInfo;
import com.icss.resourceone.sdk.framework.Context;
import com.icss.resourceone.sdk.framework.EntityException;

/**
 * @author Administrator
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public class OrgTree2Servlet extends ServletBase {
	/* (non-Javadoc)
	 * @see com.icss.j2ee.servlet.ServletBase#performTask(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String sendMail = request.getParameter("sendMail")==null?"0":request.getParameter("sendMail");
		Connection conn = null;
		try {
			//			conn=this.getConnection(Globals.DATASOURCEJNDI);
			if ((this.getServletContext().getInitParameter(AddressConfig.ADDRESSJNDI) == null) || (this.getServletContext().getInitParameter(AddressConfig.ADDRESSJNDI).equals(""))) {
				conn = getConnection(com.icss.j2ee.util.Globals.DATASOURCEJNDI);
				ConnLog.open("OrgTree2Servlet");
			} else {
				conn = DBConnectionLocator.getInstance().getConnection(this.getServletContext().getInitParameter(AddressConfig.ADDRESSJNDI));
				ConnLog.open("OrgTree2Servlet");
			}
			OrgHandler handler = new OrgHandler(conn);
			SysOrgVO topOrgVO = handler.getTopOrg();
			List list = handler.getOrgTreeList(topOrgVO.getOrguuid());
			request.setAttribute("list", list);
			request.setAttribute("topOrgVO", topOrgVO);

			//取个人分组列表信息
			Context ctx = Context.getInstance();
			UserInfo user = ctx.getCurrentLoginInfo();

			Group group = new Group(conn);
			List indiGroup = group.individualGroupList(user.getPersonUuid());
			request.setAttribute("indiGroup", indiGroup);

			//取公共分组列表信息
			List commonGroupList = group.commonGroupList(user.getPersonUuid());
			List commonTwoGroupList = group.listCommonTwoGroup();
			request.setAttribute("commonGroup", commonGroupList);
			request.setAttribute("commonTwoGroup", commonTwoGroupList);

			this.forward(request, response, "/orgtree/orgTree2.jsp?sendMail="+sendMail);
		} catch (EntityException e) {
			e.printStackTrace();
			handleError(e);
		} catch (ServiceLocatorException e) {
			e.printStackTrace();
			handleError(e);
		} catch (Exception e) {
			e.printStackTrace();
			handleError(e);
		} finally {
			try {
				if (conn != null) {
					conn.close();
					ConnLog.close("OrgTree2Servlet");
				}
			} catch (SQLException sqle) {
			}
		}
	}
}
