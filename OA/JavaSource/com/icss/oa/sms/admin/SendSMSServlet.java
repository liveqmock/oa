package com.icss.oa.sms.admin;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.common.log.ConnLog;
import com.icss.j2ee.services.ServiceLocatorException;
import com.icss.j2ee.servlet.ServletBase;
import com.icss.j2ee.util.Globals;
import com.icss.oa.sms.handler.SMSHandler;
import com.icss.oa.sms.vo.SMSPersonRoleSearchVO;
import com.icss.resourceone.common.logininfo.UserInfo;
import com.icss.resourceone.sdk.framework.Context;
import com.icss.resourceone.sdk.framework.EntityException;
import com.icss.resourceone.sdk.right.RightException;
import com.icss.resourceone.sdk.right.RightManager;

public class SendSMSServlet extends ServletBase {

	protected void performTask(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		Connection conn = null;
		Context ctx = null;

		try {

			//System.out.print("^^^" + request.getRequestURI());
			RightManager rm = RightManager.getInstance();
		
			//System.out.print("^^^" + rm.hasRight("${_context_oabase}/servlet/SendSMSServlet"));
			if (rm.hasRight("${_context_oabase}/servlet/SendSMSServlet")) {
				conn = this.getConnection(Globals.DATASOURCEJNDI);
				ConnLog.open("SendSMSServlet");
				// 取得发件人信息
				ctx = Context.getInstance();
				UserInfo user = ctx.getCurrentLoginInfo();
				String userid = "";
				userid = user.getPersonUuid();
				SMSHandler handler = new SMSHandler(conn);
				List roleList = handler.getPersonSMSRole(userid);

				// 判断是否可以手工输入手机号
				Integer isout = new Integer(0);
				Integer sendnumber = new Integer(20);
				Iterator rl = roleList.iterator();
				while (rl.hasNext()) {
					SMSPersonRoleSearchVO vo = (SMSPersonRoleSearchVO) rl
							.next();
					if (vo.getIsout().intValue() >= isout.intValue()) {
						isout = vo.getIsout();
					}
					if (vo.getSendnumber().intValue() >= sendnumber.intValue()) {
						sendnumber = vo.getSendnumber();
					}
				}
				request.setAttribute("sendnumber", sendnumber);
				request.setAttribute("isout", isout);
				this.forward(request, response, "/sms/sendSMS.jsp");

			} else {
				response.getWriter().append("你没有权限访问该页面!").flush();
			}	
		} catch (ServiceLocatorException e1) {
			e1.printStackTrace();

		} catch (EntityException e) {
			e.printStackTrace();

		} catch (RightException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.close();
					ConnLog.close("SendSMSServlet");
				} catch (SQLException e2) {
					e2.printStackTrace();
				}
			}

		}

	}

}
