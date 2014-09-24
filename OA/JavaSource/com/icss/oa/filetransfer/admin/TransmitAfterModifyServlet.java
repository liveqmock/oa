/*
 * Created on 2004-4-28
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
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
import com.icss.oa.filetransfer.util.MailhandlerFactory;
import com.icss.oa.util.CommUtil;
import com.icss.oa.util.PropertyManager;
import com.icss.resourceone.common.logininfo.UserInfo;
import com.icss.resourceone.sdk.framework.Context;

/**
 * @author Administrator
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class TransmitAfterModifyServlet extends ServletBase {

	protected void performTask(
		HttpServletRequest request,
		HttpServletResponse response)
		throws ServletException, IOException {

		String topic = request.getParameter("topic");
		String content = request.getParameter("content");
		String sendcc = request.getParameter("sendcc");
		String sendbcc = request.getParameter("sendbcc");
		
		StringBuffer sendResult = new StringBuffer();
		StringBuffer overFlowResult = new StringBuffer();
		StringBuffer noPersonResult = new StringBuffer();
		Context loginctx = null;
		Connection conn = null;
		
		dirmanage mailhandler = null;
		String ip = PropertyManager.getProperty("archivesip");
		String domain = PropertyManager.getProperty("archivesdomain");
		
		String subject = "";
		String text = "";
		String[] to = null;
		String[] cc = null;
		String[] bcc = null;
		String[] attachment = null;
		int flag = FileTransferConfig.ONLY_SEND;

		try {
			
			loginctx = Context.getInstance();
		    UserInfo user = loginctx.getCurrentLoginInfo();
		    String chinaName = user.getCnName();
			
			conn = this.getConnection(Globals.DATASOURCEJNDI);
			ConnLog.open("TransmitAfterModifyServlet");
			//���������ñ��õ�׼ȷ�������û������������������ַ
			FiletransferSetHandler ftsHandler = new FiletransferSetHandler(conn);
			String userid = ftsHandler.getUserid(user.getPersonUuid());
			
			mailhandler = MailhandlerFactory.getHandler(userid);
			
			long nowtime = System.currentTimeMillis();
			subject = topic.concat(Long.toString(nowtime));
			//��ʽ���ļ����ݸ�Ҫ
			text = CommUtil.formathtm(content);
			//ȡ�ø�����Ϣ
			long mailmemory = 0;   //Ҫ���͵��ʼ��Ĵ�С
			HttpSession session = request.getSession();
			SendFileBean sendFileBean =
				SendFileBean.getInstanceFromSession(session);
			//�õ����и������ļ��������飩
			FileTransferHandler handler = new FileTransferHandler(conn);
			if (sendFileBean.filenumber() == 0) {
				mailmemory = 1536;   //Ϊ�ֽڣ��޸���ʱĬ��Ϊ1.5K
			} else {
				mailmemory = 1536 + sendFileBean.getTotleFilesize();   //�и���ʱ�Ĵ�С
				attachment = handler.getAttachNames(sendFileBean);
			}
			mailmemory = 1536 + sendFileBean.getTotleFilesize();   //�и���ʱ�Ĵ�С
			//�õ��ɷ��͵��û���ַ
			StringBuffer noAddressPerson = new StringBuffer();
			List sendlist = handler.getSendtoAddress(request,domain);
			if(!(sendlist.get(0).toString().equals(""))){
				noAddressPerson.append(sendlist.get(0).toString());
			}
			String originUserAddress = sendlist.get(1).toString();
			List sendAddress = handler.confirmOverflow(request.getContextPath(),mailmemory,originUserAddress,chinaName,userid);
			String canSendAddress = (String)sendAddress.get(0);
			StringTokenizer toList = new StringTokenizer(canSendAddress.toString(),",");
			int toLength = toList.countTokens();
			to  = new String[toLength];
			for(int x=0;x<toLength;x++){
				to[x] = toList.nextToken();
			}
			String cannotSendAddress = (String)sendAddress.get(1);
			//�õ������û���ַ
			String ccAddress = "";
			String canSendccAddress = "";
			String cannotSendccAddress = "";
			if(!("".equals(sendcc))){
				List sendcclist = handler.getSendccAddress(request,domain);
				if(!(sendcclist.get(0).toString().equals(""))){
					noAddressPerson.append(sendcclist.get(0).toString());
				}
				ccAddress = sendcclist.get(1).toString();
				List sendccAddress = handler.confirmOverflow(request.getContextPath(),mailmemory,ccAddress,chinaName,userid);
				canSendccAddress = (String)sendccAddress.get(0);
				StringTokenizer ccList = new StringTokenizer(canSendccAddress.toString(),",");
				int ccLength = ccList.countTokens();
				cc  = new String[ccLength];
				for(int x=0;x<ccLength;x++){
					cc[x] = ccList.nextToken();
				}
				cannotSendccAddress = (String)sendccAddress.get(1);
			}
			//�õ������û���ַ
			String bccAddress = "";
			String canSendbccAddress = "";
			String cannotSendbccAddress = "";
			if(!("".equals(sendbcc))){
				List sendbcclist = handler.getSendbccAddress(request,domain);
				if(!(sendbcclist.get(0).toString().equals(""))){
					noAddressPerson.append(sendbcclist.get(0).toString());
				}
				bccAddress = sendbcclist.get(1).toString();
				List sendbccAddress = handler.confirmOverflow(request.getContextPath(),mailmemory,bccAddress,chinaName,userid);
				canSendbccAddress = (String)sendbccAddress.get(0);
				StringTokenizer bccList = new StringTokenizer(canSendbccAddress.toString(),",");
				int bccLength = bccList.countTokens();
				bcc  = new String[bccLength];
				for(int x=0;x<bccLength;x++){
					bcc[x] = bccList.nextToken();
				}
				cannotSendbccAddress = (String)sendbccAddress.get(1);
			}
			//�ɷ���
			StringBuffer ReciAddress = new StringBuffer();
			ReciAddress.append(canSendAddress);
			if(!("".equals(canSendccAddress))){
				ReciAddress.append(",").append(canSendccAddress);
			}
			if(!("".equals(canSendbccAddress))){
				ReciAddress.append(",").append(canSendbccAddress);
			}
			//���ɷ���
			StringBuffer NoReciAddress = new StringBuffer();
			NoReciAddress.append(cannotSendAddress);
			if(!("".equals(cannotSendccAddress))){
				NoReciAddress.append(",").append(cannotSendccAddress);
			}
			if(!("".equals(cannotSendbccAddress))){
				NoReciAddress.append(",").append(cannotSendbccAddress);
			}
			//�Ƿ����û�ִ
			String isRe = request.getParameter("isRe");
			if("checked1".equals(isRe)){
				flag = FileTransferConfig.SEND_RE;
			}
			
			if(noAddressPerson.length()>0){
				StringTokenizer noAddress = new StringTokenizer(noAddressPerson.toString(),",");
				while (noAddress.hasMoreTokens()) {
					noPersonResult.append(noAddress.nextToken()).append(",");
				}
			}
			if(NoReciAddress.length()>0){
				StringTokenizer noList = new StringTokenizer(NoReciAddress.toString(),",");
				while (noList.hasMoreTokens()) {
					String failAddress = noList.nextToken();
					overFlowResult.append(ftsHandler.getCName(failAddress.substring(0,failAddress.indexOf("@")))).append(",");					
				}
			}
			if(ReciAddress.length()>0){
				//�Ƿ���Ҫ���浽������
				String isSent = request.getParameter("isSent");
				if("checked2".equals(isSent)){
						handler.saveToSent(mailhandler,request.getContextPath(),mailmemory,userid,chinaName,domain,
												subject,text,attachment,flag,ReciAddress.toString());
				}
				//����
				String[][] failuser = mailhandler.transfermail(subject,text,to,cc,bcc,attachment,flag,null);
				
				StringTokenizer yesList = new StringTokenizer(ReciAddress.toString(),",");
				while (yesList.hasMoreTokens()) {
					String yesUserID = yesList.nextToken();
					sendResult.append(ftsHandler.getCName(yesUserID.substring(0,yesUserID.indexOf("@")))).append(",");
					
					int yesindex = yesUserID.indexOf("@");
					yesUserID = yesUserID.substring(0,yesindex);
					//���뵽����
					String intendtopic = "���ʼ�������"+chinaName+"��"+CommUtil.getTime(System.currentTimeMillis())+"��";
					String tointendId = userid + "," + subject + "," + yesUserID;
					handler.addToIntend(request.getContextPath(),yesUserID,intendtopic,tointendId);
				}
			}  
			if (!conn.isClosed())   conn.close();

			request.setAttribute("sendResult",sendResult.toString());
			request.setAttribute("overFlowResult",overFlowResult.toString());
			request.setAttribute("noPersonResult",noPersonResult.toString());
			request.setAttribute("title1","�ļ�ת��");
			request.setAttribute("title2","���ͽ��");

			this.forward(request, response, "/filetransfer/sendResult.jsp");
			
		}catch (SendFailedException e) {
			request.setAttribute("failResult","ת��ʧ�ܣ�");
			this.forward(request, response, "/filetransfer/sendResult.jsp");
			
		} catch (Exception e) {
			request.setAttribute("failResult","ת��ʧ�ܣ�");
			this.forward(request, response, "/filetransfer/sendResult.jsp");
			
		}finally {
			if (conn != null){
				try {
					conn.close();
					ConnLog.close("TransmitAfterModifyServlet");
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}

			if(mailhandler!=null){
				try {
					mailhandler.disconnect();
				} catch (LdapException e) {
					e.printStackTrace();
				}
			}
			
			//ɾ���������ϵ��ݴ渽���������ϴ��ģ�
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
		}

	}

}
