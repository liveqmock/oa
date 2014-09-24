/*
 * Created on 2004-6-24
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package com.icss.oa.netoffice.schedule.admin;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.common.log.ConnLog;
import com.icss.j2ee.servlet.ServletBase;
import com.icss.j2ee.servlet.download.DownloadResponse;
import com.icss.j2ee.util.Globals;
import com.icss.oa.netoffice.schedule.handler.ScheduleHandler;
import com.icss.oa.netoffice.schedule.vo.OfficeScheduleVO;
import com.icss.oa.util.CommUtil;

/**
 * @author Administrator
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public class ScheduleExportServlet extends ServletBase {

	protected void performTask(
		HttpServletRequest request,
		HttpServletResponse response)
		throws ServletException, IOException {
		Connection conn = null;
		OfficeScheduleVO svo = null;
		String mid[] = request.getParameterValues("jc");

		try {
			conn = this.getConnection(Globals.DATASOURCEJNDI);
			ConnLog.open("ScheduleExportServlet");
			//			   String exportpath=NetofficeExportProManager.getString("schedule_path");
			ServletContext context = this.getServletContext();
			String exportpath =
				context.getRealPath("/netoffice/export/schedule/");
			File path = new File(exportpath);
			if (!path.exists()) {
				path.mkdirs();
			}
			System.out.println("schedule_path is : " + exportpath);
			long timeforFilename1 = System.currentTimeMillis();
			String timeforFilename = new Long(timeforFilename1).toString();

			PrintWriter out =
				new PrintWriter(
					new FileWriter(
						exportpath + "schedule" + timeforFilename + ".html",
						true));

			String scheduleHead =
				"<Font color=red><h3>下面是您导出的日程安排：</h3></Font><br><br>";
			out.write(scheduleHead);

			for (int j = 0; j < mid.length; j++) {
				StringBuffer sbf = new StringBuffer();
				Integer suuid = Integer.valueOf(mid[j]);
				ScheduleHandler sHandler = new ScheduleHandler(conn);
				svo = sHandler.getById(suuid);
				String descript = svo.getOsDes();
				if (descript == null)
					descript = "";
				String topic = svo.getOsTopic();
				String ostype = svo.getOsType();

				Long datetime = svo.getOsDate();
				String formatTime = CommUtil.getTime(datetime.longValue(), 1);

				sbf.append(
					"***********************************************************<br>");
				sbf.append("日期： ");
				sbf.append(formatTime + "<br>");

				sbf.append("主题： ");
				sbf.append("<font color=green>" + topic + "</font><br>");
				sbf.append("事件类型： ");
				sbf.append(" " + ostype + "<br>");

				sbf.append("事宜描述：<br>");
				sbf.append("    " + descript + "<br/><br/><br/>");
				String s = sbf.toString();
				System.out.println("test  string is: " + s);

				out.write(s);

			}
			out.close();
			InputStream is =
				new FileInputStream(
					exportpath + "schedule" + timeforFilename + ".html");
			DownloadResponse ds = new DownloadResponse(response);
			ds.downloadInputStream(is, "schedule" + timeforFilename + ".html");
			is.close();
			//response.sendRedirect(request.getContextPath()+"/servlet/ShowMemoServlet");
			//this.forward(request, response, "/servlet/ShowMemoServlet");
			File f =
				new File(exportpath + "schedule" + timeforFilename + ".html");
			if (f.exists()) {
				f.delete();
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());

		} finally {
			if (conn != null) {
				try {
					conn.close();
					ConnLog.close("ScheduleExportServlet");
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		}

	}
}
