/*******************************************************************************
* Copyright (c) ICSS Corporation and others.
* All rights reserved.
* Created on 2006-10-11 10:59:44.
*
* @author wangsu
*******************************************************************************/

package com.icss.oa.fo.admin;

import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.j2ee.servlet.ServletBase;
import com.icss.j2ee.log.Log;
import com.icss.j2ee.log.LogFactory;
import com.icss.oa.fo.handler.FoHandler;
import com.icss.oa.fo.vo.FoVotepersonVO;

public class FoModifyPwdServlet extends ServletBase{
    protected void performTask(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException {
    	Connection conn = null;
       
		try{
			conn = getConnection(com.icss.j2ee.util.Globals.DATASOURCEJNDI); 
			String planid = request.getParameter("planid")==null?"-1":(String) request.getParameter("planid");
			String personid = request.getParameter("personid")==null?"-1":(String) request.getParameter("personid");
			String firstflag = request.getParameter("firstflag")==null?"-1":(String) request.getParameter("firstflag");
			String personname = request.getParameter("personname")==null?"-1":(String) request.getParameter("personname");

			String orgCode = request.getParameter("orgCode")==null?"-1":(String) request.getParameter("orgCode");
			String orgName = request.getParameter("orgName")==null?"-1":(String) request.getParameter("orgName");
			String password = (String)request.getParameter("password");
			String oldpassword = (String)request.getParameter("oldpassword");
			
			
//			System.err.println("___________________hrid="+hrid);
			
			FoVotepersonVO vo = new FoVotepersonVO();
			FoHandler handler = new FoHandler(conn);
			
			vo = handler.findByPersonid(personid);
			if(vo.getPassword().equals(oldpassword)){
				//正确登陆
				//设定新密码
				handler.updatePersonPassword(personid,password,"1");
				
				
				
				request.setAttribute("planid",planid);
				request.setAttribute("personid",personid);
				request.setAttribute("orgCode",orgCode);
				request.setAttribute("personname",personname);
				request.setAttribute("orgName",orgName);
				request.setAttribute("firstflag","1");
				String dist = "/fo/FoVoteLoginIndex.jsp?type=2"
					/*?planid="+planid
				+"&personid="+personid
				+"&firstflag=1"
				+"&personname="+URLEncoder.encode(personname)
				+"&orgCode="+orgCode
				+"&orgName="+URLEncoder.encode(orgName)
*/
				;
				
				forward(request,response,dist);
				
			}else{
				
				request.setAttribute("Msginfo","您输入的初始密码不正确，请重新输入！");
				request.setAttribute("planid",planid);
				request.setAttribute("personid",personid);
				request.setAttribute("orgCode",orgCode);
				request.setAttribute("personname",personname);
				request.setAttribute("orgName",orgName);
				request.setAttribute("firstflag",firstflag);
				
				String dist = "/fo/FoModifyPassWord.jsp?type=2" 
					/*+"?planid="+planid
				+"&personid="+personid
				+"&firstflag="+firstflag
				+"&personname="+URLEncoder.encode(personname)
				+"&orgCode="+orgCode
				+"&orgName="+URLEncoder.encode(orgName)
*/
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