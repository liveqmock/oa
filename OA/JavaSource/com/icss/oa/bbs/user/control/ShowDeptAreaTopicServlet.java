// Decompiled Using: FrontEnd Plus v2.03 and the JAD Engine
// Available From: http://www.reflections.ath.cx
// Decompiler options: packimports(3) 
// Source File Name:   ShowDeptAreaTopicServlet.java

package com.icss.oa.bbs.user.control;

import com.icss.common.log.ConnLog;
import com.icss.j2ee.services.DBConnectionLocator;
import com.icss.j2ee.servlet.ServletBase;
import com.icss.oa.bbs.handler.*;
import com.icss.oa.bbs.vo.*;
import com.icss.oa.filetransfer.handler.personInfoHandler;
import com.icss.oa.phonebook.handler.OrgHandler;
import com.icss.oa.phonebook.vo.SysOrgVO;
import com.icss.resourceone.common.logininfo.UserInfo;
import com.icss.resourceone.sdk.framework.Context;

import java.io.IOException;
import java.sql.Connection;
import java.util.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ShowDeptAreaTopicServlet extends ServletBase
{

    public ShowDeptAreaTopicServlet()
    {
    }

    protected void performTask(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException
    {
        Connection conn = null;
        List list = null;
        BbsBoardVO boardVO = null;
        BbsUserinfoVO userVO = new BbsUserinfoVO();
        List boardList = null;
        List subareaList = null;
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
            
            UserMsgHandler userMsghandler = new UserMsgHandler(conn);
            userVO = userMsghandler.getUserVO(userMsghandler.getUserId());
            request.setAttribute("userVO", userVO);
            Map boardmap = new HashMap();
            personInfoHandler _personInfoHandler = new personInfoHandler(conn);
            String deptuuid = _personInfoHandler.getOrgUUID(userVO.getUserid());
            String orguuid = deptuuid;
            OrgHandler ohandler = new OrgHandler(conn);
            SysOrgVO orgvo = ohandler.getOrg(deptuuid);
            if(orgvo != null)
            {
                for(; orgvo.getOrglevel().intValue() > 3; orgvo = ohandler.getOrg(orgvo.getParentorguuid()));
                orguuid = orgvo.getOrguuid();
            }
            subareaList = handler.getAreaByOrgList(orguuid);
            request.setAttribute("subareaList", subareaList);
            String areaids = "0";
            if(subareaList != null)
            {
                for(int i = 0; i < subareaList.size(); i++)
                {
                    BbsAreaVO vo = (BbsAreaVO)subareaList.get(i);
                    areaids = areaids + "," + vo.getAreaid().toString();
                    boardList = new ArrayList();
                    boardList = handler.getBoardByPersonList(userVO.getUserid(), vo.getAreaid().toString());
                    boardmap.put(vo.getAreaid().toString(), boardList);
                }

            }
            request.setAttribute("boardList", boardList);
            request.setAttribute("boardmap", boardmap);
            boardList = handler.getBoardByPersonList(userVO.getUserid(), areaids);
            request.setAttribute("boardList", boardList);
            String boardids = "0";
            if(boardList != null)
            {
                for(int i = 0; i < boardList.size(); i++)
                {
                    BbsBoardVO bvo = (BbsBoardVO)boardList.get(i);
                    boardids = boardids + "," + bvo.getBoardid().toString();
                }

            }
            list = handler.getTopicList(boardids);
            request.setAttribute("topicList", list);
            List rightList = handler.getRightList(userVO.getUserid());
            request.setAttribute("rightList", rightList);
            BBSAreaHandler ahandler = new BBSAreaHandler(conn);
            List areaRightList = ahandler.getRightList(userVO.getUserid());
            request.setAttribute("areaRightList", areaRightList);
            List managerList = handler.getManagerList();
            request.setAttribute("managerList", managerList);
            forward(request, response, "/bbs/deptBbs.jsp");
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