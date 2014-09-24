/*
 * Created on 2004-12-16
 *
 * 
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.icss.oa.filetransfer.admin;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.sql.Connection;
import java.util.Enumeration;
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
 * @author firecoral
 * 
 * 
 *         Window - Preferences - Java - Code Style - Code Templates
 */
public class SendFromDraftServlet extends ServletBase {

	/*
	 * (non-Javadoc)
	 * 
	 * @seecom.icss.j2ee.servlet.ServletBase#performTask(javax.servlet.http.
	 * HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	protected void performTask(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		StringBuffer sendResult = new StringBuffer();
		Context loginctx = null;
		Connection conn = null;

		dirmanage mailhandler = null;
		String domain = PropertyManager.getProperty("archivesdomain");
		String subject = "";
		String text = "";
		String content = request.getParameter("content");
		String[] to = null;
		String[] cc = null;
		String[] bcc = null;
		String[] attachment = null;
		int flag = FileTransferConfig.ONLY_SEND;
		InputStream ins = null;
		try {

			loginctx = Context.getInstance();
			UserInfo user = loginctx.getCurrentLoginInfo();
			String chinaName = user.getCnName();

			conn = this.getConnection(Globals.DATASOURCEJNDI);
			ConnLog.open("SendFromDraftServlet");
			// 依邮箱设置表，得到准确的邮箱用户名，用来构造邮箱地址
			FiletransferSetHandler ftsHandler = new FiletransferSetHandler(conn);
			String userid = ftsHandler.getUserid(user.getPersonUuid());

			mailhandler = MailhandlerFactory.getHandler(userid);

			HttpSession session = request.getSession();

			String newtopic = request.getParameter("topic");

			SendFileBean sendFileBean = SendFileBean
					.getInstanceFromSession(session);
			long nowtime = System.currentTimeMillis();
			subject = sendFileBean.getTopic();

			subject = newtopic
					+ subject.substring(subject.lastIndexOf(","), subject
							.length());

			// 给要转发的邮件换一个新的标题（换掉后13位，在服务器上不同，用户看的仍然相同）
			subject = subject.substring(0, subject.length() - 13).concat(
					Long.toString(nowtime));

			// 格式化文件内容概要
			text = CommUtil.formathtm(content);
			// 取得附件信息
			// long mailmemory = 0; //要发送的邮件的大小
			// 得到所有附件的文件名（数组）
			FileTransferHandler handler = new FileTransferHandler(conn);
			// if (sendFileBean.filenumber() == 0) {
			// mailmemory = 1536; //为字节，无附件时默认为1.5K
			// } else {
			// mailmemory = 1536 + sendFileBean.getTotleFilesize(); //有附件时的大小
			// attachment = handler.getAttachNames(sendFileBean);
			// }
			// mailmemory = 1536 + sendFileBean.getTotleFilesize(); //有附件时的大小

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
			path.append(userid).append(System.currentTimeMillis()).append(
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

			// 取得已存在附件
			Enumeration en = request.getParameterNames();
			while (en.hasMoreElements()) {
				String temp = (String) en.nextElement();
				if (temp.indexOf("exist_") >= 0) {
					j = j + 1;
					attachment[j] = (String) request.getParameter(temp);
				}
			}

			// 得到可发送的用户地址
			String originUserAddress = sendFileBean.getSendto();

			StringTokenizer toList = new StringTokenizer(originUserAddress
					.toString(), ",");
			int toLength = toList.countTokens();
			to = new String[toLength];
			for (int x = 0; x < toLength; x++) {
				to[x] = toList.nextToken();
			}

			String ccAddress = sendFileBean.getSendcc();

			StringTokenizer ccList = new StringTokenizer(ccAddress.toString(),
					",");
			int cckenLength = ccList.countTokens();
			cc = new String[cckenLength];
			for (int y = 0; y < cckenLength; y++) {
				cc[y] = ccList.nextToken();
			}

			String bccAddress = sendFileBean.getSendbcc();

			StringTokenizer bccList = new StringTokenizer(bccAddress.toString()
					.concat(","), ",");
			int bcckenLength = bccList.countTokens();
			bcc = new String[bcckenLength];
			for (int z = 0; z < bcckenLength; z++) {
				bcc[z] = bccList.nextToken();
			}

			// send + cc + bcc
			String allsendlist = originUserAddress + "," + ccAddress + ","
					+ bccAddress;

			String isRe = request.getParameter("isRe");
			String isSent = request.getParameter("isSent");

			if ("checked1".equals(isRe) && "checked2".equals(isSent)) {

				flag = FileTransferConfig.TO_SEND_RE;

			} else if (!"checked1".equals(isRe) && "checked2".equals(isSent)) {

				flag = FileTransferConfig.TO_SEND;

			} else if ("checked1".equals(isRe) && !"checked2".equals(isSent)) {

				flag = FileTransferConfig.SEND_RE;
			}

			if (allsendlist.length() > 0) {
				if ("checked2".equals(isSent)) {
					handler.saveToSent(mailhandler, request.getContextPath(),
							mailmemory, userid, chinaName, domain, subject,
							text, attachment, flag, allsendlist.toString());
				}
				String[][] failuser = mailhandler.transfermail(subject, text,
						to, cc, bcc, attachment, flag);

				StringTokenizer yesList = new StringTokenizer(allsendlist
						.toString(), ",");
				while (yesList.hasMoreTokens()) {
					String yesUserID = yesList.nextToken();
					sendResult.append(
							ftsHandler.getCName(yesUserID.substring(0,
									yesUserID.indexOf("@")))).append(",");
					int yesindex = yesUserID.indexOf("@");
					yesUserID = yesUserID.substring(0, yesindex);
					String intendtopic = "新邮件，来自" + chinaName + "（"
							+ CommUtil.getTime(System.currentTimeMillis())
							+ "）";
					String tointendId = userid + "," + sendFileBean.getTopic()
							+ "," + yesUserID;
					Thread t = new Thread(new FindMailRun(userid, subject,
							nowtime, intendtopic, tointendId, yesUserID,
							request.getContextPath()));
					ThreadsPool.getInstance().putTask(t);
				}
			}

			if (!conn.isClosed())
				conn.close();

			// request.setAttribute("sendResult",sendResult.toString());
			// request.setAttribute("title1","文件转发");
			// request.setAttribute("title2","发送结果");

			request.getSession().setAttribute("sendResult",
					sendResult.toString());
			response.sendRedirect("MailRefreshServlet");
			//forward(request,response,"/oabase/mail/SendMail_Body.jsp?sun_flag=err");
		} catch (SendFailedException e) {
			request.getSession().setAttribute("failResult", "发送失败！");
			response.sendRedirect("MailRefreshServlet");
		} catch (Exception e) {
			request.getSession().setAttribute("failResult", "其他错误导致发送失败！");
			response.sendRedirect("MailRefreshServlet");
		} finally {
			try {
				if (ins != null)
					ins.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			if (conn != null)
				try {
					conn.close();
					ConnLog.close("SendFromDraftServlet");
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
		}// finally

	}

}
