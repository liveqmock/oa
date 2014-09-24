/*
 * Created on 2004-4-19
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package com.icss.oa.netoffice.schedule.admin;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.j2ee.servlet.ServletBase;

import java.io.*;

/**
 * @author Administrator
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public class ScheduleMainServlet extends ServletBase{
	
	protected void performTask(HttpServletRequest request,
			HttpServletResponse response)
			throws ServletException, IOException{
		try{
			this.forward(request, response, "/netoffice/schedule/schedule_calendar.jsp");
		}catch(Exception e){
			System.err.println("aaaaaaaaaaaaaaaaa"+e.getMessage());
			
		}
		
	}
}
