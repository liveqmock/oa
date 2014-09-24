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
		
		//排序字段名 默认按时间字段 0:按照时间排序 1:按照大小排序
		String sortname = request.getParameter("sortname");
		//排序方式 默认为倒序
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
			//依邮箱设置表，得到准确的邮箱用户名，用来构造邮箱地址
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

			//移动部份文件
			for (int i = 0; i < messageid.length; i++) {

				String srcName = "";
				String desName = "";
				String mark = "";
				//为移到根目录下
				if ("Inbox".equals(targetfolder)) //收件箱
					mark = FileTransferConfig.RECE_FLAG;
				else if ("Sent".equals(targetfolder)) //发件箱
					mark = FileTransferConfig.SENT_FLAG;
				else if ("Draft".equals(targetfolder)) //草稿箱
					mark = FileTransferConfig.DRA_FLAG;
				else if ("Junk".equals(targetfolder)) //垃圾箱
					mark = FileTransferConfig.JUNK_FLAG;
				else
					//为移到自定义文件夹
					mark = "";

				if ("user".equals(type)) { //为用户自定义文件夹move到别处

					//删除待办
					for (int j = 0; j < userStr[0].length; j++) {
						if (messageid[i].equals(userStr[2][j])) {
							mailhead = userStr[1][j];
							handler.delIntend(code, mailhead, username);
						}
					}

					srcName = folderSort + "/" + messageid[i];
					if ("".equals(mark)) { //移动到自定义文件夹
						targetfolder2 = ftsHandler.encodeBase64(targetfolder);
						desName = targetfolder2;
						//移动
						mailhandler.dirmove(srcName, desName);
					} else {
						desName = mark
								+ messageid[i].substring(1, messageid[i]
										.length());
						//移动
						mailhandler.dirRename(srcName, desName);
					}

				} else { //由根目录移到别处

					//删除待办
					for (int j = 0; j < userStr[0].length; j++) {
						if (messageid[i].equals(userStr[2][j])) {
							mailhead = userStr[1][j];
							handler.delIntend(code, mailhead, username);
						}
					}

					srcName = messageid[i];
					if ("".equals(mark)) { //移动到自定义文件夹
						targetfolder2 = ftsHandler.encodeBase64(targetfolder);
						desName = targetfolder2;
						mailhandler.dirmove(srcName, desName);
					} else {
						desName = mark
								+ messageid[i].substring(1, messageid[i]
										.length());
						//移动
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
