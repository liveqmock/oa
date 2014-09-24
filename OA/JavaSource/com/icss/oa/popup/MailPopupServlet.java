package com.icss.oa.popup;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
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
import com.icss.oa.filetransfer.handler.FiletransferSetHandler;
import com.icss.oa.util.PropertyManager;
import com.icss.resourceone.common.logininfo.UserInfo;
import com.icss.resourceone.sdk.framework.Context;

public class MailPopupServlet extends ServletBase {

	protected void performTask(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String folderSort = request.getParameter("folder");
		if(folderSort==null){
			folderSort= "Inbox";
		}
		// �����ֶ��� Ĭ�ϰ�ʱ���ֶ� 0:����ʱ������ 1:���մ�С����
		int sortname = 0;

		// ����ʽ Ĭ��Ϊ����
		boolean sorttype = false;

		Connection conn = null;
		Context ctx = null;
		dirmanage mailhandler = null;
		List totalList = new ArrayList();
	
		// ��ǰҳ��
		int curPage = 1;
		int rowcount = 10;

		try {
			conn = this.getConnection(Globals.DATASOURCEJNDI);
			ConnLog.open("MailPopupServlet");
			ctx = Context.getInstance();
			UserInfo user = ctx.getCurrentLoginInfo();
			
			// ���������ñ��õ�׼ȷ�������û������������������ַ
			FiletransferSetHandler ftsHandler = new FiletransferSetHandler(conn);
			String username = "";
			username = ftsHandler.getUserid(user.getPersonUuid());

			if (username == null) {
				this.forward(request, response, "/filetransfer/noMailBox.jsp");
			}

			String domain = PropertyManager.getProperty("archivesdomain");
			String ip = PropertyManager.getProperty("archivesip");

			mailhandler = new dirmanage(ip, username, domain, ip, 389,
					"cn=root,o=redflag.com,c=ch", "simple", "redflag.com");

			// Ϊ���û��Զ����ļ���
			String firstword = "";
			String pd = "";
			// String[] tempstr = new String[3];
			// System.out.println("ȷ�������ϵͳ�ļ��У�");
			// str[0].lengthΪ�ʼ��ĸ���
			if ("Inbox".equals(folderSort)) // �ռ���
				pd = FileTransferConfig.RECE_FLAG;
			else if ("Sent".equals(folderSort)) // ������
				pd = FileTransferConfig.SENT_FLAG;
			else if ("Draft".equals(folderSort)) // �ݸ���
				pd = FileTransferConfig.DRA_FLAG;
			else if ("Junk".equals(folderSort)) // ������
				pd = FileTransferConfig.JUNK_FLAG;
			
			// System.out.println("�õ������ļ��еĴ��� =" + pd);
			// String[][] str = mailhandler.fileHeadPage("", curPage, 0,false,
			// rowcount, pd);

			String[][] str = mailhandler.fileHeadPage("", curPage, sortname,
					sorttype, rowcount, pd);
			// System.out.println("�õ��ļ�ͷ��");
			for (int i = 0; i < str[0].length; i++) {
				firstword = str[2][i].substring(0, 1);
				if (firstword.equals(pd)) {
					List tempList = new ArrayList();
					for (int j = 0; j < 3; j++) {
						tempList.add(str[j][i]);
					}
					totalList.add(tempList);
				}
			}
			
			
		} catch (Exception ex) {
			ex.printStackTrace();
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
					ConnLog.close("MailPopupServlet");
				} catch (Exception e2) {
					e2.printStackTrace();
				}
		}
		
		
		request.setAttribute("listOfMessages", totalList);
		request.setAttribute("folderSort", folderSort);
		this.forward(request, response, "/popup/zxyj.jsp");

	}

}
