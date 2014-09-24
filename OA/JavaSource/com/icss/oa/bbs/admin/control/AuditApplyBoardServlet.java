/*
 * Created on 2004-12-20
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.icss.oa.bbs.admin.control;

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
import com.icss.oa.bbs.handler.BBSHandler;

/**
 * @author firecoral
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class AuditApplyBoardServlet extends ServletBase{

	/* (non-Javadoc)
	 * @see com.icss.j2ee.servlet.ServletBase#performTask(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	protected void performTask(HttpServletRequest request, HttpServletResponse response) 
		throws ServletException, IOException {
		
		Connection conn = null;
		Integer boardId = new Integer(request.getParameter("boardId"));
		try {
			conn = this.getConnection(Globals.DATASOURCEJNDI);
			ConnLog.open("AreaManageServlet");
			BBSHandler handler = new BBSHandler(conn);
			handler.auditApplyBoard(boardId);
			
			this.forward(request,response,"/servlet/ShowApplyBoardServlet");
		} catch (ServiceLocatorException e) {		
			e.printStackTrace();
		} finally{
			if(conn!=null){
				try {
					conn.close();
					ConnLog.close("AreaManageServlet");
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		}
		
	}

}
