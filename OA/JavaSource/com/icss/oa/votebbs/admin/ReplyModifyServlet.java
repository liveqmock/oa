/*
 * 创建日期 2007-3-13
 *
 * 更改所生成文件模板为
 * 窗口 > 首选项 > Java > 代码生成 > 代码和注释
 */
package com.icss.oa.votebbs.admin;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.j2ee.dao.DAOFactory;
import com.icss.j2ee.services.ServiceLocatorException;
import com.icss.j2ee.servlet.ServletBase;
import com.icss.oa.votebbs.dao.BbsReplyDAO;
import com.icss.oa.votebbs.handler.BbsVoteHandler;
import com.icss.oa.votebbs.vo.BbsReplyVO;

/**
 * @author 王苏
 *
 * 更改所生成类型注释的模板为
 * 窗口 > 首选项 > Java > 代码生成 > 代码和注释
 */
public class ReplyModifyServlet extends ServletBase {

	/* （非 Javadoc）
	 * @see com.icss.j2ee.servlet.ServletBase#performTask(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO 自动生成方法存根
		Connection conn = null;
		try {
			conn = super.getConnection(com.icss.j2ee.util.Globals.DATASOURCEJNDI);

			String mainId = request.getParameter("mainId") == null ? "1" : request.getParameter("mainId");
			String replyId = request.getParameter("repid") == null ? "0" : request.getParameter("repid");
			String userId = (String) request.getParameter("userId") == null ? "0" : (String) request.getParameter("userId");
			
			String title = (String)request.getParameter("replytitle");
			String context = (String)request.getParameter("replycontent");
			
			BbsVoteHandler handler = new BbsVoteHandler(conn);
			BbsReplyVO vo = handler.getReplyVO(replyId);
			
			vo.setReTitle(title);
			vo.setReContext(context);
			
			handler.updateReply(vo);
			
			String dist = "/bbsvote/replyModify.jsp?mainId="+mainId+"&userId="+userId+"&repid="+replyId;
						
			forward(request,response,dist);			
			
		} catch (ServiceLocatorException e) {
		// TODO 自动生成 catch 块
		e.printStackTrace();
	}
	}

}
