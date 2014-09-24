/*
 * Created on 2004-4-28
 */
package com.icss.oa.filetransfer.admin;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.j2ee.servlet.ServletBase;

/**
 * ·¢ËÍÎÄ¼þ
 */
public class SendFileServlet extends ServletBase{    
	
	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	protected void performTask( HttpServletRequest request, HttpServletResponse response ) 
	throws ServletException, IOException {
		
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("/multi.txt")));
		Enumeration e = request.getParameterNames();
		while(e.hasMoreElements()){
			bw.write((String)e.nextElement()+"\n");
			bw.flush();
		}
		String num = request.getParameter("filenum");

		for(int i=0;i<Integer.parseInt(num);i++){
			String compname = "file_"+i;
		}
		String oldFileName = getUploadOldFileName(request, "file_0");
		bw.write("oldFileName:"+oldFileName+"\n");
		bw.flush();
		String fileFillName = getUploadFileFullName(request, "file_0");
		bw.write("fileFillName:"+fileFillName+"\n");
		bw.flush();

	}

}
