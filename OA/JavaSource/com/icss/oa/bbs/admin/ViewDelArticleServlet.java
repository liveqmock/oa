package com.icss.oa.bbs.admin;

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
import com.icss.oa.bbs.handler.BBSBoardHandler;
import com.icss.oa.bbs.handler.BBSHandler;
import com.icss.resourceone.common.logininfo.UserInfo;
import com.icss.resourceone.sdk.framework.Context;
import com.icss.resourceone.sdk.framework.EntityException;

public class ViewDelArticleServlet extends ServletBase {

	@Override
	protected void performTask(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		Connection conn = null;
		List mlist = null;

		try {
			conn = getConnection(com.icss.j2ee.util.Globals.DATASOURCEJNDI);
			BBSHandler handler = new BBSHandler(conn);
			BBSBoardHandler bhandler = new BBSBoardHandler(conn);
			Context ctx = Context.getInstance();
			String uuid = ctx.getCurrentPersonUuid();

			List alist = handler.getDelArticle(uuid);
			request.setAttribute("alist", alist);
			this.forward(request, response, "/bbs/delArticle.jsp");

		} catch (ServiceLocatorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (EntityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

		}

	}

}