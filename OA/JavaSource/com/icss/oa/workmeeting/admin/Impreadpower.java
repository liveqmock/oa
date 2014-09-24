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
import java.text.SimpleDateFormat;

import com.icss.oa.workmeeting.handler.HandlerException;
import com.icss.oa.workmeeting.handler.Workinghandler;

//import com.icss.wangimp.vo.InfoMsgVO;
/**
 * @author Administrator
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public class Impreadpower {

	//	private static String datePath="D:\\wangimp\\date\\attachfile\\";//附件文件路径

	public static void main(String[] args) {

		Connection sqlConn = GetConn.getSqlServerConn();
		Connection orclConn = GetConn.getOrclConn();

		PreparedStatement pstate = null;
		PreparedStatement pstate2 = null;
		ResultSet argRes = null;
		
		try {
			//此处还需要添加sql语句
			pstate = sqlConn.prepareStatement("SELECT * FROM  ");
			argRes = pstate.executeQuery();
			
			Workinghandler handler = new Workinghandler(orclConn);
			String userid = "";
			String orguuid = "";
			int i = 0;
			
			while (argRes.next()) {
				i++;
				userid = "";
				orguuid = "";
				pstate2 =
					orclConn.prepareStatement(
						"INSERT INTO readpower(USERID,CNNAME,PERSONUUID) VALUES(?,?,?)");

				pstate2.setString(1, argRes.getString("USERID"));
				pstate2.setString(2, argRes.getString("CNNAME"));
				//			pstate2.setString(3,argRes.getString("PERSONUUID"));
				System.out.println("成功提交第" + i + "条");
				
				pstate2.executeUpdate();
				pstate2.close();
				
			}
			
			
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





