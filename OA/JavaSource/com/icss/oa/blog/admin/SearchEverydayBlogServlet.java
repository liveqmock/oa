/*
 * Created on 2004-12-8
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */

package com.icss.oa.blog.admin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.sql.Connection;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.icss.j2ee.servlet.ServletBase;
import com.icss.j2ee.util.Globals;
//import com.icss.oa.bbs.handler.UserMsgHandler;
import com.icss.oa.blog.handler.*;
import com.icss.oa.blog.vo.*;
//import com.icss.oa.bbs.handler.UserMsgHandler;

import com.icss.oa.util.CommUtil;
import com.icss.oa.config.Config;
/**
 * @author Administrator
 * 
 * To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Generation - Code and Comments
 */
public class SearchEverydayBlogServlet extends ServletBase {
	protected void performTask(
		HttpServletRequest request,
		HttpServletResponse response)
		throws ServletException, IOException {
		Connection conn = null;

		List EverydayBlogList = null;

		String queryname = request.getParameter("blogname");
		queryname.trim();
		try {
			conn =getConnection(Globals.DATASOURCEJNDI);
			
			EverydayBlogHandler handler = new EverydayBlogHandler(conn);
			
			EverydayBlogList = handler.getSearchList(queryname);

			request.setAttribute("list", EverydayBlogList);
			
			this.forward(request, response, "/blog/EditEverydayBlog.jsp");
		} catch (Exception e) {
			handleError(e);
		} finally {
			try {
				if (conn != null)
					conn.close();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}
}
