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
 * 显示文件正文内容
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
				//排序字段名 默认按时间字段 0:按照时间排序 1:按照大小排序
			  	String sortname = request.getParameter("sortname");
				//排序方式 默认为倒序
			  	String sorttype = request.getParameter("sorttype");
				
				String sendMan = "";            //发件人
				List toList = new ArrayList();  //接收人
				List ccList = new ArrayList();  //抄送接收人
				List bccList = new ArrayList(); //密送人
				
				conn = this.getConnection(Globals.DATASOURCEJNDI);
				ConnLog.open("ShowFileServlet");
				ctx = Context.getInstance();
				UserInfo user = ctx.getCurrentLoginInfo();
				//依邮箱设置表，得到准确的邮箱用户名，用来构造邮箱地址
				FiletransferSetHandler ftsHandler = new FiletransferSetHandler(conn);
				String username = ftsHandler.getUserid(user.getPersonUuid());
				mailHandler = MailhandlerFactory.getHandler(username);
				byte[] mailContent = null;
				String check = "";   //判断是否需要回执
				String totalmailName = "";
				String srcName = "";
				String desName = "";
				String isNew = mailName.substring(1,2);
				boolean newMail = false;
				if("n".equals(isNew)){  //为新邮件，则需要改名和检查是否要回执
					newMail = true;
					if("system".equals(type)){
						srcName = mailName;
						desName = mailName.substring(0,1) + "o" + mailName.substring(2,mailName.length());
						check = mailHandler.checkre(mailName);
					}else if("user".equals(type)){   //为自定义文件夹
						folderSort = ftsHandler.encodeBase64(folderSort);
						srcName = folderSort+"/"+mailName;
						desName = folderSort+"/"+mailName.substring(0,1) + "o" + mailName.substring(2,mailName.length());
						check = mailHandler.checkre(folderSort+"/"+mailName);
					}else{    //处理搜索、和未读
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
					//把邮件设为已读标记
					mailHandler.dirRename(srcName,desName);
					//得到新的邮件名
					mailName = mailName.substring(0,1) + "o" + mailName.substring(2,mailName.length());
				}
				if("system".equals(type)){
					mailContent = mailHandler.viewmail(mailName);
					totalmailName = "";
				}else if("user".equals(type)){   //为自定义文件夹
					folderSort = ftsHandler.encodeBase64(folderSort);
					mailContent = mailHandler.viewmail(folderSort+"/"+mailName);
					totalmailName = folderSort+"/";
				}else{    //处理搜索、和未读
					if("inbox".equals(folderSort)){
						mailContent = mailHandler.viewmail(mailName);
						totalmailName = "";
					}else{
						if(!("n".equals(isNew))){  //为新邮件
							folderSort = ftsHandler.encodeBase64(folderSort);
						}
						mailContent = mailHandler.viewmail(folderSort+"/"+mailName);
						totalmailName = folderSort+"/";
					}
				}
				MessageHandler messageHandler = new MessageHandler();
				
				MimeMessage fileMessage = messageHandler.getContentMessage(mailContent);
				String fromAddress = "";
				//用于从草稿箱发送
				HttpSession session = request.getSession();
				SendFileBean.removeSession(session);
				SendFileBean sendFileBean = SendFileBean.getInstanceFromSession(session);
				//发件人中文名
				fromAddress = MimeUtility.decodeText(fileMessage.getFrom()[0].toString());
				sendMan = ftsHandler.getCName(fromAddress.substring(0,fromAddress.indexOf("@")));
				//加接收人
				Address[] toArray = fileMessage.getRecipients(Message.RecipientType.TO);
				toList = ftsHandler.getAddressList(toArray,sendFileBean,"to");
                //加抄送人
                Address[] ccArray = fileMessage.getRecipients(Message.RecipientType.CC);
                ccList = ftsHandler.getAddressList(ccArray,sendFileBean,"cc");
                //加密送人
                Address[] bccArray = fileMessage.getRecipients(Message.RecipientType.BCC);
                bccList = ftsHandler.getAddressList(bccArray,sendFileBean,"bcc");
                
				String[][] str = mailHandler.fileheadList(totalmailName);
				FileTransferHandler handler = new FileTransferHandler(conn);
				//用于删除待办
				String code = IntendWork.getCodeValue("oa_filetrans");
				String fileHead = "";
				for(int i=0;i<str[0].length;i++){
					if(mailName.equals(str[2][i])){
						fileHead = str[1][i];
						//删除待办
						if("n".equals(isNew)){
							handler.delIntend(code,fileHead,username);
						}
						break;
					}
				}
				//发送邮件人
				int fromindex1 = messageHandler.getFrom(fileHead).indexOf("<");
				int fromindex2 = messageHandler.getFrom(fileHead).indexOf("@");
				String fromPerson = messageHandler.getFrom(fileHead).substring(fromindex1+1,fromindex2);
				//（读收到的邮件时）用于写入阅读记录
				Integer recordMailid = null;
				String subject = messageHandler.getIntendSubject(fileHead); //包括时间
				List readPersonlist = new ArrayList();
				String recePerson = "";
				if(newMail==true){
					recordMailid = handler.getRecordMailid(fromPerson.concat(",").concat(subject));
					if(recordMailid!=null){   //添加一条阅读记录
						handler.writeReadPersonuuid(recordMailid,user.getPersonUuid());
					}
//				}else if("".equals(totalmailName)&&"s".equals(mailName.substring(0,1))){
				}else{
					//（读自己保存的邮件时）用于查看阅读记录,得到阅读的人的中文名称(在系统文件夹下的发件箱中的邮件才进入判断）
					recordMailid = handler.getRecordMailid(username.concat(",").concat(subject));
					if(recordMailid!=null){   //需要阅读记录
						readPersonlist = handler.getReadPersonName(recordMailid);
						recePerson = handler.getRecePerson(recordMailid);
					}
				}
			
				//为新邮件并且需要回执，且不是自己发给自己的邮件
				if(newMail==true&&!(check.equals(""))&&(!(username.equals(fromPerson)))){
					String to = fileMessage.getFrom()[0].toString();
					long nowtime = System.currentTimeMillis();
					String resubject = "回执："+messageHandler.getSubject(fileHead)+Long.toString(nowtime);
					String retext = "  您好!\r\n你的邮件 \""+messageHandler.getSubject(fileHead)+"\" 我已收到！";
					mailHandler.sendreceipt(to,resubject,retext,null);
				}
				
				//判断是否是进入草稿箱
				boolean goDraft = false;
				if("Draft".equals(request.getParameter("folder"))){
					goDraft = true;
				}

				String content = "";
				//用来处理当进入草稿箱时，预先写入session
				if(goDraft){
					//取出request里的信息，把附件转移到上载的目录中，并把路径名存入一份到session中，发送时使用
					//每次进入发送文件功能时，从新添加session
					sendFileBean.setMailName(mailName);
					//此处得到的已经是一个包括13位的时间的一个subject
					sendFileBean.setTopic(subject);
					sendFileBean.setType(type);
					sendFileBean.setfolder(request.getParameter("folder"));
					ServletContext context = this.getServletContext();
					sendFileBean = handler.getSessionBean(sendFileBean,fileMessage,context,user.getUserID(),request);
					//存储session
					SendFileBean.saveToSession(session, sendFileBean);
					//保存收件人，抄送人，密送人的SESSION
					HttpSession receSession = request.getSession();
					if(receSession!=null) receSession.removeAttribute("recePersonList");
					List recePersonList = new ArrayList();
					recePersonList.add(toList);
					recePersonList.add(ccList);
					recePersonList.add(bccList);
					recePersonList.add(fileMessage);
					receSession.setAttribute("recePersonList",recePersonList);
				}else{
					if (!fileMessage.isMimeType("text/plain")) { //存储下载路径
						StringBuffer privateDir = new StringBuffer(username);
						privateDir.append(System.currentTimeMillis());
					
					
					

						//下载路径修改 2005-12-28
						//原有程序：
						//ServletContext context = this.getServletContext();
						//StringBuffer path = new StringBuffer(context.getRealPath("/filetransfer/downloadfile/"));
						//修改程序：
						StringBuffer path = new StringBuffer(FiletransferUtil.getDownloadFilePath());
						
					
					

						String filepath = handler.getfilepath(path,privateDir.toString());
					
						//处理附件邮件
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
