/*
 * Created on 2004-4-16
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.icss.oa.address.admin;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.common.log.ConnLog;
import com.icss.j2ee.services.ServiceLocatorException;
import com.icss.j2ee.servlet.ServletBase;
import com.icss.j2ee.util.Globals;
import com.icss.oa.address.handler.Group;
import com.icss.oa.address.handler.SysOrgpersonHandler;
import com.icss.oa.address.vo.AddressGroupinfoVO;
import com.icss.oa.address.vo.GrouprightVO;
import com.icss.oa.address.vo.SysOrgpersonVO;
import com.icss.oa.config.AddressConfig;

/**
 * @author Administrator
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class SearchPersonServlet extends ServletBase {
	protected void performTask(
		HttpServletRequest request,
		HttpServletResponse response)
		throws ServletException, IOException {
		Connection conn = null;
		try {
			try {
				conn = getConnection(Globals.DATASOURCEJNDI);
				ConnLog.open("SearchPersonServlet");
			} catch (ServiceLocatorException e) {
				throw new RuntimeException("取得数据库连接错误");
			}
			
			
			Group group = new Group(conn);
			String cnname=request.getParameter("cnname");

			SysOrgpersonHandler handler = new SysOrgpersonHandler(conn);
			List list = handler.getbycnname(cnname);
			List list1 = handler.getbycnname(cnname);
			
			String uuid1 = null;
			
			if(!list1.isEmpty()){
				Iterator it1 = list1.iterator();
				while(it1.hasNext()){
					SysOrgpersonVO vo = (SysOrgpersonVO)it1.next();
					uuid1 = vo.getPersonuuid();
					}
				}
	
			List personGroupList  = null;
			List accredPersonList = null;
			if(!list.isEmpty()){
				Iterator it = list.iterator();
				while(it.hasNext()){
					
					SysOrgpersonVO vo = (SysOrgpersonVO)it.next();
					List groupList =
						group.personGroup(vo.getPersonuuid(), AddressConfig.GROUPTYPE_COMMOM);
					personGroupList = personGroupList(group, groupList);

					List accredList = group.getAccreditGroupList(vo.getPersonuuid());
					accredPersonList = accredGroup(group, accredList);
					}
				}
			
			request.setAttribute("personGroupList", personGroupList);
			request.setAttribute("accredPersonList", accredPersonList);
			request.setAttribute("cnnname1",cnname);
			request.setAttribute("uuid1",uuid1);
			
			if(uuid1==null)this.forward(
					request,
					response,
					"/address/errorString.jsp?errorS=您所输入的人员["+cnname+"]根本就不存在！");
			
			this.forward(request, response, "/address/searchResult.jsp");
			
		} catch (Exception e) {
			handleError(e);
		} finally {
			try {
				if (conn != null) {
					conn.close();
					ConnLog.close("SearchPersonServlet");
				}
			} catch (SQLException e1) {
				throw new RuntimeException("数据库连接关闭错误");
			}
		}

	}
	
	private List accredGroup(Group group, List accredList) {
		Iterator accredIterator = accredList.iterator();
		ArrayList accredPersonList = new ArrayList();
		while (accredIterator.hasNext()) {
			GrouprightVO grouprightVO = (GrouprightVO) accredIterator.next();
			accredPersonList.add(
				group.getCommonGroup(grouprightVO.getGroupid()));
		}
		return accredPersonList;
	}
	
	private List personGroupList(Group group, List groupList) {
		ArrayList personGroupList = new ArrayList();
		Iterator personIterator = groupList.iterator();
		while (personIterator.hasNext()) {
			AddressGroupinfoVO addressGroupinfoVO =
				(AddressGroupinfoVO) personIterator.next();
			personGroupList.add(
				group.getCommonGroup(addressGroupinfoVO.getGroupid()));
		}
		return personGroupList;
	}
}
