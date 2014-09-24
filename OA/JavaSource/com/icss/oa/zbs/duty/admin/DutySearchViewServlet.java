package com.icss.oa.zbs.duty.admin;

import java.io.IOException;
import java.sql.Connection;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.common.log.ConnLog;
import com.icss.j2ee.dao.DAOException;
import com.icss.j2ee.services.DBConnectionLocator;
import com.icss.j2ee.services.DBConnectionLocatorException;
import com.icss.j2ee.servlet.ServletBase;
import com.icss.j2ee.util.Globals;
import com.icss.oa.zbs.duty.handler.DutyHanler;
import com.icss.oa.zbs.duty.handler.ZbsWorkInfoHandler;
import com.icss.oa.zbs.duty.vo.TbZbsWorkinfomainVO;

/**
 * @version 1.0
 * @author wangsu
 */
public class DutySearchViewServlet extends ServletBase {

	/**
	 * @see com.icss.j2ee.servlet.ServletBase#void
	 *      (javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse)
	 */
	public void performTask(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Connection conn = null;
		// List mainDutyList = new ArrayList();
		// System.err.println("成功1");
		try {
			ConnLog.open("DutySearchServlet");
			conn = DBConnectionLocator.getInstance().getConnection(
					Globals.DATASOURCEJNDI);
			ZbsWorkInfoHandler handler = new ZbsWorkInfoHandler(conn);
			String id = (String) request.getParameter("id");
			// System.err.println("id=" + id);
			String keyword = (String) request.getParameter("keyword");
			// System.err.println("keyword=" + keyword);
			TbZbsWorkinfomainVO vo = handler.getMainDutyById(id);
			List list = handler.getDutyContentInfoByClause(" wim_id=" + id);

			DutyHanler handler1 = new DutyHanler(conn);
			HashMap map = handler1.getDutyName();
			request.setAttribute("map", map);
			request.setAttribute("list", list);
			request.setAttribute("keyword", keyword);
			request.setAttribute("vo", vo);
			super.forward(request, response,
					"/zbs/zbs_duty/dutySearchResult.jsp");

		} catch (DBConnectionLocatorException e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		} catch (DAOException e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		} finally {
			try {
				if (conn != null) {
					conn.close();
					ConnLog.close("DutySearchServlet");
				}
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}

}
