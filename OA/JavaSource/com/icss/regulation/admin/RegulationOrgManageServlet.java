package com.icss.regulation.admin;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.j2ee.servlet.ServletBase;
import com.icss.j2ee.util.Globals;
import com.icss.regulation.handler.RegulationHandler;
import com.icss.regulation.vo.RegulationOrgVO;
import com.icss.resourceone.common.logininfo.UserInfo;
import com.icss.resourceone.sdk.framework.Context;

public class RegulationOrgManageServlet extends ServletBase {

	private static final long serialVersionUID = -90113184585799351L;

	protected void performTask(HttpServletRequest request,
			HttpServletResponse response) {
		Connection conn = null;
		try {
			Context ctx = null;
			ctx = Context.getInstance();
			UserInfo user = ctx.getCurrentLoginInfo();
			String personuuid = user.getPersonUuid();
			conn = getConnection(Globals.DATASOURCEJNDI);
			RegulationHandler handler  = new RegulationHandler(conn);
			String type = request.getParameter("type");
			if("del".equals(type)){
				String did = request.getParameter("did");
				handler.delOrg(did);
				
			}else if("edit".equals(type)){
				String orguuid = request.getParameter("modifyid");
				String orgname = request.getParameter("o_name");
				Integer sequence =Integer.valueOf(request.getParameter("o_sequence"));
				RegulationOrgVO vo = new RegulationOrgVO();
				vo.setOrguuid(orguuid);
				vo.setOrgname(orgname);
				vo.setSequence(sequence);
				handler.editOrg(vo);
			}else if ("new".equals(type)){
				UUID uuid = UUID.randomUUID();
				RegulationOrgVO vo = new RegulationOrgVO();
				String orgname = request.getParameter("o_name");
				Integer sequence =Integer.valueOf(request.getParameter("o_sequence"));
  				vo.setOrguuid(uuid.toString());
				vo.setOrgname(orgname);
				vo.setSequence(sequence);
				handler.newOrg(vo);
			}else if("enew".equals(type)){
				String org = request.getParameter("e_org");
				String orgs[]  = org.split("@");
				String orguuid= orgs[0];
				String orgname = orgs[1];
				Integer sequence =Integer.valueOf(request.getParameter("e_sequence"));
				RegulationOrgVO vo = new RegulationOrgVO();
				vo.setOrguuid(orguuid);
				vo.setOrgname(orgname);
				vo.setSequence(sequence);
				handler.newOrg(vo);
			}
			
			response.sendRedirect(request.getContextPath() + "/servlet/RegulationOrgListServlet");
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