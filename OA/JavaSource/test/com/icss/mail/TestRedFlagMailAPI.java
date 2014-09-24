/*
 * Created on 2004-6-11
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package test.com.icss.mail;

import org.redflaglinux.ldap.Ldap;
import org.redflaglinux.ldap.LdapException;
import org.redflaglinux.services.dir.dirmanage;
import org.redflaglinux.user.usermanage;
import com.icss.resourceone.sdk.extension.IUserExtendProcessor;

/**
 * @author lichg
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class TestRedFlagMailAPI {

	public static void main(String[] args) {
		//		Ldap ldap = new Ldap();
		//		usermanage umng = new usermanage();
		try {
			//			ldap.connect(
			//				"192.9.100.25",
			//				389,
			//				"cn=root,o=redflag.com,c=ch",
			//				"simple",
			//				"redflag.com");
			//			umng.connect(
			//				"192.9.100.25",
			//				389,
			//				"cn=root,o=redflag.com,c=ch",
			//				"simple",
			//				"redflag.com");	
			
			
			dirmanage dirmng =
				new dirmanage(
					"192.9.100.25",
					"dev",
					"xinhua.com",
					"192.9.100.25",
					389,
					"cn=root,o=redflag.com,c=ch",
					"simple",
					"redflag.com");
			dirmng.createdir("Sent");
			
			System.out.println("no error.....");
		} catch (LdapException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
