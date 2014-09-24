/*
 * Created on 2004-6-21
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package com.icss.oa.netoffice.journal.admin;

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
import com.icss.oa.netoffice.journal.handler.JournalHandler;
import com.icss.oa.netoffice.journal.vo.OfficeJournalVO;

/**
 * @author Administrator
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public class exportJourServlet extends ServletBase {
	
	protected void performTask( HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		OfficeJournalVO jvo = null;
		String mid[] = request.getParameterValues("jc");
		
		Connection conn = null;
		try {
			conn = this.getConnection(Globals.DATASOURCEJNDI);
			ConnLog.open("exportJourServlet");
			ServletContext context = this.getServletContext();
			String exportpath = context.getRealPath("/netoffice/export/joural/");
			
			//			   String exportpath=NetofficeExportProManager.getString("journal_path");
			System.out.println("journal_path111 is : " + exportpath);
			File path = new File(exportpath);
			if (!path.exists()) {
				path.mkdirs();
			}
			
			System.out.println("journal_path is : " + exportpath);
			long timeforFilename1 = System.currentTimeMillis();
			String timeforFilename = new Long(timeforFilename1).toString();

			PrintWriter out =
				new PrintWriter(
					new FileWriter(
						exportpath + "journal" + timeforFilename + ".html",
						true));

			String memoHead = "<Font color=red><h3>下面是您导出的日记：</h3></Font><br><br>";
			out.write(memoHead);

			for (int j = 0; j < mid.length; j++) {
				
				StringBuffer sbf = new StringBuffer();
				Integer juuid = Integer.valueOf(mid[j]);
				JournalHandler jHandler = new JournalHandler(conn);
				jvo = jHandler.getById(juuid);
				String content = jvo.getRjContent();
				System.out.println("The content is:  " + content);
				//sbf.append(content);
				Long time = jvo.getRjDate();
				Date date = new Date(time.longValue());
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
				String formatTime = formatter.format(date);
				//sbf.append(formatTime);
				String headline = jvo.getRjHeadline();

				sbf.append("***********************************************************<br>");
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
			
			InputStream is = new FileInputStream(exportpath + "journal" + timeforFilename + ".html");
			DownloadResponse ds = new DownloadResponse(response);
			ds.downloadInputStreamByTempfile( is, "journal" + timeforFilename + ".html");
			
			is.close();

			File f = new File(exportpath + "journal" + timeforFilename + ".html");
			if (f.exists()) {
				f.delete();
			}
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			
		} finally {
			if(conn!=null){
				try {
					conn.close();
					ConnLog.close("exportJourServlet");
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		}

	}
}
