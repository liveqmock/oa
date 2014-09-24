package com.icss.oa.sendfile.admin;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.j2ee.services.ServiceLocatorException;
import com.icss.j2ee.servlet.ServletBase;
import com.icss.oa.address.handler.HandlerException;
import com.icss.oa.filetransfer.handler.personInfoHandler;
import com.icss.resourceone.common.logininfo.UserInfo;
import com.icss.resourceone.sdk.framework.Context;
import com.icss.resourceone.sdk.framework.EntityException;

public class SendFileOrgServlet extends ServletBase {

	protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Connection conn = null;

		Context ctx = null;
		String loadShow = request.getParameter("loadShow");
		if (loadShow==null){
			loadShow = "0";
		}
		try {

			conn = getConnection(com.icss.j2ee.util.Globals.DATASOURCEJNDI);

			ctx = Context.getInstance();
			UserInfo user = ctx.getCurrentLoginInfo();

			personInfoHandler _personInfoHandler = new personInfoHandler(conn);

			String orguuid = _personInfoHandler.getOrgUUID(user.getPersonUuid());

			String _orguuid = _personInfoHandler.getParentUUID(orguuid);

			request.setAttribute("_orguuid", _orguuid);
			request.setAttribute("loadShow", loadShow);
			this.forward(request, response, "/address/sendfile/selectbyorg.jsp");
		}
		catch (EntityException e) {
			e.printStackTrace();
			handleError(e);
		} catch (HandlerException e) {
			e.printStackTrace();
			handleError(e);
		} catch (ServiceLocatorException e) {
			e.printStackTrace();
			handleError(e);
		} finally {
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}

	}

}
