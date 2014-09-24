/*
 * Created on 2004-12-7
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.icss.oa.filetransfer.cardmodule;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.common.log.ConnLog;
import com.icss.j2ee.services.ServiceLocatorException;
import com.icss.j2ee.servlet.ServletBase;
import com.icss.j2ee.util.Globals;
import com.icss.oa.filetransfer.handler.CardHandler;

/**
 * @author firecoral
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class CardManageServlet extends ServletBase{

	/* (non-Javadoc)
	 * @see com.icss.j2ee.servlet.ServletBase#performTask(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	protected void performTask(HttpServletRequest request, HttpServletResponse response) 
	    throws ServletException, IOException {
		
		Connection conn = null;
		List cardlist = new ArrayList();
		try {
			conn = this.getConnection(Globals.DATASOURCEJNDI);
			ConnLog.open("CardManageServlet");
			CardHandler chandler = new CardHandler(conn);
			cardlist = chandler.getAllCard();
			
			request.setAttribute("cardlist",cardlist);
			
			this.forward(request,response,"/filetransfer/card/showAllCard.jsp");
			
		} catch (ServiceLocatorException e) {			
			
			e.printStackTrace();
			
		} finally {
			
			if(conn!=null){
				try {
					conn.close();
					ConnLog.close("CardManageServlet");
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
			
		}
		
		
	}


}


