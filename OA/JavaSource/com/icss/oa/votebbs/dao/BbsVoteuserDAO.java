package com.icss.oa.votebbs.dao;

import java.sql.Connection;

import com.icss.j2ee.dao.DAO;
import com.icss.j2ee.dao.DAOException;

public class BbsVoteuserDAO extends DAO {

    private Integer vuId;
    private String vuName;
    private String vuSex;
    private String vuDep;
    private String vuPost;
    private String vuUuid;
    private String vuIp;
    private String vuLoginname;
    private String vuPassword;
    private String	vuMainid;
    public BbsVoteuserDAO(){
        super();
    }

    public BbsVoteuserDAO(Connection conn){
        super(conn);
    }

    public java.lang.Integer getVuId(){
        return vuId;
    }
    public void setVuId(java.lang.Integer _vuId){ 
        firePropertyChange("vuId",vuId,_vuId);
        vuId = _vuId;
    }
    public java.lang.String getVuName(){
        return vuName;
    }
    public void setVuName(java.lang.String _vuName){ 
        firePropertyChange("vuName",vuName,_vuName);
        vuName = _vuName;
    }
    public java.lang.String getVuSex(){
        return vuSex;
    }
    public void setVuSex(java.lang.String _vuSex){ 
        firePropertyChange("vuSex",vuSex,_vuSex);
        vuSex = _vuSex;
    }
    public java.lang.String getVuDep(){
        return vuDep;
    }
    public void setVuDep(java.lang.String _vuDep){ 
        firePropertyChange("vuDep",vuDep,_vuDep);
        vuDep = _vuDep;
    }
    public java.lang.String getVuPost(){
        return vuPost;
    }
    public void setVuPost(java.lang.String _vuPost){ 
        firePropertyChange("vuPost",vuPost,_vuPost);
        vuPost = _vuPost;
    }
    public java.lang.String getVuUuid(){
        return vuUuid;
    }
    public void setVuUuid(java.lang.String _vuUuid){ 
        firePropertyChange("vuUuid",vuUuid,_vuUuid);
        vuUuid = _vuUuid;
    }
    public java.lang.String getVuIp(){
        return vuIp;
    }
    public void setVuIp(java.lang.String _vuIp){ 
        firePropertyChange("vuIp",vuIp,_vuIp);
        vuIp = _vuIp;
    }
    public java.lang.String getVuLoginname(){
        return vuLoginname;
    }
    public void setVuLoginname(java.lang.String _vuLoginname){ 
        firePropertyChange("vuLoginname",vuLoginname,_vuLoginname);
        vuLoginname = _vuLoginname;
    }
    public java.lang.String getVuPassword(){
        return vuPassword;
    }
    public void setVuPassword(java.lang.String _vuPassword){ 
        firePropertyChange("vuPassword",vuPassword,_vuPassword);
        vuPassword = _vuPassword;
    }
    public java.lang.String getVuMainid(){
        return vuMainid;
    }
    public void setVuMainid(java.lang.String _vuMainid){ 
    	firePropertyChange("vuMainid",vuMainid,_vuMainid);
    	vuMainid = _vuMainid;
    }
    protected void setupFields() throws DAOException {
        addField("vuId","VU_ID");
        addField("vuName","VU_NAME");
        addField("vuSex","VU_SEX");
        addField("vuDep","VU_DEP");
        addField("vuPost","VU_POST");
        addField("vuUuid","VU_UUID");
        addField("vuIp","VU_IP");
        addField("vuLoginname","VU_LOGINNAME");
        addField("vuPassword","VU_PASSWORD");
        addField("vuMainid","VU_MAINID");
        setTableName("BBS_VOTEUSER");
        addKey("VU_ID");
        setAutoIncremented("VU_ID");
    }

}