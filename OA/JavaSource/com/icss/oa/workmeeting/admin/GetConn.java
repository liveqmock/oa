/*
 * Created on 2004-8-7
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package com.icss.oa.workmeeting.admin;

import java.sql.Connection;

import com.icss.common.log.ConnLog;

/**
 * @author Administrator
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public class GetConn {
	private static String sqlserverDriverClassName = "com.microsoft.jdbc.sqlserver.SQLServerDriver";
	private static String sqlserverDriverURL = "jdbc:microsoft:sqlserver://192.9.100.9:1433;DatabaseName=xinhuaoa";
	private static String sqlserverUserName = "sa";
	private static String sqlserverPassword = "1";

	private static String orclDriverClassName = "oracle.jdbc.driver.OracleDriver";
	private static String orclDriverURL = "jdbc:oracle:oci:@orcl_192.9.100.25";
	private static String orclUserName = "oabase";
	private static String orclPassword = "oabase";

	public static Connection getSqlServerConn() {
		Connection conn = null;
		try {
			Class.forName(sqlserverDriverClassName);
			conn = java.sql.DriverManager.getConnection(sqlserverDriverURL, sqlserverUserName, sqlserverPassword);
			ConnLog.open("GetConn.getSqlServerConn");
			System.out.println("成功获得sqlserver连接：");
		} catch (Exception e) {
			System.out.println("无法获得sqlserver连接：" + e.toString());
		}
		return conn;
	}
	public static Connection getOrclConn() {
		Connection conn = null;
		try {
			Class.forName(orclDriverClassName);
			conn = java.sql.DriverManager.getConnection(orclDriverURL, orclUserName, orclPassword);
			ConnLog.open("GetConn.getOrclConn");
			System.out.println("成功获得orcl连接：");
		} catch (Exception e) {
			System.out.println("无法获得orcl连接：" + e.toString());
		}
		return conn;
	}
	public static void closeConn(Connection conn) {
		if (conn != null) {
			try {
				conn.close();
				ConnLog.close("GetConn.??????");
			} catch (Exception e) {
				System.out.println("wangerr:" + e.toString());
			}
		}
	}
}
