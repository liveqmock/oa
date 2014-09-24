package com.icss.oa.ftp;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.j2ee.dao.DAOException;
import com.icss.j2ee.dao.DAOFactory;
import com.icss.j2ee.services.ServiceLocatorException;
import com.icss.j2ee.util.Globals;
import com.icss.oa.util.FtpPropertyManager;
import com.icss.orgtree.handler.HandlerException;
import com.icss.orgtree.handler.OrgHandler;
import com.icss.orgtree.vo.SysOrgVO;
import com.icss.resourceone.common.logininfo.UserInfo;
import com.icss.resourceone.sdk.framework.Context;
import com.icss.resourceone.sdk.framework.EntityException;
import com.icss.resourceone.sdk.framework.Organization;

/**
 * Servlet implementation class for Servlet: DepAdminServlet
 *
 */
 public class DepAdminServlet extends com.icss.j2ee.servlet.ServletBase implements javax.servlet.Servlet {
    

	 /* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	 protected void performTask(
				HttpServletRequest request,
				HttpServletResponse response)
				throws ServletException, IOException {
		 Context ctx = null;
		 Connection conn = null;
		 
		 try {
			ctx = Context.getInstance();
			UserInfo user = ctx.getCurrentLoginInfo();
			String ftpip = FtpPropertyManager.getProperty("ftpip");
			String ftpuser = FtpPropertyManager.getProperty("ftpuser");
			String ftppass = FtpPropertyManager.getProperty("ftppass");
			conn = this.getConnection(Globals.DATASOURCEJNDI);
			
			OrgHandler handler = new OrgHandler(conn);
			Organization contextorg = handler.getContextUserOrg(user.getPersonUuid());
			SysOrgVO OrgVO = handler.getOrgByCurrentUser(contextorg, 3);
			FtpAdminDAO fadao = new FtpAdminDAO(conn);
			DAOFactory factory = new DAOFactory(conn);
			fadao.setWhereClause("ORG = '"+ OrgVO.getOrguuid()+"'");
			factory.setDAO(fadao);
			List l = factory.find(new FtpAdminVO());
			Integer fid = null;
			if(l !=null && l.size()!=0){
				 fid = ((FtpAdminVO)l.get(0)).getId();
			}
			String admins = "";
			String basepath = "";
			if(l!=null && l.size()!=0){
				admins = ((FtpAdminVO)l.get(0)).getOwener();
				basepath = ((FtpAdminVO)l.get(0)).getFolder_path();
			}
			String ftpdepadmin = "false";
			if(admins.contains(user.getPersonUuid())){
				ftpdepadmin = "true";
			}
			request.setAttribute("ftpdepadmin", ftpdepadmin);
			request.setAttribute("basepath", basepath);
			request.setAttribute("fid", String.valueOf(fid));
			request.setAttribute("ftpip", ftpip);
			request.setAttribute("ftpuser", ftpuser);
			request.setAttribute("ftppass", ftppass);
			this.forward(request, response, "/ftp/deptadmin.jsp");
		} catch (EntityException e) {
			e.printStackTrace();
		} catch (ServiceLocatorException e) {
			e.printStackTrace();
		} catch (HandlerException e) {
			e.printStackTrace();
		} catch (DAOException e) {
			e.printStackTrace();
		}finally{
			try {
				if(!conn.isClosed() && conn!=null){
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}      
}