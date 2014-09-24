package com.fredck.FCKeditor;

import org.redflaglinux.services.dir.dirmanage;
import org.redflaglinux.user.usermanage;

import com.icss.oa.util.PropertyManager;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			usermanage user=new usermanage();
			//String ip = PropertyManager.getProperty("archievesip");
			//String senddomain = PropertyManager.getProperty("archivesdomain");
			//String ldapip =  PropertyManager.getProperty("archivesip");
			user.connect("10.102.1.104",389,"cn=root,o=redflag.com,c=CH","simple","redflag.com");
			user.adduser("10.102.1.104","lxy","lxy","oa.xinhua.org","OA",100);

			// TODO 自动生成方法存根
			//dirmanage mailhandler = new dirmanage("10.102.1.104", "dev",
					//"oa.xinhua.org", "10.102.1.104", 389,
					//"cn=root,o=redflag.com,c=ch", "simple", "redflag.com");
			//mailhandler.transfermail("1","11","dev@oa.xinhua.org",null,nukl,attachment,flag,null);
			//String[][] str = mailhandler.fileheadList("");
			System.out.println("yes");
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}		
		
//		Process   p;
//		try {
//			p = Runtime.getRuntime().exec("cmd.exe dir");
//			BufferedReader   input   =   new   BufferedReader(new   InputStreamReader(p.getInputStream())); 
//			String   line   = " "; 
//			while((line   =   input.readLine())!=null){ 
//			System.out.println(line); 
//			} 
//			input.close();
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//		}		 
		

	}

}
