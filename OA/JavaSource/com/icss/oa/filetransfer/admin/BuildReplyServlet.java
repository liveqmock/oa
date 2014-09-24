/*
 * Created on 2004-5-11
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.icss.oa.filetransfer.admin;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.icss.common.log.ConnLog;
import com.icss.j2ee.servlet.ServletBase;
import com.icss.j2ee.util.Globals;
import com.icss.oa.filetransfer.handler.FiletransferSetHandler;
import com.icss.oa.filetransfer.handler.SendFileBean;

/**
 * @author Administrator
 *
 * 显示文件正文内容
 */
public class BuildReplyServlet extends ServletBase {

	protected void performTask(
		HttpServletRequest request,
		HttpServletResponse response)
		throws ServletException, IOException {

		Connection conn = null;
		String curPageNum = request.getParameter("curPageNum"); 
		//排序字段名 默认按时间字段 0:按照时间排序 1:按照大小排序
		String sortname = request.getParameter("sortname");
		//排序方式 默认为倒序
		String sorttype = request.getParameter("sorttype");

		try {
			conn = this.getConnection(Globals.DATASOURCEJNDI);
			ConnLog.open("BuildReplyServlet");
			FiletransferSetHandler ftsHandler =
				new FiletransferSetHandler(conn);

			//每次进入发送文件功能时，从新添加session
			HttpSession session = request.getSession();
			SendFileBean.removeSession(session);
			SendFileBean sendFileBean =
				SendFileBean.getInstanceFromSession(session);

			//依邮箱设置表，得到准确的邮箱用户名，用来构造邮箱地址
			String type = request.getParameter("type");
			String fromuser = request.getParameter("from");
			sendFileBean.setSendto(
				ftsHandler.getCName(
					fromuser.substring(0, fromuser.indexOf("@"))));
			sendFileBean.setMailName(request.getParameter("mailName"));
			sendFileBean.setTopic("回复:" + request.getParameter("subject"));
			sendFileBean.setContent(
				"&nbsp;&nbsp;&nbsp;&nbsp;------------------------文件源信息------------------------&nbsp;&nbsp;&nbsp;&nbsp;<br><br>"
					+ request.getParameter("content")
					+ "<br><br>&nbsp;&nbsp;&nbsp;&nbsp;--------------------------------------------------------&nbsp;&nbsp;&nbsp;&nbsp;<br><br>");

			sendFileBean.setisRe(request.getParameter("isRe"));
			sendFileBean.setisSent(request.getParameter("isSent"));
			sendFileBean.setfolder(request.getParameter("folder"));
			sendFileBean.setmailtype(type);

			SendFileBean.saveToSession(session, sendFileBean);
			this.forward(request, response, "/mail/ReplyMail_Body1.jsp?curPageNum="+curPageNum+"&sortname="+sortname+"&sorttype="+sorttype);
		} catch (Exception e) {
			e.printStackTrace();
			//throw new RuntimeException("wrong" + e);
		} finally {
			if (conn != null)
				try {
					conn.close();
					ConnLog.close("BuildReplyServlet");
				} catch (Exception e2) {
					e2.printStackTrace();
				}
		}

	}

}
