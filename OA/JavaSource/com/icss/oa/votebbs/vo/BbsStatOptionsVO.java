package com.icss.oa.votebbs.vo;


import java.io.InputStream;

import com.icss.j2ee.vo.ValueObject;

public class BbsStatOptionsVO extends ValueObject {

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
	private	String[][] sexstatnum;	
	private String[][]	poststatnum;


    public String[][] getSexstatnum(){
        return sexstatnum;
    }
    public void setSexstatnum(String[][] _sexstatnum){ 
    	sexstatnum = _sexstatnum;
    }   

    public String[][] getPoststatnum(){
        return poststatnum;
    }
    public void setPoststatnum(String[][] _poststatnum){ 
    	poststatnum = _poststatnum;
    }  
    public java.lang.Integer getOpId(){
        return opId;
    }
    public void setOpId(java.lang.Integer _opId){ 
        opId = _opId;
    }
    public java.lang.Integer getMainid(){
        return mainid;
    }
    public void setMainid(java.lang.Integer _mainid){ 
        mainid = _mainid;
    }
    public java.lang.String getOpTitle(){
        return opTitle;
    }
    public void setOpTitle(java.lang.String _opTitle){ 
        opTitle = _opTitle;
    }
    public java.lang.String getOpContext(){
        return opContext;
    }
    public void setOpContext(java.lang.String _opContext){ 
        opContext = _opContext;
    }
    public java.lang.String getOpOrder(){
        return opOrder;
    }
    public void setOpOrder(java.lang.String _opOrder){ 
        opOrder = _opOrder;
    }
    public InputStream getOpAccessory(){
        return opAccessory;
    }
    public void setOpAccessory(InputStream _opAccessory){ 
        opAccessory = _opAccessory;
    }
    public InputStream getOpPicblob(){
        return opPicblob;
    }
    public void setOpPicblob(InputStream _opPicblob){ 
        opPicblob = _opPicblob;
    }
    public java.lang.String getOpAccessoryname(){
        return opAccessoryname;
    }
    public void setOpAccessoryname(java.lang.String _opAccessoryname){ 
        opAccessoryname = _opAccessoryname;
    }
 
    public java.lang.String getOpPic(){
        return opPic;
    }
    public void setOpPic(java.lang.String _opPic){ 
        opPic = _opPic;
    }
    public java.lang.String getOpNum(){
        return opNum;
    }
    public void setOpNum(java.lang.String _opNum){ 
        opNum = _opNum;
    }
    public java.lang.String getOpGood(){
        return opGood;
    }
    public void setOpGood(java.lang.String _opGood){ 
        opGood = _opGood;
    }
    public java.lang.String getOpMid(){
        return opMid;
    }
    public void setOpMid(java.lang.String _opMid){ 
        opMid = _opMid;
    }
    public java.lang.String getOpBad(){
        return opBad;
    }
    public void setOpBad(java.lang.String _opBad){ 
        opBad = _opBad;
    }
}