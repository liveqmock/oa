/*
 * Created on 2004-5-10
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.icss.oa.filetransfer.admin;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.j2ee.servlet.ServletBase;
import com.icss.j2ee.util.Globals;
import com.icss.oa.filetransfer.handler.FiletransferSetHandler;
import com.icss.oa.filetransfer.vo.FiletransferSetVO;
import com.icss.resourceone.common.logininfo.UserInfo;
import com.icss.resourceone.sdk.framework.Context;

/**
 * @author Administrator
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class SetAttributeServlet extends ServletBase {

	protected void performTask(
		HttpServletRequest request,
		HttpServletResponse response)
		throws ServletException, IOException {
		Connection conn = null;

		try {
			conn = getConnection(Globals.DATASOURCEJNDI);

			//			修改邮箱属性
			if (request.getParameter("id") != null) {
				Integer id = Integer.valueOf(request.getParameter("id"));
				Integer per = Integer.valueOf(request.getParameter("per"));
				String rule = request.getParameter("rule");
				//				String name = request.getParameter("name");

				FiletransferSetHandler handler =
					new FiletransferSetHandler(conn);
				List filesetlist = handler.getByid(id);
				Iterator result = filesetlist.iterator();
				FiletransferSetVO filetransetvo = null;
				if (result.hasNext()) {
					filetransetvo = (FiletransferSetVO) result.next();
				}
				FiletransferSetVO vo = new FiletransferSetVO();
				vo.setId(id);
				vo.setFsSize(filetransetvo.getFsSize());
				vo.setFsPer(per);
				vo.setFsRule(rule);
				vo.setFsUid(filetransetvo.getFsUid());
				vo.setFsUuid(filetransetvo.getFsUuid());
				vo.setFsDeltag(filetransetvo.getFsDeltag());
				handler.alter(vo);
			}

			Context ctx = Context.getInstance();
			UserInfo user = ctx.getCurrentLoginInfo();
			//依邮箱设置表，得到准确的邮箱用户名，用来构造邮箱地址
			FiletransferSetHandler ftsHandler =
				new FiletransferSetHandler(conn);
			String username = ftsHandler.getUserid(user.getPersonUuid());
			if (username == null) {
				this.forward(request, response, "/filetransfer/noMailBox.jsp");
				return;
			}
			//			根据uid得到用户邮件信息
			List list = ftsHandler.getByUid(username);
			request.setAttribute("list", list);

			this.forward(request, response, "/filetransfer/setattribute.jsp");

		} catch (Exception e) {
			e.printStackTrace();
			handleError(e);

		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e1) {
				throw new RuntimeException("数据库连接关闭错误");
			}
		}
	}
}
