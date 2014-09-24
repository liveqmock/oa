// Decompiled Using: FrontEnd Plus v2.03 and the JAD Engine
// Available From: http://www.reflections.ath.cx
// Decompiler options: packimports(3) 
// Source File Name:   ShowDeptArticleServlet.java

package com.icss.oa.bbs.user.control;

import com.icss.common.log.ConnLog;
import com.icss.j2ee.services.DBConnectionLocator;
import com.icss.j2ee.servlet.ServletBase;
import com.icss.oa.bbs.handler.*;
import com.icss.oa.bbs.vo.BbsBoardVO;
import com.icss.resourceone.common.logininfo.UserInfo;
import com.icss.resourceone.sdk.framework.Context;

import java.io.IOException;
import java.io.PrintStream;
import java.sql.Connection;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ShowDeptArticleServlet extends ServletBase
{

    protected void performTask(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException
    {
        Connection conn = null;
        java.util.List list = null;
        String stopicId = request.getParameter("topicId");
        System.err.println("!!!!stopicId=" + stopicId);
        Integer topicId = new Integer(stopicId);
        String sboardId = request.getParameter("boardId");
        Integer boardId = new Integer(sboardId);
        String hitFlag = request.getParameter("hitFlag");
        BbsBoardVO vo = new BbsBoardVO();
        String isview = request.getParameter("isview");
        com.icss.oa.bbs.vo.BbsUserinfoVO userVO = null;
        java.util.List rightList = null;
        try
        {
            conn = DBConnectionLocator.getInstance().getConnection("java:comp/env/ResourceOne/DataSource");
            ConnLog.open("ShowArticleServlet");
            BBSHandler handler = new BBSHandler(conn);
            
            Context ctx = Context.getInstance();
			UserInfo user = ctx.getCurrentLoginInfo();
			String uuid = user.getPersonUuid();
			int day = handler.getOutDay(uuid);
			if(day > 0 ){
			request.setAttribute("day", day);
			this.forward(request, response, "/bbs/noLogin.jsp");
			}
			
			
            UserMsgHandler userMsghandler = new UserMsgHandler(conn);
            BoardArticleUpdate update = new BoardArticleUpdate(conn);
            userVO = userMsghandler.getUserVO(userMsghandler.getUserId());
            list = handler.getArticleList(topicId, isview);
            vo = handler.getBoardVO(boardId);
            String maintopic = handler.getMainTopic(topicId);
            if(hitFlag.equals("1"))
                update.updateArticleByHit(topicId);
            Integer numperpage = new Integer(getInitParameter("_rowcount_per_page"));
            request.setAttribute("numperpage", numperpage);
            request.setAttribute("articleList", list);
            request.setAttribute("maintopic", maintopic);
            request.setAttribute("boardVO", vo);
            request.setAttribute("stopicId", stopicId);
            request.setAttribute("userVO", userVO);
            request.setAttribute("rightList", rightList);
            forward(request, response, "/bbs/deptArticle.jsp");
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
                    ConnLog.close("ShowArticleServlet");
                }
            }
            catch(Exception e1)
            {
                e1.printStackTrace();
            }
        }
        return;
    }
}