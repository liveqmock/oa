package com.icss.oa.ftp;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.j2ee.dao.DAOException;
import com.icss.j2ee.dao.DAOFactory;
import com.icss.j2ee.services.ServiceLocatorException;
import com.icss.j2ee.servlet.ServletBase;
import com.icss.oa.address.dao.SysOrgDAO;
import com.icss.oa.address.dao.SysPersonDAO;
import com.icss.oa.address.vo.SysOrgVO;
import com.icss.oa.address.vo.SysPersonVO;

/**
 * Servlet implementation class for Servlet: ShowUserRightServlet
 *
 */
 public class ShowUserRightServlet extends ServletBase {
	
	 
	 protected void performTask(
					HttpServletRequest request,
					HttpServletResponse response)
					throws ServletException, IOException {
		 Connection conn = null;
		 String fid  = request.getParameter("fid");
		 String cur  = request.getParameter("cur");
		 String orgs  = request.getParameter("org");
		 
		 try {
			conn = getConnection(com.icss.j2ee.util.Globals.DATASOURCEJNDI);
			FtpAdminDAO fadao = new FtpAdminDAO(conn);
			FtpAdminVO favo = new FtpAdminVO();
			DAOFactory factory = new DAOFactory(conn);
			fadao.setWhereClause("ID =" + fid);
			factory.setDAO(fadao);
			List dirlist = factory.find(favo);
			String org = "";
			if(dirlist!=null && dirlist.size()!=0){
				org = ((FtpAdminVO)dirlist.get(0)).getOrg();
			}
			SysOrgDAO sodao = new SysOrgDAO();
			factory.setDAO(sodao);
			sodao.setWhereClause(" CNNAME='"+org+"' AND ORGLEVEL='3'");
			List orglist = factory.find(new SysOrgVO());
			if(orglist!=null && orglist.size()!=0){
				org = ((SysOrgVO)orglist.get(0)).getOrguuid();
			}
			FtpFolderDAO ffdao = new FtpFolderDAO(conn);
			String parentfolder = cur.substring(0,cur.lastIndexOf("/")+1);
			String curfolder = cur.substring(cur.lastIndexOf("/")+1,cur.length());
			ffdao.setWhereClause("NAME='"+ curfolder +"' AND FATHER='"+ parentfolder +"'");
			factory.setDAO(ffdao);
			List l = factory.find(new FtpFolderVO());
			String right = ((FtpFolderVO)l.get(0)).getRight();
			FtpUserRightDAO furdao = new FtpUserRightDAO(conn);
			factory.setDAO(furdao);
			furdao.setWhereClause("FOLDER = '"+cur+"' AND RIGHT = '"+ 1 +"'");
			List writelist = new ArrayList();
			String writestr = "";
			String writeuuid = "";
			writelist = factory.find(new FtpUserRightVO());
			for(int i=0;i<writelist.size();i++){
				String tempid = ((FtpUserRightVO)writelist.get(i)).getUname();
				SysPersonDAO spdao = new SysPersonDAO(conn);
				spdao.setWhereClause("PERSONUUID ='"+ tempid +"'");
				factory.setDAO(spdao);
				List sp = factory.find(new SysPersonVO());
				if(sp!=null && sp.size()>0){
					writestr = writestr + "," + ((SysPersonVO)sp.get(0)).getCnname();
					writeuuid = writeuuid + "," + ((SysPersonVO)sp.get(0)).getPersonuuid();
 				}
			}
			furdao.setWhereClause("FOLDER = '"+cur+"' AND RIGHT = '"+ 2 +"'");
			factory.setDAO(furdao);
			List readlist = new ArrayList();
			String readstr = "";
			String readuuid = "";
			readlist = factory.find(new FtpUserRightVO());
			for(int i=0;i<readlist.size();i++){
				String tempid = ((FtpUserRightVO)readlist.get(i)).getUname();
				SysPersonDAO spdao = new SysPersonDAO(conn);
				spdao.setWhereClause("PERSONUUID ='"+ tempid +"'");
				factory.setDAO(spdao);
				List sp = factory.find(new SysPersonVO());
				if(sp!=null && sp.size()>0){
					readstr = readstr + "," + ((SysPersonVO)sp.get(0)).getCnname();
					readuuid = readuuid + "," + ((SysPersonVO)sp.get(0)).getPersonuuid();
				}
			}
			if(orgs != null){
				request.setAttribute("org", orgs);
			}else{
				request.setAttribute("org", org);
			}
			request.setAttribute("cur", cur);
			request.setAttribute("right", right);
			request.setAttribute("writestr", writestr);
			request.setAttribute("writeuuid", writeuuid);
			request.setAttribute("readstr", readstr);
			request.setAttribute("readuuid", readuuid);
			this.forward(request, response, "/ftp/userright.jsp");
		} catch (ServiceLocatorException e) {
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