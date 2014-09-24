/*
 * �������� 2007-3-9
 *
 * �����������ļ�ģ��Ϊ
 * ���� > ��ѡ�� > Java > �������� > �����ע��
 */
package com.icss.oa.votebbs.admin;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.j2ee.services.DBConnectionLocator;
import com.icss.j2ee.services.ServiceLocatorException;
import com.icss.j2ee.servlet.ServletBase;
import com.icss.j2ee.util.Globals;

/**
 * @author ����
 *
 * ��������������ע�͵�ģ��Ϊ
 * ���� > ��ѡ�� > Java > �������� > �����ע��
 */
public class BbsLoginServlet extends ServletBase {

	/* ���� Javadoc��
	 * @see com.icss.j2ee.servlet.ServletBase#performTask(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO �Զ����ɷ������

		Connection conn = null;
		try {
			conn = super.getConnection(com.icss.j2ee.util.Globals.DATASOURCEJNDI);
			String mainId = request.getParameter("mainId") == null ? "1" : request.getParameter("mainId");
			String status = request.getParameter("status") == null ? "0" : request.getParameter("status");
			String morder = request.getParameter("morder") == null ? "0" : request.getParameter("morder");
			System.out.println("=++++++BbsLoginServlet+++++++++++status="+status+"morder="+morder);
			if("1".equals(morder)||"3".equals(morder)){//����һ��һƱ������ͶƱ

				String dist =
					request.getContextPath()
						+ "/servlet/ArticleShowServlet?mainId="+ mainId+ "&morder="+morder;
				response.sendRedirect(dist);
				return;
			}else{//ע��ͶƱ

				
				if ("1".equals(status)) {
					request.setAttribute("Msginfo", "�����û��ɹ�");
				} else if ("2".equals(status)) {
					request.setAttribute("Msginfo", "��������!�����û�ʧ��!");
				} else if ("3".equals(status)) {
					request.setAttribute("Msginfo", "��������û��������ڣ����������룡");
				} else if ("4".equals(status)) {
					request.setAttribute("Msginfo", "��������������������룡");
				} else if ("5".equals(status)) {//
					Cookie cookie[] = request.getCookies();
					if (cookie.length > 0 && cookie != null) {
						for (int i = 0; i < cookie.length; i++) {
							Cookie cooki = cookie[i];
							cooki.setMaxAge(0);
							response.addCookie(cooki);
						}
					}
					request.setAttribute("mainId", mainId);
					request.setAttribute("morder",morder);
					String dist = "/bbsvote/loginIndex.jsp";
					forward(request, response, dist);
					return;
				}

				Cookie[] cook = request.getCookies();

				for (int i = 0; i < cook.length; i++) {
					Cookie cooks = cook[i];

					String name = cooks.getName();
					String value = cooks.getValue();
					System.out.println("cookie's name is " + name);
					System.out.println("cookie's value is " + value);
					if ("userId".equals(name)) {
						String dist =
							request.getContextPath()
								+ "/servlet/ArticleShowServlet?mainId="+ mainId+ "&userId="+ value+ "&action=4&morder="+morder;
						response.sendRedirect(dist);
						return;
					}

				}

				request.setAttribute("mainId", mainId);
				request.setAttribute("morder",morder);
				String dist = "/bbsvote/loginIndex.jsp";
				forward(request, response, dist);
			}
			

		} catch (ServiceLocatorException e) {
			// TODO �Զ����� catch ��
			e.printStackTrace();
		}

	}

}
