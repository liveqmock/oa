package com.icss.oa.ftp;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.j2ee.dao.DAOException;
import com.icss.j2ee.dao.DAOFactory;
import com.icss.j2ee.services.ServiceLocatorException;
import com.icss.j2ee.servlet.ServletBase;

/**
 * Servlet implementation class for Servlet: FolderTreeServlet
 *
 */
 public class FolderTreeServlet extends ServletBase {
    /* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	
	 
	 protected void performTask(
					HttpServletRequest request,
					HttpServletResponse response)
					throws ServletException, IOException {
		 
		Connection conn = null;
		try {
			String fid = request.getParameter("fid");
			conn = getConnection(com.icss.j2ee.util.Globals.DATASOURCEJNDI);
			DAOFactory factory = new DAOFactory(conn);
			FtpAdminDAO fadao = new FtpAdminDAO(conn);
			fadao.setId(new Integer(fid));
			factory.setDAO(fadao);
			FtpAdminVO favo = (FtpAdminVO)factory.findByPrimaryKey(new FtpAdminVO());
			FtpFolderDAO ffdao = new FtpFolderDAO(conn);
			factory.setDAO(ffdao);
			List folders = factory.findAll(new FtpFolderVO());
			System.out.println("list:"+folders.size());
			request.setAttribute("curfolder", favo);
			request.setAttribute("folders", folders);
			this.forward(request, response, "/ftp/selectfolder.jsp");
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