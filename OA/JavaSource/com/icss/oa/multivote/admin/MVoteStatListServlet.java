/*******************************************************************************
* Copyright (c) ICSS Corporation and others.
* All rights reserved.
* Created on 2006-10-10 16:21:47.
*
* @author wangsu
*******************************************************************************/

package com.icss.oa.multivote.admin;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.j2ee.servlet.ServletBase;
import com.icss.oa.multivote.handler.VoteHandler;
import com.icss.oa.multivote.vo.VotePlanVO;
import com.icss.oa.multivote.vo.VoteTableVO;



public class MVoteStatListServlet extends ServletBase{
    protected void performTask(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException {
    	Connection conn = null;
      
		try{
			conn = getConnection(com.icss.j2ee.util.Globals.DATASOURCEJNDI); 
			String planid = request.getParameter("planid")==null?"-1":request.getParameter("planid");
			VoteHandler handler = new VoteHandler(conn);
//			System.out.println("进入统计页面");
//			取得计划参数信息
			VotePlanVO planvo=new VotePlanVO();
				
			planvo=handler.getPlanVo(planid);
			//取得计划中的表样信息
			VoteTableVO tablevo=new VoteTableVO();
			List tablelist=new ArrayList();
			tablelist=handler.getVoteTableList(planid);
			Iterator tableitr=tablelist.iterator();		
			//取得指标列表
			if(!tableitr.hasNext()){
				
				//forward(request,response,dist);
			}else{
				tablevo=(VoteTableVO) tableitr.next();
				
//				System.out.println("+MVoteArticleServlet+++++++++++++ tablevo"+tablevo.getTableId());
//				取得文章列表信息
				List list=new ArrayList();
				list=handler.getVoteArticlelist(tablevo.getTableId()==null?new Integer(-1):tablevo.getTableId());
				//取得投票指标列表
				List itemlist=new ArrayList();
				itemlist=handler.getVoteItemList(tablevo.getTableId()==null?new Integer(-1):tablevo.getTableId());
//				System.out.println("取得文章列表  list.size()"+list.size());
				//统计
				Map statnummap=new HashMap();
				statnummap=handler.statVotenum(planid,planvo.getPlanSeason());
//				System.out.println("++++++++++statnummap+++++"+statnummap.size());
				
				request.setAttribute("list",list);
				request.setAttribute("itemlist",itemlist);
				request.setAttribute("planvo",planvo);
				request.setAttribute("planid",planid);
				request.setAttribute("statnummap",statnummap);
				String dist = "/multivote/mVotestat_list.jsp"	;
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