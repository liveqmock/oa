
 /* Created on 2003-12-17
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.icss.oa.log.admin;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.common.log.ConnLog;
import com.icss.j2ee.services.DBConnectionLocator;
import com.icss.j2ee.servlet.ServletBase;
import com.icss.j2ee.servlet.download.DownloadResponse;
import com.icss.j2ee.util.Globals;
import com.icss.oa.log.handler.LogHandler;
import com.icss.oa.log.vo.LogAccessoryVO;
/**
 * @author Administrator
 * 显示文件
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class DownloadLogAttachServlet extends ServletBase {

	protected void performTask(
		HttpServletRequest request,
		HttpServletResponse response)
		throws ServletException, IOException {
		Integer attachid = new Integer(request.getParameter("attachid"));
		System.out.println("*******************attachid**************"+attachid);
		Connection conn = null;
		InputStream in = null;
		Statement stmt = null;
		ResultSet rs = null;
		Blob blob=null;
//		String fileName=null;
		try {
			conn =
				DBConnectionLocator.getInstance().getConnection(
					Globals.DATASOURCEJNDI);
			ConnLog.open("ShowFileServlet");
			LogHandler handler = new LogHandler(conn);
			System.out.println("*********************************");
			//取得附件内容
			LogAccessoryVO vo = null;
			List list = handler.getattachDetailList(attachid);
			if (list != null) {
				Iterator it = list.iterator();
				if (it.hasNext()) {
					vo = (LogAccessoryVO)it.next();
				}
			}
			
			String fileName = vo.getAccessoryName();
			in = vo.getAccessoryBlob();
			DownloadResponse ds = new DownloadResponse(response);
			
//			System.out.println("开始向浏览器输出流.1..........");
//			System.out.println("*********************************");
//			ds.downloadInputStream(is, fileName);
			ds.downloadInputStreamByTempfile(in, fileName);
			/*
			try {

				stmt = conn.createStatement();
				String sql = " select * from log_accessory where accessory_id = "
						+ attachid;
				rs = stmt.executeQuery(sql);

				if (rs.next()) {
					blob = (Blob) rs.getBlob("accessory_blob");
					fileName=rs.getString("accessory_name");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			System.out.println("*************fileName********************"+fileName);
			if(blob!=null){
				
				in = blob.getBinaryStream();

			String path = this.getServletContext().getRealPath("/log/download");
			handler.CreatDir(path);
			
			
			File f =
				new File(path + "/" + fileName);
			System.out.println("*****************path****************"+path+"and "+f.getPath());
			//0414如果该文件已经存在不进行重新生成
			if (!f.exists()) {
				f.createNewFile();
				System.out.println("*********1************************");
				FileOutputStream fo = new FileOutputStream(f.getPath(),false);
				System.out.println("*********2************************");
				
				byte[] buf = new byte[1024];
				int len = 0;
				
				while ((len = in.read(buf)) != -1) {
//					System.out.println("*********3-1************************");
					fo.write(buf, 0, len);
//					System.out.println("*********3-2************************");
				}
				System.out.println("*********4************************");
				fo.close();
				
			}
			response.sendRedirect(request.getContextPath() + "/log/download/"+fileName);
			}else{
				System.out.println("*********error downloadattachservlet attach is null************************");
			}
			*/
		} catch (Exception e) {
			handleError(e);
		} finally {
			
			try {
				if (in!=null) {
					in.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

			if (conn != null)
				try {
					conn.close();
					ConnLog.close("ShowFileServlet");
					if (in!=null){
						in.close();
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
		}

	}
}
