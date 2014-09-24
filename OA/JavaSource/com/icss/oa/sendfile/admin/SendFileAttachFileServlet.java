/*
 * Created on 2004-5-10
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.icss.oa.sendfile.admin;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

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
public class SendFileAttachFileServlet extends ServletBase {

	protected void performTask(
		HttpServletRequest request,
		HttpServletResponse response)
		throws ServletException, IOException {
			
		
		HttpSession session = request.getSession();
		//保存发送文件信息状态
		SendFileBean sendFileBean =
		SendFileBean.getInstanceFromSession(session);

//		sendFileBean.setSendto(request.getParameter("sendHtml"));
//		sendFileBean.setSendcc(request.getParameter("ccHtml"));
//		sendFileBean.setSendbcc(request.getParameter("bccHtml"));

		session.setAttribute("SendFileSendHtml",request.getParameter("sendHtml"));
		session.setAttribute("SendFileCcHtml",request.getParameter("ccHtml"));
		session.setAttribute("SendFileBccHtml",request.getParameter("bccHtml"));
		
		//用seesion得到主题，因为主题有附加的时间段
		String topic = "";
		if(request.getParameter("topic")!=null&&!("".equals(request.getParameter("topic")))){
			//可改标题且标题不为空时
			topic = request.getParameter("topic")+Long.toString(System.currentTimeMillis());
		}else if(!("".equals(sendFileBean.getTopic()))){
			//只用于直接转发时
			topic = sendFileBean.getTopic();
		}

		    //余下为可改标题但输入框中为空时topic = ""
		sendFileBean.setTopic(topic);
		if("1".equals(request.getParameter("donext"))||"4".equals(request.getParameter("donext"))){
			sendFileBean.setfolder("");
		}
		sendFileBean.setContent(request.getParameter("content"));
		 
		sendFileBean.setisRe(request.getParameter("isRe"));
		sendFileBean.setisSent(request.getParameter("isSent"));
		SendFileBean.saveToSession(session, sendFileBean);

		this.forward(request, response, "/mail/AttachFile.jsp");
	}
}
