// Decompiled Using: FrontEnd Plus v2.03 and the JAD Engine
// Available From: http://www.reflections.ath.cx
// Decompiler options: packimports(3) 
// Source File Name:   EditAreaServlet.java

package com.icss.oa.bbs.admin.control;

import com.icss.common.log.ConnLog;
import com.icss.j2ee.services.DBConnectionLocator;
import com.icss.j2ee.servlet.ServletBase;
import com.icss.oa.address.handler.AddressHelp;
import com.icss.oa.address.vo.SelectOrgpersonVO;
import com.icss.oa.bbs.handler.*;
import com.icss.oa.bbs.user.control.InitializeUser;
import com.icss.oa.bbs.vo.BbsAreaVO;
import com.icss.oa.bbs.vo.BbsAreaccVO;
import java.io.IOException;
import java.sql.Connection;
import java.util.Iterator;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.http.*;

public class EditAreaServlet extends ServletBase
{

    public EditAreaServlet()
    {
    }

    protected void performTask(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException
    {
        Connection conn = null;
        String areaName = request.getParameter("areaName");
        String areaDes = request.getParameter("areaDes");
        Integer areaId = new Integer(request.getParameter("areaId"));
        
        try
        {
            conn = DBConnectionLocator.getInstance().getConnection("java:comp/env/ResourceOne/DataSource");
            ConnLog.open("EditAreaServlet");
            BBSHandler handler = new BBSHandler(conn);
            BBSAreaHandler ahandler = new BBSAreaHandler(conn);
            UserMsgHandler userMsgHandler = new UserMsgHandler(conn);
            AddressHelp adressHelp = new AddressHelp(conn);
            String areaRight = request.getParameter("areaRight");
            
            handler.editArea(areaId, areaName, areaDes,areaRight);
            List arealist = handler.getAreaListById(areaId);
            if(arealist!=null){
            	for(int i=0;i<arealist.size();i++){
            		BbsAreaVO vo = (BbsAreaVO)arealist.get(i);
            		
            	}
            }
            
            //if("1".equals(areaRight))
            //{
                HttpSession session = request.getSession();
                List userList = (List)session.getAttribute("user");
                if(userList != null)
                    userList = adressHelp.getperson(userList, request, "user");
                session.removeAttribute("user");
                List managerList = (List)session.getAttribute("manager");
                if(managerList != null)
                    managerList = adressHelp.getperson(managerList, request, "manager");
                if(managerList != null)
                {
                    List mlist = ahandler.findManagerListByAreaId(areaId);
                    Iterator mit = mlist.iterator();
                    com.icss.oa.bbs.vo.BbsUserinfoVO userVO = null;
                    for(Iterator it = managerList.iterator(); it.hasNext();)
                    {
                        SelectOrgpersonVO selectUserVO = (SelectOrgpersonVO)it.next();
                        String userId = selectUserVO.getUseruuid();
                        boolean pd1 = false;
                        while(mit.hasNext()) 
                        {
                            BbsAreaccVO mVO = (BbsAreaccVO)mit.next();
                            if(userId.equals(mVO.getUserid()))
                                pd1 = true;
                        }
                        if(!pd1)
                        {
                            userVO = userMsgHandler.getUserVO(userId);
                            String lastip = request.getRemoteAddr();
                            if(userVO == null)
                            {
                                InitializeUser initializeUser = new InitializeUser(conn);
                                userVO = initializeUser.initialize(selectUserVO.getUserid(), selectUserVO.getName(), lastip);
                            }
                            ahandler.newAreaManager(areaId, userId);
                        }
                    }

                }
                if(userList != null)
                {
                    List ulist = ahandler.getAreAccList(areaId);
                    Iterator uit = ulist.iterator();
                    com.icss.oa.bbs.vo.BbsUserinfoVO userVO = null;
                    for(Iterator it = userList.iterator(); it.hasNext();)
                    {
                        boolean pd2 = false;
                        SelectOrgpersonVO selectUserVO = (SelectOrgpersonVO)it.next();
                        String userId = selectUserVO.getUseruuid();
                        while(uit.hasNext()) 
                        {
                            BbsAreaccVO uVO = (BbsAreaccVO)uit.next();
                            if(userId.equals(uVO.getUserid()))
                                pd2 = true;
                        }
                        if(!pd2)
                        {
                            userVO = userMsgHandler.getUserVO(userId);
                            String lastip = request.getRemoteAddr();
                            if(userVO == null)
                            {
                                InitializeUser initializeUser = new InitializeUser(conn);
                                userVO = initializeUser.initialize(selectUserVO.getUseruuid(), selectUserVO.getName(), lastip);
                            }
                            ahandler.newAreaRight(areaId, userId);
                        }
                    }

                    //ahandler.updateAreaLimited(areaId, "1");
                }
            
            forward(request, response, "/bbs/areaAddClose.jsp");
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
                    ConnLog.close("EditAreaServlet");
                }
            }
            catch(Exception e1)
            {
                e1.printStackTrace();
            }
        }
    }
}