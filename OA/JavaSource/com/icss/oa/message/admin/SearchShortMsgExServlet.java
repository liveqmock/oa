/*
 * Created on 2004-5-21
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package com.icss.oa.message.admin;

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
import com.icss.oa.message.handler.MsgHandler;

/**
 * @author Administrator
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public class SearchShortMsgExServlet extends ServletBase {
	/* (non-Javadoc)
	 * @see com.icss.j2ee.servlet.ServletBase#performTask(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	protected void performTask(
		HttpServletRequest request,
		HttpServletResponse response)
		throws ServletException, IOException {
		

		String content = request.getParameter("content");
		String sendPerson = request.getParameter("sendPerson");
		String sendOrg = request.getParameter("sendOrg");
		
		boolean searchOld = false;
		String _searchOld[] = request.getParameterValues("searchOld");
		if(_searchOld!=null&&_searchOld.length>0){
			searchOld = true;
		}
		
  		Long startTime=MsgHandler.getLongByTime(request.getParameter("msgDateStart"));
  		Long endTime=MsgHandler.getLongByTime(request.getParameter("msgDateStop"));
		if(startTime!=null&&endTime!=null){
			if(startTime.longValue()>endTime.longValue()){
				Long temp=endTime;
				endTime=startTime;
				startTime=temp;
			}
		}
		if(endTime!=null){
			endTime=new Long(endTime.longValue()+1000*3600*24-1);
		}
		
		Connection conn = null;
		try{
			conn=this.getConnection(Globals.DATASOURCEJNDI);
			ConnLog.open("SearchShortMsgExServlet");
			MsgHandler handler=new MsgHandler(conn);
			List list=handler.searchShortMsgEx(content,sendPerson,sendOrg,startTime,endTime,searchOld);
			request.setAttribute("list",list);

			this.forward(request, response,"/message/searchShortMsgEx.jsp?searchOld="+searchOld);
		}catch (Exception e) {
			handleError(e);
		} finally {
			try {
				if (conn != null) {
					conn.close();
					ConnLog.close("SearchShortMsgExServlet");
				}
			} catch (SQLException sqle) {
			}
		}
	}
}
