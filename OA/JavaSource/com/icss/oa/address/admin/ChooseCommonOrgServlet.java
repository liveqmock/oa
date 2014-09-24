package com.icss.oa.address.admin;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.icss.common.log.ConnLog;
import com.icss.j2ee.services.DBConnectionLocator;
import com.icss.j2ee.servlet.ServletBase;
import com.icss.oa.address.handler.Group;
import com.icss.oa.address.vo.AddressCommongroupVO;
import com.icss.oa.address.vo.AddressGroupVO;
import com.icss.oa.address.vo.SelectOrgpersonVO;
import com.icss.oa.config.AddressConfig;
/**
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public class ChooseCommonOrgServlet extends ServletBase {
	protected void performTask(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException, java.io.IOException {
		String doFunction = "";
		if (request.getParameter("doFunction") != "") {
			doFunction = request.getParameter("doFunction");
		}
		System.out.println("servlet_doFunction=" + doFunction);
		String sessionname = "";
		if (request.getParameter("sessionname") != "") {
			sessionname = request.getParameter("sessionname");
		}
		String isperson = "";
		if (request.getParameter("isperson") != "") {
			isperson = request.getParameter("isperson");
		}		int tempflag = 1;
		List selectorgpersonlist = new ArrayList();
		AddressCommongroupVO addresscommongroupvo = new AddressCommongroupVO();
		AddressGroupVO addressgroupvo = new AddressGroupVO();

		HttpSession session = request.getSession();
		selectorgpersonlist = (List) session.getAttribute(sessionname);
		if (selectorgpersonlist == null)
			selectorgpersonlist = new ArrayList();
		Connection conn = null;

		try {
			if ((this.getServletContext().getInitParameter(AddressConfig.ADDRESSJNDI) == null) || (this.getServletContext().getInitParameter(AddressConfig.ADDRESSJNDI).equals(""))) {
				conn = getConnection(com.icss.j2ee.util.Globals.DATASOURCEJNDI);
				ConnLog.open("ChooseCommonOrgServlet");
			} else {
				conn = DBConnectionLocator.getInstance().getConnection(this.getServletContext().getInitParameter(AddressConfig.ADDRESSJNDI));
				ConnLog.open("ChooseCommonOrgServlet");
			}

			if (request.getParameterValues("personid") != null) {
				String[] userid = request.getParameterValues("personid");
				for (int i = 0; i < userid.length; i++) {
					tempflag = 1;
					if (!selectorgpersonlist.isEmpty()) {
						Iterator iterator = selectorgpersonlist.iterator();
						while (iterator.hasNext()) {
							SelectOrgpersonVO vo = (SelectOrgpersonVO) iterator.next();
							//								System.out.println("userid["+i+"]="+userid[i]);
							//								System.out.println("vo.getUserid()="+vo.getUserid());
							if (userid[i].equals(vo.getUserid()) && (isperson.equals(vo.getIsperson()))) {
								tempflag = 0; //检查是否有相同的userid
							} //if
						} //while
					}
					//如果session中有相同的selectorgpersonvo，则不写入
					System.out.println("tempflag=" + tempflag);
					if (tempflag == 1) {
						SelectOrgpersonVO selectorgpersonvo = new SelectOrgpersonVO();
						//向vo写入id信息
						selectorgpersonvo.setUserid(userid[i]);

						//根据userid查找出公共Group信息
						Group handler = new Group(conn);
						addresscommongroupvo = handler.getCommonGroup(Integer.valueOf(userid[i]));
						//向vo写入name信息
						selectorgpersonvo.setName(addresscommongroupvo.getGroupname());
						selectorgpersonvo.setIsperson("00");
						//							System.out.println("commongroup="+addresscommongroupvo.getGroupname());
						//							
						//							System.out.println("***************");
						//							System.out.println(userid[i]);
						//							System.out.println("***************");

						selectorgpersonlist.add(selectorgpersonvo);
					}
				} //for
			}
			//将list信息写入到session中
			//				if (!selectorgpersonlist.isEmpty()){
			//					System.out.println("list is not null!");
			//				}else{
			//					System.out.println("list is null!");
			//				}
			request.setAttribute(sessionname, selectorgpersonlist);
			session.setAttribute(sessionname, selectorgpersonlist);
			String str = "/address/pubaddress/selected.jsp?doFunction=" + doFunction + "&sessionname=" + sessionname;
			System.out.println("_orchid_==" + str);
			this.forward(request, response, str);

		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (Exception ex) {
			ex.printStackTrace();
			handleError(ex);
		} finally {
			try {
				if (conn != null){
					conn.close();
					ConnLog.close("ChooseCommonOrgServlet");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			//
		}
	}

}
