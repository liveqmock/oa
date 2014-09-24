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
import com.icss.resourceone.common.logininfo.UserInfo;
import com.icss.resourceone.sdk.framework.Context;
import com.icss.oa.phonebook.vo.SysOrgVO;
import com.icss.oa.phonebook.dao.SysOrgDAO;
import com.icss.oa.phonebook.vo.PhonePrivVO;
import com.icss.oa.phonebook.vo.PhonePrivPersonVO;

/**
 * @author firecoral
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class AddPhonePrivServlet extends ServletBase{

	/* (non-Javadoc)
	 * @see com.icss.j2ee.servlet.ServletBase#performTask(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	protected void performTask(HttpServletRequest request, HttpServletResponse response) 
	    throws ServletException, IOException {
	    	
			Connection conn = null;
			String phoneprivname ="",privtype="",scope="",joblevelstr="",showjoblevelstr=""
			      ,useruuid="",username="";
			HttpSession session = request.getSession();
			
			if(request.getParameterValues("joblevel")!=null&&request.getParameterValues("showjoblevel")!=null)
			{
			    String[] joblevel = request.getParameterValues("joblevel");
			    String[] showjoblevel = request.getParameterValues("showjoblevel");
		        for (int i =0; i < joblevel.length; i++) 
		        {   
			        if(i>0)
			    	    joblevelstr=joblevelstr+",";
			        joblevelstr=joblevelstr+joblevel[i].substring(0,joblevel[i].length()-1);
			
		        }
		        for(int i=0;i<showjoblevel.length;i++)
		        {
		    	     if(i>0)
		    		     showjoblevelstr=showjoblevelstr+",";
		    	    showjoblevelstr=showjoblevelstr+showjoblevel[i].substring(0,showjoblevel[i].length()-1);
		        }
			}
		    
		    	
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

			try {
				   conn = this.getConnection(Globals.DATASOURCEJNDI);
				   PhoneHandler pHandler = new PhoneHandler(conn); 
				   ConnLog.open("AddPhonePrivServlet");
					   PhonePrivVO pVO = new PhonePrivVO();
					   pVO.setPp_scope(scope);
					   pVO.setPp_type(privtype);
					   pVO.setPp_name(phoneprivname);
					   if(privtype.equals("0")){
				           pVO.setPp_level(joblevelstr);
				           pVO.setPp_searchlevel(showjoblevelstr);
					   }
					   //����һ��Ȩ��phone_priviledge��
				       Integer pp_id = pHandler.NewPhonePriv(pVO);
				       
				       //���������һ������Ȩ��
				       if(privtype.equals("1"))
				       {
				    	   if(!"".equals(username)&&!"".equals(useruuid))
				    	   {  
				    		  String[] useruuidarr=useruuid.split(",");
				    		  String[] usernamearr=username.split(",");
				    		  //����������Աÿ���½�һ������Ȩ����Ա��¼ ��phone_privperson��
				    		  for(int i=0;i<useruuidarr.length;i++)
				    		  {
				    	          PhonePrivPersonVO perVO=new PhonePrivPersonVO();
				    	          //��pp_id��Ȩ�ޱ�pp_id���������
				    	          perVO.setPp_id(pp_id.toString());
				    	          //perVO.setUserid();
				    	          perVO.setPersonuuid(useruuidarr[i]);
				    	          perVO.setPersonname(usernamearr[i]);
				    	          Integer ppp_id=pHandler.NewPhonePrivPerson(perVO);
				    		  }
				    	   }
				       }
				       //out.println("added"+pp_id);
				       response.sendRedirect("/oabase/servlet/PhonebookprivServlet?tips=added");
				
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				if (conn != null)
					try {
						conn.close();
						ConnLog.close("AddPhonePrivServlet");
					} catch (Exception e2) {
						e2.printStackTrace();
					}
			} 
	}
}





