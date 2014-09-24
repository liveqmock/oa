package com.icss.oa.tq.admin;

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
import com.icss.oa.tq.Webservice.TQSysMsg;
import com.icss.oa.tq.handler.TQMsgHandler;
import com.icss.resourceone.common.logininfo.UserInfo;
import com.icss.resourceone.sdk.framework.Context;
import com.icss.resourceone.sdk.framework.EntityException;

public class SendSysMsgServlet extends ServletBase {

	protected void performTask(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		Context ctx;
		Connection conn =null;
		try {
			conn = getConnection(Globals.DATASOURCEJNDI);
			ctx = Context.getInstance();
			UserInfo user = ctx.getCurrentLoginInfo();
			String username = user.getCnName();

			String title = request.getParameter("title");
			String digest = request.getParameter("digest");
			String body = request.getParameter("body");
			String endday = request.getParameter("endday") + " 23:59:59";

			// System.out.println("#######################"+endday);
			String umsg = "";
			String url = "/oabase/tq/sendSysMsg.jsp";
			if (title != null && title.length() > 0) {
				TQSysMsg msg = new TQSysMsg();

				// System.out.println(title + digest + body + endday);
				umsg = msg.creatMsg(title, digest, body, endday, "2", "0",
						username, "", "", "", "1", "");
				// System.out.println("#######umsg=" + umsg);

				if (umsg.endsWith("ERROR")) {
					url = "/oabase/tq/sendSysMsg.jsp?msg=error";
				} else {
					
					TQMsgHandler handler = new TQMsgHandler(conn);
					handler.saveMsg(username,title,digest,body,request.getParameter("endday"));
					
					url = "/oabase/tq/sendSysMsg.jsp?msg=ok";
				}
			}
			response.sendRedirect(url);
			// this.forward(request, response, "/tq/sendSysMsg.jsp");

		} catch (EntityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ServiceLocatorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(conn!=null){
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

	}
}