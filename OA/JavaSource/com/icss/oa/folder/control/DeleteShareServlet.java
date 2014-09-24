/*
 * Created on 2004-4-26
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
import com.icss.oa.bbs.handler.UserMsgHandler;
import com.icss.oa.folder.handler.FolderHandler;

/**
 * @author Administrator
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class DeleteShareServlet extends ServletBase {

	protected void performTask(
		HttpServletRequest request,
		HttpServletResponse response)
		throws ServletException, IOException {
		
		Connection conn = null;
		Integer folderId =new Integer(request.getParameter("folderId"));
		String parentId =request.getParameter("parentId");
		String check[] = request.getParameterValues("personid");
		
		try {
			conn =DBConnectionLocator.getInstance().getConnection(
					Globals.DATASOURCEJNDI);
			ConnLog.open("DeleteShareServlet");
			FolderHandler handler = new FolderHandler(conn);
			UserMsgHandler userMsgHandler = new UserMsgHandler(conn);
			for(int i=0;i<check.length;++i){
				try{
					handler.deleteShare(folderId,check[i]);
				}
				catch(Exception e){
					e.printStackTrace();
				}
			}
			response.sendRedirect("GetShareServlet?folderId="+folderId+"&parentId="+parentId);
		} catch (Exception e) {
			handleError(e);
		} finally {
			try {
				if (conn != null){
					conn.close();
					ConnLog.close("DeleteShareServlet");
				}
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}

	}
}