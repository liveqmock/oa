
 /* Created on 2003-12-17
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.icss.oa.folder.control;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.common.log.ConnLog;
import com.icss.j2ee.services.DBConnectionLocator;
import com.icss.j2ee.servlet.ServletBase;
import com.icss.j2ee.servlet.download.DownloadResponse;
import com.icss.j2ee.util.Globals;
import com.icss.oa.folder.handler.FolderHandler;
import com.icss.oa.folder.vo.DbfilePackageVO;
/**
 * @author Administrator
 * 显示文件
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class ShowFileServlet extends ServletBase {

	protected void performTask(
		HttpServletRequest request,
		HttpServletResponse response)
		throws ServletException, IOException {
		Integer folderId = new Integer(request.getParameter("folderId"));
		Connection conn = null;
		InputStream is = null;
		try {
			conn =
				DBConnectionLocator.getInstance().getConnection(
					Globals.DATASOURCEJNDI);
			ConnLog.open("ShowFileServlet");
			FolderHandler handler = new FolderHandler(conn);

			//取得附件内容
			DbfilePackageVO vo = null;
			List list = handler.getDbFileList(folderId);
			if (list != null) {
				Iterator it = list.iterator();
				if (it.hasNext()) {
					vo = (DbfilePackageVO)it.next();
				}
			}
			
			String fileName = vo.getFpName();
			is = vo.getFdbfAccessory();
			DownloadResponse ds = new DownloadResponse(response);
//			System.out.println("*********************************");
//			System.out.println("开始向浏览器输出流...........");
//			System.out.println("*********************************");
//			ds.downloadInputStream(is, fileName);
			ds.downloadInputStreamByTempfile(is, fileName);
			//vo = null;

		} catch (Exception e) {
			handleError(e);
		} finally {
			
			try {
				if (is!=null) {
					is.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

			if (conn != null)
				try {
					conn.close();
					ConnLog.close("ShowFileServlet");
					if (is!=null){
						is.close();
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
		}

	}
}
