/*
 * Created on 2004-12-31
 *
 * 
 */
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

/**
 * @author sunchuanting
 *
 * 
 */
public class GetWorkPlanServlet extends ServletBase{
	protected void performTask(
			HttpServletRequest request,
			HttpServletResponse response)
			throws ServletException, IOException {

				Connection conn=null;
				
				
				try {
					conn = this.getConnection(Globals.DATASOURCEJNDI);
					ConnLog.open("GetWorkPlanServlet");
					
					Workinghandler handler = new Workinghandler(conn);
					List list = handler.getWorkPlanList();
					request.setAttribute("list",list);

			       this.forward(request, response, "/workmeeting/workplan.jsp");
			       
				} catch (Exception e) {
					e.printStackTrace();
				}
				catch (Throwable e1) {
					e1.printStackTrace();
				}finally {
					try {
						if (conn != null) {
							conn.close();
							ConnLog.close("GetWorkPlanServlet");
						}
					} catch (SQLException sqle) {
					}
				}

		}

}
