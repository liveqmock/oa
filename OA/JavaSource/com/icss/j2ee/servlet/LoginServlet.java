package com.icss.j2ee.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.j2ee.login.LoginException;
import com.icss.j2ee.login.LoginHandler;
import com.icss.j2ee.message.Message;
import com.icss.j2ee.message.MessageException;
import com.icss.j2ee.servlet.filter.FilterChain;
import com.icss.j2ee.util.ConfigurationUtil;

public class LoginServlet extends HttpServlet {

	public void performTask(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException {
		try {
			String redirectTo = LoginHandler.getInstance().login(request, response);
			try {
				FilterChain chain = FilterChain.getFilterChain(this, request);
				chain.doFilter(request, response);
			} catch (Exception ex) {
				throw new ServletException(ex);
			}

			if (ConfigurationUtil.getInstance().showLoginSuccess()) {

				if (ConfigurationUtil.getInstance().getLoginSuccessPage() != null) {
					getServletContext().getRequestDispatcher(ConfigurationUtil.getInstance().getLoginSuccessPage()).forward(request, response);

					return;
				} else {
					Message msg = new Message(Message.MT_LOGINSUCCESS, "成功", "恭喜登录成功！");
					msg.init(request, response);

					try {
						msg.show();
					} catch (MessageException e) {
						throw new ServletException(e.getMessage());
					}
				}
			} else {
				if (redirectTo != null) {
					response.sendRedirect(redirectTo);
					return;
					
				} else {
					getServletContext().getRequestDispatcher("/").forward(request, response);
				}
			}
		} catch (LoginException e) {
			e.init(request, response);

			try {
				e.show();
			} catch (Exception me) {
				System.out.println("error!");
			}
		} catch (IOException e) {
			throw new ServletException(e.getMessage());
		}
	}

	protected void service(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException, java.io.IOException {
		performTask(request, response);
	}

}
