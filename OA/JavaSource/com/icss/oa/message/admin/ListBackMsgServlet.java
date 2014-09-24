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
public class ListBackMsgServlet extends ServletBase {
	/* (non-Javadoc)
	 * @see com.icss.j2ee.servlet.ServletBase#performTask(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	protected void performTask(
		HttpServletRequest request,
		HttpServletResponse response)
		throws ServletException, IOException {
		
		boolean searchOld = false;
		try{
			searchOld=new Boolean(request.getParameter("searchOld")).booleanValue();
		}
		catch(Exception e){}
		Integer msgMid=null;
		try{
			msgMid=new Integer(request.getParameter("msgMid"));
		}
		catch(Exception e){}
		
		Connection conn = null;
		try{
			conn=this.getConnection(Globals.DATASOURCEJNDI);
			ConnLog.open("ListBackMsgServlet");
			MsgHandler handler=new MsgHandler(conn);
			List list=handler.getBackMsgList(msgMid,searchOld);
			request.setAttribute("list",list);

			this.forward(request, response,"/message/backMsgList.jsp?searchOld="+searchOld);
		}catch (Exception e) {
			handleError(e);
		} finally {
			try {
				if (conn != null) {
					conn.close();
					ConnLog.close("ListBackMsgServlet");
				}
			} catch (SQLException sqle) {
			}
		}
	}
}
