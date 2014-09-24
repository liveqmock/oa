package com.icss.oa.votebbs.vo;


import com.icss.j2ee.vo.ValueObject;

public class BbsReplyVO extends ValueObject {

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

    public java.lang.Integer getReId(){
        return reId;
    }
    public void setReId(java.lang.Integer _reId){ 
        reId = _reId;
    }
    public java.lang.Integer getMainid(){
        return mainid;
    }
    public void setMainid(java.lang.Integer _mainid){ 
        mainid = _mainid;
    }
    public java.lang.String getReTitle(){
        return reTitle;
    }
    public void setReTitle(java.lang.String _reTitle){ 
        reTitle = _reTitle;
    }
    public java.lang.String getReContext(){
        return reContext;
    }
    public void setReContext(java.lang.String _reContext){ 
        reContext = _reContext;
    }
    public java.lang.String getReSenderuuid(){
        return reSenderuuid;
    }
    public void setReSenderuuid(java.lang.String _reSenderuuid){ 
        reSenderuuid = _reSenderuuid;
    }
    public java.lang.String getReSendername(){
        return reSendername;
    }
    public void setReSendername(java.lang.String _reSendername){ 
        reSendername = _reSendername;
    }
    public java.lang.String getReTime(){
        return reTime;
    }
    public void setReTime(java.lang.String _reTime){ 
        reTime = _reTime;
    }
    public java.lang.String getReOrder(){
        return reOrder;
    }
    public void setReOrder(java.lang.String _reOrder){ 
        reOrder = _reOrder;
    }
    public java.lang.String getReVuid(){
        return reVuid;
    }
    public void setReVuid(java.lang.String _reVuid){ 
        reVuid = _reVuid;
    }
    public java.lang.String getReSenderip(){
        return reSenderip;
    }
    public void setReSenderip(java.lang.String _reSenderip){ 
        reSenderip = _reSenderip;
    }
}