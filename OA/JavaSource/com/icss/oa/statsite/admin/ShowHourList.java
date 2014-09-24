package com.icss.oa.statsite.admin;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.common.log.ConnLog;
import com.icss.j2ee.servlet.ServletBase;
import com.icss.j2ee.util.Globals;
import com.icss.oa.statsite.handler.StatSiteHandler;
import com.icss.oa.statsite.vo.StatSiteDate1VO;

public class ShowHourList extends ServletBase{
	protected void performTask(HttpServletRequest request,
			HttpServletResponse response)
			throws ServletException, IOException{
		Connection conn = null;
		List list = new ArrayList();
		Integer months=null;
		try{
		  conn = this.getConnection(Globals.DATASOURCEJNDI);
		  ConnLog.open("ShowHourList");
		  StatSiteHandler logHandler = new StatSiteHandler(conn);
		  
		  String time = request.getParameter("Time");
		  
		  list = logHandler.getHourList(logHandler.getLongByTime(time));

		  long count=0; 
		  if(list!=null){
		  	Iterator it = list.iterator();
		  	if(it!=null){
		  		while(it.hasNext()){
		  			StatSiteDate1VO  vo1 =(	StatSiteDate1VO) it.next();
		  			count+=vo1.getH0().longValue();
		  			count+=vo1.getH1().longValue();
		  			count+=vo1.getH2().longValue();
		  			count+=vo1.getH3().longValue();
		  			count+=vo1.getH4().longValue();
		  			count+=vo1.getH5().longValue();
		  			count+=vo1.getH6().longValue();
		  			count+=vo1.getH7().longValue();
		  			count+=vo1.getH8().longValue();
		  			count+=vo1.getH9().longValue();
		  			count+=vo1.getH10().longValue();
		  			count+=vo1.getH11().longValue();
		  			count+=vo1.getH12().longValue();
		  			count+=vo1.getH13().longValue();
		  			count+=vo1.getH14().longValue();
		  			count+=vo1.getH15().longValue();
		  			count+=vo1.getH16().longValue();
		  			count+=vo1.getH17().longValue();
		  			count+=vo1.getH18().longValue();
		  			count+=vo1.getH19().longValue();
		  			count+=vo1.getH20().longValue();
		  			count+=vo1.getH21().longValue();
		  			count+=vo1.getH22().longValue();
		  			count+=vo1.getH23().longValue();
		  			}}}	
		 
		  request.setAttribute("list",list);
		  request.setAttribute("time",time);
		  request.setAttribute("count",new Long(count));
		  
		this.forward(request, response, "/statsite/showSiteListHour.jsp");
		}
		catch(Exception e){
			System.out.println(e.toString());
			handleError(e);
		}finally {
			try {
				if (conn != null){
					conn.close();
					ConnLog.close("ShowHourList");
				}
			} catch (SQLException e1) {
				
				e1.printStackTrace();
			}
		}
	  	
		
	}
	
}
