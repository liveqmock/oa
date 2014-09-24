/*
 * Created on 2004-3-31
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package com.icss.oa.netoffice.journal.admin;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.common.log.ConnLog;
import com.icss.j2ee.servlet.ServletBase;
import com.icss.j2ee.util.Globals;
import com.icss.oa.config.TypeConfig;
import com.icss.oa.netoffice.journal.handler.JournalHandler;
import com.icss.oa.netoffice.setnetoffice.handler.SetNetofficeHandler;
import com.icss.resourceone.common.logininfo.UserInfo;
import com.icss.resourceone.sdk.framework.Context;

/**
 * @author Administrator
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public class ShowJournalServlet extends ServletBase{
	protected void performTask(HttpServletRequest request,
			HttpServletResponse response)
			throws ServletException, IOException{
		Connection conn = null;
		List list1 = new ArrayList();
		Integer months=null;
		try{
		  conn = this.getConnection(Globals.DATASOURCEJNDI);
		  ConnLog.open("ShowJournalServlet");
		  JournalHandler journalHandler = new JournalHandler(conn);		
		  

		  Context ctx = Context.getInstance();
		  UserInfo user = ctx.getCurrentLoginInfo();
		  

		  list1 = journalHandler.getJournalList(user.getPersonUuid());
//		  list1 = journalHandler.getJournalList();
		  request.setAttribute("list",list1);
		  System.out.println("get list success!...........");
		  
		  SetNetofficeHandler setHandler=new SetNetofficeHandler(conn);
		  		  months=setHandler.getMonthsByType(TypeConfig.JOURNAL_TYPE);
		  
		  System.out.println("months is...........="+months);
		  this.forward(request, response, "/netoffice/journal/journal.jsp?months="+months); //
		}
		catch(Exception e){
			System.out.println(e.toString());
			handleError(e);
		}finally {
			try {
				if (conn != null){
					conn.close();
					ConnLog.close("ShowJournalServlet");
				}
			} catch (SQLException e1) {
				
				e1.printStackTrace();
			}
		}
	  	
		
	}
	
}
