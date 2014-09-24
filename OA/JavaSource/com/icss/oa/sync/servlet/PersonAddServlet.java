// Decompiled Using: FrontEnd Plus v2.03 and the JAD Engine
// Available From: http://www.reflections.ath.cx
// Decompiler options: packimports(3) 

package com.icss.oa.sync.servlet;

import com.icss.j2ee.audit.*;
import com.icss.j2ee.audit.core.Parameters;
import com.icss.j2ee.dao.DAOFactory;
import com.icss.j2ee.log.Log;
import com.icss.j2ee.log.LogFactory;
import com.icss.j2ee.services.DBConnectionLocator;
import com.icss.j2ee.servlet.ServletBase;
import com.icss.j2ee.util.Globals;
import com.icss.j2ee.util.UUID;
import com.icss.j2ee.util.UnixPwdCrypt;
import com.icss.oa.filetransfer.handler.MailUserHandler;
import com.icss.oa.hr.admin.HRGroupWebservice;
import com.icss.oa.phonebook.handler.PersonSyncHandler;
import com.icss.oa.sync.dao.PersonDAO;
import com.icss.oa.sync.handler.OrgSyncHandler;
import com.icss.oa.sync.handler.UserSyncHandler;
import com.icss.oa.tq.Webservice.TQUser;
import com.icss.resourceone.common.login.dao.OrgPersonDAO;
import com.icss.resourceone.common.logininfo.UserInfo;
import com.icss.resourceone.org.model.OrgVO;
import com.icss.resourceone.policy.account.handler.PasswordPolicyHandler;
import com.icss.resourceone.sdk.extension.HrPerson;
import com.icss.resourceone.sdk.extension.UserManageExtendException;
import com.icss.resourceone.user.UserConstants;
import com.icss.resourceone.user.dao.PersonAccountDAO;
import com.icss.resourceone.user.handler.ModifyPasswordhandle;
import com.icss.resourceone.user.handler.UserExtensionManager;
import com.icss.resourceone.user.synchronization.*;
import com.icss.resourceone.util.ParamUtils;
import java.io.*;
import java.sql.*;
import java.util.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PersonAddServlet extends ServletBase {

	public PersonAddServlet() {
	}

	protected void performTask(HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse)
        throws ServletException, IOException
    {
        String s;
        String s1;
        String s2;
        String s3;
        UserInfo userinfo;
        String s4;
        String s5;
        Connection connection;
        Connection oaconn = null;
        Integer integer = ParamUtils.getIntegerParameter(httpservletrequest, "flag", new Integer(1));
        s = ParamUtils.getParameter(httpservletrequest, "orgid");
        s1 = httpservletrequest.getParameter("backurl");
        s2 = httpservletrequest.getParameter("pgId");
        s3 = ParamUtils.getParameter(httpservletrequest, "Password").trim();
        userinfo = (UserInfo)getUser(httpservletrequest);
        s4 = userinfo.getUserID();
        s5 = ParamUtils.getParameter(httpservletrequest, "FirstName").trim() + ParamUtils.getParameter(httpservletrequest, "LastName").trim();
        String s6 = httpservletrequest.getParameter("SnoPrefix");
        Integer integer2 = ParamUtils.getIntegerParameter(httpservletrequest, "Sequenceno", new Integer(0));
        connection = null;
        String s7;
        InputStream inputstream;
        PersonAccountDAO personaccountdao = new PersonAccountDAO();
        PersonDAO persondao = new PersonDAO();
        s7 = (new UUID()).toString();
        String userid = ParamUtils.getParameter(httpservletrequest, "UserID").trim();
        personaccountdao.setUserid(userid);
        System.out.println(httpservletrequest.getParameter("UserID"));
        int i = Integer.parseInt(userinfo.getIdType().toString());
        Integer integer1;
        if(i == 3)
            integer1 = UserConstants.WEBMASTER_UPDATE;
        else
        if(i == 5 )
            integer1 = UserConstants.APPADMIN_UPDATE;
        else
            integer1 = UserConstants.SUBADMIN_UPDATE;
        personaccountdao.setFlag(integer1);
        personaccountdao.setPersonuuid(s7);
        String pass =UnixPwdCrypt.crypt("RO", s3);
        personaccountdao.setPassword(pass);
        personaccountdao.setAccountstat(new Integer(0));
        personaccountdao.setLoginfailnum(new Integer(0));
        personaccountdao.setLastloginip("0.0.0.0");
        personaccountdao.setLastlogindate(new Timestamp(System.currentTimeMillis()));
        personaccountdao.setTtlflag(ParamUtils.getIntegerParameter(httpservletrequest, "TTLFlag", new Integer(1)));
        personaccountdao.setAccountttl(ParamUtils.getIntegerParameter(httpservletrequest, "AccountTTL", new Integer(0)));
        personaccountdao.setCreatetime(new Timestamp(System.currentTimeMillis()));
        personaccountdao.setPassquestion("password");
        personaccountdao.setPassanswer("pass");
        personaccountdao.setDeltag("0");
        persondao.setPersonuuid(personaccountdao.getPersonuuid());
        persondao.setFirstname(ParamUtils.getParameter(httpservletrequest, "FirstName").trim());
        persondao.setLastname(ParamUtils.getParameter(httpservletrequest, "LastName").trim());
        String cnname =ParamUtils.getParameter(httpservletrequest, "CnName").trim();
        persondao.setCnname(cnname);
        persondao.setEnname(ParamUtils.getParameter(httpservletrequest, "EnName").trim());
        persondao.setPcode(httpservletrequest.getParameter("PoliticalStat"));
        persondao.setIdnum(ParamUtils.getParameter(httpservletrequest, "IDNum").trim());
        persondao.setCardcode(httpservletrequest.getParameter("IDType"));
        persondao.setSex(ParamUtils.getIntegerParameter(httpservletrequest, "Sex", new Integer(3)));
        persondao.setMarrycode(httpservletrequest.getParameter("Marry"));
        persondao.setOfficetel(ParamUtils.getParameter(httpservletrequest, "OfficeTel").trim());
        persondao.setOfficefax(ParamUtils.getParameter(httpservletrequest, "OfficeFax").trim());
        persondao.setHometel(ParamUtils.getParameter(httpservletrequest, "HomeTel").trim());
        persondao.setHomefax(ParamUtils.getParameter(httpservletrequest, "HomeFax").trim());
        persondao.setMobile(ParamUtils.getParameter(httpservletrequest, "Mobile").trim());
        persondao.setPager(ParamUtils.getParameter(httpservletrequest, "Pager").trim());
        persondao.setEmail1(ParamUtils.getParameter(httpservletrequest, "Email1").trim());
        persondao.setEmail2(ParamUtils.getParameter(httpservletrequest, "Email2").trim());
        persondao.setCountry(ParamUtils.getParameter(httpservletrequest, "Country").trim());
        persondao.setProvinceid(httpservletrequest.getParameter("Nation"));
        persondao.setCityid(httpservletrequest.getParameter("City"));
        persondao.setZip(ParamUtils.getParameter(httpservletrequest, "Zip").trim());
        persondao.setConnectaddr(ParamUtils.getParameter(httpservletrequest, "ConnectAddr").trim());
        persondao.setEducode(httpservletrequest.getParameter("LastEduLevel"));
        persondao.setDegreecode(httpservletrequest.getParameter("LastDegree"));
        persondao.setOtherinfo(ParamUtils.getParameter(httpservletrequest, "OtherInfo").trim());
        persondao.setJob(ParamUtils.getParameter(httpservletrequest, "job").trim());
        persondao.setPersoncode(ParamUtils.getParameter(httpservletrequest, "PersonCode").trim());
        //persondao.setOrguuid(s);
        //persondao.setPgId(s2);
        persondao.setSequenceno(ParamUtils.getIntegerParameter(httpservletrequest, "Sequenceno", new Integer(0)));
        persondao.setAliasname("");
        try
        {
        connection = getConnection("jdbc/ROEEE");
        
        OrgPersonDAO orgpersondao = new OrgPersonDAO();
		orgpersondao.setConnection(connection);
		orgpersondao.setPersonuuid(s7);
		orgpersondao.setOrguuid(s);
		orgpersondao.setIsbelong("1");
       
		//新华通
		UserSyncHandler handler = new UserSyncHandler(connection);
		OrgVO vo =handler.getOrgCode(ParamUtils.getParameter(httpservletrequest, "orgid"));
		String deptcode = vo.getOrgcode();
		
		OrgSyncHandler  handler1 =new OrgSyncHandler(connection);
		String orgname = handler1.getOrgName(deptcode);
		String parameters = orgname +"| | | | | | | ";
		
		TQUser tq = new TQUser();
		String tqid = tq.oneUserRegister(userid,pass, "0",cnname, "", "", "2", parameters);
		if (tqid.length() < 7) {
			System.out
					.println("##########################创建 TQ 帐号 失败 ！！！");
		} else {
			persondao.setTqid(new Integer(tqid));
		}
		
		//邮箱帐号
		oaconn = DBConnectionLocator.getInstance().getConnection(
				Globals.DATASOURCEJNDI);
		
		MailUserHandler mailhandler = new MailUserHandler(oaconn);
		mailhandler.createUser(userid, s7, ParamUtils.getParameter(httpservletrequest, "orgid"));
		
		//PersonSyncHandler phonehandler = new PersonSyncHandler(oaconn);
		//phonehandler.newPersonPhoneInfo(ParamUtils.getParameter(httpservletrequest, "orgid"), s7, cnname, hrid);
		
		personaccountdao.setConnection(connection);
		personaccountdao.create();
		persondao.setConnection(connection);
		persondao.create();
		orgpersondao.create();
        
		//人事接口
		if(httpservletrequest.getParameter("sync")!=null && "sync".endsWith(httpservletrequest.getParameter("sync"))){
		String XML;
		if(vo.getOrglevel()<4){
			XML = handler.getHrXML("new",userid,deptcode,"",s7);
		}else{
			//String orgcode= handler.getOrgCode(vo.getParentorguuid()).getOrgcode();
			String orgcode;
			OrgVO vo1 = handler.getOrgCode(vo.getParentorguuid());
			if(vo1.getOrglevel()>3){
				orgcode= handler.getOrgCode(vo1.getParentorguuid()).getOrgcode();
			}else{
				orgcode=vo1.getOrgcode();
			}
				
			XML = handler.getHrXML("new",userid, orgcode, deptcode,s7);
		}
		HRGroupWebservice hw = new HRGroupWebservice();
		System.out.println(XML);
		hw.BackToHR(XML);
		}
		
		if(ParamUtils.getParameter(httpservletrequest, "signature").trim().length()>0){
            FileInputStream fileinputstream = new FileInputStream(new File(getUploadFileFullName(httpservletrequest, "signature")));
            DAOFactory daofactory = new DAOFactory(connection);
            PersonDAO persondao1 = new PersonDAO();
            persondao1.setPersonuuid(s7);
            persondao1.setConnection(connection);
            daofactory.setDAO(persondao1);
            persondao1.setSignature(fileinputstream);
            persondao1.update(true);
            fileinputstream.close();
            
		}
		
            Audit audit1 = AuditFactory.getFactory().getInstance("system");
            Parameters parameters1 = new Parameters();
            parameters1.setParameter("ACTOR", httpservletrequest.getRemoteAddr() + ":" + s4);
            parameters1.setParameter("OBJECT", "\u7EC4\u7EC7\u4EBA\u5458\u4EBA\u5458\u7EF4\u62A4");
            parameters1.setParameter("ACTION", "\u4EBA\u5458\u6DFB\u52A0");
            parameters1.setParameter("MESSAGE", "\u7EC4\u7EC7\u4EBA\u5458_\u4EBA\u5458" + s5 + "\u6DFB\u52A0\u6210\u529F");
            audit1.record(parameters1);
            httpservletresponse.sendRedirect(s1);
        }
        catch(Exception e) {
        	e.printStackTrace();
        	
        	Audit audit = AuditFactory.getFactory().getInstance("system");
            Parameters parameters = new Parameters();
            parameters.setParameter("ACTOR", httpservletrequest.getRemoteAddr() + ":" + s4);
            parameters.setParameter("OBJECT", "\u7EC4\u7EC7\u4EBA\u5458\u4EBA\u5458\u7EF4\u62A4");
            parameters.setParameter("ACTION", "\u4EBA\u5458\u6DFB\u52A0");
            parameters.setParameter("MESSAGE", "\u7EC4\u7EC7\u4EBA\u5458_\u4EBA\u5458" + s5 + "\u6DFB\u52A0\u5931\u8D25");
            audit.record(parameters);
        }
       
        //break MISSING_BLOCK_LABEL_1504;
        //Exception exception;
       // exception;
       // handleError(exception);
      
       // break MISSING_BLOCK_LABEL_1504;
      //  local;
        finally {
			try {
				if (connection != null && !connection.isClosed())

					connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			try {
				if (oaconn != null && oaconn.isClosed())
					oaconn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
       // JVM INSTR ret 30;
    }
}