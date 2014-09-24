
/* Created on 2003-12-17
*
* To change the template for this generated file go to
* Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
*/
package com.icss.oa.meeting.admin;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.common.log.ConnLog;
import com.icss.j2ee.services.DBConnectionLocator;
import com.icss.j2ee.services.DBConnectionLocatorException;
import com.icss.j2ee.servlet.ServletBase;
import com.icss.j2ee.servlet.download.DownloadResponse;
import com.icss.j2ee.util.Globals;
import com.icss.oa.meeting.handler.HandlerException;
import com.icss.oa.meeting.handler.MeetingroomManagerHandler;
/**
* @author Administrator
* ÏÔÊ¾ÎÄ¼þ
* To change the template for this generated type comment go to
* Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
*/
public class ShowAccessoryServlet extends ServletBase {

	protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Integer MeetingId = new Integer(request.getParameter("MeetingId"));
		Connection conn = null;
		try {
			conn = DBConnectionLocator.getInstance().getConnection(Globals.DATASOURCEJNDI);
			ConnLog.open("ShowAccessoryServlet");
			MeetingroomManagerHandler handler = new MeetingroomManagerHandler(conn);

			InputStream is = handler.getMeetingroomAccessory(MeetingId);
			DownloadResponse ds = new DownloadResponse(response);
			//			ds.downloadInputStream(is,"temp.gif");
			ds.downloadInputStreamByTempfile(is, "temp.gif");

		} catch (DBConnectionLocatorException e) {
			e.printStackTrace();
			handleError(e);
		} catch (HandlerException e) {
			e.printStackTrace();
			handleError(e);
		} finally {
			if (conn != null)
				try {
					conn.close();
					ConnLog.close("ShowAccessoryServlet");
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
		}

	}
}
