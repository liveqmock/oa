package com.icss.resourceone.message.dao;

import java.sql.Connection;

import com.icss.j2ee.dao.DAO;
import com.icss.j2ee.dao.DAOException;

public class RoMessageDAO extends DAO {

    private Integer messageid;
    private Integer serialindex;
    private String ispublish;
    private String messagecontent;

    public RoMessageDAO(){
        super();
    }

    public RoMessageDAO(Connection conn){
        super(conn);
    }

    public java.lang.Integer getMessageid(){
        return messageid;
    }
    public void setMessageid(java.lang.Integer _messageid){ 
        firePropertyChange("messageid",messageid,_messageid);
        messageid = _messageid;
    }
    public java.lang.Integer getSerialindex(){
        return serialindex;
    }
    public void setSerialindex(java.lang.Integer _serialindex){ 
        firePropertyChange("serialindex",serialindex,_serialindex);
        serialindex = _serialindex;
    }
    public java.lang.String getIspublish(){
        return ispublish;
    }
    public void setIspublish(java.lang.String _ispublish){ 
        firePropertyChange("ispublish",ispublish,_ispublish);
        ispublish = _ispublish;
    }
    public java.lang.String getMessagecontent(){
        return messagecontent;
    }
    public void setMessagecontent(java.lang.String _messagecontent){ 
        firePropertyChange("messagecontent",messagecontent,_messagecontent);
        messagecontent = _messagecontent;
    }
    protected void setupFields() throws DAOException {
        addField("messageid","MESSAGEID");
        addField("serialindex","SERIALINDEX");
        addField("ispublish","ISPUBLISH");
        addField("messagecontent","MESSAGECONTENT");
        setTableName("RO_MESSAGE");
        addKey("MESSAGEID");
        setAutoIncremented("MESSAGEID");
    }

}