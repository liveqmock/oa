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
		
		
		
		String folderSort = request.getParameter("folder"); //为根目录下的邮箱或自定义文件夹名
		String[] messageid = request.getParameterValues("messageid");

		String type = request.getParameter("type");
		//"system"或"user"或为（null：搜索或未读时）

		String curPageNum = request.getParameter("curPageNum");

		String folderName = folderSort;
		//排序字段名 默认按时间字段 0:按照时间排序 1:按照大小排序
		String sortname = request.getParameter("sortname");
		//排序方式 默认为倒序
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
			//依邮箱设置表，得到准确的邮箱用户名，用来构造邮箱地址
			FiletransferSetHandler ftsHandler =
				new FiletransferSetHandler(conn);
			String username = ftsHandler.getUserid(user.getPersonUuid());


			mailhandler = MailhandlerFactory.getHandler(username);

			String mailhead = "";
			//IntendWork intendHandler = new IntendWork(conn);
			String code = IntendWork.getCodeValue("oa_filetrans");
			if (messageid == null) { //在文件传递首页操作
				//清空垃圾箱或一个自定义文件夹
				
				if ("system".equals(type)) {
					String[][] str = mailhandler.fileheadList("");
					String firstword = "";
					for (int i = 0; i < str[0].length; i++) {
						firstword = str[2][i].substring(0, 1);
						if("Junk".equals(folderName)){
							if (FileTransferConfig.JUNK_FLAG.equals(firstword)) {
								mailhead = str[1][i];
								//删除阅读记录
								fhandler.deleteRecord(
									username,
									mhandler.getIntendSubject(mailhead));
								//删除待办
								fhandler.delIntend(code, mailhead, username);

								String mailpath = str[2][i];
								delMail(mailhandler,mailpath);
							}
						}else if("Sent".equals(folderName)){
							if (FileTransferConfig.SENT_FLAG.equals(firstword)) {
								mailhead = str[1][i];
								//删除阅读记录
								fhandler.deleteRecord(
									username,
									mhandler.getIntendSubject(mailhead));
								//删除待办
								fhandler.delIntend(code, mailhead, username);

								String mailpath = str[2][i];
								delMail(mailhandler,mailpath);
							}
						}else if("Draft".equals(folderName)){
							if (FileTransferConfig.DRA_FLAG.equals(firstword)) {
								mailhead = str[1][i];
								//删除阅读记录
								fhandler.deleteRecord(
									username,
									mhandler.getIntendSubject(mailhead));
								//删除待办
								fhandler.delIntend(code, mailhead, username);

								String mailpath = str[2][i];
								delMail(mailhandler,mailpath);
							}
						}else if("Rec".equals(folderName)){
							if (FileTransferConfig.RECE_FLAG.equals(firstword)) {
								mailhead = str[1][i];
								//删除阅读记录
								fhandler.deleteRecord(
									username,
									mhandler.getIntendSubject(mailhead));
								//删除待办
								fhandler.delIntend(code, mailhead, username);

								String mailpath = str[2][i];
								delMail(mailhandler,mailpath);
							}
						}
						
					}
				} else { //自定义文件夹
					folderName = ftsHandler.encodeBase64(folderName);
					String[][] userStr = mailhandler.fileheadList(folderName);
					for (int j = 0; j < userStr[0].length; j++) {
						mailhead = userStr[1][j];
						//删除阅读记录
						fhandler.deleteRecord(
							username,
							mhandler.getIntendSubject(mailhead));
						//删除待办
						fhandler.delIntend(code, mailhead, username);

						String usermailpath = folderName + "/" + userStr[2][j];
						String desName =
							"t"
								+ userStr[2][j].substring(
									1,
									userStr[2][j].length());
						//移动
						mailhandler.dirRename(usermailpath, desName);
					}
				}

			} else { //选择部份

				if ("user".equals(type)) { //删除一个自定义文件夹中的邮件
					folderName = ftsHandler.encodeBase64(folderName);
					for (int j = 0; j < messageid.length; j++) {
						//删除待办
						String[][] uStr = mailhandler.fileheadList(folderName);
						for (int k = 0; k < uStr[0].length; k++) {
							if (messageid[j].equals(uStr[2][k])) {
								mailhead = uStr[1][k];
								//删除阅读记录
								fhandler.deleteRecord(
									username,
									mhandler.getIntendSubject(mailhead));
								//删除待办
								fhandler.delIntend(code, mailhead, username);
								break;
							}
						}
						//删除文件
						String usermailpath = folderName + "/" + messageid[j];
						delMail(mailhandler,usermailpath);
					}
				} else if ("system".equals(type)) { //删除系统文件夹中的文件
	
					for (int i = 0; i < messageid.length; i++) {
						//删除待办
						String[][] str = mailhandler.fileheadList("");
						for (int u = 0; u < str[0].length; u++) {
							if (messageid[i].equals(str[2][u])) {
								mailhead = str[1][u];
								
//								String flag = str[2][i].substring(0, 1);
//								System.out.println("wwwwwwwwwwwwwwwwwwww  "+flag);
//								System.out.println("wwwwwwwwwwwwwwwwwwww1 "+messageid[i]);
//								System.out.println("wwwwwwwwwwwwwwwwwwww2 "+messageid[i].substring(0,1));    
								//System.out.println("ssssssssssssssssssssss  s2"+mailhead);
								//删除阅读记录
								if("s".equals(messageid[i].substring(0,1))){
									fhandler.deleteRecord(username,mhandler.getIntendSubject(mailhead));
								}
								//删除待办
								fhandler.delIntend(code, mailhead, username);
								break;
							}
						}
						//删除文件
						String mailpath = messageid[i];
						delMail(mailhandler,mailpath);

					}
				} else { //搜索或未读时的删除
					boolean getok = false;
					String espmailpath = "";
					//先在系统文件夹下找
					for (int x = 0; x < messageid.length; x++) {
						//删除待办
						String[][] str = mailhandler.fileheadList("");
						for (int u = 0; u < str[0].length; u++) {
							if (messageid[x].equals(str[2][u])) {
								mailhead = str[1][u];
								//删除阅读记录
								fhandler.deleteRecord(
									username,
									mhandler.getIntendSubject(mailhead));
								//删除待办
								fhandler.delIntend(code, mailhead, username);
								getok = true; //找到
								espmailpath = messageid[x];
								break;
							}
						}
						if (!getok) { //再在用户自定义文件夹下找
							//得到用户自定义的文件夹
							String[] uFolder = mailhandler.dirList("");
							//用户文件夹名称
							for (int y = 0; y < uFolder.length; y++) {
								//删除待办
								String[][] uStr =
									mailhandler.fileheadList(uFolder[y]);
								for (int k = 0; k < uStr[0].length; k++) {
									if (messageid[x].equals(uStr[2][k])) {
										mailhead = uStr[1][k];
										//删除阅读记录
										fhandler.deleteRecord(
											username,
											mhandler.getIntendSubject(
												mailhead));
										//删除待办
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
						//删除文件
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
		if ("noread".equals(folderSort)) { //到未读文件
			this.forward(request, response, "/servlet/ShowNoReadServlet");
		} else if ("searchmail".equals(folderSort)) {
			this.forward(request, response, "/servlet/SearchMailServlet");
		} else if (messageid == null) { //到文件传递首页
			this.forward(request, response, "/servlet/BoxListServlet");
		} else { //到其他文件夹
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
