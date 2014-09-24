/*
 * Created on 2004-2-10
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.icss.oa.bbs.user.control;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.common.log.ConnLog;
import com.icss.j2ee.services.DBConnectionLocator;
import com.icss.j2ee.servlet.ServletBase;
import com.icss.j2ee.util.Globals;
import com.icss.oa.bbs.handler.BBSBoardHandler;
import com.icss.oa.bbs.handler.BBSHandler;
import com.icss.oa.bbs.handler.BoardArticleUpdate;
import com.icss.oa.bbs.handler.UserMsgHandler;
import com.icss.oa.bbs.vo.BbsArticleVO;
import com.icss.oa.bbs.vo.BbsUserinfoVO;
import com.icss.oa.util.*;
import com.icss.resourceone.common.logininfo.UserInfo;
import com.icss.resourceone.sdk.framework.Context;

/**
 * @author Administrator
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class NewArticleServlet extends ServletBase {

	protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection conn = null;
		String content = request.getParameter("content");
		if (content != null) {
			try {
				content = CommUtil.formathtm(content);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		Integer boardId = new Integer(request.getParameter("boardId"));
		String articleName = request.getParameter("articleName");
		//String auditFlag = request.getParameter("auditFlag");
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
		if ("checked".equals(showsign))
			vo.setShowsign("1");
		else
			vo.setShowsign("0");

		String tointend = request.getParameter("checkbox3");
		if ("checked".equals(tointend))
			vo.setTointend("1");
		else
			vo.setTointend("0");

		//取得上传文件名
		String oldFileName = getUploadOldFileName(request, "acc");
		String fileType = "";
		if (oldFileName != null) {
			int index = 0;
			index = oldFileName.lastIndexOf("\\");
			if (index != -1)
				oldFileName = oldFileName.substring(index + 1);
			//取得文件后缀名
			index = oldFileName.lastIndexOf(".");
			if (index != -1)
				fileType = oldFileName.substring(index + 1);
		}
		//设置文件类型		
		if (oldFileName == null)
			vo.setAcctype("0");
		else if (fileType.equals("jpg") || fileType.equals("JPG") || fileType.equals("bmp") || fileType.equals("BMP") || fileType.equals("gif") || fileType.equals("GIF"))
			vo.setAcctype("1");
		else
			vo.setAcctype("2");

		try {
			boolean isAudit = false;
			conn = DBConnectionLocator.getInstance().getConnection(Globals.DATASOURCEJNDI);
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
				//this.forward(request, response, "/bbs/noPermission.jsp");
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
			//add article
			Integer articleId = handler.newArticl(vo);
			//updat board(lastId,lastTime) for last article
			if (!isAudit) {
				String userId = userMsgHandler.getUserId();
				//更新板块信息
				update.updateBoardByArticle(boardId, userId, articleId, articleName, userMsgHandler.getUserName());
				//更新用户发贴
				BbsUserinfoVO userVO = userMsgHandler.getUserVO(userId);
				Integer exp = new Integer(userVO.getExp().intValue() + 10);
				Integer pubNum = new Integer(userVO.getPubnum().intValue() + 1);
				Integer accessNum = new Integer(userVO.getAccessnum().intValue() + 1);
				userVO.setExp(exp);
				userVO.setPubnum(pubNum);
				userVO.setAccessnum(accessNum);
				userMsgHandler.updateUserInfo(userVO);
				//更新用户等级
				if (userVO.getExp().intValue() == 100 || userVO.getExp().intValue() == 300)
					userMsgHandler.updateUserLevel(userVO.getUserid(), "1");

			}
			//上传附件
			if (oldFileName != null) {

				//获得文件路径名
				String fileFillName = getUploadFileFullName(request, "acc");
				InputStream inputStream = new FileInputStream(fileFillName);

				handler.addAccessory(articleId, oldFileName, inputStream);

				//关闭输入流
				inputStream.close();
			}
			
			if(isAudit){
				request.setAttribute("FowardUrl", "ShowTopicServlet?boardId=" + boardId + "&primeFlag=0");
				response.sendRedirect("/oabase/bbs/auditFoward.jsp");
			}else{
				response.sendRedirect("ShowTopicServlet?boardId=" + boardId + "&primeFlag=0");
			}
		} catch (Exception e) {
			handleError(e);
		} finally {
			try {
				if (conn != null){
					conn.close();
					ConnLog.close("NewArticleServlet");
				}
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}

	}
}
