/*
 * �������� 2007-3-13
 *
 * �����������ļ�ģ��Ϊ
 * ���� > ��ѡ�� > Java > �������� > �����ע��
 */
package com.icss.oa.votebbs.admin;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.j2ee.services.ServiceLocatorException;
import com.icss.j2ee.servlet.ServletBase;
import com.icss.oa.votebbs.handler.BbsVoteHandler;

/**
 * @author ����
 *
 * ��������������ע�͵�ģ��Ϊ
 * ���� > ��ѡ�� > Java > �������� > �����ע��
 */
public class MainArticleListServlet extends ServletBase {

	/* ���� Javadoc��
	 * @see com.icss.j2ee.servlet.ServletBase#performTask(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO �Զ����ɷ������
		Connection conn = null;
		try {
			conn = super.getConnection(com.icss.j2ee.util.Globals.DATASOURCEJNDI);
			BbsVoteHandler handler = new BbsVoteHandler(conn);
//			System.err.println("���");
			List mainlist = handler.getMainListByClause(" STATUS='����'");
//			System.err.println("nihao:"+mainlist.size());
			request.setAttribute("mainlist",mainlist);
//			request.setAttribute("mainId", mainId);
			String dist = "/bbsvote/mainList.jsp";
			forward(request, response, dist);
			
		} catch (ServiceLocatorException e) {
			// TODO �Զ����� catch ��
			e.printStackTrace();
		}
		
	}

}
