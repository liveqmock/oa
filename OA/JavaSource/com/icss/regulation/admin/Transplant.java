package com.icss.regulation.admin;

import java.sql.*;
import java.text.SimpleDateFormat;

public class Transplant {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Connection access_conn = null;
		Statement access_st = null;
		ResultSet access_rs = null;

		Connection conn = null;
		PreparedStatement pst = null;

		String org = "";
		try {
			//access 连接 
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			String strurl = "jdbc:odbc:driver={Microsoft Access Driver (*.mdb)};DBQ=C:\\Program Files\\Regulation_Query_System\\DB\\Regulation.mdb";
			access_conn = DriverManager.getConnection(strurl, null, null);

			//oracle 连接 
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url = "jdbc:oracle:thin:@10.102.1.59:1521:orcl";
			String user = "oabase";
			String password = "oabase";
			conn = DriverManager.getConnection(url, user, password);

			String getDept = "select * from regulation";
			access_st = access_conn.createStatement();
			access_rs = access_st.executeQuery(getDept);
			
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			while (access_rs.next()) {
				int id = access_rs.getInt("id");
				String title = access_rs.getString("title");
				String dept = access_rs.getString("dept");
				String wodate = access_rs.getString("wodate");
				String mdate = access_rs.getString("mdate");
				String content = access_rs.getString("content").replaceAll("\n", "</br>").replaceAll("  ", "&nbsp;&nbsp;");
				String state = access_rs.getString("state");
				String remark = access_rs.getString("remark");
				String sql = "insert into regulation_record (id, title, org, creat_time, edit_time, flag, content, memo, personuuid, record_no)"
						+ "values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
				pst = conn.prepareStatement(sql);

				pst.setInt(1, id);
				pst.setString(2, title);
				pst.setString(3, dept);
				pst.setDate(4, wodate==null?null:new Date(df.parse(wodate).getTime()));
				pst.setDate(5, mdate==null?null:new Date(df.parse(mdate).getTime()));
				pst.setString(6, state);
				pst.setString(7, content);
				pst.setString(8, null);
				pst.setString(9, null);
				pst.setString(10, remark);
				pst.execute();
				pst.close();
			}
			Statement st = conn.createStatement();
			st.executeUpdate("update regulation_record set flag=2 where flag=1 ");
			st.executeUpdate("update regulation_record set flag=1 where flag=0 ");
			st.executeUpdate("update regulation_record set flag=0 where flag=2 ");
			st.executeUpdate("update regulation_record set org=(select orguuid from regulation_org where orgname= org)");
			st.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				access_rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				access_st.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				access_conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			try {
				pst.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
