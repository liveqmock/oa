/*******************************************************************************
* Copyright (c) ICSS Corporation and others.
* All rights reserved.
* Created on 2006-10-09 11:24:52.
*
* @author wangsu
*******************************************************************************/

package com.icss.oa.fo.admin;

import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;


import com.icss.j2ee.servlet.ServletBase;
import com.icss.j2ee.util.Globals;
import com.icss.oa.fo.handler.FoHandler;
import com.icss.oa.fo.vo.DEPARTMENT_CODE;


public class FoDepartmentListServlet extends ServletBase{
    protected void performTask(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException {
    	Connection conn = null;
        //System.out.println("_++++++++++++++++++++++++++in Fodepartmentservlet");
		try{
			
			
			conn = getConnection(Globals.DATASOURCEJNDI); 
			FoHandler handler=new FoHandler(conn);
			String	planid=request.getParameter("planid")==null?"-1":request.getParameter("planid").toString();
			
			Map map = new HashMap();
			for(int i=0;i<DEPARTMENT_CODE.DEP_NUM;i++){
				//System.out.println("µØÇøi"+i+"+"+DEPARTMENT_CODE.GET_DEP_NAME(i));
				 List list = new ArrayList();
				 list = handler.findOrgByParentname(DEPARTMENT_CODE.GET_DEP_NAME(i));
				 map.put(new Integer(i),list);
			}
			request.setAttribute("map",map);
			request.setAttribute("planid",planid);

			String dist = "/fo/FoDepartmentList.jsp";
			super.forward(request,response,dist);
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