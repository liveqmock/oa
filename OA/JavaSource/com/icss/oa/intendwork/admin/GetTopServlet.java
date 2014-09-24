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
				//ȡ���ݿ�����
				conn = DBConnectionLocator.getInstance().getConnection(this.getServletContext().getInitParameter("addressjndi"));
				ConnLog.open("GetTopServlet");
				
				
				//ȡ���û���Ϣ
				Context ctx = Context.getInstance();
				UserInfo user = ctx.getCurrentLoginInfo();
				String loginname = user.getUserID();
				String personuuid = user.getPersonUuid();
				
				
				//1.ȡ���칤����Ϣ
				IntendWork intendhandler = new IntendWork(conn);
		        //��ͨ����
				List totalList = intendhandler.getTotalList(loginname);
				//��Ҫ����
				//List getImportantList = intendhandler.getImportantList(loginname);


		        //2.ȡ��������ϵͳ���õĴ��칤����Ϣ������IntendWorkVO����ļ���
		        //������Ҫ�Ǹ���IntendWorkConfig.xml�ļ������õĴ��칤��ʵ���ಢʵ������Ȼ����ýӿڵķ���ȡ��IntendWorkVO����ļ���
		        //IntendWorkVO�����а�������Ϣ�У�
		        //intendWork.setSysid(sysid); 					//��ϵͳID
				//intendWork.setLevel(IntendWorkVO.CODE_RED); 	//��Ҫ����
				//intendWork.setTitle(objectName); 				//����
				//intendWork.setTime(fromData); 				//����
				//intendWork.setUrl(url); 						//url����Ϊ�л�����Ӧ��ϵͳʱ�����ʵ�ģ��url��������ϵͳĬ�ϵ�url
				IntendWorkHandler handler = IntendWorkHandler.newInstance();
				handler.setPersonuuid(personuuid);
		        List otherList = handler.getIntendWorks();
		        
		        request.setAttribute("totalList",totalList);   			//��ͨ
				//request.setAttribute("ringlist",getImportantList);  	//��Ҫ
				request.setAttribute("otherList",otherList);			//������ϵͳ���칤��
		        
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



//Ϊ����Ӹ������õ���ʾ��Ŀ���� ��ɾ��
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
		        

