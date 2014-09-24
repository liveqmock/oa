/*
 * �������� 2007-3-9
 *
 * �����������ļ�ģ��Ϊ
 * ���� > ��ѡ�� > Java > �������� > �����ע��
 */
package com.icss.oa.votebbs.admin;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.icss.j2ee.services.DBConnectionLocator;
import com.icss.j2ee.services.DBConnectionLocatorException;
import com.icss.j2ee.util.Globals;
import com.icss.oa.votebbs.handler.BbsVoteHandler;

/**
 * @author ����
 *
 * ��������������ע�͵�ģ��Ϊ
 * ���� > ��ѡ�� > Java > �������� > �����ע��
 */
public class GetUserName extends HttpServlet {
	protected void processRequest(HttpServletRequest request,HttpServletResponse response,String method)throws ServletException,IOException{
		response.setContentType("text/xml;charset=GB2312");
//		request.setCharacterEncoding("GBK");
//		request.setCharacterEncoding("GB2312");   //������������ʽ  
//		response.setContentType("text/html;   charset=GB2312");  
		String username = request.getParameter("username");
		String mainId = request.getParameter("mainId");
		Connection conn = null;
		try {
			conn =	DBConnectionLocator.getInstance().getConnection(Globals.DATASOURCEJNDI);
			BbsVoteHandler handler = new BbsVoteHandler(conn);
			List list = handler.getUserListByName(username,mainId);
			String responseText = "";
//			System.err.println("OK!1"+list.size());
			if(list!=null&&list.size()>0){
//				System.err.println("OK!2");
				responseText = "<font color=red>�û�����["+username+"]�Ѵ���,�벻Ҫʹ��!</font>";
			}else{
//				System.err.println("OK!3");
				responseText = "<font color=blue>�û�����["+username+"]����ʹ��!</font>";
			}
//			responseText   =   new   String(responseText.getBytes("ISO8859_1"),"UTF-8");  
			
			PrintWriter out = response.getWriter();
			out.println(responseText);
			out.close();
		} catch (DBConnectionLocatorException e) {
			// TODO �Զ����� catch ��
			e.printStackTrace();
		}
	}
	
	protected void doGet(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{
		processRequest(request,response,"GET");
	}
	protected void doPost(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{
		processRequest(request,response,"POST");
	}
}
