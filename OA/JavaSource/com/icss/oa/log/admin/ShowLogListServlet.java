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
public class ShowLogListServlet extends ServletBase{

	/* (non-Javadoc)
	 * @see com.icss.j2ee.servlet.ServletBase#performTask(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	protected void performTask(HttpServletRequest request, HttpServletResponse response) 
	    throws ServletException, IOException {
		
		Connection conn = null;
		List logList = null;
		//BbsUserinfoVO userVO = null;
		Integer parentId = request.getParameter("parentId")==null?new Integer(-1):new Integer(request.getParameter("parentId"));
		
		try {
			conn =
				DBConnectionLocator.getInstance().getConnection(
					Globals.DATASOURCEJNDI);
			LogHandler handler = new LogHandler(conn);
			//UserMsgHandler userMsghandler = new UserMsgHandler(conn);
			//得到显示列表
			if(new Integer(-1).equals(parentId)){
				logList = handler.getAllLogList();
			}else{
				logList=handler.getLogList(parentId);
			}
			//得到附件的map列表
			
			Map attachmap=new HashMap();
			Iterator itr=logList.iterator();
			while(itr.hasNext()){
				Integer logid;
				List attachlist=new ArrayList();
				LogMsgVO vo=(LogMsgVO) itr.next() ;
				logid=vo.getLogId();
				attachlist=handler.getattachListsimple(logid);
				if(attachlist.size()>0){
					attachmap.put(logid,attachlist);
				}
			}
			request.setAttribute("attachmap",attachmap);
			/*Map sysmap=new HashMap();
			List syslist=handler.getAllsysList();
			Iterator sysitr=syslist.iterator();
			while(sysitr.hasNext()){
				LogSysVO vo=(LogSysVO) itr.next() ;
				String sysname=vo.getSysName();
				Integer sysid=vo.getLogSysId();
				sysmap.put(sysid,sysname);
			}
			request.setAttribute("sysmap",sysmap);
			*/
			//System.out.println("+++++++++++++ShowLogListServlet++++++++++loglist.size="+logList.size());
			request.setAttribute(
					"logList",
					logList);
				
			
				
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
