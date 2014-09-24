package com.icss.oa.filetransfer.util;

/**
 * @author liuxy
 * ���ڴ����ʼ����Ӷ���
 */

import org.redflaglinux.services.dir.dirmanage;
import org.redflaglinux.user.usermanage;

import com.icss.oa.util.PropertyManager;

public class MailhandlerFactory {
	private static dirmanage mailhandler = null;
//	private static usermanage usermanage = null;
	private static String ip = PropertyManager.getProperty("archivesip");
	private static String backupip = PropertyManager.getProperty("backupip");
	private static String senddomain = PropertyManager.getProperty("archivesdomain");


	private MailhandlerFactory(){	}
	
	
	/**
	 * ���dirmanager��������ʼ����������Ӳ��ϣ����ӱ����ʼ���������
	 * @param userid
	 * @return
	 */
	public static dirmanage getHandler(String userid){
		try {
			dirmanage mailhandler = new dirmanage(ip, userid, senddomain, ip, 389, "cn=root,o=redflag.com,c=ch", "simple", "redflag.com");
			return mailhandler ;
		} catch (Exception e) {
			//System.err.println(e);
			//e.printStackTrace();
			//lizb 2009-11-26
			System.err.println("-----MailhandlerFactory1Start----");
			System.err.println("��һ������LDAPʧ�ܣ�������Ϣ���£�");
			System.err.println("ip=" + ip + "\nuserid=[" + userid + "]\nsenddomain=" + senddomain);
			System.err.println("-----MailhandlerFactory1End----");
			try {
				dirmanage mailhandler = new dirmanage(backupip, userid, senddomain, backupip, 389, "cn=root,o=redflag.com,c=ch", "simple", "redflag.com");
				return mailhandler ;
			} catch (Exception e1) {
				//lizb 2009-11-26
				System.err.println("-----MailhandlerFactory2Start----");
				System.err.println("��һ������LDAPʧ�ܣ�������Ϣ���£�");
				System.err.println("ip=" + backupip + "\nuserid=[" + userid + "]\nsenddomain=" + senddomain);
				System.err.println("����LDAP����ʧ�ܣ�NULL");
				System.err.println("-----MailhandlerFactory2End----");
				return null;
			}
		}
			
	}
		
	
	
	public static dirmanage getHandler(){

		if(mailhandler == null){
			try {
				mailhandler = new dirmanage(ip);
				return mailhandler ;
			} catch (Exception e) {
				System.err.println(e);
				e.printStackTrace();
				try {
					mailhandler = new dirmanage(backupip);
					return mailhandler ;
				} catch (Exception e1) {
					System.err.println(e);
					e1.printStackTrace();
					return null;
				}
			}
			
		}else{
			return mailhandler ;
		}
		
	}
	
	public  static usermanage getUserManager(){

			try {
				usermanage usermanage = new usermanage();
				usermanage.connect(ip,389,"cn=root,o=redflag.com,c=CH","simple","redflag.com");
				return usermanage;
			} catch (Exception e) {
				System.err.println(e);
				e.printStackTrace();
				try {
					usermanage usermanage = new usermanage();
					usermanage.connect(backupip,389,"cn=root,o=redflag.com,c=CH","simple","redflag.com");
					return usermanage;
				} catch (Exception e1) {
					System.err.println(e);
					e1.printStackTrace();
					return null;
				}
			}
		}
		
	
	
	public static void main(String[] args){
		
	}
	
	
	
	}

	
	

