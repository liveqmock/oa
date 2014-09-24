package com.icss.oa.ftp;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.j2ee.dao.DAOException;
import com.icss.j2ee.dao.DAOFactory;
import com.icss.j2ee.services.ServiceLocatorException;
import com.icss.j2ee.servlet.ServletBase;
import com.icss.oa.util.FtpPropertyManager;
import com.icss.oa.util.PropertyManager;
import com.icss.orgtree.handler.HandlerException;
import com.icss.orgtree.handler.OrgHandler;
import com.icss.orgtree.vo.SysOrgVO;
import com.icss.resourceone.common.logininfo.UserInfo;
import com.icss.resourceone.sdk.framework.Context;
import com.icss.resourceone.sdk.framework.EntityException;
import com.icss.resourceone.sdk.framework.Organization;

/**
 * Servlet implementation class for Servlet: FtpLoginServlet
 *
 */
 public class FtpLoginServlet extends ServletBase {
	 

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
			conn = getConnection(com.icss.j2ee.util.Globals.DATASOURCEJNDI);
			
			ctx = Context.getInstance();
			UserInfo user = ctx.getCurrentLoginInfo();
			String ftpip = FtpPropertyManager.getProperty("ftpip");
			String ftpuser = FtpPropertyManager.getProperty("ftpuser");
			String ftppass = FtpPropertyManager.getProperty("ftppass");

			String writestr = "";
			String onlyreadstr = "";
			OrgHandler handler = new OrgHandler(conn);
			Organization contextorg;
			contextorg = handler.getContextUserOrg(user.getPersonUuid());
			SysOrgVO OrgVO = handler.getOrgByCurrentUser(contextorg, 3);
			FtpAdminDAO fadao = new FtpAdminDAO(conn);
			DAOFactory factory = new DAOFactory(conn);
			fadao.setWhereClause("ORG = '"+ OrgVO.getOrguuid()+"'");
			factory.setDAO(fadao);
			List l = factory.find(new FtpAdminVO());
			String basepath = ((FtpAdminVO)l.get(0)).getFolder_path();
			Integer fid = new Integer("0");
			if(l.size()!=0){
				fid = ((FtpAdminVO)l.get(0)).getId();
			}
			FtpFolderDAO ffdao = new FtpFolderDAO(conn);
			ffdao.setWhereClause("PID='"+ fid.toString() +"'");
			factory.setDAO(ffdao);
			List folders = factory.find(new FtpFolderVO());
			for(int i=0;i<folders.size();i++){
				FtpFolderVO temp = (FtpFolderVO)folders.get(i);
				if("1".equals(temp.getRight())){
					writestr = writestr + "," + temp.getFather()+temp.getName();
					onlyreadstr = onlyreadstr + "," + temp.getFather()+temp.getName();
				}else if( "2".equals(temp.getRight())){
					onlyreadstr = onlyreadstr + "," + temp.getFather()+temp.getName();
				}
				FtpUserRightDAO furdao = new FtpUserRightDAO(conn);
				furdao.setWhereClause("FOLDER='"+ temp.getFather()+temp.getName() +"' AND UNAME ='"+ user.getPersonUuid() +"'");
				factory.setDAO(furdao);
				List rl = factory.find(new FtpUserRightVO());
				if(rl.size()>0){
					FtpUserRightVO furvo = (FtpUserRightVO)rl.get(0);
					if( "1".equals(furvo.getRight())){
						writestr = writestr + "," + temp.getFather()+temp.getName();
						onlyreadstr = onlyreadstr + "," + temp.getFather()+temp.getName();
					}else if( "2".equals(furvo.getRight())){
						onlyreadstr = onlyreadstr + "," + temp.getFather()+temp.getName();
					}
				}
			}

			request.setAttribute("serverip", request.getServerName());
			request.setAttribute("orguuid", OrgVO.getOrguuid());
			request.setAttribute("uuid", user.getPersonUuid());
			request.setAttribute("ftpip", ftpip);
			request.setAttribute("ftpuser", ftpuser);
			request.setAttribute("ftppass", ftppass);
			request.setAttribute("readstr", onlyreadstr);
			request.setAttribute("writestr", writestr);
			request.setAttribute("basepath", basepath);
			this.forward(request, response, "/ftp/index.jsp");
			
		} catch (EntityException e) {
			e.printStackTrace();
		} catch (ServiceLocatorException e) {
			e.printStackTrace();
		} catch (HandlerException e) {
			e.printStackTrace();
		} catch (DAOException e) {
			e.printStackTrace();
		} finally{
			try {
				if(conn!=null && !conn.isClosed()){
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		 
	
	}   	  	    
}