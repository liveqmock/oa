package com.icss.regulation.admin;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.j2ee.dao.DAOFactory;
import com.icss.j2ee.servlet.ServletBase;
import com.icss.j2ee.util.Globals;
import com.icss.regulation.handler.RegulationHandler;
import com.icss.resourceone.common.logininfo.UserInfo;
import com.icss.resourceone.role.dao.RolePersonSearchDAO;
import com.icss.resourceone.role.model.RolePersonVO;
import com.icss.resourceone.sdk.framework.Context;

public class AllRegulationServlet extends ServletBase {

	protected void performTask(HttpServletRequest request,
			HttpServletResponse response) {
		Connection conn = null;
		Connection connection = null;
		List Rlist = new ArrayList();
		boolean isb = true;
		try {
			Context ctx = null;
			ctx = Context.getInstance();
			UserInfo user = ctx.getCurrentLoginInfo();
			String uuid = user.getPersonUuid();

			conn = getConnection(Globals.DATASOURCEJNDI);
			connection = getConnection("jdbc/ROEEE");

			RegulationHandler handler = new RegulationHandler(conn);

			DAOFactory daofactory = new DAOFactory(connection);
			RolePersonSearchDAO rolepersonsearchdao = new RolePersonSearchDAO();
			//管理员
			rolepersonsearchdao.setRoleid(779);
			daofactory.setDAO(rolepersonsearchdao);
			List list = daofactory.find(new RolePersonVO());
			if (list != null && !list.isEmpty()) {
				Iterator it = list.iterator();
				while (it.hasNext()) {
					RolePersonVO vo = (RolePersonVO) it.next();
					String a = vo.getPersonuuid();
					if (uuid.endsWith(a)) {
						isb = false;
						break;
					}
				}
			}

			
			//查询
			String SearchFlag = request.getParameter("SearchFlag");
			if("1".equals(SearchFlag)){
				StringBuilder sb = new StringBuilder();
				String key = request.getParameter("key")==null?"":(String)request.getParameter("key");
				String org = request.getParameter("org")==null?"allorg":(String) request.getParameter("org");
				
				String orguuid = request.getParameter("orguuid");
				
				String ftime = request.getParameter("ftime");
				String etime = request.getParameter("etime");
				String fanwei = request.getParameter("fanwei");
				String youxiao = request.getParameter("youxiao");
				sb.append(" 1=1 ");

				

				if (isb) {
					String ouid = handler.getOrgUUID(uuid);
					ouid = handler.getParentUUID(ouid);
					//非管理员
					sb.append(" AND ORG='").append(ouid).append("'");
					request.setAttribute("orguuid", ouid);
					
				}else{
					if(!"allorg".equals(org)){
						sb.append(" AND ORG='").append(org).append("'");
					}
				}
				
				if (!"".equals(key.trim())) {
					if ("title".equalsIgnoreCase(fanwei)) {
						sb.append(" AND TITLE like '%").append(key).append("%'");
					} else if ("content".equalsIgnoreCase(fanwei)) {
						sb.append(" AND CONTENT like '%").append(key).append("%'");
					}else if ("rcNO".equalsIgnoreCase(fanwei)) {
						sb.append(" AND RECORD_NO like '%").append(key).append("%'");
					}  
					else {
						sb.append(" AND (TITLE like '%").append(key).append(
								"%' OR CONTENT like '%").append(key).append("%' OR RECORD_NO like '%").append(key)
								.append("%' )");
					}
				}

				if ("yes".equalsIgnoreCase(youxiao)) {
					sb.append(" AND FLAG=0");
				} else if ("no".equalsIgnoreCase(youxiao)) {
					sb.append(" AND FLAG=1");
				}
				
				if(ftime!=null&&!"".equals(ftime)){
						sb.append(" AND CREAT_TIME >=to_date('")
						.append(ftime)
						.append("','yyyy-mm-dd')");
				}
				if(etime!=null&&!"".equals(etime)){
						sb.append(" AND to_char(creat_time,'yyyy-mm-dd')<='")
						.append(etime).append("'");
				}

				System.out.println("查询条件="+sb);
				
				Rlist =handler.getRListByClause(sb.toString());
			}else{
					// 只能看到本部门的
				if (isb) {
					String ouid = handler.getOrgUUID(uuid);
					ouid = handler.getParentUUID(ouid);
					//System.out.println("ouid="+ouid);
					//Rlist = handler.getRegulationByOrg(ouid);
					Rlist =handler.getRListByClause("  ORG='"+ouid+"'");
					request.setAttribute("orguuid", ouid);
				} else {
					//Rlist = handler.getAllRegulation();
					Rlist =handler.getRListByClause("  1=1 ");
				}
			}
			
			//部门列表
			List orglist = new ArrayList();
			orglist = handler.getROrg();

			request.setAttribute("orglist",orglist);
			request.setAttribute("noAdmin", isb);
			request.setAttribute("Rlist", Rlist);

			this.forward(request, response, "/regulation/allregulation.jsp");

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
			
			try {
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException sqle) {
				sqle.printStackTrace();
			}
		}

	}

}