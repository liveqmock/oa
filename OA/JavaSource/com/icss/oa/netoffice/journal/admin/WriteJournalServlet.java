/*
 * Created on 2004-4-8
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package com.icss.oa.netoffice.journal.admin;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.j2ee.dao.DAOFactory;
import com.icss.j2ee.servlet.ServletBase;
import com.icss.j2ee.util.Globals;
import com.icss.oa.netoffice.journal.dao.OfficeJournalDAO;
import com.icss.oa.netoffice.journal.handler.JournalHandler;
import com.icss.oa.netoffice.journal.vo.OfficeJournalVO;
/**
 * @author Administrator
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public class WriteJournalServlet extends ServletBase{
	protected void performTask(HttpServletRequest request,HttpServletResponse response) 
    throws ServletException, IOException{
		Connection conn = null;
		OfficeJournalVO jvo=null;
		long now=System.currentTimeMillis();
		//long time=now-now%(3600*24*1000);下面的方法不是取的这个整时间
		//System.out.println("tttttttttttttttttttttttttttttime"+time);
		//Long date=new Long(time);
		Date date1=new Date(now);
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String formatTime = formatter.format(date1);
		
		try{
			conn = getConnection(Globals.DATASOURCEJNDI);
			long date2=JournalHandler.getLongByTime(formatTime);
			Long date=new Long(date2);
			OfficeJournalDAO jDao=new OfficeJournalDAO();
			jDao.setRjDate(date);
			DAOFactory factory = new DAOFactory(conn);
			factory.setDAO(jDao);
			List jList=factory.find(new OfficeJournalVO());
			//System.out.println("vvvvvvvvvvvvvvvvvvvvvvvvvvvvvo"+jvo);
			Iterator  journalIterator=null;
			if(!jList.isEmpty()){
			 journalIterator = jList.iterator();
			 jvo=(OfficeJournalVO)journalIterator.next();
			 request.setAttribute("vo",jvo);
			 this.forward(request, response, "/netoffice/journal/content_update.jsp");
			}
			else{
				this.forward(request, response, "/netoffice/journal/write1.jsp");	
			}
			
		}
		catch (Exception e) {
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
