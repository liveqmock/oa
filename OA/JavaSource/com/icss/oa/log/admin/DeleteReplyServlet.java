/*
 * Created on 2003-12-17
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */

package com.icss.oa.log.admin;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.j2ee.services.DBConnectionLocator;
import com.icss.j2ee.servlet.ServletBase;
import com.icss.j2ee.util.Globals;
import com.icss.oa.addressbook.handler.AddressbookHandler;
import com.icss.oa.addressbook.vo.AddressbookFolderVO;
import com.icss.oa.log.handler.LogHandler;
import com.icss.oa.log.vo.LogSysVO;


/**
 *É¾³ýÎÄ¼þ
 * @author Administrator
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class DeleteReplyServlet extends ServletBase {

	protected void performTask(
		HttpServletRequest request,
		HttpServletResponse response)
		throws ServletException, IOException {
		Connection conn = null;
		

		String sysid = request.getParameter("sysid")==null?"-1":request.getParameter("sysid");
		String logid = request.getParameter("logid")==null?"-1":request.getParameter("logid");
		String replyid = request.getParameter("replyid")==null?"-1":request.getParameter("replyid");
		
		try {
			conn =
				DBConnectionLocator.getInstance().getConnection(
					Globals.DATASOURCEJNDI);
			conn.setAutoCommit(false);
			LogHandler handler = new LogHandler(conn);
			System.out.println("++++++++++++++++++DeleteReplyServlet"+replyid);
			handler.deleteReply(replyid);
			System.out.println("++++++++++++++++++DeleteReplyServlet");
			conn.commit();
//			this.forward(request, response, "/servlet/ShowLogMsgServlet?parentId="+sysid);
			response.sendRedirect("LogDetailServlet?sysid=" + sysid+"&logid="+logid);

		} catch (Exception e) {
			e.printStackTrace();
			
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
