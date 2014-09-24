/*
 * 创建日期 2008-2-26
 *
 * 更改所生成文件模板为
 * 窗口 > 首选项 > Java > 代码生成 > 代码和注释
 */
package com.icss.oa.zbs.duty.dao;

/**
 * @author 王苏
 *
 * 更改所生成类型注释的模板为
 * 窗口 > 首选项 > Java > 代码生成 > 代码和注释
 */
import java.sql.Timestamp;
import java.sql.Connection;

import com.icss.j2ee.dao.DAO;
import com.icss.j2ee.dao.DAOException;

public class TbZbsWorkinfomainDAO extends DAO {

    private Integer wimId;
    private Timestamp witDate;
    private String witClass;
    private String witLeader;
    private String witSecret;
    private String witCreater;
    private Timestamp witCreatetime;
	private String lastEditer;
	private String lastIP;

    public TbZbsWorkinfomainDAO(Connection conn){
        super(conn);
    }

    public TbZbsWorkinfomainDAO(){
        super();
    }

    public void setWimId(java.lang.Integer _wimId){ 
        firePropertyChange("wimId",wimId,_wimId);
        wimId = _wimId;
	}

    public java.lang.Integer getWimId(){
        return wimId;
	}


    public void setWitDate(java.sql.Timestamp _witDate){ 
        firePropertyChange("witDate",witDate,_witDate);
        witDate = _witDate;
	}

    public java.sql.Timestamp getWitDate(){
        return witDate;
	}


    public void setWitClass(java.lang.String _witClass){ 
        firePropertyChange("witClass",witClass,_witClass);
        witClass = _witClass;
	}

    public java.lang.String getWitClass(){
        return witClass;
	}


    public void setWitLeader(java.lang.String _witLeader){ 
        firePropertyChange("witLeader",witLeader,_witLeader);
        witLeader = _witLeader;
	}

    public java.lang.String getWitLeader(){
        return witLeader;
	}


    public void setWitSecret(java.lang.String _witSecret){ 
        firePropertyChange("witSecret",witSecret,_witSecret);
        witSecret = _witSecret;
	}

    public java.lang.String getWitSecret(){
        return witSecret;
	}


    public void setWitCreater(java.lang.String _witCreater){ 
        firePropertyChange("witCreater",witCreater,_witCreater);
        witCreater = _witCreater;
	}

    public java.lang.String getWitCreater(){
        return witCreater;
	}


    public void setWitCreatetime(java.sql.Timestamp _witCreatetime){ 
        firePropertyChange("witCreatetime",witCreatetime,_witCreatetime);
        witCreatetime = _witCreatetime;
	}

    public java.sql.Timestamp getWitCreatetime(){
        return witCreatetime;
	}

	public void setLastEditer(java.lang.String _lastEditer) {
		firePropertyChange("lastEditer", lastEditer, _lastEditer);
		lastEditer = _lastEditer;
	}

	public java.lang.String getLastEditer() {
		return lastEditer;
	}
	public void setLastIP(java.lang.String _lastIP) {
		firePropertyChange("lastIP", lastIP, _lastIP);
		lastIP = _lastIP;
	}

	public java.lang.String getLastIP() {
		return lastIP;
	}
	
    protected void setupFields() throws DAOException {
        addField("wimId","WIM_ID");
        addField("witDate","WIT_DATE");
        addField("witClass","WIT_CLASS");
        addField("witLeader","WIT_LEADER");
        addField("witSecret","WIT_SECRET");
        addField("witCreater","WIT_CREATER");
        addField("witCreatetime","WIT_CREATETIME");
		addField("lastEditer", "LAST_EDITER");
		addField("lastIP", "LAST_IP");
		
        setTableName("TB_ZBS_WORKINFOMAIN");
        addKey("WIM_ID");
		setAutoIncremented("WIM_ID");
    }

}