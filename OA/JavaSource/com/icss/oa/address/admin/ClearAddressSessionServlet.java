package com.icss.oa.address.admin;

import javax.servlet.http.*;
import java.util.*;
import java.sql.Connection;

import com.icss.j2ee.services.DBConnectionLocator;
import com.icss.j2ee.servlet.ServletBase;

import com.icss.oa.address.handler.*;
import com.icss.oa.address.vo.*;
import com.icss.oa.config.AddressConfig;
/**
 * 得到组织的uuid,将本组织下所有人的信息都放入session中
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public class ClearAddressSessionServlet extends ServletBase {
	protected void performTask(
		HttpServletRequest request,
		HttpServletResponse response)
		throws javax.servlet.ServletException, java.io.IOException {
		String doFunction = "";
		if (request.getParameter("doFunction") != "") {
			doFunction = request.getParameter("doFunction");
		}

//		System.out.println("  orguuid ="+orguuid);
		
		String sessionname = "";
		if (request.getParameter("sessionname") != "") {
			sessionname = request.getParameter("sessionname");
		}
		
		HttpSession tempsession = request.getSession();
		tempsession.removeAttribute(sessionname);
		
		//System.out.println("ChooseAllPersonServlet_sessionname="+sessionname);

			this.forward(
				request,
				response,
				"/address/pubaddress/selected.jsp?doFunction="
					+ doFunction
					+ "&sessionname="
					+ sessionname);
		
	}

}
