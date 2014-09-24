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
import com.icss.j2ee.servlet.ServletBase;
import com.icss.j2ee.util.Globals;
import com.icss.oa.sms.handler.SMSHandler;
import com.icss.oa.sms.vo.SMSPersonRoleSearchVO;
import com.icss.resourceone.common.logininfo.UserInfo;
import com.icss.resourceone.sdk.framework.Context;

public class ViewSMSHistoryServlet extends ServletBase {

	protected void performTask(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		Connection conn = null;
		Context ctx = null;

		try {
			conn = this.getConnection(Globals.DATASOURCEJNDI);
			ConnLog.open("ViewSMSHistoryServlet");
			// 取得查看人信息
			ctx = Context.getInstance();
			UserInfo user = ctx.getCurrentLoginInfo();
			if(user!=null){
			String userid= user.getPersonUuid();
			//System.out.println("userid"+userid);
			
			SMSHandler hl=new SMSHandler(conn);
			List rolelist=hl.getPersonSMSRole(userid);
			Iterator role=rolelist.iterator();
			
			//判断查看历史纪录是本人，还是全部
			Integer ishistory =0;
			while (role.hasNext()){
				SMSPersonRoleSearchVO roleVO = (SMSPersonRoleSearchVO)role.next();
				if(roleVO.getIshistory()>=ishistory){
					ishistory=roleVO.getIshistory();
				}
								
			}
			List historylist =null;
			if(ishistory ==1){
				historylist = hl.getAllHistory();
			}
			else{
				historylist = hl.getPersonHistory(userid);
			}
			request.setAttribute("historylist", historylist);
			this.forward(request, response, "/sms/smsHistory.jsp");
			}
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			if (conn != null) {
				try {
					conn.close();
					ConnLog.close("ViewSMSHistoryServlet");
				} catch (SQLException e2) {
					e2.printStackTrace();
				}
			}

		}

	}

}
