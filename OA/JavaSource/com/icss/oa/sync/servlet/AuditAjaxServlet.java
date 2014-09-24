package com.icss.oa.sync.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

import com.icss.common.log.ConnLog;
import com.icss.j2ee.servlet.ServletBase;
import com.icss.oa.sync.handler.UserSyncHandler;

public class AuditAjaxServlet extends ServletBase {

	protected void performTask(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		Connection conn = null;
		String JNDI = "jdbc/ROEEE";

		PrintWriter out = response.getWriter();

		try {
			conn = this.getConnection(JNDI);
			ConnLog.open("AuditAjaxServlet");

			UserSyncHandler handler = new UserSyncHandler(conn);
			String userid = null;
			if (request.getParameter("userid") != null)
				userid = request.getParameter("userid");

			if (userid == null || userid.equals("")) {
				out.println("����Ϊ��");
				return;

			}
			out.println(userid);
			if (!StringUtils.isAsciiPrintable(userid)) {
				//�������������
				out.println("�в��Ϸ����ַ�");
				return; 
			}
			if (handler.UserIsExist(userid))
				out.println("�û�����ͻ��������");
			else
				out.println("���û�������");
		} catch (Exception e) {
			out.println(e);
			e.printStackTrace();
		} finally {
			if (conn != null)
				try {
					conn.close();
					ConnLog.close("AuditAjaxServlet");
				} catch (Exception e2) {
					e2.printStackTrace();
				}
		}

	}

}
