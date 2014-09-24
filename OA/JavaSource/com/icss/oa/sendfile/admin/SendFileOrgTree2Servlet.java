/*
 * Created on 2004-4-21
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package com.icss.oa.sendfile.admin;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.icss.common.log.ConnLog;
import com.icss.j2ee.services.DBConnectionLocator;
import com.icss.j2ee.services.ServiceLocatorException;
import com.icss.j2ee.servlet.ServletBase;
import com.icss.oa.address.handler.Group;
import com.icss.oa.config.AddressConfig;
import com.icss.oa.hr.admin.HRGroupWebservice;
import com.icss.oa.hr.vo.HRGroupVO;
import com.icss.orgtree.handler.OrgHandler;
import com.icss.orgtree.vo.SysOrgVO;
import com.icss.resourceone.common.logininfo.UserInfo;
import com.icss.resourceone.sdk.framework.Context;
import com.icss.resourceone.sdk.framework.EntityException;

/**
 * @author Administrator
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public class SendFileOrgTree2Servlet extends ServletBase {
	/* (non-Javadoc)
	 * @see com.icss.j2ee.servlet.ServletBase#performTask(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	protected void performTask(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String sendMail = request.getParameter("sendMail") == null ? "0"
				: request.getParameter("sendMail");
		String isCms = request.getParameter("isCms") == null ? "0"
				: request.getParameter("isCms");
		Connection conn = null;
		try {
			//conn=this.getConnection(Globals.DATASOURCEJNDI);
			if ((this.getServletContext().getInitParameter(
					AddressConfig.ADDRESSJNDI) == null)
					|| (this.getServletContext().getInitParameter(
							AddressConfig.ADDRESSJNDI).equals(""))) {
				conn = getConnection(com.icss.j2ee.util.Globals.DATASOURCEJNDI);
				ConnLog.open("OrgTree2Servlet");
			} else {
				conn = DBConnectionLocator.getInstance().getConnection(
						this.getServletContext().getInitParameter(
								AddressConfig.ADDRESSJNDI));
				ConnLog.open("OrgTree2Servlet");
			}
			OrgHandler handler = new OrgHandler(conn);
			SysOrgVO topOrgVO = handler.getTopOrg();
			List list = handler.getOrgTreeList(topOrgVO.getOrguuid());
			request.setAttribute("list", list);
			request.setAttribute("topOrgVO", topOrgVO);

			//取个人分组列表信息 
			Context ctx = Context.getInstance();
			UserInfo user = ctx.getCurrentLoginInfo();

			Group group = new Group(conn);
			List indiGroup = group.individualGroupList(user.getPersonUuid());
			request.setAttribute("indiGroup", indiGroup);
			
			group = new Group(conn);
			indiGroup = group.commonGroupList(user.getPersonUuid());
			request.setAttribute("oacommonGroup", indiGroup);

			//取公共分组列表信息 
			long lasting = System.currentTimeMillis();
			HRGroupWebservice hw = new HRGroupWebservice();
			String hrgroup = hw.GetPersonGroup();

			List toplist = new ArrayList();
			List parentlist = new ArrayList();

			Document document = DocumentHelper.parseText(hrgroup);
			Element rootElement = document.getRootElement();

			//取得所有子节点的id
			List plist = document.selectNodes("/PERSONGROUPS/GROUP/PARENTID");
			Iterator it = plist.iterator();

			while (it.hasNext()) {
				Element elm = (Element) it.next();
				if (elm.hasContent()) {
					parentlist.add(elm.getText());
				}
			}

			//取得最上层的节点
			for (Iterator i_pe = rootElement.elementIterator(); i_pe.hasNext();) {
				Element e_pe = (Element) i_pe.next();

				if (!e_pe.element("PARENTID").hasContent()) {
					HRGroupVO vo = new HRGroupVO();
					vo.setGroupid(e_pe.element("GROUPID").getText());
					vo.setOrgname(e_pe.element("ORGNAME").getText());
					vo.setDescription(e_pe.element("DESCRIPTION").getText());

					toplist.add(vo);
				}
			}

			HttpSession session = request.getSession();
			session.setAttribute("parentlist", parentlist);
			session.setAttribute("hrgroup", hrgroup);
			session.setAttribute("toplist", toplist);
			request.setAttribute("isCms", isCms);
			request.setAttribute("toplist", toplist);
			request.setAttribute("parentlist", parentlist);


			System.out.println("SendFileOrgTree2Servlet时间 = "
					+ (System.currentTimeMillis() - lasting));

			this.forward(request, response,
					"/address/sendfile/orgTree2.jsp?sendMail=" + sendMail);
		} catch (EntityException e) {
			e.printStackTrace();
			handleError(e);
		} catch (ServiceLocatorException e) {
			e.printStackTrace();
			handleError(e);
		} catch (Exception e) {
			e.printStackTrace();
			handleError(e);
		} finally {
			try {
				if (conn != null) {
					conn.close();
					ConnLog.close("OrgTree2Servlet");
				}
			} catch (SQLException sqle) {
			}
		}
	}
}
