package com.icss.oa.hr.dao;

import com.icss.j2ee.dao.DAO;
import com.icss.j2ee.dao.DAOException;


public class HRPersonTempDAO extends DAO
{

	private String hrid;
    private String username;
    private String deptcode;
    private String orgcode;
    private Integer flag;
    
    
    public String getHrid()
	{
		return hrid;
	}
	public void setHrid(String _hrid)
	{
		firePropertyChange("hrid", hrid, _hrid);
		hrid = _hrid;
	}
	
    public String getUsername()
    {
        return username;
    }
    public void setUsername(String _username)
    {
        firePropertyChange("username", username, _username);
        username = _username;
    }
   public String getDeptcode()
    {
        return deptcode;
    }
    public void setDeptcode(String _deptcode)
    {
        firePropertyChange("deptcode", deptcode, _deptcode);
        deptcode = _deptcode;
    }
    public String getOrgcode()
    {
        return orgcode;
    }
    public void setOrgcode(String _orgcode)
    {
        firePropertyChange("orgcode", orgcode, _orgcode);
        orgcode = _orgcode;
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
   
	
    protected void setupFields() throws DAOException
    {
    	addField("hrid", "HRID");
        addField("username", "USERNAME");
        addField("deptcode", "DEPTCODE");
        addField("orgcode", "ORGCODE");
        addField("flag", "FLAG");
        setTableName("ROEEE.HRPERSON_TEMP");
    }
	public HRPersonTempDAO()
	{
		super();
	}
	
}
