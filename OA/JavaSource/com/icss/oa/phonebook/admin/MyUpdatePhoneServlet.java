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
import com.icss.j2ee.dao.DAOException;
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
import com.icss.oa.phonebook.dao.PhoneInfoDAO;
import com.icss.oa.phonebook.dao.SysOrgDAO;

/**
 * @author firecoral
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class MyUpdatePhoneServlet extends ServletBase{

	/* (non-Javadoc)
	 * @see com.icss.j2ee.servlet.ServletBase#performTask(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	protected void performTask(HttpServletRequest request, HttpServletResponse response) 
	    throws ServletException, IOException {
	    	
			Connection conn = null;
			PrintWriter out = response.getWriter();
			String noteids=request.getParameter("noteids");
			String orguuid = request.getParameter("orguuid");
			String orgname = request.getParameter("orgname");
			String officeaddress = request.getParameter("officeaddress");
			//String maintanPerson = request.getParameter("maintanPerson");
			//String jobs = request.getParameter("selectJobs");
			//String isparttime = request.getParameter("isparttime");
			String username = request.getParameter("username");
			String officephone = request.getParameter("officephone");
			String mobilephone = request.getParameter("mobilephone");
			String homephone = request.getParameter("homephone");
			String netphone = request.getParameter("netphone");
			String faxphone = request.getParameter("faxphone");
			
			String phoneJob = "";
			HttpSession session = request.getSession();
			List ownerList = (List) session.getAttribute("owner");
			//清除Session
			if(ownerList!=null){
				session.removeAttribute("owner");
			}
			try {
				   conn = this.getConnection(Globals.DATASOURCEJNDI);
				   PhoneHandler pHandler = new PhoneHandler(conn); 
				   ConnLog.open("MySearchPhoneServlet");

				   //插入电话记录
				   if(noteids!=null&&!noteids.equals("")&&orguuid!=null&&!("".equals(orguuid))&&!("-1".equals(orguuid))&&username!=null&&!("".equals(username)))
				   { 
					    AddressHelp addressHelp = new AddressHelp(conn);
				        PhoneInfoVO pVO = new PhoneInfoVO();
				        pVO.setNoteid(new Integer(noteids));
				        pVO.setOrguuid(orguuid);
				        pVO.setOfficeaddress(officeaddress);
				        pVO.setFunction("2");
				        pVO.setUseruuid("");
				        pVO.setUsername(username);
				        pVO.setNameids("");
				        pVO.setIsparttime("");
				        pVO.setOfficephone(officephone);
				        pVO.setOfficeaddress(officeaddress);
				        pVO.setHomephone(homephone);
				        pVO.setNetphone(netphone);
				        pVO.setMobilephone(mobilephone);
				        pVO.setFaxphone(faxphone);
				        pVO.setIspermission("");
				        pVO.setNoteorder(new Integer(0));
				        //pVO.setMaintanperson(maintanPerson);
				        pVO.setLastmaintantime(new Long(System.currentTimeMillis()));
				        pHandler.UpdatePhone(pVO);
				        response.sendRedirect("/oabase/servlet/ManageSearchPhoneServlet?noteids="+noteids+"&orguuid="+orguuid+"&addtipstr="+"updated");  
				   }
				   else
				   {
					   response.sendRedirect("/oabase/servlet/ManageSearchPhoneServlet?orguuid="+orguuid+"&addtipstr="+"notupdated");
				   }
			}catch (Exception e) {
				       e.printStackTrace();
			}finally {
				if (conn != null)
					try {
						conn.close();
						ConnLog.close("MyAddPhoneServlet");
					} catch (Exception e2) {
						e2.printStackTrace();
					}
			} 
	}
}



