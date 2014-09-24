/*
 * Created on 2004-5-8
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package com.icss.oa.shortmessage.maintenance.admin;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.common.log.ConnLog;
import com.icss.j2ee.servlet.ServletBase;
import com.icss.j2ee.util.Globals;
import com.icss.oa.shortmessage.maintenance.handler.ShortMessageOrgCodeHandler;

/**
 * @author Administrator
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
  public class ShowShortMessageOrgCodeServlet extends ServletBase{
	  protected void performTask(HttpServletRequest request,HttpServletResponse response)
	                  throws ServletException, IOException{
	  	Connection conn = null;
		List list1 = new ArrayList();
		
		try{
			conn = this.getConnection(Globals.DATASOURCEJNDI);
			ConnLog.open("ShowShortMessageOrgCodeServlet");
			ShortMessageOrgCodeHandler handler=new ShortMessageOrgCodeHandler(conn);
			list1=handler.getOrgShortMessageCodeList();
			request.setAttribute("list",list1);
			String flag="0";
			flag=request.getParameter("flag");
			if(flag==null){
				flag="0";
			}
			request.setAttribute("flag",flag);
			//  System.out.println("get list success!...........");
			this.forward(request, response, "/shortmessage/maintenance/duanxinhao.jsp");
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally {
			try {
				if (conn != null){
					conn.close();
					ConnLog.close("ShowShortMessageOrgCodeServlet");
				}
			} catch (SQLException e1) {
				
				e1.printStackTrace();
			}
		}
		
		
	}
}
