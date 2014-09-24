/*******************************************************************************
* Copyright (c) ICSS Corporation and others.
* All rights reserved.
* Created on 2006-10-10 16:21:47.
*
* @author wangsu
*******************************************************************************/

package com.icss.oa.fo.admin;

import java.util.List;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.j2ee.servlet.ServletBase;

import com.icss.oa.fo.handler.FoHandler;
import com.icss.oa.fo.vo.FoArticalVO;
import com.icss.oa.fo.vo.FoPlanVO;


public class FoStatListServlet extends ServletBase{
    protected void performTask(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException {
    	Connection conn = null;
      
		try{
			conn = getConnection(com.icss.j2ee.util.Globals.DATASOURCEJNDI); 
			String planid = request.getParameter("planid")==null?"-1":request.getParameter("planid");
			FoHandler handler = new FoHandler(conn);
//			System.out.println("进入统计页面");
			//取得文章列表信息
			List list=new ArrayList();
			list=handler.getVoteArticlelist(planid);
			
			//取得投票人数
			FoPlanVO planvo=handler.getPlanVo(planid);
			
			String	votepersonnum=planvo.getPlanPersonnum().toString();
			request.setAttribute("list",list);
			request.setAttribute("votepersonnum",votepersonnum);
			request.setAttribute("planid",planid);
			String dist = "/fo/fostat_list.jsp"	;
			forward(request,response,dist);
				
			
			
			
			
		}catch(Exception e){
			handleError(e) ;
		}finally{
			try {
				if (conn != null) {
					try {
						conn.close();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
			} catch (Exception e) {
				handleError(e);
			}
        }
    }
}