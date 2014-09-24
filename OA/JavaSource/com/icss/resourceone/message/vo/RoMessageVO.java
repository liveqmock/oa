package com.icss.resourceone.message.vo;


import com.icss.j2ee.vo.ValueObject;

public class RoMessageVO extends ValueObject {

    private Integer messageid;
    private Integer serialindex;
    private String ispublish;
    private String messagecontent;

    public java.lang.Integer getMessageid(){
        return messageid;
    }
    public void setMessageid(java.lang.Integer _messageid){ 
        messageid = _messageid;
    }
    public java.lang.Integer getSerialindex(){
        return serialindex;
    }
    public void setSerialindex(java.lang.Integer _serialindex){ 
        serialindex = _serialindex;
    }
    public java.lang.String getIspublish(){
        return ispublish;
    }
    public void setIspublish(java.lang.String _ispublish){ 
        ispublish = _ispublish;
    }
    public java.lang.String getMessagecontent(){
        return messagecontent;
    }
    public void setMessagecontent(java.lang.String _messagecontent){ 
        messagecontent = _messagecontent;
    }
}