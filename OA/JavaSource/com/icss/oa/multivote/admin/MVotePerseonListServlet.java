/*******************************************************************************
* Copyright (c) ICSS Corporation and others.
* All rights reserved.
* Created on 2006-10-10 14:07:03.
*
* @author wangsu
*******************************************************************************/

package com.icss.oa.multivote.admin;

import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

import com.icss.j2ee.servlet.ServletBase;
import com.icss.oa.multivote.handler.VoteHandler;




public class MVotePerseonListServlet extends ServletBase{
    protected void performTask(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException {
    	Connection conn = null;

		try{
			conn = getConnection(com.icss.j2ee.util.Globals.DATASOURCEJNDI); 
			String orgCode = request.getParameter("orgCode");
			String orgName = request.getParameter("orgName");
			String planid = request.getParameter("planid");

//			System.out.println("+++++++++++++VotePerseonListServlet++++++++planid"+planid);

            List personlist = new ArrayList();

            VoteHandler handler = new VoteHandler(conn);
            //if((orgCode.length()==5)||"0121310".equals(orgCode.toString())){
				//personlist = handler.findPersonByOrgCode(orgCode,planid);
				personlist = handler.findPersonByOrgName(orgName,planid);
           // }
       
			request.setAttribute("orgCode",orgCode);

			request.setAttribute("personlist",personlist);
			request.setAttribute("orgName",orgName);
			request.setAttribute("planid",planid);
            
			String dist = "/multivote/mVotePersonList.jsp";
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