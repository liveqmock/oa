/*
 * Created on 2004-4-23
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.icss.oa.folder.control;

import java.io.IOException;
import java.sql.Connection;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.common.log.ConnLog;
import com.icss.j2ee.services.DBConnectionLocator;
import com.icss.j2ee.servlet.ServletBase;
import com.icss.j2ee.util.Globals;
import com.icss.oa.folder.handler.FolderHandler;
import com.icss.oa.folder.vo.FolderPackageVO;

/**
 * @author Administrator
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class ShowFolderServlet extends ServletBase {

	protected void performTask(
		HttpServletRequest request,
		HttpServletResponse response)
		throws ServletException, IOException {
		Integer folderId = new Integer(request.getParameter("folderId"));
		String parentId = request.getParameter("parentId");
		Connection conn = null;
		try {
			conn =
				DBConnectionLocator.getInstance().getConnection(
					Globals.DATASOURCEJNDI);
			ConnLog.open("ShowFolderServlet");
			FolderHandler handler = new FolderHandler(conn);
			FolderPackageVO vo = new FolderPackageVO();
			vo = handler.getFolderVO(folderId);
			request.setAttribute("folderVO", vo);
			request.setAttribute("folderId", folderId.toString());
			request.setAttribute("parentId", parentId);
			this.forward(request, response, "/mail/ShowFolder.jsp");

		} catch (Exception e) {
			handleError(e);
		} finally {
			try {
				if (conn != null){
					conn.close();
					ConnLog.close("ShowFolderServlet");
				}
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}
}