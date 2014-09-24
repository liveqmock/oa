/*
 * Created on 2004-4-28
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package com.icss.oa.message.admin;

import java.io.IOException;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.SQLException; 

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
public class DeleteMsgManagerServlet extends ServletBase {
	/* (non-Javadoc)
	 * @see com.icss.j2ee.servlet.ServletBase#performTask(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	protected void performTask(
		HttpServletRequest request,
		HttpServletResponse response)
		throws ServletException, IOException {
		
		Connection conn=null;
		String _page_num=request.getParameter("_page_num");
		String orgid=request.getParameter("orgid");
		String check[] = request.getParameterValues("check");
		try{
			conn=this.getConnection(Globals.DATASOURCEJNDI);
			ConnLog.open("DeleteMsgManagerServlet");
			MsgHandler handler=new MsgHandler(conn);
			for(int i=0;i<check.length;++i){
				handler.deleteMsgManager(orgid,check[i]);
			}

			response.sendRedirect("ListMsgManagerServlet?orgid="+orgid+"&orgname="+URLEncoder.encode(request.getParameter("orgname"))+"&_page_num="+_page_num);
			
		} catch (Exception e) {
			e.printStackTrace();
			handleError(e);
			
		} finally {
			try {
				if (conn != null) {
					conn.close();
					ConnLog.close("DeleteMsgManagerServlet");
				}
			} catch (SQLException sqle) {
				sqle.printStackTrace();
			}
		}
	}
}

