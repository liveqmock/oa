/*
 * Created on 2003-12-26
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
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
import com.icss.oa.blog.handler.EverydayBlogHandler;
import com.icss.oa.blog.vo.*;
/**
 * @author Administrator
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class EditEverydayBlogServlet extends ServletBase {

	protected void performTask(HttpServletRequest request,HttpServletResponse response) 
    				throws ServletException, IOException{
		Connection conn = null;

		String idtemp1 = request.getParameter("blogid");
		Integer idtemp = new Integer(Integer.parseInt(idtemp1));
		
		Integer blogid = new Integer(Integer.parseInt(request.getParameter("blogid")));
		
		System.out.println("blogid= "+blogid);
		
		String _page_num = request.getParameter("_page_num");
		
		EverydayBlogVO vo = new EverydayBlogVO();
		System.out.println("hhhhhhhhhhhhhh");
		//String check[]=new String[60];
		//check=request.getParameterValues("delete");
		//System.out.println(check);
		try {
			conn = getConnection(Globals.DATASOURCEJNDI);
			EverydayBlogHandler handler = new EverydayBlogHandler(conn);

			vo = handler.getById(blogid);
						
			request.setAttribute("vo",vo);
			
			//System.out.println("Add successfully!!!!");
			
			this.forward(request,response,"/blog/UpdataEverydayBlog.jsp?blogid="+blogid);
			
			//response.sendRedirect("ShowJournalServlet");
		} catch (Exception e) {
			//System.out.println(e.toString());
			handleError(e);
		} finally {
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
			//System.out.println("Add successfully????");
		
		}
	}
