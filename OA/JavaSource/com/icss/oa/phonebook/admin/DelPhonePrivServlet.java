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
public class DelPhonePrivServlet extends ServletBase{
	/* (non-Javadoc)
	 * @see com.icss.j2ee.servlet.ServletBase#performTask(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	protected void performTask(HttpServletRequest request, HttpServletResponse response) 
	    throws ServletException, IOException {
	    	
			Connection conn = null;
			String appidstr = request.getParameter("appids");
            String[] appids=appidstr.split(",");

			try {
				conn = this.getConnection(Globals.DATASOURCEJNDI);
				ConnLog.open("DelPhonePrivServlet");
				
				PhoneHandler pHandler = new PhoneHandler(conn);
				PhonePrivVO pvo;
				if(appids.length>0)
				{
				       for (int i =0; i < appids.length; i++) 
				       {
					      pvo = pHandler.GetOnePhonePriv(new Integer(appids[i]));
					      pHandler.DelPhonePriv(pvo);
					      // 删除与此记录关联的所有特殊权限人员记录(phone_privperson表)
					      if(pvo.getPp_type().equals("1"))
					      {
					    	  List PhonePrivPersonList=new ArrayList();
					    	  PhonePrivPersonList=pHandler.GetPhonePrivPersonByPpid(pvo.getPp_id().toString());
					    	  Iterator it=PhonePrivPersonList.iterator();
					    	  while(it.hasNext())
					    	  {
					    		  pHandler.DelPhonePrivPerson((PhonePrivPersonVO)it.next());
					    	  }
					      }
				       }
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
