/*
 * 创建日期 2005-9-12
 *
 * 更改所生成文件模板为
 * 窗口 > 首选项 > Java > 代码生成 > 代码和注释
 */
package test.MailPath;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.redflaglinux.ldap.LdapException;
import org.redflaglinux.user.usermanage;

/**
 * @author Administrator
 *
 * 更改所生成类型注释的模板为
 * 窗口 > 首选项 > Java > 代码生成 > 代码和注释
 */
public class getConnetion {

	static Connection conn = null;

	static String url = "jdbc:oracle:oci:@orcl_192.9.100.25";
	static String user1 = "oabase";
	static String password = "oabase";

	static usermanage user = null;

	static String MailIP = "192.9.100.26";
	static String Domain = "xinhua.com";

	/**
	 * @return 返回数据库的链接
	 */
	public static Connection getDBConn() {

		try {
           Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();
			conn = DriverManager.getConnection(url, user1, password);

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		return conn;

	}

	/**
	 *   关闭数据库链接
	 */
	public static void CloseConnection() {

		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void CloseMailConnection(){
	    if(user!=null){
	        try {
                user.disConnect();
            } catch (LdapException e) {
                e.printStackTrace();
            }
	    }
	}

	/**
	 * @return 返回Mail服务器的链接
	 */
	public static usermanage getMailConn() {

		user = new usermanage();
		try {
		   user.connect(
				MailIP,
				389,
				"cn=root,o=redflag.com,c=CH",
				"simple",
				"redflag.com");
		} catch (LdapException e) {
			e.printStackTrace();
		}

		return user;
	}

	/**
	 * @return 得到全部mail人员的人员列表
	 */
	public static String[] getAllMailList() {

		String list[] = null;
		try {
		    user = new usermanage();
		    user.connect(
					MailIP,
					389,
					"cn=root,o=redflag.com,c=CH",
					"simple",
					"redflag.com");
		    list= user.ListAlluserByUnit("xinhua.com","");
		} catch (LdapException e) {
			e.printStackTrace();
		}
		return list;

	}
	
	/**
	 * @param userid
	 * @return 根据用户ID＆域名获取这个用户的根路径
	 */
	public static String getPath_user(String userid){
		
		String rootpath = null;
		
		try {
		    user = new usermanage();
		    user.connect(
					MailIP,
					389,
					"cn=root,o=redflag.com,c=CH",
					"simple",
					"redflag.com");
			rootpath = user.getUserRootPath(userid,Domain);
		} catch (LdapException e) {
			e.printStackTrace();
		} 
		
		return rootpath;
	}

	/**
	 * @return
	 */
	public static Connection getConn() {
		return conn;
	}

	/**
	 * @return
	 */
	public static String getDomain() {
		return Domain;
	}

	/**
	 * @return
	 */
	public static String getMailIP() {
		return MailIP;
	}

	/**
	 * @return
	 */
	public static String getPassword() {
		return password;
	}

	/**
	 * @return
	 */
	public static String getUrl() {
		return url;
	}

	/**
	 * @return
	 */
	public static usermanage getUser() {
		return user;
	}

	/**
	 * @return
	 */
	public static String getUser1() {
		return user1;
	}

	/**
	 * @param connection
	 */
	public static void setConn(Connection connection) {
		conn = connection;
	}

	/**
	 * @param string
	 */
	public static void setDomain(String string) {
		Domain = string;
	}

	/**
	 * @param string
	 */
	public static void setMailIP(String string) {
		MailIP = string;
	}

	/**
	 * @param string
	 */
	public static void setPassword(String string) {
		password = string;
	}

	/**
	 * @param string
	 */
	public static void setUrl(String string) {
		url = string;
	}

	/**
	 * @param usermanage
	 */
	public static void setUser(usermanage usermanage) {
		user = usermanage;
	}

	/**
	 * @param string
	 */
	public static void setUser1(String string) {
		user1 = string;
	}

}
