package com.icss.oa.workmeeting.admin;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.common.log.ConnLog;
import com.icss.j2ee.servlet.ServletBase;
import com.icss.j2ee.util.Globals;
import com.icss.oa.workmeeting.handler.Workinghandler;  
import com.icss.resourceone.common.logininfo.UserInfo;
import com.icss.resourceone.sdk.framework.Context;

/**
 * @author sunchuanting
 *
 * 
 */
public class GetWorkPlanRequestServlet extends ServletBase{
	protected void performTask(
			HttpServletRequest request,
			HttpServletResponse response)
			throws ServletException, IOException {

				Connection conn=null;
				UserInfo user = null;
				boolean flag = false;
				List list = null;
				
				try {
					conn = this.getConnection(Globals.DATASOURCEJNDI);
					ConnLog.open("GetWorkPlanRequestServlet");
					Context ctx = Context.getInstance();
					user = ctx.getCurrentLoginInfo();
					String userid = user.getUserID();
					String orguuid = null;
					
					Workinghandler handler = new Workinghandler(conn);
					
					orguuid = handler.getPersonJU_UUID(userid);
					flag = handler.getReadPower(userid);
					
					if(flag&&orguuid!=null)
					
					 { 
						list = handler.getWorkAllPlanRequestList();
					 }
				     else 
				     {  list = handler.getWorkPlanRequestList(orguuid); 
				     }  
					   	
					request.setAttribute("list",list);
					
			       this.forward(request, response, "/workmeeting/workplanrequest.jsp");
			       
				} catch (Exception e) {
					e.printStackTrace();
				}
				catch (Throwable e1) {
					e1.printStackTrace();
				}finally {
					try {
						if (conn != null) {
							conn.close();
							ConnLog.close("GetWorkPlanRequestServlet");
						}
					} catch (SQLException sqle) {
						sqle.printStackTrace();
					}
				}

		}

}

