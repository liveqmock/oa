/*
 * �������� 2008-1-11
 *
 * �����������ļ�ģ��Ϊ
 * ���� > ��ѡ�� > Java > �������� > �����ע��
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
 * @author ����
 *
 * ��������������ע�͵�ģ��Ϊ
 * ���� > ��ѡ�� > Java > �������� > �����ע��
 */
public class GoJspUrlServlet extends HttpServlet {

	/* ���� Javadoc��
	 * @see com.icss.j2ee.servlet.ServletBase#performTask(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	protected void doPost(
		HttpServletRequest request,
		HttpServletResponse response)
		throws ServletException, IOException {
		// TODO �Զ����ɷ������

		String dist = "/zbs/zbs_duty/dutyAddFCK.jsp";
		//			this.forward(request, response, dist);
		//		String url="/EShop.jsp"; 
		ServletContext sc = getServletContext();
		RequestDispatcher rd = sc.getRequestDispatcher(dist);
		rd.forward(request, response);
		/*
		 * ��ת�����ַ�ʽ���ض�����ת���� 
		
		�ض������ʹ��HttpServletResponse���� 
		�� response.sendRedirect(\"xxx.jsp\"); //����Ϊurl��ַ 
		���淽����ͬjs��location.href=\"xxx.jsp\"; 
		
		ת��ʹ��HttpServletRequest���� 
		request.getRequestDispatcher(\"xxx.jsp\").forward(request, response); 
		ת��������󽻸�jspҳ������
		 * */

	}
	public void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
	}

}
