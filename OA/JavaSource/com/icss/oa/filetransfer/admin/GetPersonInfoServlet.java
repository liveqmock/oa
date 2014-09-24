/*
 * Created on 2004-7-21
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.icss.oa.filetransfer.admin;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.common.log.ConnLog;
import com.icss.j2ee.services.ServiceLocatorException;
import com.icss.j2ee.servlet.ServletBase;
import com.icss.j2ee.util.Globals;
import com.icss.oa.address.handler.HandlerException;
import com.icss.oa.address.vo.AddressGroupVO;
import com.icss.oa.filetransfer.handler.FiletransferSetHandler;
import com.icss.oa.filetransfer.handler.personInfoHandler;
import com.icss.resourceone.common.logininfo.UserInfo;
import com.icss.resourceone.sdk.framework.Context;
import com.icss.resourceone.sdk.framework.EntityException;
import com.icss.resourceone.sdk.framework.EntityManager;
import com.icss.resourceone.sdk.framework.Person;


/**
 * @author firecoral
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class GetPersonInfoServlet extends ServletBase{

	/* (non-Javadoc)
	 * @see com.icss.j2ee.servlet.ServletBase#performTask(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	protected void performTask(HttpServletRequest request, HttpServletResponse response) 
	     throws ServletException{
		
		Connection conn = null;
		Context ctx = null;

		try {
			
			conn = this.getConnection(Globals.DATASOURCEJNDI);
			ConnLog.open("GetPersonInfoServlet");
			personInfoHandler handler = new personInfoHandler(conn);
			ctx = Context.getInstance();
			UserInfo curUser = ctx.getCurrentLoginInfo();
			String curUserId = curUser.getUserID();
			String curUserName = curUser.getCnName();
		
			String fromPerson = request.getParameter("from");
		
			int index1 = fromPerson.indexOf("<");
			int index2 = fromPerson.indexOf("@");
			String userid = fromPerson.substring(index1+1,index2);

			//依邮箱设置表，由邮箱的用户名得到数据库中通用的用户名，
			FiletransferSetHandler ftsHandler = new FiletransferSetHandler(conn);
			String username = ftsHandler.getUid(userid);
			String senduuid = ftsHandler.getAllUUid(userid);
			
			//得到该人的所在的部门
			String dep = handler.getPersonJUPosition(senduuid);

			AddressGroupVO agVO = new AddressGroupVO();
			agVO.setGroupuser(curUserId);
			agVO.setGroupname(curUserName);
		
			String result = "";
			
			
		/*	Group group1 = new Group(conn);
			Integer flag = group1.AddPersonGroupByFileTran(senduuid,curUser.getPersonUuid());
		//	System.out.println("falg: 0"+flag);
			if(flag.intValue()!=0){
				group1.appendGroupWithPerson(senduuid,flag,"2");}*/
			//判断添加的个人分组是否已经存在
		/*	boolean pd = ftsHandler.reGroupid(curUserId);
			if(pd==false){
				Group group = new Group(conn);
				group.addGroup(agVO);
		
				//得到当前用户新设置的个人分组的ID
				Integer groupid = ftsHandler.getGroupid(curUserId);
				group.appendGroupWithPerson(userid,groupid,"2");
		
				result = curUserId+"已成功添加到您的个人分组！";
			}else{
				result = curUserId+"在你的个人分组已存在！";
			}*/

			EntityManager entityManager = EntityManager.getInstance();
			Person person = entityManager.findPersonByUuid(senduuid);
			
			/*if(flag.intValue()!=0){
				result = person.getFullName()+"已成功添加到您的个人分组！";}
			else{result = person.getFullName()+"在你的个人分组已存在！";}
			request.setAttribute("sendresult",result);
			request.setAttribute("title1","加入人个分组");*/
			request.setAttribute("person",person);
			request.setAttribute("dep",dep);
			 
			this.forward(request,response,"/mail/PersonInfo.jsp");
			 
		} catch (UnsupportedEncodingException e3) {
			e3.printStackTrace();
		}catch (ServletException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		} catch (ServiceLocatorException e) {
			e.printStackTrace();
		} catch (EntityException e) {
			e.printStackTrace();
		} catch (HandlerException e) {
			e.printStackTrace();
		} finally {
			if(conn!=null){
				try {
					conn.close();
					ConnLog.close("GetPersonInfoServlet");
				} catch (SQLException e2) {
					e2.printStackTrace();
				}
			}
		}
	}

}
