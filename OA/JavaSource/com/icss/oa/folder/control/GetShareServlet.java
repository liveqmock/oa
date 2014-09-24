/*
 * Created on 2004-4-26
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.icss.oa.folder.control;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.common.log.ConnLog;
import com.icss.j2ee.services.DBConnectionLocator;
import com.icss.j2ee.servlet.ServletBase;
import com.icss.j2ee.util.Globals;
import com.icss.oa.bbs.handler.UserMsgHandler;
import com.icss.oa.folder.handler.FolderHandler;

/**
 * @author Administrator
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class GetShareServlet extends ServletBase {

	protected void performTask(
		HttpServletRequest request,
		HttpServletResponse response)
		throws ServletException, IOException {
		
		Connection conn = null;
		Integer folderId =new Integer(request.getParameter("folderId"));
		
		try {
			conn =DBConnectionLocator.getInstance().getConnection(Globals.DATASOURCEJNDI);
			ConnLog.open("GetShareServlet");
			FolderHandler handler = new FolderHandler(conn);
			UserMsgHandler userMsgHandler = new UserMsgHandler(conn);
			List list=handler.getSharePeopleList(userMsgHandler.getUserId(),folderId);
		    List list1=handler.getFolderName(folderId);
		    List list2=handler.getShareList(folderId);	
		    
		    String accessright ="4";
		    
		    Integer kk = handler.getShareAccessID(userMsgHandler.getUserId(),folderId);
		    if(kk!=null){accessright = handler.getAccessRight(kk);}
			request.setAttribute("list",list);
			request.setAttribute("list1",list1);
			request.setAttribute("list2",list2);
			request.setAttribute("accessright",accessright);
			
			this.forward(request, response, "/mail/Share.jsp");

		} catch (Exception e) {
			handleError(e);
		} finally {
			try {
				if (conn != null){
					conn.close();
					ConnLog.close("GetShareServlet");
				}
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}

	}
}