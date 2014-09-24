/*
 * Created on 2004-4-28
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

import org.redflaglinux.ldap.LdapException;
import org.redflaglinux.services.dir.dirmanage;

import com.icss.common.log.ConnLog;
import com.icss.j2ee.servlet.ServletBase;
import com.icss.j2ee.util.Globals;
import com.icss.oa.config.FileTransferConfig;
import com.icss.oa.filetransfer.handler.FileTransferHandler;
import com.icss.oa.filetransfer.handler.FiletransferSetHandler;
import com.icss.oa.filetransfer.util.MailhandlerFactory;
import com.icss.oa.intendwork.handler.IntendWork;
import com.icss.oa.util.PropertyManager;
import com.icss.resourceone.common.logininfo.UserInfo;
import com.icss.resourceone.sdk.framework.Context;

/**
 * @author Administrator
 * 
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class MoveMailServlet extends ServletBase {

	protected void performTask(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		//�����ֶ��� Ĭ�ϰ�ʱ���ֶ� 0:����ʱ������ 1:���մ�С����
		String sortname = request.getParameter("sortname");
		//����ʽ Ĭ��Ϊ����
		String sorttype = request.getParameter("sorttype");	
		String curPageNum = request.getParameter("curPageNum");	

		dirmanage mailhandler = null;
		Connection conn = null;
		try {
			String folderSort = request.getParameter("folder");
			String type = request.getParameter("type");

			String[] messageid = request.getParameterValues("messageid");
			String targetfolder = request.getParameter("targetfolder");
			String targetfolder2 = "";

			String domain = PropertyManager.getProperty("archivesdomain");
			String ip = PropertyManager.getProperty("archivesip");

			conn = this.getConnection(Globals.DATASOURCEJNDI);
			ConnLog.open("MoveMailServlet");
			Context loginctx = Context.getInstance();
			UserInfo user = loginctx.getCurrentLoginInfo();
			//���������ñ��õ�׼ȷ�������û������������������ַ
			FiletransferSetHandler ftsHandler = new FiletransferSetHandler(conn);
			String username = ftsHandler.getUserid(user.getPersonUuid());

			mailhandler = MailhandlerFactory.getHandler(username);

			String code = IntendWork.getCodeValue("oa_filetrans");
			FileTransferHandler handler = new FileTransferHandler(conn);
			String[][] userStr;
			if ("user".equals(type)) {
				folderSort = ftsHandler.encodeBase64(folderSort);
				userStr = mailhandler.fileheadList(folderSort);
			} else {
				userStr = mailhandler.fileheadList("");
			}
			String mailhead = "";

			//�ƶ������ļ�
			for (int i = 0; i < messageid.length; i++) {

				String srcName = "";
				String desName = "";
				String mark = "";
				//Ϊ�Ƶ���Ŀ¼��
				if ("Inbox".equals(targetfolder)) //�ռ���
					mark = FileTransferConfig.RECE_FLAG;
				else if ("Sent".equals(targetfolder)) //������
					mark = FileTransferConfig.SENT_FLAG;
				else if ("Draft".equals(targetfolder)) //�ݸ���
					mark = FileTransferConfig.DRA_FLAG;
				else if ("Junk".equals(targetfolder)) //������
					mark = FileTransferConfig.JUNK_FLAG;
				else
					//Ϊ�Ƶ��Զ����ļ���
					mark = "";

				if ("user".equals(type)) { //Ϊ�û��Զ����ļ���move����

					//ɾ������
					for (int j = 0; j < userStr[0].length; j++) {
						if (messageid[i].equals(userStr[2][j])) {
							mailhead = userStr[1][j];
							handler.delIntend(code, mailhead, username);
						}
					}

					srcName = folderSort + "/" + messageid[i];
					if ("".equals(mark)) { //�ƶ����Զ����ļ���
						targetfolder2 = ftsHandler.encodeBase64(targetfolder);
						desName = targetfolder2;
						//�ƶ�
						mailhandler.dirmove(srcName, desName);
					} else {
						desName = mark
								+ messageid[i].substring(1, messageid[i]
										.length());
						//�ƶ�
						mailhandler.dirRename(srcName, desName);
					}

				} else { //�ɸ�Ŀ¼�Ƶ���

					//ɾ������
					for (int j = 0; j < userStr[0].length; j++) {
						if (messageid[i].equals(userStr[2][j])) {
							mailhead = userStr[1][j];
							handler.delIntend(code, mailhead, username);
						}
					}

					srcName = messageid[i];
					if ("".equals(mark)) { //�ƶ����Զ����ļ���
						targetfolder2 = ftsHandler.encodeBase64(targetfolder);
						desName = targetfolder2;
						mailhandler.dirmove(srcName, desName);
					} else {
						desName = mark
								+ messageid[i].substring(1, messageid[i]
										.length());
						//�ƶ�
						mailhandler.dirRename(srcName, desName);
					}
				}
			}

			request.setAttribute("folder", request.getParameter("folder"));
			request.setAttribute("type", type);
			this.forward(request, response, "/servlet/MessageListServlet?sortname="+sortname+"&sorttype="+sorttype+"&curPageNum="+curPageNum);

		} catch (Exception e) {
			
			e.printStackTrace();
			throw new RuntimeException("wrong " + e);
			
			
		} finally {
			if (mailhandler != null)
				try {
					mailhandler.disconnect();
				} catch (LdapException e1) {
					e1.printStackTrace();
				}
			if (conn != null)
				try {
					conn.close();
					ConnLog.close("MoveMailServlet");
				} catch (Exception e2) {
					e2.printStackTrace();
				}
		}

	}

}
