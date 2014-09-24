/*
 * Created on 2004-12-18
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.icss.oa.bbs.admin.control;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.common.log.ConnLog;
import com.icss.j2ee.services.ServiceLocatorException;
import com.icss.j2ee.servlet.ServletBase;
import com.icss.j2ee.util.Globals;
import com.icss.oa.bbs.handler.BBSHandler;
import com.icss.oa.bbs.vo.BbsArticleVO;

/**
 * @author firecoral
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class MergeBoardServlet extends ServletBase{  

	/* (non-Javadoc)
	 * @see com.icss.j2ee.servlet.ServletBase#performTask(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	protected void performTask(HttpServletRequest request, HttpServletResponse response) 
		throws ServletException, IOException {

		Connection conn = null;
		Integer boardId1 = new Integer(request.getParameter("boardA"));
		Integer boardId2 = new Integer(request.getParameter("boardB"));
		
		try {
			conn = this.getConnection(Globals.DATASOURCEJNDI);
			ConnLog.open("MergeBoardServlet");
			BBSHandler handler = new BBSHandler(conn);
			BbsArticleVO bVO = null;
			List articlelist = handler.getArticleByBoardId(boardId1);
			if(articlelist!=null&&articlelist.size()>0){
				Iterator Itr = articlelist.iterator();
				while(Itr.hasNext()){
					bVO = (BbsArticleVO)Itr.next();
					Integer articleId = bVO.getArticleid();
					handler.updateArticleWhenMergeBoard(articleId,boardId2);
				}
			}
			
			handler.delBoard(boardId1);
			this.forward(request,response,"/bbs/mergeClose.jsp");
			
		} catch (ServiceLocatorException e) {		
			e.printStackTrace();
			
		} finally{
			if(conn!=null){
				try {
					conn.close();
					ConnLog.close("MergeBoardServlet");
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		}
		
	}

}
