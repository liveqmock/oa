package com.icss.oa.ftp;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.util.StringTokenizer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.net.ftp.FTPClient;

import com.icss.j2ee.servlet.ServletBase;
import com.icss.oa.util.FtpPropertyManager;

/**
 * Servlet implementation class for Servlet: FtpTransferServlet
 *
 */
 public class FtpTransferServlet extends ServletBase {
    /* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	
	 
	 protected void performTask(
					HttpServletRequest request,
					HttpServletResponse response)
					throws ServletException, IOException { 
		 
		 String act = request.getParameter("act");
		 String files = request.getParameter("files");
		 String path = request.getParameter("folderId");
		 String cur = request.getParameter("cur");
		 StringTokenizer stz = new StringTokenizer(files,",");
		 
		 String ftpip = FtpPropertyManager.getProperty("fftpip");
		 String ftpuser = FtpPropertyManager.getProperty("fftpuser");
		 String ftppass = FtpPropertyManager.getProperty("fftppass");
		 
		 FTPClient ftpClient=new FTPClient();
		 ftpClient.connect(ftpip, 21);
		 ftpClient.login(ftpuser,ftppass);
		 String tpath = cur.substring(0,cur.lastIndexOf("/"));
		 ftpClient.changeWorkingDirectory(new String(tpath.getBytes("GBK"),"ISO-8859-1"));
		 
		 if("move".equals(act)){
			 
			 while(stz.hasMoreElements()){
				 
				 String filename = (String)stz.nextElement();
				 String newpath = path + "/" + filename;
				 ftpClient.rename(new String(filename.getBytes("GBK"),"ISO-8859-1"), new String(newpath.getBytes("GBK"),"ISO-8859-1"));
			 }
			
		 }else if("save".equals(act)){
//			 while(stz.hasMoreElements()){
//				 
//				 String filename = (String)stz.nextElement();
//				 BufferedOutputStream buffOut=new BufferedOutputStream(new FileOutputStream("C:\\temp\\"+filename));
//		         ftpClient.retrieveFile(filename, buffOut);
//
//			 }   
			 
		 }
		 ftpClient.disconnect();
	 	 this.forward(request, response, "/servlet/DepAdminServlet");
	 }
}