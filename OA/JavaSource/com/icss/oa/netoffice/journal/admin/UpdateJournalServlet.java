/*
 * Created on 2004-4-7
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
/**
 * @author Administrator
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public class UpdateJournalServlet extends ServletBase{
	
	protected void performTask(HttpServletRequest request,HttpServletResponse response) 
    throws ServletException, IOException{
		Connection conn = null;
		String id1 = request.getParameter("id1");
		Integer id=new Integer(id1);
		
		String subject = request.getParameter("subject");
		String weather = request.getParameter("weather");
		String date1 = request.getParameter("date1");
		Long date=new Long(date1);
		String content = request.getParameter("content");
		content = content.substring(2);
		
		
		OfficeJournalVO jVO = new OfficeJournalVO();
		
		try {
			conn = getConnection(Globals.DATASOURCEJNDI);
			jVO.setRjId(id);
			jVO.setRjDate(date);
			jVO.setRjHeadline(subject);
			jVO.setRjContent(content);
			jVO.setRjWeather(weather);
			JournalHandler jHandler = new JournalHandler(conn);
			String personUUID=new String();
			personUUID=jHandler.getUserId();
			jVO.setRjOwner(personUUID);
			jHandler.updateJournal(jVO);
			
			this.forward(request,response,"/servlet/ShowJournalServlet");
			//response.sendRedirect("ShowJournalServlet");
			
		} catch (Exception e) {
			System.out.println(e.toString());
			e.printStackTrace();
			handleError(e);
		} finally {
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}

	}
}
