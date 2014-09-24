// Decompiled Using: FrontEnd Plus v2.03 and the JAD Engine
// Available From: http://www.reflections.ath.cx
// Decompiler options: packimports(3) 
// Source File Name:   ShowIndexServlet.java

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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// Referenced classes of package com.icss.oa.bbs.user.control:
//            InitializeUser

public class ShowIndexServlet extends ServletBase {

	public ShowIndexServlet() {
	}

	protected void performTask(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Connection conn = null;
		List topicList = null;
		String lastip = "";
		BbsUserinfoVO userVO = null;
		String firstLogin = "0";
		List boardList = null;
		List subareaList = null;
		List rightList = null;
		List managerList = null;
		try {
			conn = DBConnectionLocator.getInstance().getConnection(
					"java:comp/env/ResourceOne/DataSource");
			ConnLog.open("ShowIndexServlet");
			Context ctx = Context.getInstance();
			UserInfo user = ctx.getCurrentLoginInfo();
			String uuid = user.getPersonUuid();
			BBSHandler handler = new BBSHandler(conn);
			String name= request.getParameter("name");
			System.out.println("name:"+name);
			
			//判断是否被禁用
			int day = handler.getOutDay(uuid);
			if(day > 0 ){
			request.setAttribute("day", day);
			this.forward(request, response, "/bbs/noLogin.jsp");
			}
			BBSAreaHandler ahandler = new BBSAreaHandler(conn);
			UserMsgHandler userMsghandler = new UserMsgHandler(conn);
			
			personInfoHandler _personInfoHandler = new personInfoHandler(conn);
			String deptuuid = _personInfoHandler.getOrgUUID(uuid);
			String orguuid = deptuuid;
			String orgname="本部门";
			OrgHandler ohandler = new OrgHandler(conn);
			SysOrgVO orgvo = ohandler.getOrg(deptuuid);
			if (orgvo != null) {
				for (; orgvo.getOrglevel().intValue() > 3; orgvo = ohandler
						.getOrg(orgvo.getParentorguuid()))
					;
				orguuid = orgvo.getOrguuid();
				orgname = orgvo.getCnname();
			}
			String userId = userMsghandler.getUserId();
			userVO = userMsghandler.getUserVO(userId);
			lastip = request.getRemoteAddr();
			if (userVO == null) {
				long lastLogintime = 0L;
				InitializeUser initializeUser = new InitializeUser(conn);
				userVO = initializeUser.initialize(userId, userMsghandler
						.getUserName(), lastip);
				firstLogin = "1";
				request.setAttribute("userVO", userVO);
			} else {
				long lastLogintime = userVO.getLasttime().longValue();
				userVO.setLastip(lastip);
				userVO.setLasttime(new Long(System.currentTimeMillis()));
				userMsghandler.updateUser(userVO);
				request.setAttribute("userVO", userVO);
			}
			subareaList = handler.getPublicSubareaList();
			String areaids = "0";
			if (subareaList != null) {
				for (int i = 0; i < subareaList.size(); i++) {
					BbsAreaVO avo = (BbsAreaVO) subareaList.get(i);
					areaids = areaids + "," + avo.getAreaid().toString();
				}

			}
			boardList = handler.getBoardByPersonList(userVO.getUserid(),
					areaids);
			String boardids = "0";
			if (boardList != null) {
				for (int j = 0; j < boardList.size(); j++) {
					BbsBoardVO bvo = (BbsBoardVO) boardList.get(j);
					boardids = boardids + "," + bvo.getBoardid().toString();
				}

			}

			//存储板块的map
			//Map boardmap = new HashMap();
			//取该人员有权限的分区列表,只取有权限的论坛分区
			List dptsubareaList = null;
			List dptboardList = null;
			dptsubareaList = handler.getAreaByOrgList(orguuid);
			String dpt = "no";

			String areaid = "0";
			if (dptsubareaList != null) {
				for (int i = 0; i < dptsubareaList.size(); i++) {
					BbsAreaVO vo = (BbsAreaVO) dptsubareaList.get(i);
					areaid += "," + vo.getAreaid().toString();

					//dptboardList = new ArrayList();
					//dptboardList = handler.getBoardByPersonList(userVO.getUserid(), vo.getAreaid().toString());
					//boardmap.put(vo.getAreaid().toString(), dptboardList);
				}
			}
			//request.setAttribute("dptsubareaList", dptsubareaList);
			//request.setAttribute("dptboardList", dptboardList);
			//request.setAttribute("boardmap", boardmap);

			//取该人员有权限的版块列表,在有权限的论坛分区下取
			dptboardList = handler.getBoardByPersonList(userVO.getUserid(),
					areaid);
			if (dptboardList != null && dptboardList.size()>0) {
				dpt = "yes";
			}
			request.setAttribute("dpt", dpt);
			request.setAttribute("orgname", orgname);
			//request.setAttribute("dptboardList", dptboardList);
			//String boardid = "0";
			//if(dptboardList!=null){
			//	for(int i=0;i<dptboardList.size();i++){
			//		BbsBoardVO bvo = (BbsBoardVO)dptboardList.get(i);
			//		boardid += ","+bvo.getBoardid().toString();
			//	}
			//}
			//			
			//			//取主题列表
			//			List dpttopiclist = handler.getTopicList(boardid);
			//			request.setAttribute("dpttopiclist", dpttopiclist);
			//			
			//            
			//			
			if(name==null || "".equals(name.trim())){
				topicList = handler.getNewTopicList(userVO.getUserid(), orguuid,
						boardids);
			}else{
				topicList = handler.getNewTopicListName(userVO.getUserid(), orguuid,
						boardids,name);
			}
			
			rightList = handler.getRightList(userId);
			managerList = handler.getManagerList();
			List areaRightList = ahandler.getRightList(userId);
			request.setAttribute("boardList", boardList);
			request.setAttribute("subareaList", subareaList);
			request.setAttribute("rightList", rightList);
			request.setAttribute("managerList", managerList);
			request.setAttribute("serverTime", new Long(System
					.currentTimeMillis()));
			request.setAttribute("areaRightList", areaRightList);
			request.setAttribute("topicList", topicList);
			request.setAttribute("firstLogin", firstLogin);
			forward(request, response, "/bbs/privalIndex.jsp");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (conn != null) {
					conn.close();
					ConnLog.close("ShowIndexServlet");
				}
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		return;
	}
}