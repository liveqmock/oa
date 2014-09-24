package com.icss.oa.zbs.xtsduty.admin;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.common.log.ConnLog;
import com.icss.j2ee.dao.DAOException;
import com.icss.j2ee.services.DBConnectionLocator;
import com.icss.j2ee.services.DBConnectionLocatorException;
import com.icss.j2ee.servlet.ServletBase;
import com.icss.j2ee.util.Globals;
import com.icss.oa.zbs.xtsduty.handler.XtsWorkInfoHandler;
import com.icss.oa.zbs.xtsduty.vo.TbXtsWorkinfomainVO;

/**
 * @version 1.0
 * @author liupei
 */
public class XtsDutySearchServlet extends ServletBase {

	/**
	 * @see com.icss.j2ee.servlet.ServletBase#void
	 *      (javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse)
	 */
	public void performTask(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Connection conn = null;
		List mainDutyList = new ArrayList();
		// System.err.println("成功1");
		try {
			ConnLog.open("YjsDutySearchServlet");
			conn = DBConnectionLocator.getInstance().getConnection(
					Globals.DATASOURCEJNDI);
			XtsWorkInfoHandler handler = new XtsWorkInfoHandler(conn);
			// System.err.println("成功2");
			String SearchFlag = (String) request.getParameter("SearchFlag");
			String keyword = "";
			System.err.println("SearchFlag=" + SearchFlag);
			if ("1".equals(SearchFlag)) {
				String fromdate = request.getParameter("fromdate") == null ? "1901-01-01"
						: request.getParameter("fromdate");
				if ("".equals(fromdate)) {
					fromdate = "1901-01-01";
				}
				String todate = request.getParameter("todate") == null ? "2099-12-12"
						: request.getParameter("todate");
				if ("".equals(todate)) {
					todate = "2099-12-12";
				}
				//String selectType = request.getParameter("selectType") == null ? "1"
						//: request.getParameter("selectType");
				String dutyName = request.getParameter("dutyName") == null ? ""
						: request.getParameter("dutyName");

				keyword = (String) request.getParameter("dutykeyword") == null ? ""
						: (String) request.getParameter("dutykeyword");
				System.err.println("keyword=" + keyword);
				
				if("".equals(keyword)){
				mainDutyList = handler
						.getMainDutyListByClause("1=1 "								
								+ " AND (WIT_LEADER like '%"
								+ dutyName
								+ "%' OR WIT_SECRET like '%"
								+ dutyName
								+ "%') AND WIT_DATE>to_date('"
								+ fromdate
								+ "','yyyy-mm-dd') AND WIT_DATE<to_date('"
								+ todate
								+ "','yyyy-mm-dd') "
								 );
				
				
				}else{
					mainDutyList = handler
					.getMainDutyListByClause("1=1 "								
							+ " AND (WIT_LEADER like '%"
							+ dutyName
							+ "%' OR WIT_SECRET like '%"
							+ dutyName
							+ "%') AND WIT_DATE>to_date('"
							+ fromdate
							+ "','yyyy-mm-dd') AND WIT_DATE<to_date('"
							+ todate
							+ "','yyyy-mm-dd') AND (WIM_ID IN (Select WIM_ID FROM TB_YJS_WORKINFO WHERE WIT_CONTENT like '%"
							+ keyword + "%'))");
					
					
				}
				
				

			} else {
				mainDutyList = handler.getMainDutyListByClause("1=1");
			}
			Map map = new HashMap();
			for (int i = 0; i < mainDutyList.size(); i++) {
				TbXtsWorkinfomainVO vo = (TbXtsWorkinfomainVO) mainDutyList
						.get(i);
				List list = new ArrayList();
				if (!"".equals(keyword)) {
					list = handler.getDutyContentInfoByClause(" Wim_id="
							+ vo.getWimId() + " AND WIT_CONTENT like '%"
							+ keyword + "%'");
					request.setAttribute("keyword", keyword);
				} else {
					list = handler.getDutyContentInfoByClause(" Wim_id="
							+ vo.getWimId());
				}
				map.put(vo.getWimId(), list);
			}

			request.setAttribute("map", map);
			// System.err.println("成功3");
			// UserMsgHandler userMsghandler = new UserMsgHandler(conn);
			// String userId = userMsghandler.getUserId();

			// request.setAttribute("userId", userId);
			request.setAttribute("mainDutyList", mainDutyList);
			// System.err.println("成功4");
			this.forward(request, response, "/zbs/xts_duty/dutySearch.jsp");

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
					ConnLog.close("XtsDutySearchServlet");
				}
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}

}
