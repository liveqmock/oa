/*
 * Created on 2004-8-6
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.icss.oa.phonebook.admin;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.common.log.ConnLog;
import com.icss.j2ee.servlet.ServletBase;
import com.icss.j2ee.util.Globals;
import com.icss.oa.phonebook.handler.PhoneHandler;
import com.icss.oa.phonebook.vo.PhoneInfoVO;

import com.icss.oa.phonebook.dao.PhonePrivDAO;
import com.icss.oa.phonebook.dao.PhonePrivPersonDAO;
import com.icss.oa.phonebook.vo.PhonePrivVO;
import com.icss.oa.phonebook.vo.PhonePrivPersonVO;

/**
 * @author firecoral
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class DelPhonePrivPersonServlet extends ServletBase{
	/* (non-Javadoc)
	 * @see com.icss.j2ee.servlet.ServletBase#performTask(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	protected void performTask(HttpServletRequest request, HttpServletResponse response) 
	    throws ServletException, IOException {
			Connection conn = null;
			String ppid = request.getParameter("ppid");
			String personuuid = request.getParameter("personuuid");
			try {
				  conn = this.getConnection(Globals.DATASOURCEJNDI);
				  ConnLog.open("DelPhonePrivServlet");
				  PhoneHandler pHandler = new PhoneHandler(conn);
				  
		    	  List PhonePrivPersonList=new ArrayList();
		    	  
		    	  PhonePrivPersonList=pHandler.GetPhonePrivPersonByPpid(ppid,personuuid);
		    	  Iterator it=PhonePrivPersonList.iterator();
		    	  while(it.hasNext())
		    	  {
		    		  pHandler.DelPhonePrivPerson((PhonePrivPersonVO)it.next());
		    	  }
	              response.sendRedirect("/oabase/servlet/PhonebookprivServlet?tips=deleted");
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				if (conn != null)
					try {
						conn.close();
						ConnLog.close("DelPhonePrivServlet");
					} catch (Exception e2) {
						e2.printStackTrace();
					}
			} 
	}
}



