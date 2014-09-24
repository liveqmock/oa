/*
 * Created on 2004-4-21
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package com.icss.oa.message.api;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.j2ee.servlet.ServletBase;

/**
 * @author Administrator
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public class GetShortMsgServlet extends ServletBase {
	public void doGet(
		HttpServletRequest request, 
		HttpServletResponse response) 
		throws IOException{	
			System.out.println("getmsg1:begin");
			String msgnum=request.getParameter(MSGSender.getSendArgName());	
			System.out.println("getmsg2:"+msgnum);
			response.setContentType("text/xml");
			String s=MSGSender.getXml(msgnum);
			System.out.println("getmsg3:"+s);
			response.getWriter().write(s);	
			System.out.println("getmsg4:end");
		}
		protected void performTask(
				HttpServletRequest arg0, 
				HttpServletResponse arg1) 
			throws ServletException, IOException {			
		}	
	}