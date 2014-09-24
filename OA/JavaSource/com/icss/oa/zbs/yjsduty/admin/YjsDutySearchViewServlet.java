package com.icss.oa.zbs.yjsduty.admin;

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
import com.icss.oa.zbs.yjsduty.handler.YjsDutyHanler;
import com.icss.oa.zbs.yjsduty.handler.YjsWorkInfoHandler;
import com.icss.oa.zbs.yjsduty.vo.TbYjsWorkinfomainVO;
import com.icss.oa.zbs.duty.vo.TbZbsWorkinfomainVO;

/**
 * @version 1.0
 * @author liupei
 */
public class YjsDutySearchViewServlet extends ServletBase {

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
			ConnLog.open("YjsDutySearchViewServlet");
			conn = DBConnectionLocator.getInstance().getConnection(
					Globals.DATASOURCEJNDI);
			YjsWorkInfoHandler handler = new YjsWorkInfoHandler(conn);
			String id = (String) request.getParameter("id");
			// System.err.println("id=" + id);
			String keyword = (String) request.getParameter("keyword");
			// System.err.println("keyword=" + keyword);
			TbYjsWorkinfomainVO vo = handler.getMainDutyById(id);
			List list = handler.getDutyContentInfoByClause(" wim_id=" + id);

			YjsDutyHanler handler1 = new YjsDutyHanler(conn);
			HashMap map = handler1.getDutyName();
			request.setAttribute("map", map);
			request.setAttribute("list", list);
			request.setAttribute("keyword", keyword);
			request.setAttribute("vo", vo);
			super.forward(request, response,
					"/zbs/yjs_duty/dutySearchResult.jsp");

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
					ConnLog.close("YjsDutySearchViewServlet");
				}
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}

}
