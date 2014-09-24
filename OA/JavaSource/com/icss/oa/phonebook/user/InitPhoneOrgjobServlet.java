/*
 * Created on 2004-12-30
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.icss.oa.phonebook.user;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

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
public class InitPhoneOrgjobServlet extends ServletBase{

	/* (non-Javadoc)
	 * @see com.icss.j2ee.servlet.ServletBase#performTask(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	protected void performTask(HttpServletRequest request, HttpServletResponse response) 
	    throws ServletException, IOException {

			String orgUUid = "";
			Context ctx = null;
			Connection conn = null;
			
			try {
				conn = this.getConnection(Globals.DATASOURCEJNDI);
				ConnLog.open("InitPhoneOrgjobServlet");
				ctx = Context.getInstance();
				UserInfo user = ctx.getCurrentLoginInfo();
				orgUUid = user.getPrimaryOrguuid();  //登录人所属组织
		    	PhoneHandler phandler = new PhoneHandler(conn);
		    	String juOrguuid = phandler.getJuOrguuid(orgUUid);
				boolean pd = false;
				pd = phandler.hasRole(user.getPersonUuid(),"oa.shephone");
				if(pd){
					juOrguuid = "4161cca6-fc3554e80d-97fe05e58eef24f3370b74f3cc7c23fc";
				}
		     	this.forward(request,response,"/servlet/OrgTree3Servlet?nodeUrl=OneOrgPhoneJobServlet&orguuid="+juOrguuid);
				
			} catch (EntityException e) {
				e.printStackTrace();
			} catch (ServiceLocatorException e) {
				e.printStackTrace();
			} finally{
				try {
					if(conn!=null){
						conn.close();
						ConnLog.close("InitPhoneOrgjobServlet");
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
	}
}
