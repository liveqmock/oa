package com.icss.oa.bbs.user.control;

import com.icss.common.log.ConnLog;
import com.icss.j2ee.services.DBConnectionLocator;
import com.icss.j2ee.servlet.ServletBase;
import com.icss.oa.bbs.handler.BBSHandler;
import com.icss.oa.bbs.handler.BoardArticleUpdate;
import com.icss.oa.intendwork.handler.IntendWork;
import com.icss.resourceone.sdk.framework.Context;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DelArticleServlet extends ServletBase {

	public DelArticleServlet() {
	}

	protected void performTask(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Connection conn = null;
		Integer articleId = new Integer(request.getParameter("articleId"));
		String boardId = request.getParameter("boardId");
		String topicId = request.getParameter("topicId");
		String topicFlag = request.getParameter("topicFlag");
		String auditFlag = request.getParameter("auditFlag");
		try {
			conn = DBConnectionLocator.getInstance().getConnection(
					"java:comp/env/ResourceOne/DataSource");
			conn.setAutoCommit(false);
			ConnLog.open("DelArticleServlet");
			BBSHandler handler = new BBSHandler(conn);
			//����ɾ��������
			Context ctx = Context.getInstance();
			String uuid = ctx.getCurrentPersonUuid();
			handler.insertDel(uuid, articleId);
			
			BoardArticleUpdate update = new BoardArticleUpdate(conn);
			if (topicFlag.equals("0")) { //����ǻ���
				System.out.println("come here");
				handler.delArticle(articleId);
				IntendWork intendHandler = new IntendWork(conn);
				intendHandler.deleteWork("oa_bbs", articleId.toString());
				//ʹ�����Ļ�����-1
				update.updateArticleByDel(new Integer(topicId), -1);
				//ʹ����������-1
				update.updateBoard(new Integer(boardId), 0, -1);
				//���ɾ���������,����������Ϣ
				update.adjustArticle(new Integer(topicId));
				
				conn.commit();
				//�ص�����ҳ��(ֻ��������ҳ��ɾ������)
				response.sendRedirect("ShowArticleServlet?boardId=" + boardId
						+ "&topicId=" + topicId + "&hitFlag=0");
			} else if (topicFlag.equals("1") && auditFlag == null) {//���������,�Ҳ��������״̬ɾ��
				//System.out.println("come here1");
				String articleDelete = request.getParameter("articleDelete");
				handler.delTopic(articleId);
				//�õ����������л�����(��������)
				List list = handler.getArticleList(articleId, "1");

				//ʹ����������-1,������-���л�����
				update.updateBoard(new Integer(boardId), -1, -list.size());
				//���ɾ���������,���°����Ϣ
				update.adjustBoard(new Integer(boardId));

				conn.commit();
				
				//�ص�����״̬
				if ("1".equals(articleDelete))
					response.sendRedirect("ShowTopicServlet?boardId=" + boardId
							+ "&primeFlag=0");
				else
					response.sendRedirect("ShowTopicServlet?boardId=" + boardId
							+ "&primeFlag=0" + "&manageFlag=1");
			} else if (topicFlag.equals("1") && auditFlag.equals("1")) {//���������,�������״̬ɾ��
				//System.out.println("come here2");
//				String articleDelete = request.getParameter("articleDelete");
				handler.delTopic(articleId);
				List list = handler.getArticleList(articleId, "1");

				update.updateBoard(new Integer(boardId), -1, -list.size());
				update.adjustBoard(new Integer(boardId));

//				
//				if ("1".equals(articleDelete))
//					response.sendRedirect("ShowTopicServlet?boardId=" + boardId
//							+ "&primeFlag=0");
//				else
				conn.commit();
					response.sendRedirect("ShowUnauditServlet?boardId="
							+ boardId);
			}
		} catch (Exception e) {
			e.printStackTrace();
			handleError(e);
		} finally {
			try {
				if (conn != null) {
					conn.close();
					ConnLog.close("DelArticleServlet");
				}
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}
}