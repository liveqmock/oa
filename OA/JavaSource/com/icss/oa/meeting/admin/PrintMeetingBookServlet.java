/*
 * Created on 2004-4-12
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */

package com.icss.oa.meeting.admin;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.Workbook;
import jxl.format.BorderLineStyle;
import jxl.format.UnderlineStyle;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

import com.icss.common.log.ConnLog;
import com.icss.j2ee.services.ServiceLocatorException;
import com.icss.j2ee.servlet.ServletBase;
import com.icss.j2ee.servlet.download.DownloadResponse;
import com.icss.j2ee.util.Globals;
import com.icss.oa.meeting.handler.MeetingroomManagerHandler;
import com.icss.oa.meeting.vo.MeetingPutVO;
import com.icss.oa.meeting.vo.MeetingRoominformationVO;
import com.icss.oa.netoffice.onduty.handler.DutyHandler;
import com.icss.oa.util.CommUtil;
import com.icss.resourceone.sdk.framework.EntityException;
import com.icss.resourceone.sdk.framework.EntityManager;
import com.icss.resourceone.sdk.framework.Person;
/**
 * @author Administrator
 * 
 * To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Generation - Code and Comments
 */
public class PrintMeetingBookServlet extends ServletBase {
	protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection conn = null;

		List foldersList = null;
		List addressbooksList = null;
		List haschildabfList = null;
		List childabcList = null;
		int j;

		try {
			conn = this.getConnection(Globals.DATASOURCEJNDI);
ConnLog.open("PrintMeetingBookServlet");
			MeetingroomManagerHandler handler = new MeetingroomManagerHandler(conn);
			DutyHandler dHandler = new DutyHandler(conn);

			EntityManager entityManager = EntityManager.getInstance();
			Person person = null;

			String path = this.getServletContext().getRealPath("/MeetingBooktmp");
			handler.CreatDir(path);

			File tempFile = new File(path + "/MeetingBook.xls");
			if (!tempFile.exists()) {
				tempFile.createNewFile();
			}
			WritableWorkbook workbook = Workbook.createWorkbook(tempFile);
			WritableSheet sheet = workbook.createSheet("TestCreateExcel", 0);
			Label l = null;

			jxl.write.Number n = null;
			jxl.write.DateTime d = null;

			WritableFont headerFont = new WritableFont(WritableFont.ARIAL, 12, WritableFont.BOLD, false, UnderlineStyle.NO_UNDERLINE, jxl.format.Colour.BLUE);
			WritableCellFormat headerFormat = new WritableCellFormat(headerFont);

			WritableFont titleFont = new WritableFont(WritableFont.ARIAL, 10, WritableFont.NO_BOLD, false, UnderlineStyle.NO_UNDERLINE, jxl.format.Colour.RED);
			WritableCellFormat titleFormat = new WritableCellFormat(titleFont);
			titleFormat.setWrap(true);
			titleFormat.setBorder(jxl.write.Border.ALL, BorderLineStyle.THIN);
			titleFormat.setAlignment(jxl.format.Alignment.CENTRE);
			titleFormat.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);

			WritableFont detFont = new WritableFont(WritableFont.ARIAL, 10, WritableFont.NO_BOLD, false, UnderlineStyle.NO_UNDERLINE, jxl.format.Colour.BLACK);
			WritableCellFormat detFormat = new WritableCellFormat(detFont);
			detFormat.setWrap(true);
			detFormat.setBorder(jxl.write.Border.ALL, BorderLineStyle.THIN);
			detFormat.setAlignment(jxl.format.Alignment.LEFT);
			detFormat.setVerticalAlignment(jxl.format.VerticalAlignment.TOP);

			l = new Label(4, 0, "�»������һ����", headerFormat);

			sheet.addCell(l);

			l = new Label(0, 1, "", titleFormat);
			sheet.addCell(l);

			long nowtime = System.currentTimeMillis();

			//�õ���ǰʱ���������ڵĿ�ʼʱ��
			long weekStart = dHandler.getWeekStart(nowtime);

			String SundayStr = com.icss.oa.util.CommUtil.getTime(weekStart + 86400000 * 1, 1).trim().substring(5, 10);
			String MondayStr = com.icss.oa.util.CommUtil.getTime(weekStart + 86400000 * 2, 1).trim().substring(5, 10);
			String TuesdayStr = com.icss.oa.util.CommUtil.getTime(weekStart + 86400000 * 3, 1).trim().substring(5, 10);
			String WednesdayStr = com.icss.oa.util.CommUtil.getTime(weekStart + 86400000 * 4, 1).trim().substring(5, 10);
			String ThursdayStr = com.icss.oa.util.CommUtil.getTime(weekStart + 86400000 * 5, 1).trim().substring(5, 10);
			String FridayStr = com.icss.oa.util.CommUtil.getTime(weekStart + 86400000 * 6, 1).trim().substring(5, 10);
			String SaturdayStr = com.icss.oa.util.CommUtil.getTime(weekStart + 86400000 * 7, 1).trim().substring(5, 10);

			l = new Label(1, 1, "����һ" + " ( " + SundayStr + " )", titleFormat);
			sheet.addCell(l);
			l = new Label(2, 1, "���ڶ�" + " ( " + MondayStr + " ) ", titleFormat);
			sheet.addCell(l);
			l = new Label(3, 1, "������" + " ( " + TuesdayStr + " ) ", titleFormat);
			sheet.addCell(l);
			l = new Label(4, 1, "������" + " ( " + WednesdayStr + " ) ", titleFormat);
			sheet.addCell(l);
			l = new Label(5, 1, "������" + " ( " + ThursdayStr + " ) ", titleFormat);
			sheet.addCell(l);
			l = new Label(6, 1, "������" + " ( " + FridayStr + " ) ", titleFormat);
			sheet.addCell(l);
			l = new Label(7, 1, "������" + " ( " + SaturdayStr + " ) ", titleFormat);
			sheet.addCell(l);

			//��ǰ�µ����һ���Ǳ��µĵڼ�������
			Calendar cal = Calendar.getInstance();
			cal.setTime(new Date(nowtime));
			int year = cal.get(Calendar.YEAR);
			int month = cal.get(Calendar.MONTH) + 2; //����
			int week = cal.get(Calendar.WEEK_OF_MONTH);
			long nextMonthStart = 0;
			if (month < 10) {
				nextMonthStart = CommUtil.getLongByTime(year + "-0" + (month) + "-01");
			} else {
				nextMonthStart = CommUtil.getLongByTime(year + "-" + (month) + "-01");
			}
			cal.setTime(new Date(nextMonthStart - 86400000));
			long weekth = new Integer(cal.get(Calendar.WEEK_OF_MONTH)).longValue();

			request.setAttribute("weekth", new Long(weekth));

			request.setAttribute("year", String.valueOf(year));
			request.setAttribute("month", String.valueOf(month - 1));
			request.setAttribute("week", String.valueOf(week));
			request.setAttribute("weekStart", new Long(weekStart));

			List list = handler.getMeetingroomList();
			request.setAttribute("List", list);
			int h = 2;
			if (list != null) {
				Iterator it = list.iterator();
				while (it.hasNext()) {
					MeetingRoominformationVO vo = (MeetingRoominformationVO) it.next();

					for (int i = 0; i < 7; i++) {
						List list1 = handler.getMeetingPutListByMeeting(vo.getMeetName(), new Long(weekStart + (i + 1) * 24 * 60 * 60 * 1000));
						if (list1 != null) {
							Iterator it1 = list1.iterator();
							MeetingPutVO vo1 = null;
							StringBuffer tmp = new StringBuffer();
							while (it1.hasNext()) {

								vo1 = (MeetingPutVO) it1.next();

								person = entityManager.findPersonByUuid(vo1.getMeetingPutperson());

								tmp.append(person.getFullName());
								tmp.append(" ");
								tmp.append(vo1.getStartimehour());
								tmp.append("--");
								tmp.append(vo1.getEndtimehour());
								tmp.append(" ");
								tmp.append(vo1.getMeetingPutdep());
								tmp.append("             ");
								tmp.append("---------------------");
							}
							l = new Label((i + 1), h, tmp.toString(), detFormat);
							sheet.addCell(l);
						}
					}
					h++;
				}

			}

			list = handler.getMeetingroomList();
			int i = 2;
			if (list != null) {
				Iterator it = list.iterator();
				while (it.hasNext()) {
					MeetingRoominformationVO vo = (MeetingRoominformationVO) it.next();
					l =
						new Label(
							0,
							i++,
							vo.getMeetName() + "                              ( " + vo.getMeetingMaxnum() + "�� )  " + "                  ��ϵ��             ʹ�ò���                       ʹ��ʱ��",
							titleFormat);
					sheet.addCell(l);

				}
			}

			if ("a3".equals(request.getParameter("type"))) {
				sheet.setColumnView(0, 13);
				sheet.setColumnView(1, 16);
				sheet.setColumnView(2, 16);
				sheet.setColumnView(3, 16);
				sheet.setColumnView(4, 16);
				sheet.setColumnView(5, 16);
				sheet.setColumnView(6, 16);
				sheet.setColumnView(7, 16);
				sheet.setColumnView(8, 16);

				sheet.setRowView(0, 300);
				sheet.setRowView(1, 300);
				sheet.setRowView(2, 2000);
				sheet.setRowView(3, 2000);
				sheet.setRowView(4, 2000);
				sheet.setRowView(5, 2000);
				sheet.setRowView(6, 2000);
				sheet.setRowView(7, 2000);
				sheet.setRowView(8, 2000);
				sheet.setRowView(9, 2000);
				sheet.setRowView(10, 2000);
				sheet.setRowView(11, 2000);
				sheet.setRowView(12, 2000);
				sheet.setRowView(13, 2000);
				sheet.setRowView(14, 2000);
				sheet.setRowView(15, 2000);
				sheet.setRowView(16, 2000);
				sheet.setRowView(17, 2000);
			}

			if ("a4".equals(request.getParameter("type"))) {

				sheet.setColumnView(0, 9);
				sheet.setColumnView(1, 11);
				sheet.setColumnView(2, 11);
				sheet.setColumnView(3, 11);
				sheet.setColumnView(4, 11);
				sheet.setColumnView(5, 11);
				sheet.setColumnView(6, 11);
				sheet.setColumnView(7, 11);
				sheet.setColumnView(8, 11);

				sheet.setRowView(0, 300);
				sheet.setRowView(1, 600);
				sheet.setRowView(2, 1500);
				sheet.setRowView(3, 1500);
				sheet.setRowView(4, 1500);
				sheet.setRowView(5, 1500);
				sheet.setRowView(6, 1500);
				sheet.setRowView(7, 1500);
				sheet.setRowView(8, 1500);
				sheet.setRowView(9, 1500);
				sheet.setRowView(10, 1500);
				sheet.setRowView(11, 1500);
				sheet.setRowView(12, 1500);
				sheet.setRowView(13, 1500);
				sheet.setRowView(14, 1500);
				sheet.setRowView(15, 1500);
				sheet.setRowView(16, 1500);
				sheet.setRowView(17, 1500);
			}

			workbook.write();
			workbook.close();

			InputStream in = null;
			in = new FileInputStream(path + "/MeetingBook.xls");
			DownloadResponse ds = new DownloadResponse(response);
			ds.downloadInputStream(in, "MeetingBook.xls");
			tempFile.delete();

			if (in != null) {
				in.close();
			}

		} catch (ServiceLocatorException e) {
			e.printStackTrace();
			handleError(e);
		} catch (EntityException e) {
			e.printStackTrace();
			handleError(e);
		} catch (WriteException e) {
			e.printStackTrace();
			handleError(e);
		} catch (Exception e) {
			e.printStackTrace();
			handleError(e);
		} finally {
			try {
				if (conn != null) {
					conn.close();
					ConnLog.close("PrintMeetingBookServlet");
				}
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}

}