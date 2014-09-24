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

			//下载路径修改 2005-12-28
			//原有程序：
			//ServletContext context = this.getServletContext();
			//String path = context.getRealPath("/filetransfer/downloadfile/");
			//要保存的附件的数据，写成输入流的形式；
			//String sep = File.separator;
			//修改程序：
			String path = FiletransferUtil.getDownloadFilePath();

			AccessInputStream = new FileInputStream(path + url);
			HttpSession session = request.getSession();

			//要保存的附近的文件名(包括扩展名)，表示成字符串的形式；
			String AccessName = filename;
			/*if(AccessInputStream==null){this.forward(request, response,"/folder/error.jsp?errormsg=没有添加附件"); }
			if(AccessName==null){this.forward(request, response,"/folder/error.jsp?errormsg=没有添加名称"); }*/
			session.setAttribute("AccessInputStream", AccessInputStream);
			session.setAttribute("path", path + url);
			session.setAttribute("AccessName", AccessName);

			this.forward(request, response, "/servlet/ShowSelectAccessServlet");

		} catch (Exception e) {
			handleError(e);
		}

	}
}
