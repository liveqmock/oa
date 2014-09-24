package com.icss.oa.hr.admin;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.common.log.ConnLog;
import com.icss.j2ee.servlet.ServletBase;
import com.icss.oa.hr.handler.HRPersonHandler;
import com.icss.oa.sync.vo.PersonVO;

public class test1 extends ServletBase {

	protected void performTask(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		Connection conn = null;
		String JNDI = "jdbc/ROEEE";
		try {
			conn = this.getConnection(JNDI);
		
			
			this.forward(request, response, "/syncperson/inithrid.jsp");
			
		} catch (Exception e) {
			e.printStackTrace();

		} 
		
	}
	
}