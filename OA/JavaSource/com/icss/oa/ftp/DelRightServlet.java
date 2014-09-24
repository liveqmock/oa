package com.icss.oa.ftp;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.j2ee.dao.DAOException;
import com.icss.j2ee.dao.DAOFactory;
import com.icss.j2ee.services.ServiceLocatorException;

/**
 * Servlet implementation class for Servlet: DelRightServlet
 *
 */
 public class DelRightServlet extends com.icss.j2ee.servlet.ServletBase implements javax.servlet.Servlet {
	 
	 
	 protected void performTask(
					HttpServletRequest request,
					HttpServletResponse response)
					throws ServletException, IOException {
		 
		Connection conn = null;
		String uuids = request.getParameter("choose");
		StringTokenizer stk = new StringTokenizer(uuids,",");
		String org=(String)request.getParameter("org");

		 try {
			 conn = getConnection(com.icss.j2ee.util.Globals.DATASOURCEJNDI);
			 while(stk.hasMoreElements()){
				 FtpUserRightDAO furdao = new FtpUserRightDAO(conn);
				 DAOFactory factory = new DAOFactory(conn);
				 factory.setDAO(furdao);
				 furdao.setWhereClause("UNAME='"+ (String)stk.nextElement() +"'");
				 factory.batchDelete();
			 }
			 request.setAttribute("org", org);
			 this.forward(request, response, "/servlet/ShowUserRightServlet");
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