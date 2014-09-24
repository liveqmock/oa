/*
 * Created on 2004-3-4
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.icss.oa.addressbook.admin;

import java.io.IOException;
import java.sql.Connection;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.common.log.ConnLog;
import com.icss.j2ee.services.DBConnectionLocator;
import com.icss.j2ee.servlet.ServletBase;
import com.icss.j2ee.util.Globals;
import com.icss.oa.bbs.handler.UserMsgHandler;
import com.icss.oa.addressbook.handler.AddressbookHandler;
import com.icss.oa.addressbook.vo.AddressbookContentVO;
import com.icss.oa.addressbook.vo.AddressbookManagerVO;

/**
 * @author Administrator
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class UpdateAddressbookServlet extends ServletBase {

	protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection conn = null;

		Integer parentId = new Integer(request.getParameter("parentId"));
		Integer abcId = new Integer(request.getParameter("abcid"));

		String abcName = request.getParameter("abcname");
		String abcCompany = request.getParameter("abccompany");
		String abcCompanyaddress = request.getParameter("abccompanyaddress");
		String abcFamilyaddress = request.getParameter("abcfamilyaddress");
		String abcCompanytel = request.getParameter("abccompanytel");
		String abcFamilytel = request.getParameter("abcfamilytel");
		String abcCellphone = request.getParameter("abccellphone");
		String abcEmail = request.getParameter("abcemail");
		String abcEmailsec = request.getParameter("abcemailsec");
		String abcMemo = request.getParameter("abcmemo");
		String abcLevel = request.getParameter("abclevel");

		//if (abcName.equals("")) //如果重名，报出错
		//	this.forward(
		//		request,
		//		response,
		//"/folder/error.jsp?errormsg=重名");
		//		  "/addressbook/updateAddrb.jsp?parentid="+parentId+"&abcid="+abcId+"&abcname="+abcName+"&abccompany"+abcCompany+"&abccompanyaddress="+abcCompanyaddress+"&abcfamilyaddress="+abcFamilyaddress+"&abccompanytel"+abcCompanytel+"&abcfamilytel="+abcFamilytel+"&abccellphone="+abcCellphone+"&abcemail"+abcEmail+"&abcemailsec="+abcEmailsec+"&abclevel="+abcLevel+"&abcmemo"+abcMemo);

		try {
			conn = DBConnectionLocator.getInstance().getConnection(Globals.DATASOURCEJNDI);
			ConnLog.open("UpdateAddressbookServlet");
			AddressbookHandler handler = new AddressbookHandler(conn);
			UserMsgHandler userHandler = new UserMsgHandler(conn);
			String userId = userHandler.getUserId();
			List abmList = handler.getUserList(userId);
			Iterator it = abmList.iterator();
			AddressbookManagerVO abmvo = null;

			if (it.hasNext()) {
				abmvo = (AddressbookManagerVO) it.next();
			}

			AddressbookContentVO vo = new AddressbookContentVO();

			vo.setAbcName(abcName);
			vo.setAbcCompany(abcCompany);
			vo.setAbcCompanyaddress(abcCompanyaddress);
			vo.setAbcFamilyaddress(abcFamilyaddress);
			vo.setAbcCompanytel(abcCompanytel);
			vo.setAbcFamilytel(abcFamilytel);
			vo.setAbcCellphone(abcCellphone);
			vo.setAbcEmail(abcEmail);
			vo.setAbcEmailsec(abcEmailsec);
			vo.setAbcMemo(abcMemo);
			vo.setAbcLever(abcLevel);
			vo.setAbcUpdatetime(new Long(System.currentTimeMillis()));
			handler.updateAddressbook(vo, abcId);
			if (parentId.intValue() == 1)
				this.forward(request, response, "/servlet/ShowAddrbookRootFolderServlet");
			else
				this.forward(request, response, "/servlet/ShowAddressbookListServlet?parentId=" + parentId);

		} catch (Exception e) {
			handleError(e);
		} finally {
			try {
				if (conn != null) {
					conn.close();
					ConnLog.close("UpdateAddressbookServlet");
				}
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}

	}
}
