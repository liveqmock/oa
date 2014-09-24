/*
 * 创建日期 2008-1-11
 *
 * 更改所生成文件模板为
 * 窗口 > 首选项 > Java > 代码生成 > 代码和注释
 */
package com.icss.oa.zbs.duty.admin;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author 王苏
 *
 * 更改所生成类型注释的模板为
 * 窗口 > 首选项 > Java > 代码生成 > 代码和注释
 */
public class GoJspUrlServlet extends HttpServlet {

	/* （非 Javadoc）
	 * @see com.icss.j2ee.servlet.ServletBase#performTask(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	protected void doPost(
		HttpServletRequest request,
		HttpServletResponse response)
		throws ServletException, IOException {
		// TODO 自动生成方法存根

		String dist = "/zbs/zbs_duty/dutyAddFCK.jsp";
		//			this.forward(request, response, dist);
		//		String url="/EShop.jsp"; 
		ServletContext sc = getServletContext();
		RequestDispatcher rd = sc.getRequestDispatcher(dist);
		rd.forward(request, response);
		/*
		 * 跳转有两种方式，重定向与转发。 
		
		重定向可以使用HttpServletResponse对象 
		如 response.sendRedirect(\"xxx.jsp\"); //参数为url地址 
		上面方法如同js的location.href=\"xxx.jsp\"; 
		
		转发使用HttpServletRequest对象 
		request.getRequestDispatcher(\"xxx.jsp\").forward(request, response); 
		转发则把请求交给jsp页来处理
		 * */

	}
	public void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
	}

}
