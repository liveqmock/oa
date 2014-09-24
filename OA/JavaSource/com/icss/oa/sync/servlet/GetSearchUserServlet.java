package com.icss.oa.sync.servlet;
 

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.common.log.ConnLog;
import com.icss.j2ee.servlet.ServletBase;
import com.icss.oa.sync.handler.UserSyncHandler;
import com.icss.oa.sync.vo.PersonSyncSearchVO;
 
/**
 * @author ������
 *
 * ���ݸ���������������ͬ���û�����List���ظ�ҳ��
 *  
 */
 

 
public class GetSearchUserServlet extends ServletBase{
	
	 
	protected void performTask(HttpServletRequest request, HttpServletResponse response) 
	throws ServletException, IOException {
		
		
		
		//ԭ���Ĵ���
		
		/*
		Connection conn = null;
		List userList = new ArrayList();
		List orgCnameList=new ArrayList();
		
		String JNDI="jdbc/ROEEE";
		int i;
		try{
			
			conn = this.getConnection(JNDI);
		  	ConnLog.open("GetSearchUserServlet");
		 	UserSyncHandler handler=new UserSyncHandler(conn);
		 	
		 	
		 	String name=request.getParameter("name");
			String userid=request.getParameter("usercode");
			String Otype=request.getParameter("operatetype");
			String Atype=request.getParameter("audittype");
			
			
		 
			//�õ�ͬ���û�����Ϣ
			if(Otype==null||Atype==null){
				userList=handler.GetSearchPerson("","","all",9);
			}
			else{
				 
				int audittype=Integer.parseInt(Atype);
				userList=handler.GetSearchPerson(name,userid,Otype,audittype);
			}
		     
		 	//����ÿ���û�����Ӧ����֯������
		  
		    for(i=0;i<userList.size();i++){
		    	
		    	String orgtree; 
		    	PersonTempVO person=(PersonTempVO)userList.get(i);
		    	
		    	String orgname=handler.GetOrgName(person.getOrgid());
		    	String tempcode=person.getDeptid();
		     
		    	if(tempcode==null||tempcode.equals("")){
		    		orgtree=orgname;
		    	 }
		    	else{
		    		orgtree=orgname+"->"+handler.GetOrgName(tempcode);
		    	}
		    	orgCnameList.add(i,orgtree);
		    	
		    }
		    
		    request.setAttribute("userList",userList); 
		    request.setAttribute("orgCnameList", orgCnameList);
		    
		   this.forward(request,response,"/syncperson/syncaudit.jsp");
			
		} catch (Exception e) {
		 
		 	e.printStackTrace();
		}
		 finally {
			if (conn != null)
				try {
					conn.close();
					ConnLog.close("GetSearchUserServlet");
				} catch (Exception e2) {
					e2.printStackTrace();
				}
		}
		*/
		
		
		
		
		
		
		//�޸ĺ�Ĵ���
		
		Connection conn = null;
		List userList = new ArrayList();
		List orgCnameList=new ArrayList();
		List oldOrgCnameList= new ArrayList();
		
		String JNDI="jdbc/ROEEE";
		int i; 
		try{
			
			conn = this.getConnection(JNDI);
		  	ConnLog.open("GetSearchUserServlet");
		 	UserSyncHandler handler=new UserSyncHandler(conn);
		 	
		 	
		 	String name=request.getParameter("name");
			String userid=request.getParameter("usercode");
			String Otype=request.getParameter("operatetype");
			String Atype=request.getParameter("audittype");
			
			//��һ�ν���ҳ��ʱ,�Զ������״̬��Ϊ"�����"
			if(Atype==null){
				Atype="0";
			}
			
			
			userList=handler.GetSearchPerson(name,userid,Otype,Atype);
			
		     
		 	//����ÿ���û�����Ӧ����֯������
			
			/*
		    for(i=0;i<userList.size();i++){
		    	PersonSyncSearchVO person=(PersonSyncSearchVO)userList.get(i);
		    	String orgCode = person.getGroupid();
		    	String oldOrgCode = person.getOldorgcode();
		    	
		    	String oldOrgFullName = handler.getFullOrgName(oldOrgCode);
		    	String orgFullName = "";
		    	
		    	//������޸��û�������������֯code�б��
		    	//if(person.getOperatetype().equals("updUser")&&!orgCode.equals(oldOrgCode)&&!orgCode.equals("")){
		    	orgFullName = handler.getFullOrgName(orgCode); 
		    	//} 
		    	
		    	orgCnameList.add(i,orgFullName);
		    	oldOrgCnameList.add(i,oldOrgFullName);
		    }*/
		    
		    request.setAttribute("userList",userList);
		    //request.setAttribute("orgCnameList", orgCnameList);
		    //request.setAttribute("oldOrgCnameList", oldOrgCnameList);
		    
		    request.setAttribute("usercode", userid);
		    request.setAttribute("name", name);
		    request.setAttribute("operatetype", Otype);
		    request.setAttribute("audittype", Atype);
		    
		    
		   //this.forward(request,response,"/syncperson/syncaudit.jsp"); 
		    this.forward(request,response,"/syncperson/syncauditperson.jsp");
			
		} catch (Exception e) {
		 
		 	e.printStackTrace();
		}finally {
			if (conn != null)
				try {
					conn.close();
					ConnLog.close("GetSearchUserServlet");
				} catch (Exception e2) {
					e2.printStackTrace();
				}
		}
		
		
		
		
	}
	

}
