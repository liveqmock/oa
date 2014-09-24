/*
 * Created on 2003-12-17
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
/*以上代码是站点统计的扩展标记需要加入的类*/


/**
 *删除文件
 * @author Administrator
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class DelFileServlet extends ServletBase {

	protected void performTask(
		HttpServletRequest request,
		HttpServletResponse response)
		throws ServletException, IOException {
		Connection conn = null;
		String[] folder = request.getParameterValues("folderId");
		String parentId = request.getParameter("parentId");
	
		try {
			conn =
				DBConnectionLocator.getInstance().getConnection(
					Globals.DATASOURCEJNDI);
			ConnLog.open("DelFileServlet");
			conn.setAutoCommit(false);
			FolderHandler handler = new FolderHandler(conn);

			String folderId = null;
			FolderPackageVO folderVO = new FolderPackageVO();
			String parentLevel = null;

			for (int i = 0; i < folder.length; i++) {
				folderId = folder[i];
				handler.delFile(new Integer(folderId));
			}
			conn.commit();
			String nextpage = "";
			if (parentId.equals("1"))
				nextpage = "/servlet/ShowRootFolderServlet";
			else
				nextpage = "/servlet/ShowFileListServlet?parentId=" + parentId;
			request.setAttribute("nextpage",nextpage);
			this.forward(request, response, "/mail/MailReload.jsp");
		} catch (Exception e) {
			e.printStackTrace();
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
