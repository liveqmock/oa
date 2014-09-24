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

public class updateIPServlet extends ServletBase {

	
	protected void performTask(
		HttpServletRequest request,
		HttpServletResponse response)
		throws ServletException, IOException {

			Connection conn=null;
			Integer ipId = new Integer(request.getParameter("ipId"));
			String AdressName = request.getParameter("AdressName");
			String StartIP =request.getParameter("StartIP");
			String EndIP = request.getParameter("EndIP");
			String IPDescrptor = request.getParameter("IPDescrptor");
			StringTokenizer st_StartIP = new StringTokenizer(StartIP,".");
			StringBuffer sb_StartIP = new StringBuffer();
			while(st_StartIP.hasMoreTokens()){
				
				String sb1 =st_StartIP.nextToken();
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
				}
			}
			
			StringTokenizer st_EndIP = new StringTokenizer(EndIP,".");
			StringBuffer sb_EndIP = new StringBuffer();
			while(st_EndIP.hasMoreTokens()){
				
				String sb1 =st_EndIP.nextToken();
				int sbLength = sb1.length();
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
				}
			}
					
			try {
				conn = this.getConnection(Globals.DATASOURCEJNDI);		
				ConnLog.open("updateIPServlet");
				ipManagerHandler handler = new ipManagerHandler(conn);
				StatSiteIpcontentVO vo = new StatSiteIpcontentVO();
				vo.setIpcontent(AdressName);
				if(new Long(sb_StartIP.toString()).longValue()>new Long(sb_EndIP.toString()).longValue())
				   {
					vo.setStartip(new Long(sb_EndIP.toString()));
					vo.setEndip(new Long(sb_StartIP.toString()));}
				else{	
					vo.setStartip(new Long(sb_StartIP.toString()));
					vo.setEndip(new Long(sb_EndIP.toString()));
				}
				vo.setIpmeno(IPDescrptor);
				vo.setId(ipId);
				
				handler.update(vo);

				response.sendRedirect("IPListServlet?_page_num="+request.getParameter("_page_num"));
				
			} catch (Exception e) {
				handleError(e);
			} finally {
				try {
					if (conn != null) {
						conn.close();
						ConnLog.close("updateIPServlet");
					}
				} catch (SQLException sqle) {
				}
			}

	}

}
