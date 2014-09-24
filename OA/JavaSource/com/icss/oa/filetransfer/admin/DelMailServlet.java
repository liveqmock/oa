/*
 * Created on 2004-6-27
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.icss.oa.filetransfer.admin;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

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
import com.icss.oa.filetransfer.handler.MessageHandler;
import com.icss.oa.filetransfer.util.MailhandlerFactory;
import com.icss.oa.intendwork.handler.IntendWork;
import com.icss.resourceone.common.logininfo.UserInfo;
import com.icss.resourceone.sdk.framework.Context;

/**
 * @author firecoral
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class DelMailServlet extends ServletBase {

	
	private void delMail(dirmanage mailhandler,String mailpath) throws IOException, Exception {
		
		if (mailpath != null && !"".equals(mailpath)) {
			mailhandler.deletedir(mailpath, 1);
		}
		
	}
	
	
	protected void performTask(
		HttpServletRequest request,
		HttpServletResponse response)
		throws ServletException, IOException {
		
		
		
		String folderSort = request.getParameter("folder"); //Ϊ��Ŀ¼�µ�������Զ����ļ�����
		String[] messageid = request.getParameterValues("messageid");

		String type = request.getParameter("type");
		//"system"��"user"��Ϊ��null��������δ��ʱ��

		String curPageNum = request.getParameter("curPageNum");

		String folderName = folderSort;
		//�����ֶ��� Ĭ�ϰ�ʱ���ֶ� 0:����ʱ������ 1:���մ�С����
		String sortname = request.getParameter("sortname");
		//����ʽ Ĭ��Ϊ����
		String sorttype = request.getParameter("sorttype");

		Connection conn = null;
		Context ctx = null;
		dirmanage mailhandler = null;

		try {

			conn = this.getConnection(Globals.DATASOURCEJNDI);
			ConnLog.open("DelMailServlet");
			ctx = Context.getInstance();
			UserInfo user = ctx.getCurrentLoginInfo();

			MessageHandler mhandler = new MessageHandler();
			FileTransferHandler fhandler = new FileTransferHandler(conn);
			//���������ñ��õ�׼ȷ�������û������������������ַ
			FiletransferSetHandler ftsHandler =
				new FiletransferSetHandler(conn);
			String username = ftsHandler.getUserid(user.getPersonUuid());


			mailhandler = MailhandlerFactory.getHandler(username);

			String mailhead = "";
			//IntendWork intendHandler = new IntendWork(conn);
			String code = IntendWork.getCodeValue("oa_filetrans");
			if (messageid == null) { //���ļ�������ҳ����
				//����������һ���Զ����ļ���
				
				if ("system".equals(type)) {
					String[][] str = mailhandler.fileheadList("");
					String firstword = "";
					for (int i = 0; i < str[0].length; i++) {
						firstword = str[2][i].substring(0, 1);
						if("Junk".equals(folderName)){
							if (FileTransferConfig.JUNK_FLAG.equals(firstword)) {
								mailhead = str[1][i];
								//ɾ���Ķ���¼
								fhandler.deleteRecord(
									username,
									mhandler.getIntendSubject(mailhead));
								//ɾ������
								fhandler.delIntend(code, mailhead, username);

								String mailpath = str[2][i];
								delMail(mailhandler,mailpath);
							}
						}else if("Sent".equals(folderName)){
							if (FileTransferConfig.SENT_FLAG.equals(firstword)) {
								mailhead = str[1][i];
								//ɾ���Ķ���¼
								fhandler.deleteRecord(
									username,
									mhandler.getIntendSubject(mailhead));
								//ɾ������
								fhandler.delIntend(code, mailhead, username);

								String mailpath = str[2][i];
								delMail(mailhandler,mailpath);
							}
						}else if("Draft".equals(folderName)){
							if (FileTransferConfig.DRA_FLAG.equals(firstword)) {
								mailhead = str[1][i];
								//ɾ���Ķ���¼
								fhandler.deleteRecord(
									username,
									mhandler.getIntendSubject(mailhead));
								//ɾ������
								fhandler.delIntend(code, mailhead, username);

								String mailpath = str[2][i];
								delMail(mailhandler,mailpath);
							}
						}else if("Rec".equals(folderName)){
							if (FileTransferConfig.RECE_FLAG.equals(firstword)) {
								mailhead = str[1][i];
								//ɾ���Ķ���¼
								fhandler.deleteRecord(
									username,
									mhandler.getIntendSubject(mailhead));
								//ɾ������
								fhandler.delIntend(code, mailhead, username);

								String mailpath = str[2][i];
								delMail(mailhandler,mailpath);
							}
						}
						
					}
				} else { //�Զ����ļ���
					folderName = ftsHandler.encodeBase64(folderName);
					String[][] userStr = mailhandler.fileheadList(folderName);
					for (int j = 0; j < userStr[0].length; j++) {
						mailhead = userStr[1][j];
						//ɾ���Ķ���¼
						fhandler.deleteRecord(
							username,
							mhandler.getIntendSubject(mailhead));
						//ɾ������
						fhandler.delIntend(code, mailhead, username);

						String usermailpath = folderName + "/" + userStr[2][j];
						String desName =
							"t"
								+ userStr[2][j].substring(
									1,
									userStr[2][j].length());
						//�ƶ�
						mailhandler.dirRename(usermailpath, desName);
					}
				}

			} else { //ѡ�񲿷�

				if ("user".equals(type)) { //ɾ��һ���Զ����ļ����е��ʼ�
					folderName = ftsHandler.encodeBase64(folderName);
					for (int j = 0; j < messageid.length; j++) {
						//ɾ������
						String[][] uStr = mailhandler.fileheadList(folderName);
						for (int k = 0; k < uStr[0].length; k++) {
							if (messageid[j].equals(uStr[2][k])) {
								mailhead = uStr[1][k];
								//ɾ���Ķ���¼
								fhandler.deleteRecord(
									username,
									mhandler.getIntendSubject(mailhead));
								//ɾ������
								fhandler.delIntend(code, mailhead, username);
								break;
							}
						}
						//ɾ���ļ�
						String usermailpath = folderName + "/" + messageid[j];
						delMail(mailhandler,usermailpath);
					}
				} else if ("system".equals(type)) { //ɾ��ϵͳ�ļ����е��ļ�
	
					for (int i = 0; i < messageid.length; i++) {
						//ɾ������
						String[][] str = mailhandler.fileheadList("");
						for (int u = 0; u < str[0].length; u++) {
							if (messageid[i].equals(str[2][u])) {
								mailhead = str[1][u];
								
//								String flag = str[2][i].substring(0, 1);
//								System.out.println("wwwwwwwwwwwwwwwwwwww  "+flag);
//								System.out.println("wwwwwwwwwwwwwwwwwwww1 "+messageid[i]);
//								System.out.println("wwwwwwwwwwwwwwwwwwww2 "+messageid[i].substring(0,1));    
								//System.out.println("ssssssssssssssssssssss  s2"+mailhead);
								//ɾ���Ķ���¼
								if("s".equals(messageid[i].substring(0,1))){
									fhandler.deleteRecord(username,mhandler.getIntendSubject(mailhead));
								}
								//ɾ������
								fhandler.delIntend(code, mailhead, username);
								break;
							}
						}
						//ɾ���ļ�
						String mailpath = messageid[i];
						delMail(mailhandler,mailpath);

					}
				} else { //������δ��ʱ��ɾ��
					boolean getok = false;
					String espmailpath = "";
					//����ϵͳ�ļ�������
					for (int x = 0; x < messageid.length; x++) {
						//ɾ������
						String[][] str = mailhandler.fileheadList("");
						for (int u = 0; u < str[0].length; u++) {
							if (messageid[x].equals(str[2][u])) {
								mailhead = str[1][u];
								//ɾ���Ķ���¼
								fhandler.deleteRecord(
									username,
									mhandler.getIntendSubject(mailhead));
								//ɾ������
								fhandler.delIntend(code, mailhead, username);
								getok = true; //�ҵ�
								espmailpath = messageid[x];
								break;
							}
						}
						if (!getok) { //�����û��Զ����ļ�������
							//�õ��û��Զ�����ļ���
							String[] uFolder = mailhandler.dirList("");
							//�û��ļ�������
							for (int y = 0; y < uFolder.length; y++) {
								//ɾ������
								String[][] uStr =
									mailhandler.fileheadList(uFolder[y]);
								for (int k = 0; k < uStr[0].length; k++) {
									if (messageid[x].equals(uStr[2][k])) {
										mailhead = uStr[1][k];
										//ɾ���Ķ���¼
										fhandler.deleteRecord(
											username,
											mhandler.getIntendSubject(
												mailhead));
										//ɾ������
										fhandler.delIntend(
											code,
											mailhead,
											username);
										espmailpath =
											uFolder[y] + "/" + messageid[x];
										break;
									} //if
								} //for
							} //for
						} //if getok
						//ɾ���ļ�
						//mailhandler.deletedir(espmailpath, 1);
						delMail(mailhandler,espmailpath);
					} //for
				} //else
			} //if messageid ==null

			
		} catch (Exception ex) {
			ex.printStackTrace();
			handleError(ex);
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
					ConnLog.close("DelMailServlet");
				} catch (Exception e2) {
					e2.printStackTrace();
				}
		}
		if("del".equals(type)){
			this.getServletContext().getRequestDispatcher("/servlet/SearchMailServlet").forward(request,response);
		}
		if ("noread".equals(folderSort)) { //��δ���ļ�
			this.forward(request, response, "/servlet/ShowNoReadServlet");
		} else if ("searchmail".equals(folderSort)) {
			this.forward(request, response, "/servlet/SearchMailServlet");
		} else if (messageid == null) { //���ļ�������ҳ
			this.forward(request, response, "/servlet/BoxListServlet");
		} else { //�������ļ���
			request.setAttribute("folder", folderSort);
			this.forward(
				request,
				response,
				"/servlet/MessageListServlet?curPageNum="
					+ curPageNum
					+ "&sortname="
					+ sortname
					+ "&sorttype="
					+ sorttype);
		}
	}

}
