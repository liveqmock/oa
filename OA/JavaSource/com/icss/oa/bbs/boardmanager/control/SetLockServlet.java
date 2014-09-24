/*
 * Created on 2004-2-25
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.icss.oa.bbs.boardmanager.control;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.common.log.ConnLog;
import com.icss.j2ee.services.DBConnectionLocator;
import com.icss.j2ee.servlet.ServletBase;
import com.icss.j2ee.util.Globals;
import com.icss.oa.bbs.handler.BBSHandler;

/**
 * @author Administrator
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class SetLockServlet extends ServletBase {

	protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection conn = null;
		String boardId = request.getParameter("boardId");
		Integer topicId = new Integer(request.getParameter("topicId"));
		String lock = request.getParameter("lock");
		String auditFlag = request.getParameter("auditFlag");
		try {
			conn = DBConnectionLocator.getInstance().getConnection(Globals.DATASOURCEJNDI);
			ConnLog.open("SetLockServlet");
			BBSHandler handler = new BBSHandler(conn);
			handler.setArticleLock(topicId, lock);
			if (auditFlag == null)
				response.sendRedirect("ShowTopicServlet?boardId=" + boardId + "&primeFlag=0" + "&manageFlag=1");
			else if (auditFlag.equals("1"))
				response.sendRedirect("ShowUnauditServlet?boardId=" + boardId);
		} catch (Exception e) {
			handleError(e);
		} finally {
			try {
				if (conn != null){
					conn.close();
					ConnLog.close("SetLockServlet");
				}
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}

	}
}
