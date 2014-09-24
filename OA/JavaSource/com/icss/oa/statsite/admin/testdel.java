package com.icss.oa.statsite.admin;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.common.log.ConnLog;
import com.icss.j2ee.servlet.ServletBase;
import com.icss.j2ee.util.Globals;
import com.icss.oa.statsite.handler.StatSiteHandler;

public class testdel extends ServletBase{
	protected void performTask(HttpServletRequest request,
			HttpServletResponse response)
			throws ServletException, IOException{
		Connection conn = null;
		try{	

		conn = this.getConnection(Globals.DATASOURCEJNDI);	
		ConnLog.open("testdel");
		long time = System.currentTimeMillis();
		StatSiteHandler logHandler = new StatSiteHandler(conn);
		logHandler.working();
		}
		catch(Exception e){
			System.out.println(e.toString());
			handleError(e);
		}finally {
			try {
				if (conn != null){
					conn.close();
					ConnLog.close("testdel");
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}
	
}
