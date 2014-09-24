package com.icss.resourceone.message.admin;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.j2ee.dao.DAOException;
import com.icss.j2ee.services.ServiceLocatorException;
import com.icss.j2ee.servlet.ServletBase;
import com.icss.j2ee.util.Globals;
import com.icss.resourceone.message.handler.MessageHandler;

/**
 * 消息列表
 * @version 	1.0
 * @author
 */
public class ROMessageListServlet extends ServletBase {

	protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//错误信息
		String errorMsg = request.getParameter("errorMsg") == null?"":request.getParameter("errorMsg");
		Connection conn = null;
		try {
		
			
			conn = this.getConnection(Globals.DATASOURCEJNDI);
			
			//取得RoMessageVO对象的集合
			MessageHandler handler = new MessageHandler(conn);
			List list = handler.getMessages();

			request.setAttribute("list", list);
			request.setAttribute("errorMsg",errorMsg);
			this.forward(request, response, "/romessage/ROMessageList.jsp");
			
		} catch (DAOException e) {
			e.printStackTrace();
			handleError(e);
			
		} catch (ServiceLocatorException e) {
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


//Context ctx = new InitialContext();
//DataSource ds = (DataSource) ctx.lookup("jdbc/OABASE");
//conn = ds.getConnection();		