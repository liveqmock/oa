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

public class FoVoteLoginServlet extends ServletBase{
    protected void performTask(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException {
    	Connection conn = null;
        Log log = LogFactory.getFactory().getInstance("system");
		try{
			conn = getConnection(com.icss.j2ee.util.Globals.DATASOURCEJNDI); 
			String personid = request.getParameter("personid");
			String personname = request.getParameter("personname");
			String firstflag = request.getParameter("firstflag");
			String planid = request.getParameter("planid");
			String orgCode = request.getParameter("orgCode");
			String orgName = request.getParameter("orgName");
			String password = (String)request.getParameter("password");
			String Msginfo = "";
			FoVotepersonVO vo = new FoVotepersonVO();
			FoHandler handler = new FoHandler(conn);
			
			vo = handler.findByPersonid(personid);
			String	userhasvote=vo.getFlag();
			
			if(vo.getPassword().equals(password)){//��ȷ��½
					
				
				request.setAttribute("planid",planid);
				request.setAttribute("personid",personid);
				request.setAttribute("orgCode",orgCode);
				request.setAttribute("personname",personname);
				request.setAttribute("orgName",orgName);
				request.setAttribute("userhasvote",userhasvote);
				String dist = "/fo/Focommonuserframe.jsp?"
					//+"personid="+personid
				//+"&personname="+URLEncoder.encode(personname)
				//+"&planid="+planid
				//+"&orgCode="+orgCode
				//+"&orgName="+URLEncoder.encode(orgName)
				//+"&firstflag="+firstflag
				//+"&userhasvote="+userhasvote
				;
			//	response.sendRedirect(request.getContextPath()+dist);
				System.out.println("+++++++++++++++++++++��ȷ��½personname="+personname+"dist="+dist);	
			forward(request,response,dist);
			
			
			}else{//���벻��ȷ
				Msginfo = "����������벻��ȷ�����������룡";
				request.setAttribute("Msginfo",Msginfo);
				request.setAttribute("planid",planid);
				request.setAttribute("personid",personid);
				request.setAttribute("orgCode",orgCode);
				request.setAttribute("personname",personname);
				request.setAttribute("orgName",orgName);
				request.setAttribute("firstflag",firstflag);
				
				String dist = "/fo/FoVoteLoginIndex.jsp?type=2"
					//?personid="+personid
					//+"&personname="+URLEncoder.encode(personname)
					//+"&planid="+planid
					//+"&orgCode="+orgCode
					//+"&orgName="+URLEncoder.encode(orgName)
					//+"&firstflag="+firstflag
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