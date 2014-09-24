/*
 * Created on 2004-9-17
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.icss.oa.filetransfer.admin; 

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.redflaglinux.ldap.LdapException;
import org.redflaglinux.services.dir.dirmanage;

import com.icss.common.log.ConnLog;
import com.icss.j2ee.servlet.ServletBase;
import com.icss.j2ee.servlet.download.DownloadResponse;
import com.icss.j2ee.util.Globals;
import com.icss.oa.filetransfer.handler.FileTransferHandler;
import com.icss.oa.filetransfer.handler.FiletransferSetHandler;
import com.icss.oa.filetransfer.handler.MessageHandler;
import com.icss.oa.filetransfer.util.MailhandlerFactory;
import com.icss.oa.statsite.handler.StatSiteHandler;
import com.icss.oa.util.PropertyManager;
import com.icss.resourceone.common.logininfo.UserInfo;
import com.icss.resourceone.sdk.framework.Context;

/**
 * @author firecoral
 * 
 *  删除一个用户一段时间内的邮件（所有文件夹下）
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class DownloadMailServlet extends ServletBase{
	
	 private String path = null;
	
	 public void getFileItem(String str,String filename) throws IOException{
	 	
       
	 	path=this.getServletContext().getRealPath("/downloadmail");
	 	
	 	File dirname = new File(path);
	 	if(!dirname.exists()){
	 	    dirname.mkdir();
	 	}
	 	
	 	PrintWriter out = null;
    	File file=new File(path+"\\"+filename+".txt");  
		 if(!file.exists()){
		      file.createNewFile();
		   }	
		out=new PrintWriter(new FileWriter(path+"\\"+filename+".txt"));
		out.write(str);
	 	if(out!=null){out.close();}
    	}

	protected void performTask(HttpServletRequest request, HttpServletResponse response) 
	throws ServletException, IOException {
		
		Connection conn = null;
		Context ctx = null;
		dirmanage mailhandler = null;
		
		long systime = System.currentTimeMillis();
		long usertime = System.currentTimeMillis();
		long smallesttime = System.currentTimeMillis();

		
		try {
			
			conn = this.getConnection(Globals.DATASOURCEJNDI);
			ConnLog.open("DownloadMailServlet");
			ctx = Context.getInstance();
			UserInfo user = ctx.getCurrentLoginInfo();
			//System.out.println("ggggggggggggggggggggggggggggggggggggg "+user.getPersonUuid());
			/*int day =90;
			String del_day = DelMailManager.getString("delday");
			
			
			try{
			if(del_day!=null){
				day = new Integer(del_day).intValue();}
			}catch(Exception e){
				day = 90;}
			System.out.println("day   "+day);*/
			MessageHandler mhandler = new MessageHandler();
			
			//依邮箱设置表，得到准确的邮箱用户名，用来构造邮箱地址
			FiletransferSetHandler ftsHandler = new FiletransferSetHandler(conn);
			StatSiteHandler my_handler = new StatSiteHandler(conn);
			FileTransferHandler handler = new FileTransferHandler(conn);
			String username = ftsHandler.getUserid(user.getPersonUuid());
			
			String domain = PropertyManager.getProperty("archivesdomain");
			String ip = PropertyManager.getProperty("archivesip");
			
			mailhandler = MailhandlerFactory.getHandler(username);
			
			//********得到系统文件夹所有邮件中最老邮件的时间（long）************************************
			String[][] str = mailhandler.fileheadList("");
			for(int i=0; i < str[0].length; i++){
				String mailhead = str[1][i];
				if(i==0){
					systime = mhandler.getReceiveDate(mailhead).getTime();
				}else{
					long temptime = mhandler.getReceiveDate(mailhead).getTime();
					if(systime>temptime){
						systime = temptime;
					}
				}
			}
			
			//********得到自定义文件夹所有邮件中最老邮件的时间（long）***********************************
			//得到用户自定义的文件夹
			String[] uFolder = mailhandler.dirList("");
			//用户文件夹名称
			for(int i=0;i<uFolder.length;i++){
				String[][] userStr = mailhandler.fileheadList(uFolder[i]);
				for(int j=0;j<userStr[0].length;j++){
					String mailhead = str[1][i];
					if(i==0){
						usertime = mhandler.getReceiveDate(mailhead).getTime();
					}else{
						long temptime = mhandler.getReceiveDate(mailhead).getTime();
						if(usertime>temptime){
							usertime = temptime;
						}
					}
				}
				
			}
			
			//*********合并，用以得到最小时间***************************
			smallesttime = (systime<usertime)?systime:usertime;
			//三个月后的时间
			long lasttime = smallesttime + 90*86400000l;
			
			//**********开始删除****************************************
			
			//***********删除系统文件夹下的文件**************
			

			Map map = new HashMap();   
		    map = new TreeMap(); 
		    int counter=0;
		    
			for(int i=0; i < str[0].length; i++){	
				
				//System.out.println("length "+str[0].length);
				String mailHead = str[1][i];
				Date receDate = mhandler.getReceiveDate(mailHead);
				byte[] content = mailhandler.viewmail(str[2][i]);
				long recetime = receDate.getTime();
				
				if(recetime<=lasttime){
				
				String mailpath = str[2][i];
					
				MimeMessage fileMessage = mhandler.getContentMessage(content);
				
				String subject = mhandler.getSubject(mailHead);
				System.out.println("subject9 "+subject);
				String receDate1 = my_handler.getTimeByLong(new Long(recetime)); 
				String send = mhandler.getFrom(mailHead);
				String content1=null;
				List list = new ArrayList();
				
				try{
				if (!fileMessage.isMimeType("text/plain")) {
				
					//处理附件邮件
					content1 =handler.getMailContent(fileMessage);
					list = handler.getAccessoryList(fileMessage);
					
				} else {
					content1 = (String)fileMessage.getContent();
				}
				}
				catch(Exception e){
					e.printStackTrace();
				  }
				
				StringBuffer str5 = new StringBuffer();
				str5.append("主题为:").append(subject).append("发信时间为:").append(receDate1).append("发信人为:").append(send).append("内容为:").append(content1);

				String textName = "";
				textName = new Long(recetime).toString();
				this.getFileItem(str5.toString(),textName);
				map.put(new Integer(counter), new String(textName));
				counter++;
				handler.zipFile(textName,list,path+"\\");
			   	//mailhandler.deletedir(mailpath,1);
				}
			}
			
			handler.zipAllFile(map,path+"\\");
			InputStream is1 = new FileInputStream(path+"\\download.zip");
			DownloadResponse ds1 = new DownloadResponse(response);
//			ds1.downloadInputStream(is1, "download.zip");  
			ds1.downloadInputStreamByTempfile(is1, "download.zip");  

			//***********删除自定义文件夹下的文件***************
			//得到用户自定义的文件夹
			for(int i=0;i<uFolder.length;i++){
				String[][] userStr = mailhandler.fileheadList(uFolder[i]);
				for(int j=0; j < str[0].length; j++){
					String mailHead = str[1][j];
					Date receDate = mhandler.getReceiveDate(mailHead);
					long recetime = receDate.getTime();
					if(recetime<=lasttime){
						String mailpath = str[2][j];
						//mailhandler.deletedir(mailpath,1);
					}
				}
			}
				
			
			//by yangyang
			if(is1!=null){
				is1.close();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(mailhandler!=null)
				try {
					mailhandler.disconnect();
				} catch (LdapException e1) {
					e1.printStackTrace();
				}
			if (conn != null)
			try {
				conn.close();
				ConnLog.close("DownloadMailServlet");
			} catch (Exception e2) {
				e2.printStackTrace();
			}
	    }
		
	}

}
