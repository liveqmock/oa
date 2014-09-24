package com.icss.oa.tq;

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
import com.icss.j2ee.servlet.ServletBase;
import com.icss.oa.address.handler.Group;
import com.icss.oa.config.AddressConfig;
import com.icss.oa.hr.admin.HRGroupWebservice;
import com.icss.oa.hr.vo.HRGroupVO;
import com.icss.oa.tq.handler.TQHandler;
import com.icss.resourceone.common.logininfo.UserInfo;
import com.icss.resourceone.sdk.framework.Context;

public class GroupTreeServlet extends ServletBase {

	protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Connection conn = null;
		try {
			if ((this.getServletContext().getInitParameter(AddressConfig.ADDRESSJNDI) == null) || (this.getServletContext().getInitParameter(AddressConfig.ADDRESSJNDI).equals(""))) {
				conn = getConnection(com.icss.j2ee.util.Globals.DATASOURCEJNDI);
				ConnLog.open("GroupTreeServlet");
			} else {
				conn = DBConnectionLocator.getInstance().getConnection(this.getServletContext().getInitParameter(AddressConfig.ADDRESSJNDI));
				ConnLog.open("GroupTreeServlet");
			}
			
			String hwnd =request.getParameter("hwnd");
			
			//取个人分组列表信息
			Context ctx = Context.getInstance();
			UserInfo user = ctx.getCurrentLoginInfo();

			Group group = new Group(conn);
			List indiGroup = group.individualGroupList(user.getPersonUuid());
			request.setAttribute("indiGroup", indiGroup);

			//取公共分组列表信息
			
			HRGroupWebservice hw = new HRGroupWebservice();
			String hrgroup = hw.GetPersonGroup();
			Document document;
			List toplist= new ArrayList();
			List parentlist = new ArrayList();
			
			System.out.println("#########"+hrgroup);
				document = DocumentHelper.parseText(hrgroup);
				Element rootElement = document.getRootElement();
				
				//取得所有子节点的id
				List plist=document.selectNodes("/PERSONGROUPS/GROUP/PARENTID");
				Iterator it=plist.iterator();
			      
			      while(it.hasNext()){
			        Element elm=(Element)it.next(); 
			        if(elm.hasContent()){
			        parentlist.add(elm.getText());
			        }
			      }
			      
			    //取得最上层的节点
				for (Iterator i_pe = rootElement.elementIterator(); i_pe.hasNext();) {
					Element e_pe = (Element) i_pe.next(); 
		            
					if(!e_pe.element("PARENTID").hasContent()){
		            	HRGroupVO vo = new HRGroupVO();
						vo.setGroupid(e_pe.element("GROUPID").getText());
						vo.setOrgname(e_pe.element("ORGNAME").getText());
						vo.setDescription(e_pe.element("DESCRIPTION").getText());
						
						toplist.add(vo);
		            }
				}
					
				
				request.setAttribute("commonGroup", toplist);
				
				HttpSession session = request.getSession(); 
				session.setAttribute("parentlist", parentlist);
				session.setAttribute("hrgroup", hrgroup);
						
				
			
			//取得在线人员
			TQHandler handler = new TQHandler(conn);
			List userlist =handler.getOnlineUser();
			
			session.setAttribute("userlist", userlist);
			
			request.setAttribute("hwnd", hwnd);
			this.forward(request, response, "/tq/groupTree.jsp");
		} catch (Exception e) {
			e.printStackTrace();
			handleError(e);
		} finally {
			try {
				if (conn != null) {
					conn.close();
					ConnLog.close("GroupTreeServlet");
				}
			} catch (SQLException sqle) {
			}
		}
	}
}
