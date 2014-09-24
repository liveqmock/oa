
package com.icss.oa.sync.dao;

import java.sql.Connection;

import com.icss.j2ee.dao.DAO;
import com.icss.j2ee.dao.DAOException;

public class SysOrgShortcutDAO extends DAO {

    private Integer sysid;
    private String orguuid;
    
    public SysOrgShortcutDAO(Connection conn){
        super(conn);
    }

    public SysOrgShortcutDAO(){
        super();
    }

    public void setSysid(Integer _id){ 
        firePropertyChange("sysid",sysid,_id);
        sysid = _id;
	}

    public Integer getSysid(){
        return sysid;
	}


    public void setOrguuid(java.lang.String _orguuid){ 
        firePropertyChange("orguuid",orguuid,_orguuid);
        orguuid = _orguuid;
	}

    public java.lang.String getOrguuid(){
        return orguuid;
	}


    protected void setupFields() throws DAOException {
    	
        addField("sysid","SYSID");
        addField("orguuid","ORGUUID");
        setTableName("RO_SYSORGSHORTCUT");
    }

}