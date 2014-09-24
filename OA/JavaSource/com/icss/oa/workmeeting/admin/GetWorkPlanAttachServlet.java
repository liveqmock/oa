/*
 * Created on 2004-12-31
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.icss.oa.workmeeting.admin;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.common.log.ConnLog;
import com.icss.j2ee.dao.DAOFactory;
import com.icss.j2ee.servlet.ServletBase;
import com.icss.j2ee.util.Globals;
import com.icss.oa.workmeeting.handler.Workinghandler;
import com.icss.oa.workmeeting.vo.Attachment1VO;

/**
 * @author sunchuanting
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public class GetWorkPlanAttachServlet extends ServletBase {
	/* (non-Javadoc)
	 * @see com.icss.j2ee.servlet.ServletBase#performTask(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	protected void performTask(
		HttpServletRequest request,
		HttpServletResponse response)
		throws ServletException, IOException {

			Connection conn=null;
			
			String id = request.getParameter("id");
			int read_id = -1;
			if(id!=null||"".equals(id)) read_id = new Integer(id).intValue();
			
			String filename = null;
			String filepath = null;
			File f=null;
			InputStream in = null;

			try{
				conn = this.getConnection(Globals.DATASOURCEJNDI);
				ConnLog.open("GetWorkPlanAttachServlet");
				Workinghandler handler=new Workinghandler(conn);
				DAOFactory factory=new DAOFactory(conn);
				Attachment1VO vo = handler.getWorkPlanAttach(new Integer(read_id));
				
				filename="/tmp/"+System.currentTimeMillis()+".doc";
				filepath=this.getServletContext().getRealPath(filename);
						
				f=new File(filepath);
				f.createNewFile();
				in = vo.getAttach();
				
				FileOutputStream fo=new FileOutputStream(filepath,true);
						while (in.available() > 0) {
					        byte[] b = new byte[10];
					        int nResult = in.read(b);
					        if (nResult == -1) break;
					        fo.write(b);
					    }
			   
				fo.close();
  
				response.sendRedirect(request.getContextPath()+filename);
				
				
			} catch (Exception e) {
				handleError(e);
			} finally {
				try {
					if (conn != null) {
						conn.close();
						ConnLog.close("GetWorkPlanAttachServlet");
					}
				} catch (SQLException sqle) {
					sqle.printStackTrace();
				}
			}
	}
}
