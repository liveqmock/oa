/*
 * Created on 2004-4-19
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package com.icss.oa.netoffice.setnetoffice.admin;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.j2ee.servlet.ServletBase;
import com.icss.j2ee.util.Globals;
import com.icss.oa.config.TypeConfig;
import com.icss.oa.netoffice.setnetoffice.handler.SetNetofficeHandler;
import com.icss.oa.netoffice.setnetoffice.vo.OfficeSetnetofficeVO;

/**
 * @author Administrator
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public class SetMemoServlet extends ServletBase {
	protected void performTask(
		HttpServletRequest request,
		HttpServletResponse response)
		throws ServletException, IOException {
		Connection conn = null;
		String memoid = request.getParameter("memo_id");
		Integer memoId = new Integer(memoid);
		String resMonths = request.getParameter("select_memo");
		Integer reserveFormonths = new Integer(resMonths);
		int hours = 0;
		Integer hourRemind = new Integer(hours);
		//System.out.println("ssssssssssssssssssss"+reserveFormonths);
		Integer type = new Integer(TypeConfig.MEMO_TYPE);
		OfficeSetnetofficeVO vo = new OfficeSetnetofficeVO();

		try {
			conn = getConnection(Globals.DATASOURCEJNDI);
			vo.setSetId(memoId);
			vo.setMonthsReserve(reserveFormonths);
			vo.setHoursRemind(hourRemind);
			vo.setSetType(type);
			System.out.println("ffffffffffffff");
			SetNetofficeHandler setHandler = new SetNetofficeHandler(conn);
			setHandler.setNetOffice(vo);
			System.out.println("Update successfully!!!!");

			this.forward(
				request,
				response,
				"/servlet/ShowSetNetofficePageServlet");
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
