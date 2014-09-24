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
 * ��ʾ�ļ���������
 */
public class BuildReplyServlet extends ServletBase {

	protected void performTask(
		HttpServletRequest request,
		HttpServletResponse response)
		throws ServletException, IOException {

		Connection conn = null;
		String curPageNum = request.getParameter("curPageNum"); 
		//�����ֶ��� Ĭ�ϰ�ʱ���ֶ� 0:����ʱ������ 1:���մ�С����
		String sortname = request.getParameter("sortname");
		//����ʽ Ĭ��Ϊ����
		String sorttype = request.getParameter("sorttype");

		try {
			conn = this.getConnection(Globals.DATASOURCEJNDI);
			ConnLog.open("BuildReplyServlet");
			FiletransferSetHandler ftsHandler =
				new FiletransferSetHandler(conn);

			//ÿ�ν��뷢���ļ�����ʱ���������session
			HttpSession session = request.getSession();
			SendFileBean.removeSession(session);
			SendFileBean sendFileBean =
				SendFileBean.getInstanceFromSession(session);

			//���������ñ��õ�׼ȷ�������û������������������ַ
			String type = request.getParameter("type");
			String fromuser = request.getParameter("from");
			sendFileBean.setSendto(
				ftsHandler.getCName(
					fromuser.substring(0, fromuser.indexOf("@"))));
			sendFileBean.setMailName(request.getParameter("mailName"));
			sendFileBean.setTopic("�ظ�:" + request.getParameter("subject"));
			sendFileBean.setContent(
				"&nbsp;&nbsp;&nbsp;&nbsp;------------------------�ļ�Դ��Ϣ------------------------&nbsp;&nbsp;&nbsp;&nbsp;<br><br>"
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
