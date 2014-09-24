/*
 * Created on 2004-3-31
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package com.icss.oa.blog.admin;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.common.log.ConnLog;
import com.icss.j2ee.servlet.ServletBase;
import com.icss.j2ee.util.Globals;
import com.icss.oa.blog.handler.*;

/**
 * @author Administrator
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public class ListEverydayBlogServlet extends ServletBase{
	protected void performTask(HttpServletRequest request,
			HttpServletResponse response)
			throws ServletException, IOException{
		Connection conn = null;
		List list1 = new ArrayList();
		Integer months=null;
		String _page_num = request.getParameter("_page_num");
		if (_page_num==null || _page_num==""){
			_page_num="1";
		}
		SimpleDateFormat formatTime = new SimpleDateFormat("yyyy-MM-dd");
		formatTime.toString();
		try{
		  conn = this.getConnection(Globals.DATASOURCEJNDI);
		  ConnLog.open("ListEverydayBlogServlet");
		  EverydayBlogHandler handler = new EverydayBlogHandler(conn);		
		  list1 = handler.getEverydayBlogList();
		  request.setAttribute("list",list1);
		  System.out.println("get list success!...........");
	  
		  this.forward(request, response, "/blog/EditEverydayBlog.jsp?_page_num="+_page_num); //
		}
		catch(Exception e){
			System.out.println(e.toString());
			handleError(e);
		}finally {
			try {
				if (conn != null){
					conn.close();
					ConnLog.close("ListEverydayBlogServlet");
				}
			} catch (SQLException e1) {
				
				e1.printStackTrace();
			}
		}
	  	
		
	}
}
	
