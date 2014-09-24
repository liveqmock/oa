/*
 * Created on 2004-4-21
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package com.icss.oa.sendfile.admin;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

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
public class SendFileTwoGuoupXmlServlet extends ServletBase {
    
	public void doGet(
		HttpServletRequest request, 
		HttpServletResponse response) 
		throws IOException{
		String flag = request.getParameter("flag");
		String parentid =request.getParameter("ParentID");
		StringBuffer outSB = new StringBuffer();
		Connection conn=null;
		PrintWriter write = null;
	try{	
		if("oa".equalsIgnoreCase(flag))
		{
			Integer parentID = new Integer(parentid);
			
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
				String text=new String(isName.getBytes("GBK"),"ISO8859_1");
				outSB.append("<tree text=\"").append(text).append("\"");
				outSB.append(" action=\"");
                outSB.append("SendFileSelectCommonPersonServlet?groupid="+vo.getId()+"&amp;shouquan="+vo.getNeedaccredit()+"&amp;ParentID="+parentID+"&amp;groupid1="+parentID);
				outSB.append("\"");
				outSB.append("/>");
			}
			outSB.append("</tree>");
		}
		else
		{
			HttpSession session = request.getSession();
			List parentlist =(List)session.getAttribute("parentlist");
			String hrgroup = (String)session.getAttribute("hrgroup");
			
			outSB.append("<?xml version=\"1.0\" encoding=\"GBK\"?>");
			outSB.append("<tree>");
			
			//HRGroupWebservice hw = new HRGroupWebservice();
			//String group = hw.GetPersonGroup();

				Document document = DocumentHelper.parseText(hrgroup);
				Element rootElement = document.getRootElement();
					
				for (Iterator i_pe = rootElement.elementIterator(); i_pe.hasNext();) {
					Element e_pe = (Element) i_pe.next(); 
		            if(e_pe.element("PARENTID").getText().equals(parentid)){
		            	String name = new String(e_pe.element("ORGNAME").getText().getBytes("GBK"), "ISO8859_1");
						outSB.append("<tree text=\"").append(name).append("\"");
						if(parentlist.contains(e_pe.element("GROUPID").getText())){
						outSB.append(" src=\"../SendFileTwoGuoupXmlServlet.xml?ParentID=").append(e_pe.element("GROUPID").getText()).append("\"");
						outSB.append(" action=\"");
						outSB.append("../servlet/SendFileSelectCommonTwoLevelGroupServlet?groupid=").append(e_pe.element("GROUPID").getText()).append("&amp;parentid=").append(e_pe.element("PARENTID").getText());
						outSB.append("\"");
						}else{
						outSB.append(" action=\"");
		                outSB.append("SendFileSelectCommonPersonServlet?groupid="+e_pe.element("GROUPID").getText()+"&amp;groupid1="+e_pe.element("PARENTID").getText());
		                outSB.append("\"");
						}
						
		                outSB.append("/>");
						
		            }
				}
				
				outSB.append("</tree>");
		}
		
			
			response.setContentType("text/xml");
		    write = response.getWriter();
			write.write(outSB.toString());
			write.flush();
			
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
	finally
	{
		if(write!=null)
		{
			write.close();
		}
		try {
			if(conn!=null&&!conn.isClosed())
			{
				conn.close();
			}
		} catch (SQLException e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}
	}
		
		}
		protected void performTask(
				HttpServletRequest arg0, 
				HttpServletResponse arg1) 
			throws ServletException, IOException {			
		}	
	}