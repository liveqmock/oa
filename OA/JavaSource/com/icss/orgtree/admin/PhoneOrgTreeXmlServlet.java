// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PhoneOrgTreeXmlServlet.java

package com.icss.orgtree.admin;

import com.icss.common.log.ConnLog;
import com.icss.j2ee.services.ServiceLocatorException;
import com.icss.j2ee.servlet.ServletBase;
import com.icss.j2ee.util.Globals;
import com.icss.orgtree.handler.HandlerException;
import com.icss.orgtree.handler.OrgHandler;
import com.icss.orgtree.vo.SysOrgTreeVO;
import com.icss.orgtree.vo.SysOrgVO;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PhoneOrgTreeXmlServlet extends ServletBase
{

    public PhoneOrgTreeXmlServlet()
    {
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
        throws IOException
    {
        StringBuffer outSB;
        StringBuffer realUrl = new StringBuffer();
        outSB = new StringBuffer();
        String orgId = request.getParameter("orgId");
        String nodeUrl = request.getParameter("nodeUrl");
        outSB.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
        outSB.append("<tree>");
        Connection conn = null;
        List list = null;
        OrgHandler handler = null;
        try
        {
            conn = getConnection(Globals.DATASOURCEJNDI);
            ConnLog.open("PhoneOrgTreeXmlServlet");
            handler = new OrgHandler(conn);
            list = handler.getOrgTreeList(orgId);
            if(list != null)
            {
                for(Iterator it = list.iterator(); it.hasNext(); outSB.append("/>"))
                {
                    SysOrgTreeVO vo = (SysOrgTreeVO)it.next();
                    String corgId = vo.getVO().getOrguuid();
                    String corgName = vo.getVO().getCnname();
                    outSB.append("<tree text=\"").append(corgName).append("\"");
                    if(vo.getHasChild())
                        outSB.append(" src=\"../servlet/PhoneOrgTreeXmlServlet.xml?orgId=").append(corgId).append("&amp;nodeUrl=").append(nodeUrl).append("\"");
                    SysOrgVO orgvo = handler.getOrg(orgId);
                    SysOrgVO parentorgvo = orgvo;
                    String parentorgid = parentorgvo.getOrguuid();
                    String parentorgname = parentorgvo.getCnname();
                    int parentorglevel = orgvo.getOrglevel().intValue();
                    if(orgvo.getOrglevel().intValue() > 3)
                        for(parentorglevel = orgvo.getOrglevel().intValue(); parentorglevel >= 3; parentorglevel = parentorgvo.getOrglevel().intValue())
                        {
                            if(parentorglevel == 3)
                            {
                                parentorgid = parentorgvo.getOrguuid();
                                parentorgname = parentorgvo.getCnname();
                                break;
                            }
                            parentorgvo = handler.getOrg(parentorgvo.getParentorguuid());
                        }

                    if(orgvo.getOrglevel().intValue() >= 3)
                        outSB.append(" action=\"").append(nodeUrl).append("?detail=1&amp;orgname=" + parentorgname + "&amp;deptname=").append(corgName).append("\"");
                    else
                        outSB.append(" action=\"").append(nodeUrl).append("?detail=1&amp;orgname=" + corgName).append("\"");
                }

            }
        }
        catch(ServiceLocatorException e)
        {
            e.printStackTrace();
        }
        catch(HandlerException e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                if(conn != null)
                {
                    conn.close();
                    ConnLog.close("PhoneOrgTreeXmlServlet");
                }
            }
            catch(SQLException sqlexception) { }
        }
        outSB.append("</tree>");
        response.setContentType("text/xml;charset=UTF-8");
        PrintWriter write = response.getWriter();
        write.write(outSB.toString());
        write.flush();
        write.close();
        return;
    }

    protected void performTask(HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse)
        throws ServletException, IOException
    {
    }
}
