/*
 * Created on 2004-4-26
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */

package com.icss.oa.folder.control;

import java.sql.Connection;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;

import com.icss.common.log.ConnLog;
import com.icss.j2ee.services.DBConnectionLocator;
import com.icss.oa.folder.handler.FolderHandler;
import com.icss.j2ee.servlet.ServletBase;
import com.icss.j2ee.util.Globals;

/**
 * @author Administrator
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class DelShareServlet extends ServletBase {

	protected void performTask(
		HttpServletRequest request,
		HttpServletResponse response)
		throws ServletException, IOException {

		//Integer folderId =new Integer(request.getParameter("folderId"));
		Connection conn = null;
		String parentId = request.getParameter("parentId");
		String[] folder = request.getParameterValues("folderId");
		String folderId;

		try {
			conn =
				DBConnectionLocator.getInstance().getConnection(
					Globals.DATASOURCEJNDI);
			ConnLog.open("DelFileServlet");
			FolderHandler handler = new FolderHandler(conn);

			try {
				for (int i = 0; i < folder.length; i++) {
					folderId = folder[i];
					handler.deleteShare(new Integer(folderId));
				}

			} catch (Exception e) {
				e.printStackTrace();
			}

			response.sendRedirect("ShowRootFolderServlet");
			//	response.sendRedirect("ShowFileListServlet?parentId=" + parentId);
			
		} catch (Exception e) {
			e.printStackTrace();
			handleError(e);
			
		} finally {
			try {
				if (conn != null){
					conn.close();
					ConnLog.close("DelFileServlet");
				}
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}

	}
}
