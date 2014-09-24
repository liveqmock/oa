/*
 * Created on 2004-8-4
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.icss.oa.phonebook.admin;

import java.io.IOException;
import java.sql.Connection;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.common.log.ConnLog;
import com.icss.j2ee.servlet.ServletBase;
import com.icss.j2ee.util.Globals;
import com.icss.oa.phonebook.handler.PhoneHandler;
import com.icss.oa.util.RoleControl;
import com.icss.resourceone.common.logininfo.UserInfo;
import com.icss.resourceone.sdk.framework.Context;

/**
 * @author firecoral
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class GetMPhoneServlet extends ServletBase{

	/* (non-Javadoc)
	 * @see com.icss.j2ee.servlet.ServletBase#performTask(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	protected void performTask(HttpServletRequest request, HttpServletResponse response) 
		throws ServletException, IOException {
			
			Connection conn = null;
			
			List phoneList = new ArrayList();
			
			String OrgUUid  = request.getParameter("orgid");
			String personUUid = "";
			String maintanPerson = "";
			String orgName = "";
			
			try {
				Context ctx = null;
				ctx = Context.getInstance();
				UserInfo user = ctx.getCurrentLoginInfo();
				
				conn = this.getConnection(Globals.DATASOURCEJNDI);
				ConnLog.open("GetMPhoneServlet");
				PhoneHandler phandler = new PhoneHandler(conn);
			
				personUUid = user.getPersonUuid();
				if(OrgUUid==null){
					String orgUUid = user.getPrimaryOrguuid();
					//�õ����ھֵ�ORGUUID
					OrgUUid = phandler.getJuOrguuid(orgUUid);
				}
				//��֯��
				orgName = phandler.GetOrgName(OrgUUid);  //��¼��������֯
				//05/05/26lijy��
				//�ж��Ƿ�Ϊ�缶�쵼�ĵ绰��ְ������ɫ
//				boolean isShe = false;
//				boolean pd = false;
//				pd = phandler.hasRole(user.getPersonUuid(),"oa.shephonel");
//				if(pd){
//					OrgUUid = "4161cca6-fc3554e80d-97fe05e58eef24f3370b74f3cc7c23fc";
//					orgName = "���쵼";
//				}
				//�õ�������(��������)����Ϣ���л�û�г��ּ�¼����
				List notPhonelist = phandler.getNoPhonePerson(OrgUUid);
				if(notPhonelist!=null&&notPhonelist.size()>0){
					//�����Ǽ�����Ϣ��
					phandler.setNoPhoneNote(notPhonelist);
				}
				//��¼�û�����
				maintanPerson = phandler.getUserName(personUUid);
				phoneList = phandler.GetOrgPhone(OrgUUid);
							
				request.setAttribute("phoneList",phoneList);
				request.setAttribute("maintanPerson",maintanPerson);
				request.setAttribute("orgName",orgName);
				request.setAttribute("orgUUid",OrgUUid);
				
				this.forward(request,response,"/phonebook/adminPhone.jsp");
				
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				if (conn != null)
					try {
						conn.close();
						ConnLog.close("GetMPhoneServlet");
					} catch (Exception e2) {
						e2.printStackTrace();
					}
			}
		
	}
}
