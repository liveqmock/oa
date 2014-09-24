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
import com.icss.oa.bbs.handler.BBSSearchHandler;
import com.icss.oa.bbs.handler.BoardArticleUpdate;
import com.icss.oa.bbs.handler.UserMsgHandler;
import com.icss.oa.bbs.vo.BbsArticleVO;
import com.icss.oa.bbs.vo.BbsUserinfoVO;
import com.icss.oa.intendwork.handler.IntendWork;
import com.icss.oa.util.CommUtil;
import com.icss.resourceone.common.logininfo.UserInfo;
import com.icss.resourceone.sdk.framework.Context;

/**
 * @author Administrator
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class ReArticleServlet extends ServletBase {

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
		Integer topicId = new Integer(request.getParameter("topicId"));
		String articleName = request.getParameter("articleName");
		Integer boardId = new Integer(request.getParameter("boardId"));
//		String auditFlag = request.getParameter("auditFlag");
		String face = request.getParameter("face");
		String quto = request.getParameter("quto");
		String rearticleId = request.getParameter("rearticleId");
		
		BbsArticleVO vo = new BbsArticleVO();
		vo.setArticlename(articleName);
		vo.setBoardid(boardId);
		vo.setReid(topicId);
		vo.setEmittime(new Long(System.currentTimeMillis()));
		vo.setTopic("0");
		vo.setTop("0");
		vo.setPrime("0");
		vo.setArticlelock("0");
		vo.setRenum(new Integer(0));
		vo.setHitnum(new Integer(0));
		vo.setFace(face);
//		if (auditFlag.equals("1"))
//			vo.setIsview("0");
//		else if (auditFlag.equals("0"))
//			vo.setIsview("1");
		//		ȡ���ϴ��ļ���
		String oldFileName = getUploadOldFileName(request, "acc");
		String fileType = "";
		if (oldFileName != null) {
			int index = 0;
			index = oldFileName.lastIndexOf("\\");
			if (index != -1)
				oldFileName = oldFileName.substring(index + 1);
			//ȡ���ļ���׺��
			index = oldFileName.lastIndexOf(".");
			if (index != -1)
				fileType = oldFileName.substring(index + 1);
		}
		//�����ļ�����		
		if (oldFileName == null)
			vo.setAcctype("0");
		else if (fileType.equals("jpg") || fileType.equals("bmp") || fileType.equals("gif"))
			vo.setAcctype("1");
		else
			vo.setAcctype("2");

		String showsign = request.getParameter("checkbox4");
		if ("checked".equals(showsign))
			vo.setShowsign("1");
		else
			vo.setShowsign("0");

		//�Ƿ�����������
		vo.setTointend("0");

		try {
			boolean isAudit = false;

			conn = DBConnectionLocator.getInstance().getConnection(Globals.DATASOURCEJNDI);
			ConnLog.open("ReArticleServlet");
			BBSHandler handler = new BBSHandler(conn);
			//�ж��Ƿ���Ȩ�޻ظ�
			Context ctx = Context.getInstance();
			UserInfo user = ctx.getCurrentLoginInfo();
			String uuid = user.getPersonUuid();
			boolean isUser = handler.isCommonUser(uuid);
			if(!"dev".equals(user.getUserID())&&!isUser){
				response.sendRedirect("/oabase/bbs/noPermission.jsp");
				return ;
				//return ;
			}
			
			//�ж��Ƿ��ǻ�������
			if("1".equals(quto)&& rearticleId!=null){
				BbsArticleVO  baVo= handler.getArticleVO(Integer.valueOf(rearticleId));
				String reContent = baVo.getArticlecontent();
				String reuser = handler.getUserName(baVo.getUserid());
				StringBuilder sb = new StringBuilder();
				sb.append("<div class='reAc'>");
				sb.append("<span class='reSp'>&nbsp;&nbsp;");
				sb.append(reuser);
				sb.append(" &nbsp;&nbsp;��ԭ����</span><br/>");
				sb.append("<p class='reP'>");
				sb.append(reContent);
				sb.append("</p></div>");
				sb.append(content);
				vo.setArticlecontent(sb.toString());
			}else{
				vo.setArticlecontent(content);
			}

			
			UserMsgHandler userMsgHandler = new UserMsgHandler(conn);
			BoardArticleUpdate update = new BoardArticleUpdate(conn);
			vo.setUserid(userMsgHandler.getUserId());

			//�ж��Ƿ�Ҫ���
			BBSBoardHandler  bhandler = new BBSBoardHandler(conn);
			isAudit = bhandler.getIsAudit(boardId);
			if (isAudit)
				vo.setIsview("0");
			else
				vo.setIsview("1");
			
			Integer articleId = handler.newArticl(vo);
			
			if (!isAudit) {
				String userId = userMsgHandler.getUserId();
				//���°����Ϣ
				update.updateBoard(boardId, 0, 1);
//                update.adjustBoard(boardId);
				//����������Ϣ
				update.updateArticleByArticle(userId, topicId, userMsgHandler.getUserName());
				//�����û�����
				BbsUserinfoVO userVO = userMsgHandler.getUserVO(userId);
				Integer exp = new Integer(userVO.getExp().intValue() + 10);
				userVO.setExp(exp);
				userMsgHandler.updateUserInfo(userVO);
				//�����û��ȼ�
				if (userVO.getExp().intValue() == 100 || userVO.getExp().intValue() == 300)
					userMsgHandler.updateUserLevel(userId, "1");

			}
			if (oldFileName != null) {

				//����ļ�·����
				String fileFillName = getUploadFileFullName(request, "acc");
				InputStream inputStream = new FileInputStream(fileFillName);

				handler.addAccessory(articleId, oldFileName, inputStream);

				//�ر�������
				inputStream.close();
			}

			//�ж��Ƿ�Ҫ�����������
			BBSSearchHandler shandler = new BBSSearchHandler(conn);
			String[] tointend = shandler.getTointend(topicId);
			if ("1".equals(tointend[0])) {
				IntendWork addIntendHandler = new IntendWork(conn);
				String topic = new String();
				String source = new String();
				String url = new String();
				String navigate = new String();
				String personid = new String();
				topic = articleName;
				source = "����԰��";
				url = "/oabase/servlet/ShowArticleServlet?topicId=" + topicId + "&boardId=" + boardId + "&hitFlag=1";
				navigate = "";
				personid = tointend[1];
				addIntendHandler.addWork(topic, source, url, navigate, personid, IntendWork.getCodeValue("oa_bbs"), articleId.toString());
			}

			if(isAudit){
				request.setAttribute("FowardUrl", "/servlet/ShowArticleServlet?topicId=" + topicId + "&boardId=" + boardId + "&hitFlag=0");
				response.sendRedirect("/oabase/bbs/auditFoward.jsp");
			}else{
				response.sendRedirect("ShowArticleServlet?topicId=" + topicId + "&boardId=" + boardId + "&hitFlag=0");
			}
		} catch (Exception e) {
			handleError(e);
		} finally {
			try {
				if (conn != null){
					conn.close();
					ConnLog.close("ReArticleServlet");
				}
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}

	}
}
