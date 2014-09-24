/*
 * Created on 2004-12-4
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.icss.oa.votebbs.admin;

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
import com.icss.oa.votebbs.handler.BbsVoteHandler;


/**
 * @author firecoral
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ArticleManagerListServlet extends ServletBase{

	/* (non-Javadoc)
	 * @see com.icss.j2ee.servlet.ServletBase#performTask(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	protected void performTask(HttpServletRequest request, HttpServletResponse response) 
	    throws ServletException, IOException {
		
		Connection conn = null;
		List list = null;
		
		request.setCharacterEncoding("GBK");
		String uuid=request.getParameter("uuid")==null?"-1":request.getParameter("uuid").toString();//���uuidΪ�գ����ֵΪ-1������ȡuuid��ֵ
		String userid=request.getParameter("userid")==null?"-1":request.getParameter("userid").toString();
		String userenname=request.getParameter("userenname")==null?"-1":request.getParameter("userenname").toString();//Ӣ����
		String usercnname=request.getParameter("usercnname")==null?"-1":request.getParameter("usercnname").toString();//������
			
		try {
			conn =
				DBConnectionLocator.getInstance().getConnection(
					Globals.DATASOURCEJNDI);
			BbsVoteHandler handler = new BbsVoteHandler(conn);
			//��session
			HttpSession session = request.getSession();
			System.out.println("����ͶƱBBS�Ĺ���ҳ��uuid="+uuid+",userid="+userid+",usercnname="+usercnname+"session()="+session.getAttribute("votebbsusercnname"));
			
			if(session.getAttribute("votebbsuuid")==null||session.getAttribute("votebbsuuid")==""){
				if(uuid==null||"".equals(uuid)){
					forward(request, response, "http://10.102.1.230:9080/oabase/servlet/OnePointSignOnForVotebbsServlet");
					
				}
				System.out.println("����ͶƱBBS�Ĺ���ҳ�桪������session");
				session.setAttribute("votebbsuuid", uuid);
				session.setAttribute("votebbsuserid", userid);
				session.setAttribute("votebbsuserenname", userenname);
				session.setAttribute("votebbsusercnname", usercnname);
				
			}
			
			
			//�õ���ʾ�б�
			list=handler.getArticalManagerList();
			request.setAttribute("list",list);
			System.out.println("����ͶƱBBS�Ĺ���ҳ��list.size()"+list.size());	
			
			//response.sendRedirect(request.getContextPath()+"/votebbs/artical_manager.jsp");
			forward(request, response, "/votebbs/artical_manager.jsp");
//			System.out.println("��ת"+"/votebbs/artical_manager.jsp");	
			
//			
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
