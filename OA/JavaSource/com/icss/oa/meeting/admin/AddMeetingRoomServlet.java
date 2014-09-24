package com.icss.oa.meeting.admin;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.common.log.ConnLog;
import com.icss.j2ee.services.ServiceLocatorException;
import com.icss.j2ee.servlet.ServletBase;
import com.icss.j2ee.util.Globals;
import com.icss.oa.meeting.handler.HandlerException;
import com.icss.oa.meeting.handler.MeetingroomManagerHandler;
import com.icss.oa.meeting.vo.MeetingRoominformationVO;

public class AddMeetingRoomServlet extends ServletBase {

	
	protected void performTask(
		HttpServletRequest request,
		HttpServletResponse response)
		throws ServletException, IOException {

			Connection conn=null;
			InputStream inputStream = null;
			
			String MeetingName = request.getParameter("MeetingName");
			String MeetingType = request.getParameter("MeetingType");
			String MeetingBuilding = request.getParameter("MeetingBuilding");
			String MeetingRoom = request.getParameter("MeetingRoom");
			String MeetingUtil = request.getParameter("MeetingUtil");
			String MeetingMaxNum = request.getParameter("MeetingMaxNum");
			String MeetingRoomNum = request.getParameter("MeetingRoomNum");
					
			try {
				conn = this.getConnection(Globals.DATASOURCEJNDI);		
				ConnLog.open("AddMeetingRoomServlet");
				MeetingroomManagerHandler handler = new MeetingroomManagerHandler(conn);
				MeetingRoominformationVO vo = new MeetingRoominformationVO();
				
				vo.setMeetingMaxnum(new Integer(MeetingMaxNum));
				vo.setMeetingUtil(MeetingUtil);
				vo.setMeetName(MeetingName);
				vo.setBelongBuilding(MeetingBuilding);
				vo.setBelongRoom(MeetingRoom);
				vo.setNum(new Integer(MeetingRoomNum));
			   
	     		if(!request.getParameter("access1").equals("default"))
	     		{
	     			String fileFillName = getUploadFileFullName(request, "access");
	     			inputStream = new FileInputStream(fileFillName);
	     			vo.setMeetingSitting(inputStream);
	     			vo.setSittingName(this.getUploadFileName(request,"access"));
	     		}
	     		
				handler.addMeetingroom(vo);
				
				if(!request.getParameter("access1").equals("default")){
					inputStream.close();
				}
			
		       response.sendRedirect("MeetingRoomManagerServlet?page_num="+request.getParameter("_page_num"));
		       
			} catch (HandlerException e) {
				e.printStackTrace();
				handleError(e);
			} catch (ServiceLocatorException e) {
				e.printStackTrace();
				handleError(e);
			}finally {
				try {
					if (conn != null) {
						conn.close();
						ConnLog.close("AddMeetingRoomServlet");
					}
				} catch (SQLException sqle) {
					sqle.printStackTrace();
				}
			}

	}

}
