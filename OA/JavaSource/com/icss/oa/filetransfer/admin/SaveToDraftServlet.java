/*
 * Created on 2004-12-15
 *
 * 
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.icss.oa.filetransfer.admin;

import java.io.File;
import java.io.IOException;
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
import com.icss.j2ee.util.Globals;
import com.icss.oa.config.FileTransferConfig;
import com.icss.oa.filetransfer.handler.FileTransferHandler;
import com.icss.oa.filetransfer.handler.FiletransferSetHandler;
import com.icss.oa.filetransfer.handler.SendFileBean;
import com.icss.oa.util.CommUtil;
import com.icss.oa.util.PropertyManager;
import com.icss.resourceone.common.logininfo.UserInfo;
import com.icss.resourceone.sdk.framework.Context;

/**
 * @author firecoral
 *
 * 
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class SaveToDraftServlet extends ServletBase{
	
	protected void performTask(
			HttpServletRequest request,
			HttpServletResponse response)
			throws ServletException, IOException {
	
		String topic = request.getParameter("topic");
		String content = request.getParameter("content");
		String sendcc = request.getParameter("sendcc");
		String sendbcc = request.getParameter("sendbcc");
		
		dirmanage mailhandler = null;
		String ip = PropertyManager.getProperty("archivesip");
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
		
		request.setAttribute("title1","文件发送");
		request.setAttribute("title2","发送结果");

		try {
			conn = this.getConnection(Globals.DATASOURCEJNDI);
			ConnLog.open("SaveToDraftServlet");
			FileTransferHandler handler = new FileTransferHandler(conn);
			//取得发件人信息
			ctx = Context.getInstance();
			UserInfo user = ctx.getCurrentLoginInfo();
			String chinaName = user.getCnName();  //中文名
			//依邮箱设置表，得到准确的邮箱用户名，用来构造邮箱地址
			FiletransferSetHandler ftsHandler = new FiletransferSetHandler(conn);
			String userid = ftsHandler.getUserid(user.getPersonUuid());
			mailhandler = new dirmanage(ip, userid, senddomain, ip, 389, "cn=root,o=redflag.com,c=ch", "simple", "redflag.com");
			long nowtime = System.currentTimeMillis();
			//发送的标题为标题加上发送的时间串，为删除待办滚动用
			subject = topic.concat(Long.toString(nowtime));
			//格式化文件内容概要
			text = CommUtil.formathtm(content);

			//取得附件信息
			long mailmemory = 0;   //要发送的邮件的大小
			HttpSession session = request.getSession();
			SendFileBean sendFileBean =
				SendFileBean.getInstanceFromSession(session);
			if (sendFileBean.filenumber() == 0) {
				mailmemory = 1536;   //为字节，无附件时默认为1.5K
			} else {
				mailmemory = 1536 + sendFileBean.getTotleFilesize();   //有附件时的大小
				attachment = handler.getAttachNames(sendFileBean);
			}
			//得到可发送的用户地址
			StringBuffer noAddressPerson = new StringBuffer();
			List sendlist = handler.getSendtoAddress(request,senddomain);
			if(!(sendlist.get(0).toString().equals(""))){
				noAddressPerson.append(sendlist.get(0).toString());
			}
			//接收人地址
			originUserAddress = sendlist.get(1).toString();
			StringTokenizer toList = new StringTokenizer(originUserAddress.toString().concat(","),",");
			int tokenLength = toList.countTokens();
			to = new String[tokenLength];
			for(int x=0;x<tokenLength;x++){
				to[x] = toList.nextToken();
			}
			//得到抄送用户地址
			if(!("".equals(sendcc))){
				List sendcclist = handler.getSendccAddress(request,senddomain);
				if(!(sendcclist.get(0).toString().equals(""))){
					noAddressPerson.append(sendcclist.get(0).toString());
				}
				ccAddress = sendcclist.get(1).toString();
				StringTokenizer ccList = new StringTokenizer(ccAddress.concat(","),",");
				int cckenLength = ccList.countTokens();
				cc = new String[cckenLength];
				for(int y=0;y<cckenLength;y++){
					cc[y] = ccList.nextToken();
				}
			}
			//得到密送用户地址
			if(!("".equals(sendbcc))){
				List sendbcclist = handler.getSendbccAddress(request,senddomain);
				if(!(sendbcclist.get(0).toString().equals(""))){
					noAddressPerson.append(sendbcclist.get(0).toString());
				}
				bccAddress = sendbcclist.get(1).toString();
				StringTokenizer bccList = new StringTokenizer(bccAddress.concat(","),",");
				int bcckenLength = bccList.countTokens();
				bcc = new String[bcckenLength];
				for(int z=0;z<bcckenLength;z++){
					bcc[z] = bccList.nextToken();
				}
			}
			
			//可发送
			StringBuffer ReciAddress = new StringBuffer();
			ReciAddress.append(originUserAddress);
			if(!("".equals(ccAddress))){
				ReciAddress.append(",").append(ccAddress);
			}
			if(!("".equals(bccAddress))){
				ReciAddress.append(",").append(bccAddress);
			}
			
//			//是否需要保存到发件箱
//			String isSent = request.getParameter("isSent");
//			if("checked2".equals(isSent)){
//					handler.saveToSent(mailhandler,request.getContextPath(),mailmemory,userid,chinaName,senddomain,
//											subject,text,attachment,flag,ReciAddress.toString());
//			}
			
			if(noAddressPerson.length()>0){
				StringTokenizer noAddress = new StringTokenizer(noAddressPerson.toString(),",");
				while (noAddress.hasMoreTokens()) {
					noPersonResult.append(noAddress.nextToken()).append(",");
				}
			}
			
			if(ReciAddress.length()>0){
				mailhandler.transfermail(subject,text,to,cc,bcc,attachment,flag,null);
				
				StringTokenizer yesList = new StringTokenizer(ReciAddress.toString(),",");
				while (yesList.hasMoreTokens()) {
					String yesUserID = yesList.nextToken();
					sendResult.append(ftsHandler.getCName(yesUserID.substring(0,yesUserID.indexOf("@")))).append(",");
				}
			}
			
			request.setAttribute("sendResult",sendResult.toString());
			request.setAttribute("noPersonResult",noPersonResult.toString());

			this.forward(request, response, "/filetransfer/savetoDraftResult.jsp");

		}catch (SendFailedException e) {
			request.setAttribute("failResult","保存到草稿箱失败！");
			this.forward(request, response, "/filetransfer/savetoDraftResult.jsp");
	    } catch (IOException e) {
			request.setAttribute("failResult","保存到草稿箱失败！");
			this.forward(request, response, "/filetransfer/savetoDraftResult.jsp");
		} catch (Exception e) {
			request.setAttribute("failResult","保存到草稿箱失败！");
			this.forward(request, response, "/filetransfer/savetoDraftResult.jsp");
		} finally {
			if (conn != null)
			try {
				conn.close();
				ConnLog.close("SaveToDraftServlet");
			} catch (Exception e2) {
				 e2.printStackTrace();
			}
			if(mailhandler!=null){
				try {
					mailhandler.disconnect();
				} catch (LdapException e) {
					e.printStackTrace();
				}
			}
			//删除服务器上的暂存附件（用于上传的）
			if(attachment!=null&&attachment.length>0){
				for(int attachnum=0;attachnum<attachment.length;attachnum++){
					int pathindex = attachment[attachnum].lastIndexOf(File.separator);
					String privatedir = attachment[attachnum].substring(0,pathindex);
					File attachfile = new File(attachment[attachnum]);
					attachfile.delete();
					File dirfile = new File(privatedir);
					dirfile.delete();
				}
			}
	    }//try
	}


}
