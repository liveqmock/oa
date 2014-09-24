/*
 * Created on 2004-8-5
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.icss.oa.phonebook.admin;

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
import com.icss.oa.util.RoleControl;
import com.icss.resourceone.common.logininfo.UserInfo;
import com.icss.resourceone.sdk.framework.Context;
import com.icss.resourceone.sdk.framework.EntityException;

/**
 * @author firecoral
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class InitPhoneLeftServlet extends ServletBase{

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
				ConnLog.open("InitPhoneLeftServlet");
				ctx = Context.getInstance();
				UserInfo user = ctx.getCurrentLoginInfo();
				orgUUid = user.getPrimaryOrguuid();  //登录人所属组织
				if(RoleControl.hasRole(user.getPersonUuid(),"oa.phonesuperman")){
					this.forward(request,response,"/servlet/OrgTreeServlet?nodeUrl=GetMPhoneServlet");
			    }else{
			    	PhoneHandler phandler = new PhoneHandler(conn);
			    	String juOrguuid = phandler.getJuOrguuid(orgUUid);
//					05/05/26lijy改
//					boolean pd = false;
//					pd = phandler.hasRole(user.getPersonUuid(),"oa.shephonel");
//					if(pd){
//						juOrguuid = "4161cca6-fc3554e80d-97fe05e58eef24f3370b74f3cc7c23fc";
//					}
			     	this.forward(request,response,"/servlet/OrgTree3Servlet?nodeUrl=GetMPhoneServlet&orguuid="+juOrguuid);
			    }
				
			} catch (EntityException e) {
				e.printStackTrace();
			} catch (ServiceLocatorException e) {
				e.printStackTrace();
			} finally{
				if(conn!=null){
					try {
						conn.close();
						ConnLog.close("InitPhoneLeftServlet");
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
			}
	}

	
	

}
