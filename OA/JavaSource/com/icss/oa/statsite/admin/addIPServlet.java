package com.icss.oa.statsite.admin;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.StringTokenizer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.common.log.ConnLog;
import com.icss.j2ee.servlet.ServletBase;
import com.icss.j2ee.util.Globals;
import com.icss.oa.statsite.handler.ipManagerHandler;
import com.icss.oa.statsite.vo.*;

public class addIPServlet extends ServletBase {

	
	protected void performTask(
		HttpServletRequest request,
		HttpServletResponse response)
		throws ServletException, IOException {

			Connection conn=null;
			String AdressName = request.getParameter("AdressName");
			String StartIP = request.getParameter("StartIP");
			String EndIP = request.getParameter("EndIP");
			String IPDescrptor = request.getParameter("IPDescrptor");
			
			StringTokenizer st_StartIP = new StringTokenizer(StartIP,".");
			boolean flag = false;
			StringBuffer sb_StartIP = new StringBuffer();
			while(st_StartIP.hasMoreTokens()){
				
				String sb1 =st_StartIP.nextToken();
				try{if(Integer.parseInt(sb1)>255){flag=true;}}
				catch(Exception e) {
					this.forward(request,response,"/statsite/error.jsp?errormsg=你输入的IP地址段中含有非数字或者其中含有大于255的部分或者请确认是否在英文状态下输入了IP分割符，请重新输入!");}
				int sbLength = sb1.length();
				switch (sbLength){
				  case 1:
				  	sb_StartIP.append("00");
				  	sb_StartIP.append(sb1);
				      	break;
				  case 2:
				  	sb_StartIP.append("0");
				  	sb_StartIP.append(sb1);
			      		break;  
				  case 3:
		      	    sb_StartIP.append(sb1);
		      			break; 
				  default: 
	      	  		flag=true;
	      	  		break;
				}
			}
			
			StringTokenizer st_EndIP = new StringTokenizer(EndIP,".");
			StringBuffer sb_EndIP = new StringBuffer();
			while(st_EndIP.hasMoreTokens()){
				
				String sb1 =st_EndIP.nextToken();
				int sbLength = sb1.length();
				try{if(Integer.parseInt(sb1)>255){flag=true;}}
				catch(Exception e) {
					this.forward(request,response,"/statsite/error.jsp?errormsg=你输入的IP地址段中含有非数字或者其中含有大于255的部分或者请确认是否在英文状态下输入了IP分割符，请重新输入!");}
				switch (sbLength){
				  case 1:
				  	sb_EndIP.append("00");
				  	sb_EndIP.append(sb1);
				      	break;
				  case 2:
				  	sb_EndIP.append("0");
				  	sb_EndIP.append(sb1);
			      		break;  
				  case 3:
				  	sb_EndIP.append(sb1);
		      			break;  
		      	  default: 
		      	  		flag=true;
		      	  		break;
				}
			}
					
			try {
				conn = this.getConnection(Globals.DATASOURCEJNDI);	
				ConnLog.open("addIPServlet");	
				ipManagerHandler handler = new ipManagerHandler(conn);
				StatSiteIpcontentVO vo = new StatSiteIpcontentVO();
				vo.setIpcontent(AdressName);
				String tem=null;
				
				if(new Long(sb_StartIP.toString()).longValue()>new Long(sb_EndIP.toString()).longValue())
				   {
					vo.setStartip(new Long(sb_EndIP.toString()));
					vo.setEndip(new Long(sb_StartIP.toString()));
					
				   }
				
				else{	
					vo.setStartip(new Long(sb_StartIP.toString()));
					vo.setEndip(new Long(sb_EndIP.toString()));
				}
				
				
				if((Long.parseLong(sb_StartIP.toString()))>255255255256L||(Long.parseLong(sb_StartIP.toString()))<1255255255L||flag)
				{
					//response.sendRedirect(request.getContextPath()+"/statsite/error.jsp?errormsg=你输入的IP地址有误，请从新输入!");
					this.forward(request,response,"/statsite/error.jsp?errormsg=你输入的IP地址有误，请重新输入!");
					}
				if((Long.parseLong(sb_EndIP.toString()))>255255255256L||(Long.parseLong(sb_EndIP.toString()))<1255255255L||flag)
				{
					//response.sendRedirect(request.getContextPath()+"/statsite/error.jsp?errormsg=你输入的IP地址有误，请从新输入!");
					this.forward(request,response,"/statsite/error.jsp?errormsg=你输入的IP地址有误，请重新输入!");
					}
			
				if(!"Flag".equals(handler.IsAdress(EndIP)))
							{this.forward(request,response,"/statsite/error.jsp?errormsg=你输入的IP段和"+handler.IsAdress(EndIP)+"的IP有重复，请重新输入!");}
	
				if(!"Flag".equals(handler.IsAdress(StartIP)))
						{this.forward(request,response,"/statsite/error.jsp?errormsg=你输入的IP段和"+handler.IsAdress(StartIP)+"的IP有重复，请从新输入!");}
				
				
				vo.setIpmeno(IPDescrptor);
				handler.add(vo);

			    response.sendRedirect("IPListServlet?_page_num="+request.getParameter("_page_num"));
				
			} catch (Exception e) {
				this.forward(request,response,"/statsite/error.jsp?errormsg=你输入的IP地址有误(请确认是否在英文状态下输入了IP分割符)，请重新输入!");
				e.printStackTrace();
			}
			catch (Throwable e1) {
				e1.printStackTrace();
			}finally {
				try {
					if (conn != null) {
						conn.close();
						ConnLog.close("addIPServlet");
					}
				} catch (SQLException sqle) {
					sqle.printStackTrace();
				}
			}

	}

}
