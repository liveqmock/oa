/*
 * Created on 2004-3-31
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package com.icss.oa.blog.admin;

import java.util.List;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.common.log.ConnLog;
import com.icss.j2ee.servlet.ServletBase;
import com.icss.j2ee.util.Globals;
import com.icss.oa.blog.handler.*;
import com.icss.oa.blog.vo.*;



/**
 * @author Administrator
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public class ShowEverydayBlogServlet extends ServletBase{
	protected void performTask(HttpServletRequest request,
			HttpServletResponse response)
			throws ServletException, IOException{
		Integer randomId = null;
		int Counts;
		Connection conn = null;
		EverydayBlogVO vo = new EverydayBlogVO();
		List list = new ArrayList();
		try{
		  conn = this.getConnection(Globals.DATASOURCEJNDI);
		  ConnLog.open("ShowEverydayBlogServlet");
		  EverydayBlogHandler bHandler = new EverydayBlogHandler(conn);	
		  Counts = bHandler.getCounts();
		  
		  randomId = bHandler.getRandomBlogID(Counts);
		  
		  vo = bHandler.getById(randomId);
		  //Integer BlogId = vo.getId(list);		  
		  request.setAttribute("vo",vo);
		  System.out.println("get RandomEverydayBlogVO success!...........");
		  
		  this.forward(request, response, "/blog/ShowEverydayBlog.jsp?BlogId="+randomId); //
		}
		catch(Exception e){
			System.out.println(e.toString());
			handleError(e);
		}finally {
			try {
				if (conn != null){
					conn.close();
					ConnLog.close("ShowEverydayBlogServlet");
				}
			} catch (SQLException e1) {
				
				e1.printStackTrace();
			}
		}
	  	
		
	}
	
}
