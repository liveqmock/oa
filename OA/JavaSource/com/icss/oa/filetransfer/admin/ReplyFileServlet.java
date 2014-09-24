/*
 * Created on 2004-5-11
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.icss.oa.filetransfer.admin;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.util.List;
import java.util.StringTokenizer;

import javax.mail.SendFailedException;
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
import com.icss.oa.config.FileTransferConfig;
import com.icss.oa.filetransfer.handler.FileTransferHandler;
import com.icss.oa.filetransfer.handler.FiletransferSetHandler;
import com.icss.oa.filetransfer.handler.MessageHandler;
import com.icss.oa.filetransfer.handler.SendFileBean;
import com.icss.oa.filetransfer.util.FiletransferUtil;
import com.icss.oa.filetransfer.util.FindMailRun;
import com.icss.oa.filetransfer.util.MailhandlerFactory;
import com.icss.oa.filetransfer.util.ThreadsPool;
import com.icss.oa.util.CommUtil;
import com.icss.oa.util.PropertyManager;
import com.icss.oa.util.StringUtility;
import com.icss.resourceone.common.logininfo.UserInfo;
import com.icss.resourceone.sdk.framework.Context;

/**
 * @author Administrator
 * 
 *         显示文件正文内容
 */
public class ReplyFileServlet extends ServletBase {

	protected void performTask(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String mailpath = "";
		StringBuffer sendResult = new StringBuffer();
		StringBuffer overFlowResult = new StringBuffer();

		String topic = request.getParameter("topic");
		String content = request.getParameter("content");

		Connection conn = null;
		Context ctx = null;
		dirmanage mailhandler = null;

		String subject = "";
		String text = "";
		String[] to = null;
		String[] attachment = null;
		int flag = FileTransferConfig.ONLY_SEND;
		InputStream ins = null;
		try {

			conn = this.getConnection(Globals.DATASOURCEJNDI);
			ConnLog.open("ReplyFileServlet");
			String domain = PropertyManager.getProperty("archivesdomain");
			String ip = PropertyManager.getProperty("archivesip");

			String senddomain = domain;
			String userid = "";
			ctx = Context.getInstance();
			UserInfo user = ctx.getCurrentLoginInfo();
			String chinaName = user.getCnName();
			// 依邮箱设置表，得到准确的邮箱用户名，用来构造邮箱地址
			FiletransferSetHandler ftsHandler = new FiletransferSetHandler(conn);
			String username = "";
			try {
				username = ftsHandler.getUserid(user.getPersonUuid());
				userid = ftsHandler.getUserid(user.getPersonUuid());

			} catch (Exception e) {
				this.forward(request, response, "/filetransfer/noMailBox.jsp");
			}
			long nowtime = System.currentTimeMillis();
			// 发送的标题为标题加上发送的时间串，为删除待办滚动用
			subject = topic.concat(",").concat(Long.toString(nowtime));
			// 格式化文件内容概要
			text = CommUtil.formathtm(content);

			HttpSession session = request.getSession();
			SendFileBean sendFileBean = SendFileBean
					.getInstanceFromSession(session);

			String folderSort = sendFileBean.getfolder();

			// 当为时，表示为搜索或未读时候的转发
			if ("inbox".equals(folderSort) || "Inbox".equals(folderSort)
					|| "Sent".equals(folderSort) || "Draft".equals(folderSort)
					|| "Junk".equals(folderSort)) {
				mailpath = "/"; // 为根目录下
			} else if (folderSort == null || "null".equals(folderSort.trim())) {
				mailpath = "/";
			} else {
				mailpath = ftsHandler.encodeBase64(folderSort) + "/";
			}

			// System.out.println("My Test:"+folderSort);
			// System.out.println("My Test1:"+mailpath);

			FileTransferHandler handler = new FileTransferHandler(conn);

			mailhandler = MailhandlerFactory.getHandler(username);
			String[][] str = mailhandler.fileheadList(mailpath);

			String mailName = sendFileBean.getMailName();
			String fileHead = "";
			for (int i = 0; i < str[0].length; i++) {
				if (mailName.equals(str[2][i])) {
					fileHead = str[1][i];
					break;
				}
			}

			// 取得附件信息
			// long mailmemory = 0; //要发送的邮件的大小
			// if (sendFileBean.filenumber() == 0) {
			// mailmemory = 1536; //为字节，无附件时默认为1.5K
			// } else {
			// mailmemory = 1536 + sendFileBean.getTotleFilesize(); //有附件时的大小
			// attachment = handler.getAttachNames(sendFileBean);
			// }

			long mailmemory = 0;
			String filenum = request.getParameter("filenum");
			String realnum = request.getParameter("realnum");

			if (filenum == null || "".equals(filenum)) {
				filenum = "0";
			}
			if (realnum == null || "".equals(realnum)) {
				realnum = "0";
			}
			StringBuffer path = new StringBuffer(FiletransferUtil
					.getUploadFilePath());
			path.append(username).append(System.currentTimeMillis()).append(
					File.separator);
			File uploadpath = new File(path.toString());
			if (!uploadpath.exists()) {
				uploadpath.mkdirs();
			}
			if (!"0".equals(realnum)) {
				attachment = new String[Integer.parseInt(realnum)];
			}
			int j = -1;
			for (int i = 0; i < Integer.parseInt(filenum); i++) {
				String filename = "";
				String compname = "file_" + i;
				String fileFillName = getUploadFileFullName(request, compname);
				if ("null".equals(fileFillName.substring(fileFillName
						.lastIndexOf("/") + 1))) {
					continue;
				}
				j = j + 1;
				File tempf = new File(fileFillName);
				mailmemory = mailmemory + tempf.length();

				filename = getUploadOldFileName(request, compname);
				if (filename != null) {
					filename = StringUtility.CtoB(filename);
				}
				int index = 0;
				index = filename.lastIndexOf("\\");
				if (index != -1) {
					filename = filename.substring(index + 1);
				}
				String filepath = path.toString() + filename;
				ins = new FileInputStream(fileFillName);
				FileUtil.copy(ins, new File(filepath));
				attachment[j] = filepath;
			}

			MessageHandler mHandler = new MessageHandler();
			// 得到可发送的用户地址
			StringBuffer noAddressPerson = new StringBuffer();
			String originUserAddress = mHandler.getReplyTo(fileHead);
			if (!handler.isMailUser(originUserAddress)) { // 原发件人已经不是邮箱用户了
				int atindex = originUserAddress.indexOf("@");
				String CName = ftsHandler.getCName(originUserAddress.substring(
						0, atindex));
				noAddressPerson.append(CName);
			} else { // 原发件人还是邮箱用户
				List sendAddress = handler.confirmOverflow(request
						.getContextPath(), mailmemory, originUserAddress,
						chinaName, username);
				String canSendAddress = (String) sendAddress.get(0);
				to = new String[1];
				to[0] = canSendAddress;
				String cannotSendAddress = (String) sendAddress.get(1);

				// 可发送
				StringBuffer ReciAddress = new StringBuffer();
				ReciAddress.append(canSendAddress);

				// 不可发送
				StringBuffer NoReciAddress = new StringBuffer();
				NoReciAddress.append(cannotSendAddress);

				// 是否设置回执
				String isRe = request.getParameter("isRe");
				String isSent = request.getParameter("isSent");
				if ("checked1".equals(isRe) && "checked2".equals(isSent)) {

					flag = FileTransferConfig.TO_SEND_RE;

				} else if (!"checked1".equals(isRe)
						&& "checked2".equals(isSent)) {

					flag = FileTransferConfig.TO_SEND;

				} else if ("checked1".equals(isRe)
						&& !"checked2".equals(isSent)) {

					flag = FileTransferConfig.SEND_RE;
				}
				// 是否需要保存到发件箱

				if ("checked2".equals(isSent)) {
					handler.saveToSent(mailhandler, request.getContextPath(),
							mailmemory, username, chinaName, senddomain,
							subject, text, attachment, flag, ReciAddress
									.toString());
				}

				// if(NoReciAddress.length()>0){
				// StringTokenizer noList = new
				// StringTokenizer(NoReciAddress.toString(),",");
				// while (noList.hasMoreTokens()) {
				// String failAddress = noList.nextToken();
				// overFlowResult.append(ftsHandler.getCName(failAddress.substring(0,failAddress.indexOf("@")))).append(",");
				// }
				// }
				if (ReciAddress.length() > 0) {

					String[][] failuser = mailhandler.transfermail(subject,
							text, to, null, null, attachment, flag);

					StringTokenizer yesList = new StringTokenizer(ReciAddress
							.toString(), ",");
					while (yesList.hasMoreTokens()) {
						String yesUserID = yesList.nextToken();
						sendResult.append(
								ftsHandler.getCName(yesUserID.substring(0,
										yesUserID.indexOf("@")))).append(",");

						// 加入到待办
						int yesindex = yesUserID.indexOf("@");
						yesUserID = yesUserID.substring(0, yesindex);
						String intendtopic = "新邮件，来自" + chinaName + "（"
								+ CommUtil.getTime(System.currentTimeMillis())
								+ "）";
						String tointendId = username + "," + subject + ","
								+ yesUserID;
						// handler.addToIntend(request.getContextPath(),yesUserID,intendtopic,tointendId);
						Thread t = new Thread(new FindMailRun(userid, subject,
								nowtime, intendtopic, tointendId, yesUserID,
								request.getContextPath()));
						ThreadsPool.getInstance().putTask(t);
					}
				}

			}// else

			request.getSession().setAttribute("sendResult",
					sendResult.toString());
			response.sendRedirect("MailRefreshServlet");
			//forward(request,response,"/oabase/mail/SendMail_Body.jsp?sun_flag=err");
		} catch (SendFailedException e) {
			e.printStackTrace();
			request.getSession().setAttribute("failResult", "邮件回复失败！");
			response.sendRedirect("MailRefreshServlet");

		} catch (Exception e) {
			e.printStackTrace();
			request.getSession().setAttribute("failResult", "其他错误！");
			response.sendRedirect("MailRefreshServlet");
			throw new RuntimeException("wrong" + e);
		} finally {
			try {
				if (ins != null)
					ins.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			if (mailhandler != null)
				try {
					mailhandler.disconnect();
				} catch (LdapException e1) {
					e1.printStackTrace();
				}
			if (conn != null)
				try {
					conn.close();
					ConnLog.close("ReplyFileServlet");
				} catch (Exception e2) {
					e2.printStackTrace();
				}

			// 删除服务器上的暂存附件（用于上传的）
			if (attachment != null && attachment.length > 0) {
				for (int attachnum = 0; attachnum < attachment.length; attachnum++) {
					int pathindex = attachment[attachnum]
							.lastIndexOf(File.separator);
					String privatedir = attachment[attachnum].substring(0,
							pathindex);
					File attachfile = new File(attachment[attachnum]);
					attachfile.delete();
					File dirfile = new File(privatedir);
					dirfile.delete();
				}
			}
		}

	}

}
