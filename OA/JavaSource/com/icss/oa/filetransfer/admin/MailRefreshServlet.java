package com.icss.oa.filetransfer.admin;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.j2ee.servlet.ServletBase;

/**
 * Servlet implementation class for Servlet: MailRefreshServlet
 *
 */
 public class MailRefreshServlet extends ServletBase {
    /* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	 protected void performTask(HttpServletRequest request,HttpServletResponse response)
		throws ServletException, IOException {
		 
		 request.setAttribute("title1", "���ͽ��");
		 request.setAttribute("title2", "���ͽ��");
		 String result = (String)request.getSession().getAttribute("sendResult");
		 String overFlowResult = (String)request.getSession().getAttribute("overFlowResult");

		 String fail = (String)request.getSession().getAttribute("failResult");
		 String type = (String)request.getParameter("type");
		 if("1".equals(type)){
			 request.setAttribute("sendResult","�ɹ����浽�ݸ���!");
		 }else if("2".equals(type)){
			 request.setAttribute("failResult","���浽�ݸ���ʧ��!");
		 }else{
			 request.getSession().removeAttribute("sendResult");
			 request.getSession().removeAttribute("failResult");
			 request.getSession().removeAttribute("overFlowResult");

			 request.setAttribute("sendResult", result);
			 request.setAttribute("failResult", fail);
			 request.setAttribute("overFlowResult", overFlowResult);

		 }
		 forward(request,response,"/mail/SendResult1.jsp");
		 
	 }
	
	
}