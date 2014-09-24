package com.icss.regulation.admin;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.j2ee.dao.DAOFactory;
import com.icss.j2ee.servlet.ServletBase;
import com.icss.j2ee.util.Globals;
import com.icss.resourceone.common.logininfo.UserInfo;
import com.icss.resourceone.role.dao.RolePersonSearchDAO;
import com.icss.resourceone.role.model.RolePersonVO;
import com.icss.resourceone.sdk.framework.Context;

public class JudgeRoleServlet extends ServletBase {

	protected void performTask(HttpServletRequest request,
			HttpServletResponse response) {
		Connection conn = null;
		Connection connection = null;
		
		String isadmin = new String ("0");//1为管理员，0为普通
		try {
			Context ctx = null;
			ctx = Context.getInstance();
			UserInfo user = ctx.getCurrentLoginInfo();
			String uuid = user.getPersonUuid();

			conn = getConnection(Globals.DATASOURCEJNDI);
			connection = getConnection("jdbc/ROEEE");

			//RegulationHandler handler = new RegulationHandler(conn);

			DAOFactory daofactory = new DAOFactory(connection);
			
			//管理制度系统管理员
			RolePersonSearchDAO rolepersonsearchdao1 = new RolePersonSearchDAO();
			rolepersonsearchdao1.setRoleid(779);
			daofactory.setDAO(rolepersonsearchdao1);
			List list1 = daofactory.find(new RolePersonVO());
			//System.out.println("系统管理员list is "+list1.size());
			
			//管理制度部门管理员
			RolePersonSearchDAO rolepersonsearchdao2 = new RolePersonSearchDAO();
			rolepersonsearchdao2.setRoleid(778);
			daofactory.setDAO(rolepersonsearchdao2);
			List list2 = daofactory.find(new RolePersonVO());
			//System.out.println("部门管理员list is "+list2.size());
			
			if (list1 != null && !list1.isEmpty()) {
				Iterator it1 = list1.iterator();
				while (it1.hasNext()) {
					RolePersonVO vo1 = (RolePersonVO) it1.next();
					String a = vo1.getPersonuuid();
					if (uuid.endsWith(a)) {
						isadmin = new String("1");
						//System.out.println(uuid+"是系统管理员");
						break;
					}
				}
			}
			
			
			if (list2 != null && !list2.isEmpty()) {
				Iterator it2 = list2.iterator();
				while (it2.hasNext()) {
					RolePersonVO vo2 = (RolePersonVO) it2.next();
					String  b = vo2.getPersonuuid();
					if (uuid.endsWith(b)) {
						isadmin = new String("1");
						//System.out.println(uuid+"是部门管理员");
						break;
					}
				}
			}
			
			request.setAttribute("isadmin", isadmin);
			

			this.forward(request, response, "/regulation/welcome.jsp");

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException sqle) {
				sqle.printStackTrace();
			}
			
			try {
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException sqle) {
				sqle.printStackTrace();
			}
		}

	}

}