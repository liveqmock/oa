/*******************************************************************************
* Copyright (c) ICSS Corporation and others.
* All rights reserved.
* Created on 2006-10-10 16:21:47.
*
* @author wangsu
*******************************************************************************/

package com.icss.oa.fo.admin;

import java.util.List;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.j2ee.servlet.ServletBase;
import com.icss.j2ee.log.Log;
import com.icss.j2ee.log.LogFactory;
import com.icss.oa.fo.handler.FoHandler;
import com.icss.oa.fo.vo.FoPlanVO;
import com.icss.oa.fo.vo.FoVotepersonVO;

public class FoVoteArticleServlet extends ServletBase{
    protected void performTask(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException {
    	Connection conn = null;
        Log log = LogFactory.getFactory().getInstance("system");
		try{
			conn = getConnection(com.icss.j2ee.util.Globals.DATASOURCEJNDI); 
			String personid = request.getParameter("personid");
			String personname = request.getParameter("personname");

			String planid = request.getParameter("planid");
			String userhasvote = request.getParameter("userhasvote")==null?"-1":request.getParameter("userhasvote");
			
			String Msginfo = "";
			FoHandler handler = new FoHandler(conn);
			System.out.println("+FoVoteArticleServlet+++++++++++++userhasvote  ="+userhasvote);
			if(!"1".equals(userhasvote)){//未投过票
//				取得计划参数信息
				FoPlanVO planvo=new FoPlanVO();
					
				planvo=handler.getPlanVo(planid);
					
				
				//取得文章列表信息
				List list=new ArrayList();
				list=handler.getVoteArticlelist(planid);
				//System.out.println("取得文章列表  list.size()"+list.size());
				request.setAttribute("list",list);
				request.setAttribute("planvo",planvo);
				String dist = "/fo/FovoteAritclelist.jsp?personid="+personid
					+"&personname="+URLEncoder.encode(personname)
					+"&planid="+planid

					;
				System.out.println("dist"+dist);	
				forward(request,response,dist);
				
			}else{//已投过票
				
				//System.out.println("+++++++++++==FoVoteArticleServlet 已投过票");
				//重新定向到已投票页面返回用户列表
				String dist = "/fo/votecomplete.jsp?personid="+personid
				+"&personname="+personname
				+"&planid="+planid
				
				+"&errormsg=您已完成投票，谢谢您的参与"
				;
				forward(request,response,dist);
				
			}
			
			
			
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