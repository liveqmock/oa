/*
 * Created on 2003-12-26
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.icss.oa.useraddress.handler;

import java.io.File;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;


import com.icss.oa.addressbook.dao.AddressbookFolderDAO;
import com.icss.oa.addressbook.dao.AddressbookManagerDAO;
import com.icss.oa.addressbook.vo.AddressbookFolderVO;
import com.icss.oa.addressbook.vo.AddressbookTreeVO;
import com.icss.oa.log.dao.LogAccessoryDAO;
import com.icss.oa.log.dao.LogMsgDAO;
import com.icss.oa.log.dao.LogReplyDAO;
import com.icss.oa.log.dao.LogSysDAO;
import com.icss.oa.log.vo.LogAccessoryVO;
import com.icss.oa.log.vo.LogMsgVO;
import com.icss.oa.log.vo.LogReplyVO;
import com.icss.oa.log.vo.LogSysTreeVO;
import com.icss.oa.log.vo.LogSysVO;

import com.icss.j2ee.dao.DAOException;
import com.icss.j2ee.dao.DAOFactory;
import com.icss.j2ee.services.DBConnectionLocator;
import com.icss.j2ee.services.DBConnectionLocatorException;

/**
 * @author Administrator
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class CommonHandler {
	
	public CommonHandler() {
		
	}
	
	/**
	 * 得到一个人的中文名称，根据此人的uuid
	 * 
	 * @param personuuid
	 * @return
	 * @throws HandlerException
	 */
	public String getPerson_name(String personuuid)  {
//		System.out.println("eeee11111111111111111111111     "+personuuid);
		Connection conn = null;
		try {
			conn = DBConnectionLocator.getInstance().getConnection(
					com.icss.j2ee.util.Globals.DATASOURCEJNDI);
		} catch (DBConnectionLocatorException e1) {
			e1.printStackTrace();
		}
		
		String sql = " SELECT CNNAME AS cnname FROM  SYS_PERSON WHERE PERSONUUID = '"
				+ personuuid+"'";
//		System.out.println("eeee22222222222222222222222222     "+sql); 

		Statement stmt = null;
		ResultSet rs = null;
		String cnname = "";
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			if (rs != null) {
				if (rs.next()) {
					cnname = rs.getString("cnname");
//					System.out.println("eeee3333333     "+cnname); 
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
					
				}
			}
			if(stmt!=null){
				try {
					stmt.close();
				} catch (SQLException e2) {
					e2.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
					
				}
			}
		}
		
		return cnname;

	}
	



}
