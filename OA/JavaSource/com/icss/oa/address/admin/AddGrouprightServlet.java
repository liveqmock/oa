/*
 * Created on 2004-4-26
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.icss.oa.address.admin;         

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Iterator;
import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.common.log.ConnLog;
import com.icss.j2ee.util.Globals;
import com.icss.j2ee.services.ServiceLocatorException;
import com.icss.j2ee.servlet.ServletBase;

import com.icss.oa.address.handler.AddressHelp;
import com.icss.oa.address.handler.Group;
import com.icss.oa.address.vo.SelectOrgpersonVO;

/**
 * @author Administrator
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class AddGrouprightServlet extends ServletBase {  

	protected void performTask(
		HttpServletRequest request,
		HttpServletResponse response)  
		throws ServletException, IOException {
		Connection conn = null;
		HttpSession session = null;
		try {
			try {
				conn = getConnection(Globals.DATASOURCEJNDI);
				ConnLog.open("AddGrouprightServlet");
			} catch (ServiceLocatorException e) {
				throw new RuntimeException("取得数据库连接错误");
			}
			String groupid = request.getParameter("groupid");

			Group group = new Group(conn);
			session = request.getSession();

			AddressHelp helper = new AddressHelp(conn);
			List personList =
				helper.getperson(
					(List) session.getAttribute("selectperson"),request,"selectperson");
			addPerson2Group(groupid, group, personList);

			this.forward(request, response, "/servlet/ListGrouprightServlet");
			
		} catch (NumberFormatException ne) {
			ne.printStackTrace();
			handleError(ne);
			
		} catch (Exception e) {
			e.printStackTrace();
			handleError(e);
			
		} finally {
			try {
				if (conn != null) {
					conn.close();
					ConnLog.close("AddGrouprightServlet");
				}
			} catch (SQLException e1) {
				throw new RuntimeException("数据库连接关闭错误");
			}
			if (session != null) {
				session.setAttribute("selectperson", new java.util.ArrayList());
			}
		}
	}
	private void addPerson2Group(
		String groupid,
		Group group,
		List personList) {
		if (personList != null) {
			Iterator personIterator = personList.iterator();
			while (personIterator.hasNext()) {
				SelectOrgpersonVO selectOrgpersonVO =
					(SelectOrgpersonVO) personIterator.next();
				if (group
					.isAccreded(
						new Integer(groupid),
						selectOrgpersonVO.getUseruuid())) {
					continue;
				}
				group.accreditGroup2Person(
					new Integer(groupid),
					selectOrgpersonVO.getUseruuid());
			}
		}
	}

}
