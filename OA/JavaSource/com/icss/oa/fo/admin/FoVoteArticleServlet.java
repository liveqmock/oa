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
			if(!"1".equals(userhasvote)){//δͶ��Ʊ
//				ȡ�üƻ�������Ϣ
				FoPlanVO planvo=new FoPlanVO();
					
				planvo=handler.getPlanVo(planid);
					
				
				//ȡ�������б���Ϣ
				List list=new ArrayList();
				list=handler.getVoteArticlelist(planid);
				//System.out.println("ȡ�������б�  list.size()"+list.size());
				request.setAttribute("list",list);
				request.setAttribute("planvo",planvo);
				String dist = "/fo/FovoteAritclelist.jsp?personid="+personid
					+"&personname="+URLEncoder.encode(personname)
					+"&planid="+planid

					;
				System.out.println("dist"+dist);	
				forward(request,response,dist);
				
			}else{//��Ͷ��Ʊ
				
				//System.out.println("+++++++++++==FoVoteArticleServlet ��Ͷ��Ʊ");
				//���¶�����ͶƱҳ�淵���û��б�
				String dist = "/fo/votecomplete.jsp?personid="+personid
				+"&personname="+personname
				+"&planid="+planid
				
				+"&errormsg=�������ͶƱ��лл���Ĳ���"
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