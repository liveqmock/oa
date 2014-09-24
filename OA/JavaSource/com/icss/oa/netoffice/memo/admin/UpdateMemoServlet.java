/*
 * Created on 2004-4-12
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
/**
 * @author Administrator
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public class UpdateMemoServlet extends ServletBase {
	protected void performTask(
		HttpServletRequest request,
		HttpServletResponse response)
		throws ServletException, IOException {
		Connection conn = null;
		String id1 = request.getParameter("id1");
		Integer id = new Integer(id1);
		String subject = request.getParameter("subject");
		String date1 = request.getParameter("date1");
		Long date = new Long(date1);
		String content = request.getParameter("content");
		OfficeMemoVO mVO = new OfficeMemoVO();

		try {
			conn = getConnection(Globals.DATASOURCEJNDI);
			mVO.setMemoId(id);
			mVO.setMemoTime(date);
			mVO.setMemoHeadline(subject);
			mVO.setMemoContent(content);
			MemoHandler mHandler = new MemoHandler(conn);
			String personUUID = new String();
			personUUID = mHandler.getUserId();
			mVO.setMemoOwnerid(personUUID);
			mHandler.updateMemo(mVO);
			response.sendRedirect(
				request.getContextPath() + "/servlet/ShowMemoServlet");
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
