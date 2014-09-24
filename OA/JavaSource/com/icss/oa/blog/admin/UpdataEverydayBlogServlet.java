	/*
	 * Created on 2004-4-7
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
	import com.icss.oa.blog.handler.EverydayBlogHandler;
	import com.icss.oa.blog.vo.*;


	/**
	 * @author Administrator
	 *
	 * To change the template for this generated type comment go to
	 * Window - Preferences - Java - Code Generation - Code and Comments
	 */
	public class UpdataEverydayBlogServlet extends ServletBase{
		
		protected void performTask(
				HttpServletRequest request,
				HttpServletResponse response)
				throws ServletException, IOException{
		   Connection conn = null;
		   
		   String id=request.getParameter("blogid");
		   
		   Integer blogid = new Integer(Integer.parseInt(id));
		   		   
		   String _page_num = request.getParameter("_page_num");
		   
		   String blogname = request.getParameter("blogname");
		   
		   String blogcontent= request.getParameter("blogcontent");	
		   
		   String time = request.getParameter("time");

		   EverydayBlogVO vo=new EverydayBlogVO();		  
		 
		   try {
			conn = getConnection(Globals.DATASOURCEJNDI);
			EverydayBlogHandler handler = new EverydayBlogHandler(conn);
			
			if(blogid==null){
				System.out.println("blogid is null");
			}
			
			if(vo==null){
				System.out.println("vo is null");
			}

			vo.setId(blogid);				
			vo.setBlogname(blogname);			
			vo.setBlogcontent(blogcontent);			
			vo.setBlogdata(time);
			
			handler.UpdateEverydayBlogVO(vo);
			
			this.forward(request, response, "/servlet/ListEverydayBlogServlet?page_num="+_page_num);	
			
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

			
		}
	}
