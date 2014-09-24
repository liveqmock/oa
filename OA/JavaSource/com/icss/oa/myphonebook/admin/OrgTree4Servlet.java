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
import com.icss.j2ee.services.ServiceLocatorException;
import com.icss.j2ee.servlet.ServletBase;
import com.icss.j2ee.util.Globals;
import com.icss.oa.myphonebook.handler.HandlerException;
import com.icss.oa.myphonebook.handler.OrgHandler;
import com.icss.oa.myphonebook.vo.SysOrgVO;
import com.icss.resourceone.common.logininfo.UserInfo;
import com.icss.resourceone.sdk.framework.Context;
import com.icss.resourceone.sdk.framework.EntityException;
import com.icss.resourceone.sdk.framework.Organization;
/**
 * @author Administrator
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public class OrgTree4Servlet extends ServletBase {
	/* (non-Javadoc)
	 * @see com.icss.j2ee.servlet.ServletBase#performTask(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Connection conn = null;
		try {
			conn = this.getConnection(Globals.DATASOURCEJNDI);
			ConnLog.open("OrgTree4Servlet");
			OrgHandler handler = new OrgHandler(conn);

			Context ctx = null;
			UserInfo user = null;
			try {
				ctx = Context.getInstance();
				user = ctx.getCurrentLoginInfo();
			} catch (EntityException e) {
				e.printStackTrace();
			}
			Integer level = handler.getContextUserOrgLevel(user.getPersonUuid());

			Organization contextorg = handler.getContextUserOrg(user.getPersonUuid());

			SysOrgVO topOrgVO = handler.getOrgByCurrentUser(contextorg, 3);

			//			SysOrgVO topOrgVO=handler.getTopOrg();
			List list = handler.getOrgTreeList(topOrgVO.getOrguuid());
			request.setAttribute("list", list);
			request.setAttribute("topOrgVO", topOrgVO);
			this.forward(request, response, "/orgtree/orgTree4.jsp");
		} catch (HandlerException e) {
			e.printStackTrace();
			handleError(e);
		} catch (ServiceLocatorException e) {
			e.printStackTrace();
			handleError(e);
		} finally {
			try {
				if (conn != null) {
					conn.close();
					ConnLog.close("OrgTree4Servlet");
				}
			} catch (SQLException sqle) {
			}
		}
	}
}
