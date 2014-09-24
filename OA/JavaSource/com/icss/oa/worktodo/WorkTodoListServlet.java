// Decompiled Using: FrontEnd Plus v2.03 and the JAD Engine
// Available From: http://www.reflections.ath.cx
// Decompiler options: packimports(3) 
// Source File Name:   WorkTodoListServlet.java

package com.icss.oa.worktodo;

import com.icss.j2ee.services.ServiceLocatorException;
import com.icss.j2ee.servlet.ServletBase;
import com.icss.oa.intendwork.handler.IntendWork;
import com.icss.oa.util.ParamUtils;
import com.icss.resourceone.common.logininfo.UserInfo;
import com.icss.resourceone.sdk.framework.Context;
import com.icss.resourceone.sdk.framework.EntityException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class WorkTodoListServlet extends ServletBase
{

    public WorkTodoListServlet()
    {
    }

    protected void performTask(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException
    {
        Connection conn = null;
        try
        {
            conn = getConnection("java:comp/env/ResourceOne/DataSource");
            String page_num = ParamUtils.getParameter(request, "_page_num", false);
            String re_nu = request.getParameter("re_nu")==null?"5":request.getParameter("re_nu");
            if(page_num == null)
                page_num = "";
            else
                page_num = "?_page_num=" + page_num;
            long startDate = ParamUtils.getDateTimeParameter(request, "startTime", 0L, 3);
            long endDate = ParamUtils.getDateTimeParameter(request, "endTime", System.currentTimeMillis(), 3);
            String flag = ParamUtils.getParameter(request, "flag", false);
            String topic = ParamUtils.getParameter(request, "topic", false);
            String type = request.getParameter("intype");
            Context ctx = Context.getInstance();
            UserInfo user = ctx.getCurrentLoginInfo();
            IntendWork intendWork = new IntendWork(conn);
            String sql = intendWork.getSearchSql(user.getUserID(), flag, type, startDate, endDate, topic);
            java.util.List workList = intendWork.searchWork(sql);
            request.setAttribute("workList", workList);
            request.setAttribute("re_nu", re_nu);
            forward(request, response, "/worktodo/worktodolist.jsp" + page_num);
        }
        catch(IOException e)
        {
            e.printStackTrace();
            handleError(e);
        }
        catch(EntityException e)
        {
            e.printStackTrace();
            handleError(e);
        }
        catch(ServiceLocatorException e)
        {
            e.printStackTrace();
            handleError(e);
        }
        finally
        {
            try
            {
                if(conn != null)
                    conn.close();
            }
            catch(SQLException e1)
            {
                e1.printStackTrace();
            }
        }
        return;
    }
}