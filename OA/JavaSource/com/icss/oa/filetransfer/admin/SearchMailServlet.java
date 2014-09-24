/*
 * Created on 2004-7-6
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.icss.oa.filetransfer.admin;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.util.ArrayList;
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
import com.icss.oa.util.CommUtil;
import com.icss.oa.util.PropertyManager;
import com.icss.resourceone.common.logininfo.UserInfo;
import com.icss.resourceone.sdk.framework.Context;

/**
 * @author firecoral
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class SearchMailServlet extends ServletBase{

	protected void performTask(HttpServletRequest request, HttpServletResponse response) 
	   throws ServletException, IOException {
		
		Connection conn = null;
		dirmanage mailhandler = null;
		Context ctx = null;

		String domain = PropertyManager.getProperty("archivesdomain");
		String ip = PropertyManager.getProperty("archivesip");
		
		List searchList = new ArrayList();   //传给JSP的LIST
		List reList = new ArrayList();   
		List userFolderName = new ArrayList();
		List alluserList = new ArrayList();
		List rootList = new ArrayList();
		List userList = new ArrayList();
		
		String startTime = request.getParameter("startTime");
		String endTime = request.getParameter("endTime");
		String subject = request.getParameter("subject");
		
		String folderSort = request.getParameter("folder");
		String isRead = request.getParameter("isRead");
		
		
		try{
			conn = this.getConnection(Globals.DATASOURCEJNDI);
			ConnLog.open("SearchMailServlet");
			ctx = Context.getInstance();
			
			UserInfo user = ctx.getCurrentLoginInfo();
			//依邮箱设置表，得到准确的邮箱用户名，用来构造邮箱地址
			FiletransferSetHandler ftsHandler = new FiletransferSetHandler(conn);
			String username = "";
			username = ftsHandler.getUserid(user.getPersonUuid());
			if(username==null){
				this.forward(request,response,"/filetransfer/noMailBox.jsp");
				return;
			}
			
			mailhandler = MailhandlerFactory.getHandler(username);
			
			//得到用户自定义的文件夹
			String[] uFolder = mailhandler.dirList("");
			//用户文件夹名称
			for(int j=0;j<uFolder.length;j++){
				userFolderName.add(uFolder[j]);
			}
			
			if(subject==null){ //标识为第一次进入查找
				folderSort = "0";
				startTime = "";
				endTime = "";
				subject = "";
				isRead = "0";
			}
				
			String[][] str1 = null;
			String[][] str2 = null;
			String[][] str4 = null;
			
			MessageHandler mhandler = new MessageHandler();
			if("0".equals(folderSort)){
				str1 = mailhandler.fileheadList("");
			}else if("Inbox".equals(folderSort)||"Sent".equals(folderSort)
			         ||"Draft".equals(folderSort)||"Junk".equals(folderSort)){
				str1 = mailhandler.fileheadList("");
				String pd = "";
				if("Inbox".equals(folderSort))          //收件箱
					pd = FileTransferConfig.RECE_FLAG;
				else if("Sent".equals(folderSort))       //发件箱
					pd = FileTransferConfig.SENT_FLAG;
				else if("Draft".equals(folderSort))    //草稿箱
					pd = FileTransferConfig.DRA_FLAG;
				else if("Junk".equals(folderSort))      //垃圾箱
					pd = FileTransferConfig.JUNK_FLAG;
			    int num = 0;
				str4 = new String[3][str1[0].length];
				for(int p=0;p<str1[0].length;p++){
					if(pd.equals(str1[2][p].substring(0,1))){
						for(int q=0;q<3;q++){
							str4[q][num] = str1[q][p];
						}
						num++;
					}
				}
			}else{
				folderSort = ftsHandler.encodeBase64(folderSort);
				str2 = mailhandler.fileheadList(folderSort);
			}
			
			if("0".equals(folderSort)){   //为查找所有目录下的
				rootList = this.getList("inbox",str1,mhandler,startTime,endTime,subject,isRead);
				for(int v=0;v<uFolder.length;v++){
					String[][] str3 = null;
					str3 = mailhandler.fileheadList(uFolder[v]);
					List oneUserList = new ArrayList();
					String userfolername = ftsHandler.decodeBase64(uFolder[v]);
					oneUserList = this.getList(userfolername,str3,mhandler,startTime,endTime,subject,isRead);
					alluserList = mhandler.ListCombine(alluserList,oneUserList);
				}
				reList = mhandler.ListCombine(rootList,alluserList);
			}else if(str4!=null){    //某一个系统文件下
				reList = this.getList("inbox",str4,mhandler,startTime,endTime,subject,isRead);
			}else{   //为新建文件夹
				folderSort = ftsHandler.decodeBase64(folderSort);
				reList = this.getList(folderSort,str2,mhandler,startTime,endTime,subject,isRead);
			}
			
			FileTransferHandler ftHandler = new FileTransferHandler();
			reList = ftHandler.OrderByTimeDes(reList);
			
		}catch(Exception ex){
		    ex.printStackTrace();
	    }finally{
		    if(mailhandler!=null)
				try {
					mailhandler.disconnect();
				} catch (LdapException e1) {
					// 
					e1.printStackTrace();
				}
			if (conn != null)
			try {
				conn.close();
				ConnLog.close("SearchMailServlet");
			} catch (Exception e2) {
				e2.printStackTrace();
			}
	    }//try
	    
		///////////////////////////用于分页/////////////////////
		//一页多少条记录
		int rowcount = Integer.parseInt(this.getInitParameter("_mailcount_per_page"));  
//		int rowcount = 5;
		//当前页数
		int curPageNum = 0;
		String currentNum = request.getParameter("curPageNum");
		if(currentNum!=null){
			curPageNum = Integer.parseInt(request.getParameter("curPageNum"));
		}else{
			curPageNum = 1;    //第一次到JSP为第一页
		}
		//共有多少页
		int pagecount = 0;
		if(reList.size()%rowcount==0)
			pagecount = reList.size()/rowcount;
		else
			pagecount = reList.size()/rowcount+1;   
		//共有多少条记录
		int totalcount = reList.size();
		
		///////////////////////////////////////////////////////////
		int startNum = (curPageNum-1)*rowcount;  //开始条数（在LIST中的序号）
		int endcount = startNum+rowcount;
		if(endcount>totalcount){
			endcount = totalcount;
		}
		for(int k=startNum;k<endcount;k++){
			searchList.add(reList.get(k));
		}
		
		request.setAttribute("reList",searchList);
	    request.setAttribute("userFolderName",userFolderName);
	    
		///////////////////////////用于分页/////////////////////
		request.setAttribute("curPageNum",new Integer(curPageNum));
		request.setAttribute("pagecount",new Integer(pagecount));
		request.setAttribute("totalcount",new Integer(totalcount));
		
		request.setAttribute("startTime",startTime);
		request.setAttribute("endTime",endTime);
		request.setAttribute("subject",subject);
		request.setAttribute("folderSort",folderSort);
		request.setAttribute("isRead",isRead);
		

		this.forward(request, response, "/mail/SearchMailList_Body.jsp");
	}
	
	//返回得到的最终的LIST
	private List getList(String folderSort,String[][] str,MessageHandler handler,
	                     String starttime,String endtime,String subject,String isread) 
	               throws UnsupportedEncodingException, MessagingException, Exception{
		List rlist = new ArrayList();
		for(int i=0; i < str[0].length; i++){
			String mailHead = str[1][i];
			if(mailHead!=null){
				String secondword = "";
				try{
					if((handler.getSubject(mailHead)).indexOf(subject)!=-1){  //标题符合的子串
						if(!("".equals(starttime))){  //用户选择了时间查找
							long stime = CommUtil.getLongByTime(starttime);
							long etime = CommUtil.getLongByTime(endtime);
							if(stime<=handler.getReceiveDate(mailHead).getTime()
									&&handler.getReceiveDate(mailHead).getTime()<=etime+86400000){
								if(!("0".equals(isread))){  //有是否为新邮件的查找
									if("1".equals(isread)){
										secondword = "n";
									}else{
										secondword = "o";
									}
									if(secondword.equals(str[2][i].substring(1,2))){
										rlist.add(this.addToList(folderSort,str,i));
									}
								}else{
									rlist.add(this.addToList(folderSort,str,i));
								}//isRead
							}
						}else{
							if(!("0".equals(isread))){  //有是否为新邮件的查找
									if("1".equals(isread)){
										secondword = "n";
									}else{
										secondword = "o";
									}
									if(secondword.equals(str[2][i].substring(1,2))){
										rlist.add(this.addToList(folderSort,str,i));
									}
							}else{
								rlist.add(this.addToList(folderSort,str,i));
							}//isRead
						}//time
					} //if
				}catch(Exception ex){
					System.out.println("there is a mail error（一个非法的邮件）....");
				}
			}  //if mailHead
		}//for
		return rlist;
	}

   //从数组中把数据放入LIST	
	private List addToList(String folderSort,String[][] str,int i){
		//每一个tempList中有四个元素，第一个标识在哪个文件夹中
		List tempList = new ArrayList();
		tempList.add(folderSort);
		for(int j=0;j<3;j++){
			tempList.add(str[j][i]);
		}
		return tempList;
	}
	
	private List getOrderList(List oldList){
		//对List中的邮件依时间从旧到新接收时间进行排序
		List newStr = new ArrayList();
		String[][] timeStr = new String[3][oldList.size()];
		for(int m =0;m<oldList.size();m++){
			for(int n=0;n<3;n++){
				timeStr[n][m] = ((List)oldList.get(m)).get(n).toString();
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

}
