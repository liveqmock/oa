package com.icss.oa.ftp;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.net.ftp.FTPClient;

import com.icss.j2ee.dao.DAOException;
import com.icss.j2ee.services.ServiceLocatorException;
import com.icss.j2ee.servlet.ServletBase;
import com.icss.oa.util.FtpPropertyManager;

/**
 * Servlet implementation class for Servlet: NewFtpDirServlet
 *
 */
 public class NewFtpDirServlet extends ServletBase  {
    /* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	
	 
	 protected void performTask(
					HttpServletRequest request,
					HttpServletResponse response)
					throws ServletException, IOException {
		 Connection conn = null;
		 
		 /*获取参数*/
		 String name = request.getParameter("name");
		 String admin = request.getParameter("uuid");
		 String org = request.getParameter("org");
		 /*结束*/

		String ftpip = FtpPropertyManager.getProperty("fftpip");
		String ftpuser = FtpPropertyManager.getProperty("fftpuser");
		String ftppass = FtpPropertyManager.getProperty("fftppass");
		
		 try {
			FTPClient ftpClient=new FTPClient();
			ftpClient.connect(ftpip, 21);
			ftpClient.login(ftpuser,ftppass);
			ftpClient.changeWorkingDirectory(".");
			ftpClient.makeDirectory(new String(name.toString().getBytes("GBK"),"ISO-8859-1"));
			conn = getConnection(com.icss.j2ee.util.Globals.DATASOURCEJNDI);
			FtpAdminDAO fadao = new FtpAdminDAO(conn);
			fadao.setFolder_name(name);
			fadao.setFolder_path(ftpClient.printWorkingDirectory()+"/"+name);
			fadao.setOwener(admin);
			fadao.setOrg(org);
			fadao.create();
			ftpClient.disconnect();
			this.forward(request, response, "/servlet/QuaryFtpDirServlet");
		} catch (ServiceLocatorException e) {
			e.printStackTrace();
		} catch (DAOException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				if (conn != null) {
					try {
						conn.close();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
			} catch (Exception e) {
				handleError(e);
			}
        }

	}   	  	   	    
}