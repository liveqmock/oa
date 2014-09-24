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
import com.icss.j2ee.log.Log;
import com.icss.j2ee.log.LogFactory;
import com.icss.oa.multivote.handler.VoteHandler;
import com.icss.oa.multivote.vo.VotePersonVO;


public class MVoteLoginServlet extends ServletBase{
    protected void performTask(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException {
    	Connection conn = null;
       
		try{
			conn = getConnection(com.icss.j2ee.util.Globals.DATASOURCEJNDI); 
			String personid = request.getParameter("personid");
			String personname = request.getParameter("personname");
			String firstflag = request.getParameter("firstflag");
			String planid = request.getParameter("planid");
			String orgCode = request.getParameter("orgCode");
			String orgName = request.getParameter("orgName");
			String password = request.getParameter("password")==null?"": (String)request.getParameter("password");
			String Msginfo = "";
			VotePersonVO vo = new VotePersonVO();
			VoteHandler handler = new VoteHandler(conn);
			
			vo = handler.findByPersonid(personid);
			String	userhasvote=vo.getFlag();
			//System.out.println("+++++++++++++++++++++登陆personname="+userhasvote);	
			if(password.equals(vo.getPassword())){//正确登陆
					
				//System.out.println("+++++++++++++++++++++登陆personname=密码正确");	
				request.setAttribute("planid",planid);
				request.setAttribute("personid",personid);
				request.setAttribute("orgCode",orgCode);
				request.setAttribute("personname",personname);
				request.setAttribute("orgName",orgName);
				request.setAttribute("userhasvote",userhasvote);
				String dist = "/multivote/mVotecommonuserframe.jsp?"
					//+"personid="+personid
				//+"&personname="+URLEncoder.encode(personname)
				//+"&planid="+planid
				//+"&orgCode="+orgCode
				//+"&orgName="+URLEncoder.encode(orgName)
				//+"&firstflag="+firstflag
				//+"&userhasvote="+userhasvote
				;
			//	response.sendRedirect(request.getContextPath()+dist);
				//System.out.println("+++++++++++++++++++++正确登陆personname="+personname+"dist="+dist);	
				forward(request,response,dist);
			
			
			}else{//密码不正确
				Msginfo = "您输入的密码不正确，请重新输入！";
				request.setAttribute("Msginfo",Msginfo);
				request.setAttribute("planid",planid);
				request.setAttribute("personid",personid);
				request.setAttribute("orgCode",orgCode);
				request.setAttribute("personname",personname);
				request.setAttribute("orgName",orgName);
				request.setAttribute("firstflag",firstflag);
				
				String dist = "/multivote/mVoteLoginIndex.jsp?type=2"
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