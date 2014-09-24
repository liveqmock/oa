/*
 * Created on 2003-12-17
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.icss.oa.filetransfer.admin;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.icss.j2ee.servlet.ServletBase;
import com.icss.oa.filetransfer.util.FiletransferUtil;

public class SaveAccessServlet extends ServletBase {

	protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		InputStream AccessInputStream = null;
		try {

			String filename = request.getParameter("filename");
			String url = request.getParameter("url");

			//����·���޸� 2005-12-28
			//ԭ�г���
			//ServletContext context = this.getServletContext();
			//String path = context.getRealPath("/filetransfer/downloadfile/");
			//Ҫ����ĸ��������ݣ�д������������ʽ��
			//String sep = File.separator;
			//�޸ĳ���
			String path = FiletransferUtil.getDownloadFilePath();

			AccessInputStream = new FileInputStream(path + url);
			HttpSession session = request.getSession();

			//Ҫ����ĸ������ļ���(������չ��)����ʾ���ַ�������ʽ��
			String AccessName = filename;
			/*if(AccessInputStream==null){this.forward(request, response,"/folder/error.jsp?errormsg=û����Ӹ���"); }
			if(AccessName==null){this.forward(request, response,"/folder/error.jsp?errormsg=û���������"); }*/
			session.setAttribute("AccessInputStream", AccessInputStream);
			session.setAttribute("path", path + url);
			session.setAttribute("AccessName", AccessName);

			this.forward(request, response, "/servlet/ShowSelectAccessServlet");

		} catch (Exception e) {
			handleError(e);
		}

	}
}
