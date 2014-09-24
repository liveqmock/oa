package com.icss.oa.sync.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;

import com.icss.common.log.ConnLog;
import com.icss.j2ee.dao.DAOFactory;
import com.icss.j2ee.services.DBConnectionLocator;
import com.icss.j2ee.servlet.ServletBase;
import com.icss.oa.sync.dao.OrgSyncDAO;
import com.icss.oa.sync.dao.PersonSyncDAO;
import com.icss.oa.sync.handler.OrgSyncHandler;
import com.icss.oa.sync.vo.OrgSyncVO;
import com.icss.oa.sync.vo.PersonSyncVO;
import com.icss.resourceone.org.model.OrgVO;

public class OrgPassServlet extends ServletBase {

	
	
	protected void performTask(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		String method = request.getParameter("method");
		if(method==null){
			method = "";
		}
		
		if(method.equals("audit")){
			audit(request,response);
		} 
		
	}
	
	
	protected void audit(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		
		Connection conn = null;
		String JNDI = "jdbc/ROEEE";
		String type = request.getParameter("type");

		try {

			conn = this.getConnection(JNDI);
			ConnLog.open("OrgPassServlet");

			OrgSyncHandler handler = new OrgSyncHandler(conn);
			String[] syncorg = request.getParameterValues("syncorg");
			if (type != null && "no".equals(type)) {
				for (int i = 0; i < syncorg.length; i++) {
					// 将通过审批的记录取出来，调用handler的处理函数进行处理
					String id = syncorg[i];
					handler.updflag(id, -1);
				}

			} else {
				for (int i = 0; i < syncorg.length; i++) {
					// 将通过审批的记录取出来，调用handler的处理函数进行处理
					String id = syncorg[i];

					boolean flag = handler.passOrg(id);

					if (flag) {
						handler.updflag(id, 1);
					}

				}
			}

			response.sendRedirect("/oabase/servlet/GetSyncOrgServlet");

		} catch (Exception e) {

			PrintWriter out = response.getWriter();
			out.println(e);
			e.printStackTrace();

		} finally {
			if (conn != null)
				try {
					conn.close();
					ConnLog.close("OrgPassServlet");
				} catch (Exception e2) {
					e2.printStackTrace();
				}
		}
		 
	}
	
	
}
