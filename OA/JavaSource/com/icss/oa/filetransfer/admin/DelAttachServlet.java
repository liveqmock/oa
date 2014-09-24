/*
 * Created on 2004-5-10
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.icss.oa.filetransfer.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.icss.j2ee.servlet.ServletBase;
import com.icss.oa.filetransfer.handler.SendFileBean;

/**
 * @author Administrator
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class DelAttachServlet extends ServletBase {

	/* (non-Javadoc)
	 * @see com.icss.j2ee.servlet.ServletBase#performTask(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	protected void performTask(
		HttpServletRequest request,
		HttpServletResponse response)
		throws ServletException, IOException {
		try {
			String donext = request.getParameter("delreturn");
			String index = request.getParameter("attach");
			if (index != null) {
				HttpSession session = request.getSession();
				//保存发送文件信息状态
				SendFileBean sendFileBean =
					SendFileBean.getInstanceFromSession(session);
				sendFileBean.removeAttach(Integer.parseInt(index));
				SendFileBean.saveToSession(session, sendFileBean);
			}
			response.sendRedirect(
					request.getContextPath() + "/filetransfer/attachFile.jsp?donext="+donext);
		} catch (Exception e) {
			handleError(e);
		}

	}

}
