// Decompiled by Jad v1.5.7g. Copyright 2000 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/SiliconValley/Bridge/8617/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi 
// Source File Name:   SysOrgpersonHandler.java

package com.icss.oa.address.handler;

import com.icss.common.log.ConnLog;
import com.icss.j2ee.dao.*;
import com.icss.j2ee.services.DBConnectionLocator;
import com.icss.j2ee.services.DBConnectionLocatorException;
import com.icss.oa.address.dao.*;
import com.icss.oa.address.vo.*;
import com.icss.oa.phonebook.dao.PhoneInfoDAO;
import com.icss.oa.phonebook.dao.PhoneJobnameDAO;
import com.icss.oa.phonebook.vo.PhoneInfoVO;
import com.icss.oa.phonebook.vo.PhoneJobnameVO;
import com.icss.oa.util.StatSiteControl;
import java.io.PrintStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;

// Referenced classes of package com.icss.oa.address.handler:
//			  HandlerException

public class SysOrgpersonHandler
{

	private Connection conn;

	public SysOrgpersonHandler()
	{
	}

	public SysOrgpersonHandler(Connection conn)
	{
		this.conn = conn;
	}

	public void add(SysOrgpersonVO sysorgpersonVO)
		throws HandlerException
	{
		SysOrgpersonSearchDAO sysorgpersondao = new SysOrgpersonSearchDAO();
		sysorgpersondao.setValueObject(sysorgpersonVO);
		sysorgpersondao.setConnection(conn);
		try
		{
			sysorgpersondao.create();
		}
		catch(DAOException e)
		{
			throw new HandlerException(e);
		}
	}

	public List get()
		throws HandlerException
	{
		DAOFactory factory = new DAOFactory(conn);
		SysOrgpersonSearchDAO dao = new SysOrgpersonSearchDAO();
		factory.setDAO(dao);
		try
		{
			List list = factory.find(new SysOrgpersonVO());
			return list;
		}
		catch(DAOException e)
		{
			throw new HandlerException(e);
		}
	}

	public void alter(SysOrgpersonVO sysorgpersonVO)
		throws HandlerException
	{
		DAOFactory factory = new DAOFactory(conn);
		try
		{
			SysOrgpersonSearchDAO sysorgpersonDAO = new SysOrgpersonSearchDAO();
			sysorgpersonDAO.setValueObject(sysorgpersonVO);
			sysorgpersonDAO.setConnection(conn);
			sysorgpersonDAO.update(true);
		}
		catch(Exception e)
		{
			throw new HandlerException(e);
		}
	}

	public void delete(String id)
		throws HandlerException
	{
		try
		{
			DAOFactory factory = new DAOFactory(conn);
			SysOrgpersonSearchDAO sysorgpersonDAO = new SysOrgpersonSearchDAO();
			factory.setDAO(sysorgpersonDAO);
			sysorgpersonDAO.setConnection(conn);
			sysorgpersonDAO.setPersonuuid(id);
			sysorgpersonDAO.delete();
		}
		catch(Exception e)
		{
			throw new HandlerException();
		}
	}

	public List getOrgByLevel(int level)
		throws HandlerException
	{
		DAOFactory factory = new DAOFactory(conn);
		SysOrgDAO dao = new SysOrgDAO();
		dao.setOrglevel(new Integer(level));
		dao.addOrderBy("cnname", true);
		factory.setDAO(dao);
		try
		{
			List list = factory.find(new SysOrgVO());
			return list;
		}
		catch(DAOException e)
		{
			throw new HandlerException(e);
		}
	}

	public List getOrgByLevel1(int level)
		throws HandlerException
	{
		DAOFactory factory = new DAOFactory(conn);
		SysOrgDAO dao = new SysOrgDAO();
		dao.setWhereClause(" orglevel = " + level + " and SubStr(sys_org.orgcode,2,1)<>'3'  and deltag ='0' ");
		dao.addOrderBy("orgcode", true);
		factory.setDAO(dao);
		try
		{
			List list = factory.find(new SysOrgVO());
			return list;
		}
		catch(DAOException e)
		{
			throw new HandlerException(e);
		}
	}

	public List getList(String orguuid)
		throws HandlerException
	{
		StringBuffer sql = new StringBuffer();
		sql.append("select ");
		sql.append("SYS_ORGPERSON.PERSONUUID,SYS_ORGPERSON.ORGUUID,SYS_ORGPERSON.ISBELONG,SYS_PERSON.FLAG,SYS_PERSON.USERID,SYS_PERSON.ACCOUNTSTAT,SYS_PERSON.LOGINFAILNUM,SYS_PERSON.LASTLOGINIP,SYS_PERSON.PASSQUESTION,SYS_PERSON.PASSANSWER,SYS_PERSON.TTLFLAG,SYS_PERSON.ACCOUNTTTL,SYS_PERSON.DELTAG,SYS_PERSON.PERSONCODE,SYS_PERSON.CNNAME,SYS_PERSON.JOB,SYS_PERSON.ENNAME,SYS_PERSON.FIRSTNAME,SYS_PERSON.LASTNAME,SYS_PERSON.IDNUM,SYS_PERSON.CARDCODE,SYS_PERSON.SEX,SYS_PERSON.MARRYCODE,SYS_PERSON.PCODE,SYS_PERSON.HOMETEL,SYS_PERSON.OFFICETEL,SYS_PERSON.HOMEFAX,SYS_PERSON.OFFICEFAX,SYS_PERSON.MOBILE,SYS_PERSON.PAGER,SYS_PERSON.EMAIL1,SYS_PERSON.EMAIL2,SYS_PERSON.COUNTRY,SYS_PERSON.PROVINCEID,SYS_PERSON.CITYID,SYS_PERSON.CONNECTADDR,SYS_PERSON.ZIP,SYS_PERSON.EDUCODE,SYS_PERSON.DEGREECODE,SYS_PERSON.OTHERINFO ");
		sql.append("from ");
		sql.append("SYS_ORGPERSON,SYS_PERSON ");
		sql.append("where ");
		sql.append("SYS_PERSON.DELTAG = '0' and  SYS_PERSON.TTLFLAG='0' and ");
		sql.append("SYS_ORGPERSON.PERSONUUID= SYS_PERSON.PERSONUUID and ");
		sql.append("SYS_ORGPERSON.ORGUUID= '" + orguuid + "' ");
		sql.append(" order by SYS_PERSON.SEQUENCENO ,SYS_PERSON.ENNAME ASC");
		System.out.println("SysOrgpersonHandler getList() sql = " + sql.toString());
		DAOFactory factory = new DAOFactory(conn);
		SysOrgpersonSearchDAO dao = new SysOrgpersonSearchDAO();
		dao.setSearchSQL(sql.toString());
		factory.setDAO(dao);
		try
		{
			System.out.println("  findsize =" + factory.find(new SysOrgpersonVO()).size());
			return factory.find(new SysOrgpersonVO());
		}
		catch(Exception e)
		{
			e.printStackTrace();
			throw new HandlerException(e);
		}
	}

	public List getList1(String orguuid)
		throws HandlerException
	{
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT ");
		sql.append(" SYS_ORGPERSON.PERSONUUID,SYS_ORGPERSON.ORGUUID,SYS_ORGPERSON.ISBELONG,SYS_PERSON.FLAG,SYS_PERSON.USERID,SYS_PERSON.ACCOUNTSTAT,SYS_PERSON.LOGINFAILNUM,SYS_PERSON.LASTLOGINIP,SYS_PERSON.PASSQUESTION,SYS_PERSON.PASSANSWER,SYS_PERSON.TTLFLAG,SYS_PERSON.ACCOUNTTTL,SYS_PERSON.DELTAG,SYS_PERSON.PERSONCODE,SYS_PERSON.CNNAME,SYS_PERSON.JOB,SYS_PERSON.ENNAME,SYS_PERSON.FIRSTNAME,SYS_PERSON.LASTNAME,SYS_PERSON.IDNUM,SYS_PERSON.CARDCODE,SYS_PERSON.SEX,SYS_PERSON.MARRYCODE,SYS_PERSON.PCODE,SYS_PERSON.HOMETEL,SYS_PERSON.OFFICETEL,SYS_PERSON.HOMEFAX,SYS_PERSON.OFFICEFAX,SYS_PERSON.MOBILE,SYS_PERSON.PAGER,SYS_PERSON.EMAIL1,SYS_PERSON.EMAIL2,SYS_PERSON.COUNTRY,SYS_PERSON.PROVINCEID,SYS_PERSON.CITYID,SYS_PERSON.CONNECTADDR,SYS_PERSON.ZIP,SYS_PERSON.EDUCODE,SYS_PERSON.DEGREECODE,SYS_PERSON.OTHERINFO ");
		sql.append(" FROM ");
		sql.append(" SYS_ORGPERSON,SYS_PERSON ");
		sql.append(" WHERE ");
		sql.append(" SYS_PERSON.DELTAG = '0' and  SYS_PERSON.TTLFLAG='0' and ");
		sql.append(" SYS_ORGPERSON.PERSONUUID= SYS_PERSON.PERSONUUID and ");
		sql.append(" SYS_ORGPERSON.ORGUUID= '" + orguuid + "' ");
		sql.append(" order by SYS_PERSON.SEQUENCENO ,SYS_PERSON.ENNAME ASC");
		System.out.println("SysOrgpersonHandler getList() sql = " + sql.toString());
		DAOFactory factory = new DAOFactory(conn);
		SysOrgpersonSearchDAO1 dao = new SysOrgpersonSearchDAO1();
		dao.setSearchSQL(sql.toString());
		factory.setDAO(dao);
		try
		{
			return factory.find(new SysOrgpersonVO());
		}
		catch(DAOException e)
		{
			e.printStackTrace();
			throw new HandlerException(e);
		}
	}

	public List getChildOrgsPersonList(String orguuid)
		throws HandlerException
	{
		String allorguuid = "";
		String orglevelcode = "";
		try
		{
			DAOFactory factory = new DAOFactory(conn);
			SysOrgDAO dao = new SysOrgDAO();
			dao.setOrguuid(orguuid);
			factory.setDAO(dao);
			try
			{
				List list = factory.find(new SysOrgVO());
				Iterator result1 = list.iterator();
				if(result1.hasNext())
				{
					SysOrgVO sysorg = (SysOrgVO)result1.next();
					orglevelcode = sysorg.getOrglevelcode();
				}
			}
			catch(DAOException e)
			{
				throw new HandlerException(e);
			}
			SysOrgDAO dao2 = new SysOrgDAO();
			dao2.setWhereClause(" orglevelcode like '" + orglevelcode + "%' ");
			factory.setDAO(dao2);
			try
			{
				List list = factory.find(new SysOrgVO());
				for(Iterator result1 = list.iterator(); result1.hasNext();)
				{
					SysOrgVO sysorg = (SysOrgVO)result1.next();
					allorguuid = allorguuid + "'" + sysorg.getOrguuid() + "',";
				}

			}
			catch(DAOException e)
			{
				throw new HandlerException(e);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		allorguuid = allorguuid + "'" + orguuid + "'";
		StringBuffer sql = new StringBuffer();
		sql.append("select ");
		sql.append("SYS_ORGPERSON.PERSONUUID,SYS_ORGPERSON.ORGUUID,SYS_ORGPERSON.ISBELONG,SYS_PERSON.FLAG,SYS_PERSON.USERID,SYS_PERSON.ACCOUNTSTAT,SYS_PERSON.LOGINFAILNUM,SYS_PERSON.LASTLOGINIP,SYS_PERSON.PASSQUESTION,SYS_PERSON.PASSANSWER,SYS_PERSON.TTLFLAG,SYS_PERSON.ACCOUNTTTL,SYS_PERSON.DELTAG,SYS_PERSON.PERSONCODE,SYS_PERSON.CNNAME,SYS_PERSON.JOB,SYS_PERSON.ENNAME,SYS_PERSON.FIRSTNAME,SYS_PERSON.LASTNAME,SYS_PERSON.IDNUM,SYS_PERSON.CARDCODE,SYS_PERSON.SEX,SYS_PERSON.MARRYCODE,SYS_PERSON.PCODE,SYS_PERSON.HOMETEL,SYS_PERSON.OFFICETEL,SYS_PERSON.HOMEFAX,SYS_PERSON.OFFICEFAX,SYS_PERSON.MOBILE,SYS_PERSON.PAGER,SYS_PERSON.EMAIL1,SYS_PERSON.EMAIL2,SYS_PERSON.COUNTRY,SYS_PERSON.PROVINCEID,SYS_PERSON.CITYID,SYS_PERSON.CONNECTADDR,SYS_PERSON.ZIP,SYS_PERSON.EDUCODE,SYS_PERSON.DEGREECODE,SYS_PERSON.OTHERINFO ");
		sql.append("from ");
		sql.append("SYS_ORGPERSON,SYS_PERSON ");
		sql.append("where ");
		sql.append("SYS_PERSON.DELTAG = '0' and  SYS_PERSON.TTLFLAG='0' and ");
		sql.append("SYS_ORGPERSON.PERSONUUID= SYS_PERSON.PERSONUUID and ");
		sql.append("SYS_ORGPERSON.ORGUUID in(" + allorguuid + ") ");
		sql.append("order by(SYS_PERSON.USERID) ");
		DAOFactory factory = new DAOFactory(conn);
		SysOrgpersonSearchDAO dao = new SysOrgpersonSearchDAO();
		dao.setSearchSQL(sql.toString());
		factory.setDAO(dao);
		try
		{
			System.out.println("  findsize =" + factory.find(new SysOrgpersonVO()).size());
			return factory.find(new SysOrgpersonVO());
		}
		catch(Exception e)
		{
			e.printStackTrace();
			throw new HandlerException(e);
		}
	}

	public List getbyuserid(String userid)
		throws HandlerException
	{
		StringBuffer sql = new StringBuffer();
		sql.append("select ");
		sql.append("SYS_ORGPERSON.PERSONUUID,SYS_ORGPERSON.ORGUUID,SYS_ORGPERSON.ISBELONG,SYS_PERSON.FLAG,SYS_PERSON.USERID,SYS_PERSON.ACCOUNTSTAT,SYS_PERSON.LOGINFAILNUM,SYS_PERSON.LASTLOGINIP,SYS_PERSON.PASSQUESTION,SYS_PERSON.PASSANSWER,SYS_PERSON.TTLFLAG,SYS_PERSON.ACCOUNTTTL,SYS_PERSON.DELTAG,SYS_PERSON.PERSONCODE,SYS_PERSON.CNNAME,SYS_PERSON.JOB,SYS_PERSON.ENNAME,SYS_PERSON.FIRSTNAME,SYS_PERSON.LASTNAME,SYS_PERSON.IDNUM,SYS_PERSON.CARDCODE,SYS_PERSON.SEX,SYS_PERSON.MARRYCODE,SYS_PERSON.PCODE,SYS_PERSON.HOMETEL,SYS_PERSON.OFFICETEL,SYS_PERSON.HOMEFAX,SYS_PERSON.OFFICEFAX,SYS_PERSON.MOBILE,SYS_PERSON.PAGER,SYS_PERSON.EMAIL1,SYS_PERSON.EMAIL2,SYS_PERSON.COUNTRY,SYS_PERSON.PROVINCEID,SYS_PERSON.CITYID,SYS_PERSON.CONNECTADDR,SYS_PERSON.ZIP,SYS_PERSON.EDUCODE,SYS_PERSON.DEGREECODE,SYS_PERSON.OTHERINFO ");
		sql.append("from ");
		sql.append("SYS_ORGPERSON,SYS_PERSON ");
		sql.append("where ");
		sql.append("SYS_ORGPERSON.PERSONUUID= SYS_PERSON.PERSONUUID and ");
		sql.append("SYS_PERSON.DELTAG='0' and  SYS_PERSON.TTLFLAG='0' and ");
		sql.append("SYS_PERSON.USERID= '" + userid + "'");
		DAOFactory factory = new DAOFactory(conn);
		SysOrgpersonSearchDAO dao = new SysOrgpersonSearchDAO();
		dao.setSearchSQL(sql.toString());
		factory.setDAO(dao);
		try
		{
			return factory.find(new SysOrgpersonVO());
		}
		catch(Exception e)
		{
			e.printStackTrace();
			throw new HandlerException(e);
		}
	}

	public List getbyuseruuid(String useruuid)
		throws HandlerException
	{
		StringBuffer sql = new StringBuffer();
		sql.append("select ");
		sql.append("SYS_ORGPERSON.PERSONUUID,SYS_ORGPERSON.ORGUUID,SYS_ORGPERSON.ISBELONG,SYS_PERSON.FLAG,SYS_PERSON.USERID,SYS_PERSON.ACCOUNTSTAT,SYS_PERSON.LOGINFAILNUM,SYS_PERSON.LASTLOGINIP,SYS_PERSON.PASSQUESTION,SYS_PERSON.PASSANSWER,SYS_PERSON.TTLFLAG,SYS_PERSON.ACCOUNTTTL,SYS_PERSON.DELTAG,SYS_PERSON.PERSONCODE,SYS_PERSON.CNNAME,SYS_PERSON.JOB,SYS_PERSON.ENNAME,SYS_PERSON.FIRSTNAME,SYS_PERSON.LASTNAME,SYS_PERSON.IDNUM,SYS_PERSON.CARDCODE,SYS_PERSON.SEX,SYS_PERSON.MARRYCODE,SYS_PERSON.PCODE,SYS_PERSON.HOMETEL,SYS_PERSON.OFFICETEL,SYS_PERSON.HOMEFAX,SYS_PERSON.OFFICEFAX,SYS_PERSON.MOBILE,SYS_PERSON.PAGER,SYS_PERSON.EMAIL1,SYS_PERSON.EMAIL2,SYS_PERSON.COUNTRY,SYS_PERSON.PROVINCEID,SYS_PERSON.CITYID,SYS_PERSON.CONNECTADDR,SYS_PERSON.ZIP,SYS_PERSON.EDUCODE,SYS_PERSON.DEGREECODE,SYS_PERSON.OTHERINFO ");
		sql.append("from ");
		sql.append("SYS_ORGPERSON,SYS_PERSON ");
		sql.append("where ");
		sql.append("SYS_ORGPERSON.PERSONUUID= SYS_PERSON.PERSONUUID and ");
		sql.append("SYS_PERSON.DELTAG='0' and  SYS_PERSON.TTLFLAG='0' and ");
		sql.append("SYS_PERSON.PERSONUUID= '" + useruuid + "'");
		DAOFactory factory = new DAOFactory(conn);
		SysOrgpersonSearchDAO dao = new SysOrgpersonSearchDAO();
		dao.setSearchSQL(sql.toString());
		factory.setDAO(dao);
		try
		{
			return factory.find(new SysOrgpersonVO());
		}
		catch(Exception e)
		{
			e.printStackTrace();
			throw new HandlerException(e);
		}
	}

	public List getbycnname(String cnname)
		throws HandlerException
	{
		StringBuffer sql = new StringBuffer();
		sql.append("select distinct ");
		sql.append("SYS_ORGPERSON.PERSONUUID,SYS_PERSON.FLAG,SYS_PERSON.USERID,SYS_PERSON.ACCOUNTSTAT,SYS_PERSON.LOGINFAILNUM,SYS_PERSON.LASTLOGINIP,SYS_PERSON.PASSQUESTION,SYS_PERSON.PASSANSWER,SYS_PERSON.TTLFLAG,SYS_PERSON.ACCOUNTTTL,SYS_PERSON.DELTAG,SYS_PERSON.PERSONCODE,SYS_PERSON.CNNAME,SYS_PERSON.JOB,SYS_PERSON.ENNAME,SYS_PERSON.FIRSTNAME,SYS_PERSON.LASTNAME,SYS_PERSON.IDNUM,SYS_PERSON.CARDCODE,SYS_PERSON.SEX,SYS_PERSON.MARRYCODE,SYS_PERSON.PCODE,SYS_PERSON.HOMETEL,SYS_PERSON.OFFICETEL,SYS_PERSON.HOMEFAX,SYS_PERSON.OFFICEFAX,SYS_PERSON.MOBILE,SYS_PERSON.PAGER,SYS_PERSON.EMAIL1,SYS_PERSON.EMAIL2,SYS_PERSON.COUNTRY,SYS_PERSON.PROVINCEID,SYS_PERSON.CITYID,SYS_PERSON.CONNECTADDR,SYS_PERSON.ZIP,SYS_PERSON.EDUCODE,SYS_PERSON.DEGREECODE,SYS_PERSON.OTHERINFO ");
		sql.append("from ");
		sql.append("SYS_ORGPERSON,SYS_PERSON ");
		sql.append("where ");
		sql.append("SYS_ORGPERSON.PERSONUUID= SYS_PERSON.PERSONUUID and ");
		sql.append("SYS_PERSON.DELTAG='0' and SYS_PERSON.TTLFLAG='0' and ");
		sql.append("SYS_PERSON.CNNAME like '%" + cnname + "%' ");
		DAOFactory factory = new DAOFactory(conn);
		SysOrgpersonSearchDAO dao = new SysOrgpersonSearchDAO();
		dao.setSearchSQL(sql.toString());
		factory.setDAO(dao);
		try
		{
			return factory.find(new SysOrgpersonVO());
		}
		catch(Exception e)
		{
			e.printStackTrace();
			throw new HandlerException(e);
		}
	}

	public List getinorgbycnname(String cnname, String orguuid)
		throws HandlerException
	{
		String allorguuid = "";
		String orglevelcode = "";
		try
		{
			DAOFactory factory = new DAOFactory(conn);
			SysOrgDAO dao = new SysOrgDAO();
			dao.setOrguuid(orguuid);
			factory.setDAO(dao);
			try
			{
				List list = factory.find(new SysOrgVO());
				Iterator result1 = list.iterator();
				if(result1.hasNext())
				{
					SysOrgVO sysorg = (SysOrgVO)result1.next();
					orglevelcode = sysorg.getOrglevelcode();
				}
			}
			catch(DAOException e)
			{
				throw new HandlerException(e);
			}
			SysOrgDAO dao2 = new SysOrgDAO();
			dao2.setWhereClause(" orglevelcode like '" + orglevelcode + "%' ");
			factory.setDAO(dao2);
			try
			{
				List list = factory.find(new SysOrgVO());
				for(Iterator result1 = list.iterator(); result1.hasNext();)
				{
					SysOrgVO sysorg = (SysOrgVO)result1.next();
					allorguuid = allorguuid + "'" + sysorg.getOrguuid() + "',";
				}

			}
			catch(DAOException e)
			{
				throw new HandlerException(e);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		allorguuid = allorguuid + "'" + orguuid + "'";
		StringBuffer sql = new StringBuffer();
		sql.append("select distinct ");
		sql.append("SYS_ORGPERSON.PERSONUUID,SYS_PERSON.FLAG,SYS_PERSON.USERID,SYS_PERSON.ACCOUNTSTAT,SYS_PERSON.LOGINFAILNUM,SYS_PERSON.LASTLOGINIP,SYS_PERSON.PASSQUESTION,SYS_PERSON.PASSANSWER,SYS_PERSON.TTLFLAG,SYS_PERSON.ACCOUNTTTL,SYS_PERSON.DELTAG,SYS_PERSON.PERSONCODE,SYS_PERSON.CNNAME,SYS_PERSON.JOB,SYS_PERSON.ENNAME,SYS_PERSON.FIRSTNAME,SYS_PERSON.LASTNAME,SYS_PERSON.IDNUM,SYS_PERSON.CARDCODE,SYS_PERSON.SEX,SYS_PERSON.MARRYCODE,SYS_PERSON.PCODE,SYS_PERSON.HOMETEL,SYS_PERSON.OFFICETEL,SYS_PERSON.HOMEFAX,SYS_PERSON.OFFICEFAX,SYS_PERSON.MOBILE,SYS_PERSON.PAGER,SYS_PERSON.EMAIL1,SYS_PERSON.EMAIL2,SYS_PERSON.COUNTRY,SYS_PERSON.PROVINCEID,SYS_PERSON.CITYID,SYS_PERSON.CONNECTADDR,SYS_PERSON.ZIP,SYS_PERSON.EDUCODE,SYS_PERSON.DEGREECODE,SYS_PERSON.OTHERINFO ");
		sql.append("from ");
		sql.append("SYS_ORGPERSON,SYS_PERSON ");
		sql.append("where ");
		sql.append("SYS_ORGPERSON.PERSONUUID= SYS_PERSON.PERSONUUID and ");
		sql.append("SYS_PERSON.DELTAG='0' and  SYS_PERSON.TTLFLAG='0' and ");
		sql.append("SYS_ORGPERSON.ORGUUID in(" + allorguuid + ") and ");
		sql.append("SYS_PERSON.CNNAME like '%" + cnname + "%' ");
		DAOFactory factory = new DAOFactory(conn);
		SysOrgpersonSearchDAO dao = new SysOrgpersonSearchDAO();
		dao.setSearchSQL(sql.toString());
		factory.setDAO(dao);
		try
		{
			return factory.find(new SysOrgpersonVO());
		}
		catch(Exception e)
		{
			e.printStackTrace();
			throw new HandlerException(e);
		}
	}

	public String getJobFromPhonebyPeron(String personuuid)
		throws HandlerException
	{
		Connection conn = null;
		List list = null;
		String job_code = "";
		String job_name = "";
		try
		{
			System.out.println("app_name 007 " + StatSiteControl.getAppname());
			if("infopub".equals(StatSiteControl.getAppname()))
			{
				conn = DBConnectionLocator.getInstance().getConnection(StatSiteControl.gejndi());
				ConnLog.open("SysOrgpersonHandler.getJobFromPhonebyPeron");
			} else
			{
				conn = DBConnectionLocator.getInstance().getConnection("java:comp/env/ResourceOne/DataSource");
				ConnLog.open("SysOrgpersonHandler.getJobFromPhonebyPeron");
			}
			DAOFactory factory = new DAOFactory(conn);
			PhoneInfoDAO dao = new PhoneInfoDAO();
			dao.setUseruuid(personuuid);
			factory.setDAO(dao);
			list = factory.find(new PhoneInfoVO());
			if(list != null && list.size() > 0)
			{
				PhoneInfoVO vo = (PhoneInfoVO)list.get(0);
				job_code = vo.getNameids();
			}
			StringBuffer sbf = new StringBuffer();
			if(job_code != null)
			{
				StringTokenizer joblist = new StringTokenizer(job_code, ",");
				int t = 0;
				while(joblist.hasMoreTokens()) 
				{
					String name_id = joblist.nextToken();
					Integer nameID = new Integer(name_id);
					PhoneJobnameVO pjVO = getOrgjobVO(nameID, conn);
					if(pjVO != null)
					{
						job_name = pjVO.getName();
						if(t > 0)
							sbf.append(",");
						sbf.append(job_name);
						t++;
					}
				}
			} else
			{
				sbf.append("");
			}
			System.out.println("sbf " + sbf);
			String s = job_name;
			String s1 = s;
			return s1;
		}
		catch(DAOException e)
		{
			e.printStackTrace();
			throw new RuntimeException("取得人员的电话簿中的列表有误");
		}
		catch(DBConnectionLocatorException e)
		{
			e.printStackTrace();
			throw new HandlerException(e);
		}
		finally
		{
			if(conn != null)
				try
				{
					conn.close();
					ConnLog.close("SysOrgpersonHandler.getJobFromPhonebyPeron");
				}
				catch(SQLException e1)
				{
					e1.printStackTrace();
					throw new HandlerException(e1);
				}
		}
	}

	public PhoneJobnameVO getOrgjobVO(Integer nameId, Connection conn)
	{
		PhoneJobnameDAO dao = new PhoneJobnameDAO();
		dao.setNameid(nameId);
		DAOFactory factory = new DAOFactory(conn);
		factory.setDAO(dao);
		PhoneJobnameVO pvo = null;
		try
		{
			List list = factory.find(new PhoneJobnameVO());
			if(list == null || list.size() == 0)
				return pvo;
			pvo = (PhoneJobnameVO)list.get(0);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return pvo;
	}

	public String getCnnamebyUUID(String personuuid)
		throws HandlerException
	{
		Connection conn = null;
		List list = null;
		String cnname = null;
		try
		{
			conn = DBConnectionLocator.getInstance().getConnection("java:comp/env/ResourceOne/DataSource");
			ConnLog.open("SysOrgpersonHandler.getCnnamebyUUID");
			DAOFactory factory = new DAOFactory(conn);
			SysPersonDAO dao = new SysPersonDAO();
			dao.setPersonuuid(personuuid);
			list = factory.find(new SysPersonVO());
		}
		catch(DAOException e)
		{
			throw new RuntimeException("根据人的uuid得到人的中文名出错！");
		}
		catch(DBConnectionLocatorException e)
		{
			e.printStackTrace();
			throw new HandlerException(e);
		}
		finally
		{
			if(conn != null)
				try
				{
					conn.close();
					ConnLog.close("SysOrgpersonHandler.getCnnamebyUUID");
				}
				catch(SQLException e1)
				{
					e1.printStackTrace();
					throw new HandlerException(e1);
				}
		}
		if(list != null && list.size() > 0)
		{
			SysPersonVO vo = (SysPersonVO)list.get(1);
			cnname = vo.getCnname();
		}
		return cnname;
	}

	public String GetOnePhone(String personuuid)
		throws HandlerException
	{
		Connection conn = null;
		PhoneInfoVO pVO = null;
		try
		{
			System.out.println("app_name 007 " + StatSiteControl.getAppname());
			if("infopub".equals(StatSiteControl.getAppname()))
			{
				conn = DBConnectionLocator.getInstance().getConnection(StatSiteControl.gejndi());
				ConnLog.open("SysOrgpersonHandler.GetOnePhone");
			} else
			{
				conn = DBConnectionLocator.getInstance().getConnection("java:comp/env/ResourceOne/DataSource");
				ConnLog.open("SysOrgpersonHandler.GetOnePhone");
			}
			List onePhonelist = new ArrayList();
			PhoneInfoDAO pdao = new PhoneInfoDAO();
			pdao.setUseruuid(personuuid);
			DAOFactory factory = new DAOFactory(conn);
			factory.setDAO(pdao);
			onePhonelist = factory.find(new PhoneInfoVO());
			if(onePhonelist.size() > 0)
				pVO = (PhoneInfoVO)onePhonelist.get(0);
		}
		catch(DAOException e)
		{
			e.printStackTrace();
		}
		catch(DBConnectionLocatorException e)
		{
			e.printStackTrace();
		}
		finally
		{
			if(conn != null)
				try
				{
					conn.close();
					ConnLog.close("SysOrgpersonHandler.GetOnePhone");
				}
				catch(SQLException e1)
				{
					e1.printStackTrace();
					throw new HandlerException(e1);
				}
		}
		return pVO != null ? pVO.getOfficephone() : "";
	}
}
