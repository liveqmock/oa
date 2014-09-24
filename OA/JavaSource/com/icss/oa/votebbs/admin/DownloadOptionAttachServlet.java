
 /* Created on 2003-12-17
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.icss.oa.votebbs.admin;


import java.io.IOException;
import java.io.InputStream;

import java.sql.Connection;

import java.sql.SQLException;

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

import com.icss.oa.votebbs.handler.BbsVoteHandler;
import com.icss.oa.votebbs.vo.BbsOptionsVO;
/**
 * @author Administrator
 * 显示文件
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class DownloadOptionAttachServlet extends ServletBase {

	protected void performTask(
		HttpServletRequest request,
		HttpServletResponse response)
		throws ServletException, IOException {
		Integer optionid = new Integer(request.getParameter("optionid"));
		Connection conn = null;
		InputStream in = null;


		try {
			conn =
				DBConnectionLocator.getInstance().getConnection(
					Globals.DATASOURCEJNDI);
			System.out.println("开始向浏览器输出流.000000..........optionid="+optionid);
			BbsVoteHandler handler = new BbsVoteHandler(conn);
			//取得附件内容
			BbsOptionsVO vo = null;
			List list = handler.getOptionDetail(optionid);
			System.out.println("开始向浏览器输出流.0..........list.size()="+list.size());
			if (list != null) {
				Iterator it = list.iterator();
				if (it.hasNext()) {
					vo = (BbsOptionsVO)it.next();
					System.out.println("开始向浏览器输出流.1..........");
				}
			}
			
			String fileName = vo.getOpAccessoryname();
			System.out.println("开始向浏览器输出流.1..........fileName"+fileName);
			in = vo.getOpAccessory();
			System.out.println("开始向浏览器输出流.2.........."+in.available());
			DownloadResponse ds = new DownloadResponse(response);
			
			System.out.println("开始向浏览器输出流.3..........");

			ds.downloadInputStreamByTempfile(in, fileName);
		
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

					if (in!=null){
						in.close();
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
		}

	}
}
