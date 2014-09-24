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

import com.icss.oa.fo.handler.FoHandler;
import com.icss.oa.fo.vo.DEPARTMENT_CODE;
import com.icss.oa.fo.vo.FoArticalVO;
import com.icss.oa.fo.vo.FoPlanVO;


public class FoArtDeptStatServlet extends ServletBase{
    protected void performTask(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException {
    	Connection conn = null;
      
		try{
			conn = getConnection(com.icss.j2ee.util.Globals.DATASOURCEJNDI); 
			String planid = request.getParameter("planid")==null?"-1":request.getParameter("planid");
			String artid = request.getParameter("artid")==null?"-1":request.getParameter("artid");
			FoHandler handler = new FoHandler(conn);
//			System.out.println("进入统计页面");
			//取得此文章的所有投票数量
			Integer artvotenum=new Integer(0);
			int votenum=handler.getArtVoteNum(new Integer(planid),artid);
			int invotenumstat=handler.getInputvalueStat(new Integer(planid),artid);
			artvotenum=new Integer(votenum+invotenumstat);
			System.out.println("++++++++++++++++artvotenum"+artvotenum);
			//取得部门投票数量
			Map votemap = new HashMap();
			votemap=handler.getArtDeptVotenum(planid,artid);
			
			//取得部门信息
			FoPlanVO planvo=handler.getPlanVo(planid);
			Map map = new HashMap();
			for(int i=0;i<DEPARTMENT_CODE.DEP_NUM;i++){
				 List list = new ArrayList();
				 list = handler.findOrgByParentname(DEPARTMENT_CODE.GET_DEP_NAME(i));
				 map.put(new Integer(i),list);
			}
			request.setAttribute("votemap",votemap);
			request.setAttribute("map",map);
			request.setAttribute("artvotenum",artvotenum);
			
			this.forward(request, response, "/fo/foartdept_stat.jsp?planid="+planid);
			
				
			
			
			
			
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