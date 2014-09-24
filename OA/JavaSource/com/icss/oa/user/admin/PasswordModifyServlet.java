package com.icss.oa.user.admin;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.common.log.ConnLog;
import com.icss.j2ee.audit.Audit;
import com.icss.j2ee.audit.AuditFactory;
import com.icss.j2ee.audit.core.Parameters;
import com.icss.j2ee.dao.DAOFactory;
import com.icss.j2ee.servlet.ServletBase;
import com.icss.j2ee.util.StringUtil;
import com.icss.j2ee.util.UnixPwdCrypt;
import com.icss.oa.sync.handler.UserSyncHandler;
import com.icss.oa.sync.vo.PersonVO;
import com.icss.oa.tq.Webservice.TQUser;
import com.icss.resourceone.common.login.LoginConstants;
import com.icss.resourceone.sdk.framework.Context;
import com.icss.resourceone.sdk.framework.Person;
import com.icss.resourceone.user.dao.PersonAccountDAO;

public class PasswordModifyServlet extends ServletBase {

	protected void performTask(HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws ServletException,
			IOException {
		Connection conn = null;
		String JNDI = "jdbc/ROEEE";
		PrintWriter out = null;
		try {
			conn = this.getConnection(JNDI);
			ConnLog.open("PasswordModifyServlet");

			String url = "";
			Context ctx = Context.getInstance();
			boolean resetPassword = false;
			Person per = ctx.getCurrentPerson();
				String userType = per.getAccountType();
				
				String resetPwUserId = httpservletrequest.getParameter("resetPwUserId");
				if(resetPwUserId==null)
			{
				resetPwUserId = "";
			}
			else
			{
				resetPwUserId =resetPwUserId.trim();
			}
			String uuid = ctx.getCurrentPersonUuid();
			String opass = null;
			String npass = null;
			
			if(LoginConstants.ACCOUNT_TYPE_WEBMASTER==Integer.valueOf(userType).intValue()&&!"".equalsIgnoreCase(resetPwUserId))
			{ 
				uuid = resetPwUserId;
				resetPassword = true;
				npass = "1";
				opass = "";
			}
			else
			{
				opass = httpservletrequest.getParameter("Password").trim();
				npass = StringUtil.convertSingleQuot(httpservletrequest
						.getParameter("newPassword").trim());
			}
			UserSyncHandler handler = new UserSyncHandler(conn);
			PersonVO vo = handler.getPersonByuuid(uuid);
			String userid =ctx.getCurrentUserid();
			String cnname= vo.getCnname();


			PersonAccountDAO personaccountdao = new PersonAccountDAO();
			personaccountdao.setPersonuuid(uuid);
			DAOFactory daofactory = new DAOFactory(conn);
			daofactory.setDAO(personaccountdao);
			PersonAccountDAO personaccountdao1 = (PersonAccountDAO) daofactory
					.findByPrimaryKey();

			if (UnixPwdCrypt.validate(opass, personaccountdao1.getPassword())||resetPassword) {
				//修改密码
				personaccountdao1.setConnection(conn);
				String pass =UnixPwdCrypt.crypt("RO", npass);
				personaccountdao1.setPassword(pass);
				personaccountdao1.update();

				//审核日志
				Audit audit1 = AuditFactory.getFactory().getInstance("system");
				Parameters parameters1 = new Parameters();
				parameters1.setParameter("ACTOR", userid);
				parameters1.setParameter("OBJECT",
						"\u7EC4\u7EC7\u4EBA\u5458\u4EBA\u5458\u7EF4\u62A4");
				parameters1.setParameter("ACTION", "\u5BC6\u7801\u4FEE\u6539");
				parameters1.setParameter("MESSAGE",
						"\u7EC4\u7EC7\u4EBA\u5458_\u4EBA\u5458" + cnname
								+ "\u5BC6\u7801\u4FEE\u6539\u6210\u529F");
				audit1.record(parameters1);
				
				//修改TQ密码
				//System.out.println("!!!!!!!!!="+vo.getTqid()+"+"+userid+"+"+pass);
				TQUser web = new TQUser();
				String rs = web.updateUser(vo.getTqid().toString(), userid, pass, vo.getCnname(), "2", " | | | | | | | | | | | ");
				System.out.println(rs);
				
				out = httpservletresponse.getWriter();
				out.print("<SCRIPT >alert('修改密码成功!');window.close();</SCRIPT>");
				
			} else {
				url = "/oabase/user/ModifyPassword.jsp";
				httpservletresponse.sendRedirect(url);

			}


		} catch (Exception e) {
		  out.println(e);
		   e.printStackTrace();

		} finally {
			if(out!=null)
			{
				out.flush();
				out.close();
			}
			if (conn != null) {
				try {
					conn.close();
					ConnLog.close("PasswordModifyServlet");

				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

	}
}