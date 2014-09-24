/*
 * Created on 2007-6-22
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.icss.oa.multivote.admin;

import java.io.IOException;

import java.sql.Connection;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.icss.j2ee.services.DBConnectionLocator;
import com.icss.j2ee.servlet.ServletBase;
import com.icss.j2ee.util.Globals;
import com.icss.oa.multivote.handler.VoteHandler;



/**
 * @author liupei
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class MVoteUserManagerListServlet extends ServletBase{

	/* (non-Javadoc)
	 * @see com.icss.j2ee.servlet.ServletBase#performTask(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	protected void performTask(HttpServletRequest request, HttpServletResponse response) 
	    throws ServletException, IOException {
		
		Connection conn = null;
		List list = null;
		
		request.setCharacterEncoding("GBK");
		String username=request.getParameter("username")==null?"":request.getParameter("username");
		String planid=request.getParameter("planid")==null?"":request.getParameter("planid");
		//System.out.println("进入FoUserManagerListServlet********planid="+planid) ;
		try {
			conn =
				DBConnectionLocator.getInstance().getConnection(
					Globals.DATASOURCEJNDI);
			VoteHandler handler = new VoteHandler(conn);
			String sql="";
			if(!"".equals(username)){
				sql=" name like '"+username+"' and plan_planid="+planid;
			}else{
				sql=" plan_planid="+planid;
			}
			
			//得到显示列表111111
			list=handler.getUserManagerList(sql);
			request.setAttribute("list",list);
			
			//System.out.println("servlet已经取得list");
			//System.out.println("进入用户列表页面list.size()*****"+list.size());	
			
			forward(request, response, "/multivote/mVoteUserList.jsp?planid="+planid);

			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (conn != null)
					conn.close();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		
	}
	

}
