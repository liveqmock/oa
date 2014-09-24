package com.icss.oa.votebbs.dao;

import java.sql.Connection;

import com.icss.j2ee.dao.DAO;
import com.icss.j2ee.dao.DAOException;

public class BbsReplyDAO extends DAO {

    private Integer reId;
    private Integer mainid;
    private String reTitle;
    private String reContext;
    private String reSenderuuid;
    private String reSendername;
    private String reTime;
    private String reOrder;
    private String reVuid;
    private String reSenderip;

    public BbsReplyDAO(){
        super();
    }

    public BbsReplyDAO(Connection conn){
        super(conn);
    }

    public java.lang.Integer getReId(){
        return reId;
    }
    public void setReId(java.lang.Integer _reId){ 
        firePropertyChange("reId",reId,_reId);
        reId = _reId;
    }
    public java.lang.Integer getMainid(){
        return mainid;
    }
    public void setMainid(java.lang.Integer _mainid){ 
        firePropertyChange("mainid",mainid,_mainid);
        mainid = _mainid;
    }
    public java.lang.String getReTitle(){
        return reTitle;
    }
    public void setReTitle(java.lang.String _reTitle){ 
        firePropertyChange("reTitle",reTitle,_reTitle);
        reTitle = _reTitle;
    }
    public java.lang.String getReContext(){
        return reContext;
    }
    public void setReContext(java.lang.String _reContext){ 
        firePropertyChange("reContext",reContext,_reContext);
        reContext = _reContext;
    }
    public java.lang.String getReSenderuuid(){
        return reSenderuuid;
    }
    public void setReSenderuuid(java.lang.String _reSenderuuid){ 
        firePropertyChange("reSenderuuid",reSenderuuid,_reSenderuuid);
        reSenderuuid = _reSenderuuid;
    }
    public java.lang.String getReSendername(){
        return reSendername;
    }
    public void setReSendername(java.lang.String _reSendername){ 
        firePropertyChange("reSendername",reSendername,_reSendername);
        reSendername = _reSendername;
    }
    public java.lang.String getReTime(){
        return reTime;
    }
    public void setReTime(java.lang.String _reTime){ 
        firePropertyChange("reTime",reTime,_reTime);
        reTime = _reTime;
    }
    public java.lang.String getReOrder(){
        return reOrder;
    }
    public void setReOrder(java.lang.String _reOrder){ 
        firePropertyChange("reOrder",reOrder,_reOrder);
        reOrder = _reOrder;
    }
    public java.lang.String getReVuid(){
        return reVuid;
    }
    public void setReVuid(java.lang.String _reVuid){ 
        firePropertyChange("reVuid",reVuid,_reVuid);
        reVuid = _reVuid;
    }
    public java.lang.String getReSenderip(){
        return reSenderip;
    }
    public void setReSenderip(java.lang.String _reSenderip){ 
        firePropertyChange("reSenderip",reSenderip,_reSenderip);
        reSenderip = _reSenderip;
    }
    protected void setupFields() throws DAOException {
        addField("reId","RE_ID");
        addField("mainid","MAINID");
        addField("reTitle","RE_TITLE");
        addField("reContext","RE_CONTEXT");
        addField("reSenderuuid","RE_SENDERUUID");
        addField("reSendername","RE_SENDERNAME");
        addField("reTime","RE_TIME");
        addField("reOrder","RE_ORDER");
        addField("reVuid","RE_VUID");
        addField("reSenderip","RE_SENDERIP");
        setTableName("BBS_REPLY");
        addKey("RE_ID");
        setAutoIncremented("RE_ID");
    }

}