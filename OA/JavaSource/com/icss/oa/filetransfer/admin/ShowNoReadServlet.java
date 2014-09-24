/*
 * Created on 2004-6-29
 *
 */
package com.icss.oa.filetransfer.admin;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.mail.MessagingException;
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
import com.icss.oa.util.PropertyManager;
import com.icss.resourceone.common.logininfo.UserInfo;
import com.icss.resourceone.sdk.framework.Context;

/**
 * @author firecoral
 * 
 * 
 */
public class ShowNoReadServlet extends ServletBase {

	/*
	 * (non-Javadoc)
	 * 
	 * @seecom.icss.j2ee.servlet.ServletBase#performTask(javax.servlet.http.
	 * HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	protected void performTask(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		Connection conn = null;
		dirmanage mailhandler = null;
		Context ctx = null;

		try {

			conn = this.getConnection(Globals.DATASOURCEJNDI);
			ConnLog.open("ShowNoReadServlet");
			ctx = Context.getInstance();
			UserInfo user = ctx.getCurrentLoginInfo();
			// ���������ñ��õ�׼ȷ�������û������������������ַ
			FiletransferSetHandler ftsHandler = new FiletransferSetHandler(conn);
			String username = ftsHandler.getUserid(user.getPersonUuid());
			ctx.close();

			if (conn != null) {
				try {
					conn.close();
					ConnLog.close("ShowNoReadServlet");
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}

			if (username == null) {
				this.forward(request, response, "/filetransfer/noMailBox.jsp");
			}
			mailhandler = MailhandlerFactory.getHandler(username);
			String[][] str = mailhandler.fileheadList("");

			String secondword = "";

			// str[0].lengthΪ�ʼ��ĸ���

			List noReadList = new ArrayList();
			List reList = new ArrayList();
			List rootList = new ArrayList();
			List userList = new ArrayList();
			String sendword = new String();

			for (int i = 0; i < str[0].length; i++) {
				secondword = str[2][i].substring(1, 2);
				sendword = str[2][i].substring(0, 1);
				// if(secondword.equals("n")){
				if (secondword.equals("n")
						&& !(FileTransferConfig.SENT_FLAG).equals(sendword)
						&& !(FileTransferConfig.JUNK_FLAG).equals(sendword)
						&& !(FileTransferConfig.DRA_FLAG).equals(sendword)) {
					List tempList = new ArrayList();
					tempList.add("inbox");
					for (int j = 0; j < 3; j++) {
						tempList.add(str[j][i]);
					}
					rootList.add(tempList);
				}
			}

			// �õ��û��Զ�����ļ���
			String[] uFolder = mailhandler.dirList("");
			// �û��ļ�������
			for (int i = 0; i < uFolder.length; i++) {
				// �õ��û��Զ����ļ����е����ʼ�
				String[][] userStr = mailhandler.fileheadList(uFolder[i]);
				for (int j = 0; j < userStr[0].length; j++) {
					secondword = userStr[2][j].substring(1, 2);
					if (secondword.equals("n")) {
						List tempList = new ArrayList();
						tempList.add(ftsHandler.decodeBase64(uFolder[i]));
						for (int k = 0; k < 3; k++) {
							tempList.add(userStr[k][j]);
						}
						userList.add(tempList);
					}
				}

			}

			MessageHandler mhandler = new MessageHandler();
			reList = mhandler.ListCombine(rootList, userList);

			// reList = this.getOrderList(reList);
			FileTransferHandler ftHandler = new FileTransferHandler();
			reList = ftHandler.OrderByTimeDes(reList);

			// /////////////////////////���ڷ�ҳ/////////////////////
			// һҳ��������¼
			int rowcount = Integer.parseInt(this
					.getInitParameter("_mailcount_per_page"));
			// int rowcount = 5;
			// ��ǰҳ��
			int curPageNum = 0;
			String currentNum = request.getParameter("curPageNum");
			if (currentNum != null) {
				curPageNum = Integer.parseInt(request
						.getParameter("curPageNum"));
			} else {
				curPageNum = 1; // ��һ�ε�JSPΪ��һҳ
			}
			// ���ж���ҳ
			int pagecount = 0;
			if (reList.size() % rowcount == 0)
				pagecount = reList.size() / rowcount;
			else
				pagecount = reList.size() / rowcount + 1;
			// ���ж�������¼
			int totalcount = reList.size();

			// /////////////////////////////////////////////////////////
			int startNum = (curPageNum - 1) * rowcount; // ��ʼ��������LIST�е���ţ�
			int endcount = startNum + rowcount;
			if (endcount > totalcount) {
				endcount = totalcount;
			}
			for (int k = startNum; k < endcount; k++) {
				noReadList.add(reList.get(k));
			}

			request.setAttribute("listOfMessages", noReadList);

			// /////////////////////////���ڷ�ҳ/////////////////////
			request.setAttribute("curPageNum", new Integer(curPageNum));
			request.setAttribute("pagecount", new Integer(pagecount));
			request.setAttribute("totalcount", new Integer(totalcount));

			this.forward(request, response, "/mail/NoReadMailList_Body.jsp");

		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("wrong " + e);

		} finally {
			if (mailhandler != null) {
				try {
					mailhandler.disconnect();
				} catch (LdapException e1) {
					e1.printStackTrace();
				}
			}

			if (conn != null) {
				try {
					conn.close();
					ConnLog.close("ShowNoReadServlet");
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}

		}
	}

	private List getOrderList(List oldList) {
		// ��List�е��ʼ���ʱ��Ӿɵ��½���ʱ���������
		List newStr = new ArrayList();
		String[][] timeStr = new String[4][oldList.size()];
		for (int m = 0; m < oldList.size(); m++) {
			for (int n = 0; n < 4; n++) {
				timeStr[n][m] = ((List) oldList.get(m)).get(n).toString();
			}
		}
		FileTransferHandler ftHandler = new FileTransferHandler();
		try {
			newStr = ftHandler.OrderByTime(timeStr);
		} catch (MessagingException e1) {
			e1.printStackTrace();
		}
		return newStr;
	}

	private List OrderByTimeDes(List olderlist) throws MessagingException {

		List newlist = new ArrayList();
		java.util.Iterator oItr = olderlist.iterator();

		int i = 0;
		while (oItr.hasNext()) {

			String[] onemail = (String[]) oItr.next();
			String mailHead = (String) onemail[2];
			MessageHandler mHandler = new MessageHandler();
			Date reDate = mHandler.getReceiveDate(mailHead);
			long retime = 0;
			if (reDate != null)
				retime = reDate.getTime();
			if (i == 0) {
				newlist.add(onemail);
				i++;
			} else {
				// һ��ѭ��������һ���ʼ���¼
				int listLength = newlist.size();
				for (int k = 0; k < listLength; k++) {
					// Ϊ�ҵ�һ��ʱ��С��Ҫ�����������¼��ʱ��
					String[] temp = (String[]) newlist.get(k);
					String compHead = temp[2];
					long compTime = 0;
					if (mHandler.getReceiveDate(compHead) != null)
						compTime = mHandler.getReceiveDate(compHead).getTime();

					if (retime > compTime) {
						// ���¼�¼д��һ��LIST
						String[] tempList1 = null;
						String[] tempList2 = null;

						tempList1 = onemail;
						// ����������
						for (int m = k; m < listLength; m++) {
							// exchange to set
							tempList2 = (String[]) newlist.get(m);
							newlist.set(m, tempList1);
							tempList1 = tempList2;
						}
						newlist.add(tempList1);
						break;
					} else {
						if (k == listLength - 1) {
							// С�����һ����ʱ�䣬ֱ�Ӽ���ĩβ
							newlist.add(onemail);
						}
					}
				}// for
			}// if

		}// while
		return newlist;
	}

}
