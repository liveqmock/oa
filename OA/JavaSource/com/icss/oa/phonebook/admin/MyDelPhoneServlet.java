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

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.common.log.ConnLog;
import com.icss.j2ee.servlet.ServletBase;
import com.icss.j2ee.util.Globals;
import com.icss.oa.phonebook.handler.PhoneHandler;
import com.icss.oa.phonebook.vo.PhoneInfoVO;

/**
 * @author firecoral
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class MyDelPhoneServlet extends ServletBase{
	/* (non-Javadoc)
	 * @see com.icss.j2ee.servlet.ServletBase#performTask(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	protected void performTask(HttpServletRequest request, HttpServletResponse response) 
	    throws ServletException, IOException {
	    	
			Connection conn = null;
			String aonteid="";
			String[] noteids = request.getParameterValues("mm");
            String orguuid=request.getParameter("orguuid");
            if(request.getParameter("anoteid")!=null)
             aonteid=request.getParameter("anoteid");
            PrintWriter out=response.getWriter();
            out.println(aonteid);
			try {
				conn = this.getConnection(Globals.DATASOURCEJNDI);
				ConnLog.open("DelPhoneServlet");
				
				PhoneHandler pHandler = new PhoneHandler(conn);
				PhoneInfoVO pvo;
				if(!aonteid.equals(""))
				{
				      pvo = pHandler.GetOnePhone(new Integer(aonteid));
				      pHandler.DelPhone(pvo);
				}else{
				       for (int i =0; i < noteids.length; i++) 
				       {
					      pvo = pHandler.GetOnePhone(new Integer(noteids[i]));
					      pHandler.DelPhone(pvo);
				       }
				}
				response.sendRedirect("/oabase/servlet/ManageSearchPhoneServlet?orguuid="+orguuid+"&fromdelpage=true"+"&addtipstr="+"deleted");
			} catch (Exception e) {
				response.sendRedirect("/oabase/servlet/ManageSearchPhoneServlet?orguuid="+orguuid+"&fromdelpage=true"+"&addtipstr="+"notdeleted");
				e.printStackTrace();
			}finally {
				if (conn != null)
					try {
						conn.close();
						ConnLog.close("DelPhoneServlet");
					} catch (Exception e2) {
						e2.printStackTrace();
					}
			} 
	}
}
