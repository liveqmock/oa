/*
 * Created on 2004-12-31
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.icss.oa.netoffice.onduty.util;

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
import com.icss.oa.netoffice.onduty.handler.DutyHandler;
import com.icss.resourceone.common.logininfo.UserInfo;
import com.icss.resourceone.sdk.framework.Context;
import com.icss.resourceone.sdk.framework.EntityException;

/**
 * @author firecoral
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class InitJobMaintanServlet extends ServletBase{

	/* (non-Javadoc)
	 * @see com.icss.j2ee.servlet.ServletBase#performTask(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	protected void performTask(HttpServletRequest request, HttpServletResponse response) 
		throws ServletException, IOException {
		
		String orgUUid = "";
		String personUUid = "";
		Context ctx = null;
		Connection conn = null;
		try {
			conn = this.getConnection(Globals.DATASOURCEJNDI);
			ConnLog.open("InitJobMaintanServlet");
			ctx = Context.getInstance();
			UserInfo user = ctx.getCurrentLoginInfo();
			personUUid = user.getPersonUuid();
			orgUUid = user.getPrimaryOrguuid();  //登录人所属在编组织,如果没有在编组织时会在查看本部门下排班表时出错
		
			//判断是否为可查看本组织和其子组织的值班表
			boolean pd = false;
			DutyHandler dHandler = new DutyHandler(conn);
	    	String juOrguuid = dHandler.getJuOrguuid(orgUUid);
			this.forward(request,response,"/servlet/OrgTree3Servlet?nodeUrl=ListAuthorServlet&orguuid="+juOrguuid);
			
		} catch (EntityException e) {
			e.printStackTrace();
		} catch (ServiceLocatorException e) {
			e.printStackTrace();
		} finally{
			try {
				if(conn!=null){
					conn.close();
					ConnLog.close("InitJobMaintanServlet");
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		
	}

}
