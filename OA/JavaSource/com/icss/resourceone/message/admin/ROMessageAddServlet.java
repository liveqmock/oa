package com.icss.resourceone.message.admin;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.j2ee.dao.DAOException;
import com.icss.j2ee.services.ServiceLocatorException;
import com.icss.j2ee.servlet.ServletBase;
import com.icss.j2ee.util.Globals;
import com.icss.resourceone.message.handler.MessageHandler;
import com.icss.resourceone.message.vo.RoMessageVO;

/**
 * �����Ϣ
 * @version 	1.0
 * @author
 */
public class ROMessageAddServlet extends ServletBase {

	protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String _serialindex = request.getParameter("serialindex")==null?"0":request.getParameter("serialindex");
		Integer serialindex = new Integer(0);
		try{
			serialindex = Integer.valueOf(_serialindex);
		} catch(NumberFormatException e){
			System.out.println("/servlet/ROMessageAddServlet NumberFormatException " + e.getMessage());
			serialindex = new Integer(0);
		}
		
		//�Ƿ񷢲�
		String ispublish = request.getParameter("ispublish")==null?"0":request.getParameter("ispublish");
		//��Ϣ����
		String content = request.getParameter("content")==null?"":request.getParameter("content");
		
		System.out.println("/servlet/ROMessageAddServlet serialindex = " + serialindex);
		System.out.println("/servlet/ROMessageAddServlet ispublish = " + ispublish);
		System.out.println("/servlet/ROMessageAddServlet content = " + content);
		String errorMsg = null;
		Connection conn = null;
		try {
			conn = this.getConnection(Globals.DATASOURCEJNDI);
		
			RoMessageVO vo = new RoMessageVO();
			vo.setSerialindex(serialindex);
			vo.setIspublish(ispublish);
			vo.setMessagecontent(content);
			
			//�����Ϣ
			MessageHandler handler = new MessageHandler(conn);
			if (!handler.serialindexExist(serialindex)) {
				handler.addMessage(vo);
				errorMsg = "";
			} else {
				//�Ѿ�������Ϣ
				String text = "��Ϣ�б����Ѵ������Ϊ" + serialindex + "����Ϣ����������ӣ�";
				errorMsg = new String(text.getBytes("gb2312"), "ISO8859_1");

			}

			//��Ϣ�б�
			response.sendRedirect(request.getContextPath() + "/servlet/ROMessageListServlet?errorMsg=" + errorMsg);
			
		} catch (ServiceLocatorException e) {
			e.printStackTrace();
			handleError(e);
			
		} catch (DAOException e) {
			e.printStackTrace();
			handleError(e);
			
		} finally {
			try {
				if (conn != null) {

					conn.close();
				}
			} catch (SQLException e1) {

				e1.printStackTrace();
			}
		}

	}

}
