/*
 * Created on 2004-8-9
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.icss.oa.phonebook.user;

import java.io.IOException;
import java.sql.Connection;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.icss.common.log.ConnLog;
import com.icss.j2ee.servlet.ServletBase;
import com.icss.j2ee.util.Globals;
import com.icss.oa.address.handler.AddressHelp;
import com.icss.oa.address.vo.SelectOrgpersonVO;
import com.icss.oa.phonebook.handler.PhoneHandler;
import com.icss.resourceone.common.logininfo.UserInfo;
import com.icss.resourceone.sdk.framework.Context;

/**
 * @author firecoral
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class SearchPhoneServlet extends ServletBase{

	/* (non-Javadoc)
	 * @see com.icss.j2ee.servlet.ServletBase#performTask(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	protected void performTask(HttpServletRequest request, HttpServletResponse response) 
	    throws ServletException, IOException {
	    	
	    	Connection conn = null;
	    	List searchList = new ArrayList();
	    	List sqlList = new ArrayList();
	    	
	    	String sqlid = request.getParameter("sqlid");
			String username1 = request.getParameter("username1");
			String userJob = request.getParameter("userJob");
	    	String username2 = request.getParameter("username2");
	    	String phonetype =request.getParameter("phonetype");
	    	String phonenum = request.getParameter("phonenum");
	    	String functiontype = request.getParameter("functiontype");
	    	
	    	String sqltitle = request.getParameter("sqltitle");
	    	String savetosql = request.getParameter("savetosql");
	    	
			HttpSession session = request.getSession();
			List ownerList = (List) session.getAttribute("owner");
			//清除Session
			if(ownerList!=null){
				session.removeAttribute("owner");
			}
	    	
			try {
				Context ctx = null;
				ctx = Context.getInstance();
				UserInfo user = ctx.getCurrentLoginInfo();
				
				conn = this.getConnection(Globals.DATASOURCEJNDI);
				ConnLog.open("SearchPhoneServlet");
				PhoneHandler pHandler = new PhoneHandler(conn);
				AddressHelp addressHelp = new AddressHelp(conn);
				
				String typename = "0";
				List personUUidList = new ArrayList();
				if("1".equals(functiontype)){
					typename = "";
					 
					if(ownerList!=null){
						ownerList = addressHelp.getperson(ownerList,request,"owner");
						Iterator OItr = ownerList.iterator();
						while(OItr.hasNext()){
							SelectOrgpersonVO ownerVO = (SelectOrgpersonVO)OItr.next();
							personUUidList.add(ownerVO.getUseruuid());
						}
					}
				}else{
					typename = "值班电话";
				}
				
				String orgUUid = request.getParameter("orgUUid");
				String personUUid = user.getPersonUuid();
				String username = "";
				if("".equals(username1)){
					username = username2;
				}else{
					username = username1;
				}
				searchList = pHandler.GetSearchList(personUUid,sqlid,username,userJob,functiontype,personUUidList,phonetype,phonenum,typename,sqltitle,savetosql);
				
				//得到当前用户常用的SQL
				String sqlOrguuid = user.getPrimaryOrguuid();
				sqlList = pHandler.GetSqlList(user.getUserID(),sqlOrguuid);
				
				request.setAttribute("phoneList",searchList);
				request.setAttribute("sqlList",sqlList);
				request.setAttribute("orgUUid",orgUUid);
				
				//得到组织的联系地址
				String contact = pHandler.GetOrgContact(orgUUid);
				request.setAttribute("contact",contact);				
				
				this.forward(request,response,"/phonebook/searchPhone.jsp");
				
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				if (conn != null)
					try {
						conn.close();
						ConnLog.close("SearchPhoneServlet");
					} catch (Exception e2) {
						e2.printStackTrace();
					}
			}
		
	}

}
