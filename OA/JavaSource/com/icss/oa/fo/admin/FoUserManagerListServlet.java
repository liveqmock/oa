/*
 * Created on 2007-6-22
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.icss.oa.fo.admin;

import java.io.IOException;

import java.sql.Connection;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.icss.j2ee.services.DBConnectionLocator;
import com.icss.j2ee.servlet.ServletBase;
import com.icss.j2ee.util.Globals;
import com.icss.oa.fo.handler.FoHandler;
import com.icss.oa.fo.handler.FoLiuHandler;
import com.icss.oa.votebbs.handler.BbsVoteHandler;


/**
 * @author liupei
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class FoUserManagerListServlet extends ServletBase{

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
		System.out.println("����FoUserManagerListServlet********planid="+planid) ;
		try {
			conn =
				DBConnectionLocator.getInstance().getConnection(
					Globals.DATASOURCEJNDI);
			FoLiuHandler handler = new FoLiuHandler(conn);
			String sql="";
			if(!"".equals(username)){
				sql=" name like '"+username+"' and plan_planid="+planid;
			}else{
				sql=" plan_planid="+planid;
			}
			
			//�õ���ʾ�б�111111
			list=handler.getUserManagerList(sql);
			request.setAttribute("list",list);
			
			System.out.println("servlet�Ѿ�ȡ��list");
			System.out.println("�����û��б�ҳ��list.size()*****"+list.size());	
			
			forward(request, response, "/fo/FoVoteUserList.jsp?planid="+planid);

			
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
