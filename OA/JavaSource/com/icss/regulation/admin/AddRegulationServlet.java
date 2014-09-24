package com.icss.regulation.admin;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.j2ee.servlet.ServletBase;
import com.icss.j2ee.util.Globals;
import com.icss.regulation.handler.RegulationHandler;
import com.icss.regulation.vo.RegulationVO;
import com.icss.resourceone.common.logininfo.UserInfo;
import com.icss.resourceone.sdk.framework.Context;

public class AddRegulationServlet extends ServletBase {

	protected void performTask(HttpServletRequest request,
			HttpServletResponse response) {
		Connection conn = null;

		RegulationVO vo = new RegulationVO();

		try {
			conn = getConnection(Globals.DATASOURCEJNDI);

			Context ctx = null;
			ctx = Context.getInstance();
			UserInfo user = ctx.getCurrentLoginInfo();
			String uuid = user.getPersonUuid();
			
			RegulationHandler handler = new RegulationHandler(conn);

			String title = request.getParameter("title");
			String org = request.getParameter("org");
			String ck = request.getParameter("ck");
			String memo = request.getParameter("memo");
			String content = request.getParameter("content");
			String ctime= request.getParameter("creattime");
			String etime = request.getParameter("edittime");
			String recordNo = request.getParameter("recordNo");
			
			if ("youxiao".equalsIgnoreCase(ck)) {
				vo.setFlag(0);
			} else {
				vo.setFlag(1);
			}
						
			if(ctime!=null && !"".equalsIgnoreCase(ctime.trim())){
				ctime = ctime +" 00:00:00.000000000";
				System.out.println(ctime);
				vo.setCreateTime( Timestamp.valueOf(ctime));
			}
			
			if(etime!=null && !"".equalsIgnoreCase(etime.trim())){
				etime = etime +" 00:00:00.000000000";
				vo.setEditTime( Timestamp.valueOf(etime));
			}
			
			Integer id = -1;
			String tid = request.getParameter("id");
			if(tid!=null&&!"".equals(tid)){
				id= new Integer(tid);
			}
			
			vo.setTitle(title);
			vo.setOrg(org);
			vo.setMemo(memo);
			vo.setContent(content);
			vo.setPersonuuid(uuid);
			vo.setRecordNo(recordNo);
			
			if (id == -1) {
				id = handler.saveRegulation(vo);
			} else {
				vo.setId(id);
				handler.updRegulation(vo);
			}

			this.forward(request, response, "/regulation/edit.jsp?id=" + id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException sqle) {
				sqle.printStackTrace();
			}
		}

	}

}