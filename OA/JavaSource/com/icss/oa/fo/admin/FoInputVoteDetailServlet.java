/*******************************************************************************
* Copyright (c) ICSS Corporation and others.
* All rights reserved.
* Created on 2006-10-10 16:21:47.
*
* @author wangsu
*******************************************************************************/

package com.icss.oa.fo.admin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

public class FoInputVoteDetailServlet extends ServletBase{
    protected void performTask(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException {
    	Connection conn = null;
       
		try{
			conn = getConnection(com.icss.j2ee.util.Globals.DATASOURCEJNDI); 
			
			String planid = request.getParameter("planid");
			String invoteid = request.getParameter("invoteid");
			
			FoHandler handler = new FoHandler(conn);
			

			//取得文章列表信息
			List list=new ArrayList();
			//list=handler.getInputValueList(invoteid);
			list=handler.getVoteArticlelist(planid);
			System.out.println("取得投票文章列表 "+list.size());
			Map map=new HashMap();
			map=handler.getInputValueMap(new Integer(invoteid));
			request.setAttribute("map",map);
			System.out.println("取得投票值Map "+map.size());
			request.setAttribute("list",list);
			
			String dist = "/fo/Foinputvote_detail.jsp?planid="+planid+"&invoteid="+invoteid	;
			
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