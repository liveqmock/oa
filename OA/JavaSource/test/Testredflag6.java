/*
 * Created on 2005-7-2
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package test;

import org.redflaglinux.ldap.LdapException;
import org.redflaglinux.services.dir.dirmanage;

/**
 * @author Administrator
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class Testredflag6 {

	public static void main(String[] args) {
		
		dirmanage mailhandler = null;
		
		try {
			mailhandler = new dirmanage("192.9.100.25", "dev", "xinhua.com",
					"192.9.100.25", 389, "cn=root,o=redflag.com,c=ch",
					"simple", "redflag.com");
			
			 String text = "ddddddddddddddddddddd";
			 String subject = "test1";
			 String[] to = {"dev@xinhua.com"};
			 int flag = 1;
			 
			String[] attachment = {"c:\\ssss.txt"};
			System.out.println("start!");
			mailhandler.transfermail(subject,text ,to,null,null,attachment,flag,null);
			System.out.println("end!");
		} catch (LdapException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			try {
				//关闭邮件连接
				mailhandler.disconnect();
				
			} catch (LdapException e) {
				e.printStackTrace();
			}
		}
		
	}
}
