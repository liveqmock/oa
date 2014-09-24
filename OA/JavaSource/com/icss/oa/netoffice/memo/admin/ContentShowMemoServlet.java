/*
 * Created on 2004-4-10
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package com.icss.oa.netoffice.memo.admin;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.icss.j2ee.servlet.ServletBase;
import com.icss.j2ee.util.Globals;
import com.icss.oa.netoffice.memo.handler.*;
import com.icss.oa.netoffice.memo.vo.*;
/**
 * @author Administrator
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public class ContentShowMemoServlet extends ServletBase{
	protected void performTask(
			HttpServletRequest request,
			HttpServletResponse response)
			throws ServletException, IOException{
		    Connection conn = null;
		   String id=request.getParameter("mid");
		  
		   int m_id = Integer.parseInt(id); 
		   Integer id_memo=new Integer(m_id);
		  
		   OfficeMemoVO mvo=null;
		  
		 
		   try {
			conn = getConnection(Globals.DATASOURCEJNDI);

			MemoHandler mHandler = new MemoHandler(conn);
			mvo=mHandler.getById(id_memo);
			
			request.setAttribute("vo",mvo);
			String choice=request.getParameter("doChoice");

			if(choice.equals("content")){
				this.forward(request, response, "/netoffice/memo/content_memo.jsp");
			}
			else{
				this.forward(request, response, "/netoffice/memo/content_update.jsp");	
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
			//System.out.println(e.toString());
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
