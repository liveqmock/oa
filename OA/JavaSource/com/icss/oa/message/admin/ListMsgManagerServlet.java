/*
 * Created on 2004-4-27
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
public class ListMsgManagerServlet extends ServletBase {
	/* (non-Javadoc)
	 * @see com.icss.j2ee.servlet.ServletBase#performTask(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	protected void performTask(
		HttpServletRequest request,
		HttpServletResponse response)
		throws ServletException, IOException {

		Connection conn=null;
		String orguuid=request.getParameter("orgid");
		try{
			conn=this.getConnection(Globals.DATASOURCEJNDI);
			ConnLog.open("ListMsgManagerServlet");
			MsgHandler handler=new MsgHandler(conn);
			List list=handler.getMsgManagerList(orguuid);
			request.setAttribute("list",list);

			this.forward(request, response,"/message/msgManagerList.jsp");
		} catch (Exception e) {
			handleError(e);
		} finally {
			try {
				if (conn != null) {
					conn.close();
					ConnLog.close("ListMsgManagerServlet");
				}
			} catch (SQLException sqle) {
			}
		}
	}
}

