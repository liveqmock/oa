/*
 * Created on 2004-2-19
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.icss.oa.bbs.user.control;

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
import com.icss.oa.bbs.handler.BBSHandler;
import com.icss.oa.bbs.vo.BbsAccVO;

/**
 * @author Administrator
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class DownloadAccServlet extends ServletBase {

	protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Integer articleId = new Integer(request.getParameter("articleId"));
		Connection conn = null;
		InputStream is = null;
		try {
			conn = DBConnectionLocator.getInstance().getConnection(Globals.DATASOURCEJNDI);
			ConnLog.open("DownloadAccServlet");
			//String auuid = request.getParameter("auuid");

			BBSHandler handler = new BBSHandler(conn);

			//取得附件内容
			List list = handler.getAccList(articleId);
			Iterator i = list.iterator();
			String fileName = "";
			if (i.hasNext()) {
				BbsAccVO vo = (BbsAccVO) i.next();
				is = vo.getAcclns();
				fileName = vo.getAccname();
			}

			DownloadResponse ds = new DownloadResponse(response);
			//			ds.downloadInputStream(is, fileName);
			ds.downloadInputStreamByTempfile(is, fileName);

		} catch (Exception e) {
			e.printStackTrace();
			handleError(e);

		} finally {
			if (conn != null)
				try {
					conn.close();
					ConnLog.close("DownloadAccServlet");
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			if (is != null) {
				try {
					is.close();
				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		}
	}

}
