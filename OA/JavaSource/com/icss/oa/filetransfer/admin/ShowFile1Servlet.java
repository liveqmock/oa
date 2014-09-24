/*
 * Created on 2004-6-28
 *
 * 
 */
package com.icss.oa.filetransfer.admin;

import java.io.IOException;
import java.rmi.RemoteException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.redflaglinux.ldap.LdapException;
import org.redflaglinux.services.dir.dirmanage;

import com.icss.common.log.ConnLog;
import com.icss.j2ee.services.ServiceLocatorException;
import com.icss.j2ee.servlet.ServletBase;
import com.icss.j2ee.util.Globals;
import com.icss.oa.filetransfer.handler.FileTransferHandler;
import com.icss.oa.filetransfer.handler.FiletransferSetHandler;
import com.icss.oa.filetransfer.handler.MessageHandler;
import com.icss.oa.filetransfer.handler.SendFileBean;
import com.icss.oa.filetransfer.util.FiletransferUtil;
import com.icss.oa.filetransfer.util.MailhandlerFactory;
import com.icss.oa.filetransfer.vo.DraftPersonVO;
import com.icss.oa.intendwork.handler.IntendWork;
import com.icss.resourceone.common.logininfo.UserInfo;
import com.icss.resourceone.sdk.framework.Context;
import com.icss.resourceone.sdk.framework.EntityException;

/**
 * @author firecoral ��ʾ�ļ���������
 */
public class ShowFile1Servlet extends ServletBase {

	protected void performTask(HttpServletRequest request,
			HttpServletResponse response) throws ServletException {
		dirmanage mailHandler = null;
		Connection conn = null;
		Context ctx = null;
		try {
			String type = request.getParameter("type");
			String mailName = request.getParameter("mailName");
			String folderSort = request.getParameter("folder");
			String curPageNum = request.getParameter("curPageNum");
			// �����ֶ��� Ĭ�ϰ�ʱ���ֶ� 0:����ʱ������ 1:���մ�С����
			String sortname = request.getParameter("sortname");
			// ����ʽ Ĭ��Ϊ����
			String sorttype = request.getParameter("sorttype");
			String sendMan = ""; // ������
			List toList = new ArrayList(); // ������
			List ccList = new ArrayList(); // ���ͽ�����
			List bccList = new ArrayList(); // ������
			List toList1 = new ArrayList(); // ������
			List ccList1 = new ArrayList(); // ���ͽ�����
			List bccList1 = new ArrayList(); // ������
			conn = this.getConnection(Globals.DATASOURCEJNDI);
			ConnLog.open("ShowFile1Servlet");
			ctx = Context.getInstance();
			UserInfo user = ctx.getCurrentLoginInfo();
			// ���������ñ��õ�׼ȷ�������û������������������ַ
			FiletransferSetHandler ftsHandler = new FiletransferSetHandler(conn);
			String username = ftsHandler.getUserid(user.getPersonUuid());
			mailHandler = MailhandlerFactory.getHandler(username);
			byte[] mailContent = null;
			String check = ""; // �ж��Ƿ���Ҫ��ִ
			// String totalmailName = "";
			String srcName = "";
			String desName = "";
			String isNew = mailName.substring(1, 2);
			boolean newMail = false;
			if ("n".equals(isNew)) { // Ϊ���ʼ�������Ҫ�����ͼ���Ƿ�Ҫ��ִ
				newMail = true;
				if ("system".equals(type)) {
					srcName = mailName;
					desName = mailName.substring(0, 1) + "o"
							+ mailName.substring(2, mailName.length());
					check = mailHandler.checkre(mailName);
				} else if ("user".equals(type)) { // Ϊ�Զ����ļ���
					folderSort = ftsHandler.encodeBase64(folderSort);
					srcName = folderSort + "/" + mailName;
					desName = folderSort + "/" + mailName.substring(0, 1) + "o"
							+ mailName.substring(2, mailName.length());
					check = mailHandler.checkre(folderSort + "/" + mailName);
				} else { // ������������δ��
					if ("inbox".equals(folderSort) || "Sent".equals(folderSort)) {
						srcName = mailName;
						desName = mailName.substring(0, 1) + "o"
								+ mailName.substring(2, mailName.length());
						check = mailHandler.checkre(mailName);
					} else {
						folderSort = ftsHandler.encodeBase64(folderSort);
						srcName = folderSort + "/" + mailName;
						desName = folderSort + "/" + mailName.substring(0, 1)
								+ "o"
								+ mailName.substring(2, mailName.length());
						check = mailHandler
								.checkre(folderSort + "/" + mailName);
					}
				}
				// ���ʼ���Ϊ�Ѷ����
				try {
					mailHandler.dirRename(srcName, desName);
				} catch (RemoteException e) {
					newMail = false;
					e.printStackTrace();
				}
				// �õ��µ��ʼ���
				mailName = mailName.substring(0, 1) + "o"
						+ mailName.substring(2, mailName.length());

			}
			if ("system".equals(type)) {
				mailContent = mailHandler.viewmail(mailName);
			} else if ("user".equals(type)) { // Ϊ�Զ����ļ���
				if (!("n".equals(isNew))) {
					folderSort = ftsHandler.encodeBase64(folderSort);
				}
				mailContent = mailHandler.viewmail(folderSort + "/" + mailName);
			} else { // ������������δ��
				if ("inbox".equals(folderSort) || "Sent".equals(folderSort)) {
					mailContent = mailHandler.viewmail(mailName);
				} else {
					if (!("n".equals(isNew))) { // Ϊ���ʼ�
						folderSort = ftsHandler.encodeBase64(folderSort);
					}
					mailContent = mailHandler.viewmail(folderSort + "/"
							+ mailName);
				}
			}
			MessageHandler messageHandler = new MessageHandler();
			MimeMessage fileMessage = messageHandler
					.getContentMessage(mailContent);
			String fromAddress = "";
			// ���ڴӲݸ��䷢��
			HttpSession session = request.getSession();
			SendFileBean.removeSession(session);
			SendFileBean sendFileBean = SendFileBean
					.getInstanceFromSession(session);
			// ������������
			fromAddress = MimeUtility.decodeText(fileMessage.getFrom()[0]
					.toString());
			sendMan = ftsHandler.getCName(fromAddress.substring(0, fromAddress
					.indexOf("@")));
			// �ӽ�����
			Address[] toArray = fileMessage
					.getRecipients(Message.RecipientType.TO);
			toList = ftsHandler.getAddressList(toArray, sendFileBean, "to");
			toList1 = ftsHandler.getDraftVOList(toArray);
			// �ӳ�����
			Address[] ccArray = fileMessage
					.getRecipients(Message.RecipientType.CC);
			ccList = ftsHandler.getAddressList(ccArray, sendFileBean, "cc");
			ccList1 = ftsHandler.getDraftVOList(ccArray);
			// ��������
			Address[] bccArray = fileMessage
					.getRecipients(Message.RecipientType.BCC);
			bccList = ftsHandler.getAddressList(bccArray, sendFileBean, "bcc");
			bccList1 = ftsHandler.getDraftVOList(bccArray);

			String fromPerson = fromAddress.substring(0, fromAddress
					.indexOf("@"));
			String subject = fileMessage.getSubject();
			String realSubject = subject.substring(0, subject.length() - 14);
			// ɾ������
			if ("n".equals(isNew)) {
				String intendId = fromPerson + "," + subject + "," + username;
				String code = IntendWork.getCodeValue("oa_filetrans");
				IntendWork intendHandler = new IntendWork(conn);
				intendHandler.deleteWork(code, intendId);

			}

			FileTransferHandler handler = new FileTransferHandler(conn);
			// String[][] str = mailHandler.fileheadList(totalmailName);
			// FileTransferHandler handler = new FileTransferHandler(conn);
			// //����ɾ������
			// String code = IntendWork.getCodeValue("oa_filetrans");
			// String fileHead = "";
			// for(int i=0;i<str[0].length;i++){
			// System.out.println("str="+str[2][i]);
			//
			// if(mailName.equals(str[2][i])){
			// System.out.println("fileHead="+str[1][i]);
			//
			// fileHead = str[1][i];
			// //ɾ������
			// if("n".equals(isNew)){
			// handler.delIntend(code,fileHead,username);
			// }
			// break;
			// }
			// }
			// �����ʼ���
			// int fromindex1 = messageHandler.getFrom(fileHead).indexOf("<");
			// int fromindex2 = messageHandler.getFrom(fileHead).indexOf("@");
			// String fromPerson = messageHandler.getFrom(fileHead).substring(
			// fromindex1 + 1, fromindex2);
			// �����յ����ʼ�ʱ������д���Ķ���¼
			Integer recordMailid = null;
			// String subject = messageHandler.getIntendSubject(fileHead); //
			// ����ʱ��
			List readPersonlist = new ArrayList();
			String recePerson = "";
			if (newMail && !("Sent".equals(folderSort))) {
				recordMailid = handler.getRecordMailid(fromPerson.concat(",")
						.concat(subject));
				if (recordMailid != null) { // ���һ���Ķ���¼
					handler.writeReadPersonuuidWithBak(recordMailid, user
							.getPersonUuid());
				}
			} else {
				// �����Լ�������ʼ�ʱ�����ڲ鿴�Ķ���¼,�õ��Ķ����˵���������(��ϵͳ�ļ����µķ������е��ʼ��Ž����жϣ�
				recordMailid = handler.getRecordMailid(username.concat(",")
						.concat(subject));
				if (recordMailid != null) { // ��Ҫ�Ķ���¼
					readPersonlist = handler
							.getReadPersonNameWithBak(recordMailid);
				}
			}
			// Ϊ���ʼ�������Ҫ��ִ���Ҳ����Լ������Լ����ʼ�:
			if (!(check.equals("")) && (!(username.equals(fromPerson)))) {
				String to = fileMessage.getFrom()[0].toString();
				long nowtime = System.currentTimeMillis();
				String resubject = "��ִ��" + realSubject + Long.toString(nowtime);
				String retext = "  ����!����ʼ� \"" + realSubject + "\" �����յ���";
				mailHandler.sendreceipt(to, resubject, retext, null);
			}
			// �ж��Ƿ��ǽ���ݸ���
			boolean goDraft = false;
			if ("Draft".equals(request.getParameter("folder"))) {
				goDraft = true;
			}
			String content = "";
			// ������������ݸ���ʱ��Ԥ��д��session
			if (goDraft) {
				// ȡ��request�����Ϣ���Ѹ���ת�Ƶ����ص�Ŀ¼�У�����·��������һ�ݵ�session�У�����ʱʹ��
				// ÿ�ν��뷢���ļ�����ʱ���������session
				sendFileBean.setMailName(mailName);
				// �˴��õ����Ѿ���һ������13λ��ʱ���һ��subject
				sendFileBean.setTopic(subject);
				sendFileBean.setType(type);
				sendFileBean.setfolder(request.getParameter("folder"));
				ServletContext context = this.getServletContext();
				sendFileBean = handler.getSessionBean(sendFileBean,
						fileMessage, context, user.getUserID(), request);
				// �洢session
				SendFileBean.saveToSession(session, sendFileBean);
				// �����ռ��ˣ������ˣ������˵�SESSION
				HttpSession receSession = request.getSession();
				if (receSession != null)
					receSession.removeAttribute("recePersonList");
				List recePersonList = new ArrayList();
				recePersonList.add(toList);
				recePersonList.add(ccList);
				recePersonList.add(bccList);
				recePersonList.add(fileMessage);
				receSession.setAttribute("recePersonList", recePersonList);
				// Ϊ�˵õ���Ҫ������Attributes
				StringBuffer sendHtml = new StringBuffer();
				StringBuffer ccHtml = new StringBuffer();
				StringBuffer bccHtml = new StringBuffer();
				if (toList1 != null) {
					Iterator to_i = toList1.iterator();

					if (to_i != null) {
						while (to_i.hasNext()) {
							DraftPersonVO vo = new DraftPersonVO();
							vo = (DraftPersonVO) to_i.next();
							String cnname = vo.getCnname();
							if (cnname == null) {
								cnname = vo.getUserid();
							}
							if (cnname == null) {
								cnname = "�𻵵��û�";
							}
							sendHtml
									.append("<span class=\"send\" personType=\"0\" onclick=\"selectName(this,0);\" department=\"\" personName=\"");
							sendHtml.append(cnname);
							sendHtml.append("\" uuid =\"").append(vo.getUuid());
							sendHtml
									.append("\" person =\"")
									.append(vo.getUserid())
									.append(
											"\"><img src=\"/oabase/images/person.gif\">");
							sendHtml.append(cnname).append(",</span>");

						}
					}
				}
				if (ccList1 != null) {

					Iterator cc_i1 = ccList1.iterator();

					if (cc_i1 != null) {
						while (cc_i1.hasNext()) {
							DraftPersonVO vo = new DraftPersonVO();
							vo = (DraftPersonVO) cc_i1.next();
							String cnname = vo.getCnname();
							if (cnname == null) {
								cnname = vo.getUserid();
							}
							if (cnname == null) {
								cnname = "�𻵵��û�";
							}
							ccHtml
									.append("<span class=\"send\" personType=\"0\" onclick=\"selectName(this,1);\"  department=\"\" personName=\"");
							ccHtml.append(cnname);
							ccHtml.append("\" uuid=\"").append(vo.getUuid());
							ccHtml.append("\" person =\"");
							ccHtml
									.append(vo.getUserid())
									.append(
											"\"><img src=\"/oabase/images/person.gif\">");
							ccHtml.append(cnname).append(",</span>");

						}
					}
				}
				if (bccList1 != null) {

					Iterator bcc_i1 = bccList1.iterator();
					if (bcc_i1 != null) {
						while (bcc_i1.hasNext()) {
							DraftPersonVO vo = new DraftPersonVO();
							vo = (DraftPersonVO) bcc_i1.next();
							String cnname = vo.getCnname();
							if (cnname == null) {
								cnname = vo.getUserid();
							}
							if (cnname == null) {
								cnname = "�𻵵��û�";
							}
							bccHtml
									.append("<span class=\"send\" personType=\"0\" onclick=\"selectName(this,2);\" department=\"\" personName=\"");
							bccHtml.append(cnname);
							bccHtml.append("\" uuid = \"").append(vo.getUuid());
							bccHtml
									.append("\" person =\"")
									.append(vo.getUserid())
									.append(
											"\"><img src=\"/oabase/images/person.gif\">");
							bccHtml.append(cnname).append(",</span>");
						}
					}
				}
				request.setAttribute("sendHtml", sendHtml.toString());
				request.setAttribute("ccHtml", ccHtml.toString());
				request.setAttribute("bccHtml", bccHtml.toString());
			} else {

				if (!fileMessage.isMimeType("text/plain")) { // �洢����·��
					StringBuffer privateDir = new StringBuffer(username);
					privateDir.append(System.currentTimeMillis());
					// ����·���޸� 2005-12-28
					// ԭ�г���
					// �޸ĳ���
					StringBuffer path = new StringBuffer(FiletransferUtil
							.getDownloadFilePath());
					String filepath = handler.getfilepath(path, privateDir
							.toString());
					// �������ʼ�
					content = handler.processAttachPart(fileMessage, filepath,
							request, privateDir.toString());
				} else {
					if (!"autosender".equals(sendMan)) {
						content = (String) fileMessage.getContent();
					} else {
						content = (String) fileMessage.getContent();
					}

				}
				request.setAttribute("content", content);
			}
			// ȡ�÷�����uuid
			// String from = fileMessage.getFrom()[0].toString();
			// int index1 = from.indexOf("<");
			// int index2 = from.indexOf("@");
			// String userid = from.substring(index1 + 1, index2);
			String senduuid = ftsHandler.getAllUUid(fromPerson);
			request.setAttribute("senduuid", senduuid);
			request.setAttribute("sendMan", sendMan);
			request.setAttribute("tolist", toList);
			request.setAttribute("cclist", ccList);
			request.setAttribute("bcclist", bccList);
			request.setAttribute("type", type);
			request.setAttribute("fileMsg", fileMessage);
			request.setAttribute("mailName", mailName);
			request.setAttribute("folder", request.getParameter("folder"));
			request.setAttribute("readPersonlist", readPersonlist);
			request.setAttribute("recePerson", recePerson);
			request.setAttribute("recordMailid", recordMailid);
			request.setAttribute("username", username);
			if (goDraft) {
				this.forward(request, response, "/mail/ShowDraft_Body1.jsp");
			} else {
				this.forward(request, response,
						"/mail/ShowMail_Body.jsp?curPageNum=" + curPageNum
								+ "&sortname=" + sortname + "&sorttype="
								+ sorttype);
			}
		} catch (IOException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new RuntimeException("wrong" + e);
		} catch (MessagingException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new RuntimeException("wrong" + e);
		} catch (ServiceLocatorException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new RuntimeException("wrong" + e);
		} catch (EntityException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new RuntimeException("wrong" + e);
		} catch (Exception e) {
			try {
				System.out.println(e.getMessage());
				e.printStackTrace();
				request.setAttribute("title1", "����");
				request.setAttribute("title2", "����");
				request.setAttribute("failResult", "���ʼ������Ѿ���ɾ����");
				this.forward(request, response, "/mail/SendResult.jsp");
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			throw new RuntimeException("wrong" + e);
		} finally {
			if (mailHandler != null)
				try {
					mailHandler.disconnect();
				} catch (LdapException e1) {
					e1.printStackTrace();
				}
			if (conn != null)
				try {
					conn.close();
					ConnLog.close("ShowFile1Servlet");
				} catch (Exception e2) {
					e2.printStackTrace();
				}
		}
	}

}
