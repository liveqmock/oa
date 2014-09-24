/*
* Created on 2003-12-26
*
* To change the template for this generated file go to
* Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
*/
package com.icss.oa.bbs.user.control;

import java.io.IOException;
import java.sql.Connection;
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
import com.icss.oa.bbs.vo.BbsBoardVO;
import com.icss.oa.bbs.vo.BbsNoticeVO;
import com.icss.oa.bbs.vo.BbsUserinfoVO;
import com.icss.oa.bbs.vo.ManagerUserinfoVO;

/**
 * @author Administrator
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class ShowTopicServlet1 extends ServletBase {

	protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection conn = null;
		List list = null;
		List noticeList = null;
		Integer boardId = new Integer(request.getParameter("boardId"));
		String primeFlag = request.getParameter("primeFlag");
		BbsNoticeVO noticeVO = null;
		BbsBoardVO boardVO = null;
		String manageFlag = request.getParameter("manageFlag");
		BbsUserinfoVO userVO = new BbsUserinfoVO();
		List managerList = null;
		List rightList = null;
		
		List boardList = null;
		List subareaList = null;
		List wholerightList = null;
		List wholemanagerList = null;
		
		try {
			conn = DBConnectionLocator.getInstance().getConnection(Globals.DATASOURCEJNDI);
			ConnLog.open("ShowTopicServlet");
			BBSHandler handler = new BBSHandler(conn);
			
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
			
			//			String userid = userMsghandler.getUser();
			//			boolean ispublic;
			//			ispublic = handler.isPublicUserid(userid);
			//			if(!ispublic){  //不是公用账号
			

			managerList = handler.getManagerList(boardId);
			request.setAttribute("managerList", managerList);
			
			list = handler.getTopicList(boardId, primeFlag);
			request.setAttribute("topicList", list);
			
			//noticeList = handler.getNotice(boardId);
			request.setAttribute("noticeList", noticeList);
			
			boardVO = handler.getBoardVO(boardId);
			request.setAttribute("boardVO", boardVO);
			
			String areaname = handler.getAreaNameById(boardVO.getAreaid());
			request.setAttribute("areaname", areaname);
			//rightList = handler.getRightList(userVO.getUserid());
			request.setAttribute("rightList", rightList);	
			
			BBSAreaHandler ahandler = new BBSAreaHandler(conn); 
			//List areaRightList = ahandler.getRightList(userVO.getUserid());
			//request.setAttribute("areaRightList", areaRightList);	

			String userId = userMsghandler.getUserId(); 		
			
			String managerIds = "";
			Map manageMap = new HashMap();
			for(int i=0;i<managerList.size();i++){
				ManagerUserinfoVO vo1= (ManagerUserinfoVO)managerList.get(i);
				managerIds = (String)manageMap.get(vo1.getBoardid());
				managerIds += vo1.getUserid()+"|||";
				manageMap.put(vo1.getBoardid(),managerIds);
			}
			
			if (manageFlag == null)
				this.forward(request, response, "/bbs/topic1.jsp");
			else{ 
				String manageId = (String)manageMap.get(boardId);
				if(manageId.indexOf(userVO.getUserid())>=0){
					this.forward(request, response, "/bbs/boardManage.jsp");
				}else{
					this.forward(request, response, "/bbs/topic.jsp");
				}			
			}
			//			}else{   //用公用账号登录
			//				this.forward(request,response,"/bbs/loginDeny.jsp");				
			//			}
		} catch (Exception e) {
			handleError(e);
		} finally {
			try {
				if (conn != null){
					conn.close();
					ConnLog.close("ShowTopicServlet1");
				}
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}

	}
}
