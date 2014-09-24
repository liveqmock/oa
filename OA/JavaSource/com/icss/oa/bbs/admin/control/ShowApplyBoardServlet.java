/*
 * Created on 2004-12-21
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.icss.oa.bbs.admin.control;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.common.log.ConnLog;
import com.icss.j2ee.services.ServiceLocatorException;
import com.icss.j2ee.servlet.ServletBase;
import com.icss.j2ee.util.Globals;
import com.icss.oa.bbs.handler.BBSHandler;
import com.icss.oa.bbs.handler.UserMsgHandler;
import com.icss.oa.bbs.vo.BbsBoardVO;
import com.icss.resourceone.sdk.framework.EntityException;
import com.icss.resourceone.sdk.framework.EntityManager;

/**
 * @author firecoral
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ShowApplyBoardServlet extends ServletBase{

	/* (non-Javadoc)
	 * @see com.icss.j2ee.servlet.ServletBase#performTask(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	protected void performTask(HttpServletRequest request, HttpServletResponse response) 
		throws ServletException, IOException {
		
		Connection conn = null;
		try {
			conn = this.getConnection(Globals.DATASOURCEJNDI);
			ConnLog.open("ShowApplyBoardServlet");
			BBSHandler handler = new BBSHandler(conn);
			List applylist = handler.getApplyBoardlist();
			List applypersonNamelist = new ArrayList();
			if(applylist!=null&&applylist.size()>0){
				String applypersonuuid = "";
				String cnname = "";
				Iterator Itr = applylist.iterator();
				while(Itr.hasNext()){
					BbsBoardVO vo = (BbsBoardVO)Itr.next();
					applypersonuuid = vo.getApplypersonuuid();
					EntityManager entitymanger = EntityManager.getInstance();
					cnname = entitymanger.findPersonByUuid(applypersonuuid).getFullName();
					applypersonNamelist.add(cnname);
				}

			}
			UserMsgHandler uhandler = new UserMsgHandler(conn);
			
			request.setAttribute("curUUid",uhandler.getUserId());
			request.setAttribute("applylist",applylist);
			request.setAttribute("applypersonNamelist",applypersonNamelist);
			this.forward(request,response,"/bbs/applyBoardlist.jsp");
			
		} catch (ServiceLocatorException e) {		
			e.printStackTrace();
		} catch (EntityException e) {
			e.printStackTrace();
		}finally{
			if(conn!=null){
				try {
					conn.close();
					ConnLog.close("ShowApplyBoardServlet");
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		}
		
	}

}
