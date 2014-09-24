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
import com.icss.oa.addressbook.vo.AddressbookFolderVO;
import com.icss.oa.addressbook.vo.AddressbookManagerVO;

/**
 * @author Administrator
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class AddAddressbookServlet extends ServletBase {

	protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection conn = null;

		Integer parentId = new Integer(request.getParameter("parentId"));
		String username = request.getParameter("username");
		String company = request.getParameter("company");
		String companyaddress = request.getParameter("companyaddress");
		String familyaddress = request.getParameter("familyaddress");
		String companytel = request.getParameter("companytel");
		String familytel = request.getParameter("familytel");
		String cellphone = request.getParameter("cellphone");
		String email = request.getParameter("email");
		String lever = request.getParameter("lever");
		String emailsec = request.getParameter("emailsec");
		String memo = request.getParameter("abcmemo");

		//if (folderName.equals("")) //如果重名，报出错
		//	this.forward(
		//		request,
		//		response,
		//"/folder/error.jsp?errormsg=重名");
		//	  "/addressbook/addFolder.jsp?parentId="+parentId+"&folderName="+folderName+"&folderDes"+folderDes);

		try {
			conn = DBConnectionLocator.getInstance().getConnection(Globals.DATASOURCEJNDI);
			ConnLog.open("AddAddressbookServlet");
			AddressbookHandler handler = new AddressbookHandler(conn);
			UserMsgHandler userHandler = new UserMsgHandler(conn);
			String userId = userHandler.getUserId();
			List abmList = handler.getUserList(userId);
			Iterator it = abmList.iterator();
			AddressbookManagerVO abmvo = null;

			if (it.hasNext()) {
				abmvo = (AddressbookManagerVO) it.next();
			}

			//boolean hasSameName =
			//	handler.checkSameName(folderName, parentId, abmvo.getAbmId());
			//if (hasSameName == true) //如果重名，报出错
			//	this.forward(
			//		request,
			//		response,
			//		//"/folder/error.jsp?errormsg=重名");
			//		  "/addressbook/addFolder.jsp?parentId="+parentId+"&folderName="+folderName+"&folderDes"+folderDes);

			//else if (hasSameName == false) { //没有重名

			AddressbookFolderVO vo = new AddressbookFolderVO();
			//添加文件夹
			vo.setAddAbfId(parentId);
			//vo.setAbfName(folderName);
			//vo.setAbfDescript(folderDes);
			vo.setAbfCreatetime(new Long(System.currentTimeMillis()));
			vo.setAbfUpdatetime(new Long(System.currentTimeMillis()));
			vo.setAbfFlag("0");
			vo.setAddAbmId(abmvo.getAbmId());
			vo.setAbfId(handler.addFolder(vo));

			AddressbookContentVO abcvo = new AddressbookContentVO();
			abcvo.setAbcName(username);
			abcvo.setAddAbfcId(vo.getAbfId());
			//System.out.println("++++++++++++abfid+++++++++++++"+vo.getAbfId());
			abcvo.setAbcCompany(company);
			abcvo.setAbcCompanyaddress(companyaddress);
			abcvo.setAbcFamilyaddress(familyaddress);
			abcvo.setAbcCompanytel(companytel);
			abcvo.setAbcFamilytel(familytel);
			abcvo.setAbcCellphone(cellphone);
			abcvo.setAbcEmail(email);
			abcvo.setAbcLever(lever);
			abcvo.setAbcEmailsec(emailsec);
			abcvo.setAbcMemo(memo);
			abcvo.setAbcCreatetime(new Long(System.currentTimeMillis()));
			abcvo.setAbcUpdatetime(new Long(System.currentTimeMillis()));
			handler.addAddressbook(abcvo);

			if (parentId.intValue() == 1)
				response.sendRedirect("ShowAddrbookRootFolderServlet");
			else
				response.sendRedirect("ShowAddressbookListServlet?parentId=" + parentId);
			//}
		} catch (Exception e) {
			e.printStackTrace();
			handleError(e);
		} finally {
			try {
				if (conn != null){
					conn.close();
					ConnLog.close("AddAddressbookServlet");
				}
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}

	}
}
