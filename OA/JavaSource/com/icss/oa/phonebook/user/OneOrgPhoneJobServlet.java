/*
 * Created on 2004-12-24
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.icss.oa.phonebook.user;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.common.log.ConnLog;
import com.icss.j2ee.services.ServiceLocatorException;
import com.icss.j2ee.servlet.ServletBase;
import com.icss.j2ee.util.Globals;
import com.icss.oa.phonebook.handler.PhoneHandler;
import com.icss.resourceone.common.logininfo.UserInfo;
import com.icss.resourceone.sdk.framework.Context;
import com.icss.resourceone.sdk.framework.EntityException;

/**
 * @author firecoral
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class OneOrgPhoneJobServlet extends ServletBase{

	/* (non-Javadoc)
	 * @see com.icss.j2ee.servlet.ServletBase#performTask(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	protected void performTask(HttpServletRequest request, HttpServletResponse response) 
		throws ServletException, IOException {
		
		Connection conn = null;
		
		String errorMsg = (String)request.getAttribute("errorMsg");
		
		
		
		
		try {
			conn = this.getConnection(Globals.DATASOURCEJNDI);
			ConnLog.open("OneOrgPhoneJobServlet");
			PhoneHandler phandler = new PhoneHandler(conn);
			
			String orgUUid = request.getParameter("orgid");			
			Context ctx = Context.getInstance();
			UserInfo user = ctx.getCurrentLoginInfo();
			if(orgUUid==null||"".equals(orgUUid)){
				String userOrguuid = user.getPrimaryOrguuid();
				orgUUid = phandler.getJuOrguuid(userOrguuid);
			}
			String orgName = phandler.GetOrgName(orgUUid);
			String personUuid = user.getPersonUuid();
			//判断是否为社级领导的电话和职务管理角色
			boolean pd = false;
			pd = phandler.hasRole(personUuid,"oa.shephonel");
			if(pd){
				orgUUid = "4161cca6-fc3554e80d-97fe05e58eef24f3370b74f3cc7c23fc";
				orgName = "社领导";
			}
			List joblist = phandler.getOrgJob(orgUUid);
			request.setAttribute("jobList",joblist);
			request.setAttribute("orgname",orgName);
			request.setAttribute("orguuid",orgUUid);
			request.setAttribute("errorMsg",errorMsg);
			
			this.forward(request,response,"/phonebook/orgjoblist.jsp");
		} catch (ServiceLocatorException e) {		
			e.printStackTrace();
		} catch (EntityException e) {
			e.printStackTrace();
		} finally{
			try {
				if(conn!=null){
					conn.close();
					ConnLog.close("OneOrgPhoneJobServlet");
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}

}
