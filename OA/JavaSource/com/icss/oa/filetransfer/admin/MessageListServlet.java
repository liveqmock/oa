/*
 * Created on 2004-4-28
 *
 * 
 */
package com.icss.oa.filetransfer.admin;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.j2ee.servlet.ServletBase;

public class MessageListServlet extends ServletBase {

	protected void performTask(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		
		/*
		String resUUID = request.getParameter("resUUID");
		if(resUUID == null) {
			resUUID = "";
		}
		String html = "window.location.href=\""+request.getContextPath()+"/servlet/MessageListServletX?type=system&folder=Inbox&search=no&resUUID="+resUUID+"\";";
		*/
	
		
		String url = "";
		Enumeration enums = request.getParameterNames();
		while(enums.hasMoreElements()){
			String parameterName = (String)enums.nextElement();
			String parameterValue = (String)request.getParameter(parameterName);
			parameterValue = (parameterValue==null?"":java.net.URLEncoder.encode(parameterValue));			
			url += parameterName + "=" + parameterValue + "&";
		}
		if(url.endsWith("&")){
			url = url.substring(0,url.lastIndexOf("&"));
		}		

		String html = "window.location.href=\""+request.getContextPath()+"/servlet/MessageListServletX?"+url+"\";";
		
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<head>");
		out.println("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=GBK\">");
		out.println("<title>welcome</title>");
		out.println("</head>");
		out.println("<body background=\"" + request.getContextPath() + "/images/grid.gif\" style=\"background-attachment: fixed\">");
		out.println("<div id=\"alertbox\" style=\"position:absolute; width:196px; height:24px; z-index:1; left: 300px; top: 200px;\">");
		out.println("	<table width=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"1\" bgcolor=\"#000000\">");
		out.println("		<tr bgcolor=\"#EEFFF7\">");
		out.println("			<td style=\"font-size:12px\" height=\"25\" align=center>正在加载数据，请稍等...</td>");
		out.println("		</tr>");
		out.println("	</table>");
		out.println("</div>");
		out.println("<script language=\"javascript\">");
		out.println(html);
		out.println("</script>");			
		out.println("</body>");
		out.println("</html>");
		out.flush();
		out.close();
	
	}

}
