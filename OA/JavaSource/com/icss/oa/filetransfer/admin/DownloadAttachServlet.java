/*
 * Created on 2004-5-12
 *
 */

package com.icss.oa.filetransfer.admin;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.URLEncoder;

import com.icss.j2ee.servlet.download.DownloadResponse;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.j2ee.servlet.ServletBase;
import com.icss.oa.filetransfer.util.FiletransferUtil;

/**
 * @author sunchuanting
 */
public class DownloadAttachServlet extends ServletBase {

	protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BufferedInputStream bi = null;
		OutputStream os = null;
		InputStream is = null;
		String url1=null,url2=null;
		try {
			String filename = request.getParameter("filename");
			String url = request.getParameter("url");
			String down = request.getParameter("down");
			url1=url;
			//response.getWriter()
			//下载路径修改 2005-12-28 
			//原有程序：
			//ServletContext context = this.getServletContext();
			//String path = context.getRealPath("/filetransfer/downloadfile/");  
			//修改程序：
			String path = FiletransferUtil.getDownloadFilePath();
	
			String extend = filename.substring(filename.lastIndexOf(".")+1,filename.length());

			if(("doc".equals(extend) || "xls".equals(extend)) && !"1".equals(down)){
				if("doc".equals(extend)){
					response.setContentType("application/msword");
					response.setHeader("Content-Disposition", "inline;filename="+new String(filename.getBytes("gb2312"),"ISO8859-1"));
				}else if("xls".equals(extend)){
					response.setContentType("application/vnd.ms-excel");
					response.setHeader("Content-Disposition", "inline;filename="+new String(filename.getBytes("gb2312"),"ISO8859-1"));
				}
				
				
				url2="aaa"+path + url;
				bi = new BufferedInputStream(new FileInputStream(path + url));
				 
				os = response.getOutputStream();
				byte[] buffer = new byte[1024];
				while (bi.read(buffer) != -1){
					os.write(buffer);
				}
				os.flush();
				bi.close();
				os.close();
				buffer=null;
			}else{
				is = new FileInputStream(path + url);
				DownloadResponse ds = new DownloadResponse(response);
				ds.downloadFileInputStream(is, filename);
				is.close();
			}
		} catch (Exception e) {
			 throw new RuntimeException("下载错误:" + url1+url2);
		} finally {
			if (bi != null) {
				bi.close();
				bi=null;
			}
			if (os != null) {
				os.close();
				os=null;
			}
			if (is !=null){
				is.close();
				is=null;
			}
		}

	}

}
