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

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.j2ee.servlet.ServletBase;
import com.icss.j2ee.util.Globals;
import com.icss.oa.netoffice.memo.handler.MemoHandler;
import com.icss.oa.netoffice.memo.vo.OfficeMemoVO;
import com.icss.oa.util.CommUtil;

/**
 * @author Administrator
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public class AddMemoServlet extends ServletBase{
	protected void performTask(HttpServletRequest request,HttpServletResponse response) 
    throws ServletException, IOException{
		
		Connection conn = null;
		String subject = request.getParameter("subject");
		String content_unformat = request.getParameter("content");
		String date1 = "";
		String xx = request.getParameter("startTime1");
		if("".equals(request.getParameter("startTime1"))||request.getParameter("startTime1")==null){
			date1 = request.getParameter("startTime2");
		}else{
			date1 = request.getParameter("startTime1");
		}
		String hour = request.getParameter("hour_m");
		String minute = request.getParameter("minu_m");
				
		OfficeMemoVO mVO = new OfficeMemoVO();
		
		
		try {
			conn = getConnection(Globals.DATASOURCEJNDI);
			long time=CommUtil.getLongByTime(date1)+Long.parseLong(hour)*3600000+Long.parseLong(minute)*60000;
			Long date=new Long(time);
			String content=CommUtil.formathtm(content_unformat);
			mVO.setMemoTime(date);
			mVO.setMemoHeadline(subject);
			mVO.setMemoContent(content);
						
			MemoHandler mHandler = new MemoHandler(conn);
			String personUUID=new String();
			personUUID=mHandler.getUserId();
			mVO.setMemoOwnerid(personUUID);
			mHandler.addMemo(mVO);
			
			response.sendRedirect(request.getContextPath()+"/servlet/ShowMemoServlet");
		} catch (Exception e) {
			System.out.println(e.toString());
			handleError(e);
		} finally {
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}

	}
}
