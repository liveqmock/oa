package com.icss.oa.address.admin;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.j2ee.services.ServiceLocatorException;
import com.icss.j2ee.servlet.ServletBase;
import com.icss.oa.address.handler.HandlerException;
import com.icss.oa.filetransfer.handler.personInfoHandler;
import com.icss.resourceone.common.logininfo.UserInfo;
import com.icss.resourceone.sdk.framework.Context;
import com.icss.resourceone.sdk.framework.EntityException;

public class OrgServlet extends ServletBase {
	
	protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Connection conn = null;
		
		Context ctx = null;
		try {
			String doFunction = "";
			if (request.getParameter("doFunction") != "") {
				doFunction = request.getParameter("doFunction");
			}
			//						System.out.println("SelectCommonGroupServlet_doFunction="+doFunction);
			String sessionname = "";
			if (request.getParameter("sessionname") != "") {
				sessionname = request.getParameter("sessionname");
			}

							conn = getConnection(com.icss.j2ee.util.Globals.DATASOURCEJNDI);
			//				
//							SysOrgpersonHandler handler = new SysOrgpersonHandler(conn);
			//				List orglist = handler.getOrgByLevel(3);

			//				String orguuid = null;
			//				List personlist = null;
			//				if (null!=request.getParameter("orguuid")||!"".equals(request.getParameter("orguuid"))){
			//					orguuid=request.getParameter("orguuid");
			//				}
			//				if(null!=orguuid){
			//					EntityManager entitymanger = EntityManager.getInstance();
			//					personlist = entitymanger.findPersonsRecursivelyByOrgUuid(orguuid);
			//				}
			//				request.setAttribute("orglist", orglist);
			//				request.setAttribute("personlist", personlist);
			
			ctx = Context.getInstance();
			UserInfo user = ctx.getCurrentLoginInfo();
			
			
			personInfoHandler _personInfoHandler = new personInfoHandler(conn);
			
			String orguuid = _personInfoHandler.getOrgUUID(user.getPersonUuid());
			
			String _orguuid = _personInfoHandler.getParentUUID(orguuid);

			request.setAttribute("_orguuid",_orguuid);
			
			this.forward(request, response, "/address/pubaddress/selectbyorg.jsp?doFunction=" + doFunction + "&sessionname=" + sessionname);
			
		} 
//		catch (IOException ex) {
//			ex.printStackTrace();
//			handleError(ex);
//			
//		} 
		catch (EntityException e) {
			e.printStackTrace();
			handleError(e);
		} 
		catch (HandlerException e) {
			e.printStackTrace();
			handleError(e);
		} catch (ServiceLocatorException e) {
			e.printStackTrace();
			handleError(e);
		} 
		finally {
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
		
	}
	
}



