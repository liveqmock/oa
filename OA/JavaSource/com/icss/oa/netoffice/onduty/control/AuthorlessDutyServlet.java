/*
 * Created on 2005-1-4
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.icss.oa.netoffice.onduty.control;

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
import com.icss.oa.config.OndutyConfig;
import com.icss.oa.netoffice.onduty.handler.DutyHandler;

/**
 * @author firecoral
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class AuthorlessDutyServlet extends ServletBase{
	
	Connection conn = null;

	/* (non-Javadoc)
	 * @see com.icss.j2ee.servlet.ServletBase#performTask(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	protected void performTask(HttpServletRequest request, HttpServletResponse response) 
		throws ServletException, IOException {
		
		Connection conn = null;
		String orguuid = request.getParameter("orgUUid");
		try {
			conn = this.getConnection(Globals.DATASOURCEJNDI);
			ConnLog.open("AuthorlessDutyServlet");
			String[] ids = request.getParameterValues("checkid");
			String personuuid = "";
			DutyHandler dhandler = new DutyHandler(conn);
			for(int i=0;i<ids.length;i++){
				personuuid = ids[i];
				dhandler.deleteRolePerson(personuuid,OndutyConfig.AUTHORDUTY_ROLE);
				//删除一条权限记录
				dhandler.delDutyRight(orguuid,personuuid);
			}
			
			this.forward(request,response,"/servlet/ListAuthorServlet?orgid="+orguuid);
			
		} catch (ServiceLocatorException e) {		
			e.printStackTrace();
		} finally{
			try {
				if(conn!=null){
					conn.close();
					ConnLog.close("AuthorlessDutyServlet");
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		
	}		

}
