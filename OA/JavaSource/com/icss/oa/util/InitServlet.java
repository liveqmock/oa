/*
 * Created on 2004-7-5
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package com.icss.oa.util;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.common.log.ConnLog;
import com.icss.j2ee.servlet.ServletBase;
import com.icss.j2ee.util.Globals;
import com.icss.oa.intendwork.handler.IntendWork;

/**
 * @author Administrator
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public class InitServlet extends ServletBase {
	/* (non-Javadoc)
	 * @see com.icss.j2ee.servlet.ServletBase#performTask(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}
	public void init() {
		ServletContext sc = this.getServletContext();
		IntendWork.InitProperties(sc.getRealPath("/"));
		String _switch = sc.getInitParameter("STATSITE_SWITCH");
		StatSiteControl.InitControl(_switch);
		String _statsitejndi = sc.getInitParameter("addressjndi");
		StatSiteControl.Initjndi(_statsitejndi);
		Connection conn = null;
		try {
			conn = this.getConnection(Globals.DATASOURCEJNDI);
			ConnLog.open("InitServlet");
		} catch (Exception e) {
			conn = null;
		}
		//MsgConfig.Init();
		//MsgMark.Init(conn,MsgConfig.MSG_NUM_LENGTH);
		//MSGSender.init(MsgConfig.MSG_URL_SEND,MsgConfig.MSG_NUM_SERVER,MsgConfig.MSG_NUM_APP,MsgConfig.MSG_SENDARGNAME);
		//MSGReceiver.init(MsgConfig.MSG_URL_RECEIVE,MsgConfig.MSG_RECEIVEARGNAME);
		if (conn != null) {
			try {
				conn.close();
				ConnLog.close("InitServlet");
			} catch (Exception e) {
			}
		}

	}
}
