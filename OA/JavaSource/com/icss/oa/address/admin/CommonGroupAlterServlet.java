/*
 * Created on 2004-4-12
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.icss.oa.address.admin;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.common.log.ConnLog;
import com.icss.j2ee.util.Globals;
import com.icss.j2ee.services.ServiceLocatorException;
import com.icss.j2ee.servlet.ServletBase;

import com.icss.oa.address.handler.Group;
import com.icss.oa.address.vo.AddressCommongroupVO;
import com.icss.resourceone.common.logininfo.UserInfo;
import com.icss.resourceone.sdk.framework.Context;

/**
 * @author Administrator
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class CommonGroupAlterServlet extends ServletBase {

	protected void performTask(
		HttpServletRequest request,
		HttpServletResponse response)
		throws ServletException, IOException {
		Connection conn = null;
		try {
			try {
				conn = getConnection(Globals.DATASOURCEJNDI);
				ConnLog.open("CommonGroupAlterServlet");
			} catch (ServiceLocatorException e) {
				throw new RuntimeException("取得数据库连接错误");
			}
			Group group = new Group(conn);
			String groupName = request.getParameter("groupName").trim();
		/*	if (group.isCommonGroupNameExist(groupName)) {
				request.setAttribute("groupNameExist", "true");
				request.setAttribute("groupName", groupName);
			} else {*/
			Context ctx = Context.getInstance();
			UserInfo user = ctx.getCurrentLoginInfo();
				AddressCommongroupVO addressCommonGroupVO =
					new AddressCommongroupVO();
				addressCommonGroupVO.setId(
					new Integer(request.getParameter("groupid")));
				addressCommonGroupVO.setGroupname(groupName);
				addressCommonGroupVO.setGroupdes(
					request.getParameter("groupDes"));
				addressCommonGroupVO.setNeedaccredit(
					request.getParameter("needAccredit"));
				addressCommonGroupVO.setLevels(new Integer(0));
				addressCommonGroupVO.setParentid(new Integer(0));
				addressCommonGroupVO.setRootid(new Integer(0));
				addressCommonGroupVO.setOwner(user.getPersonUuid());
					
				//将该组中的授权信息删除
				if(("0").equals(request.getParameter("needAccredit"))&&("1").equals(request.getParameter("OldAccredit")))
				{
				group.delAllAccreditPerson(new Integer(request.getParameter("groupid")));
				}
				group.alterCommonGroup(addressCommonGroupVO);
			/*}*/
			this.forward(request, response, "/servlet/ListCommonGroupServlet");
			
		} catch (Exception e) {
			e.printStackTrace();
			handleError(e);
			
		} finally {
			try {
				if (conn != null) {
					conn.close();
					ConnLog.close("CommonGroupAlterServlet");
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
				//throw new RuntimeException("数据库连接关闭错误");
			}
		}
	}

}
