package com.icss.oa.sync.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.common.log.ConnLog;
import com.icss.j2ee.servlet.ServletBase;
import com.icss.oa.hr.admin.HRGroupWebservice;
import com.icss.oa.sync.handler.OrgSyncHandler;

public class GetSyncOrgServlet extends ServletBase {

	protected void performTask(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		
		
		/*原来的代码
		Connection conn = null;
		String JNDI = "jdbc/ROEEE";
		try {
			
			
			conn = this.getConnection(JNDI);
			ConnLog.open("GetSyncOrgServlet");
			
			Integer pass = request.getParameter("pass")==null?0:new Integer(request.getParameter("pass"));
			String  type = request.getParameter("type")==null?"all":request.getParameter("type");
			
			OrgSyncHandler handler = new OrgSyncHandler(conn);
			List orglist = handler.getSyncOrg(pass,type);
			
			request.setAttribute("orglist", orglist);
			
			this.forward(request, response, "/syncperson/syncorg.jsp");
			
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			if (conn != null)
				try {
					conn.close();
					ConnLog.close("GetSyncOrgServlet");
				} catch (Exception e2) {
					e2.printStackTrace();
				}

		}*/
		
		
		
		//修改后的代码
		Connection conn = null;
		String JNDI = "jdbc/ROEEE";
		try {
			
			
			conn = this.getConnection(JNDI);
			ConnLog.open("GetSyncOrgServlet");
			
			
			String orgname=request.getParameter("orgname");
			String orgcode=request.getParameter("orgcode");
			String Otype=request.getParameter("operatetype");
			String Atype=request.getParameter("audittype");
			
			//第一次进入页面时,自动将审核状态置为"待审核"
			if(Atype==null){
				Atype="0";
			}
			
			
			
			Integer pass = request.getParameter("pass")==null?0:new Integer(request.getParameter("pass"));
			String  type = request.getParameter("type")==null?"all":request.getParameter("type");
			
			OrgSyncHandler handler = new OrgSyncHandler(conn);
			List orglist = handler.getSyncOrg(orgname,orgcode,Otype,Atype);
			
			
			
			request.setAttribute("orgcode", orgcode);
		    request.setAttribute("orgname", orgname);
		    request.setAttribute("operatetype", Otype);
		    request.setAttribute("audittype", Atype);
			
			request.setAttribute("orglist", orglist);
			
			this.forward(request, response, "/syncperson/syncauditorg.jsp");
			
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			if (conn != null)
				try {
					conn.close();
					ConnLog.close("GetSyncOrgServlet");
				} catch (Exception e2) {
					e2.printStackTrace();
				}

		}
		
		
		
		
		
	}
	
}