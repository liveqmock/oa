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
 * Servlet implementation class for Servlet: CreateFileServlet
 *
 */
 public class CreateFileServlet extends ServletBase {
	 
	 protected void performTask(
				HttpServletRequest request,
				HttpServletResponse response)
				throws ServletException, IOException {
		
		 String name = request.getParameter("name");
		 String folder = request.getParameter("folder");
		 String depuuid = request.getParameter("depuuid");
		 String createruuid = request.getParameter("createruuid");
		 String createtime = request.getParameter("createtime");
		 
		 name = Base64.replace(name, "-", "/");
		 name =Base64.replace(name, "|", "+");
		 folder = Base64.replace(folder, "-", "/");
		 folder = Base64.replace(folder, "|", "+");
		 	 
		 name = new String(Base64.decode(name));
		 folder = new String(Base64.decode(folder));
		
		 Connection conn = null;
		 
		 try {
			 
			conn = getConnection(com.icss.j2ee.util.Globals.DATASOURCEJNDI);
			DAOFactory factory = new DAOFactory(conn);
			FtpFileDAO ffdao = new FtpFileDAO(conn);
			ffdao.setWhereClause("FILENAME ='"+ name +"' AND FOLDER='"+ folder +"'");
			factory.setDAO(ffdao);
			List l = factory.find();
			if(l !=null && l.size()==0){
				ffdao.setFilename(name);
				ffdao.setFolder(folder);
				ffdao.setDepuuid("0");
				ffdao.setCreateruuid("0");
				ffdao.setCreatetime(createtime);
				ffdao.setDownloadpath(folder);
				ffdao.create();
			}
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