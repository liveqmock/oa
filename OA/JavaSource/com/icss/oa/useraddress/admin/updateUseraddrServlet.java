package com.icss.oa.useraddress.admin;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.common.log.ConnLog;
import com.icss.j2ee.servlet.ServletBase;
import com.icss.j2ee.util.Globals;
import com.icss.oa.useraddress.handler.UserAddressHandler;
import com.icss.oa.useraddress.vo.OaaddListVO;

public class updateUseraddrServlet extends ServletBase {

	
	protected void performTask(
		HttpServletRequest request,
		HttpServletResponse response)
		throws ServletException, IOException {

			Connection conn=null;
			Integer odid = new Integer(request.getParameter("odid"));
			String odusrname = request.getParameter("odusrname");
			String oddept = request.getParameter("oddept");
			String odroomnum = request.getParameter("odroomnum");
			String odtel = request.getParameter("odtel");
			String odword = request.getParameter("odword");
			String OdBuss = request.getParameter("OdBuss");
			String OdMachcircs = request.getParameter("OdMachcircs");
			String OdSys = request.getParameter("OdSys");
			String OdIp = request.getParameter("OdIp");
			String OdOpesys = request.getParameter("OdOpesys");
			String OdMacnum = request.getParameter("OdMacnum");
			String OdMachname = request.getParameter("OdMachname");
			String OdMemo = request.getParameter("OdMemo");
			String OdMachid = request.getParameter("OdMachid");
			String OdOnflag = request.getParameter("OdOnflag");
			String OdRegion = request.getParameter("OdRegion");
			String OdVirus = request.getParameter("OdVirus");
			String OdOpentime = request.getParameter("OdOpentime");
			String OdRoomnod = request.getParameter("OdRoomnod");
			String OdNetcircs = request.getParameter("OdNetcircs");
			String OdOffice = request.getParameter("OdOffice");
			String OdNettel = request.getParameter("OdNettel");
					
			try {
				conn = this.getConnection(Globals.DATASOURCEJNDI);	
				ConnLog.open("updateUseraddrServlet");	
				UserAddressHandler handler = new UserAddressHandler(conn);
				OaaddListVO vo = new OaaddListVO();
				vo.setOdUsrname(odusrname);
				vo.setOdDept(oddept);
				vo.setOdRoomnum(odroomnum);
				vo.setOdTel(odtel);
				vo.setOdWord(odword);
				vo.setOdBuss(OdBuss);
				vo.setOdMachcircs(OdMachcircs);
				vo.setOdSys(OdSys);
				vo.setOdIp(OdIp);
				vo.setOdOpesys(OdOpesys);
				vo.setOdMacnum(OdMacnum);
				vo.setOdMachname(OdMachname);
				vo.setOdMemo(OdMemo);
				vo.setOdId(odid);
				vo.setOdMachid(OdMachid);
				vo.setOdOnflag(OdOnflag);
				vo.setOdRegion(OdRegion);
				vo.setOdVirus(OdVirus);
				vo.setOdOpentime(OdOpentime);
				vo.setOdRoomnod(OdRoomnod);
				vo.setOdNetcircs(OdNetcircs);
				vo.setOdOffice(OdOffice);
				vo.setOdNettel(OdNettel);

				
				
				handler.update(vo);
				response.sendRedirect("OaaddListServlet");
				
			} catch (Exception e) {
				e.printStackTrace();
				handleError(e);
			} finally {
				try {
					if (conn != null) {
						conn.close();
						ConnLog.close("updateUseraddrServlet");	
					}
				} catch (SQLException sqle) {
					sqle.printStackTrace();
					handleError(sqle);
				}
			}

	}

}
