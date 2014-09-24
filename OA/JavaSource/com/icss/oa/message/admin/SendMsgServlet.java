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
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import com.icss.common.log.ConnLog;
import com.icss.j2ee.servlet.ServletBase;
import com.icss.j2ee.util.Globals;
import com.icss.oa.address.handler.AddressHelp;
import com.icss.oa.message.handler.MsgHandler;
import com.icss.oa.util.CurrentUser;

/** 
 * @author Administrator
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public class SendMsgServlet extends ServletBase {
	/* (non-Javadoc)
	 * @see com.icss.j2ee.servlet.ServletBase#performTask(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	protected void performTask(
		HttpServletRequest request,
		HttpServletResponse response)
		throws ServletException, IOException {
		
		Connection conn=null;
		
		String personuuid=new CurrentUser().getId();
		String orgid=request.getParameter("orgid");
		String orgname=request.getParameter("orgname");
		String content=request.getParameter("content");
		
		HttpSession session=request.getSession();
		List list0=(List) session.getAttribute("selectMsgPersonSession");
		
		try{
			conn=this.getConnection(Globals.DATASOURCEJNDI);
			ConnLog.open("SendMsgServlet");
			AddressHelp addresshelp=new AddressHelp(conn);
			List list=addresshelp.getperson(list0,request,"selectMsgPersonSession");
			MsgHandler handler=new MsgHandler(conn);
			String resStr=handler.sendMsg(list,content+"~"+orgname+"~",orgid);
			StringBuffer url=new StringBuffer();
			url.append("EditMsgServlet?orgid="+orgid+"&orgname="+URLEncoder.encode(orgname)+"&_tmp=true");
			if(resStr!=null){
				url.append("&resStr="+URLEncoder.encode(resStr));
			}

			response.sendRedirect(url.toString());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (conn != null) {
					conn.close();
					ConnLog.close("SendMsgServlet");
				}
			} catch (SQLException sqle) {
				sqle.printStackTrace();
			}
		}
	}
}

