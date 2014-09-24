/*
 * Created on 2004-5-10
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.icss.oa.votebbs.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.icss.j2ee.servlet.ServletBase;

import com.icss.oa.votebbs.handler.OptionUploadBean;

/**
 * @author Administrator
 * 
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class AddOptionAttachFileServlet extends ServletBase {

	protected void performTask(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();

		// 保存发送文件信息状态
		OptionUploadBean optionUploads = OptionUploadBean.getInstanceFromSession(session);
		Integer mainid = request.getParameter("mainid") == null ?new Integer("-1") : new Integer(request
				.getParameter("mainid"));
		String title = request.getParameter("title") == null?"":request.getParameter("title");
		String content = request.getParameter("content") == null ? "": request.getParameter("content");
		String order = request.getParameter("order") == null ? "": request.getParameter("order");
		
//		System.out.println("+++++++++addlogmsgattachfileservlet++++sysid11111111="+sysid);
		optionUploads.setOpTitle(title);
		optionUploads.setOpContext(content);
		optionUploads.setOpOrder(order);
		optionUploads.setMainid(mainid.toString());
		
		optionUploads.saveToSession(session, optionUploads);
		
		System.out.println("+++++++++AddOptionAttachFileServlet++++optionUploads="+title);
		this.forward(request, response, "/votebbs/attachFile.jsp?mainid="+mainid);
	}
}
