package com.icss.oa.folder.control;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.common.log.ConnLog;
import com.icss.j2ee.services.DBConnectionLocator;
import com.icss.j2ee.servlet.ServletBase;
import com.icss.j2ee.util.Globals;
import com.icss.oa.folder.handler.FolderHandler;

/**
 * @author Administrator
 * 
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class isSHARE extends ServletBase {

	private Integer folderID;

	private Connection conn = null;

	public boolean jj;

	public isSHARE(Integer folderid) throws ServletException, IOException {
		try {

			conn = DBConnectionLocator.getInstance().getConnection(
					Globals.DATASOURCEJNDI);
			ConnLog.open("isSHARE");
			this.folderID = folderid;

			FolderHandler handler = new FolderHandler(conn);
			jj = handler.isShare1(folderID);

		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			handleError(e);
		} finally {
			try {
				if (conn != null){
					conn.close();
					ConnLog.close("isSHARE");
				}
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}

	protected void performTask(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
	}
}