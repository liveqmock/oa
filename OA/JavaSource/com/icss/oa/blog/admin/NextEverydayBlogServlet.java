
/*
 * Created on 2004-12-13
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
public class NextEverydayBlogServlet extends ServletBase{
	protected void performTask(HttpServletRequest request,
			HttpServletResponse response)
			throws ServletException, IOException{
		Integer randomId = null;
		int Counts;
		Connection conn = null;
		EverydayBlogVO vo = new EverydayBlogVO();
		List list = new ArrayList();
		String strBlog = request.getParameter("BlogId");
		int intBlog = Integer.parseInt(strBlog);
		Integer nextBlogId = null;
		
		try{
		  conn = this.getConnection(Globals.DATASOURCEJNDI);
		  ConnLog.open("NextEverydayBlogServlet");
		  EverydayBlogHandler bHandler = new EverydayBlogHandler(conn);	
		  Counts = bHandler.getCounts();
		  if ((intBlog+1)>Counts||intBlog<0){
		  	//intBlog = -1;
		  	nextBlogId = new Integer(1);
		  }
		  else{
		  	nextBlogId = new Integer(intBlog+1);
		  }
		  
		  //randomId = bHandler.getRandomBlogID(Counts);
		  
		  vo = bHandler.getById(nextBlogId);
		  //Integer BlogId = vo.getId(list);		  
		  request.setAttribute("vo",vo);
		  System.out.println("get RandomEverydayBlogVO success!...........");
		  
		  this.forward(request, response, "/blog/ShowEverydayBlog.jsp?BlogId="+nextBlogId); //
		}
		catch(Exception e){
			System.out.println(e.toString());
			handleError(e);
		}finally {
			try {
				if (conn != null){
					conn.close();
					ConnLog.close("NextEverydayBlogServlet");
				}
			} catch (SQLException e1) {
				
				e1.printStackTrace();
			}
		}
	  	
		
	}
	
}
