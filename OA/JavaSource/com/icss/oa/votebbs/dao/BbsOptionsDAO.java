package com.icss.oa.votebbs.dao;

import java.io.InputStream;
import java.sql.Connection;

import com.icss.j2ee.dao.DAO;
import com.icss.j2ee.dao.DAOException;

public class BbsOptionsDAO extends DAO {

    private Integer opId;
    private Integer mainid;
    private String opTitle;
    private String opContext;
    private String opOrder;
    private InputStream opAccessory;
    private String opPic;
    private String opNum;
    private String opGood;
    private String opMid;
    private String opBad;
    private InputStream	opPicblob;
    private	String	opAccessoryname;
    private String	opScore;

    public BbsOptionsDAO(){
        super();
    }

    public BbsOptionsDAO(Connection conn){
        super(conn);
    }

    public java.lang.Integer getOpId(){
        return opId;
    }
    public void setOpId(java.lang.Integer _opId){ 
        firePropertyChange("opId",opId,_opId);
        opId = _opId;
    }
    public java.lang.Integer getMainid(){
        return mainid;
    }
    public void setMainid(java.lang.Integer _mainid){ 
        firePropertyChange("mainid",mainid,_mainid);
        mainid = _mainid;
    }
    public java.lang.String getOpTitle(){
        return opTitle;
    }
    public void setOpTitle(java.lang.String _opTitle){ 
        firePropertyChange("opTitle",opTitle,_opTitle);
        opTitle = _opTitle;
    }
    public java.lang.String getOpContext(){
        return opContext;
    }
    public void setOpContext(java.lang.String _opContext){ 
        firePropertyChange("opContext",opContext,_opContext);
        opContext = _opContext;
    }
    public java.lang.String getOpOrder(){
        return opOrder;
    }
    public void setOpOrder(java.lang.String _opOrder){ 
        firePropertyChange("opOrder",opOrder,_opOrder);
        opOrder = _opOrder;
    }
    public InputStream getOpAccessory(){
        return opAccessory;
    }
    public void setOpAccessory(InputStream _opAccessory){ 
        firePropertyChange("opAccessory",opAccessory,_opAccessory);
        opAccessory = _opAccessory;
    }
    public InputStream getOpPicblob(){
        return opPicblob;
    }
    public void setOpPicblob(InputStream _opPicblob){ 
        firePropertyChange("opPicblob",opPicblob,_opPicblob);
        opPicblob = _opPicblob;
    }
    public java.lang.String getOpAccessoryname(){
        return opAccessoryname;
    }
    public void setOpAccessoryname(java.lang.String _opAccessoryname){ 
        firePropertyChange("opAccessoryname",opAccessoryname,_opAccessoryname);
        opAccessoryname = _opAccessoryname;
    }
    
    public java.lang.String getOpPic(){
        return opPic;
    }
    public void setOpPic(java.lang.String _opPic){ 
        firePropertyChange("opPic",opPic,_opPic);
        opPic = _opPic;
    }
    public java.lang.String getOpNum(){
        return opNum;
    }
    public void setOpNum(java.lang.String _opNum){ 
        firePropertyChange("opNum",opNum,_opNum);
        opNum = _opNum;
    }
    public java.lang.String getOpGood(){
        return opGood;
    }
    public void setOpGood(java.lang.String _opGood){ 
        firePropertyChange("opGood",opGood,_opGood);
        opGood = _opGood;
    }
    public java.lang.String getOpMid(){
        return opMid;
    }
    public void setOpMid(java.lang.String _opMid){ 
        firePropertyChange("opMid",opMid,_opMid);
        opMid = _opMid;
    }
    public java.lang.String getOpScore(){
        return opScore;
    }
    public void setOpScore(java.lang.String _opScore){ 
        firePropertyChange("opScore",opScore,_opScore);
        opScore = _opScore;
    }
    public java.lang.String getOpBad(){
        return opBad;
    }
    public void setOpBad(java.lang.String _opBad){ 
        firePropertyChange("opBad",opBad,_opBad);
        opBad = _opBad;
    }
    protected void setupFields() throws DAOException {
        addField("opId","OP_ID");
        addField("mainid","MAINID");
        addField("opTitle","OP_TITLE");
        addField("opContext","OP_CONTEXT");
        addField("opOrder","OP_ORDER");
        addField("opAccessory","OP_ACCESSORY");
        addField("opPic","OP_PIC");
        addField("opNum","OP_NUM");
        addField("opGood","OP_GOOD");
        addField("opMid","OP_MID");
        addField("opBad","OP_BAD");
        addField("opAccessoryname","OP_ACCESSORYNAME");
        addField("opPicblob","OP_PICBLOB");
        addField("opScore","OP_SCORE");
        setTableName("BBS_OPTIONS");
        
        
        addKey("OP_ID");
        setAutoIncremented("OP_ID");
    }

}