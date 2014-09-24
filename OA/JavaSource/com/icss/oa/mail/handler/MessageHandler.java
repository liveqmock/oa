/*
 * Created on 2004-6-25
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.icss.oa.mail.handler;

import java.io.ByteArrayInputStream;
import java.io.StringBufferInputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;

/**
 * @author firecoral
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class MessageHandler {
	
	public MimeMessage getHeadMessage(String mailHead) throws MessagingException{

		StringBufferInputStream in=new StringBufferInputStream(mailHead); 
		Session session = null; 
		MimeMessage message =null; 
		Properties props = System.getProperties(); 
		session = Session.getInstance(props, null); 
		try {
			message = new MimeMessage(session, in);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return message;
		
	}
	
	public MimeMessage getContentMessage(byte[] mailcontent) throws MessagingException{

        String content = new String(mailcontent);
		ByteArrayInputStream in=new ByteArrayInputStream(mailcontent);
		Session session = null; 
		MimeMessage message =null; 
		Properties props = System.getProperties(); 
		session = Session.getInstance(props, null); 
		try {
			message = new MimeMessage(session, in);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		return message;
		
	}
	
//	//把邮件头分割
//	private String[] getInMailList(String mailHead){
//		mailHeadTempStr = new String[30];
//		StringTokenizer mailHeadlist = new StringTokenizer(mailHead,"\r\n");
//		int i=0;
//		while (mailHeadlist.hasMoreTokens()) {
//			mailHeadTempStr[i] = mailHeadlist.nextToken();
//			i++;
//		}
//		return mailHeadTempStr;
//	}
	
	//两个数组合并（默认为3行）
	public String[][] ArrayCombine(String[][] inarr1,String[][] inarr2){
		int cols1 = inarr1[0].length;
		int cols2 = inarr2[0].length; 
		String[][] inarr0 = new String[3][cols1+cols2];
		for(int i=0;i<inarr1[0].length;i++){
			for(int j=0;j<3;j++){
				inarr0[i][j] = inarr1[i][j];
			}
		}
		for(int i=0;i<inarr2[0].length;i++){
			for(int j=0;j<3;j++){
				inarr0[i][j] = inarr2[i][j];
			}
		}
		return inarr0;
	}
	
	//两个LIST合并
	public List ListCombine(List list1,List list2){

		for(int i=0;i<list2.size();i++){
			list1.add(list2.get(i));
		}
		return list1;
	}
	
	//得到发送地址
	public String getFrom(String mailHead) throws MessagingException, UnsupportedEncodingException{
		Address[] address = this.getHeadMessage(mailHead).getFrom();
		String mailFrom = address[0].toString();
		return MimeUtility.decodeText(mailFrom);
	}
	
	public String getMessageId(String mailHead) throws MessagingException{
		String messageid = this.getHeadMessage(mailHead).getMessageID();
		return messageid;
	}
	
	//得到接收时间
	public Date getReceiveDate(String mailHead) throws MessagingException{
		Date sentDate = null;
		sentDate = this.getHeadMessage(mailHead).getSentDate();
		return sentDate;
	}
	
	public List getRecipients(String mailHead) throws MessagingException{
		List receList = new ArrayList();
		Address[] toaddress = this.getHeadMessage(mailHead).getRecipients(Message.RecipientType.TO);
		Address[] ccaddress = this.getHeadMessage(mailHead).getRecipients(Message.RecipientType.CC);
		receList.add(toaddress);
		receList.add(ccaddress);
		return receList;
	}
	
	public String getReplyTo(String mailHead) throws MessagingException, UnsupportedEncodingException{
		Address[] address = this.getHeadMessage(mailHead).getReplyTo();
		String replyTo = address[0].toString();
		return MimeUtility.decodeText(replyTo);
	}
    
    //邮件主题
    public String getSubject(String mailHead) throws MessagingException, UnsupportedEncodingException{
		String mailSubject = "";
		mailSubject = this.getHeadMessage(mailHead).getSubject();
		mailSubject = MimeUtility.decodeText(mailSubject);
		//去掉标题后面的时间部分
		mailSubject = mailSubject.substring(0,mailSubject.length()-13);
		return mailSubject;
    }
    
	//邮件主题--用于删除待办
	public String getIntendSubject(String mailHead) throws MessagingException, UnsupportedEncodingException{
		String mailSubject = "";
		mailSubject = this.getHeadMessage(mailHead).getSubject();
		mailSubject = MimeUtility.decodeText(mailSubject);
		return mailSubject;
	}
    
    //判断是否有附件
    public String getContentType(String mailHead) throws MessagingException{
		String mailContentType = "";
		String tempStr = this.getHeadMessage(mailHead).getContentType();
		int index = tempStr.indexOf(';');
		mailContentType = tempStr.substring(0,index);
		return mailContentType;
    }


    
}
