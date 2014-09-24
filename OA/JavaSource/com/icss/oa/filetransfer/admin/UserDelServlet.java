/*
 * Created on 2004-9-17
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.icss.oa.filetransfer.admin; 

import java.io.IOException;
import java.sql.Connection;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.redflaglinux.ldap.LdapException;
import org.redflaglinux.services.dir.dirmanage;

import com.icss.common.log.ConnLog;
import com.icss.j2ee.servlet.ServletBase;
import com.icss.j2ee.util.Globals;
import com.icss.oa.filetransfer.handler.FiletransferSetHandler;
import com.icss.oa.filetransfer.handler.MessageHandler;
import com.icss.oa.filetransfer.util.MailhandlerFactory;
import com.icss.oa.util.PropertyManager;
import com.icss.resourceone.common.logininfo.UserInfo;
import com.icss.resourceone.sdk.framework.Context;

/**
 * @author firecoral
 * 
 *  ɾ��һ���û�һ��ʱ���ڵ��ʼ��������ļ����£�
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class UserDelServlet extends ServletBase{

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
			ConnLog.open("UserDelServlet");
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
			
			//���������ñ��õ�׼ȷ�������û������������������ַ
			FiletransferSetHandler ftsHandler = new FiletransferSetHandler(conn);
			String username = ftsHandler.getUserid(user.getPersonUuid());
			
			String domain = PropertyManager.getProperty("archivesdomain");
			String ip = PropertyManager.getProperty("archivesip");
			
			mailhandler = MailhandlerFactory.getHandler(username);
			
			//********�õ�ϵͳ�ļ��������ʼ��������ʼ���ʱ�䣨long��************************************
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
			
			//********�õ��Զ����ļ��������ʼ��������ʼ���ʱ�䣨long��***********************************
			//�õ��û��Զ�����ļ���
			String[] uFolder = mailhandler.dirList("");
			//�û��ļ�������
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
			
			//*********�ϲ������Եõ���Сʱ��***************************
			smallesttime = (systime<usertime)?systime:usertime;
			//�����º��ʱ��
			long lasttime = smallesttime + 90*86400000l;
			
			//**********��ʼɾ��****************************************
			
			//***********ɾ��ϵͳ�ļ����µ��ļ�**************
			for(int i=0; i < str[0].length; i++){
				String mailHead = str[1][i];
				Date receDate = mhandler.getReceiveDate(mailHead);
				String subject = mhandler.getSubject(mailHead);
				byte[] content = mailhandler.viewmail(str[2][i]);
				String content1 = new String(content);
				String send = mhandler.getFrom(mailHead);
				
				
				long recetime = receDate.getTime();
				if(recetime<=lasttime){
					String mailpath = str[2][i];
					mailhandler.deletedir(mailpath,1);
				}
			}
			//***********ɾ���Զ����ļ����µ��ļ�***************
			//�õ��û��Զ�����ļ���
			for(int i=0;i<uFolder.length;i++){
				String[][] userStr = mailhandler.fileheadList(uFolder[i]);
				for(int j=0; j < str[0].length; j++){
					String mailHead = str[1][j];
					Date receDate = mhandler.getReceiveDate(mailHead);
					long recetime = receDate.getTime();
					if(recetime<=lasttime){
						String mailpath = str[2][j];
						mailhandler.deletedir(mailpath,1);
					}
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}finally{
			
			if(mailhandler!=null){
				try {
					mailhandler.disconnect();
				} catch (LdapException e1) {
					e1.printStackTrace();
				}				
			}

			if (conn != null){
				try {
					conn.close();
					ConnLog.close("UserDelServlet");
				} catch (Exception e2) {
					e2.printStackTrace();
				}			
			}

	    }
		
	}

}
