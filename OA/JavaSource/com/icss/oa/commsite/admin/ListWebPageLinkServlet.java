/*
 * �������� 2004-3-31
 *
 * �����������ļ�ģ��Ϊ
 * ���� > ��ѡ�� > Java > �������� > �����ע��
 */
package com.icss.oa.commsite.admin;
 
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.common.log.ConnLog;
import com.icss.j2ee.servlet.ServletBase;
import com.icss.j2ee.util.Globals;
import com.icss.oa.commsite.handler.CommsiteHandler;
/**
 * @author Administrator
 *
 * ��������������ע�͵�ģ��Ϊ
 * ���� > ��ѡ�� > Java > �������� > �����ע��
 */
public class ListWebPageLinkServlet extends ServletBase {

	/* ���� Javadoc��
	 * @see com.icss.j2ee.servlet.ServletBase#performTask(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	protected void performTask(
		HttpServletRequest request,
		HttpServletResponse response)
		throws ServletException, IOException {
			
		Connection conn=null;
		String _page_num=request.getParameter("_page_num");
		try {
			conn = this.getConnection(Globals.DATASOURCEJNDI);
			ConnLog.open("ListWebPageLinkServlet");
			CommsiteHandler handler = new CommsiteHandler(conn);
			List list = handler.getWebList();
			request.setAttribute("list", list);
			String task=request.getParameter("task");
			this.forward(request, response, "/commsite/website.jsp?_page_num="+_page_num);
			
		} catch (Exception e) {
			handleError(e);
		} finally {
			try {
				if (conn != null) {
					conn.close();
					ConnLog.close("ListWebPageLinkServlet");
				}
			} catch (SQLException sqle) {
			}
		}

	}

}
