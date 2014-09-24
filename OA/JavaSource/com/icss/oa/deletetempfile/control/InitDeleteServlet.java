/*
 * Created on 2004-11-17
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.icss.oa.deletetempfile.control;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.j2ee.servlet.ServletBase;
import com.icss.oa.deletetempfile.handler.PathBean;

public class InitDeleteServlet extends ServletBase{

	protected void performTask(HttpServletRequest arg0, HttpServletResponse arg1) throws ServletException, IOException {
	}
	
	public void init(){
		ServletContext context = this.getServletContext();
		String mailuploadpath = context.getRealPath(File.separator+"filetransfer"+File.separator+"uploadfile"+File.separator);
		File mailuppath = new File(mailuploadpath);
		if(!mailuppath.exists()){
			mailuppath.mkdirs();
		}
		PathBean pbean = PathBean.getInstance();
		pbean.setMailuploadpath(mailuploadpath);
		System.out.println("the inited mailtemppath is:"+pbean.getMailuploadpath());
		
		String maildownloadpath = context.getRealPath(File.separator+"filetransfer"+File.separator+"downloadfile"+File.separator);
		File maildownpath = new File(mailuploadpath);
		if(!maildownpath.exists()){
			maildownpath.mkdirs();
		}
		pbean.setMaildownloadpath(maildownloadpath);
		
	}

}
