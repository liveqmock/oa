/*******************************************************************************
* Copyright (c) ICSS Corporation and others.
* All rights reserved.
* Created on 2006-10-11 10:59:44.
*
* @author wangsu
*******************************************************************************/

package com.icss.oa.multivote.admin;


import java.sql.Connection;
import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.icss.j2ee.servlet.ServletBase;
import com.icss.oa.multivote.handler.VoteHandler;
import com.icss.oa.multivote.vo.VotePersonVO;


public class MVoteModifyPwdServlet extends ServletBase{
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
			String oldpassword = request.getParameter("oldpassword")==null?"": (String)request.getParameter("oldpassword");
			
			
//			System.err.println("___________________hrid="+hrid);
			
			VotePersonVO vo = new VotePersonVO();
			VoteHandler handler = new VoteHandler(conn);
			
			vo = handler.findByPersonid(personid);
			if(oldpassword.equals(vo.getPassword())){
				//��ȷ��½
				//�趨������
				handler.updatePersonPassword(personid,password,"1");
				
				
				
				request.setAttribute("planid",planid);
				request.setAttribute("personid",personid);
				request.setAttribute("orgCode",orgCode);
				request.setAttribute("personname",personname);
				request.setAttribute("orgName",orgName);
				request.setAttribute("firstflag","1");
				String dist = "/multivote/mVoteLoginIndex.jsp?type=2"
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
				
				request.setAttribute("Msginfo","������ĳ�ʼ���벻��ȷ�����������룡");
				request.setAttribute("planid",planid);
				request.setAttribute("personid",personid);
				request.setAttribute("orgCode",orgCode);
				request.setAttribute("personname",personname);
				request.setAttribute("orgName",orgName);
				request.setAttribute("firstflag",firstflag);
				
				String dist = "/multivote/mVoteModifyPassWord.jsp?type=2" 
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