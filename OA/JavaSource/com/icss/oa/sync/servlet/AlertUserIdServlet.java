package com.icss.oa.sync.servlet;
 

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

import com.icss.common.log.ConnLog;
import com.icss.j2ee.servlet.ServletBase;
import com.icss.oa.sync.handler.UserSyncHandler;

/**
 * @author ������
 *
 * ���ݸ���������������ͬ���û�����List���ظ�ҳ��
 *  
 */
 


public class AlertUserIdServlet extends ServletBase{
	
	 
	protected void performTask(HttpServletRequest request, HttpServletResponse response) 
	throws ServletException, IOException {
		
		Connection conn = null;
		String JNDI="jdbc/ROEEE";
	 
		int i;
		PrintWriter out = response.getWriter();
		String userid=null;
		 try{
			
			conn = this.getConnection(JNDI);
		  	ConnLog.open("AlertUserIdServlet");
		 	UserSyncHandler handler=new UserSyncHandler(conn);
		 	String temp=request.getParameter("operateid");
		 	userid=request.getParameter("usercode");
		 	boolean result=false;
		 	if(temp==null||userid==null){
		 		return;
		 	}
		 	if(!StringUtils.isAsciiPrintable(userid)){
		 		out.println("<script>alert('�û����к��зǷ��ַ�')</script>");
		 		return;
		 	}
		 	if(handler.UserIsExist(userid)){
		 		out.println("<script>alert('�û���������')</script>");
		 		return;
		 	}
		 	
		 	if(!temp.equals("")){
		 		String operateid=temp;
		 		result=handler.AlertUserId(operateid, userid);
		 		 
		 	}
		 	if(result){
		 		out.println("<script>alert('�����ɹ�');window.opener.location.reload();window.close();</script>");
		 	 }
		 	else{
		 		 out.println("<script>alert('�������ɹ�')</script>");
		 	}
		 
		} catch (Exception e) {
		   out.println("<script>alert('�������ɹ�')</script>");
			out.println(e);
		 	e.printStackTrace();
		}
		 finally {
			if (conn != null)
				try {
					conn.close();
					ConnLog.close("AlertUserIdServlet");
				} catch (Exception e2) {
					e2.printStackTrace();
				}
				out.println("<script>history.back()</script>");
				// this.forward(request,response,"/syncperson/edituser.jsp?result=login&LTPAToken=d2VibWFzdGVyKjEyMzQ1Njc4OTAxMjM0NTY3ODkwMTIzNDU2Nzg5MDEyMzQ1Njc4OTAxMjM0NTY3ODkwMTIzNDU2Nzg5MDEyMzQqMSpTdHJpbmc=&operateid="+userid);
		}
		 
	}
	

}
