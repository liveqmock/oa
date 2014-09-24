/*
 * Created on 2004-6-17
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package com.icss.oa.netoffice.memo.admin;

import java.io.File;
import java.io.FileInputStream;

import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.common.log.ConnLog;
import com.icss.j2ee.servlet.ServletBase;
import com.icss.j2ee.servlet.download.DownloadResponse;
import com.icss.j2ee.util.Globals;
import com.icss.oa.netoffice.memo.handler.MemoHandler;
import com.icss.oa.netoffice.memo.vo.OfficeMemoVO;
//import com.icss.oa.util.NetofficeExportProManager;
;

/**
 * @author Administrator
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public class OutputServlet extends ServletBase {
	protected void performTask(
		HttpServletRequest request,
		HttpServletResponse response)
		throws ServletException, IOException {
		Connection conn = null;
		OfficeMemoVO mvo = null;
		String mid[] = request.getParameterValues("mc");

		try {
			conn = this.getConnection(Globals.DATASOURCEJNDI);
			ConnLog.open("OutputServlet");
			//			   String exportpath=NetofficeExportProManager.getString("memo_path");
			ServletContext context = this.getServletContext();
			String exportpath = context.getRealPath("/netoffice/export/memo/");
			File path = new File(exportpath);
			if (!path.exists()) {
				path.mkdirs();
			}
			System.out.println("memo_path is : " + exportpath);
			long timeforFilename1 = System.currentTimeMillis();
			String timeforFilename = new Long(timeforFilename1).toString();

			PrintWriter out =
				new PrintWriter(
					new FileWriter(
						exportpath + "memo" + timeforFilename + ".html",
						true));

			String memoHead =
				"<Font color=red><h3>下面是您导出的备忘录：</h3></Font><br><br>";
			out.write(memoHead);

			for (int j = 0; j < mid.length; j++) {
				StringBuffer sbf = new StringBuffer();
				Integer muuid = Integer.valueOf(mid[j]);
				MemoHandler mHandler = new MemoHandler(conn);
				mvo = mHandler.getById(muuid);
				String content = mvo.getMemoContent();
				System.out.println("The content is:  " + content);
				//sbf.append(content);
				Long time = mvo.getMemoTime();
				Date date = new Date(time.longValue());
				SimpleDateFormat formatter =
					new SimpleDateFormat("yyyy.MM.dd ' 'HH:mm  ");
				String formatTime = formatter.format(date);
				//sbf.append(formatTime);
				String headline = mvo.getMemoHeadline();

				sbf.append(
					"***********************************************************<br>");
				sbf.append("时间： ");
				sbf.append(formatTime + "<br>");
				sbf.append("主题： ");
				sbf.append("<font color=green>" + headline + "</font><br>");
				sbf.append("内容：<br>");
				sbf.append("    " + content + "<br/><br/><br/>");
				String s = sbf.toString();
				System.out.println("test  string is: " + s);

				out.write(s);

			}
			out.close();
			InputStream is =
				new FileInputStream(
					exportpath + "memo" + timeforFilename + ".html");
			DownloadResponse ds = new DownloadResponse(response);
			//			  ds.downloadInputStream(is,"memo"+timeforFilename+".html");
			ds.downloadInputStreamByTempfile(
				is,
				"memo" + timeforFilename + ".html");
			is.close();
			//response.sendRedirect(request.getContextPath()+"/servlet/ShowMemoServlet");
			//this.forward(request, response, "/servlet/ShowMemoServlet");
			File f = new File(exportpath + "memo" + timeforFilename + ".html");
			if (f.exists()) {
				f.delete();
			}
			
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			
		} finally{
			if(conn!=null){
				try {
					conn.close();
					ConnLog.close("OutputServlet");
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		}

	}

}
