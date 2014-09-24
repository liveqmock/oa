/*
 * Created on 2004-5-27
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package com.icss.oa.shortmessage.send.admin;

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
public class SendMainServlet extends ServletBase{
	protected void performTask(HttpServletRequest request,HttpServletResponse response) 
    throws ServletException, IOException{
		Connection conn = null;
		
		try{
			conn = getConnection(Globals.DATASOURCEJNDI);
			DXPartRecordHandler handler=new DXPartRecordHandler(conn);
			String personId=handler.getUserId();
			List list=handler.getPoweredOrgListByperId(personId);
			request.setAttribute("list",list);
			this.forward(request, response, "/shortmessage/send/send.jsp");
						
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
