/*
 * Created on 2004-8-5
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.icss.oa.phonebook.admin;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import com.icss.common.log.ConnLog;
import com.icss.j2ee.servlet.ServletBase;
import com.icss.j2ee.util.Globals;
import com.icss.oa.address.handler.AddressHelp;
import com.icss.oa.address.vo.SelectOrgpersonVO;
import com.icss.oa.config.PhoneBookConfig;
import com.icss.oa.phonebook.handler.PhoneHandler;
import com.icss.oa.phonebook.vo.PhoneInfoSysPersonVO;
import com.icss.oa.phonebook.vo.PhoneInfoVO;
import com.icss.oa.phonebook.vo.PhoneSysNameVO;
import com.icss.resourceone.common.logininfo.UserInfo;
import com.icss.resourceone.sdk.framework.Context;
import com.icss.oa.phonebook.vo.SysOrgVO;
import com.icss.oa.phonebook.dao.SysOrgDAO;

/**
 * @author firecoral
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class ManageSearchPhoneServlet extends ServletBase{

	/* (non-Javadoc)
	 * @see com.icss.j2ee.servlet.ServletBase#performTask(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	protected void performTask(HttpServletRequest request, HttpServletResponse response) 
	    throws ServletException, IOException {
			Connection conn = null;
			String noteids="",username="",orguuid="",officeaddress="",officephone="",homephone="",mobilephone="",netphone="",faxphone="";
			//À´×ÔMyDelPhone
			if(request.getParameter("fromdelpage")!=null)
			{
			  if(request.getParameter("noteids")!=null)
           	    noteids=request.getParameter("noteids");
			}
			
			if(request.getParameter("username")!=null)
		    	  username = request.getParameter("username");
			
		    if(request.getParameter("orguuid")!=null)
		    	  orguuid=request.getParameter("orguuid");
		    
		    if(request.getParameter("officeaddress")!=null)
		    	officeaddress = request.getParameter("officeaddress");
		    
		    if(request.getParameter("officephone")!=null)
		    	officephone = request.getParameter("officephone");
		    
		    if(request.getParameter("homephone")!=null)
		    	homephone = request.getParameter("homephone");
		    
		    if(request.getParameter("mobilephone")!=null)
		    	mobilephone = request.getParameter("mobilephone");
		    
		    if(request.getParameter("netphone")!=null)
		    	netphone = request.getParameter("netphone");
		    
		    if(request.getParameter("faxphone")!=null)
		    	faxphone = request.getParameter("faxphone");

			String type="";
			HttpSession session = request.getSession();
			List ownerList = (List) session.getAttribute("owner");
			
			//Çå³ýSession
			if(ownerList!=null){
				   session.removeAttribute("owner");
			}
			try {
				   conn = this.getConnection(Globals.DATASOURCEJNDI);
		           PhoneHandler pHandler = new PhoneHandler(conn);
				   ConnLog.open("MySearchPhoneServlet");
				   Context ctx = null;
			       ctx = Context.getInstance();
			       UserInfo user = ctx.getCurrentLoginInfo();
			       String cuorguuid=user.getPrimaryOrguuid();
			       String cupersonuuid=user.getPersonUuid();
			       String cuorgname=user.getCnName();
			       
			       List chuorgList=new ArrayList();
			       chuorgList=pHandler.getChuOrguuid(cuorguuid);
			       List phoneList=new ArrayList();
				   List fullorglist=new ArrayList();
				   phoneList=pHandler.ManageSearchPhone(noteids,username,orguuid,officeaddress,officephone,homephone, mobilephone, netphone, faxphone,chuorgList);

				   Iterator it = phoneList.iterator();
				   while(it.hasNext())
				   {
				      PhoneInfoVO vo=(PhoneInfoVO)it.next();
				      fullorglist.add(pHandler.getFullOrgName(vo.getOrguuid()));
				   }
				   request.setAttribute("chuorgList",chuorgList);
				   request.setAttribute("phoneList",phoneList);
				   request.setAttribute("fullorglist",fullorglist);
				   //System.out.println("sizesize3:"+phoneList.size());
				  this.forward(request,response,"/phonebook/managephone.jsp?orguuid="+orguuid);	    
			}catch (Exception e) {
				e.printStackTrace();
			}finally 
			{
				if (conn != null)
					try { 
						 conn.close();
						 ConnLog.close("MySearchPhoneServlet");
					}
				    catch (Exception e2) {
						e2.printStackTrace();
					}
		    }
	}
}

