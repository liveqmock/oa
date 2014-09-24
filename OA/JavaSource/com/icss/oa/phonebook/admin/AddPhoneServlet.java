/*
 * Created on 2004-8-5
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.icss.oa.phonebook.admin;

import java.io.IOException;
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
import com.icss.oa.phonebook.vo.PhoneInfoVO;

/**
 * @author firecoral
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class AddPhoneServlet extends ServletBase{

	/* (non-Javadoc)
	 * @see com.icss.j2ee.servlet.ServletBase#performTask(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	protected void performTask(HttpServletRequest request, HttpServletResponse response) 
	    throws ServletException, IOException {
	    	
			Connection conn = null;
			
			String orgUUid = request.getParameter("orgUUid");
			String orgName = request.getParameter("orgName");
			String maintanPerson = request.getParameter("maintanPerson");
			String function = request.getParameter("phonefunction");
			String jobs = request.getParameter("selectJobs");
			String isparttime = request.getParameter("isparttime");
			String belongto2 = request.getParameter("belongto2");
			String officephone = request.getParameter("officephone");
			String officeaddress = request.getParameter("officeaddress");
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
				ConnLog.open("AddPhoneServlet");
				PhoneHandler pHandler = new PhoneHandler(conn); 
				AddressHelp addressHelp = new AddressHelp(conn);
				PhoneInfoVO pVO = new PhoneInfoVO();
				pVO.setOrguuid(orgUUid);
				
				if("1".equals(function)){
					pVO.setFunction("");
					ownerList = addressHelp.getperson(ownerList,request,"owner");
					if(ownerList.size()>1){
						//选择的不是一个人员，而是分组
						this.forward(request,response,"/phonebook/addPhone.jsp?orgUUid="+orgUUid+"&orgName="+orgName+"&maintanPerson="+maintanPerson+"&addresult=no");	
					}else{
						SelectOrgpersonVO ownerVO = (SelectOrgpersonVO)ownerList.get(0);
						pVO.setUseruuid(ownerVO.getUseruuid());
						pVO.setUsername(ownerVO.getName());
					}
					//添加职务
					pVO.setNameids(jobs);
					//是否为兼职
					if(!("".equals(jobs))){
						if("checked".equals(isparttime)){
							pVO.setIsparttime(PhoneBookConfig.ISPARTTIME);
						}else if(isparttime==null){
							pVO.setIsparttime(PhoneBookConfig.NOPARTTIME);
						}
					}else{
						pVO.setIsparttime("");
					}
				}else if("2".equals(function)){
					pVO.setFunction("值班电话");
					pVO.setUseruuid("");
					pVO.setUsername(belongto2);
					pVO.setNameids("");
					pVO.setIsparttime("");
				}
				pVO.setOfficephone(officephone);
				pVO.setOfficeaddress(officeaddress);
				pVO.setHomephone(homephone);
				pVO.setNetphone(netphone);
				pVO.setMobilephone(mobilephone);
				pVO.setFaxphone(faxphone);
				pVO.setIspermission("");
				pVO.setMaintanperson(maintanPerson);
				pVO.setLastmaintantime(new Long(System.currentTimeMillis()));
				//得到排序号
				
				if("2".equals(function)){  //值班电话
					pVO.setNoteorder(new Integer(0));
				}else{
					if(!("".equals(jobs))){
						int noteOrder = pHandler.getNoteOrder(jobs);
						pVO.setNoteorder(new Integer(noteOrder));
					}else{
						pVO.setNoteorder(new Integer(999));
					}
				}
				Integer noteId = pHandler.NewPhone(pVO);

				this.forward(request,response,"/phonebook/addPhoneClose.jsp");
				
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				if (conn != null)
					try {
						conn.close();
						ConnLog.close("AddPhoneServlet");
					} catch (Exception e2) {
						e2.printStackTrace();
					}
			}
	}
}





