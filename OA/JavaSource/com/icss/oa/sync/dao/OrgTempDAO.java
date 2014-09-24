
package com.icss.oa.sync.dao;

import java.sql.Connection;

import com.icss.j2ee.dao.DAO;
import com.icss.j2ee.dao.DAOException;

public class OrgTempDAO extends DAO {

    private String id;
    private String type;
    private String business;
    private String orgname;
    private String orgcode;
    private String serialindex;
    private String parentorgid;
    private String updcontent;
    private String oldmsg;
    private String newmsg;
    private Integer pass;
    private String time;

    public OrgTempDAO(Connection conn){
        super(conn);
    }

    public OrgTempDAO(){
        super();
    }

    public void setId(java.lang.String _id){ 
        firePropertyChange("id",id,_id);
        id = _id;
	}

    public java.lang.String getId(){
        return id;
	}


    public void setType(java.lang.String _type){ 
        firePropertyChange("type",type,_type);
        type = _type;
	}

    public java.lang.String getType(){
        return type;
	}


    public void setBusiness(java.lang.String _business){ 
        firePropertyChange("business",business,_business);
        business = _business;
	}

    public java.lang.String getBusiness(){
        return business;
	}


    public void setOrgname(java.lang.String _orgname){ 
        firePropertyChange("orgname",orgname,_orgname);
        orgname = _orgname;
	}

    public java.lang.String getOrgname(){
        return orgname;
	}


    public void setOrgcode(java.lang.String _orgcode){ 
        firePropertyChange("orgcode",orgcode,_orgcode);
        orgcode = _orgcode;
	}

    public java.lang.String getOrgcode(){
        return orgcode;
	}

    public void setSerialindex(java.lang.String _serialindex){ 
        firePropertyChange("serialindex",serialindex,_serialindex);
        serialindex = _serialindex;
	}

    public java.lang.String getSerialindex(){
        return serialindex;
	}

   public void setParentorgid(java.lang.String _parentorgid){ 
        firePropertyChange("parentorgid",parentorgid,_parentorgid);
        parentorgid = _parentorgid;
	}

    public java.lang.String getParentorgid(){
        return parentorgid;
	}
    
    public void setUpdcontent(java.lang.String _updcontent){ 
        firePropertyChange("updcontent",updcontent,_updcontent);
        updcontent = _updcontent;
	}

    public java.lang.String getUpdcontent(){
        return updcontent;
	}


    public void setOldmsg(java.lang.String _oldmsg){ 
        firePropertyChange("oldmsg",oldmsg,_oldmsg);
        oldmsg = _oldmsg;
	}

    public java.lang.String getOldmsg(){
        return oldmsg;
	}


    public void setNewmsg(java.lang.String _newmsg){ 
        firePropertyChange("newmsg",newmsg,_newmsg);
        newmsg = _newmsg;
	}

    public java.lang.String getNewmsg(){
        return newmsg;
	}
    
    public void setPass(java.lang.Integer _pass){ 
        firePropertyChange("pass",pass,_pass);
        pass = _pass;
	}

    public java.lang.Integer getPass(){
        return pass;
	}

    public void setTime(String _time) {
		firePropertyChange("time",time,_time);
    	 time = _time;		
	}
    public java.lang.String getTime(){
        return time;
	}


    protected void setupFields() throws DAOException {
    	
        addField("id","ID");
        addField("type","TYPE");
        addField("business","BUSINESS");
        addField("orgname","ORGNAME");
        addField("orgcode","ORGCODE");
        addField("serialindex","SERIALINDEX");
        addField("parentorgid","PARENTORGID");
        addField("updcontent","UPDCONTENT");
        addField("oldmsg","OLDMSG");
        addField("newmsg","NEWMSG");
        addField("pass","PASS");
        addField("time","TIME");

        setTableName("ORG_TEMP");
        addKey("ID");
    }

	

}