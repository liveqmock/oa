/*
 * Created on 2004-5-12
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.icss.oa.filetransfer.handler;

import javax.mail.Folder;
import javax.mail.Store;
//import org.redflaglinux.user.usermanage;
/**
 * @author Administrator
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class MailFolder {
	public Folder getUserFolder(String ip,String userid,String domain,String pwd) throws Exception {
		javax.mail.Session mailSession = MailLocator.getInstance();
//
//		usermanage user = new usermanage();
//		boolean conn = true;
//		try
//		{
//		conn = user.connect("192.9.100.25", 389, "cn=root,o=redflag.com,c=ch", "simple", "redflag.com");
//		System.out.print("conn = "+conn);
//
//		}
//		catch(Exception ex)
//		{
//			System.out.print(ex.getMessage());
//			ex.printStackTrace();
//		}
//		
		Store store = mailSession.getStore();

		//TODO �����������Ҫ��ȡ�����ļ��ſ���
		store.connect(ip, userid + "@" +domain, "");
		//store.connect("192.9.100.25", userid + "@xinhua.com", "1");
		Folder folder = store.getFolder("inbox");
		return folder;
	}
}
