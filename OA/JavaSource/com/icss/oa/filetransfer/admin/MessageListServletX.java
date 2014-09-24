/*
 * Created on 2004-4-28
 *
 * 
 */
package com.icss.oa.filetransfer.admin;

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
import com.icss.oa.filetransfer.util.MailhandlerFactory;
import com.icss.oa.util.PropertyManager;
import com.icss.resourceone.common.logininfo.UserInfo;
import com.icss.resourceone.sdk.framework.Context;

/**
 *  
 */
public class MessageListServletX extends ServletBase {

	protected void performTask(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String folderSort = request.getParameter("folder");
		String type = request.getParameter("type");
		//�����ֶ��� Ĭ�ϰ�ʱ���ֶ� 0:����ʱ������ 1:���մ�С����
		int sortname = 0;
		try{
			sortname = request.getParameter("sortname")==null?Integer.parseInt("0"):Integer.parseInt(request.getParameter("sortname"));
		}catch(NumberFormatException e){
			sortname = 0;//�������ݴ������Ĭ������ʽ������ʱ���ֶ�����
		}
		
		//����ʽ Ĭ��Ϊ����
		boolean sorttype = false;
		//"DSC"Ϊ�������� "ASC"Ϊ����
		String sorttypestr = request.getParameter("sorttype")==null?"DSC":request.getParameter("sorttype");
		if("DSC".equals(sorttypestr)){
			sorttype = false;
		}else{
			sorttype = true;
		}
				
		Connection conn = null;
		Context ctx = null;
		dirmanage mailhandler = null;
		List totalList = new ArrayList();
		List reList = new ArrayList();
		List userFolderName = new ArrayList();
		int totalcount = 0;

		//��ǰҳ��
		int curPageNum = 0;
		String currentNum = request.getParameter("curPageNum");
		if (currentNum != null&&!"null".equals(currentNum)) {
			curPageNum = Integer.parseInt(request.getParameter("curPageNum"));
		} else {
			curPageNum = 1; //��һ�ε�JSPΪ��һҳ
		}

		int curPage = 1;
		try {
			curPage = new Integer(currentNum).intValue();
		} catch (Exception e) {
			curPage = 1;
			System.out.println("[����]��ָ���һҳ");
		}

		int rowcount = Integer.parseInt(this
				.getInitParameter("_mailcount_per_page"));

		try {
			conn = this.getConnection(Globals.DATASOURCEJNDI);
			ConnLog.open("MessageListServlet");
			ctx = Context.getInstance();
			UserInfo user = ctx.getCurrentLoginInfo();
			//���������ñ��õ�׼ȷ�������û������������������ַ
			FiletransferSetHandler ftsHandler = new FiletransferSetHandler(conn);
			String username = "";
			username = ftsHandler.getUserid(user.getPersonUuid());
			ctx.close();
			if (username == null) {
				this.forward(request, response, "/filetransfer/noMailBox.jsp");
			}

			String domain = PropertyManager.getProperty("archivesdomain");
			String ip = PropertyManager.getProperty("archivesip");

			mailhandler = MailhandlerFactory.getHandler(username);
			
			//�õ��û��Զ�����ļ���
			String[] uFolder = mailhandler.dirList("");
			//�û��ļ�������
			for (int j = 0; j < uFolder.length; j++) {
				userFolderName.add(uFolder[j]);
			}

			if ("user".equals(type)) { //Ϊ�û��Զ����ļ���
				String foldername = folderSort;
				foldername = ftsHandler.encodeBase64(foldername);
				
				//0522���ر����ݿ�������ǰ�ر�
				if (conn != null)
					try {
						conn.close();
						ConnLog.close("MessageListServlet");
					} catch (Exception e2) {
						e2.printStackTrace();
					}
				//0522���ر����ݿ�������ǰ�ر�
				
				
				//System.out.println("������˶���ϵͳ�ļ��У�");
				//System.out.println("foldername =" + foldername);
				//				String[][] userStr = mailhandler.fileheadList(foldername);
				//				0425�޸ĺ��API�����޸�
				//String[][] userStr = mailhandler.fileHeadPage(foldername,curPage, 0, false, rowcount, null);
				
				totalcount = mailhandler.getTotalMails(foldername, "");
				
				int pagecount = 0;
				if (totalcount % rowcount == 0)
					pagecount = totalcount / rowcount;
				else
					pagecount = totalcount / rowcount + 1;

				if (curPageNum>pagecount){//����������ҳ������ص���һҳ
					curPage = pagecount;
					curPageNum = pagecount;
				}

				String[][] userStr = mailhandler.fileHeadPage(foldername,curPage, sortname, sorttype, rowcount, null);
				
				//System.out.println("�õ����˶����ļ�ͷ��");
				//String[] tempstr = new String[3];
				for (int i = 0; i < userStr[0].length; i++) {
					List tempList = new ArrayList();
					for (int j = 0; j < 3; j++) {
						tempList.add(userStr[j][i]);
					}
					totalList.add(tempList);
				}
				//System.out.println("sunchuanting  ���˶���" + totalList.size());
			} else { //Ϊ���û��Զ����ļ���
				//				String[][] str = mailhandler.fileheadList("");

				//System.out.println("����ϵͳ�ļ��У�");
				String firstword = "";
				String pd = "";
				//String[] tempstr = new String[3];
				//System.out.println("ȷ�������ϵͳ�ļ��У�");
				//str[0].lengthΪ�ʼ��ĸ���
				if ("Inbox".equals(folderSort)) //�ռ���
					pd = FileTransferConfig.RECE_FLAG;
				else if ("Sent".equals(folderSort)) //������
					pd = FileTransferConfig.SENT_FLAG;
				else if ("Draft".equals(folderSort)) //�ݸ���
					pd = FileTransferConfig.DRA_FLAG;
				else if ("Junk".equals(folderSort)) //������
					pd = FileTransferConfig.JUNK_FLAG;
				//System.out.println("�õ������ļ��еĴ��� =" + pd);
				//String[][] str = mailhandler.fileHeadPage("", curPage, 0,false, rowcount, pd);
				System.out.println("folderSort:"+folderSort);
				totalcount = mailhandler.getTotalMails("", pd);
				System.out.println("totalcount:"+totalcount);
				int pagecount = 0;
				if (totalcount % rowcount == 0)
					pagecount = totalcount / rowcount;
				else
					pagecount = totalcount / rowcount + 1;

				if (curPageNum>pagecount){//����������ҳ������ص���һҳ
					curPage = pagecount;
					curPageNum = pagecount;
				}
//				System.out.println("wangjiang::MessageListServlet-->totalcount = "+totalcount);
//				System.out.println("wangjiang::MessageListServlet-->pagecount = "+pagecount);
//				System.out.println("wangjiang::MessageListServlet-->curPageNum = "+curPageNum);
//				System.out.println("wangjiang::MessageListServlet-->curPage = "+curPage);
				String[][] str = mailhandler.fileHeadPage("", curPage, sortname, sorttype, rowcount, pd);
				System.out.println("sizesize:"+str[0].length);
				System.out.println("content:"+str[2][0]);
				//System.out.println("�õ��ļ�ͷ��");
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
				//System.out.println("�õ��ļ�ͷ��");
				//System.out.println("sunchuanting  " + totalList.size());
			}

			/*
			 * FileTransferHandler ftHandler = new FileTransferHandler();
			 * totalList = ftHandler.OrderSingleFolderByTimeDes(totalList);
			 */

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
					ConnLog.close("MessageListServlet");
				} catch (Exception e2) {
					e2.printStackTrace();
				}
		}

		///////////////////////////���ڷ�ҳ/////////////////////
		//һҳ��������¼
		//		String pernum = this.getInitParameter("_mailcount_per_page");
		/*
		 * int rowcount = Integer.parseInt(this
		 * .getInitParameter("_mailcount_per_page"));
		 */
		//		int rowcount = 5;
		//��ǰҳ��
		/*
		 * int curPageNum = 0; String currentNum =
		 * request.getParameter("curPageNum"); if (currentNum != null) {
		 * curPageNum = Integer.parseInt(request.getParameter("curPageNum")); }
		 * else { curPageNum = 1; //��һ�ε�JSPΪ��һҳ }
		 */
		//���ж���ҳ
		int pagecount = 0;
		if (totalcount % rowcount == 0)
			pagecount = totalcount / rowcount;
		else
			pagecount = totalcount / rowcount + 1;
		//���ж�������¼
		//		int totalcount = totalList.size();

		///////////////////////////////////////////////////////////
		/*
		 * int startNum = (curPageNum - 1) * rowcount; //��ʼ��������LIST�е���ţ� int
		 * endcount = startNum + rowcount; if (endcount > totalcount) { endcount =
		 * totalcount; } for (int k = startNum; k < endcount; k++) {
		 * reList.add(totalList.get(k)); }
		 */

		reList = totalList;

		
		
		request.setAttribute("listOfMessages", reList);
		request.setAttribute("userFolderName", userFolderName);
		System.out.println("folderSort 2222= "+folderSort);
		request.setAttribute("folderSort", folderSort);
		//request.setAttribute("sortname",sorttype);
		//request.setAttribute("sortname",sortname);		

		///////////////////////////���ڷ�ҳ/////////////////////
		request.setAttribute("curPageNum", new Integer(curPageNum));
		request.setAttribute("pagecount", new Integer(pagecount));
		request.setAttribute("totalcount", new Integer(totalcount));
		
//		System.out.println("wangjiang::MessageListServlet------------------>sortname = "+ sortname);
//		System.out.println("wangjiang::MessageListServlet------------------>sorttypestr = "+ sorttypestr);

		//System.out.println("fileTransfer start1------");
		this.forward(request, response, "/mail/MailList_Body.jsp?sortname="+sortname+"&sorttype="+sorttypestr);

	}

}
