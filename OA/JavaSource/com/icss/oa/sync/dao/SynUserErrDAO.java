
package com.icss.oa.sync.dao;

import java.sql.Connection;

import com.icss.j2ee.dao.DAO;
import com.icss.j2ee.dao.DAOException;

public class SynUserErrDAO extends DAO {

	private String operateid;
    private String pname;
    private String orgid;
    private String deptid;
    private String operatetype;
    private String newmsg;
    private String business;
    private String hrid;
    private String updcontent;
    private String mobilephone;
    private String serialindex;
    private String time;
    private String result;
    

    public SynUserErrDAO(Connection conn){
        super(conn);
    }

    public SynUserErrDAO(){
        super();
    }
    public void setOperateid(java.lang.String _operateid){ 
        firePropertyChange("operateid",operateid,_operateid);
        operateid = _operateid;
	}

    public java.lang.String getOperateid(){
        return operateid;
	}


    public void setPname(java.lang.String _pname){ 
        firePropertyChange("pname",pname,_pname);
        pname = _pname;
	}

    public java.lang.String getPname(){
        return pname;
	}


    public void setOrgid(java.lang.String _orgid){ 
        firePropertyChange("orgid",orgid,_orgid);
        orgid = _orgid;
	}

    public java.lang.String getOrgid(){
        return orgid;
	}


    public void setDeptid(java.lang.String _deptid){ 
        firePropertyChange("deptid",deptid,_deptid);
        deptid = _deptid;
	}

    public java.lang.String getDeptid(){
        return deptid;
	}

    public void setOperatetype(java.lang.String _operatetype){ 
        firePropertyChange("operatetype",operatetype,_operatetype);
        operatetype = _operatetype;
	}

    public java.lang.String getOperatetype(){
        return operatetype;
	}


    public void setNewmsg(java.lang.String _newmsg){ 
        firePropertyChange("newmsg",newmsg,_newmsg);
        newmsg = _newmsg;
	}

    public java.lang.String getNewmsg(){
        return newmsg;
	}


    public void setBusiness(java.lang.String _business){ 
        firePropertyChange("business",business,_business);
        business = _business;
	}

    public java.lang.String getBusiness(){
        return business;
	}


    public void setHrid(java.lang.String _hrid){ 
        firePropertyChange("hrid",hrid,_hrid);
        hrid = _hrid;
	}

    public java.lang.String getHrid(){
        return hrid;
	}


    public void setUpdcontent(java.lang.String _updcontent){ 
        firePropertyChange("updcontent",updcontent,_updcontent);
        updcontent = _updcontent;
	}

    public java.lang.String getUpdcontent(){
        return updcontent;
	}

    public void setMobilephone(java.lang.String _mobilephone){ 
        firePropertyChange("mobilephone",mobilephone,_mobilephone);
        mobilephone = _mobilephone;
	}

    public java.lang.String getMobilephone(){
        return mobilephone;
	}
    
    public void setSerialindex(java.lang.String _serialindex){ 
        firePropertyChange("serialindex",serialindex,_serialindex);
        serialindex = _serialindex;
	}

    public java.lang.String getSerialindex(){
        return serialindex;
	}
    
    public void setTime(java.lang.String _time){ 
        firePropertyChange("time",time,_time);
        time = _time;
	}

    public java.lang.String getTime(){
        return time;
	}
    
    public void setResult(java.lang.String _result){ 
        firePropertyChange("result",result,_result);
        result = _result;
	}

    public java.lang.String getResult(){
        return result;
	}


    protected void setupFields() throws DAOException {
        addField("operateid","OPERATEID");
        addField("pname","PNAME");
        addField("orgid","ORGID");
        addField("deptid","DEPTID");
        addField("operatetype","OPERATETYPE");
        addField("newmsg","NEWMSG");
        addField("business","BUSINESS");
        addField("hrid","HRID");
        addField("updcontent","UPDCONTENT");
        addField("mobilephone","MOBILEPHONE");
        addField("serialindex","SERIALINDEX");
        addField("time","OPERATEDATE");
        addField("result","RESULT");

        setTableName("SYNCHROUSERINFO");
        addKey("OPERATEID");
       
    }
}