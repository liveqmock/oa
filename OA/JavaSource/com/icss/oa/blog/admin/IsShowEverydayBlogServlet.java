
/*
 * Created on 2004-12-14
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package com.icss.oa.blog.admin;

import java.io.IOException;

import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
public class IsShowEverydayBlogServlet extends ServletBase{
	
	protected void performTask(HttpServletRequest request,HttpServletResponse response) 
    throws ServletException, IOException{
		Connection conn = null;
		String isshow = request.getParameter("setshow");
		if ((isshow == null)||(isshow == "")){			
			isshow = "0";
		}
		String blogid = request.getParameter("BlogId");
		Integer showblogid = new Integer(Integer.parseInt(blogid));
		//EverydayBlogVO vo = new EverydayBlogVO();

		try {
			conn = getConnection(Globals.DATASOURCEJNDI);
			EverydayBlogHandler handler = new EverydayBlogHandler(conn);
			String userid = handler.getUserId();
			//String userid = "005" ;//≤‚ ‘”√¿˝
			String username = handler.getUserCnName();
			//String username = "icssicss";//≤‚ ‘”√¿˝
			if (isshow.equals("0")){
				handler.setIsShow(false,userid,username);
			}
			else{
				handler.setIsShow(true,userid,username);
			}
			//vo = handler.getById(showblogid);
			//request.setAttribute("vo",vo);
			
			//this.forward(request,response,"/blog/ShowEverydayBlog.jsp?BlogId="+blogid);

		} catch (Exception e) {
			System.out.println(e.toString());
			handleError(e);
		} finally {
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		System.out.println("Set successfully????");
		
	}
}
