/*
 * Created on 2003-12-17
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */

package com.icss.oa.votebbs.admin;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.j2ee.services.DBConnectionLocator;
import com.icss.j2ee.servlet.ServletBase;
import com.icss.j2ee.util.Globals;
import com.icss.oa.addressbook.handler.AddressbookHandler;
import com.icss.oa.addressbook.vo.AddressbookFolderVO;
import com.icss.oa.log.handler.LogHandler;
import com.icss.oa.log.vo.LogSysVO;
import com.icss.oa.votebbs.handler.BbsVoteHandler;


/**
 *ɾ���ļ�
 * @author Administrator
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class DeleteMainArticalServlet extends ServletBase {

	protected void performTask(
		HttpServletRequest request,
		HttpServletResponse response)
		throws ServletException, IOException {
		Connection conn = null;
		

		String mainid = request.getParameter("mainid")==null?"-1":request.getParameter("mainid");
		System.out.println("++++++DeleteMainArticalServlet++++++++++mainid="+mainid);
		try {
			conn =
				DBConnectionLocator.getInstance().getConnection(
					Globals.DATASOURCEJNDI);
			
			BbsVoteHandler handler = new BbsVoteHandler(conn);
			//�ж��Ƿ�ӵ��ѡ����Ϣ
			if(handler.hasOptions(mainid)){
				System.out.println("�˱���ӵ���ӱ������¼");
				handler.deleteOptions(mainid);
			}
			//ɾ�����¼�¼
			handler.deleteArtical(mainid);
			 this.forward(request, response, "/servlet/ArticleManagerListServlet");
			//response.sendRedirect("ArticalOptionListServlet?mainid=" + mainid);

		} catch (Exception e) {
			e.printStackTrace();
			
		} finally {
			try {
				if (conn != null)
					conn.close();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}

	}
}
