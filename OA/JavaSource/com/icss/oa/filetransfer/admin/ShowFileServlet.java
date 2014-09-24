/*
 * Created on 2004-6-28
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.icss.oa.filetransfer.admin;     

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import javax.mail.Address;
import javax.mail.Message;
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
import com.icss.j2ee.servlet.ServletBase;
import com.icss.j2ee.util.Globals;
import com.icss.oa.filetransfer.handler.FileTransferHandler;
import com.icss.oa.filetransfer.handler.FiletransferSetHandler;
import com.icss.oa.filetransfer.handler.MessageHandler;
import com.icss.oa.filetransfer.handler.SendFileBean;
import com.icss.oa.filetransfer.util.FiletransferUtil;
import com.icss.oa.filetransfer.util.MailhandlerFactory;
import com.icss.oa.intendwork.handler.IntendWork;
import com.icss.oa.util.PropertyManager;
import com.icss.resourceone.common.logininfo.UserInfo;
import com.icss.resourceone.sdk.framework.Context;

/**
 * @author firecoral
 * ��ʾ�ļ���������
 */
public class ShowFileServlet extends ServletBase{

		protected void performTask(HttpServletRequest request, HttpServletResponse response) 
		throws ServletException{
			
			dirmanage mailHandler = null;
			Connection conn = null;
			Context ctx = null;
			
			try {
				String type = request.getParameter("type");
				String mailName = request.getParameter("mailName");
				String folderSort = request.getParameter("folder");

				String domain = PropertyManager.getProperty("archivesdomain");
				String ip = PropertyManager.getProperty("archivesip");
				
				String curPageNum = request.getParameter("curPageNum");
				//�����ֶ��� Ĭ�ϰ�ʱ���ֶ� 0:����ʱ������ 1:���մ�С����
			  	String sortname = request.getParameter("sortname");
				//����ʽ Ĭ��Ϊ����
			  	String sorttype = request.getParameter("sorttype");
				
				String sendMan = "";            //������
				List toList = new ArrayList();  //������
				List ccList = new ArrayList();  //���ͽ�����
				List bccList = new ArrayList(); //������
				
				conn = this.getConnection(Globals.DATASOURCEJNDI);
				ConnLog.open("ShowFileServlet");
				ctx = Context.getInstance();
				UserInfo user = ctx.getCurrentLoginInfo();
				//���������ñ��õ�׼ȷ�������û������������������ַ
				FiletransferSetHandler ftsHandler = new FiletransferSetHandler(conn);
				String username = ftsHandler.getUserid(user.getPersonUuid());
				mailHandler = MailhandlerFactory.getHandler(username);
				byte[] mailContent = null;
				String check = "";   //�ж��Ƿ���Ҫ��ִ
				String totalmailName = "";
				String srcName = "";
				String desName = "";
				String isNew = mailName.substring(1,2);
				boolean newMail = false;
				if("n".equals(isNew)){  //Ϊ���ʼ�������Ҫ�����ͼ���Ƿ�Ҫ��ִ
					newMail = true;
					if("system".equals(type)){
						srcName = mailName;
						desName = mailName.substring(0,1) + "o" + mailName.substring(2,mailName.length());
						check = mailHandler.checkre(mailName);
					}else if("user".equals(type)){   //Ϊ�Զ����ļ���
						folderSort = ftsHandler.encodeBase64(folderSort);
						srcName = folderSort+"/"+mailName;
						desName = folderSort+"/"+mailName.substring(0,1) + "o" + mailName.substring(2,mailName.length());
						check = mailHandler.checkre(folderSort+"/"+mailName);
					}else{    //������������δ��
						if("inbox".equals(folderSort)){
							srcName = mailName;
							desName = mailName.substring(0,1) + "o" + mailName.substring(2,mailName.length());
							check = mailHandler.checkre(mailName);
						}else{
							folderSort = ftsHandler.encodeBase64(folderSort);
							srcName = folderSort+"/"+mailName;
							desName = folderSort+"/"+mailName.substring(0,1) + "o" + mailName.substring(2,mailName.length());
							check = mailHandler.checkre(folderSort+"/"+mailName);
						}
					}
					//���ʼ���Ϊ�Ѷ����
					mailHandler.dirRename(srcName,desName);
					//�õ��µ��ʼ���
					mailName = mailName.substring(0,1) + "o" + mailName.substring(2,mailName.length());
				}
				if("system".equals(type)){
					mailContent = mailHandler.viewmail(mailName);
					totalmailName = "";
				}else if("user".equals(type)){   //Ϊ�Զ����ļ���
					folderSort = ftsHandler.encodeBase64(folderSort);
					mailContent = mailHandler.viewmail(folderSort+"/"+mailName);
					totalmailName = folderSort+"/";
				}else{    //������������δ��
					if("inbox".equals(folderSort)){
						mailContent = mailHandler.viewmail(mailName);
						totalmailName = "";
					}else{
						if(!("n".equals(isNew))){  //Ϊ���ʼ�
							folderSort = ftsHandler.encodeBase64(folderSort);
						}
						mailContent = mailHandler.viewmail(folderSort+"/"+mailName);
						totalmailName = folderSort+"/";
					}
				}
				MessageHandler messageHandler = new MessageHandler();
				
				MimeMessage fileMessage = messageHandler.getContentMessage(mailContent);
				String fromAddress = "";
				//���ڴӲݸ��䷢��
				HttpSession session = request.getSession();
				SendFileBean.removeSession(session);
				SendFileBean sendFileBean = SendFileBean.getInstanceFromSession(session);
				//������������
				fromAddress = MimeUtility.decodeText(fileMessage.getFrom()[0].toString());
				sendMan = ftsHandler.getCName(fromAddress.substring(0,fromAddress.indexOf("@")));
				//�ӽ�����
				Address[] toArray = fileMessage.getRecipients(Message.RecipientType.TO);
				toList = ftsHandler.getAddressList(toArray,sendFileBean,"to");
                //�ӳ�����
                Address[] ccArray = fileMessage.getRecipients(Message.RecipientType.CC);
                ccList = ftsHandler.getAddressList(ccArray,sendFileBean,"cc");
                //��������
                Address[] bccArray = fileMessage.getRecipients(Message.RecipientType.BCC);
                bccList = ftsHandler.getAddressList(bccArray,sendFileBean,"bcc");
                
				String[][] str = mailHandler.fileheadList(totalmailName);
				FileTransferHandler handler = new FileTransferHandler(conn);
				//����ɾ������
				String code = IntendWork.getCodeValue("oa_filetrans");
				String fileHead = "";
				for(int i=0;i<str[0].length;i++){
					if(mailName.equals(str[2][i])){
						fileHead = str[1][i];
						//ɾ������
						if("n".equals(isNew)){
							handler.delIntend(code,fileHead,username);
						}
						break;
					}
				}
				//�����ʼ���
				int fromindex1 = messageHandler.getFrom(fileHead).indexOf("<");
				int fromindex2 = messageHandler.getFrom(fileHead).indexOf("@");
				String fromPerson = messageHandler.getFrom(fileHead).substring(fromindex1+1,fromindex2);
				//�����յ����ʼ�ʱ������д���Ķ���¼
				Integer recordMailid = null;
				String subject = messageHandler.getIntendSubject(fileHead); //����ʱ��
				List readPersonlist = new ArrayList();
				String recePerson = "";
				if(newMail==true){
					recordMailid = handler.getRecordMailid(fromPerson.concat(",").concat(subject));
					if(recordMailid!=null){   //���һ���Ķ���¼
						handler.writeReadPersonuuid(recordMailid,user.getPersonUuid());
					}
//				}else if("".equals(totalmailName)&&"s".equals(mailName.substring(0,1))){
				}else{
					//�����Լ�������ʼ�ʱ�����ڲ鿴�Ķ���¼,�õ��Ķ����˵���������(��ϵͳ�ļ����µķ������е��ʼ��Ž����жϣ�
					recordMailid = handler.getRecordMailid(username.concat(",").concat(subject));
					if(recordMailid!=null){   //��Ҫ�Ķ���¼
						readPersonlist = handler.getReadPersonName(recordMailid);
						recePerson = handler.getRecePerson(recordMailid);
					}
				}
			
				//Ϊ���ʼ�������Ҫ��ִ���Ҳ����Լ������Լ����ʼ�
				if(newMail==true&&!(check.equals(""))&&(!(username.equals(fromPerson)))){
					String to = fileMessage.getFrom()[0].toString();
					long nowtime = System.currentTimeMillis();
					String resubject = "��ִ��"+messageHandler.getSubject(fileHead)+Long.toString(nowtime);
					String retext = "  ����!\r\n����ʼ� \""+messageHandler.getSubject(fileHead)+"\" �����յ���";
					mailHandler.sendreceipt(to,resubject,retext,null);
				}
				
				//�ж��Ƿ��ǽ���ݸ���
				boolean goDraft = false;
				if("Draft".equals(request.getParameter("folder"))){
					goDraft = true;
				}

				String content = "";
				//������������ݸ���ʱ��Ԥ��д��session
				if(goDraft){
					//ȡ��request�����Ϣ���Ѹ���ת�Ƶ����ص�Ŀ¼�У�����·��������һ�ݵ�session�У�����ʱʹ��
					//ÿ�ν��뷢���ļ�����ʱ���������session
					sendFileBean.setMailName(mailName);
					//�˴��õ����Ѿ���һ������13λ��ʱ���һ��subject
					sendFileBean.setTopic(subject);
					sendFileBean.setType(type);
					sendFileBean.setfolder(request.getParameter("folder"));
					ServletContext context = this.getServletContext();
					sendFileBean = handler.getSessionBean(sendFileBean,fileMessage,context,user.getUserID(),request);
					//�洢session
					SendFileBean.saveToSession(session, sendFileBean);
					//�����ռ��ˣ������ˣ������˵�SESSION
					HttpSession receSession = request.getSession();
					if(receSession!=null) receSession.removeAttribute("recePersonList");
					List recePersonList = new ArrayList();
					recePersonList.add(toList);
					recePersonList.add(ccList);
					recePersonList.add(bccList);
					recePersonList.add(fileMessage);
					receSession.setAttribute("recePersonList",recePersonList);
				}else{
					if (!fileMessage.isMimeType("text/plain")) { //�洢����·��
						StringBuffer privateDir = new StringBuffer(username);
						privateDir.append(System.currentTimeMillis());
					
					
					

						//����·���޸� 2005-12-28
						//ԭ�г���
						//ServletContext context = this.getServletContext();
						//StringBuffer path = new StringBuffer(context.getRealPath("/filetransfer/downloadfile/"));
						//�޸ĳ���
						StringBuffer path = new StringBuffer(FiletransferUtil.getDownloadFilePath());
						
					
					

						String filepath = handler.getfilepath(path,privateDir.toString());
					
						//�������ʼ�
						content =
							handler.processAttachPart(
								fileMessage,
								filepath,
								request,
								privateDir.toString());
					} else {
						content = (String)fileMessage.getContent();
					}
					request.setAttribute("content", content);
				}
				request.setAttribute("sendMan",sendMan);
				request.setAttribute("tolist",toList);
				request.setAttribute("cclist",ccList);
				request.setAttribute("bcclist",bccList);
				request.setAttribute("type",type);
				request.setAttribute("fileMsg", fileMessage);
				request.setAttribute("mailName", mailName);
				request.setAttribute("folder",request.getParameter("folder"));
				request.setAttribute("readPersonlist",readPersonlist);
				request.setAttribute("recePerson",recePerson);
				request.setAttribute("recordMailid",recordMailid);  
//				System.out.println("no error   ------");
				if(goDraft){
					this.forward(request,response,"/filetransfer/fromDraftSend.jsp");
				}else{
					this.forward(request, response, "/filetransfer/messageShow.jsp?curPageNum="+curPageNum+"&sortname="+sortname+"&sorttype="+sorttype);
				}
				
			} catch (Exception e) {
				
				System.out.println(e.getMessage());
				e.printStackTrace();
				
				throw new RuntimeException("wrong" + e);
			}finally{
			    if(mailHandler!=null)
				    try {
					    mailHandler.disconnect();
				    } catch (LdapException e1) {
					    e1.printStackTrace();
				    }
				if (conn != null)
				try {
					conn.close();
					ConnLog.close("ShowFileServlet");
				} catch (Exception e2) {
					e2.printStackTrace();
				}
		    }
			
		}


}
