/*
 * Created on 2004-8-9
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
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.common.log.ConnLog;
import com.icss.j2ee.servlet.ServletBase;
import com.icss.j2ee.util.Globals;
import com.icss.oa.phonebook.handler.PhoneHandler;
import com.icss.resourceone.common.logininfo.UserInfo;
import com.icss.resourceone.user.util.PersonUtil;
import com.icss.resourceone.sdk.framework.Context;
import com.icss.oa.phonebook.vo.PhoneSysNameVO;

/**
 * @author firecoral
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class MySearchPhoneServlet extends ServletBase{
	/* (non-Javadoc)
	 * @see com.icss.j2ee.servlet.ServletBase#performTask(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	protected void performTask(HttpServletRequest request, HttpServletResponse response) 
	    throws ServletException, IOException {
		System.out.println("1");
	    	Connection conn = null;
	    	List searchList = new ArrayList();
	    	List searchHrList=new ArrayList();
	    	String username="",orgname="",deptname="",functionname="",phonenum="",hrid="";
	    	//从页面上获取条件参数
	    	if(request.getParameter("username")!=null)
	    	  username = request.getParameter("username");
	    	if(request.getParameter("orgname")!=null)
	    	  orgname=request.getParameter("orgname");
	    	if(request.getParameter("deptname")!=null)
	    		deptname=request.getParameter("deptname");
	    	if(request.getParameter("functionname")!=null)
	          functionname = request.getParameter("functionname");
	    	if(request.getParameter("phonenum")!=null)
	    	  phonenum = request.getParameter("phonenum");
	    	if(request.getParameter("hrid")!=null)
		    	  hrid = request.getParameter("hrid");
	    	if(functionname.equals("0"))
	    		functionname="";
	    	String detail = "";
	    	if(request.getParameter("detail")!=null){
	    		detail = request.getParameter("detail");
	    	}
	    	
	    	System.out.println("2");
	    	
			try {
				conn = this.getConnection(Globals.DATASOURCEJNDI);
				ConnLog.open("MySearchPhoneServlet");
				PhoneHandler pHandler = new PhoneHandler(conn);
				System.out.println("3");
				//获取登陆人员信息
				Context ctx = Context.getInstance();
	            UserInfo user = ctx.getCurrentLoginInfo();
	            System.out.println("4");
	            //获取登陆人员的职级,从人事视图中获取,如果没有返回空
	            String jobcode = pHandler.getPersonJobCode(user.getPersonUuid());
	            //System.out.println("--------job code is "+jobcode);
	            //获取登陆人员的职级查看权限
	            String searchpriv = "";
	            if(!"".equals(jobcode)){
	            	searchpriv = pHandler.getSearchPrivJobCode(jobcode);
	            }System.out.println("5");
	            //System.out.println("--------search priv by job "+searchpriv);
	            request.setAttribute("searchpriv", searchpriv);
	            //获取登陆人员的特殊查看权限,如果没有返回空
	            String specialpriv = pHandler.getSpecialSearchPriv(user.getPersonUuid());
	            //System.out.println("--------specialpriv "+specialpriv);
	            request.setAttribute("specialpriv", specialpriv);
	            System.out.println("6");
				//是否值班电话
//			    if(functionname.equals("2")){
//					  searchList=pHandler.MyGetSearchList(username,phonenum,orgname,deptname);
//				}
//				else{
					  //PhoneInfoHrPersonSearchVO类型List
					  searchHrList=pHandler.HrPhoneInfoList(username,functionname,phonenum,orgname,deptname,detail,hrid);
//			    }
//			    System.out.println("rtrtrt"+searchHrList.size());
				request.setAttribute("searchList",searchList);
				request.setAttribute("searchHrList",searchHrList);
				request.setAttribute("functionname",functionname);
				request.setAttribute("username",username);
				request.setAttribute("orgname",orgname);
				request.setAttribute("deptname",deptname);
				request.setAttribute("phonenum",phonenum);
				request.setAttribute("hrid",hrid);System.out.println("7");
				this.forward(request,response,"/phonebook/showUserPhone.jsp");
				System.out.println("8");
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				if (conn != null)
					try {
						conn.close();
						ConnLog.close("MySearchPhoneServlet");
					} catch (Exception e2) {
						e2.printStackTrace();
					}
			}
	}
}

