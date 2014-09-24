package com.icss.oa.sms.admin;

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
import com.icss.oa.address.vo.SelectOrgpersonVO;
import com.icss.oa.sms.handler.SMSHandler;

public class AddRoleInfoServlet extends ServletBase {

	protected void performTask(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Connection conn = null;
		HttpSession session = null;
		try {
			try {
				conn = getConnection(Globals.DATASOURCEJNDI);
				ConnLog.open("AddRoleInfoServlet");
			} catch (ServiceLocatorException e) {
				throw new RuntimeException("取得数据库连接错误");
			}
			Integer id = new Integer(request.getParameter("id"));
			SMSHandler handler = new SMSHandler(conn);
			session = request.getSession();
			AddressHelp helper = new AddressHelp(conn);
			List personList = helper.getperson((List) session
					.getAttribute("selectorgperson"), request,
					"selectorgperson");

			if (personList != null) {
				Iterator personIterator = personList.iterator();
				while (personIterator.hasNext()) {
					SelectOrgpersonVO selectOrgpersonVO = (SelectOrgpersonVO) personIterator
							.next();

					if (handler.isPersonInRole(selectOrgpersonVO.getUseruuid(),
							id)) {
						continue;
					}
					handler.appendRoleWithPerson(selectOrgpersonVO
							.getUseruuid(), id);
				}
			}

			this.forward(request, response, "/servlet/ListRoleInfoServlet");

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
					ConnLog.close("AddRoleInfoServlet");
				}
			} catch (SQLException e1) {
				throw new RuntimeException("数据库连接关闭错误");
			}
			if (session != null) {
				session.setAttribute("selectorgperson",
						new java.util.ArrayList());
			}
		}
	}

}
