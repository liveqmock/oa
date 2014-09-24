/*
 * Created on 2004-8-23
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.icss.oa.phonebook.admin;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.icss.oa.address.handler.*;
import com.icss.common.log.ConnLog;
import com.icss.j2ee.servlet.ServletBase;
import com.icss.j2ee.util.Globals;
import com.icss.oa.phonebook.handler.PhoneHandler;
import com.icss.oa.phonebook.vo.PhonePrivVO;
import com.icss.oa.phonebook.vo.PhonePrivPersonVO;
import com.icss.oa.phonebook.vo.PhonePrivPersonSearchVO;

/**
 * @author firecoral
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class PhonebookprivServlet extends ServletBase {

	protected void performTask(
		HttpServletRequest request,
		HttpServletResponse response)
		throws ServletException, IOException {
		
		String phoneprivname ="",privtype="",scope="",useruuid="",username="";
		

		if(request.getParameter("from")!=null&&request.getParameter("from").equals("search")){
		if(request.getParameter("privtype")!=null)
			privtype=request.getParameter("privtype");
	
		if(request.getParameter("phoneprivname")!=null)
			phoneprivname=request.getParameter("phoneprivname");
		
		if(request.getParameter("scope")!=null)
			scope=request.getParameter("scope");
		
		if(request.getParameter("useruuid")!=null)
			useruuid=request.getParameter("useruuid");
		
		if(request.getParameter("username")!=null)
			username=request.getParameter("username");
		}
		String type="";
		HttpSession session = request.getSession();
		List ownerList = (List) session.getAttribute("owner");
		
		//清除Session
		if(ownerList!=null){
			   session.removeAttribute("owner");
		}
        
		Connection conn = null;
		try {
			List allchuorg = new ArrayList();
			conn = this.getConnection(Globals.DATASOURCEJNDI);
			ConnLog.open("PhonebookprivServlet");
			PhoneHandler phandler = new PhoneHandler(conn);
			SysOrgpersonHandler handler = new SysOrgpersonHandler(conn);
			
			List joblist=phandler.getAllJob();
			allchuorg = handler.getOrgByLevel1(3);
			
		
			
			List rphoneprivList=phandler.SearchPhonePrivPerson(phoneprivname,privtype,scope,useruuid,username);
			List phoneprivList=new ArrayList();
			Iterator it=rphoneprivList.iterator();
			while(it.hasNext())
			{
				PhonePrivPersonSearchVO vo=(PhonePrivPersonSearchVO)it.next();
				if(vo.getPp_scope().equals("0"))
					vo.setPp_scope("0,全社");
				else if(vo.getPp_scope().equals("-1"))
					vo.setPp_scope("-1,本部门");
				else
				    vo.setPp_scope(vo.getPp_scope()+","+phandler.GetOrgName(vo.getPp_scope()));
				phoneprivList.add(vo);
			}
			
			Map privmap = new HashMap();
		    privmap=phandler.getAllJobMap();
		    
			
			PrintWriter out=response.getWriter();

			//out.println(""+phoneprivList.size()+allchuorg.size()+joblist.size());
			
			request.setAttribute("allchuorg",allchuorg);
			request.setAttribute("privmap",privmap);
			request.setAttribute("joblist",joblist);
			
			request.setAttribute("phoneprivList",phoneprivList);
			this.forward(request,response,"/phonebook/phonebookprivilege.jsp");	
		
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (conn != null)
				try {
					conn.close();
					ConnLog.close("PhonebookprivServlet");
				} catch (Exception e2) {
					PrintWriter out=response.getWriter();
					out.println("error");
					//e2.printStackTrace();
				}
		}
	}
}
