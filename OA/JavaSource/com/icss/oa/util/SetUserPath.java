/*
 * 创建日期 2005-9-13
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

	//	邮件服务器的IP地址
	private String mailServerIp = PropertyManager.getProperty("archivesip");

	//  邮件服务器的域名
	private String domain = PropertyManager.getProperty("archivesdomain");

	// 构在一个HashTable
	private Map map = null;

	// 构造一个mail服务器的链接
	private usermanage user = null;

	private Connection conn = getConnetion.getDBConn();

	private Mailhandler handler = new Mailhandler(conn);

	public void execute(JobExecutionContext arg0) throws JobExecutionException {

		//	初始化集合,包括全部的邮件用户ID

		user = new usermanage();

		String Array_user[] = null;

		try {
			user.connect(mailServerIp, 389, "cn=root,o=redflag.com,c=CH", "simple", "redflag.com");

			//			1：取得全部的mail用户的放入数组中
			Array_user = user.ListAlluserByUnit(domain, "");

			System.out.println("ssssss Array_user.length= " + Array_user.length);

			//			2：删除mail_path表中的全部记录
			handler.DelMailPath();

			//			3：遍历全部的mail用户将对应的path放入数据表中 mail_path;并构造一个新的map对象并对它赋值

			for (int i = 0; i < Array_user.length; i++) {

				String usrname = Array_user[i];
				System.out.println("ssssss usrname= " + usrname);

				this.InsertDBandMap(usrname);
			}

			//			4:将内存中原有的map的引用clear掉；将其新应用到3构造的map上
			UserManagerCache.getInstance().setMap(map);

		} catch (LdapException e) {
			e.printStackTrace();
		}

	}

	public void InsertDBandMap(String usrname) {

		map = new Hashtable();

		try {
			user.connect(mailServerIp, 389, "cn=root,o=redflag.com,c=CH", "simple", "redflag.com");

			//			根据用户ID＆域名获取这个用户的根路径
			String rootpath = user.getUserRootPath(usrname, domain);

			handler.InsertMailPath(usrname, rootpath);

			map.put(usrname, rootpath);

		} catch (LdapException e) {
			e.printStackTrace();
		}

	}

}
