/*
 * 创建日期 2007-3-13
 *
 * 更改所生成文件模板为
 * 窗口 > 首选项 > Java > 代码生成 > 代码和注释
 */
package com.icss.oa.votebbs.admin;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.j2ee.services.ServiceLocatorException;
import com.icss.j2ee.servlet.ServletBase;
import com.icss.oa.votebbs.handler.BbsVoteHandler;

/**
 * @author 王苏
 *
 * 更改所生成类型注释的模板为
 * 窗口 > 首选项 > Java > 代码生成 > 代码和注释
 */
public class MainArticleListServlet extends ServletBase {

	/* （非 Javadoc）
	 * @see com.icss.j2ee.servlet.ServletBase#performTask(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO 自动生成方法存根
		Connection conn = null;
		try {
			conn = super.getConnection(com.icss.j2ee.util.Globals.DATASOURCEJNDI);
			BbsVoteHandler handler = new BbsVoteHandler(conn);
//			System.err.println("你好");
			List mainlist = handler.getMainListByClause(" STATUS='开放'");
//			System.err.println("nihao:"+mainlist.size());
			request.setAttribute("mainlist",mainlist);
//			request.setAttribute("mainId", mainId);
			String dist = "/bbsvote/mainList.jsp";
			forward(request, response, dist);
			
		} catch (ServiceLocatorException e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}
		
	}

}
