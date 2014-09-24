/*******************************************************************************
* Copyright (c) ICSS Corporation and others.
* All rights reserved.
* Created on 2006-10-10 16:21:47.
*
* @author wangsu
*******************************************************************************/

package com.icss.oa.multivote.admin;

import java.util.List;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.j2ee.servlet.ServletBase;
import com.icss.oa.multivote.handler.VoteHandler;
import com.icss.oa.multivote.vo.VotePlanVO;


public class MVoteTopMsgServlet extends ServletBase{
    protected void performTask(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException {
    	Connection conn = null;
        
		try{
			conn = getConnection(com.icss.j2ee.util.Globals.DATASOURCEJNDI); 
			String personid = request.getParameter("personid");
			String personname = request.getParameter("personname");

			String planid = request.getParameter("planid");

			
			
			VoteHandler handler = new VoteHandler(conn);
			
			
			//ȡ�üƻ�������Ϣ
			VotePlanVO planvo=new VotePlanVO();
			planvo=handler.getPlanVo(planid);
			
			request.setAttribute("planvo",planvo);
			request.setAttribute("personid",personid);
			request.setAttribute("personname",personname);
			request.setAttribute("planid",planid);
			String dist = "/multivote/mVoteTitlemsglist.jsp"
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