/*
 * Created on 2004-5-20
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package com.icss.oa.shortmessage.partrecord.admin;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.j2ee.servlet.ServletBase;
import com.icss.j2ee.util.Globals;
import com.icss.oa.shortmessage.partrecord.handler.DXPartRecordHandler;

/**
 * @author Administrator
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public class LeftPoweredOrgListServlet extends ServletBase{
	protected void performTask(HttpServletRequest request,HttpServletResponse response) 
    throws ServletException, IOException{
		Connection conn = null;
		
		try{
			
			conn = getConnection(Globals.DATASOURCEJNDI);
			DXPartRecordHandler handler=new DXPartRecordHandler(conn);
			System.out.println("11111111111111111111111111111"+conn);
			String personId=handler.getUserId();
			System.out.println("22222222222222222222222222222"+personId);
			//List list=handler.getPoweredOrgListByperId(personId);
			List list=handler.getPoweredOrgListByperId(personId);
			System.out.println("33333333333333333333333333333"+list);
			request.setAttribute("list",list);
			this.forward(request, response, "/shortmessage/partrecord/orgSMRecord_powered.jsp");
			System.out.println("444444444444444444444444444444");
			
		}
		catch(Exception e){
			System.out.println("555555555555555555555555555555");
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
