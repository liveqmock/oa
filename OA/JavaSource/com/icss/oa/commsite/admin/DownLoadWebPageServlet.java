/*
 * �������� 2004-4-3
 *
 * �����������ļ�ģ��Ϊ
 * ���� > ��ѡ�� > Java > �������� > �����ע��
 */
package com.icss.oa.commsite.admin;

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
import com.icss.j2ee.servlet.ServletBase;
import com.icss.j2ee.servlet.download.DownloadResponse;
import com.icss.j2ee.util.Globals;
import com.icss.oa.commsite.handler.CommsiteHandler;

/**
 * @author Administrator
 *
 * ��������������ע�͵�ģ��Ϊ
 * ���� > ��ѡ�� > Java > �������� > �����ע��
 */
public class DownLoadWebPageServlet extends ServletBase {

	/* ���� Javadoc��
	 * @see com.icss.j2ee.servlet.ServletBase#performTask(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	protected void performTask(
		HttpServletRequest request,
		HttpServletResponse response)
		throws ServletException, IOException {
			
			
			Integer ocsId = null;
			try{
				ocsId = new Integer(request.getParameter("ocsId"));
			}
			catch(Exception e){
				e.printStackTrace();
				ocsId = null;
			}
			Connection conn = null;
			InputStream in=null;
			try {
				if(ocsId!=null){
					conn = this.getConnection(Globals.DATASOURCEJNDI);
					ConnLog.open("DownLoadWebPageServlet");
					CommsiteHandler handler = new CommsiteHandler(conn);

					in =handler.getStream(ocsId);
				}
				if(in==null){
					String path=this.getServletContext().getRealPath("/images/defaultweblogo.gif");
					in=new FileInputStream(new File(path));
				}
				DownloadResponse dr = new DownloadResponse(response);
				dr.downloadInputStream(in,"temp.gif");

			} catch (Exception e) {
				e.printStackTrace();
				handleError(e);
			} finally {
				try {
					if (conn != null){
						conn.close();
						ConnLog.close("DownLoadWebPageServlet");
					}
				} catch (SQLException e2) {
					e2.printStackTrace();
				}
				if(in!=null){
					in.close();
				}
			}


	}

}
