/*
 * Created on 2004-4-13
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.icss.oa.address.admin;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.common.log.ConnLog;
import com.icss.j2ee.util.Globals;
import com.icss.j2ee.services.ServiceLocatorException;
import com.icss.j2ee.servlet.ServletBase;

import com.icss.oa.address.handler.Group;
import com.icss.oa.address.vo.AddressCommongroupVO;
import com.icss.oa.address.vo.AddressGroupVO;
import com.icss.oa.config.AddressConfig;
import com.icss.oa.util.RoleControl;
import com.icss.resourceone.common.logininfo.UserInfo;
import com.icss.resourceone.sdk.framework.Context;

/**
 * @author Administrator
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */

public class ListGroupInfoServlet extends ServletBase {

	protected void performTask(
		HttpServletRequest request,
		HttpServletResponse response)
		throws ServletException, IOException {
		Connection conn = null;
		
		try {
			try {
				conn = getConnection(Globals.DATASOURCEJNDI);  
				ConnLog.open("ListGroupInfoServlet");
			} catch (ServiceLocatorException e) {
				throw new RuntimeException("取得数据库连接错误");
			}
			String groupid = request.getParameter("groupid");
			String type = request.getParameter("type");
			Context ctx = Context.getInstance();
		    UserInfo user = ctx.getCurrentLoginInfo();
		    
		    Integer OneManager_flag = new Integer(0);
		    
		    //判断此人是否是一级信箱管理员
		    if(RoleControl.hasRole(user.getPersonUuid(),"info.admin0")){
		    	OneManager_flag = new Integer(1); 
		     	request.setAttribute("OneManager_flag", OneManager_flag);
		     }
			
			
			Integer ParentID=null;
			if(request.getParameter("ParentID")!=null){
				ParentID = new Integer(request.getParameter("ParentID"));
			}
			Group group = new Group(conn);
			
			if(("1").equals(request.getParameter("shouquan"))){

				
			    if(!group.isAccreded(ParentID,user.getPersonUuid())){
			    	this.forward(
							request,
							response,    
			    	         "/address/errorString.jsp?errorS=您没有被授权查看该根分组信息！因此也无权查看该分组下的二级分组信息");
			    }   
			}

			//List groupinfoList =  group.personInGroup(new Integer(groupid), type);
			List groupinfoList =  group.personInGroupbyName(new Integer(groupid), type);
			//取得这个人是否有权限管理这个二级分组
			Integer _2flag = new Integer(0);
			_2flag = group.IsPowerCommonTwoGroupbyMY(new Integer(groupid),user.getPersonUuid()); 
			
			String groupName = getGroupName(groupid, type, group);

			request.setAttribute("groupinfo", groupinfoList);
			request.setAttribute("groupname", groupName);
			request.setAttribute("groupid", groupid);
			request.setAttribute("_2flag",_2flag);
			if(!"2".equals(type)){
				request.setAttribute("owner",group.getCommonTwoOwner(new Integer(groupid)));
			}else{
				request.setAttribute("owner",user.getPersonUuid());
				}
			
		    request.setAttribute("return",new String("ListGroupInfoServlet"));
		    
		    if("1".equals(type)||"1".equals(request.getAttribute("type1")))
		    	 this.forward(request, response, "/address/groupInfo1.jsp");
		    else{
		    	this.forward(request, response, "/mail/PersonalGroupUser_Body.jsp");
		    	}
		    
		} catch (NumberFormatException ne) {
			handleError(ne);
		} catch (Exception e) {
			handleError(e);
		} finally {
			try {
				if (conn != null) {
					conn.close();
					ConnLog.close("ListGroupInfoServlet");
				}
			} catch (SQLException e1) {
				throw new RuntimeException("数据库连接关闭错误");
			}
		}
	}

	private String getGroupName(String groupid, String type, Group group) {
		if (type.equals(AddressConfig.GROUPTYPE_PRIVATE)) {
			AddressGroupVO addressGroupVO =
				group.getIndiGroup(new Integer(groupid));
			return addressGroupVO.getGroupname();
		} else if (type.equals(AddressConfig.GROUPTYPE_COMMOM)) {
			AddressCommongroupVO vo =
				group.getCommonGroup(new Integer(groupid));
			return vo.getGroupname();
		}
		return "";

	}

}
