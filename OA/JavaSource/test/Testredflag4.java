/*
 * Created on 2004-4-25
 *
 * 
 */
package test;

import java.util.ArrayList;
import java.util.List;

import org.redflaglinux.ldap.LdapException;
import org.redflaglinux.services.dir.dirmanage;

/**
 * @author Administrator
 * 
 *  
 */
public class Testredflag4 {

	public static void main(String[] args) {
		
		dirmanage mailhandler = null;
		
		try {
			mailhandler = new dirmanage("192.9.100.25", "dev", "xinhua.com",
					"192.9.100.25", 389, "cn=root,o=redflag.com,c=ch",
					"simple", "redflag.com");
			
//			String[][] str = mailhandler.fileheadList("");
			String[][] str = mailhandler.fileHeadPage("", 1, 0, true, 13,"d");
//			String[][] str = mailhandler.fileHeadPage("", 1, 2, false, 13,"d");
			int i = mailhandler.getTotalMails("","d"); 
			System.out.println("ggggggggggggggggggggg   "+i);
			
			/*String firstword = "";
			for (int i = 0; i < str[0].length; i++) {
				for (int j = 0; j < str.length; j++) {
					try {
						System.out.println("str["+i+"]["+j+"]   =" +str[i][j]);
					} catch (RuntimeException e2) {
						e2.printStackTrace();
					}
					
				}
			}*/
			
		} catch (LdapException e) {
			e.printStackTrace();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}
}
