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
public class ImpGongzuowj {

	//	private static String datePath="D:\\wangimp\\date\\attachfile\\";//附件文件路径

	public static void main(String[] args) {

		Connection sqlConn = GetConn.getSqlServerConn();
		Connection orclConn = GetConn.getOrclConn();

		PreparedStatement pstate = null;
		PreparedStatement pstate2 = null;
		ResultSet argRes = null;
		try {
			pstate = sqlConn.prepareStatement("SELECT * FROM gongzuowj ");
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
						"INSERT INTO gongzuowj(WR_NO,TITLE,QS,TOTAL_QS,PUBLIC_DATE,ED_NAME,ISSUE_NAME,IN_TIME,ATTCH_ID,SCOPE,CLASSID,CFLAG,SEQNO) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)");

				pstate2.setInt(1, argRes.getInt("WR_NO"));
				pstate2.setString(2, argRes.getString("TITLE"));
				pstate2.setInt(3, argRes.getInt("QS"));
				pstate2.setInt(4, argRes.getInt("TOTAL_QS"));
				pstate2.setString(
					5,
					new SimpleDateFormat("yyyy-MM-dd").format(
						argRes.getDate("PUBLIC_DATE")));
				pstate2.setString(6, argRes.getString("ED_NAME"));
				userid = argRes.getString("ISSUE_NAME");
				pstate2.setString(7, userid);
				pstate2.setLong(8, argRes.getTimestamp("IN_TIME").getTime());
				pstate2.setInt(9, argRes.getInt("ATTCH_ID"));
				pstate2.setString(10, argRes.getString("SCOPE"));
				pstate2.setInt(11, argRes.getInt("CLASSID"));
				pstate2.setInt(12, argRes.getInt("CFLAG"));
				pstate2.setInt(13, argRes.getInt("SEQNO"));
				System.out.println("成功提交第" + i + "条");
				pstate2.executeUpdate();
				
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







