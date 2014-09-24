package com.icss.oa.user.admin;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.j2ee.servlet.ServletBase;
import com.icss.j2ee.util.Globals;
import com.icss.oa.hr.admin.HRGroupWebservice;
import com.icss.oa.sync.vo.PersonVO;
import com.icss.oa.user.handler.hrUserInfoHandler;
import com.icss.oa.user.vo.UserInfoSearchVO;

public class UserImageServlet extends ServletBase {

	protected void performTask(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//HRGroupWebservice hw = new HRGroupWebservice();
		String path = HRGroupWebservice.readValue("TQPICPATH");
		
		Connection conn = null;
		Connection rcon = null;
		try {
			synchronized (this) {
				rcon = getConnection("jdbc/ROEEE");
				conn = getConnection(Globals.DATASOURCEJNDI);

				hrUserInfoHandler handler1 = new hrUserInfoHandler(rcon);
				List list = handler1.getAllPerson();
				//System.out.println(list.size());

				Iterator it = list.iterator();
				while (it.hasNext()) {
					PersonVO vo = (PersonVO) it.next();
					//System.out.println(vo);
					if (vo.getTqid() != null) {
						//System.out.println(vo.getTqid());
						hrUserInfoHandler handler = new hrUserInfoHandler(conn);
						UserInfoSearchVO uservo = handler.getUserInfo(vo
								.getPersonuuid());

						if (uservo.getImage()!= null) {
							handler.creatPic(uservo.getImage(), path+"/"+vo.getTqid()+".jpg");
						}
					}

				}
			}

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
				if (rcon != null) {
					rcon.close();
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}

}
