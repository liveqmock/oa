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
import com.icss.oa.util.FtpPropertyManager;
import com.icss.orgtree.handler.OrgHandler;
import com.icss.orgtree.vo.SysOrgVO;

/**
 * Servlet implementation class for Servlet: ModifyFtpDirServlet
 *
 */
 public class ModifyFtpDirServlet extends com.icss.j2ee.servlet.ServletBase implements javax.servlet.Servlet {
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
		 String id = request.getParameter("col");
		 
		 System.out.println("ftpadmin="+name+admin+org+id);
		 /*结束*/

		String ftpip = FtpPropertyManager.getProperty("fftpip");
		String ftpuser = FtpPropertyManager.getProperty("fftpuser");
		String ftppass = FtpPropertyManager.getProperty("fftppass");
		
		 try {
			FTPClient ftpClient=new FTPClient();
			ftpClient.connect(ftpip, 21);
			ftpClient.login(ftpuser,ftppass);
			ftpClient.changeWorkingDirectory(".");
			conn = getConnection(com.icss.j2ee.util.Globals.DATASOURCEJNDI);
			OrgHandler handler = new OrgHandler(conn);
			SysOrgVO topOrgVO = handler.getOrg(org);
			FtpAdminDAO fadao = new FtpAdminDAO(conn);
			DAOFactory factory = new DAOFactory(conn);
			factory.setDAO(fadao);
			fadao.setFolder_name(name);
			fadao.setFolder_path(ftpClient.printWorkingDirectory()+"/"+name);
			fadao.setOwener(admin);
			fadao.setOrg(topOrgVO.getOrguuid());
			fadao.setId(new Integer(id));
			fadao.setWhereClause("ID="+id);
			List l = factory.find(new FtpAdminVO());
			String oldname = ((FtpAdminVO)l.get(0)).getFolder_name();
			fadao.update();
			ftpClient.removeDirectory(new String(oldname.toString().getBytes("GBK"),"ISO-8859-1"));
			ftpClient.makeDirectory(new String(name.toString().getBytes("GBK"),"ISO-8859-1"));
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