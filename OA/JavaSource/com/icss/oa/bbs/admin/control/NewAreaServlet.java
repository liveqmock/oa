// Decompiled Using: FrontEnd Plus v2.03 and the JAD Engine
// Available From: http://www.reflections.ath.cx
// Decompiler options: packimports(3) 
// Source File Name:   NewAreaServlet.java

package com.icss.oa.bbs.admin.control;

import com.icss.common.log.ConnLog;
import com.icss.j2ee.services.DBConnectionLocator;
import com.icss.j2ee.servlet.ServletBase;
import com.icss.oa.address.handler.AddressHelp;
import com.icss.oa.address.vo.SelectOrgpersonVO;
import com.icss.oa.bbs.handler.*;
import com.icss.oa.bbs.user.control.InitializeUser;
import com.icss.oa.bbs.vo.BbsAreaVO;
import java.io.IOException;
import java.sql.Connection;
import java.util.Iterator;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.http.*;

public class NewAreaServlet extends ServletBase
{

    public NewAreaServlet()
    {
    }

    protected void performTask(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException
    {
        Connection conn = null;
        String areaName = request.getParameter("areaName");
        String areaDes = request.getParameter("areaDes");
        String arearight = request.getParameter("areaRight");
        BbsAreaVO vo = new BbsAreaVO();
        vo.setAreades(areaDes);
        vo.setAreaname(areaName);
        vo.setArearight(arearight);
        System.out.println("&&&&&&&&&&&&&&&&&set arearight "+arearight);
        System.out.println("&&&&&&&&&&&&&&&&&set arearight "+vo.getArearight());
        try
        {
            conn = DBConnectionLocator.getInstance().getConnection("java:comp/env/ResourceOne/DataSource");
            ConnLog.open("NewAreaServlet");
            BBSHandler handler = new BBSHandler(conn);
            //新建分区
            BBSAreaHandler ahandler = new BBSAreaHandler(conn);
            Integer areaid = handler.newArea(vo);
            
            UserMsgHandler uhandler = new UserMsgHandler(conn);
            HttpSession session = request.getSession();
            //分区管理员
            List managerList = (List)session.getAttribute("manager");
            AddressHelp adressHelp = new AddressHelp(conn);
            if(managerList != null)
                managerList = adressHelp.getperson(managerList, request, "manager");
            if(managerList != null)
            {
                com.icss.oa.bbs.vo.BbsUserinfoVO userVO = null;
                String userId;
                for(Iterator it = managerList.iterator(); it.hasNext(); ahandler.newAreaManager(areaid, userId))
                {
                    SelectOrgpersonVO selectUserVO = (SelectOrgpersonVO)it.next();
                    userId = selectUserVO.getUseruuid();
                    userVO = uhandler.getUserVO(userId);
                    String lastip = request.getRemoteAddr();
                    if(userVO == null)
                    {
                        InitializeUser initializeUser = new InitializeUser(conn);
                        userVO = initializeUser.initialize(selectUserVO.getUseruuid(), selectUserVO.getName(), lastip);
                    }
                }

            }
            forward(request, response, "/bbs/areaAddClose.jsp");
        }
        catch(Exception e)
        {
            handleError(e);
        }
        finally
        {
            try
            {
                if(conn != null)
                {
                    conn.close();
                    ConnLog.close("NewAreaServlet");
                }
            }
            catch(Exception e1)
            {
                e1.printStackTrace();
            }
        }
    }
}