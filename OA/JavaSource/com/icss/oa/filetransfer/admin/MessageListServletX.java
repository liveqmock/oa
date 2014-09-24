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
		//排序字段名 默认按时间字段 0:按照时间排序 1:按照大小排序
		int sortname = 0;
		try{
			sortname = request.getParameter("sortname")==null?Integer.parseInt("0"):Integer.parseInt(request.getParameter("sortname"));
		}catch(NumberFormatException e){
			sortname = 0;//参数传递错误采用默认排序方式——按时间字段排序
		}
		
		//排序方式 默认为倒序
		boolean sorttype = false;
		//"DSC"为降序排序 "ASC"为升序
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

		//当前页数
		int curPageNum = 0;
		String currentNum = request.getParameter("curPageNum");
		if (currentNum != null&&!"null".equals(currentNum)) {
			curPageNum = Integer.parseInt(request.getParameter("curPageNum"));
		} else {
			curPageNum = 1; //第一次到JSP为第一页
		}

		int curPage = 1;
		try {
			curPage = new Integer(currentNum).intValue();
		} catch (Exception e) {
			curPage = 1;
			System.out.println("[警告]：指向第一页");
		}

		int rowcount = Integer.parseInt(this
				.getInitParameter("_mailcount_per_page"));

		try {
			conn = this.getConnection(Globals.DATASOURCEJNDI);
			ConnLog.open("MessageListServlet");
			ctx = Context.getInstance();
			UserInfo user = ctx.getCurrentLoginInfo();
			//依邮箱设置表，得到准确的邮箱用户名，用来构造邮箱地址
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
			
			//得到用户自定义的文件夹
			String[] uFolder = mailhandler.dirList("");
			//用户文件夹名称
			for (int j = 0; j < uFolder.length; j++) {
				userFolderName.add(uFolder[j]);
			}

			if ("user".equals(type)) { //为用户自定义文件夹
				String foldername = folderSort;
				foldername = ftsHandler.encodeBase64(foldername);
				
				//0522将关闭数据库链接提前关闭
				if (conn != null)
					try {
						conn.close();
						ConnLog.close("MessageListServlet");
					} catch (Exception e2) {
						e2.printStackTrace();
					}
				//0522将关闭数据库链接提前关闭
				
				
				//System.out.println("进入个人定制系统文件夹！");
				//System.out.println("foldername =" + foldername);
				//				String[][] userStr = mailhandler.fileheadList(foldername);
				//				0425修改后的API进行修改
				//String[][] userStr = mailhandler.fileHeadPage(foldername,curPage, 0, false, rowcount, null);
				
				totalcount = mailhandler.getTotalMails(foldername, "");
				
				int pagecount = 0;
				if (totalcount % rowcount == 0)
					pagecount = totalcount / rowcount;
				else
					pagecount = totalcount / rowcount + 1;

				if (curPageNum>pagecount){//如果大于最大页数，则回到第一页
					curPage = pagecount;
					curPageNum = pagecount;
				}

				String[][] userStr = mailhandler.fileHeadPage(foldername,curPage, sortname, sorttype, rowcount, null);
				
				//System.out.println("得到个人定制文件头！");
				//String[] tempstr = new String[3];
				for (int i = 0; i < userStr[0].length; i++) {
					List tempList = new ArrayList();
					for (int j = 0; j < 3; j++) {
						tempList.add(userStr[j][i]);
					}
					totalList.add(tempList);
				}
				//System.out.println("sunchuanting  个人定制" + totalList.size());
			} else { //为非用户自定义文件夹
				//				String[][] str = mailhandler.fileheadList("");

				//System.out.println("进入系统文件夹！");
				String firstword = "";
				String pd = "";
				//String[] tempstr = new String[3];
				//System.out.println("确定具体的系统文件夹！");
				//str[0].length为邮件的个数
				if ("Inbox".equals(folderSort)) //收件箱
					pd = FileTransferConfig.RECE_FLAG;
				else if ("Sent".equals(folderSort)) //发件箱
					pd = FileTransferConfig.SENT_FLAG;
				else if ("Draft".equals(folderSort)) //草稿箱
					pd = FileTransferConfig.DRA_FLAG;
				else if ("Junk".equals(folderSort)) //垃圾箱
					pd = FileTransferConfig.JUNK_FLAG;
				//System.out.println("得到具体文件夹的代号 =" + pd);
				//String[][] str = mailhandler.fileHeadPage("", curPage, 0,false, rowcount, pd);
				System.out.println("folderSort:"+folderSort);
				totalcount = mailhandler.getTotalMails("", pd);
				System.out.println("totalcount:"+totalcount);
				int pagecount = 0;
				if (totalcount % rowcount == 0)
					pagecount = totalcount / rowcount;
				else
					pagecount = totalcount / rowcount + 1;

				if (curPageNum>pagecount){//如果大于最大页数，则回到第一页
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
				//System.out.println("得到文件头！");
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
				//System.out.println("得到文件头！");
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

		///////////////////////////用于分页/////////////////////
		//一页多少条记录
		//		String pernum = this.getInitParameter("_mailcount_per_page");
		/*
		 * int rowcount = Integer.parseInt(this
		 * .getInitParameter("_mailcount_per_page"));
		 */
		//		int rowcount = 5;
		//当前页数
		/*
		 * int curPageNum = 0; String currentNum =
		 * request.getParameter("curPageNum"); if (currentNum != null) {
		 * curPageNum = Integer.parseInt(request.getParameter("curPageNum")); }
		 * else { curPageNum = 1; //第一次到JSP为第一页 }
		 */
		//共有多少页
		int pagecount = 0;
		if (totalcount % rowcount == 0)
			pagecount = totalcount / rowcount;
		else
			pagecount = totalcount / rowcount + 1;
		//共有多少条记录
		//		int totalcount = totalList.size();

		///////////////////////////////////////////////////////////
		/*
		 * int startNum = (curPageNum - 1) * rowcount; //开始条数（在LIST中的序号） int
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

		///////////////////////////用于分页/////////////////////
		request.setAttribute("curPageNum", new Integer(curPageNum));
		request.setAttribute("pagecount", new Integer(pagecount));
		request.setAttribute("totalcount", new Integer(totalcount));
		
//		System.out.println("wangjiang::MessageListServlet------------------>sortname = "+ sortname);
//		System.out.println("wangjiang::MessageListServlet------------------>sorttypestr = "+ sorttypestr);

		//System.out.println("fileTransfer start1------");
		this.forward(request, response, "/mail/MailList_Body.jsp?sortname="+sortname+"&sorttype="+sorttypestr);

	}

}
