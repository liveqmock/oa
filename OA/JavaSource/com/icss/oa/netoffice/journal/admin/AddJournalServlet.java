/*
 * Created on 2004-4-6
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package com.icss.oa.netoffice.journal.admin;


import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.j2ee.servlet.ServletBase;
import com.icss.j2ee.util.Globals;
import com.icss.oa.netoffice.journal.handler.JournalHandler;
import com.icss.oa.netoffice.journal.vo.OfficeJournalVO;
import com.icss.oa.util.CommUtil;
/**
 * @author Administrator
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public class AddJournalServlet extends ServletBase{
	
	protected void performTask(HttpServletRequest request,HttpServletResponse response) 
	                throws ServletException, IOException{
		Connection conn = null;
		String subject = request.getParameter("subject");
		String weather = request.getParameter("weather");
		String content_unformat = request.getParameter("content");
		
		String time1 = request.getParameter("time");
	
		
		
		int k=0;
		
		
		//long time = System.currentTimeMillis();
		
		//Long date=new Long(time);
		
		OfficeJournalVO jVO = new OfficeJournalVO();
		//System.out.println("hhhhhhhhhhhhhh");
		//String check[]=new String[60];
		//check=request.getParameterValues("delete");
		//System.out.println(check);
		try {
			conn = getConnection(Globals.DATASOURCEJNDI);
			String content=CommUtil.formathtm(content_unformat);
			long time=JournalHandler.getLongByTime(time1);
			Long date=new Long(time);
			jVO.setRjDate(date);
			jVO.setRjHeadline(subject);
			jVO.setRjContent(content);
			
			jVO.setRjWeather(weather);
			
			JournalHandler jHandler = new JournalHandler(conn);
			String personUUID=new String();
			personUUID=jHandler.getUserId();
			jVO.setRjOwner(personUUID);
			jHandler.addJournal(jVO);
			
			//System.out.println("Add successfully!!!!");
			this.forward(request,response,"/servlet/ShowJournalServlet");
			//response.sendRedirect("ShowJournalServlet");
			
		} catch (Exception e) {
			e.printStackTrace();
			//System.out.println(e.toString());
			handleError(e);
			
		} finally {
			try {
				if (conn != null){
					conn.close();
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	
	}
}
