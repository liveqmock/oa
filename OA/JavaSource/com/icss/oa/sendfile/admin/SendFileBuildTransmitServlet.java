/*
 * Created on 2004-5-11
 */
package com.icss.oa.sendfile.admin;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.sql.Connection;
import java.util.ArrayList;

import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.redflaglinux.ldap.LdapException;
import org.redflaglinux.services.dir.dirmanage;

import com.icss.common.log.ConnLog;
import com.icss.j2ee.servlet.ServletBase;
import com.icss.j2ee.util.FileUtil;
import com.icss.j2ee.util.Globals;
import com.icss.oa.filetransfer.handler.AttachFileBean;
import com.icss.oa.filetransfer.handler.FileTransferHandler;
import com.icss.oa.filetransfer.handler.FiletransferSetHandler;
import com.icss.oa.filetransfer.handler.MessageHandler;
import com.icss.oa.filetransfer.handler.SendFileBean;
import com.icss.oa.filetransfer.util.FiletransferUtil;
import com.icss.oa.filetransfer.util.MailhandlerFactory;
import com.icss.oa.util.PropertyManager;
import com.icss.resourceone.common.logininfo.UserInfo;
import com.icss.resourceone.sdk.framework.Context;

/**
 * ����ת���ʼ�
 */
public class SendFileBuildTransmitServlet extends ServletBase {

	protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String type = request.getParameter("type");
		String folderSort = request.getParameter("folder");
		String mailpath = "";
		Connection conn = null;
		Context ctx = null;
		dirmanage mailhandler = null;

		try {

			conn = this.getConnection(Globals.DATASOURCEJNDI);
			ConnLog.open("BuildTransmitServlet");
			ctx = Context.getInstance();
			UserInfo user = ctx.getCurrentLoginInfo();
			//���������ñ��õ�׼ȷ�������û������������������ַ
			FiletransferSetHandler ftsHandler = new FiletransferSetHandler(conn);
			String username = ftsHandler.getUserid(user.getPersonUuid());

			//��Ϊʱ����ʾΪ������δ��ʱ���ת��
			if ("inbox".equals(folderSort) || "Inbox".equals(folderSort) || "Sent".equals(folderSort) || "Draft".equals(folderSort) || "Junk".equals(folderSort))
				mailpath = ""; //Ϊ��Ŀ¼��
			else {
				folderSort = ftsHandler.encodeBase64(folderSort);
				mailpath = folderSort + "/";
			} 

			String domain = PropertyManager.getProperty("archivesdomain");
			String ip = PropertyManager.getProperty("archivesip");

			//ÿ�ν��뷢���ļ�����ʱ���������session
			HttpSession session = request.getSession();
			SendFileBean.removeSession(session);
			SendFileBean sendFileBean = SendFileBean.getInstanceFromSession(session);
			sendFileBean.setfolder(folderSort);
			sendFileBean.setmailtype(type);
			sendFileBean.setMailName(request.getParameter("mailName"));
			//�˴��õ����Ѿ���һ������13λ��ʱ���һ��subject
			sendFileBean.setTopic("ת��:" + request.getParameter("subject"));
			sendFileBean.setContent(
				"&nbsp;&nbsp;&nbsp;&nbsp;------------------------�ļ�Դ��Ϣ------------------------&nbsp;&nbsp;&nbsp;&nbsp;<br><br>" + request.getParameter("content")+ "<br><br>&nbsp;&nbsp;&nbsp;&nbsp;--------------------------------------------------------&nbsp;&nbsp;&nbsp;&nbsp;<br><br>");
			sendFileBean.setisRe(request.getParameter("isRe"));
			
			mailhandler = MailhandlerFactory.getHandler(username);;

			String mailName = sendFileBean.getMailName();
			System.out.println(")))");
			System.out.println(mailName);
			System.out.println(mailpath + mailName);
			byte[] mailcontent = mailhandler.viewmail(mailpath + mailName);

			MessageHandler mHandler = new MessageHandler();
			MimeMessage reply = mHandler.getContentMessage(mailcontent);

			// ������ַ��Ϣ
			FileTransferHandler handler = new FileTransferHandler();
			StringBuffer privateDir = new StringBuffer(user.getUserID());
			privateDir.append(System.currentTimeMillis());



			//����·���޸� 2005-12-28
			//ԭ�г���
			//ServletContext context = this.getServletContext();
			//StringBuffer downloaddir = new StringBuffer(context.getRealPath("\\filetransfer\\downloadfile\\"));
			//�޸ĳ���
			StringBuffer downloaddir = new StringBuffer(FiletransferUtil.getDownloadFilePath());



			String downloadfilepath = handler.getfilepath(downloaddir, privateDir.toString());
			if (!reply.isMimeType("text/plain")) { //�洢����
				//�������ʼ�
				handler.processAttachPart(reply, downloadfilepath, request, privateDir.toString());
			}

			//ȡ��request�����Ϣ���Ѹ���ת�Ƶ����ص�Ŀ¼�У�����·��������һ�ݵ�session�У�����ʱʹ��
			ArrayList fileList = (ArrayList) request.getAttribute("attachList");

			if (fileList != null)
				for (int i = 0; i < fileList.size(); i++) { //�洢�����͸�����·��
					//���ڷ��͵�·��
					
					
					//�ϴ�·���޸� 2005-12-28
					//ԭ�г���
					//StringBuffer uploaddir = new StringBuffer(context.getRealPath("\\filetransfer\\uploadfile\\"));
					//�޸ĳ���
					StringBuffer uploaddir = new StringBuffer(FiletransferUtil.getUploadFilePath());
					
					
					
					
					File urlFile = new File(uploaddir.toString());
					if (!urlFile.exists()) {
						urlFile.mkdirs();
					}

					AttachFileBean filebean = (AttachFileBean) fileList.get(i);

					String downloadpath = downloaddir.toString().concat(filebean.getDownloadUrl());
					InputStream is = new FileInputStream(downloadpath);

					String filename = filebean.getFileOriginName();
					String uploadpath = uploaddir.append(File.separator).append(privateDir.toString()).append(File.separator).append(filename).toString();
					File uploadFile = new File(uploadpath);
					FileUtil.copy(is, uploadFile);
					filebean.setUploadUrl(uploadpath);
					sendFileBean.addAttach(filebean);

					if (is != null) {
						is.close();
					}
				}

			//�洢session
			SendFileBean.saveToSession(session, sendFileBean);

			//edit by yangyang 20050816
			//����Ĭ�ϵ�ת��
			//this.forward(request, response, "/filetransfer/transmit.jsp");
			//������Ĭ�ϵ�ת����ֱ�ӽ��뵽���޸�ת��ҳ��
			this.forward(request, response, "/mail/TransmitMail_Body1.jsp");

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			if (conn != null)
				try {
					conn.close();
					ConnLog.close("BuildTransmitServlet");
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			if (mailhandler != null) {
				try {
					mailhandler.disconnect();
				} catch (LdapException e) {
					e.printStackTrace();
				}
			}
		}

	}

}
