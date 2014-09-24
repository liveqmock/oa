/*
 * 创建日期 2005-12-26
 *
 * 更改所生成文件模板为
 * 窗口 > 首选项 > Java > 代码生成 > 代码和注释
 */
package test;

import org.redflaglinux.user.usermanage;

/**
 * @author Administrator
 *
 * 更改所生成类型注释的模板为
 * 窗口 > 首选项 > Java > 代码生成 > 代码和注释
 */
public class test_1226 {

	//	构造一个mail服务器的链接
	private static usermanage user = null;

	public static void main(String[] args) {

		//		初始化集合,包括全部的邮件用户ID

		user = new usermanage();

		try {
			user.connect(
				"192.9.100.26",
				389,
				"cn=root,o=redflag.com,c=CH",
				"simple",
				"redflag.com");

			//					1：取得全部的mail用户的放入数组中
			String Array_user[] = user.ListAlluserByUnit("xinhua.com", "");
			String path ;

			long start_time = System.currentTimeMillis();
			System.out.println("---start time " + start_time);

			for (int i = 0; i < Array_user.length; i++) {
				if(i==100){ 
					//break;
				}
				path = user.searchvalue("cn="+Array_user[i]+",ou=xinhua.com,o=redflag.com,c=CH",Array_user[i],"mailboxpath");
				System.out.println("path ============================="+path);
			}

			long end_time = System.currentTimeMillis();

			System.out.println("---end time " + end_time);
			System.out.println("---totel time " + (end_time - start_time));
			System.out.println("---Array_user.length=========== " + (Array_user.length));

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
