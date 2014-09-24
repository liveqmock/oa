package com.icss.oa.sendfile.admin;

import java.sql.Connection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.common.log.ConnLog;
import com.icss.j2ee.dao.DAOFactory;
import com.icss.j2ee.services.DBConnectionLocator;
import com.icss.j2ee.servlet.ServletBase;
import com.icss.oa.address.dao.SysOrgpersonSearchDAO;
import com.icss.oa.address.handler.HandlerException;
import com.icss.oa.address.handler.SysOrgpersonHandler;
import com.icss.oa.address.vo.SysOrgpersonVO;
import com.icss.oa.config.AddressConfig;

/**
 * 
 * To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Generation - Code and Comments
 */
public class SendFileFindPersonServlet extends ServletBase {
	protected void performTask(HttpServletRequest request,
			HttpServletResponse response)
			throws javax.servlet.ServletException, java.io.IOException {
		Connection conn = null;
		try {
			if ((this.getServletContext().getInitParameter(
					AddressConfig.ADDRESSJNDI) == null)
					|| (this.getServletContext().getInitParameter(
							AddressConfig.ADDRESSJNDI).equals(""))) {
				conn = getConnection(com.icss.j2ee.util.Globals.DATASOURCEJNDI);
				ConnLog.open("FindPersonServlet");
			} else {
				conn = DBConnectionLocator.getInstance().getConnection(
						this.getServletContext().getInitParameter(
								AddressConfig.ADDRESSJNDI));
				ConnLog.open("FindPersonServlet");
			}
			String cnname = request.getParameter("cnname");
			// SysOrgpersonHandler handler = new SysOrgpersonHandler(conn);
			// List list = handler.getbycnname(cnname);

			// SysOrgpersonHandler 的代码不知道是不是最新的，所以就在这里写查询。。。
			StringBuffer sql = new StringBuffer();
			sql.append("select distinct ");
			sql
					.append("SYS_ORGPERSON.PERSONUUID,SYS_PERSON.FLAG,SYS_PERSON.USERID,SYS_PERSON.ACCOUNTSTAT,SYS_PERSON.LOGINFAILNUM,SYS_PERSON.LASTLOGINIP,SYS_PERSON.PASSQUESTION,SYS_PERSON.PASSANSWER,SYS_PERSON.TTLFLAG,SYS_PERSON.ACCOUNTTTL,SYS_PERSON.DELTAG,SYS_PERSON.PERSONCODE,SYS_PERSON.CNNAME,SYS_PERSON.JOB,SYS_PERSON.ENNAME,SYS_PERSON.FIRSTNAME,SYS_PERSON.LASTNAME,SYS_PERSON.IDNUM,SYS_PERSON.CARDCODE,SYS_PERSON.SEX,SYS_PERSON.MARRYCODE,SYS_PERSON.PCODE,SYS_PERSON.HOMETEL,SYS_PERSON.OFFICETEL,SYS_PERSON.HOMEFAX,SYS_PERSON.OFFICEFAX,SYS_PERSON.MOBILE,SYS_PERSON.PAGER,SYS_PERSON.EMAIL1,SYS_PERSON.EMAIL2,SYS_PERSON.COUNTRY,SYS_PERSON.PROVINCEID,SYS_PERSON.CITYID,SYS_PERSON.CONNECTADDR,SYS_PERSON.ZIP,SYS_PERSON.EDUCODE,SYS_PERSON.DEGREECODE,SYS_PERSON.OTHERINFO ");
			sql.append("from ");
			sql.append("SYS_ORGPERSON,SYS_PERSON ");
			sql.append("where ");
			sql.append("SYS_ORGPERSON.PERSONUUID= SYS_PERSON.PERSONUUID and ");
			sql.append("SYS_PERSON.DELTAG='0' and SYS_PERSON.TTLFLAG='0' and (");
			sql.append("SYS_PERSON.CNNAME like '%" + cnname
					+ "%' or SYS_PERSON.USERID like'%" + cnname + "%') order by SYS_PERSON.USERID");
			DAOFactory factory = new DAOFactory(conn);
			SysOrgpersonSearchDAO dao = new SysOrgpersonSearchDAO();
			dao.setSearchSQL(sql.toString());
			//System.out.println("查询sql="+sql.toString());
			factory.setDAO(dao);
			List list = factory.find(new SysOrgpersonVO());

			if (!list.isEmpty())
				request.setAttribute("personlist", list);
			this.forward(request, response, "/address/sendfile/findperson.jsp");
		} catch (Exception ex) {
			handleError(ex);
		} finally {
			try {
				if (conn != null) {
					conn.close();
					ConnLog.close("FindPersonServlet");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
