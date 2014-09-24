// Decompiled Using: FrontEnd Plus v2.03 and the JAD Engine
// Available From: http://www.reflections.ath.cx
// Decompiler options: packimports(3) 
// Source File Name:   NewDeptArticleServlet.java

package com.icss.oa.bbs.user.control;

import com.icss.common.log.ConnLog;
import com.icss.j2ee.services.DBConnectionLocator;
import com.icss.j2ee.servlet.ServletBase;
import com.icss.oa.bbs.handler.*;
import com.icss.oa.bbs.vo.BbsArticleVO;
import com.icss.oa.bbs.vo.BbsUserinfoVO;
import com.icss.oa.util.CommUtil;
import com.icss.resourceone.common.logininfo.UserInfo;
import com.icss.resourceone.sdk.framework.Context;

import java.io.*;
import java.sql.Connection;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class NewDeptArticleServlet extends ServletBase
{

    public NewDeptArticleServlet()
    {
    }

    protected void performTask(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException
    {
        Connection conn = null;
        String content = request.getParameter("content");
        if(content != null)
            try
            {
                content = CommUtil.formathtm(content);
            }
            catch(Exception e1)
            {
                e1.printStackTrace();
            }
        Integer boardId = new Integer(request.getParameter("boardId"));
        String articleName = request.getParameter("articleName");
//      String auditFlag = request.getParameter("auditFlag");
        String face = request.getParameter("face");
        BbsArticleVO vo = new BbsArticleVO();
        vo.setArticlecontent(content);
        vo.setTopic("1");
        vo.setBoardid(boardId);
        vo.setArticlename(articleName);
        vo.setEmittime(new Long(System.currentTimeMillis()));
        vo.setHitnum(new Integer(0));
        vo.setTopic("1");
        vo.setTop("0");
        vo.setPrime("0");
        vo.setRenum(new Integer(0));
        vo.setArticlelock("0");
        vo.setFace(face);
        
        
        String showsign = request.getParameter("checkbox4");
        if("checked".equals(showsign))
            vo.setShowsign("1");
        else
            vo.setShowsign("0");
        String tointend = request.getParameter("checkbox3");
        if("checked".equals(tointend))
            vo.setTointend("1");
        else
            vo.setTointend("0");
        String oldFileName = getUploadOldFileName(request, "acc");
        String fileType = "";
        if(oldFileName != null)
        {
            int index = 0;
            index = oldFileName.lastIndexOf("\\");
            if(index != -1)
                oldFileName = oldFileName.substring(index + 1);
            index = oldFileName.lastIndexOf(".");
            if(index != -1)
                fileType = oldFileName.substring(index + 1);
        }
        fileType = fileType.toLowerCase();
        if(oldFileName == null)
            vo.setAcctype("0");
        else
        if(fileType.equals("jpg") || fileType.equals("JPG") || fileType.equals("bmp") || fileType.equals("BMP") || fileType.equals("gif") || fileType.equals("GIF") || fileType.equals("png"))
            vo.setAcctype("1");
        else
            vo.setAcctype("2");
        try
        {
			boolean isAudit = false;

            conn = DBConnectionLocator.getInstance().getConnection("java:comp/env/ResourceOne/DataSource");
            ConnLog.open("NewArticleServlet");
            
            //判断是否能用论坛
			BBSHandler handler = new BBSHandler(conn);
			Context ctx = Context.getInstance();
			UserInfo user = ctx.getCurrentLoginInfo();
			String uuid = user.getPersonUuid();
			int day = handler.getOutDay(uuid);
			if(day > 0 ){
			request.setAttribute("day", day);
			this.forward(request, response, "/bbs/noLogin.jsp");
			}
			//判断是否有权限回复
			boolean isUser = handler.isCommonUser(uuid);
			if(!"dev".equals(user.getUserID())&&!isUser){
				response.sendRedirect("/oabase/bbs/noPermission.jsp");
				return ;
			}
			
            UserMsgHandler userMsgHandler = new UserMsgHandler(conn);
            BoardArticleUpdate update = new BoardArticleUpdate(conn);
            
            BBSBoardHandler  bhandler = new BBSBoardHandler(conn);
			isAudit = bhandler.getIsAudit(boardId);
			if (isAudit)
				vo.setIsview("0");
			else
				vo.setIsview("1");
			
            vo.setUserid(userMsgHandler.getUserId());
            vo.setReuserid(userMsgHandler.getUserId());
            vo.setReusername(userMsgHandler.getUserName());
            vo.setRetime(new Long(System.currentTimeMillis()));

            Integer articleId = handler.newArticl(vo);
            
            if(!isAudit)
            {
                String userId = userMsgHandler.getUserId();
                update.updateBoardByArticle(boardId, userId, articleId, articleName, userMsgHandler.getUserName());
                BbsUserinfoVO userVO = userMsgHandler.getUserVO(userId);
                Integer exp = new Integer(userVO.getExp().intValue() + 10);
                Integer pubNum = new Integer(userVO.getPubnum().intValue() + 1);
                Integer accessNum = new Integer(userVO.getAccessnum().intValue() + 1);
                userVO.setExp(exp);
                userVO.setPubnum(pubNum);
                userVO.setAccessnum(accessNum);
                userMsgHandler.updateUserInfo(userVO);
                if(userVO.getExp().intValue() == 100 || userVO.getExp().intValue() == 300)
                    userMsgHandler.updateUserLevel(userVO.getUserid(), "1");
            }
            if(oldFileName != null)
            {
                String fileFillName = getUploadFileFullName(request, "acc");
                InputStream inputStream = new FileInputStream(fileFillName);
                handler.addAccessory(articleId, oldFileName, inputStream);
                inputStream.close();
            }

            if(isAudit){
				request.setAttribute("FowardUrl", "ShowDeptTopicServlet?boardId=" + boardId + "&primeFlag=0");
				response.sendRedirect("/oabase/bbs/auditFoward.jsp");
			}else{
				response.sendRedirect("ShowDeptTopicServlet?boardId=" + boardId + "&primeFlag=0");
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
                    ConnLog.close("NewArticleServlet");
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