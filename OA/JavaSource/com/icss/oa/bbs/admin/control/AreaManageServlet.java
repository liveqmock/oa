// Decompiled Using: FrontEnd Plus v2.03 and the JAD Engine
// Available From: http://www.reflections.ath.cx
// Decompiler options: packimports(3) 
// Source File Name:   AreaManageServlet.java

package com.icss.oa.bbs.admin.control;

import com.icss.common.log.ConnLog;
import com.icss.j2ee.services.DBConnectionLocator;
import com.icss.j2ee.servlet.ServletBase;
import com.icss.oa.bbs.handler.*;
import com.icss.oa.bbs.vo.BbsAreaVO;
import com.icss.oa.bbs.vo.BbsAreaccVO;
import java.io.IOException;
import java.sql.Connection;
import java.util.*;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AreaManageServlet extends ServletBase
{

    public AreaManageServlet()
    {
    }

    protected void performTask(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException
    {
        Connection conn = null;
        List boardList = null;
        List subareaList = null;
        List managerList = null;
        try
        {
            conn = DBConnectionLocator.getInstance().getConnection("java:comp/env/ResourceOne/DataSource");
            ConnLog.open("AreaManageServlet");
            BBSHandler handler = new BBSHandler(conn);
            boardList = handler.getBoardList();
            subareaList = handler.getSubareaList();
            managerList = handler.getManagerList();
            request.setAttribute("boardList", boardList);
            request.setAttribute("subareaList", subareaList);
            request.setAttribute("managerList", managerList);
            request.setAttribute("serverTime", new Long(System.currentTimeMillis()));
            BBSAreaHandler ahandler = new BBSAreaHandler(conn);
            List adminList = ahandler.findAdministrator();
            String SuperAdminIds = "";
            for(int a = 0; a < adminList.size(); a++)
            {
                BbsAreaccVO accvo = (BbsAreaccVO)adminList.get(a);
                SuperAdminIds = SuperAdminIds + accvo.getUserid() + "||";
            }

            request.setAttribute("SuperAdminIds", SuperAdminIds);
            List areaManagerList = new ArrayList();
            Map areaManagerMap = new HashMap();
            for(int i = 0; i < subareaList.size(); i++)
            {
                BbsAreaVO vo = (BbsAreaVO)subareaList.get(i);
                areaManagerList = ahandler.findManagerListByAreaId(vo.getAreaid());
                String uuid = "";
                for(int j = 0; j < areaManagerList.size(); j++)
                {
                    BbsAreaccVO accvo = (BbsAreaccVO)areaManagerList.get(j);
                    uuid = uuid + accvo.getUserid() + "||";
                }

                areaManagerMap.put(vo.getAreaid(), uuid);
            }

            request.setAttribute("areaManagerMap", areaManagerMap);
            UserMsgHandler userMsghandler = new UserMsgHandler(conn);
            String userId = userMsghandler.getUserId();
            request.setAttribute("cUserId", userId);
            forward(request, response, "/bbs/areaManage.jsp");
        }
        catch(Exception e)
        {
            e.printStackTrace();
            handleError(e);
        }
        finally
        {
            try
            {
                if(conn != null)
                {
                    conn.close();
                    ConnLog.close("AreaManageServlet");
                }
            }
            catch(Exception e1)
            {
                e1.printStackTrace();
            }
        }
    }
}