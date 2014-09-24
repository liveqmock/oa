/*
 * Created on 2004-8-4
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.icss.oa.phonebook.user;

import java.io.IOException;
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

/**
 * @author firecoral
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class GetUPhoneServlet extends ServletBase{  

	/* (non-Javadoc)
	 * @see com.icss.j2ee.servlet.ServletBase#performTask(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	protected void performTask(HttpServletRequest request, HttpServletResponse response) 
	    throws ServletException, IOException {
	    	
			Connection conn = null;
			List phoneList = new ArrayList();
			List sqlList = new ArrayList();
			String orgUUid = request.getParameter("orgid");
			String orgName = "";
			String sqlOrguuid = "";
			
			try {
				Context ctx = null;
				ctx = Context.getInstance();
				UserInfo user = ctx.getCurrentLoginInfo();
				
				conn = this.getConnection(Globals.DATASOURCEJNDI);
				ConnLog.open("GetUPhoneServlet");
				PhoneHandler pHandler = new PhoneHandler(conn);
				if(orgUUid==null){  //the firsttime in
					phoneList = pHandler.GetOrgPhone(orgUUid);
					orgName = "所有组织";  //登录人所查看的组织
				}else{
					phoneList = pHandler.GetOrgPhone(orgUUid);
					//组织名
					orgName = pHandler.GetOrgName(orgUUid);  //登录人所查看的组织
				} 
				//得到当前用户常用的SQL
				sqlOrguuid = user.getPrimaryOrguuid();
				sqlList = pHandler.GetSqlList(user.getUserID(),sqlOrguuid);
				
				request.setAttribute("phoneList",phoneList);
				request.setAttribute("sqlList",sqlList);
				request.setAttribute("orgUUid",request.getParameter("orgid"));
				request.setAttribute("orgName",orgName);
				
				//得到组织的联系地址
				String contact = pHandler.GetOrgContact(orgUUid);
				request.setAttribute("contact",contact);

				this.forward(request,response,"/phonebook/userPhone.jsp");
				
			}catch (Exception e) {
				e.printStackTrace();
			}finally {
				if (conn != null)
					try {
						conn.close();
						ConnLog.close("GetUPhoneServlet");
					} catch (Exception e2) {
						e2.printStackTrace();
					}
			}
	}
}
