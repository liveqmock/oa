package com.icss.oa.tq.admin;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.icss.j2ee.dao.DAOException;
import com.icss.j2ee.dao.DAOFactory;
import com.icss.j2ee.servlet.ServletBase;
import com.icss.oa.address.handler.HandlerException;
import com.icss.oa.tq.Webservice.TQUser;
import com.icss.oa.tq.dao.TqRopersonSearchDAO;
import com.icss.oa.tq.handler.TQHandler;
import com.icss.oa.tq.vo.TqRopersonSearchVO;
import com.icss.oa.tq.vo.TqRopersonVO;

public class AddAllTQUserServlet extends ServletBase {

	protected void performTask(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		Connection conn = null;
		try {
			System.out.println("Add TQUSER begin!!!!!!!");
			conn = getConnection("jdbc/ROEEE");
			TQUser tquser = new TQUser();
			Logger logger = Logger.getLogger(TQUser.class);
			String userid = request.getParameter("userid");
			if (userid != null && !"".equals(userid.trim())) {
				TQHandler handler = new TQHandler(conn);

				List personlist = this.getPersonByUserid(userid, conn);

				System.out.println("~~~~~~~~~~~~~+" + personlist.size());
				Iterator it = personlist.iterator();
				while (it.hasNext()) {
					TqRopersonSearchVO vo = (TqRopersonSearchVO) it.next();
					String uin = tquser.oneUserRegister(vo.getUserid(), vo
							.getPassword(), vo.getSex(), vo.getCnname(), vo
							.getEmail(), "", "2", "");
					logger.info(uin);
					if (uin.length() < 7) {
						request.setAttribute("error", uin);
						break;
					} else {
						TqRopersonVO tqvo = new TqRopersonVO();
						tqvo.setPersonuuid(vo.getPersonuuid());
						tqvo.setTqid(new Integer(uin));
						handler.setTqid(tqvo);
					}

				}
			} else {
				request.setAttribute("error", "请输入用户名");
			}
			this.forward(request, response, "/tq/addUser.jsp");

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}

	}

	private List getPersonByUserid(String userid, Connection conn)
			throws HandlerException {
		StringBuffer sql = new StringBuffer();
		sql.append("select distinct ");
		sql
				.append("RO_PERSON.PERSONUUID,RO_PERSON.CNNAME,RO_PERSON.SEX,RO_PERSON.OFFICETEL,RO_PERSON.EMAIL1,RO_PERSONACCOUNT.USERID,RO_PERSONACCOUNT.PASSWORD ");
		sql.append(" from ");
		sql.append("RO_PERSON,RO_PERSONACCOUNT ");
		sql.append("where ");
		sql
				.append("RO_PERSON.PERSONUUID= RO_PERSONACCOUNT.PERSONUUID and RO_PERSONACCOUNT.USERID='"
						+ userid + "' ");
		DAOFactory factory = new DAOFactory(conn);
		TqRopersonSearchDAO dao = new TqRopersonSearchDAO();
		System.out.println("!!!!!getpersonSQL=" + sql.toString());
		dao.setSearchSQL(sql.toString());
		factory.setDAO(dao);
		try {
			return factory.find(new TqRopersonSearchVO());
		} catch (DAOException e) {
			throw new HandlerException(e);
		}
	}
}