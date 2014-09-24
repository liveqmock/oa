/*
 * �������� 2005-9-12
 *
 * �����������ļ�ģ��Ϊ
 * ���� > ��ѡ�� > Java > �������� > �����ע��
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
 * ��������������ע�͵�ģ��Ϊ
 * ���� > ��ѡ�� > Java > �������� > �����ע��
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
	 * @return �������ݿ������
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
	 *   �ر����ݿ�����
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
	 * @return ����Mail������������
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
	 * @return �õ�ȫ��mail��Ա����Ա�б�
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
	 * @return �����û�ID��������ȡ����û��ĸ�·��
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
