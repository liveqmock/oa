/*
 * �������� 2005-9-13
 */
package com.icss.oa.util;

import java.sql.Connection;
import java.util.Hashtable;
import java.util.Map;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.redflaglinux.ldap.LdapException;
import org.redflaglinux.user.usermanage;

import test.MailPath.Mailhandler;
import test.MailPath.getConnetion;

/**
 * @author sunchuanting
 */
public class SetUserPath implements Job {

	//	�ʼ���������IP��ַ
	private String mailServerIp = PropertyManager.getProperty("archivesip");

	//  �ʼ�������������
	private String domain = PropertyManager.getProperty("archivesdomain");

	// ����һ��HashTable
	private Map map = null;

	// ����һ��mail������������
	private usermanage user = null;

	private Connection conn = getConnetion.getDBConn();

	private Mailhandler handler = new Mailhandler(conn);

	public void execute(JobExecutionContext arg0) throws JobExecutionException {

		//	��ʼ������,����ȫ�����ʼ��û�ID

		user = new usermanage();

		String Array_user[] = null;

		try {
			user.connect(mailServerIp, 389, "cn=root,o=redflag.com,c=CH", "simple", "redflag.com");

			//			1��ȡ��ȫ����mail�û��ķ���������
			Array_user = user.ListAlluserByUnit(domain, "");

			System.out.println("ssssss Array_user.length= " + Array_user.length);

			//			2��ɾ��mail_path���е�ȫ����¼
			handler.DelMailPath();

			//			3������ȫ����mail�û�����Ӧ��path�������ݱ��� mail_path;������һ���µ�map���󲢶�����ֵ

			for (int i = 0; i < Array_user.length; i++) {

				String usrname = Array_user[i];
				System.out.println("ssssss usrname= " + usrname);

				this.InsertDBandMap(usrname);
			}

			//			4:���ڴ���ԭ�е�map������clear����������Ӧ�õ�3�����map��
			UserManagerCache.getInstance().setMap(map);

		} catch (LdapException e) {
			e.printStackTrace();
		}

	}

	public void InsertDBandMap(String usrname) {

		map = new Hashtable();

		try {
			user.connect(mailServerIp, 389, "cn=root,o=redflag.com,c=CH", "simple", "redflag.com");

			//			�����û�ID��������ȡ����û��ĸ�·��
			String rootpath = user.getUserRootPath(usrname, domain);

			handler.InsertMailPath(usrname, rootpath);

			map.put(usrname, rootpath);

		} catch (LdapException e) {
			e.printStackTrace();
		}

	}

}
