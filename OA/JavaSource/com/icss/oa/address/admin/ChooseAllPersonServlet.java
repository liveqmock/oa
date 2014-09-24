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
import com.icss.oa.address.handler.SysOrgpersonHandler;
import com.icss.oa.address.vo.SelectOrgpersonVO;
import com.icss.oa.address.vo.SysOrgpersonVO;
import com.icss.oa.config.AddressConfig;
/**
 * �õ���֯��uuid,������֯�������˵���Ϣ������session��
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public class ChooseAllPersonServlet extends ServletBase {
	protected void performTask(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException, java.io.IOException {
		String doFunction = "";
		String block = request.getParameter("block");
		if (request.getParameter("doFunction") != "") {
			doFunction = request.getParameter("doFunction");
		}
		String orguuid = "";
		if (request.getParameter("orguuid") != "") {
			orguuid = request.getParameter("orguuid");
		}

		//		System.out.println("  orguuid ="+orguuid);

		String sessionname = "";
		if (request.getParameter("sessionname") != "") {
			sessionname = request.getParameter("sessionname");
		}
		//System.out.println("ChooseAllPersonServlet_sessionname="+sessionname);

		int tempflag = 1; //����Ƿ�����ͬ��userid
		List selectorgpersonlist = new ArrayList();
		SysOrgpersonVO sysorgpersonvo = new SysOrgpersonVO();

		HttpSession session = request.getSession();
		selectorgpersonlist = (List) session.getAttribute(sessionname);
		if (selectorgpersonlist == null)
			selectorgpersonlist = new ArrayList();
		Connection conn = null;

		try {
			if ((this.getServletContext().getInitParameter(AddressConfig.ADDRESSJNDI) == null) || (this.getServletContext().getInitParameter(AddressConfig.ADDRESSJNDI).equals(""))) {
				conn = getConnection(com.icss.j2ee.util.Globals.DATASOURCEJNDI);
				ConnLog.open("ChooseAllPersonServlet");
			} else {
				conn = DBConnectionLocator.getInstance().getConnection(this.getServletContext().getInitParameter(AddressConfig.ADDRESSJNDI));
				ConnLog.open("ChooseAllPersonServlet");
			}
			SysOrgpersonHandler handler = new SysOrgpersonHandler(conn);
			List personlist = handler.getList(orguuid);
			Iterator personiterator = personlist.iterator();

			//			System.out.println("  personlistsize ="+personlist.size());

			while (personiterator.hasNext()) {
				sysorgpersonvo = (SysOrgpersonVO) personiterator.next();
				tempflag = 1;
				if (!selectorgpersonlist.isEmpty()) {
					Iterator iterator = selectorgpersonlist.iterator();
					while (iterator.hasNext()) {
						SelectOrgpersonVO vo = (SelectOrgpersonVO) iterator.next();
						//System.out.println("sysorgpersonvo.getUserid()="+sysorgpersonvo.getUserid());
						//System.out.println("vo.getUserid()="+vo.getUserid());
						if (sysorgpersonvo.getUserid().equals(vo.getUserid())) {
							tempflag = 0; //����Ƿ�����ͬ��userid
						} //if
					} //while
				}
				//���session������ͬ��selectorgpersonvo����д��
				System.out.println("tempflag=" + tempflag);
				if (tempflag == 1) {
					SelectOrgpersonVO selectorgpersonvo = new SelectOrgpersonVO();
					//��voд��id��Ϣ
					selectorgpersonvo.setUserid(sysorgpersonvo.getUserid());
					//��voд��name��Ϣ
					selectorgpersonvo.setName(sysorgpersonvo.getCnname());
					//��voд��useruuid��Ϣ
					selectorgpersonvo.setUseruuid(sysorgpersonvo.getPersonuuid());
					selectorgpersonvo.setIsperson("1");

					//							System.out.println(sysorgpersonvo.getCnname());
					//							System.out.println("***************");
					//							System.out.println(sysorgpersonvo.getCnname());
					//							System.out.println("***************");

					selectorgpersonlist.add(selectorgpersonvo);
				}
			} //while
			//				��list��Ϣд�뵽session��
			//				if (!selectorgpersonlist.isEmpty()){
			//					System.out.println("list is not null!");
			//				}else{
			//					System.out.println("list is null!");
			//				}
			request.setAttribute(sessionname, selectorgpersonlist);
			session.setAttribute(sessionname, selectorgpersonlist);
			if("ftp".equals(block)){
				this.forward(request, response, "/address/FTP/selected.jsp?doFunction=" + doFunction + "&sessionname=" + sessionname);
			}else{
				this.forward(request, response, "/address/pubaddress/selected.jsp?doFunction=" + doFunction + "&sessionname=" + sessionname);
			}


		} catch (IllegalStateException e) {
			e.printStackTrace();

		} catch (Exception ex) {
			ex.printStackTrace();
			handleError(ex);

		} finally {
			try {
				if (conn != null){
					conn.close();
					ConnLog.close("ChooseAllPersonServlet");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			//
		}
	}

}
