/*
 * Created on 2004-8-4
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.icss.oa.myphonebook.admin;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.common.log.ConnLog;
import com.icss.j2ee.servlet.ServletBase;
import com.icss.j2ee.util.Globals;
import com.icss.oa.phonebook.handler.PhoneHandler;
import com.icss.resourceone.common.logininfo.UserInfo;
import com.icss.resourceone.sdk.framework.Context;

import com.icss.oa.phonebook.vo.PhoneInfoSysPersonVO;
import com.icss.oa.phonebook.vo.PhoneSysNameVO;

/**
 * @author firecoral
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class MyGetUPhoneServlet extends ServletBase{  

	/* (non-Javadoc)
	 * @see com.icss.j2ee.servlet.ServletBase#performTask(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	protected void performTask(HttpServletRequest request, HttpServletResponse response) 
	    throws ServletException, IOException {
	    	
			Connection conn = null;
			List phoneList = new ArrayList();
			//List sqlList = new ArrayList();
			String orgUUid = request.getParameter("orgid");
			try {
				Context ctx = null;
				ctx = Context.getInstance();
				conn = this.getConnection(Globals.DATASOURCEJNDI);
				ConnLog.open("GetUPhoneServlet");
				PhoneHandler pHandler = new PhoneHandler(conn);
				if(orgUUid!=null){
					phoneList = pHandler.GetOrgPhone(orgUUid);
				}
				if(request.getParameter("orgid")!=null)
				  request.setAttribute("phoneList",phoneList);
				request.setAttribute("orgUUid",request.getParameter("orgid"));
				request.setAttribute("orgName",pHandler.getFullOrgName(request.getParameter("orgid")));
				this.forward(request,response,"/phonebook/showUserPhone.jsp");
		
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				if (conn != null)
					try {
						conn.close();
						ConnLog.close("MyGetUPhoneServlet");
					} catch (Exception e2) {
						e2.printStackTrace();
					}
			}
	}
}








