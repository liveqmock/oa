/*
 * Created on 2004-8-9
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.icss.oa.phonebook.user;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.common.log.ConnLog;
import com.icss.j2ee.servlet.ServletBase;
import com.icss.j2ee.util.Globals;
import com.icss.oa.phonebook.handler.PhoneHandler;

/**
 * @author firecoral
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class DeleteCustomSqlServlet extends ServletBase{

	/* (non-Javadoc)
	 * @see com.icss.j2ee.servlet.ServletBase#performTask(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	protected void performTask(HttpServletRequest request, HttpServletResponse response) 
	    throws ServletException, IOException {
	    	
	      String sqlid = request.getParameter("sqlid");
	      
	      Connection conn = null;
		  try {
			  conn = this.getConnection(Globals.DATASOURCEJNDI);
			  ConnLog.open("DeleteCustomSqlServlet");
			  PhoneHandler pHandler = new PhoneHandler(conn);
			  
			  pHandler.DelCustomSql(sqlid);
			  
			  this.forward(request,response,"/servlet/GetUPhoneServlet");
			  
		  } catch (Exception e) {
			  e.printStackTrace();
		  }finally {
			  if (conn != null)
				  try {
					  conn.close();
					  ConnLog.close("DeleteCustomSqlServlet");
				  } catch (Exception e2) {
					  e2.printStackTrace();
				  }
		  }
		
	}

}
