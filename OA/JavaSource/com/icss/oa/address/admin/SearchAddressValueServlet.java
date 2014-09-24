package com.icss.oa.address.admin;

import javax.servlet.http.*;
import java.util.*;

import com.icss.j2ee.servlet.ServletBase;
import com.icss.resourceone.sdk.framework.*;


/**
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public class SearchAddressValueServlet extends ServletBase {
	protected void performTask(HttpServletRequest request, HttpServletResponse response)
		throws javax.servlet.ServletException, java.io.IOException {
			
			try {
				String orgid = request.getParameter("orgid");

				EntityManager entitymanager = EntityManager.getInstance();
				List personList = entitymanager.findPersonsRecursivelyByOrgUuid(orgid);
				request.setAttribute("personlist", personList);
				this.forward(request, response, "/address/pubaddress/pubaddress.jsp");
			}
			catch (Exception ex) {
				handleError(ex);
			}  
		}
}
