package com.icss.oa.tq.dao;
import java.sql.Connection;

import com.icss.j2ee.dao.DAO;
import com.icss.j2ee.dao.DAOException;

public class TqRopersonDAO extends DAO
{


    public String personuuid;
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
    
    public Integer getTqid()
    {
        return tqid;
    }
    public void setTqid(Integer _tqid)
    {
        firePropertyChange("tqid", tqid, _tqid);
        tqid = _tqid;
    }
    
    
    
    protected void setupFields() throws DAOException
    {
    	addField("personuuid", "PERSONUUID");
		addField("tqid", "TQID");
		addKey("PERSONUUID");
		setTableName("ROEEE.RO_PERSON");
    }
	public TqRopersonDAO()
	{
		super();
	}
	public TqRopersonDAO(Connection conn) {
		super(conn);
	}
}