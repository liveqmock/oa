/*
 * Created on 2004-12-4
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.icss.oa.multivote.admin;

import java.io.IOException;
import java.sql.Connection;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.j2ee.services.DBConnectionLocator;
import com.icss.j2ee.servlet.ServletBase;
import com.icss.j2ee.util.Globals;
import com.icss.oa.multivote.handler.VoteHandler;
import com.icss.oa.multivote.vo.DEPARTMENT_CODE;



/**
 * @author firecoral
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class MVoteDeptVoteStatListServlet extends ServletBase{

	/* (non-Javadoc)
	 * @see com.icss.j2ee.servlet.ServletBase#performTask(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	protected void performTask(HttpServletRequest request, HttpServletResponse response) 
	    throws ServletException, IOException {
//		System.out.println("+++++++++++ArticleOptionListServlet++++++++");
		Connection conn = null;
		
		Integer planid=request.getParameter("planid")==null?new Integer(-1):Integer.valueOf(request.getParameter("planid"));
		try {
			conn =
				DBConnectionLocator.getInstance().getConnection(
					Globals.DATASOURCEJNDI);
			VoteHandler handler = new VoteHandler(conn);
			//得到部门人员数量的map
			Map personmap=handler.getDeptVotePersonNum(planid);
//			System.out.println("++++++++++++++++++++======0");
			Map hasvotemap=handler.getDeptHasVotePersonNum(planid);
//			System.out.println("++++++++++++++++++++======1");
			Map map = new HashMap();
			for(int i=0;i<DEPARTMENT_CODE.DEP_NUM;i++){
				//System.out.println("++++++++++++++++++++i="+i+"=====DEPARTMENT_CODE.DEP_NUM"+DEPARTMENT_CODE.DEP_NUM);
				 List list = new ArrayList();
				 list = handler.findOrgByParentname(DEPARTMENT_CODE.GET_DEP_NAME(i));
				 map.put(new Integer(i),list);
			}
//			System.out.println("++++++++++++++++++++======2");
			request.setAttribute("map",map);
			request.setAttribute("personmap",personmap);
			request.setAttribute("hasvotemap",hasvotemap);
			//request.setAttribute("planid",planid);
			this.forward(request, response, "/multivote/mVotedeptvote_list.jsp?planid="+planid);
			
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
