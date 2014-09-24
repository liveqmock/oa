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
//			System.out.println("����ͳ��ҳ��");
//			ȡ�üƻ�������Ϣ
			VotePlanVO planvo=new VotePlanVO();
				
			planvo=handler.getPlanVo(planid);
			//ȡ�üƻ��еı�����Ϣ
			VoteTableVO tablevo=new VoteTableVO();
			List tablelist=new ArrayList();
			tablelist=handler.getVoteTableList(planid);
			Iterator tableitr=tablelist.iterator();		
			//ȡ��ָ���б�
			if(!tableitr.hasNext()){
				
				//forward(request,response,dist);
			}else{
				tablevo=(VoteTableVO) tableitr.next();
				
//				System.out.println("+MVoteArticleServlet+++++++++++++ tablevo"+tablevo.getTableId());
//				ȡ�������б���Ϣ
				List list=new ArrayList();
				list=handler.getVoteArticlelist(tablevo.getTableId()==null?new Integer(-1):tablevo.getTableId());
				//ȡ��ͶƱָ���б�
				List itemlist=new ArrayList();
				itemlist=handler.getVoteItemList(tablevo.getTableId()==null?new Integer(-1):tablevo.getTableId());
//				System.out.println("ȡ�������б�  list.size()"+list.size());
				//ͳ��
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