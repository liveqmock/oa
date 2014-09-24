/*
 * Created on 2004-4-12
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */

package com.icss.oa.log.admin;

import java.io.IOException;
import java.util.List;
import java.sql.Connection;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.icss.j2ee.services.DBConnectionLocator;
import com.icss.j2ee.servlet.ServletBase;
import com.icss.j2ee.util.Globals;
import com.icss.oa.bbs.handler.UserMsgHandler;
import com.icss.oa.log.handler.LogHandler;
import com.icss.oa.addressbook.handler.AddressbookHandler;

/**
 * @author Administrator
 * 
 * To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Generation - Code and Comments
 */

public class LogLeftTreeServlet extends ServletBase {
	protected void performTask(
		HttpServletRequest request,
		HttpServletResponse response)
		throws ServletException, IOException {
		Connection conn = null;
		List list = null;
		
		
		try {
			conn =
				DBConnectionLocator.getInstance().getConnection(
					Globals.DATASOURCEJNDI);
			LogHandler handler = new LogHandler(conn);
			UserMsgHandler userHandler = new UserMsgHandler(conn);
			String userId = userHandler.getUserId();
			System.out.println("***********************"+userId);
			list = handler.getAllsysList();
			
			
			if (list != null && list.size() > 0) {
				request.setAttribute("hasFolder", "yes");
				System.out.println("*********listsize**************"+list.size());
			} else {
				request.setAttribute("hasFolder", "no");
				System.out.println("*********listsize**************"+list.size());
			}
			
			
			request.setAttribute("folderList", list);
			this.forward(request, response, "/log/tree/LogTreeList.jsp");

		} catch (Exception e) {
			handleError(e);
		} finally {
			try {
				if (conn != null)
					conn.close();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}
}
