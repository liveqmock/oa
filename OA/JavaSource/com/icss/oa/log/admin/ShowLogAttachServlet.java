/*
 * Created on 2004-12-4
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.icss.oa.log.admin;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.j2ee.services.DBConnectionLocator;
import com.icss.j2ee.servlet.ServletBase;
import com.icss.j2ee.util.Globals;
import com.icss.oa.bbs.handler.BBSHandler;
import com.icss.oa.bbs.handler.UserMsgHandler;
import com.icss.oa.bbs.vo.BbsUserinfoVO;
import com.icss.oa.log.handler.LogHandler;
import com.icss.oa.log.vo.LogMsgVO;
import com.icss.oa.log.vo.LogSysVO;

/**
 * @author firecoral
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ShowLogAttachServlet extends ServletBase{

	/* (non-Javadoc)
	 * @see com.icss.j2ee.servlet.ServletBase#performTask(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	protected void performTask(HttpServletRequest request, HttpServletResponse response) 
	    throws ServletException, IOException {
		
		Connection conn = null;
		List list = null;
		//BbsUserinfoVO userVO = null;
		Integer parentId = request.getParameter("parentId")==null?new Integer(-1):new Integer(request.getParameter("parentId"));
		
		try {
			conn =
				DBConnectionLocator.getInstance().getConnection(
					Globals.DATASOURCEJNDI);
			LogHandler handler = new LogHandler(conn);
			
			//得到显示列表
			
			request.setAttribute(
					"list",
					list);
				
			
				
			this.forward(request, response, "/log/logmsglist.jsp?parentId="+parentId);
//			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (conn != null)
					conn.close();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		
	}

}
