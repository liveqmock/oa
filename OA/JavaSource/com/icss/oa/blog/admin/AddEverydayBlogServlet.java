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
import com.icss.oa.util.CommUtil;
/**
 * @author Administrator
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class AddEverydayBlogServlet extends ServletBase {

	protected void performTask(
		HttpServletRequest request,
		HttpServletResponse response)
		throws ServletException, IOException {
		Connection conn = null;
		int Counts;
		String _page_num = request.getParameter("_page_num");
		String blogname = request.getParameter("BlogName");
		String blogcontent = request.getParameter("BlogContent");
		String blogtime = request.getParameter("time");

		int k = 0;
		EverydayBlogVO VO = new EverydayBlogVO();
		//System.out.println("hhhhhhhhhhhhhh");
		//String check[]=new String[60];
		//check=request.getParameterValues("delete");
		//System.out.println(check);
		try {
			conn = getConnection(Globals.DATASOURCEJNDI);
			String content = CommUtil.formathtm(blogcontent);

			EverydayBlogHandler Handler = new EverydayBlogHandler(conn);
			Counts = Handler.getCounts();
			Integer blogid = new Integer(Counts + 1);

			VO.setId(blogid);
			VO.setBlogname(blogname);
			VO.setBlogcontent(blogcontent);
			VO.setBlogdata(blogtime);

			//			String personUUID=new String();
			//			personUUID=Handler.getUserId();
			Handler.addEverydayBlog(VO);

			System.out.println("Add successfully!!!!");
			this.forward(
				request,
				response,
				"/servlet/ListEverydayBlogServlet?_page_num=" + _page_num);
			//response.sendRedirect("ShowJournalServlet");
		} catch (Exception e) {
			e.printStackTrace();
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
		System.out.println("Add successfully????");

	}
}
