/*
 * Created on 2004-12-4
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.icss.oa.log.admin;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

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

/**
 * @author firecoral
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class LogDetailServlet extends ServletBase{

	/* (non-Javadoc)
	 * @see com.icss.j2ee.servlet.ServletBase#performTask(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	protected void performTask(HttpServletRequest request, HttpServletResponse response) 
	    throws ServletException, IOException {
		
		Connection conn = null;
		List loglist = null;
		List replylist=null;
		List attachlist=null;
		//BbsUserinfoVO userVO = null;
		Integer sysid = request.getParameter("sysid")==null?new Integer(-1):new Integer(request.getParameter("sysid"));
		Integer logid = request.getParameter("logid")==null?new Integer(-1):new Integer(request.getParameter("logid"));
		
		try {
			if(new Integer(-1).equals(logid)){
				this.forward(request, response, "/log/logmsglist.jsp?logid="+logid);
				
			}else{
				
				
				conn =
					DBConnectionLocator.getInstance().getConnection(
						Globals.DATASOURCEJNDI);
				LogHandler handler = new LogHandler(conn);
				//UserMsgHandler userMsghandler = new UserMsgHandler(conn);
				//得到显示列表
				
				loglist=handler.getLogDetail(logid);
				replylist=handler.getLogReply(logid);
				attachlist=handler.getattachListfull(logid);
//				System.out.println("+++++++++++++LogDetailServlet++++++++++loglist.size="+loglist.size()+" and "+replylist.size());
				request.setAttribute(
						"loglist",
						loglist);
				request.setAttribute(
						"replylist",
						replylist);
				request.setAttribute("attachlist",attachlist);
					
//				System.out.println("+++++++++++++LogDetailServlet++++++++++go to logdetail.jsp");
				
					
				this.forward(request, response, "/log/logdetail.jsp?sysid="+sysid+"&logid="+logid);
			}
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
