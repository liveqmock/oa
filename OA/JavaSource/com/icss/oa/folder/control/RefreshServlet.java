/*
 * Created on 2004-3-16
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.icss.oa.folder.control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.j2ee.servlet.ServletBase;

/**
 * @author Administrator
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class RefreshServlet extends ServletBase {

	protected void performTask(
		HttpServletRequest request,
		HttpServletResponse response)
		throws ServletException, IOException {
		String errortype = request.getParameter("errortype");
		String errormsg = "����,";
		if("1".equals(errortype)){
			errormsg = "���ϴ����ļ�����,����������!";
		}else if("2".equals(errortype)){
			errormsg = "��������û���㹻�ռ�,����ɾ��һЩ�ļ�!";
		}
		String url = "/mail/FolderResult.jsp";
		request.setAttribute("title1","����");
		request.setAttribute("title2","����");
		request.setAttribute("failResult",errormsg);
		this.forward(request, response, url);
	}
}
