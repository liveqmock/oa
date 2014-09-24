/*
 * Created on 2004-5-10
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.icss.oa.log.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.icss.j2ee.servlet.ServletBase;
import com.icss.oa.log.handler.SendFileBean;

/**
 * @author Administrator
 * 
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class AddLogMsgAttachFileServlet extends ServletBase {

	protected void performTask(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();

		// 保存发送文件信息状态
		SendFileBean sendFileBean = SendFileBean
				.getInstanceFromSession(session);
		Integer sysid = request.getParameter("sysid") == null ?new Integer("-1") : new Integer(request
				.getParameter("sysid"));
		String loganalyse = request.getParameter("loganalyse") == null ? "" : request
				.getParameter("loganalyse");
		String logreason = request.getParameter("logreason") == null ? ""
				: request.getParameter("logreason");
		String logpheno = request.getParameter("logpheno") == null ? ""
				: request.getParameter("logpheno");
		String logdesc = request.getParameter("logdesc") == null ? "" : request
				.getParameter("logdesc");
//		System.out.println("+++++++++addlogmsgattachfileservlet++++sysid11111111="+sysid);
		sendFileBean.setSysid(sysid);
		sendFileBean.setLogreason(logreason);
		sendFileBean.setLoganalyse(loganalyse);
		sendFileBean.setLogdesc(logdesc);
		sendFileBean.setLogpheno(logpheno);
		
		SendFileBean.saveToSession(session, sendFileBean);
//		System.out.println("+++++++++addlogmsgattachfileservlet++++sysid="+sysid);
		this.forward(request, response, "/log/attachFile.jsp?donext=1&sysid="+sysid);
	}
}
