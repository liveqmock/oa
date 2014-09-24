/*
 * Created on 2004-7-30
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.icss.oa.netoffice.onduty.util;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.common.log.ConnLog;
import com.icss.j2ee.services.ServiceLocatorException;
import com.icss.j2ee.servlet.ServletBase;
import com.icss.j2ee.util.Globals;
import com.icss.oa.config.OndutyConfig;
import com.icss.oa.netoffice.onduty.handler.DutyHandler;
import com.icss.oa.phonebook.handler.PhoneHandler;
import com.icss.resourceone.common.logininfo.UserInfo;
import com.icss.resourceone.sdk.framework.Context;
import com.icss.resourceone.sdk.framework.EntityException;

/**
 * @author firecoral
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class InitOndutyServlet extends ServletBase{

	/* (non-Javadoc)
	 * @see com.icss.j2ee.servlet.ServletBase#performTask(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	
	protected void performTask(HttpServletRequest request, HttpServletResponse response) 
	    throws ServletException, IOException {
	    	
			String permission = "";
			String orgUUid = "";
			
			String personUUid = "";
			Context ctx = null;
			Connection conn = null;
			try {
				ctx = Context.getInstance();
				UserInfo user = ctx.getCurrentLoginInfo();
				
				//System.out.println(user.getCnName());
				personUUid = user.getPersonUuid();
				orgUUid = user.getPrimaryOrguuid();  //��¼�������ڱ���֯,���û���ڱ���֯ʱ���ڲ鿴���������Ű��ʱ����
			
				ServletContext sc = this.getServletContext();
			
				//�ж��Ƿ�Ϊ�ɲ鿴����֯��������֯��ֵ���
				boolean pd = false;
				DutyHandler dHandler = new DutyHandler();
				pd = dHandler.hasRole(personUUid,"oa.dutyform");
				boolean ifAll = false;
				ifAll = dHandler.hasRole(personUUid,"oa.dutysuper");//���Բ鿴���е�ֵ���Ӧ�ý�ɫ
			
				//����Ȩ��
				if(ifAll){
					permission = OndutyConfig.ALLPERMISSION;
				}else if(pd==true){
					permission = OndutyConfig.PARTPERMISSION;
				}
				
				if(permission.equals(OndutyConfig.ALLPERMISSION)){
					//System.out.println("���Բο�����ֵ���1..........");
					this.forward(request,response,"/servlet/OrgTreeServlet?nodeUrl=OndutyServlet");
					
				}else if(permission.equals(OndutyConfig.PARTPERMISSION)){
					//System.out.println("���Բο���������ֵ���1..........");
					conn = this.getConnection(Globals.DATASOURCEJNDI);
					ConnLog.open("InitOndutyServlet");
					PhoneHandler phandler = new PhoneHandler(conn);
			    	String juOrguuid = phandler.getJuOrguuid(orgUUid);
					this.forward(request,response,"/servlet/OrgTree3Servlet?nodeUrl=OndutyServlet&orguuid="+juOrguuid);
					
				}else{
					//System.out.println("ֻ�鿴����λ��ֵ���1..........");
					this.forward(request,response,"/netoffice/onduty/left.jsp");
				}
				
			} catch (EntityException e) {
				e.printStackTrace();
				
			} catch (ServiceLocatorException e) {
				e.printStackTrace();
				
			} finally{
				
				if(conn!=null){
					try {
						conn.close();
						ConnLog.close("InitOndutyServlet");
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
			}
	}

}
