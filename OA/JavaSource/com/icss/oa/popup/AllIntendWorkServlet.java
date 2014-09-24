package com.icss.oa.popup;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.j2ee.services.ServiceLocatorException;
import com.icss.j2ee.servlet.ServletBase;
import com.icss.j2ee.util.Globals;
import com.icss.oa.config.Config;
import com.icss.oa.intendwork.handler.IntendWork;
import com.icss.oa.util.ParamUtils;
import com.icss.resourceone.common.logininfo.UserInfo;
import com.icss.resourceone.sdk.framework.Context;
import com.icss.resourceone.sdk.framework.EntityException;

public class AllIntendWorkServlet extends ServletBase {

	protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Connection conn = null;
		try {
			conn = getConnection(Globals.DATASOURCEJNDI);

			String page_num = ParamUtils.getParameter(request, "_page_num", false);
			if (page_num == null){
				page_num = "";
			} else {
				page_num = "?_page_num=" + page_num;
			}

			long startDate = ParamUtils.getDateTimeParameter(request, "startTime", 0, Config.DATE_THREAD);
			long endDate = ParamUtils.getDateTimeParameter(request, "endTime", System.currentTimeMillis(), Config.DATE_THREAD);

			String flag = ParamUtils.getParameter(request, "flag", false);
			String topic = ParamUtils.getParameter(request, "topic", false);
			String type = request.getParameter("intype");
			//String type = "'2','12'";
			//1  普通本页打开
			//2  重要本页打开
			//11 普通弹出打开
			//12 重要弹出打开
			Context ctx = Context.getInstance(); 
			UserInfo user = ctx.getCurrentLoginInfo();

			IntendWork intendWork = new IntendWork(conn);
			String sql = intendWork.getSearchSql(user.getUserID(), flag, type, startDate, endDate, topic);

			List workList = intendWork.searchWork(sql);
			
			
			request.setAttribute("workList", workList);

			this.forward(request, response, "/popup/intendWorkList.jsp" + page_num);

		} catch (IOException e) {
			e.printStackTrace();
			handleError(e);
			
		} catch (EntityException e) {
			e.printStackTrace();
			handleError(e);
			
		} catch (ServiceLocatorException e) {
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
