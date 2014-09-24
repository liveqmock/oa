/*
 * Created on 2004-8-7
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package com.icss.oa.workmeeting.admin;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;

//import com.icss.wangimp.vo.InfoMsgVO;
/**
 * @author Administrator
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public class ImpGzwjviewRec {

	//	private static String datePath="D:\\wangimp\\date\\attachfile\\";//附件文件路径

	public static void main(String[] args) {

		Connection sqlConn = GetConn.getSqlServerConn();
		Connection orclConn = GetConn.getOrclConn();

		PreparedStatement pstate = null;
		PreparedStatement pstate2 = null;
		
		ResultSet argRes = null;
		try {
			pstate = sqlConn.prepareStatement("SELECT * FROM gzwjview_rec ");

			argRes = pstate.executeQuery();

			int i = 0;
			while (argRes.next()) {

				i++;

				pstate2 = orclConn.prepareStatement("INSERT INTO gzwjview_rec(WR_NO,USERNAME,TRUENAME,OFFICEID,OFFICENNAME,IP,VIEWTIME) VALUES(?,?,?,?,?,?,?)");
				pstate2.setInt(1, argRes.getInt("WR_NO"));
				pstate2.setString(2, argRes.getString("USERNAME"));
				pstate2.setString(3, argRes.getString("TRUENAME"));
				pstate2.setInt(4, argRes.getInt("OFFICEID"));
				pstate2.setString(5, argRes.getString("OFFICENAME"));
				pstate2.setString(6, argRes.getString("IP"));
				pstate2.setLong(7, argRes.getTimestamp("VIEWTIME").getTime());
				pstate2.executeUpdate();

				System.out.println("成功提交第" + i + "条");

				pstate2.close();

			}

			
			
			pstate.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		} finally {
			
			if(argRes!=null){
				try {
					argRes.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
			
			if(pstate!=null){
				try {
					pstate.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
			
			if(pstate2!=null){
				try {
					pstate2.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
			
			if(sqlConn!=null){
				try {
					sqlConn.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
			
			if(orclConn!=null){
				try {
					orclConn.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
			
		}
		
		System.out.println("完成");
		
		
	}
	
	

}






