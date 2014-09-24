package com.icss.oa.sendfile.admin;

import java.sql.Connection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.common.log.ConnLog;
import com.icss.j2ee.services.DBConnectionLocator;
import com.icss.j2ee.servlet.ServletBase;
import com.icss.oa.address.handler.Group;
import com.icss.oa.address.handler.NewGroupHandler;
import com.icss.oa.config.AddressConfig;

/**
 * 
 * 个人分组的选择页面数据
 */
public class SendFileSelectIndiPersonServlet extends ServletBase {
	protected void performTask(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException, java.io.IOException {
		String groupid = "";
		if (request.getParameter("groupid") != "") {
			groupid = request.getParameter("groupid");
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
			//Group group = new Group(conn);
			//原来没有删除 已经删除人员
			NewGroupHandler group = new NewGroupHandler(conn);
			List personlist = group.personInGroupbyName(new Integer(groupid), "2");
			request.setAttribute("personlist", personlist);
			this.forward(request, response, "/address/sendfile/selectindiperson.jsp");
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
