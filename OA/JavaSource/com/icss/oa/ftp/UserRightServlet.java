package com.icss.oa.ftp;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.j2ee.dao.DAOException;
import com.icss.j2ee.dao.DAOFactory;
import com.icss.j2ee.services.ServiceLocatorException;
import com.icss.j2ee.servlet.ServletBase;

/**
 * Servlet implementation class for Servlet: UserRightServlet
 *
 */
 public class UserRightServlet extends ServletBase {
	
	 
	 protected void performTask(
					HttpServletRequest request,
					HttpServletResponse response)
					throws ServletException, IOException {
		 Connection conn = null;
		 String[] allread  = request.getParameterValues("allread");
		 String[] allwrite  = request.getParameterValues("allwrite");
		 String readp = request.getParameter("readuuid");
		 String writep = request.getParameter("writeuuid");
		 String desfolder = request.getParameter("cur");
		 String org = request.getParameter("org");
		 List readandwrite = new ArrayList();
		 List onlyread = new ArrayList();
		 String flag = "";
		 String parentfolder = desfolder.substring(0,desfolder.lastIndexOf("/")+1);
		 String curfolder = desfolder.substring(desfolder.lastIndexOf("/")+1,desfolder.length());

		 if(allread == null && allwrite == null){
			 flag = "0";
		 }else if(allread != null && allwrite == null){
			 flag = "2";
		 }else if(allread == null && allwrite != null){
			 flag = "1";
		 }else if(allread != null && allwrite != null){
			 flag = "1";
		 }
		 
		 try {
			 conn = getConnection(com.icss.j2ee.util.Globals.DATASOURCEJNDI);
			 FtpFolderDAO ffdao = new FtpFolderDAO(conn);
			 ffdao.setWhereClause("NAME='"+curfolder+"' AND FATHER='"+ parentfolder +"'");
			 ffdao.setRight(flag);
			 ffdao.update(false);

			 StringTokenizer wstz = new StringTokenizer(writep,",");
			 StringTokenizer rstz = new StringTokenizer(readp,",");
			 while(wstz.hasMoreElements()){
				 String s = (String)wstz.nextElement();
				 readandwrite.add(s);
			 }
			 while(rstz.hasMoreElements()){
				 String s = (String)rstz.nextElement();
				 if(!writep.contains(s)){
					 onlyread.add(s);
				 }
			 }
			 DAOFactory factory = new DAOFactory(conn);
			 for(int i=0;i< readandwrite.size();i++){
				 String uname = (String)readandwrite.get(i);
				 FtpUserRightDAO temp = new FtpUserRightDAO(conn);
				 temp.setWhereClause("UNAME = '"+ uname +"' AND FOLDER='"+ desfolder +"'");
				 factory.setDAO(temp);
				 List l = factory.find(new FtpUserRightVO());
				 if(l.size()>0){
					 temp.setOrg(org);
					 temp.setRight("1");	
					 temp.setWhereClause("UNAME = '"+ uname +"' AND FOLDER='"+ desfolder +"' AND ORG='"+ org +"'");
					 temp.update(false);
				 }else{
					 temp.setUname(uname);
					 temp.setFolder(desfolder);
					 temp.setOrg(org);
					 temp.setRight("1");
					 temp.create();
				 }
			 }
			 for(int i=0;i<onlyread.size();i++){
				 String uname = (String)onlyread.get(i);
				 FtpUserRightDAO temp = new FtpUserRightDAO(conn);
				 temp.setWhereClause("UNAME = '"+ uname +"' AND FOLDER='"+ desfolder +"'");
				 factory.setDAO(temp);
				 List l = factory.find(new FtpUserRightVO());
				 if(l.size() == 0){
					 temp.setUname(uname);
					 temp.setFolder(desfolder);
					 temp.setOrg(org);
					 temp.setRight("2");
					 temp.create();
				 }
			 }
			 
			 this.forward(request, response, "/servlet/ShowUserRightServlet");
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
				e.printStackTrace();				}
		}
		  	  
	 } 	  	    
}