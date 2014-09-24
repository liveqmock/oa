// Decompiled Using: FrontEnd Plus v2.03 and the JAD Engine
// Available From: http://www.reflections.ath.cx
// Decompiler options: packimports(3) 
// Source File Name:   ShowDeptTopicServlet.java

package com.icss.oa.bbs.user.control;

import com.icss.common.log.ConnLog;
import com.icss.j2ee.services.DBConnectionLocator;
import com.icss.j2ee.servlet.ServletBase;
import com.icss.oa.bbs.handler.*;
import com.icss.oa.bbs.vo.*;
import com.icss.resourceone.common.logininfo.UserInfo;
import com.icss.resourceone.sdk.framework.Context;

import java.io.IOException;
import java.sql.Connection;
import java.util.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ShowDeptTopicServlet extends ServletBase
{

    protected void performTask(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException
    {
        Connection conn = null;
        List list = null;
        List noticeList = null;
        Integer boardId = new Integer(request.getParameter("boardId"));
        String primeFlag = request.getParameter("primeFlag");
        com.icss.oa.bbs.vo.BbsNoticeVO noticeVO = null;
        BbsBoardVO boardVO = null;
        String manageFlag = request.getParameter("manageFlag");
        BbsUserinfoVO userVO = new BbsUserinfoVO();
        List managerList = null;
        List rightList = null;
        List boardList = null;
        List subareaList = null;
        List wholerightList = null;
        List wholemanagerList = null;
        try
        {
            conn = DBConnectionLocator.getInstance().getConnection("java:comp/env/ResourceOne/DataSource");
            ConnLog.open("ShowTopicServlet");
            BBSHandler handler = new BBSHandler(conn);
            
            Context ctx = Context.getInstance();
			UserInfo user = ctx.getCurrentLoginInfo();
			String uuid = user.getPersonUuid();
			int day = handler.getOutDay(uuid);
			if(day > 0 ){
			request.setAttribute("day", day);
			this.forward(request, response, "/bbs/noLogin.jsp");
			}
			
			
            boardList = handler.getBoardList();
            request.setAttribute("boardList", boardList);
            subareaList = handler.getSubareaList();
            request.setAttribute("subareaList", subareaList);
            UserMsgHandler userMsghandler = new UserMsgHandler(conn);
            userVO = userMsghandler.getUserVO(userMsghandler.getUserId());
            request.setAttribute("userVO", userVO);
            wholerightList = handler.getRightList(userMsghandler.getUserId());
            request.setAttribute("wholerightList", wholerightList);
            wholemanagerList = handler.getManagerList();
            request.setAttribute("wholemanagerList", wholemanagerList);
            managerList = handler.getManagerList(boardId);
            request.setAttribute("managerList", managerList);
            list = handler.getTopicList(boardId, primeFlag);
            request.setAttribute("topicList", list);
            request.setAttribute("noticeList", noticeList);
            boardVO = handler.getBoardVO(boardId);
            request.setAttribute("boardVO", boardVO);
            String areaname = handler.getAreaNameById(boardVO.getAreaid());
            request.setAttribute("areaname", areaname);
            request.setAttribute("rightList", rightList);
            BBSAreaHandler ahandler = new BBSAreaHandler(conn);
            String userId = userMsghandler.getUserId();
            String managerIds = "";
            Map manageMap = new HashMap();
            for(int i = 0; i < managerList.size(); i++)
            {
                ManagerUserinfoVO vo1 = (ManagerUserinfoVO)managerList.get(i);
                managerIds = (String)manageMap.get(vo1.getBoardid());
                managerIds = managerIds + vo1.getUserid() + "|||";
                manageMap.put(vo1.getBoardid(), managerIds);
            }

            if(manageFlag == null)
            {
                forward(request, response, "/bbs/deptTopic.jsp");
            } else
            {
                String manageId = (String)manageMap.get(boardId);
                if(manageId.indexOf(userVO.getUserid()) >= 0)
                    forward(request, response, "/bbs/boardManage.jsp");
                else
                    forward(request, response, "/bbs/deptTopic.jsp");
            }
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
                    ConnLog.close("ShowTopicServlet");
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