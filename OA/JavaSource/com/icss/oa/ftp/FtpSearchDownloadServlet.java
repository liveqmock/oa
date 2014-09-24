package com.icss.oa.ftp;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.net.ftp.FTPClient;

import com.icss.j2ee.servlet.ServletBase;
import com.icss.oa.util.FtpPropertyManager;

/**
 * Servlet implementation class for Servlet: FtpSearchDownloadServlet
 * 
 */
public class FtpSearchDownloadServlet extends ServletBase {
	/*
	 * (non-Java-doc)
	 * 
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	protected void performTask(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String path = request.getParameter("path");
		String ftpip = FtpPropertyManager.getProperty("fftpip");
		String ftpuser = FtpPropertyManager.getProperty("fftpuser");
		String ftppass = FtpPropertyManager.getProperty("fftppass");
		String dir = path.substring(0, path.lastIndexOf("/"));
		String name = path.substring(path.lastIndexOf("/") + 1, path.length());
		response.setContentType("APPLICATION/OCTET-STREAM");
		response.setHeader("Content-Disposition", "attachment; filename=\""
				+ new String(name.getBytes("GBK"), "ISO-8859-1") + "\"");
		OutputStream os = null;
		FTPClient ftpClient = null;
		try {
			os = response.getOutputStream();
			ftpClient = new FTPClient();
			ftpClient.connect(ftpip, 21);
			ftpClient.login(ftpuser, ftppass);
			ftpClient.changeWorkingDirectory(new String(dir.getBytes("GBK"),
					"ISO-8859-1"));
			ftpClient.setFileType(ftpClient.BINARY_FILE_TYPE);
			ftpClient.retrieveFile(new String(name.getBytes("GBK"),
					"ISO-8859-1"), os);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (os != null)
					os.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				if (ftpClient != null)
					ftpClient.disconnect();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}