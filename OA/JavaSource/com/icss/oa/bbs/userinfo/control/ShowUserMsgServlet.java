/*
 * Created on 2004-2-13
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.icss.oa.bbs.userinfo.control;

import java.io.IOException;
import java.util.List;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.common.log.ConnLog;
import com.icss.j2ee.services.DBConnectionLocator;
import com.icss.j2ee.servlet.ServletBase;
import com.icss.j2ee.util.Globals;
import com.icss.oa.bbs.handler.UserMsgHandler;

/**
 * @author Administrator
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class ShowUserMsgServlet extends ServletBase {

	protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection conn = null;
		List list = null;
		String userId = request.getParameter("userId");
		String editFlag = request.getParameter("editFlag");
		String currUserId = request.getParameter("currUserId");
		try {
			conn = DBConnectionLocator.getInstance().getConnection(Globals.DATASOURCEJNDI);
			ConnLog.open("ShowUserMsgServlet");
			UserMsgHandler handler = new UserMsgHandler(conn);
			list = handler.getUserListById(userId);
			request.setAttribute("userList", list);
			request.setAttribute("currUserId", currUserId);
			if (editFlag == null)
				this.forward(request, response, "/bbs/userMsg.jsp");
			else
				this.forward(request, response, "/bbs/editUserMsg.jsp");
		} catch (Exception e) {
			handleError(e);
		} finally {
			try {
				if (conn != null){
					conn.close();
					ConnLog.close("ShowUserMsgServlet");
				}
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}

	}
}
