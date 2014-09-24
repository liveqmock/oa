package com.icss.oa.mail.admin;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
//import com.icss.oa.mail.handler.MailBean;

import com.icss.common.log.ConnLog;
import com.icss.j2ee.services.ServiceLocatorException;
import com.icss.j2ee.servlet.ServletBase;
import com.icss.j2ee.util.Globals;
import com.icss.oa.filetransfer.handler.FiletransferSetHandler;


import com.icss.resourceone.common.logininfo.UserInfo;
import com.icss.resourceone.sdk.framework.Context;
import com.icss.resourceone.sdk.framework.EntityException;

/**
 * 在发送邮件之前，初始化邮件内容
 * Servlet implementation class for Servlet: PerSendMailServlet
 * @author lxy
 * @version 1.0
 */
 public class PerSendMailServlet extends ServletBase {
	    /*
		 * （非 Javadoc）
		 * @see com.icss.j2ee.servlet.ServletBase#performTask(javax.servlet.http.HttpServletRequest, 
		 * javax.servlet.http.HttpServletResponse)
		 * 处理并跳转到SendMail.jsp
		 */
		protected void performTask(
				HttpServletRequest request,
				HttpServletResponse response){
			
		//**************************无接收参数***********************
			
			Connection conn = null;
			Context ctx = null;

			try {
				//清理session 每次进入发送文件功能时，重新添加session
				HttpSession session = request.getSession();
				//MailBean.removeSession(session);
				//MailBean mailbean = MailBean.getInstanceFromSession(session);
				//MailBean.saveToSession(session, mailbean);
				conn = this.getConnection(Globals.DATASOURCEJNDI);
				ConnLog.open("PerSendMailServlet");
				//取得发件人信息
				ctx = Context.getInstance();
				UserInfo user = ctx.getCurrentLoginInfo();
				//依邮箱设置表，得到准确的邮箱用户名，用来构造邮箱地址
				FiletransferSetHandler ftsHandler = new FiletransferSetHandler(conn);
				String userid = "";
				userid = ftsHandler.getUserid(user.getPersonUuid());
				if (userid == null) {
					this.forward(request, response, "/filetransfer/noMailBox.jsp");
				}
				request.setAttribute("newSendFile", "OK");
				this.forward(request, response, "/address/mail/sendmail.jsp");
			} catch (ServiceLocatorException e) {
				e.printStackTrace();
			} catch (EntityException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ServletException e) {
				e.printStackTrace();
			} finally {
				if (conn != null) {
					try {
						conn.close();
						ConnLog.close("PerSendMailServlet");
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}

			}
			
			
		}	  	 	  	    
}