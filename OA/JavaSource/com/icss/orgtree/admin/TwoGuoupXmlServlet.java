/*
 * Created on 2004-4-21
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package com.icss.orgtree.admin;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.j2ee.services.DBConnectionLocator;
import com.icss.j2ee.services.DBConnectionLocatorException;
import com.icss.j2ee.services.ServiceLocatorException;
import com.icss.j2ee.servlet.ServletBase;
import com.icss.oa.address.handler.Group;
import com.icss.oa.address.vo.AddressCommongroupVO;
import com.icss.oa.config.AddressConfig;

/**
 * @author Administrator
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public class TwoGuoupXmlServlet extends ServletBase {
    
	public void doGet(
		HttpServletRequest request, 
		HttpServletResponse response) 
		throws IOException{
			
			StringBuffer outSB = new StringBuffer();
			Integer parentID = new Integer(request.getParameter("ParentID"));
			String sessionname = request.getParameter("sessionname");
			String doFunction = request.getParameter("doFunction");
			outSB.append("<?xml version=\"1.0\" encoding=\"GBK\"?>");
			outSB.append("<tree>");
			Connection conn=null;
			
			if ((this.getServletContext().getInitParameter(AddressConfig.ADDRESSJNDI) == null) || (this.getServletContext().getInitParameter(AddressConfig.ADDRESSJNDI).equals(""))) {
				try {
                    conn = getConnection(com.icss.j2ee.util.Globals.DATASOURCEJNDI);
                } catch (ServiceLocatorException e) {
                    e.printStackTrace();
                }
			} else {
				try {
                    conn = DBConnectionLocator.getInstance().getConnection(this.getServletContext().getInitParameter(AddressConfig.ADDRESSJNDI));
                } catch (DBConnectionLocatorException e) {
                    e.printStackTrace();
                }
			}
			List list = null;
			Group group = new Group(conn);
			list = group.listCommonTwoGroup(parentID);
			
			
			Iterator it=list.iterator();
			while(it.hasNext()){
			    AddressCommongroupVO vo=(AddressCommongroupVO)it.next();
				String isName = vo.getGroupname();
				Integer int_no = vo.getId();
				String text=new String(isName.getBytes("GBK"),"ISO8859_1");
				outSB.append("<tree text=\"").append(text).append("\"");
				outSB.append(" action=\"");
                outSB.append("SelectCommonPersonServlet?doFunction="+doFunction+"&amp;groupid="+vo.getId()+"&amp;shouquan="+vo.getNeedaccredit()+"&amp;ParentID="+parentID+"&amp;doFunction="+sessionname+"&amp;groupid1="+parentID);
				outSB.append("\"");
				outSB.append("/>");
			}
			outSB.append("</tree>");
			
			response.setContentType("text/xml");
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException sqle) {
			}
			response.getWriter().write(outSB.toString());	
		}
		protected void performTask(
				HttpServletRequest arg0, 
				HttpServletResponse arg1) 
			throws ServletException, IOException {			
		}	
	}