/*
 * Created on 2004-4-28
 *
 *将数据库中有，但邮件服务器中没有的用户添加到邮件服务器。
 */
package test;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.redflaglinux.ldap.LdapException;
import org.redflaglinux.services.dir.dirmanage;
import org.redflaglinux.user.usermanage;

import com.icss.oa.config.FileTransferConfig;
import com.icss.oa.filetransfer.handler.MailFolder;
import com.icss.oa.util.PropertyManager;
import com.icss.resourceone.common.logininfo.UserInfo;
import com.icss.resourceone.sdk.framework.Context;

/**
 * @author Administrator
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class Testredflag {

	public static void main(String args[]) {

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			//			try{

			usermanage user = new usermanage();
			user.connect(
				"10.102.1.205",
				389,
				"cn=root,o=redflag.com,c=CH",
				"simple",
				"redflag.com");
			//String list[] = user.ListAlluserByUnit("oa.xinhua.org","");
			String list[] =
				{
					"duiwaiewen",
					"duiwaifawen",
					"duiwaiawen",
					"duiwaixiwen",
					"jinxuegeng",
					"duiwaipuwen",
					"dwbbwh",
					"leiming1",
					"liudongkai",
					"jiaanping",
					"yangjigang",
					"duanmulaidi",
					"changyong",
					"wangpinxing",
					"fansongjiu",
					"zhangyongqing",
					"guoguangqin",
					"liyong",
					"zhangchengzhi",
					"xionglei",
					"xiangjianguo",
					"wangyanping",
					"weitiemin",
					"wanghongyu",
					"cheyuming",
					"ljie",
					"zhaozhuxiu",
					"lihuailin",
					"yangzidi",
					"lishengjiang",
					"jixiaofeng",
					"huanglinlin",
					"liusuqin",
					"cuiruiqiang",
					"yinshuqin",
					"fanliqing",
					"huchuangwei",
					"yuyuanjiang",
					"zhaoxinbing",
					"hezili",
					"guqianjiang",
					"lihaitang",
					"qideliang",
					"zhaowei",
					"xuesongmei",
					"yangguoqiang",
					"qiantong",
					"xuxingtang",
					"luohui",
					"nisiyi",
					"wuxiaojun",
					"chenyao",
					" huangyan",
					"gaofuhai",
					"huozhongyou",
					"jiangshugen",
					"lizhiping",
					"wangbo",
					"chenyongfang",
					"dwb000",
					"huangxiaonan",
					"yanwenbin",
					"liuchunnian",
					"shijianguo",
					"zhouxiaozheng",
					"shengzuren",
					"peijianrong",
					"dingdeqi",
					"wangjingzhong",
					"linuer" };
			System.out.println("list的大小= " + list.length);

			for (int i = 0; i < list.length; i++) {
				System.out.println("list" + i + " =" + list[i]);
			}

			Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();
			String url = "jdbc:oracle:oci:@orcl_10.102.1.200";
			//			  orcl为你的数据库的SID 
			String user1 = "roeee";
			String password = "roeee";
			conn = DriverManager.getConnection(url, user1, password);

			String sql =
				" SELECT userid FROM ro_personaccount group by userid ";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			int j = 0;
			int m = 0;
			int isExit = 0;
			while (rs.next()) {
				j++;
				int flag = 0;
				for (int i = 0; i < list.length; i++) {
					if (rs.getString(1).equals(list[i])) {
						flag = 1;
						break;
					} else {
						flag = 0;
					}
				}

				if (flag == 1) {
					isExit++;
					System.out.println("邮件服务器中有 " + rs.getString(1));
				} else {
					m++;
					System.out.println(
						"*****************邮件服务器中没有 " + rs.getString(1));
					//				try {
					//				user.adduser(
					//									"10.102.1.205",						//邮件服务器的地址 
					//									rs.getString(1),				//用户名
					//									"",									//用户密码
					//									"oa.xinhua.org",						//域名
					//									"技术局",//用户所在部门名称
					//									20971520							//邮箱空间大小
					//									);
					//				}catch (LdapException e) {
					//					e.printStackTrace();
					//				}
					//				System.out.println("为"+rs.getString(1)+"建邮箱成功");
				}

			} //while

			System.out.println(
				"数据库服务器中有 " + j + "个用户,数据库服务器中有" + m + "个用户没有邮箱");
			System.out.println("in " + isExit);
			user.disConnect();
			//				dirmanage test = new dirmanage("192.9.100.25", "dev", "xinhua.com", "192.9.100.25", 389, "cn=root,o=redflag.com,c=ch", "simple", "redflag.com");
			//				test.createdir("Sent");
			//				test.disconnect();
			//				System.out.println("111111111111111");
			//
			//				String str[][] = new String [3][50];
			//				str = test.fileheadList("inbox");
			//				for(int i = 0; i < 3; i++)
			//				{
			//					System.out.println("000000000 = "+str[0][i]);
			//					System.out.println("111111111 = "+str[1][i]);
			//					System.out.println("222222222 = "+str[2][i]);
			//				}
			//			}catch(Exception ex){
			//				ex.printStackTrace();
			//			}
			//			
		} catch (LdapException e) {
			e.printStackTrace();
			
		} catch (Exception e) {
			throw new RuntimeException("wrong" + e);

		} finally {
			
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
			
			if(pstmt!=null){
				try {
					pstmt.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
			
			if(conn!=null){
				try {
					conn.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
			
			
		}



		//	   long i = Long.parseLong("08");
		//       System.out.println(i*3600000);
		//		Calendar cal = Calendar.getInstance();
		//		cal.setTime(new Date(System.currentTimeMillis()));
		//		int year = cal.get(Calendar.YEAR);
		//		int month = cal.get(Calendar.MONTH)+1; 
		//		int day = cal.get(Calendar.DATE);
		//		System.out.println(year+"ww"+month+"ww"+day);
		//      String tempStr = "我的一个测试aaaa";
		//      System.out.println(tempStr.length());
	}

}
