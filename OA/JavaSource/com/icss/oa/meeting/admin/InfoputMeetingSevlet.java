package com.icss.oa.meeting.admin;

import java.io.IOException;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.common.log.ConnLog;
import com.icss.j2ee.services.ServiceLocatorException;
import com.icss.j2ee.servlet.ServletBase;
import com.icss.j2ee.util.Globals;
import com.icss.oa.intendwork.handler.IntendWork;
import com.icss.oa.meeting.handler.HandlerException;
import com.icss.oa.meeting.handler.MeetingroomManagerHandler;
import com.icss.oa.meeting.vo.MeetingPersonVO;
import com.icss.oa.meeting.vo.MeetingPersonmeetVO;
import com.icss.oa.meeting.vo.MeetingPutVO;

public class InfoputMeetingSevlet extends ServletBase {

	protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Connection conn = null;

		String id = request.getParameter("putId");

		try {
			conn = this.getConnection(Globals.DATASOURCEJNDI);
			ConnLog.open("InfoputMeetingSevlet");
			String putId = request.getParameter("putId");
			String MeetingName = request.getParameter("MeetingName");
			String flag = request.getParameter("flag");
			String status = request.getParameter("status");

			MeetingroomManagerHandler handler = new MeetingroomManagerHandler(conn);
			List list = handler.getMeetingPersonList(new Integer(putId));
			MeetingPutVO vo11 = handler.getMeetingPutVO(new Integer(putId));

			IntendWork addIntendHandler = new IntendWork(conn);
			if (list.size() == 0 && "会议信息发表".equals(flag)) {
				response.sendRedirect(request.getContextPath() + "/meeting/error2.jsp?dd=" + URLEncoder.encode("你要发布会议信息,但是与会人员为空,请确认！"));
			}
			if ("会议信息发表".equals(flag)) {
				if (list != null) {
					Iterator it = list.iterator();
					while (it.hasNext()) {

						MeetingPersonVO vo = (MeetingPersonVO) it.next();
						String topic = new String();
						String source = new String();
						String url = new String();
						String navigate = new String();
						String personid = new String();
						topic = MeetingName + "通知";
						source = "会议通知";
						url = "/oabase/servlet/ShowMeetingServlet?putId=" + putId;
						navigate = "#";
						personid = vo.getPerson1();

						//将会议记录加入个人会议表中

						MeetingPersonmeetVO vo1 = new MeetingPersonmeetVO();

						vo1.setMeetingName(vo11.getMeetingName());
						vo1.setMeetingType(vo11.getMeetingType());
						vo1.setMeetingConcept(vo11.getPutMeno());
						vo1.setStarttime(vo11.getStarttimeday());
						vo1.setEndtime(vo11.getEndtimeday());
						vo1.setStart1(vo11.getStartimehour());
						vo1.setEnd1(vo11.getEndtimehour());
						vo1.setPerson(vo.getPerson());
						vo1.setMeetingPutdep(vo11.getMeetingPutdep());
						vo1.setMeetingUtil(vo11.getMeetingNeedingutil());
						vo1.setMeetingPutid(vo11.getPutId());
						vo1.setMeetingPutperon(vo11.getMeetingPutperson());
						vo1.setMeetingRoom(vo11.getMeetingRoom());

						List list1 = handler.getPersonMeetingList(vo11.getPutId(), handler.getUserId());
						Integer p;

						if (list1.size() > 0) {
							if (list1 != null) {
								Iterator it1 = list1.iterator();
								if (it1.hasNext()) {
									MeetingPersonmeetVO vo111 = (MeetingPersonmeetVO) it1.next();
									vo1.setId(vo111.getId());
								}
							}
							p = handler.UpdatePersonMeeting(vo1);
						} else {

							p = handler.addPersonMeeting(vo1);
						}
						addIntendHandler.addWork(topic, source, url, navigate, personid, IntendWork.getCodeValue("msg_pubmeeting"), p.toString());

					}
				}
			}
			if ("会议开始".equals(flag)) {
				if ("已确认".equals(status)) {
					handler.PutMeetingStatus(new Integer(putId), flag);
					response.sendRedirect(request.getContextPath() + "/meeting/error2.jsp?dd=" + URLEncoder.encode("会议信息还有发布给[与会人员],请确认！"));
				}
			}

			handler.PutMeetingStatus(new Integer(putId), flag);

			response.sendRedirect(request.getContextPath() + "/meeting/error1.jsp?dd=" + URLEncoder.encode("将会议状态置为[") + URLEncoder.encode(flag) + URLEncoder.encode("]成功!"));

		} catch (ServiceLocatorException e) {
			e.printStackTrace();
			handleError(e);
		} catch (NumberFormatException e) {
			e.printStackTrace();
			handleError(e);
		} catch (HandlerException e) {
			e.printStackTrace();
			handleError(e);
		}finally {
			try {
				if (conn != null) {
					conn.close();
					ConnLog.close("InfoputMeetingSevlet");
				}
			} catch (SQLException sqle) {
				sqle.printStackTrace();
			}
		}

	}

}
