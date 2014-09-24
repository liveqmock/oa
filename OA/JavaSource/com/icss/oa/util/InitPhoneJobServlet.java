/*
 * Created on 2004-12-23
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.icss.oa.util;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.common.log.ConnLog;
import com.icss.j2ee.services.ServiceLocatorException;
import com.icss.j2ee.servlet.ServletBase;
import com.icss.j2ee.util.Globals;
import com.icss.oa.phonebook.handler.JobBean;

/**
 * @author firecoral
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class InitPhoneJobServlet extends ServletBase{

	/* (non-Javadoc)
	 * @see com.icss.j2ee.servlet.ServletBase#performTask(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	protected void performTask(HttpServletRequest request, HttpServletResponse response) 
		throws ServletException, IOException {
	}
	
	public void init(){
		Connection conn = null;
		System.out.println("InitPhoneJobServlet.init()");
		try {
			conn = this.getConnection(Globals.DATASOURCEJNDI);
			ConnLog.open("InitPhoneJobServlet");
			JobBean.getInstance().initHashTable(conn);
		} catch (ServiceLocatorException e) {
			e.printStackTrace();
		} finally{
			try {
				if(conn!=null){
					conn.close();
					ConnLog.close("InitPhoneJobServlet");
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}

}
