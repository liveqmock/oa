/*
 * Created on 2004-6-3
 */
package com.icss.oa.intendwork.admin;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.common.intendwork.IntendWorkHandler;
import com.icss.common.log.ConnLog;
import com.icss.j2ee.services.DBConnectionLocator;
import com.icss.j2ee.servlet.ServletBase;
import com.icss.oa.intendwork.handler.IntendWork;
import com.icss.resourceone.common.logininfo.UserInfo;
import com.icss.resourceone.sdk.framework.Context;

public class GetTopServlet extends ServletBase{

	protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	     	
	     	Connection conn = null;
			try {
				//取数据库连接
				conn = DBConnectionLocator.getInstance().getConnection(this.getServletContext().getInitParameter("addressjndi"));
				ConnLog.open("GetTopServlet");
				
				
				//取得用户信息
				Context ctx = Context.getInstance();
				UserInfo user = ctx.getCurrentLoginInfo();
				String loginname = user.getUserID();
				String personuuid = user.getPersonUuid();
				
				
				//1.取待办工作信息
				IntendWork intendhandler = new IntendWork(conn);
		        //普通待办
				List totalList = intendhandler.getTotalList(loginname);
				//重要待办
				//List getImportantList = intendhandler.getImportantList(loginname);


		        //2.取得其它子系统设置的待办工作信息，返回IntendWorkVO对象的集合
		        //这里主要是根据IntendWorkConfig.xml文件中设置的待办工作实现类并实例化，然后调用接口的方法取得IntendWorkVO对象的集合
		        //IntendWorkVO对象中包括的信息有：
		        //intendWork.setSysid(sysid); 					//子系统ID
				//intendWork.setLevel(IntendWorkVO.CODE_RED); 	//重要级别
				//intendWork.setTitle(objectName); 				//标题
				//intendWork.setTime(fromData); 				//日期
				//intendWork.setUrl(url); 						//url——为切换到对应子系统时，反问的模块url。而非子系统默认的url
				IntendWorkHandler handler = IntendWorkHandler.newInstance();
				handler.setPersonuuid(personuuid);
		        List otherList = handler.getIntendWorks();
		        
		        request.setAttribute("totalList",totalList);   			//普通
				//request.setAttribute("ringlist",getImportantList);  	//重要
				request.setAttribute("otherList",otherList);			//其它子系统待办工作
		        
		        forward(request,response,"/netoffice/intendWork/showTop.jsp");		
		        
			}catch (Exception e){
				handleError(e);
			} finally { 
			try {
				if (conn != null) {
					conn.close();
					ConnLog.close("GetTopServlet");
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			
		}
		
		
	}


}



//为了添加个人设置的提示栏目设置 已删除
/*String setup_item = null;
String condition = null;
String []setup_item1 = new String[200];
setup_item = intendhandler.getItembyUserid(loginname);
if(setup_item!=null){
	setup_item1= intendhandler.getSetUpItemArray(setup_item);
	condition = intendhandler.getCondition(setup_item1);
	}
if(condition==null)  totalList = intendhandler.getTotalList(loginname);
else  totalList = intendhandler.getTotalListbySort(loginname,condition);*/
		        

