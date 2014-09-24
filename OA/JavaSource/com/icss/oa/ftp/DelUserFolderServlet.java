package com.icss.oa.ftp;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

import com.icss.j2ee.dao.DAOException;
import com.icss.j2ee.dao.DAOFactory;
import com.icss.j2ee.services.ServiceLocatorException;
import com.icss.j2ee.servlet.ServletBase;
import com.icss.oa.util.FtpPropertyManager;

/**
 * Servlet implementation class for Servlet: DelUserFolderServlet
 *
 */
 public class DelUserFolderServlet extends ServletBase {
	 
	 
	 protected void performTask(
					HttpServletRequest request,
					HttpServletResponse response)
					throws ServletException, IOException {
		 
		Connection conn = null;
		 /*获取参数*/
		String folderstr = request.getParameter("folderstr");
		String filestr = request.getParameter("filestr");
		String curpath = request.getParameter("cur");
		String fid = request.getParameter("fid");
		 /*结束*/
		
		String ftpip = FtpPropertyManager.getProperty("fftpip");
		String ftpuser = FtpPropertyManager.getProperty("fftpuser");
		String ftppass = FtpPropertyManager.getProperty("fftppass");
	
		StringTokenizer folderstk = new StringTokenizer(folderstr,",");
		StringTokenizer filestk = new StringTokenizer(filestr,",");
	
		
		
		try {
			conn = getConnection(com.icss.j2ee.util.Globals.DATASOURCEJNDI);
			DAOFactory factory = new DAOFactory(conn);
			FTPClient ftpClient=new FTPClient();
			ftpClient.connect(ftpip, 21);
			ftpClient.login(ftpuser,ftppass);
			ftpClient.changeWorkingDirectory(gbktoiso(curpath));
			List undelete = new ArrayList();
		
			//删除选中文件
			while(filestk.hasMoreTokens()){
				String filename = filestk.nextToken();
				ftpClient.deleteFile(gbktoiso(filename));
				FtpFileDAO ffdao = new FtpFileDAO(conn);
				ffdao.setWhereClause("FILENAME ='"+ filename +"' AND FOLDER='"+ curpath.substring(1,curpath.length())+filename +"'");
				factory.setDAO(ffdao);
				List l = factory.find();
				if(l!=null && l.size()>0){
					factory.batchDelete();
				}
			}
			while(folderstk.hasMoreElements()){
				String foldername = folderstk.nextToken();
				if(!".".equals(foldername) && !"..".equals(foldername)){
					ftpClient.changeWorkingDirectory(gbktoiso(curpath+"/"+foldername));
					List subfolders = getFolders(ftpClient);
					List subfiles =  getFiles(ftpClient);
					if(subfolders.size() == 2 && subfiles.size()==0){
						ftpClient.changeToParentDirectory();
						ftpClient.removeDirectory(gbktoiso(foldername));
						FtpFolderDAO ffdao = new FtpFolderDAO(conn);	
						ffdao.setWhereClause("NAME = '"+ foldername + "' AND PID="+ fid);
						factory.setDAO(ffdao);
						factory.batchDelete();
					}else{
						undelete.add(foldername);
					}
				}
			}
			ftpClient.disconnect();
			request.setAttribute("curpath",curpath);
			request.setAttribute("undelete",undelete);
			this.forward(request, response, "/servlet/DepAdminServlet");			
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
	 
	 private String gbktoiso(String str){
		 try {
			return new String(str.getBytes("GBK"),"ISO-8859-1");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return "";
		}
	 }
	 
	 private String isotogbk(String str){
		 try {
			return new String(str.getBytes("ISO-8859-1"),"GBK");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return "";
		}
	 }
	 
	public List getFolders(FTPClient ftpClient) throws IOException{
		List folders = new ArrayList();
		FTPFile[] remoteFiles = ftpClient.listFiles();    
	    if(remoteFiles==null||remoteFiles.length==0){
	        System.out.println("There has not any file!");
	    }
	    for(int i=0;i<remoteFiles.length;i++){
	   	   if(remoteFiles[i].isDirectory()){
	            String name = isotogbk(remoteFiles[i].getName());
	            folders.add(name);
	         }
	      }    
	       return folders;
		}
	
	public List getFiles(FTPClient ftpClient) throws IOException{
		List files = new ArrayList();
		 FTPFile[] remoteFiles = ftpClient.listFiles();    
       if(remoteFiles==null||remoteFiles.length==0){
           System.out.println("There has not any file!");
       }

      for(int i=0;i<remoteFiles.length;i++){
   	   if(remoteFiles[i].isFile()){
            String name = isotogbk(remoteFiles[i].getName());
            files.add(name);
         }
      }    
       return files;
	}
}