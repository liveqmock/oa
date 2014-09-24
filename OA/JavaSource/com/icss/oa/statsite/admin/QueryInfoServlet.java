/*
 * Created on 2004-4-5
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package com.icss.oa.statsite.admin;



import java.io.IOException;   
import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.common.log.ConnLog;
import com.icss.j2ee.servlet.ServletBase;
import com.icss.j2ee.util.Globals;
import com.icss.oa.statsite.handler.StatSiteHandler;


/**
 * @author Administrator
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public class QueryInfoServlet extends ServletBase{
	
	protected void performTask(
			HttpServletRequest request,
			HttpServletResponse response)
			throws ServletException, IOException{


		Connection conn = null;
		try{
			conn = this.getConnection(Globals.DATASOURCEJNDI);
			ConnLog.open("QueryInfoServlet");
			StatSiteHandler handler = new StatSiteHandler(conn);	

			String person = request.getParameter("person");
			String ip = request.getParameter("ip");
			String mold = request.getParameter("mold");
			
			boolean havesql=false;
			StringBuffer sql=new StringBuffer();
			
			
			if(person!=null&&!person.equals("")){
			  	 sql.append(" USERID = '"+person+"'");
			  	 havesql=true;
			}
			
			if(ip!=null&&!ip.equals("")){
				 if(havesql) sql.append(" AND ");
			  	 sql.append(" IP ='"+ip+"'");
			    	havesql=true;
			}
			
			if(mold!=null&&!mold.equals("")){
				 if(havesql) sql.append(" AND ");
			  	 sql.append(" MODULEID ='"+mold+"'");
			    	havesql=true;
			}
			
			List list = handler.getInfoList(sql.toString());
	//		List list = handler.get();
		//	System.out.println("llllllllllllllllllll  "+sql.toString());


			try {
				if (conn != null) conn.close();
			} catch (SQLException sqle) {}
			
				
			request.setAttribute("list",list);
			/*System.out.println("ppp  "+list.size());
			request.setAttribute("kk",new Integer(1));*/
			  
			//   System.out.println("get list success!...........");
			this.forward(request, response, "/statsite/showSiteList.jsp");
        	//this.forward(request,response, "/servlet/ShowCountsite");  
			
		}
		catch(Exception e){  
			System.err.println(e.getMessage());
		}
		finally {
			try {
				if (conn != null){
					conn.close();
					ConnLog.close("QueryInfoServlet");
				}
			} catch (SQLException e1) {      
				
				e1.printStackTrace();
			}
		}
        
	}	
}
