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

import com.icss.oa.fo.handler.FoHandler;
import com.icss.oa.fo.vo.FoPlanVO;
import com.icss.oa.fo.vo.FoVotepersonVO;

public class FoTopMsgServlet extends ServletBase{
    protected void performTask(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException {
    	Connection conn = null;
        
		try{
			conn = getConnection(com.icss.j2ee.util.Globals.DATASOURCEJNDI); 
			String personid = request.getParameter("personid");
			String personname = request.getParameter("personname");

			String planid = request.getParameter("planid");

			
			
			FoHandler handler = new FoHandler(conn);
			
			
			//取得计划参数信息
			FoPlanVO planvo=new FoPlanVO();
			planvo=handler.getPlanVo(planid);
			
			request.setAttribute("planvo",planvo);
			request.setAttribute("personid",personid);
			request.setAttribute("personname",personname);
			request.setAttribute("planid",planid);
			String dist = "/fo/FoTitlemsglist.jsp"
				//personid="+personid
				//+"&personname="+URLEncoder.encode(personname)
			//	+"&planid="+planid
				;
				
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