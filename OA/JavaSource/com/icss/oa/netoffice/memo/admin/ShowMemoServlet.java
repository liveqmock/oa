/*
 * Created on 2004-4-10
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package com.icss.oa.netoffice.memo.admin;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.common.log.ConnLog;
import com.icss.j2ee.servlet.ServletBase;
import com.icss.j2ee.util.Globals;
import com.icss.oa.netoffice.memo.handler.MemoHandler;
import com.icss.oa.util.CommUtil;


/**
 * @author Administrator
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public class ShowMemoServlet extends ServletBase{
	protected void performTask(HttpServletRequest request,
			HttpServletResponse response)
			throws ServletException, IOException{
		Connection conn = null;
		List list1 = new ArrayList();
		Integer months=null;
		long now=System.currentTimeMillis();
		String date=CommUtil.getTime(now,1);
		
	    String  page_num=request.getParameter("_page_num");
	    request.setAttribute("page_num",page_num);
		try{
		  long date1=CommUtil.getLongByTime(date);
		  long date2=date1+3600*24*1000;
		  conn = this.getConnection(Globals.DATASOURCEJNDI);
ConnLog.open("ShowMemoServlet");
		  MemoHandler memoHandler = new MemoHandler(conn);	
		  String personuuid=memoHandler.getUserId();
		  list1 = memoHandler.getListInTimeSegment(new Long(date1),new Long(date2),personuuid);
		  request.setAttribute("list",list1);
		  //取办公室设置的备忘录保留月份数目
//		  SetNetofficeHandler setHandler=new SetNetofficeHandler(conn);
//		  months=setHandler.getMonthsByType(TypeConfig.MEMO_TYPE);
//		  System.out.println("months is...............="+months);
		  this.forward(request, response, "/netoffice/memo/memo.jsp"); ///
	
		}catch(Exception e){
			System.out.println(e.toString());
			handleError(e);
		}finally {
			try {
				if (conn != null){
					conn.close();
					ConnLog.close("ShowMemoServlet");
				}
			} catch (SQLException e1) {
				
				e1.printStackTrace();
			}
		}
		
		
	}
	
}
