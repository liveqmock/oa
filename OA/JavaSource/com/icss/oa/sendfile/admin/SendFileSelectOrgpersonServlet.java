package com.icss.oa.sendfile.admin;

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
import com.icss.oa.address.handler.SysOrgpersonHandler;
import com.icss.oa.address.vo.AddressCommongroupVO;
import com.icss.oa.address.vo.AddressGroupVO;
import com.icss.oa.address.vo.SelectOrgpersonVO;
import com.icss.oa.address.vo.SysOrgpersonVO;
import com.icss.oa.config.AddressConfig;
/**
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public class SendFileSelectOrgpersonServlet extends ServletBase {
	protected void performTask(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException, java.io.IOException {
		int tempflag = 1;
		List selectorgpersonlist = new ArrayList();
		SysOrgpersonVO sysorgpersonvo = new SysOrgpersonVO();
		AddressCommongroupVO addresscommongroupvo = new AddressCommongroupVO();
		AddressGroupVO addressgroupvo = new AddressGroupVO();
		HttpSession session = request.getSession();
		selectorgpersonlist = (List) session.getAttribute("selectorgpersonlist");
		if (selectorgpersonlist == null)
			selectorgpersonlist = new ArrayList();
		Connection conn = null;

		//System.out.println("servlet_doFunction="+doFunction);

		try {
			if ((this.getServletContext().getInitParameter(AddressConfig.ADDRESSJNDI) == null) || (this.getServletContext().getInitParameter(AddressConfig.ADDRESSJNDI).equals(""))) {
				conn = getConnection(com.icss.j2ee.util.Globals.DATASOURCEJNDI);
				ConnLog.open("SelectOrgpersonServlet");
			} else {
				conn = DBConnectionLocator.getInstance().getConnection(this.getServletContext().getInitParameter(AddressConfig.ADDRESSJNDI));
				ConnLog.open("SelectOrgpersonServlet");
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
							if (userid[i].equals(vo.getUserid())) {
								tempflag = 0; //����Ƿ�����ͬ��userid
							} //if
						} //while
					}
					//���session������ͬ��selectorgpersonvo����д��
					System.out.println("tempflag=" + tempflag);
					if (tempflag == 1) {
						SelectOrgpersonVO selectorgpersonvo = new SelectOrgpersonVO();
						//��voд��id��Ϣ
						selectorgpersonvo.setUserid(userid[i]);
						//�ж�����Ա���Ƿ���
						if (request.getParameter("isperson").equals("1")) {
							//����userid���ҳ�user��Ϣ
							SysOrgpersonHandler handler = new SysOrgpersonHandler(conn);
							List list = handler.getbyuserid(userid[i]);
							Iterator result = list.iterator();
							while (result.hasNext()) {
								sysorgpersonvo = (SysOrgpersonVO) result.next();
							}
							//��voд��name��Ϣ
							selectorgpersonvo.setName(sysorgpersonvo.getCnname());
							selectorgpersonvo.setIsperson("1");
							//								System.out.println(sysorgpersonvo.getCnname());
						} else {
							if (request.getParameter("isperson").equals("00")) {
								//����userid���ҳ�����Group��Ϣ
								Group handler = new Group(conn);
								addresscommongroupvo = handler.getCommonGroup(Integer.valueOf(userid[i]));
								//��voд��name��Ϣ
								selectorgpersonvo.setName(addresscommongroupvo.getGroupname());
								selectorgpersonvo.setIsperson("00");
								//									System.out.println("commongroup="+addresscommongroupvo.getGroupname());
							} else {
								//����userid���ҳ�����Group��Ϣ
								Group handler = new Group(conn);
								addressgroupvo = handler.getIndiGroup(Integer.valueOf(userid[i]));
								//��voд��name��Ϣ
								selectorgpersonvo.setName(addressgroupvo.getGroupname());
								selectorgpersonvo.setIsperson("01");
								//									System.out.println("addressgroup="+addressgroupvo.getGroupname());

							}
						}
						//							System.out.println("***************");
						//							System.out.println(userid[i]);
						//							System.out.println("***************");

						selectorgpersonlist.add(selectorgpersonvo);
					}
				} //for
			}
			//��list��Ϣд�뵽session��
			//				if (!selectorgpersonlist.isEmpty()){
			//					System.out.println("list is not null!");
			//				}else{
			//					System.out.println("list is null!");
			//				}
			request.setAttribute("selectorgpersonlist", selectorgpersonlist);
			session.setAttribute("selectorgpersonlist", selectorgpersonlist);

			this.forward(request, response, "/address/sendfile/selected.jsp");
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (Exception ex) {
			handleError(ex);
		} finally {
			try {
				if (conn != null){
					conn.close();
					ConnLog.close("SelectOrgpersonServlet");
				}
			} catch (Exception e) {

			}
			//
		}
	}

}
