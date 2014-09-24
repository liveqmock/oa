package com.icss.oa.ftp;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.net.ftp.FTPClient;

import com.icss.j2ee.dao.DAOException;
import com.icss.j2ee.dao.DAOFactory;
import com.icss.j2ee.services.ServiceLocatorException;
import com.icss.j2ee.servlet.ServletBase;
import com.icss.oa.util.FtpPropertyManager;

/**
 * Servlet implementation class for Servlet: DelFtpDirServlet
 *
 */
 public class DelFtpDirServlet extends ServletBase {
	 
	 
	 protected void performTask(
					HttpServletRequest request,
					HttpServletResponse response)
					throws ServletException, IOException {
		 
		 Connection conn = null;
		 /*获取参数*/
		 String id = request.getParameter("col");
		 /*结束*/


		String ftpip = FtpPropertyManager.getProperty("fftpip");
		String ftpuser = FtpPropertyManager.getProperty("fftpuser");
		String ftppass = FtpPropertyManager.getProperty("fftppass");
	
		try {
			conn = getConnection(com.icss.j2ee.util.Globals.DATASOURCEJNDI);
			FTPClient ftpClient=new FTPClient();
			ftpClient.connect(ftpip, 21);
			ftpClient.login(ftpuser,ftppass);
			ftpClient.changeWorkingDirectory(".");
			FtpAdminDAO fadao = new FtpAdminDAO(conn);	
			DAOFactory factory = new DAOFactory(conn);
			fadao.setId(new Integer(id));
			fadao.setWhereClause("ID = " + id);
			factory.setDAO(fadao);
			FtpAdminVO fvo = (FtpAdminVO)factory.findByPrimaryKey(new FtpAdminVO());
			fadao.delete();
			ftpClient.removeDirectory(new String(fvo.getFolder_name().getBytes("GBK"),"ISO-8859-1"));
			ftpClient.disconnect();
			this.forward(request, response, "/servlet/QuaryFtpDirServlet");			
		} catch (ServiceLocatorException e) {
			e.printStackTrace();
		} catch (DAOException e) {
			e.printStackTrace();
		} finally{
			try {
				if(conn!=null && !conn.isClosed()){
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		
	 }
}