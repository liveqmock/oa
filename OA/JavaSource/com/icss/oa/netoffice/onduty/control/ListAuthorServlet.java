/*
 * Created on 2004-12-31
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.icss.oa.netoffice.onduty.control;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
public class ListAuthorServlet extends ServletBase{

	/* (non-Javadoc)
	 * @see com.icss.j2ee.servlet.ServletBase#performTask(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	protected void performTask(HttpServletRequest request, HttpServletResponse response) 
		throws ServletException, IOException {
		
		Connection conn = null;
		String orgUUid = request.getParameter("orgid");
		List authorPersonList = new ArrayList();
		Context ctx = null;
		
		try {
			conn = this.getConnection(Globals.DATASOURCEJNDI);
			ConnLog.open("ListAuthorServlet");
			DutyHandler dhandler = new DutyHandler(conn);
			if(orgUUid==null){
				ctx = Context.getInstance();
				UserInfo user = ctx.getCurrentLoginInfo();
				orgUUid = dhandler.getJuOrguuid(user.getPrimaryOrguuid());
			}
//			List allOrgPerson = dhandler.GetAllUser(orgUUid);
//			if(allOrgPerson!=null&&allOrgPerson.size()>0){
//				Iterator it = allOrgPerson.iterator();
//				String personuuid = "";
//				while(it.hasNext()){
//					SysOrgpersonSysPersonVO vo = (SysOrgpersonSysPersonVO)it.next();
//					personuuid  = vo.getPersonuuid();
//					if(dhandler.hasRole(personuuid,OndutyConfig.AUTHORDUTY_ROLE)){
//						authorPersonList.add(vo);
//					}
//				}
//			}
			authorPersonList = dhandler.GetAllDutyUser(orgUUid);
			
			String orgName = dhandler.GetOrgName(orgUUid);
			request.setAttribute("orgName",orgName);
			request.setAttribute("orgUUid",orgUUid);
			request.setAttribute("authorPersonList",authorPersonList);
			this.forward(request,response,"/netoffice/onduty/showAuthorPerson.jsp");
		} catch (ServiceLocatorException e) {
			e.printStackTrace();
		} catch (EntityException e) {
			e.printStackTrace();
		} finally{
			try {
				if(conn!=null){
					conn.close();
					ConnLog.close("ListAuthorServlet");
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		
	}

}
