/*
 * �������� 2005-9-12
 *
 * �����������ļ�ģ��Ϊ
 * ���� > ��ѡ�� > Java > �������� > �����ע��
 */
package test.MailPath;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.redflaglinux.ldap.LdapException;
import org.redflaglinux.user.usermanage;

/**
 * @author Administrator
 *
 * ��������������ע�͵�ģ��Ϊ
 * ���� > ��ѡ�� > Java > �������� > �����ע��
 */
public class Mailhandler {

	Connection conn = null;
	
	Statement stmt = null;
	ResultSet rs = null;
	
	
	
	public Mailhandler(Connection conn){
		this.conn = conn;
	}

	/**�����û���·����Ϣ
		 * @param userid
		 * @param path
		 */
	public void InsertMailPath(String userid, String path) {

		String sql =
			"INSERT INTO USER_PATH VALUES ('" + userid + "','" + path + "')";

		try {
			stmt = conn.createStatement();
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}

		}
	}

	/**
	 * 	ɾ���û���·����Ϣ
	 */
	public void DelMailPath(String userid) {

		String sql = "DELETE FROM USER_PATH WHERE USERID ='" + userid+"'";

		try {
			stmt = conn.createStatement();
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}

		}

	}
	
	/**
		 * 	ɾ��ȫ�����û���·����Ϣ
		 */
		public void DelMailPath() {

			String sql = "DELETE FROM USER_PATH " ;

			try {
				stmt = conn.createStatement();
				stmt.executeUpdate(sql);
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {

				if (stmt != null) {
					try {
						stmt.close();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}

			}

		}

	/**
	 *  �����û���·����Ϣ
	 */
	public void UpdateMailPath(String userid, String path) {
		String sql =
			"UPDATE USER_PATH SET PATH = '" + path + "' WHERE USERID = '" + userid + "'";

		try {
			stmt = conn.createStatement();
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}

		}

	}

	/**
	 * 	��ѯ�û���·����Ϣ
	 */
	public UserPath QueryMailPath(String userid) {

		String sql = "SELECT * FROM USER_PATH WHERE USERID = '" + userid+"'";

		UserPath userpathVO = new UserPath();

		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);

			userpathVO.setUserid(rs.getString("USERID"));
			userpathVO.setUser_path(rs.getString("PATH"));

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}

			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}

		}

		return userpathVO;

	}

	/** ����һ���˵�userid�жϴ����Ƿ����path
	 * @param userid
	 * @return
	 */
	public boolean isExitMailPaht(String userid) {

		boolean flag = false;

		String sql =
			"SELECT COUNT(*) AS COUNT_PATH FROM USER_PATH WHERE USERID = '"
				+ userid +"' ";
		System.out.println("sql ="+sql);
		

		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			int cont_path = 0;
            
            if (rs != null) {
				if (rs.next()) {
				    cont_path = rs.getInt("COUNT_PATH");
				    System.out.println("cont_path ="+cont_path);
				}
			}
    
			if (cont_path > 0) {
				flag = true;
			} else {
				flag = false;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}

			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}

		}

		return flag;

	}
	
	
	
	
	

}
