/*
 * Created on 2004-7-21
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.icss.oa.filetransfer.admin;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.j2ee.servlet.ServletBase;
import com.icss.j2ee.util.Globals;
import com.icss.oa.address.handler.Group;
import com.icss.oa.address.vo.AddressGroupVO;
import com.icss.resourceone.sdk.framework.EntityManager;
import com.icss.resourceone.sdk.framework.Person;

/**
 * @author firecoral
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class AddToGroupServlet extends ServletBase{

	/* (non-Javadoc)
	 * @see com.icss.j2ee.servlet.ServletBase#performTask(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	protected void performTask(HttpServletRequest request,HttpServletResponse response)
		throws ServletException, IOException {
		Integer groupid = new Integer(request.getParameter("groupid"));
		String type  = request.getParameter("type");
		String personUUid = request.getParameter("personUUid");
		Connection conn = null;
		String result = "";
		try {
			conn = getConnection(Globals.DATASOURCEJNDI);
			Group group = new Group(conn);
			AddressGroupVO vo = group.getIndiGroup(groupid);
			String groupName = vo.getGroupname();			
			EntityManager entityManager = EntityManager.getInstance();
			Person person = entityManager.findPersonByUuid(personUUid);
			if (group.isPersonInGroup(personUUid,groupid,type)) {
				result = "'"+person.getFullName()+"'在你的个人分组["+groupName+"]中已经存在！";
			}else{
				group.appendGroupWithPerson(personUUid,groupid,type);
				result = "已成功将'"+person.getFullName()+"'添加到您的个人分组["+groupName+"]中！";
			}
			request.setAttribute("addResult",result);
			request.setAttribute("title1","加入人个分组");
			request.setAttribute("title2","操作结果");
			this.forward(request, response, "/filetransfer/addGroupResult.jsp");		
		} catch (Exception e) {
			e.printStackTrace();
			handleError(e);
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}
}
