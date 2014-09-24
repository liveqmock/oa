package com.icss.oa.ftp;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

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
 * Servlet implementation class for Servlet: NewUserFolderServlet
 *
 */
 public class NewUserFolderServlet extends ServletBase {
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
		String pid = request.getParameter("pid");
		String cur = request.getParameter("cur");
		 /*结束*/

		String ftpip = FtpPropertyManager.getProperty("fftpip");
		String ftpuser = FtpPropertyManager.getProperty("fftpuser");
		String ftppass = FtpPropertyManager.getProperty("fftppass");
		String fid = "";
		
		 try {
			conn = getConnection(com.icss.j2ee.util.Globals.DATASOURCEJNDI);
			FtpFolderDAO ffdao = new FtpFolderDAO(conn);
			FtpAdminDAO fadao = new FtpAdminDAO(conn);
			DAOFactory factory = new DAOFactory(conn);
			
			FTPClient ftpClient=new FTPClient();
			ftpClient.connect(ftpip, 21);
			ftpClient.login(ftpuser,ftppass);
			ftpClient.changeWorkingDirectory(new String(cur.toString().getBytes("GBK"),"ISO-8859-1"));
			ftpClient.makeDirectory(new String(name.toString().getBytes("GBK"),"ISO-8859-1"));
			
			fadao.setWhereClause("FOLDER_PATH = '" + cur.substring(0,cur.lastIndexOf("/")) +"'");
			factory.setDAO(fadao);
			List l = factory.find(new FtpAdminVO());
			if(l.size()>0){
				fid = "0";
			}else{
				String temp = cur.substring(0,cur.lastIndexOf("/"));
				String parentfolder = temp.substring(0,temp.lastIndexOf("/")+1);
				String curfolder = temp.substring(temp.lastIndexOf("/")+1,temp.length());
				System.out.println("parentfolder:"+parentfolder);
				FtpFolderDAO tempdao = new FtpFolderDAO(conn);
				tempdao.setWhereClause("NAME ='"+curfolder+"' AND FATHER='"+ parentfolder +"'");
				factory.setDAO(tempdao);
				List tl = factory.find(new FtpFolderVO());
				if(tl.size()>0){
					fid = String.valueOf(((FtpFolderVO)tl.get(0)).getId());
				}
			}
			
			ffdao.setName(name);
			ffdao.setPid(pid);
			ffdao.setRight("0");
			ffdao.setFather(cur);
			ffdao.setFid(fid);
			ffdao.create();
			ftpClient.disconnect();
			request.setAttribute("curpath",cur);
			this.forward(request, response, "/servlet/DepAdminServlet");
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