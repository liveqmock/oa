/*******************************************************************************
* Copyright (c) ICSS Corporation and others.
* All rights reserved.
* Created on 2006-11-13 16:36:18.
*
* @author wangsu
*******************************************************************************/

package com.icss.oa.votebbs.admin;


import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.j2ee.servlet.ServletBase;

import com.icss.resourceone.sdk.framework.Context;
import com.icss.resourceone.common.logininfo.UserInfo;
import java.net.URLEncoder;


public class OnePointSignOnForVotebbsServlet extends ServletBase{
    protected void performTask(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException {
		System.out.println("the new onepointsignonservlet for Votebbs system in");
		String dist = request.getParameter("dist") == null ? "error.jsp":request.getParameter("dist").toString() ;
		Connection conn = null;
		
		try
		{
			Context ctx = Context.getInstance();
			UserInfo user = ctx.getCurrentLoginInfo();
			String uuid = user.getPersonUuid();
			String	userid=user.getUserID();
			String	userenname=user.getEnName();
			String 	usercnname=user.getCnName();

			
			System.out.println("OnePointSignOnForVotebbsServlet  dist=http://10.102.1.230:9080/examine/servlet/ArticleManagerListServlet");
//			System.out.println("usercnname="+usercnname+" and"+URLEncoder.encode(usercnname));
			response.sendRedirect("http://10.102.1.230:9080/examine/servlet/ArticleManagerListServlet?uuid="+uuid+"&userid="+userid+"&userenname="+userenname+"&usercnname="+URLEncoder.encode(usercnname));
//			response.sendRedirect("http://10.90.20.136:9080/oabase/servlet/ArticleManagerListServlet?uuid="+uuid+"&userid="+userid+"&userenname="+userenname+"&usercnname="+URLEncoder.encode(usercnname));
		
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