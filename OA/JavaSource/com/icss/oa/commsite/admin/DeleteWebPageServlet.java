/*
 * 创建日期 2004-4-1
 *
 * 更改所生成文件模板为
 * 窗口 > 首选项 > Java > 代码生成 > 代码和注释
 */
package com.icss.oa.commsite.admin;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.common.log.ConnLog;
import com.icss.j2ee.servlet.ServletBase;
import com.icss.j2ee.util.Globals;
import com.icss.oa.commsite.handler.*;

/**
 * @author Administrator
 *
 * 更改所生成类型注释的模板为
 * 窗口 > 首选项 > Java > 代码生成 > 代码和注释
 */
public class DeleteWebPageServlet extends ServletBase {

	/* （非 Javadoc）
	 * @see com.icss.j2ee.servlet.ServletBase#performTask(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	protected void performTask(
		HttpServletRequest request,
		HttpServletResponse response)
		throws ServletException, IOException {

		Connection conn = null;
		try {
			conn = this.getConnection(Globals.DATASOURCEJNDI);
			ConnLog.open("DeleteWebPageServlet");
			CommsiteHandler handler = new CommsiteHandler(conn);
			Integer ocsId = new Integer(request.getParameter("ocsId"));
			handler.delete(ocsId);
			response.sendRedirect(
				"ListWebPageServlet?_page_num="
					+ request.getParameter("_page_num"));

		} catch (Exception e) {
			e.printStackTrace();
			handleError(e);

		} finally {
			try {
				if (conn != null) {
					conn.close();
					ConnLog.close("DeleteWebPageServlet");
				}
			} catch (SQLException sqle) {
				sqle.printStackTrace();
			}
		}
	}

}
