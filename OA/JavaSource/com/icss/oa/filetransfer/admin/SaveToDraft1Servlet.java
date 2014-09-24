/*
 * Created on 2004-12-15
 *
 * 
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.icss.oa.filetransfer.admin;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.util.Enumeration;
import java.util.List;
import java.util.StringTokenizer;

import javax.mail.SendFailedException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.redflaglinux.ldap.LdapException;
import org.redflaglinux.services.dir.dirmanage;

import com.icss.common.log.ConnLog;
import com.icss.j2ee.servlet.ServletBase;
import com.icss.j2ee.util.FileUtil;
import com.icss.j2ee.util.Globals;
import com.icss.oa.config.FileTransferConfig;
import com.icss.oa.filetransfer.handler.FileTransferHandler;
import com.icss.oa.filetransfer.handler.FiletransferSetHandler;
import com.icss.oa.filetransfer.util.FiletransferUtil;
import com.icss.oa.filetransfer.util.MailhandlerFactory;
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
public class SaveToDraft1Servlet extends ServletBase {

	protected void performTask(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String topic = request.getParameter("topic");
		String content = request.getParameter("content");
		String sendcc = request.getParameter("sendcc");
		String sendbcc = request.getParameter("sendbcc");

		boolean send_flag = false;
		boolean cc_flag = false;
		boolean bcc_flag = false;

		// �ֱ�õ������˵���Ϣ
		String s1 = request.getParameter("addPerson_person");
		String s2 = request.getParameter("addPerson_group");
		String s3 = request.getParameter("addPerson_org");

		send_flag = ((s1 != null && !"null".equals(s1) && !"".equals(s1))
				|| (s2 != null && !"null".equals(s2) && !"".equals(s2)) || (s3 != null
				&& !"null".equals(s3) && !"".equals(s3)));

		// �ֱ�õ������˵���Ϣ
		String s4 = request.getParameter("addcc_person");
		String s5 = request.getParameter("addcc_group");
		String s6 = request.getParameter("addcc_org");

		cc_flag = ((s4 != null && !"null".equals(s4) && !"".equals(s4))
				|| (s5 != null && !"null".equals(s5) && !"".equals(s5)) || (s6 != null
				&& !"null".equals(s6) && !"".equals(s6)));

		// �ֱ�õ������˵���Ϣ
		String s7 = request.getParameter("addbcc_person");
		String s8 = request.getParameter("addbcc_group");
		String s9 = request.getParameter("addbcc_org");

		bcc_flag = ((s7 != null && !"null".equals(s7) && !"".equals(s7))
				|| (s8 != null && !"null".equals(s8) && !"".equals(s8)) || (s9 != null
				&& !"null".equals(s9) && !"".equals(s9)));

		dirmanage mailhandler = null;
		String senddomain = PropertyManager.getProperty("archivesdomain");

		Connection conn = null;
		Context ctx = null;
		StringBuffer sendResult = new StringBuffer();
		StringBuffer noPersonResult = new StringBuffer();

		String subject = "";
		String text = "";
		String[] to = null;
		String[] cc = null;
		String[] bcc = null;
		String[] attachment = null;
		int flag = FileTransferConfig.TO_DRAFT;

		String originUserAddress = "";
		String ccAddress = "";
		String bccAddress = "";

		request.setAttribute("title1", "�ļ�����");
		request.setAttribute("title2", "���ͽ��");
		InputStream ins = null;
		try {
			conn = this.getConnection(Globals.DATASOURCEJNDI);
			ConnLog.open("SaveToDraftServlet");
			FileTransferHandler handler = new FileTransferHandler(conn);
			// ȡ�÷�������Ϣ
			ctx = Context.getInstance();
			UserInfo user = ctx.getCurrentLoginInfo();
			String chinaName = user.getCnName(); // ������
			// ���������ñ��õ�׼ȷ�������û������������������ַ
			FiletransferSetHandler ftsHandler = new FiletransferSetHandler(conn);
			String userid = ftsHandler.getUserid(user.getPersonUuid());
			mailhandler = MailhandlerFactory.getHandler(userid);
			long nowtime = System.currentTimeMillis();
			// ���͵ı���Ϊ������Ϸ��͵�ʱ�䴮��Ϊɾ�����������
			subject = topic.concat(",").concat(Long.toString(nowtime));
			// ��ʽ���ļ����ݸ�Ҫ
			text = CommUtil.formathtm(content);
			// ȡ�ø�����Ϣ
			// long mailmemory = 0; //Ҫ���͵��ʼ��Ĵ�С
			// HttpSession session = request.getSession();
			// SendFileBean sendFileBean =
			// SendFileBean.getInstanceFromSession(session);
			// if (sendFileBean.filenumber() == 0) {
			// mailmemory = 1536; //Ϊ�ֽڣ��޸���ʱĬ��Ϊ1.5K
			// } else {
			// mailmemory = 1536 + sendFileBean.getTotleFilesize(); //�и���ʱ�Ĵ�С
			// attachment = handler.getAttachNames(sendFileBean);
			// }

			// ȡ�ø�����Ϣ
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

			// ȡ���Ѵ��ڸ���
			Enumeration en = request.getParameterNames();
			while (en.hasMoreElements()) {
				String temp = (String) en.nextElement();
				if (temp.indexOf("exist_") >= 0) {
					j = j + 1;
					attachment[j] = (String) request.getParameter(temp);
				}
			}

			// �õ��ɷ��͵��û���ַ
			StringBuffer noAddressPerson = new StringBuffer();

			List sendlist = handler.getSendtoAddress0830(s1, s2, s3, "",
					senddomain);

			// �����˵�ַ
			originUserAddress = sendlist.get(1).toString();

			StringTokenizer toList = new StringTokenizer(originUserAddress
					.toString().concat(","), ",");
			int tokenLength = toList.countTokens();
			to = new String[tokenLength];
			for (int x = 0; x < tokenLength; x++) {
				to[x] = toList.nextToken();
			}

			// �õ������û���ַ
			if (cc_flag) {
				List sendcclist = handler.getSendtoAddress0830(s4, s5, s6, "",
						senddomain);

				if (!(sendcclist.get(0).toString().equals(""))) {
					noAddressPerson.append(sendcclist.get(0).toString());
				}
				ccAddress = sendcclist.get(1).toString();
				StringTokenizer ccList = new StringTokenizer(ccAddress
						.concat(","), ",");
				int cckenLength = ccList.countTokens();
				cc = new String[cckenLength];
				for (int y = 0; y < cckenLength; y++) {
					cc[y] = ccList.nextToken();
				}
			}
			// �õ������û���ַ
			if (bcc_flag) {
				List sendbcclist = handler.getSendtoAddress0830(s7, s8, s9, "",
						senddomain);

				if (!(sendbcclist.get(0).toString().equals(""))) {
					noAddressPerson.append(sendbcclist.get(0).toString());
				}
				bccAddress = sendbcclist.get(1).toString();
				StringTokenizer bccList = new StringTokenizer(bccAddress
						.concat(","), ",");
				int bcckenLength = bccList.countTokens();
				bcc = new String[bcckenLength];
				for (int z = 0; z < bcckenLength; z++) {
					bcc[z] = bccList.nextToken();
				}
			}

			// �ɷ���
			StringBuffer ReciAddress = new StringBuffer();
			ReciAddress.append(originUserAddress);
			if (!("".equals(ccAddress))) {
				ReciAddress.append(",").append(ccAddress);
			}
			if (!("".equals(bccAddress))) {
				ReciAddress.append(",").append(bccAddress);
			}

			// //�Ƿ���Ҫ���浽������
			// String isSent = request.getParameter("isSent");
			// if("checked2".equals(isSent)){
			// handler.saveToSent(mailhandler,request.getContextPath(),mailmemory,userid,chinaName,senddomain,
			// subject,text,attachment,flag,ReciAddress.toString());
			// }

			if (noAddressPerson.length() > 0) {
				StringTokenizer noAddress = new StringTokenizer(noAddressPerson
						.toString(), ",");
				while (noAddress.hasMoreTokens()) {
					noPersonResult.append(noAddress.nextToken()).append(",");
				}
			}

			if (ReciAddress.length() > 0) {
				System.out.println("aaa1:" + subject);
				System.out.println("aaa2:" + text);
				for (int i = 0; i < to.length; i++) {
					System.out.println("sss:" + to[i]);
				}
				System.out.println("sss:" + flag);
				String[][] failuser = mailhandler.transfermail(subject, text,
						to, cc, bcc, attachment, flag);
				System.out.println("bbb");
				StringTokenizer yesList = new StringTokenizer(ReciAddress
						.toString(), ",");
				while (yesList.hasMoreTokens()) {
					String yesUserID = yesList.nextToken();
					sendResult.append(
							ftsHandler.getCName(yesUserID.substring(0,
									yesUserID.indexOf("@")))).append(",");
				}
			}

			// request.setAttribute("sendResult",sendResult.toString());

			// request.setAttribute("noPersonResult",noPersonResult.toString());

			response.sendRedirect("MailRefreshServlet?type=1");

		} catch (SendFailedException e) {
			e.printStackTrace();
			response.sendRedirect("MailRefreshServlet?type=1");
		} catch (IOException e) {
			e.printStackTrace();
			response.sendRedirect("MailRefreshServlet?type=1");
		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect("MailRefreshServlet?type=1");
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
					ConnLog.close("SaveToDraftServlet");
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
			// ɾ���������ϵ��ݴ渽���������ϴ��ģ�
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
		}// try
	}

}
