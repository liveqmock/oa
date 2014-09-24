/*
 * Created on 2004-4-28
 *
 *将数据库中邮箱为20M的用户,在邮件服务器中的大小也改为20M。
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
public class Testredflag2{

	public static void main(String args[])
	{

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet  rs = null;
		try {
//			try{

			usermanage user=new usermanage();
			user.connect("10.102.1.205",389,"cn=root,o=redflag.com,c=CH","simple","redflag.com");

			
			Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();   
			String url="jdbc:oracle:oci:@orcl_10.102.1.200";
//			  orcl为你的数据库的SID 
			String user1="oabase";
			String password="oabase";
			conn= DriverManager.getConnection(url,user1,password);

			String  sql= " select fs_uid from filetransfer_set where fs_size=20 ";
			pstmt = conn.prepareStatement(sql);
			rs= pstmt.executeQuery();

			int j=0;
			int m=0;
			while(rs.next()){
			j++;
			System.out.println(rs.getString(1));
			user.changemailsize(rs.getString(1),"oa.xinhua.org","20971520");
			}//while
			System.out.println("共有"+j+"个邮箱大小为20M的人*");
			
//			user.changemailsize("zhuangchaoying","oa.xinhua.org","20971520");

		} catch (LdapException e) {
			e.printStackTrace();
			
		} catch (Exception e) {
//			throw new RuntimeException("wrong" + e);
			e.printStackTrace();
			
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
//		 System.out.println(i*3600000);
//		Calendar cal = Calendar.getInstance();
//		cal.setTime(new Date(System.currentTimeMillis()));
//		int year = cal.get(Calendar.YEAR);
//		int month = cal.get(Calendar.MONTH)+1; 
//		int day = cal.get(Calendar.DATE);
//		System.out.println(year+"ww"+month+"ww"+day);
//		String tempStr = "我的一个测试aaaa";
//		System.out.println(tempStr.length());
	}

}
