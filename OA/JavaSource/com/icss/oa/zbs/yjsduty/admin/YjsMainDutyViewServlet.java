package com.icss.oa.zbs.yjsduty.admin;

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
import com.icss.j2ee.dao.DAOException;
import com.icss.j2ee.services.DBConnectionLocator;
import com.icss.j2ee.services.DBConnectionLocatorException;
import com.icss.j2ee.servlet.ServletBase;
import com.icss.j2ee.util.Globals;
import com.icss.oa.bbs.handler.UserMsgHandler;

import com.icss.oa.zbs.yjsduty.handler.YjsWorkInfoHandler;
import com.icss.oa.zbs.yjsduty.vo.TbYjsWorkinfoVO;
import com.icss.oa.zbs.yjsduty.vo.TbYjsWorkinfomainVO;

/**
 * @version 	1.0
 * @author		liupei
 */
public class YjsMainDutyViewServlet extends ServletBase {

	/**
	* @see com.icss.j2ee.servlet.ServletBase#void (javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	*/
	public void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection conn = null;
		List dutyInfoList = new ArrayList();
		System.err.println("�ɹ�1");
		try {
			
			//System.out.println("����YjsMainDutyViewServlet");
			
			ConnLog.open("YjsMainDutyViewServlet");
			conn = DBConnectionLocator.getInstance().getConnection(Globals.DATASOURCEJNDI);
			YjsWorkInfoHandler handler = new YjsWorkInfoHandler(conn);
			
			String wimid = request.getParameter("wimid");
			
			TbYjsWorkinfomainVO vo = new TbYjsWorkinfomainVO();
			vo = handler.getMainDutyById(wimid);
			dutyInfoList = handler.getDutyContentInfoByClause(" wim_id="+wimid);
			UserMsgHandler userMsghandler = new UserMsgHandler(conn); 
			String userId = userMsghandler.getUserId();		
			List typeList = handler.getMainDutyTypeListByClause(" 1=1");
			
			request.setAttribute("typeList",typeList);
			request.setAttribute("userId", userId);
			request.setAttribute("vo", vo);
			request.setAttribute("dutyInfoList", dutyInfoList);
			Map map = new HashMap();
			for(int i=0;i<dutyInfoList.size();i++){
				TbYjsWorkinfoVO infovo =(TbYjsWorkinfoVO)dutyInfoList.get(i);
				map.put(infovo.getWitId(),infovo);
			}
			request.setAttribute("map", map);
			String manager = request.getParameter("manager");
			if("true".equals(manager)){
				request.setAttribute("manager","true");
			}			
			System.err.println("�ɹ�2");
			
			/*if(vo.getWitCreater().equalsIgnoreCase(userId) ||"true".equals(manager) ){
			System.out.println("userId="+userId+"���û�����YjsMainDutyViewServlet���༭wimid="+wimid);
			}else{System.out.println("userId="+userId+"���û�����YjsMainDutyViewServlet���鿴wimid="+wimid);}*/
			
			if("1".equals(vo.getFlag())){
				//System.out.println("wimid="+wimid+"����־���ڱ�"+vo.getLastEditer()+vo.getLastIP()+"�༭");
				//����־���ڱ༭
				this.forward(request, response, "/zbs/yjs_duty/dutyView.jsp");
			}else{
				//����־û�б��༭		
					//System.out.println("wimid="+wimid+"����־û�б��༭");
					if(vo.getWitCreater().equalsIgnoreCase(userId) ||"true".equals(manager) ){
						//�������¸�����
						String username = userMsghandler.getUserName();
						String ip = request.getRemoteAddr();
						vo.setLastEditer(username);
						vo.setLastIP(ip);
						vo.setFlag("1");//������־�ı༭��ʶ��Ϊ1
						Integer id = handler.editDutyMainInfo(vo);
						
						//System.out.println("��YjsMainDutyViewServlet�У�����ת��dutyEditFCK.jsp");
						this.forward(request, response, "/zbs/yjs_duty/dutyEditFCK.jsp");
						
					}else{
						this.forward(request, response, "/zbs/yjs_duty/dutyView.jsp");
					}
					
			}

		} catch (DBConnectionLocatorException e) {
			// TODO �Զ����� catch ��
			e.printStackTrace();
		} catch (DAOException e) {
			// TODO �Զ����� catch ��
			e.printStackTrace();
		} finally {
			try {
				if (conn != null) {
					conn.close();
					ConnLog.close("YjsMainDutyViewServlet");
				}
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}

}
