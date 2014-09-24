package com.icss.oa.sync.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.common.log.ConnLog;
import com.icss.j2ee.servlet.ServletBase;
import com.icss.oa.hr.vo.HRPersonTempVO;
import com.icss.oa.sync.handler.InitPersonHandler;

public class InitAllPersonServlet extends ServletBase {

	protected void performTask(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		Connection conn = null;
		String JNDI = "jdbc/ROEEE";
		try {

			conn = this.getConnection(JNDI);
			ConnLog.open("InitAllPersonServlet");
			
			List faultlist = new ArrayList();
			InitPersonHandler handler = new InitPersonHandler(conn);
			//取得所有未初始化的人员
			List personlist = handler.getAllPerson();
			
			Iterator it = personlist.iterator();
			
			while(it.hasNext()){
				HRPersonTempVO vo = (HRPersonTempVO) it.next();
				
				boolean flag =handler.newperson(vo);
				if(flag){
					handler.updFlag(vo.getHrid());
				}else{
					faultlist.add(vo);
				}
			}
			
			request.setAttribute("faultlist1", faultlist);
			this.forward(request, response, "/syncperson/inithrid.jsp");
			
		} catch (Exception e) {

			PrintWriter out = response.getWriter();
			out.println(e);
			e.printStackTrace();

		} finally {
			if (conn != null)
				try {
					conn.close();
					ConnLog.close("InitAllPersonServlet");
				} catch (Exception e2) {
					e2.printStackTrace();
				}
		}

	}

}
