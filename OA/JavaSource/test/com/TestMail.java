/*
 * Created on 2004-11-9
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package test.com;

import org.redflaglinux.ldap.LdapException;
import org.redflaglinux.services.dir.dirmanage;

/**
 * @author firecoral
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class TestMail {

	public static void main(String[] args) {
		
		dirmanage mailhandler = null;
		
		String domain = "";
		String ip = "";
		
//        String[] to = {"dev@xinhua.com"};
//		String[] to = {"zhiju@redflag-linux.com"};
//		String[] to = {"firecoral@126.com"};
		String[] to = {"sunchuangting@Tom.com"};
        String[] cc = {};
        String[] bcc = {};
//        String[] cc = {"dev@xinhua.com"};
//        String[] bcc = {"dev@xinhua.com"};
        String[] attachment = new String[1];
        attachment[0] = "C:\\我的report.txt";
//        attachment[1] = "C:\\邮箱ase.log";
        int flag = 3;
		try{
			
			domain = "xinhua.com";
			ip = "192.9.100.25";
			
/*			
            File attachmentfile = new File("c:\\1.txt");
			if(attachmentfile.exists()){
                System.out.println("WWWWWWWw");
				InputStream is = new FileInputStream(attachmentfile);
				long length = attachmentfile.length();
				System.out.println("the file length is:"+length);
				byte[] bytes = new byte[(int)length];
				int offset = 0;
				int rednum = 0;
				while(offset<bytes.length&&(rednum = is.read(bytes,offset,bytes.length-offset))>=0){
					offset+= rednum;
					System.out.println("the rednum is:"+rednum);
				}
				
				System.out.println("the length is:"+bytes.length);
				//attFile[0] = new String(bytes);
				is.close();
			}
			*/
			long nowtime = System.currentTimeMillis();
			String subject  = "一个测试邮件！".concat((new Long(nowtime)).toString());
					
			mailhandler = new dirmanage(ip,"dev", domain, ip, 389, "cn=root,o=redflag.com,c=ch", "simple", "redflag.com");
//			String[][] str = mailhandler.fileheadList("");
//			
//			System.out.println("the mail num is..........:"+str[0].length);
//			for(int i=0;i<str[0].length;i++){
//				
//				System.out.println(str[0][i]);
//				System.out.println(str[1][i]);
//				System.out.println(str[2][i]);
//				System.out.println("---------------------------------------");
//			}
			
			//发邮件
			mailhandler.transfermail(subject,
                    "this is a test mail!",
                    to,
                    null,
                    null,
                    attachment,
                    flag,null);
			
			//转发邮件
//			mailhandler.TransmitMail("so11005157388840.7115612745139297",to);
			
			System.out.println("send successfully!");
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			if(mailhandler!=null)
			try {
				mailhandler.disconnect();
			} catch (LdapException e1) {
				e1.printStackTrace();
			}
		}
	}
}
