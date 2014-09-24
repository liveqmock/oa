/*
 * 创建日期 2005-9-12
 *
 * 更改所生成文件模板为
 * 窗口 > 首选项 > Java > 代码生成 > 代码和注释
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
 * 更改所生成类型注释的模板为
 * 窗口 > 首选项 > Java > 代码生成 > 代码和注释
 */
public class Mailhandler {

	Connection conn = null;
	
	Statement stmt = null;
	ResultSet rs = null;
	
	
	
	public Mailhandler(Connection conn){
		this.conn = conn;
	}

	/**插入用户的路径信息
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
	 * 	删除用户的路径信息
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
		 * 	删除全部的用户的路径信息
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
	 *  更新用户的路径信息
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
	 * 	查询用户的路径信息
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

	/** 根据一个人的userid判断此人是否存在path
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
