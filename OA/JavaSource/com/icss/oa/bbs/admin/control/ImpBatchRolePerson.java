package com.icss.oa.bbs.admin.control;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.common.log.ConnLog;
import com.icss.j2ee.servlet.ServletBase;
import com.icss.resourceone.sdk.framework.Person;
import com.icss.resourceone.sdk.right.AppRole;
import com.icss.resourceone.sdk.right.RightException;
import com.icss.resourceone.sdk.right.RightManager;
import com.icss.resourceone.sdk.right.Role;

/**
 * 得到组织的uuid,将本组织下所有人的信息都放入session中
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public class ImpBatchRolePerson extends ServletBase {

	protected void performTask(
		HttpServletRequest arg0,
		HttpServletResponse arg1)
		throws ServletException, IOException {

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		Role role = new Role();
		role.setRoleid(new Integer(184)); //184为论坛的角色号码
		try {
			role = role.getInstanceByKey();
		} catch (RightException e1) {
			e1.printStackTrace();
		}

		List personlist = new ArrayList();
		String personuuid = "";
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();
			String url = "jdbc:oracle:thin:@10.102.1.200:1521:orcl";
			//								  orcl为你的数据库的SID 
			String user = "roeee";
			String password = "roeee";
			conn = DriverManager.getConnection(url, user, password);
			ConnLog.open("ImpBatchRolePerson");
			String sql =
				" select personuuid from ro_person where cnname not like'%分社%' and cnname not like'%子库%' and cnname not like'%用户%' ";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				personuuid = rs.getString(1);
				Person p = new Person();
				p.setUuid(personuuid);
				personlist.add(p);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				if(pstmt!=null){
					pstmt.close();
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				if (conn != null) {
					conn.close();
					ConnLog.close("ImpBatchRolePerson");
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		try {
			role.addPersons(personlist);
		} catch (RightException e2) {
			e2.printStackTrace();
		}
		
		System.out.println("分配bbs权限成功");
		
	}
	
	
}







