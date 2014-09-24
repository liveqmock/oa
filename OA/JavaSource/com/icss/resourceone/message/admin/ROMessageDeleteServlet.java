package com.icss.resourceone.message.admin;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.j2ee.dao.DAOException;
import com.icss.j2ee.services.ServiceLocatorException;
import com.icss.j2ee.servlet.ServletBase;
import com.icss.j2ee.util.Globals;
import com.icss.resourceone.message.handler.MessageHandler;

/**
 * 删除消息
 * @version 	1.0
 * @author
 */
public class ROMessageDeleteServlet extends ServletBase {

	protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//消息ID
		Integer messageid = request.getParameter("messageid") == null ? new Integer(-1) : Integer.valueOf(request.getParameter("messageid"));

		System.out.println("/servlet/ROMessageDeleteServlet messageid = " + messageid);

		Connection conn = null;
		try {
			conn = this.getConnection(Globals.DATASOURCEJNDI);

			//添加消息
			MessageHandler handler = new MessageHandler(conn);
			handler.deleteMessage(messageid);

			//消息列表
			response.sendRedirect(request.getContextPath() + "/servlet/ROMessageListServlet");

		} catch (ServiceLocatorException e) {
			e.printStackTrace();
			handleError(e);

		} catch (SQLException e) {
			e.printStackTrace();
			handleError(e);
			
		} finally {
			try {
				if (conn != null) {

					conn.close();
				}
			} catch (SQLException e1) {

				e1.printStackTrace();
			}
		}

	}

}
