package com.icss.oa.ftp;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.StringTokenizer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.j2ee.dao.DAOException;
import com.icss.j2ee.dao.DAOFactory;
import com.icss.j2ee.services.ServiceLocatorException;

/**
 * Servlet implementation class for Servlet: MidifyRightServlet
 *
 */
 public class MidifyRightServlet extends com.icss.j2ee.servlet.ServletBase implements javax.servlet.Servlet {
	 
	 
	 protected void performTask(
					HttpServletRequest request,
					HttpServletResponse response)
					throws ServletException, IOException {
		 Connection conn = null;
		String uuids = request.getParameter("choose");
		String rightval = request.getParameter("rightval");
		StringTokenizer uuidstk = new StringTokenizer(uuids,",");
		StringTokenizer rightstk = new StringTokenizer(rightval,",");
		
		 try {
			 conn = getConnection(com.icss.j2ee.util.Globals.DATASOURCEJNDI);
			 while(uuidstk.hasMoreElements() && rightstk.hasMoreElements()){
				 FtpUserRightDAO furdao = new FtpUserRightDAO(conn);
				 furdao.setRight((String)rightstk.nextElement());
				 furdao.setWhereClause("UNAME='"+ (String)uuidstk.nextElement() +"'");
				 furdao.update(false);
			 }
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