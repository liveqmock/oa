/*
 * Created on 2004-4-21
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package com.icss.oa.message.api;

import java.io.IOException;
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
public class ReceiveShortMsgServlet extends ServletBase {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
		throws IOException {
		String msgnum = request.getParameter(MSGReceiver.getReceiveArgName());
		MsgContent msg = MSGReceiver.receive(msgnum);
		String reXml = null;
		Connection conn = null;
		if (msg != null) {
			try {
				conn = this.getConnection(Globals.DATASOURCEJNDI);
ConnLog.open("ReceiveShortMsgServlet");
				MsgHandler handler = new MsgHandler(conn);
				handler.receiveMsg(msg);
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if(conn!=null){
					try {
						conn.close();
						ConnLog.close("ReceiveShortMsgServlet");
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
			}
			reXml = MSGReceiver.createResXml(true);
		} else {
			reXml = MSGReceiver.createResXml(false);
		}
		response.setContentType("text/xml");
		response.getWriter().write(reXml);
	}
	
	
	protected void performTask(
		HttpServletRequest arg0,
		HttpServletResponse arg1)
		throws ServletException, IOException {
	}
	
	
}




