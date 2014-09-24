/*
 * Created on 2004-4-12
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.icss.oa.address.admin;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.common.log.ConnLog;
import com.icss.j2ee.util.Globals;
import com.icss.j2ee.services.ServiceLocatorException;
import com.icss.j2ee.servlet.ServletBase;

import com.icss.oa.address.handler.Group;
import com.icss.oa.address.vo.AddressCommongroupAddressTransferVO;
import com.icss.oa.util.RoleControl;
import com.icss.resourceone.common.logininfo.UserInfo;
import com.icss.resourceone.sdk.framework.Context;
/**
 * @author Administrator
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class ListCommonTwoGroupServlet extends ServletBase {

	protected void performTask(
		HttpServletRequest request,
		HttpServletResponse response)
		throws ServletException, IOException {
		Connection conn = null;
		try {
			try {
				conn = getConnection(Globals.DATASOURCEJNDI);
				ConnLog.open("ListCommonTwoGroupServlet");
			} catch (ServiceLocatorException e) {
				throw new RuntimeException("取得数据库连接错误");
			}
			Group group = new Group(conn);
			Context ctx = Context.getInstance();
		    UserInfo user = ctx.getCurrentLoginInfo();
		    Integer flag = new Integer(0);
		    
		    if(RoleControl.hasRole(user.getPersonUuid(),"info.admin0")){
		     	flag = new Integer(1); 
		     }

			Integer parentID = new Integer(request.getParameter("ParentID"));
			if(("1").equals(request.getParameter("shouquan"))){

				
			    if(!group.isAccreded(parentID,user.getPersonUuid())&&flag.intValue()==0){
			    	this.forward(
							request,
							response,  
							"/address/errorString.jsp?errorS=您没有被授权查看该根分组信息！");}    
			    
			}
			
			if(flag.intValue()==0){

				//	List commonTwoGroupList = group.listCommonTwoGroup1(parentID,user.getPersonUuid());
				//	List YourcommonTwoGroupList = group.listCommonTwoGroup2(parentID,user.getPersonUuid());
				
				List commonTwoGroupList = group.listCommonTwoGroupbyMY(parentID,user.getPersonUuid());
				
				List commonTwoGroupList_clone = commonTwoGroupList;
				
				Iterator it = null;
				
				if(commonTwoGroupList_clone!=null) it = commonTwoGroupList_clone.iterator();
				
				StringBuffer str = new StringBuffer();
				int i=0;
				
				if(it!=null){
					while(it.hasNext()){
						AddressCommongroupAddressTransferVO vo = (AddressCommongroupAddressTransferVO)it.next();
						if(i++>0) str.append(",");
						str.append(vo.getId());
					}
				}
				
				List YourcommonTwoGroupList = group.listCommonTwoGroupByYour(parentID,str.toString());  
				
				request.setAttribute("commonTwoGroup", commonTwoGroupList);
				request.setAttribute("commonTwoGroup1", YourcommonTwoGroupList);
				request.setAttribute("ParentID",parentID);
				
				request.setAttribute("flag",new String("two"));
				this.forward(request, response, "/address/commonTwoGroup.jsp");
			}
			
			if(flag.intValue()==1){
				
  				List commonTwoGroupList = group.listCommonTwoGroup(parentID);
					 request.setAttribute("commonTwoGroup", commonTwoGroupList);
					 request.setAttribute("ParentID",parentID);
					 this.forward(request, response, "/address/commonTwoGroup1.jsp");
			}
            
			
		} catch (Exception e) {
			handleError(e);
		} finally {
			try {
				if (conn != null) {
					conn.close();
					ConnLog.close("ListCommonTwoGroupServlet");
				}
			} catch (SQLException e1) {
				throw new RuntimeException("数据库连接关闭错误");
			}
		}
	}

}
