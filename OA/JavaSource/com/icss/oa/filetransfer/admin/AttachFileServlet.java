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
public class AttachFileServlet extends ServletBase {

	protected void performTask(
		HttpServletRequest request,
		HttpServletResponse response)
		throws ServletException, IOException {
			
		HttpSession session = request.getSession();
		//���淢���ļ���Ϣ״̬
		SendFileBean sendFileBean =
			SendFileBean.getInstanceFromSession(session);
		if(!("2".equals(request.getParameter("donext")))&&!("5".equals(request.getParameter("donext")))){  //���ǻظ���ʱ��
			sendFileBean.setSendto(request.getParameter("sendto"));
			sendFileBean.setSendcc(request.getParameter("sendcc"));
			sendFileBean.setSendbcc(request.getParameter("sendbcc"));
		}
		//��seesion�õ����⣬��Ϊ�����и��ӵ�ʱ���
		String topic = "";
		if(request.getParameter("topic")!=null&&!("".equals(request.getParameter("topic")))){
			//�ɸı����ұ��ⲻΪ��ʱ
			topic = request.getParameter("topic")+Long.toString(System.currentTimeMillis());
		}else if(!("".equals(sendFileBean.getTopic()))){
			//ֻ����ֱ��ת��ʱ
			topic = sendFileBean.getTopic();
		}
		    //����Ϊ�ɸı��⵫�������Ϊ��ʱtopic = ""
		sendFileBean.setTopic(topic);
		if("1".equals(request.getParameter("donext"))||"4".equals(request.getParameter("donext"))){
			sendFileBean.setfolder("");
		}
		sendFileBean.setContent(request.getParameter("content"));
		
		sendFileBean.setisRe(request.getParameter("isRe"));
		sendFileBean.setisSent(request.getParameter("isSent"));

		SendFileBean.saveToSession(session, sendFileBean);
		this.forward(request, response, "/filetransfer/attachFile.jsp");
	}
}
