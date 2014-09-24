package com.icss.oa.tq.dao;

import com.icss.j2ee.dao.DAOException;
import com.icss.j2ee.dao.SearchDAO;

public class TqOrgpersonSearchDAO extends SearchDAO
{

	private String sql;
    public String personuuid;
    public String orguuid;
    public String isbelong;
    public Integer flag;
    public String userid;
    public Integer accountstat;
    public Integer loginfailnum;
    public String lastloginip;
    public String passquestion;
    public String passanswer;
    public Integer ttlflag;
    public Integer accountttl;
    public String deltag;
    public String personcode;
    public String cnname;
    public String enname;
    public String firstname;
    public String lastname;
    public String idnum;
    public String cardcode;
    public Integer sex;
    public String marrycode;
    public String pcode;
    public String hometel;
    public String officetel;
    public String homefax;
    public String officefax;
    public String mobile;
    public String pager;
    public String email1;
    public String email2;
    public String country;
    public String provinceid;
    public String cityid;
    public String connectaddr;
    public String zip;
    public String educode;
    public String degreecode;
    public String otherinfo;
    public Integer sequenceno;
	public String job;
    public Integer tqid;
    public String getPersonuuid()
    {
        return personuuid;
    }
    public void setPersonuuid(String _personuuid)
    {
        firePropertyChange("personuuid", personuuid, _personuuid);
        personuuid = _personuuid;
    }
    public String getOrguuid()
    {
        return orguuid;
    }
    public void setOrguuid(String _orguuid)
    {
        firePropertyChange("orguuid", orguuid, _orguuid);
        orguuid = _orguuid;
    }
    public String getIsbelong()
    {
        return isbelong;
    }
    public void setIsbelong(String _isbelong)
    {
        firePropertyChange("isbelong", isbelong, _isbelong);
        isbelong = _isbelong;
    }
    public Integer getFlag()
    {
        return flag;
    }
    public void setFlag(Integer _flag)
    {
        firePropertyChange("flag", flag, _flag);
        flag = _flag;
    }
    public String getUserid()
    {
        return userid;
    }
    public void setUserid(String _userid)
    {
        firePropertyChange("userid", userid, _userid);
        userid = _userid;
    }
    public Integer getAccountstat()
    {
        return accountstat;
    }
    public void setAccountstat(Integer _accountstat)
    {
        firePropertyChange("accountstat", accountstat, _accountstat);
        accountstat = _accountstat;
    }
    public Integer getLoginfailnum()
    {
        return loginfailnum;
    }
    public void setLoginfailnum(Integer _loginfailnum)
    {
        firePropertyChange("loginfailnum", loginfailnum, _loginfailnum);
        loginfailnum = _loginfailnum;
    }
    public String getLastloginip()
    {
        return lastloginip;
    }
    public void setLastloginip(String _lastloginip)
    {
        firePropertyChange("lastloginip", lastloginip, _lastloginip);
        lastloginip = _lastloginip;
    }
    public String getPassquestion()
    {
        return passquestion;
    }
    public void setPassquestion(String _passquestion)
    {
        firePropertyChange("passquestion", passquestion, _passquestion);
        passquestion = _passquestion;
    }
    public String getPassanswer()
    {
        return passanswer;
    }
    public void setPassanswer(String _passanswer)
    {
        firePropertyChange("passanswer", passanswer, _passanswer);
        passanswer = _passanswer;
    }
    public Integer getTtlflag()
    {
        return ttlflag;
    }
    public void setTtlflag(Integer _ttlflag)
    {
        firePropertyChange("ttlflag", ttlflag, _ttlflag);
        ttlflag = _ttlflag;
    }
    public Integer getAccountttl()
    {
        return accountttl;
    }
    public void setAccountttl(Integer _accountttl)
    {
        firePropertyChange("accountttl", accountttl, _accountttl);
        accountttl = _accountttl;
    }
    public String getDeltag()
    {
        return deltag;
    }
    public void setDeltag(String _deltag)
    {
        firePropertyChange("deltag", deltag, _deltag);
        deltag = _deltag;
    }
    public String getPersoncode()
    {
        return personcode;
    }
    public void setPersoncode(String _personcode)
    {
        firePropertyChange("personcode", personcode, _personcode);
        personcode = _personcode;
    }
    public String getCnname()
    {
        return cnname;
    }
    public void setCnname(String _cnname)
    {
        firePropertyChange("cnname", cnname, _cnname);
        cnname = _cnname;
    }
    public String getEnname()
    {
        return enname;
    }
    public void setEnname(String _enname)
    {
        firePropertyChange("enname", enname, _enname);
        enname = _enname;
    }
    public String getFirstname()
    {
        return firstname;
    }
    public void setFirstname(String _firstname)
    {
        firePropertyChange("firstname", firstname, _firstname);
        firstname = _firstname;
    }
    public String getLastname()
    {
        return lastname;
    }
    public void setLastname(String _lastname)
    {
        firePropertyChange("lastname", lastname, _lastname);
        lastname = _lastname;
    }
    public String getIdnum()
    {
        return idnum;
    }
    public void setIdnum(String _idnum)
    {
        firePropertyChange("idnum", idnum, _idnum);
        idnum = _idnum;
    }
    public String getCardcode()
    {
        return cardcode;
    }
    public void setCardcode(String _cardcode)
    {
        firePropertyChange("cardcode", cardcode, _cardcode);
        cardcode = _cardcode;
    }
    public Integer getSex()
    {
        return sex;
    }
    public void setSex(Integer _sex)
    {
        firePropertyChange("sex", sex, _sex);
        sex = _sex;
    }
    public String getMarrycode()
    {
        return marrycode;
    }
    public void setMarrycode(String _marrycode)
    {
        firePropertyChange("marrycode", marrycode, _marrycode);
        marrycode = _marrycode;
    }
    public String getPcode()
    {
        return pcode;
    }
    public void setPcode(String _pcode)
    {
        firePropertyChange("pcode", pcode, _pcode);
        pcode = _pcode;
    }
    public String getHometel()
    {
        return hometel;
    }
    public void setHometel(String _hometel)
    {
        firePropertyChange("hometel", hometel, _hometel);
        hometel = _hometel;
    }
    public String getOfficetel()
    {
        return officetel;
    }
    public void setOfficetel(String _officetel)
    {
        firePropertyChange("officetel", officetel, _officetel);
        officetel = _officetel;
    }
    public String getHomefax()
    {
        return homefax;
    }
    public void setHomefax(String _homefax)
    {
        firePropertyChange("homefax", homefax, _homefax);
        homefax = _homefax;
    }
    public String getOfficefax()
    {
        return officefax;
    }
    public void setOfficefax(String _officefax)
    {
        firePropertyChange("officefax", officefax, _officefax);
        officefax = _officefax;
    }
    public String getMobile()
    {
        return mobile;
    }
    public void setMobile(String _mobile)
    {
        firePropertyChange("mobile", mobile, _mobile);
        mobile = _mobile;
    }
    public String getPager()
    {
        return pager;
    }
    public void setPager(String _pager)
    {
        firePropertyChange("pager", pager, _pager);
        pager = _pager;
    }
    public String getEmail1()
    {
        return email1;
    }
    public void setEmail1(String _email1)
    {
        firePropertyChange("email1", email1, _email1);
        email1 = _email1;
    }
    public String getEmail2()
    {
        return email2;
    }
    public void setEmail2(String _email2)
    {
        firePropertyChange("email2", email2, _email2);
        email2 = _email2;
    }
    public String getCountry()
    {
        return country;
    }
    public void setCountry(String _country)
    {
        firePropertyChange("country", country, _country);
        country = _country;
    }
    public String getProvinceid()
    {
        return provinceid;
    }
    public void setProvinceid(String _provinceid)
    {
        firePropertyChange("provinceid", provinceid, _provinceid);
        provinceid = _provinceid;
    }
    public String getCityid()
    {
        return cityid;
    }
    public void setCityid(String _cityid)
    {
        firePropertyChange("cityid", cityid, _cityid);
        cityid = _cityid;
    }
    public String getConnectaddr()
    {
        return connectaddr;
    }
    public void setConnectaddr(String _connectaddr)
    {
        firePropertyChange("connectaddr", connectaddr, _connectaddr);
        connectaddr = _connectaddr;
    }
    public String getZip()
    {
        return zip;
    }
    public void setZip(String _zip)
    {
        firePropertyChange("zip", zip, _zip);
        zip = _zip;
    }
    public String getEducode()
    {
        return educode;
    }
    public void setEducode(String _educode)
    {
        firePropertyChange("educode", educode, _educode);
        educode = _educode;
    }
    public String getDegreecode()
    {
        return degreecode;
    }
    public void setDegreecode(String _degreecode)
    {
        firePropertyChange("degreecode", degreecode, _degreecode);
        degreecode = _degreecode;
    }
    public String getOtherinfo()
    {
        return otherinfo;
    }
    public void setOtherinfo(String _otherinfo)
    {
        firePropertyChange("otherinfo", otherinfo, _otherinfo);
        otherinfo = _otherinfo;
    }
    public Integer getSequenceno()
    {
        return sequenceno;
    }
    public void setSequenceno(Integer _sequenceno)
    {
        firePropertyChange("sequenceno", sequenceno, _sequenceno);
        sequenceno = _sequenceno;
    }
   public Integer getTqid()
    {
       return tqid;
   }
   public void setTqid(Integer _tqid)
   {
        firePropertyChange("tqid", tqid, _tqid);
   }
	public String getJob()
	{
		return job;
	}
	public void setJob(String _job)
	{
		firePropertyChange("job", job, _job);
		job = _job;
	}
    protected void setupFields() throws DAOException
    {
        addField("personuuid", "SYS_ORGPERSON.PERSONUUID");
        addField("orguuid", "SYS_ORGPERSON.ORGUUID");
        addField("isbelong", "SYS_ORGPERSON.ISBELONG");
        addField("flag", "SYS_PERSON.FLAG");
        addField("userid", "SYS_PERSON.USERID");
        addField("accountstat", "SYS_PERSON.ACCOUNTSTAT");
        addField("loginfailnum", "SYS_PERSON.LOGINFAILNUM");
        addField("lastloginip", "SYS_PERSON.LASTLOGINIP");
        addField("passquestion", "SYS_PERSON.PASSQUESTION");
        addField("passanswer", "SYS_PERSON.PASSANSWER");
        addField("ttlflag", "SYS_PERSON.TTLFLAG");
        addField("accountttl", "SYS_PERSON.ACCOUNTTTL");
        addField("deltag", "SYS_PERSON.DELTAG");
        addField("personcode", "SYS_PERSON.PERSONCODE");
        addField("cnname", "SYS_PERSON.CNNAME");
        addField("enname", "SYS_PERSON.ENNAME");
        addField("firstname", "SYS_PERSON.FIRSTNAME");
        addField("lastname", "SYS_PERSON.LASTNAME");
        addField("idnum", "SYS_PERSON.IDNUM");
        addField("cardcode", "SYS_PERSON.CARDCODE");
        addField("sex", "SYS_PERSON.SEX");
        addField("marrycode", "SYS_PERSON.MARRYCODE");
        addField("pcode", "SYS_PERSON.PCODE");
        addField("hometel", "SYS_PERSON.HOMETEL");
        addField("officetel", "SYS_PERSON.OFFICETEL");
        addField("homefax", "SYS_PERSON.HOMEFAX");
        addField("officefax", "SYS_PERSON.OFFICEFAX");
        addField("mobile", "SYS_PERSON.MOBILE");
        addField("pager", "SYS_PERSON.PAGER");
        addField("email1", "SYS_PERSON.EMAIL1");
        addField("email2", "SYS_PERSON.EMAIL2");
        addField("country", "SYS_PERSON.COUNTRY");
        addField("provinceid", "SYS_PERSON.PROVINCEID");
        addField("cityid", "SYS_PERSON.CITYID");
        addField("connectaddr", "SYS_PERSON.CONNECTADDR");
        addField("zip", "SYS_PERSON.ZIP");
        addField("educode", "SYS_PERSON.EDUCODE");
        addField("degreecode", "SYS_PERSON.DEGREECODE");
        addField("otherinfo", "SYS_PERSON.OTHERINFO");
		addField("sequenceno", "SYS_PERSON.SEQUENCENO");
		addField("job", "SYS_PERSON.JOB");
        addField("tqid", "SYS_PERSON.TQID");
    }
	protected String getSearchSQL()
	{
		return sql;
	}
	public TqOrgpersonSearchDAO()
	{
		super();
	}
	public void setSearchSQL(String sql)
	{
		this.sql = sql;
	}
}
