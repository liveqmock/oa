/*
* Created on 2003-12-26
*
* To change the template for this generated file go to
* Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
*/
package com.icss.oa.bbs.user.control;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.common.log.ConnLog;
import com.icss.j2ee.services.DBConnectionLocator;
import com.icss.j2ee.servlet.ServletBase;
import com.icss.j2ee.util.Globals;
import com.icss.oa.bbs.handler.BBSAreaHandler;
import com.icss.oa.bbs.handler.BBSHandler;
import com.icss.oa.bbs.handler.UserMsgHandler;
import com.icss.oa.bbs.vo.BbsAreaVO;
import com.icss.oa.bbs.vo.BbsBoardVO;
import com.icss.oa.bbs.vo.BbsNoticeVO;
import com.icss.oa.bbs.vo.BbsUserinfoVO;
import com.icss.oa.bbs.vo.ManagerUserinfoVO;
import com.icss.oa.filetransfer.handler.personInfoHandler;
import com.icss.oa.phonebook.handler.OrgHandler;
import com.icss.oa.phonebook.vo.SysOrgVO;
import com.icss.resourceone.common.logininfo.UserInfo;
import com.icss.resourceone.sdk.framework.Context;
import com.icss.resourceone.sdk.framework.EntityException;

/**
 * @author Administrator
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class ShowTopicByDeptServlet extends ServletBase {

	protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection conn = null;
		List list = null;
		//BbsBoardVO boardVO = null;
		BbsUserinfoVO userVO = new BbsUserinfoVO();
		
		List boardList = null;
		List subareaList = null;
		
		try {
			conn = DBConnectionLocator.getInstance().getConnection(Globals.DATASOURCEJNDI);
			ConnLog.open("ShowTopicServlet");
			BBSHandler handler = new BBSHandler(conn);
			
			UserInfo user = null;
			String userId=null;
			//String firstLogin = "0";

			Context ctx = Context.getInstance();
			user = ctx.getCurrentLoginInfo();
			if (user != null)
				userId= user.getPersonUuid();
			

			//判断是否能用论坛
			int day = handler.getOutDay(userId);
			if(day > 0 ){
			request.setAttribute("day", day);
			this.forward(request, response, "/bbs/noLogin.jsp");
			}
			
			
			String lastip = request.getRemoteAddr();

			UserMsgHandler userMsghandler = new UserMsgHandler(conn);
			userVO = userMsghandler.getUserVO(userMsghandler.getUserId());
			
			if (userVO == null) {
				//long lastLogintime = 0L;
				InitializeUser initializeUser = new InitializeUser(conn);
				userVO = initializeUser.initialize(userId, userMsghandler
						.getUserName(), lastip);
				//firstLogin = "1";
				request.setAttribute("userVO", userVO);
			} else {
				//long lastLogintime = userVO.getLasttime().longValue();
				userVO.setLastip(lastip);
				userVO.setLasttime(new Long(System.currentTimeMillis()));
				userMsghandler.updateUser(userVO);
				request.setAttribute("userVO", userVO);
			}
			
			request.setAttribute("userVO", userVO);
			
			//存储板块的map
			Map boardmap = new HashMap();
			
			//获取登陆人员所在局级部门uuid
            personInfoHandler _personInfoHandler = new personInfoHandler(conn);
            //System.out.println("------------user id is "+userVO.getUserid());
			String deptuuid = _personInfoHandler.getOrgUUID(userVO.getUserid());
			//System.out.println("------------dept id is "+deptuuid);
			//String orguuid = _personInfoHandler.getParentUUID(deptuuid);
			String orguuid = deptuuid;
			OrgHandler ohandler = new OrgHandler(conn);
			SysOrgVO orgvo = ohandler.getOrg(deptuuid);
			if(orgvo !=null){
				while(orgvo.getOrglevel().intValue()>3){
					//如果组织级别在3级以上，即局级以下,循环直到是局级单位
					orgvo=ohandler.getOrg(orgvo.getParentorguuid());
				}
				orguuid = orgvo.getOrguuid();
			}
			
			
			//取该人员有权限的分区列表,只取有权限的论坛分区
			subareaList = handler.getAreaByOrgList(orguuid);
			request.setAttribute("subareaList", subareaList);
			String areaids = "0";
			if(subareaList!=null){
				for(int i=0;i<subareaList.size();i++){
					BbsAreaVO vo = (BbsAreaVO)subareaList.get(i);
					areaids += ","+vo.getAreaid().toString();
					
					boardList = new ArrayList();
					boardList = handler.getBoardByPersonList(userVO.getUserid(), vo.getAreaid().toString());
					boardmap.put(vo.getAreaid().toString(), boardList);
				}
			}
			request.setAttribute("boardmap", boardmap);
			
			//取该人员有权限的版块列表,在有权限的论坛分区下取
			boardList = handler.getBoardByPersonList(userVO.getUserid(), areaids);
			request.setAttribute("boardList", boardList);
			String boardids = "0";
			if(boardList!=null){
				for(int i=0;i<boardList.size();i++){
					BbsBoardVO bvo = (BbsBoardVO)boardList.get(i);
					boardids += ","+bvo.getBoardid().toString();
				}
			}
			
			//取主题列表
			list = handler.getTopicList(boardids);
			request.setAttribute("topicList", list);
			
			//noticeList = handler.getNotice(boardId);
			//request.setAttribute("noticeList", noticeList);
			
			//boardVO = handler.getBoardVO(boardId);
			//request.setAttribute("boardVO", boardVO);
			
			//String areaname = handler.getAreaNameById(boardVO.getAreaid());
			//request.setAttribute("areaname", areaname);
			//rightList = handler.getRightList(userVO.getUserid());
			//request.setAttribute("rightList", rightList);	
			
			//BBSAreaHandler ahandler = new BBSAreaHandler(conn); 
			//List areaRightList = ahandler.getRightList(userVO.getUserid());
			//request.setAttribute("areaRightList", areaRightList);	

			//String userId = userMsghandler.getUserId(); 		
			
			
			this.forward(request, response, "/bbs/boardTopicByDept.jsp");
			
		} catch (Exception e) {
			handleError(e);
		} finally {
			try {
				if (conn != null){
					conn.close();
					ConnLog.close("ShowTopicServlet");
				}
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}

	}
}
