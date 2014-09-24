/*
 * Created on 2005-8-25
 */
package com.icss.oa.util;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintStream;

import javax.servlet.http.HttpServlet;

import org.redflaglinux.ldap.LdapException;
import org.redflaglinux.user.usermanage;

import com.icss.common.log.MailLog;
import com.icss.oa.filetransfer.util.FiletransferTaskMachine;
import com.icss.oa.filetransfer.util.ReadRecordBackup;


/**
 * @author SUNCHUANTING
 * 2005-8-25 11:24:49
 */
public class InitMailServletServlet extends HttpServlet{

        
        //邮件服务器的IP地址
	    private String mailServerIp = PropertyManager.getProperty("archivesip");
	    //邮件服务器的域名
	    private String domain = PropertyManager.getProperty("archivesdomain");
    
	    public void init(){
	
			try {
		        
				
			    //初始化集合,包括全部的邮件用户ID
		        usermanage user=new usermanage();
		        String Array_user[] = null;   
		        
		        try {
		        	
					MailLog.write("mailServerIp  =" + mailServerIp);
					MailLog.write("domain  =" + domain);
					
		            user.connect(mailServerIp,389,"cn=root,o=redflag.com,c=CH","simple","redflag.com");
		            Array_user = user.ListAlluserByUnit(domain,"");
		            
					MailLog.write("Array_user.length  =" + Array_user.length );
					
		        } catch (LdapException e) {
		            e.printStackTrace();
		        } finally{
		        	if(user!=null){
		        		user.disConnect();
		        	}
		        }

		        //UserManagerCache.getInstance().setUsernames(Array_user);
		        //Thread thread = new Thread(UserManagerCache.getInstance());
		        //thread.setDaemon(false);
		        //thread.start();
		        
		        //暂不删除阅读记录表
		        
		        //ReadRecordBackup tt = FiletransferTaskMachine.getInstance().createTask();
		        //tt.start();
		        
			} catch (Exception e) {
				e.printStackTrace();
			} finally{
			}
		}
}
