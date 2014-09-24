	/*
	 * Created on 2004-12-9
	 *
	 * To change the template for this generated file go to
	 * Window - Preferences - Java - Code Generation - Code and Comments
	 */

	/**
	 * @author Administrator
	 *
	 * To change the template for this generated type comment go to
	 * Window - Preferences - Java - Code Generation - Code and Comments
	 */
	package com.icss.oa.blog.admin;

	import java.io.IOException;
	import java.sql.Connection;
	import java.sql.SQLException;

	import javax.servlet.ServletException;
	import javax.servlet.http.HttpServletRequest;
	import javax.servlet.http.HttpServletResponse;

	import com.icss.common.log.ConnLog;
	import com.icss.j2ee.servlet.ServletBase;
	import com.icss.j2ee.util.Globals;
	import com.icss.oa.blog.handler.*;
	import com.icss.oa.blog.vo.EverydayBlogVO;

	public class DelEverydayBlogServlet extends ServletBase {

		protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			Connection conn = null;
			int counts;
			String id[] = request.getParameterValues("jc");

			try {
				conn = this.getConnection(Globals.DATASOURCEJNDI);
				ConnLog.open("DelEverydayBlogServlet");
				EverydayBlogVO vo = new EverydayBlogVO();

				for (int j = 0; j < id.length; j++) {
					Integer blogid = Integer.valueOf(id[j]);
					EverydayBlogHandler handler = new EverydayBlogHandler(conn);
					counts = handler.getCounts();
					Integer maxBlogId = new Integer(counts);
					if (!(blogid.equals(maxBlogId))) {
						handler.DelEverydayBlog(blogid);
						vo = handler.getById(maxBlogId);
						String blogname = vo.getBlogname();
						String blogcontent = vo.getBlogcontent();
						String time = vo.getBlogdata();

						handler.DelEverydayBlog(maxBlogId);

						vo.setId(blogid);
						vo.setBlogname(blogname);
						vo.setBlogcontent(blogcontent);
						vo.setBlogdata(time);
						handler.addEverydayBlog(vo);
					} else {
						handler.DelEverydayBlog(blogid);
					}
				}
				this.forward(request, response, "/servlet/ListEverydayBlogServlet");

			} catch (Exception e) {
				System.err.println(e.getMessage());
				e.printStackTrace();

			} finally {
				try {
					if (conn != null){
						conn.close();
						ConnLog.close("DelEverydayBlogServlet");
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
			//response.sendRedirect(request.getContextPath()+"/servlet/ShowServiceTypeServlet");
			// response.sendRedirect("ShowJournalServlet");

		}

	}
