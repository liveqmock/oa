/*
 * Created on 2004-5-19
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package com.icss.oa.shortmessage.powerassign.admin;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.j2ee.servlet.ServletBase;
import com.icss.j2ee.util.Globals;
import com.icss.oa.shortmessage.maintenance.handler.ShortMessageOrgCodeHandler;



/**
 * @author Administrator
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
  public class LeftOrgListPowerServlet extends ServletBase{
	
	protected void performTask(HttpServletRequest request,HttpServletResponse response) 
    throws ServletException, IOException{
		Connection conn = null;
		System.out.println("#####################");
		try{
			conn = getConnection(Globals.DATASOURCEJNDI);
			ShortMessageOrgCodeHandler handler=new ShortMessageOrgCodeHandler(conn);
			List list=handler.getOrgShortMessageCodeList();
			request.setAttribute("list",list);
			
			this.forward(request, response, "/shortmessage/powerassign/orgSMRecord.jsp");
			
		}
		catch(Exception e){
			handleError(e);
		}
		finally {
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException e1) {
				
				e1.printStackTrace();
			}
		}
		
	
	}
}
