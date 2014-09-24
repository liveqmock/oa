package com.icss.oa.hr.dao;

import com.icss.j2ee.dao.DAO;
import com.icss.j2ee.dao.DAOException;


public class HRSysPersonDAO extends DAO
{

	private String personuuid;
    private String userid;
    private String cnname;
    private String enname;
    private Integer sex;
    private String hometel;
    private String officetel;
    private String mobile;
    private String email1;
    private String job;
    private Integer tqid;
    private String hrid;
    public String getPersonuuid()
    {
        return personuuid;
    }
    public void setPersonuuid(String _personuuid)
    {
        firePropertyChange("personuuid", personuuid, _personuuid);
        personuuid = _personuuid;
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
   public Integer getSex()
    {
        return sex;
    }
    public void setSex(Integer _sex)
    {
        firePropertyChange("sex", sex, _sex);
        sex = _sex;
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
    public String getMobile()
    {
        return mobile;
    }
    public void setMobile(String _mobile)
    {
        firePropertyChange("mobile", mobile, _mobile);
        mobile = _mobile;
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
	public String getHrid()
	{
		return hrid;
	}
	public void setHrid(String _hrid)
	{
		firePropertyChange("hrid", hrid, _hrid);
		hrid = _hrid;
	}
    protected void setupFields() throws DAOException
    {
        addField("personuuid", "PERSONUUID");
        addField("userid", "USERID");
        addField("cnname", "CNNAME");
        addField("enname", "ENNAME");
        addField("sex", "SEX");
        addField("hometel", "HOMETEL");
        addField("officetel", "OFFICETEL");
        addField("mobile", "MOBILE");
        addField("email1", "EMAIL1");
        addField("job", "JOB");
        addField("tqid", "TQID");
        addField("hrid", "HRID");
        setTableName("SYS_PERSON");
    }
	public HRSysPersonDAO()
	{
		super();
	}
	
}
