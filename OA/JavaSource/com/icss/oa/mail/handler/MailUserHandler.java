package com.icss.oa.mail.handler;

import java.sql.Connection;
import java.util.List;

import com.icss.j2ee.dao.DAOException;
import com.icss.j2ee.dao.DAOFactory;
import com.icss.oa.address.dao.SysOrgpersonSearchDAO;
import com.icss.oa.address.vo.SysOrgpersonVO;

public class MailUserHandler {

	private Connection conn;

	public MailUserHandler(Connection _conn) {
		this.conn = _conn;
	}

	public List getUserbyName(String name) throws DAOException {
		StringBuffer sql = new StringBuffer();
		sql.append("select distinct ");
		sql.append("SYS_ORGPERSON.PERSONUUID,SYS_PERSON.FLAG,SYS_PERSON.USERID,SYS_PERSON.ACCOUNTSTAT,SYS_PERSON.LOGINFAILNUM,SYS_PERSON.LASTLOGINIP,SYS_PERSON.PASSQUESTION,SYS_PERSON.PASSANSWER,SYS_PERSON.TTLFLAG,SYS_PERSON.ACCOUNTTTL,SYS_PERSON.DELTAG,SYS_PERSON.PERSONCODE,SYS_PERSON.CNNAME,SYS_PERSON.JOB,SYS_PERSON.ENNAME,SYS_PERSON.FIRSTNAME,SYS_PERSON.LASTNAME,SYS_PERSON.IDNUM,SYS_PERSON.CARDCODE,SYS_PERSON.SEX,SYS_PERSON.MARRYCODE,SYS_PERSON.PCODE,SYS_PERSON.HOMETEL,SYS_PERSON.OFFICETEL,SYS_PERSON.HOMEFAX,SYS_PERSON.OFFICEFAX,SYS_PERSON.MOBILE,SYS_PERSON.PAGER,SYS_PERSON.EMAIL1,SYS_PERSON.EMAIL2,SYS_PERSON.COUNTRY,SYS_PERSON.PROVINCEID,SYS_PERSON.CITYID,SYS_PERSON.CONNECTADDR,SYS_PERSON.ZIP,SYS_PERSON.EDUCODE,SYS_PERSON.DEGREECODE,SYS_PERSON.OTHERINFO ");
		sql.append("from ");
		sql.append("SYS_ORGPERSON,SYS_PERSON ");
		sql.append("where ");
		sql.append("SYS_ORGPERSON.PERSONUUID= SYS_PERSON.PERSONUUID and ");
		sql.append("SYS_PERSON.DELTAG='0' and SYS_PERSON.TTLFLAG='0' and (");
		sql.append("SYS_PERSON.CNNAME like '" + name
				+ "%' or SYS_PERSON.USERID like'" + name
				+ "%') order by SYS_PERSON.USERID");
		DAOFactory factory = new DAOFactory(conn);
		SysOrgpersonSearchDAO dao = new SysOrgpersonSearchDAO();
		dao.setSearchSQL(sql.toString());
		
		factory.setDAO(dao);
		List list = factory.find(new SysOrgpersonVO());
		
		return list;
	}
}