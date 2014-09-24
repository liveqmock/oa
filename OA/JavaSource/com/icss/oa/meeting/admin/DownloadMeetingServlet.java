/*
 * �������� 2004-4-3
 *
 * �����������ļ�ģ��Ϊ
 * ���� > ��ѡ�� > Java > �������� > �����ע��
 */
package com.icss.oa.meeting.admin;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.common.log.ConnLog;
import com.icss.j2ee.services.ServiceLocatorException;
import com.icss.j2ee.servlet.ServletBase;
import com.icss.j2ee.servlet.download.DownloadResponse;
import com.icss.j2ee.util.Globals;
import com.icss.oa.meeting.handler.HandlerException;
import com.icss.oa.meeting.handler.MeetingroomManagerHandler;

/**
 * @author Administrator
 *
 * ��������������ע�͵�ģ��Ϊ
 * ���� > ��ѡ�� > Java > �������� > �����ע��
 */
public class DownloadMeetingServlet extends ServletBase {

	/* ���� Javadoc��
	 * @see com.icss.j2ee.servlet.ServletBase#performTask(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Integer meetingId = null;
		boolean flag = false;
		MeetingroomManagerHandler handler = null;

		try {
			meetingId = new Integer(request.getParameter("meetingId"));
		} catch (Exception e) {
			e.printStackTrace();
			meetingId = null;
		}
		Connection conn = null;
		InputStream in = null;
		try {

			conn = this.getConnection(Globals.DATASOURCEJNDI);
			ConnLog.open("DownloadMeetingServlet");
			handler = new MeetingroomManagerHandler(conn);

			//System.out.println("ffffffffffffffffffff "+meetingId);

			if (meetingId != null) {
				in = handler.getStream1(meetingId);
			}

			if (in == null) {
				flag = true;
				String path = this.getServletContext().getRealPath("/images/defaultweblogo.gif");
				in = new FileInputStream(new File(path));
			}

			DownloadResponse dr = new DownloadResponse(response);
			//				if(flag)dr.downloadInputStream(in,"temp.gif");  
			//				  else{dr.downloadInputStream(in,handler.getMeetingPutVO(meetingId).getSittingName());}
			if (flag)
				dr.downloadInputStreamByTempfile(in, "temp.gif");
			else {
				dr.downloadInputStreamByTempfile(in, handler.getMeetingPutVO(meetingId).getSittingName());
			}

		} catch (HandlerException e) {
			e.printStackTrace();
			handleError(e);
		} catch (IOException e) {
			e.printStackTrace();
			handleError(e);
		} catch (ServletException e) {
			e.printStackTrace();
			handleError(e);
		} catch (ServiceLocatorException e) {
			e.printStackTrace();
			handleError(e);
		} finally {
			try {
				if (conn != null){
					conn.close();
					ConnLog.close("DownloadMeetingServlet");
				}
			} catch (SQLException e2) {

				e2.printStackTrace();
			}
			if (in != null) {
				in.close();
			}
		}

	}

}
