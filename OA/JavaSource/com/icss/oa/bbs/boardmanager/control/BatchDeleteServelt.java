/*
 * Created on 2004-4-19
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.icss.oa.bbs.boardmanager.control;

import java.io.IOException;
import java.sql.Connection;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.common.log.ConnLog;
import com.icss.j2ee.services.DBConnectionLocator;
import com.icss.j2ee.servlet.ServletBase;
import com.icss.j2ee.util.Globals;
import com.icss.oa.bbs.handler.BBSHandler;
import com.icss.oa.bbs.handler.BoardArticleUpdate;
import com.icss.oa.bbs.vo.BbsArticleVO;
import com.icss.oa.util.*;
/**
 * @author Administrator
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class BatchDeleteServelt extends ServletBase {

	protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection conn = null;
		String includePrime = request.getParameter("includePrime");
		Integer boardId = new Integer(request.getParameter("boardId"));
		String sStartTime = request.getParameter("startTime");
		String sEndTime = request.getParameter("endTime");

		try {
			conn = DBConnectionLocator.getInstance().getConnection(Globals.DATASOURCEJNDI);
			ConnLog.open("BatchDeleteServelt");
			BoardArticleUpdate update = new BoardArticleUpdate(conn);
			long startTime = CommUtil.getLongByTime(sStartTime);
			long endTime = CommUtil.getLongByTime(sEndTime);

			BBSHandler handler = new BBSHandler(conn);
			StringBuffer sql = new StringBuffer();
			sql.append(" EMITTIME between " + startTime + " and " + endTime + " and BOARDID = " + boardId + " ");
			//得到主贴数
			int topicNum = handler.getArticleNum(sql.toString() + " and TOPIC='1' ");
			//得到帖子数
			int articleNum = handler.getArticleNum(sql.toString());
			//得到帖子:在批量删除过程中主贴没删除,回帖被删除的帖子
			List articleList = update.getArticleOutDelete(boardId, startTime, endTime);
			//如果不包含精华贴
			if (includePrime.equals("no")) {
				sql.append(" and PRIME <> 1 ");
			}
			//批量删除
			handler.batchDelArticle(sql.toString());

			//更新板块信息
			update.updateBoard(boardId, -topicNum, -articleNum);
			//更新板块最后发贴记录
			update.adjustBoard(boardId);

			//校正主贴的最后发贴
			if (articleList != null) {
				Iterator it = articleList.iterator();
				while (it.hasNext()) {
					BbsArticleVO vo = (BbsArticleVO) it.next();
					update.adjustArticle(vo.getArticleid());
				}
			}

			response.sendRedirect("ShowTopicServlet?boardId=" + boardId + "&primeFlag=0" + "&manageFlag=1");

		} catch (Exception e) {
			e.printStackTrace();
			handleError(e);

		} finally {
			try {
				if (conn != null){
					conn.close();
					ConnLog.close("BatchDeleteServelt");
				}
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}

	}

}
