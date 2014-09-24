package com.icss.oa.votebbs.vo;


import com.icss.j2ee.vo.ValueObject;

public class BbsVoteuserVO extends ValueObject {

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

    public java.lang.Integer getVuId(){
        return vuId;
    }
    public void setVuId(java.lang.Integer _vuId){ 
        vuId = _vuId;
    }
    public java.lang.String getVuName(){
        return vuName;
    }
    public void setVuName(java.lang.String _vuName){ 
        vuName = _vuName;
    }
    public java.lang.String getVuSex(){
        return vuSex;
    }
    public void setVuSex(java.lang.String _vuSex){ 
        vuSex = _vuSex;
    }
    public java.lang.String getVuDep(){
        return vuDep;
    }
    public void setVuDep(java.lang.String _vuDep){ 
        vuDep = _vuDep;
    }
    public java.lang.String getVuPost(){
        return vuPost;
    }
    public void setVuPost(java.lang.String _vuPost){ 
        vuPost = _vuPost;
    }
    public java.lang.String getVuUuid(){
        return vuUuid;
    }
    public void setVuUuid(java.lang.String _vuUuid){ 
        vuUuid = _vuUuid;
    }
    public java.lang.String getVuIp(){
        return vuIp;
    }
    public void setVuIp(java.lang.String _vuIp){ 
        vuIp = _vuIp;
    }
    public java.lang.String getVuLoginname(){
        return vuLoginname;
    }
    public void setVuLoginname(java.lang.String _vuLoginname){ 
        vuLoginname = _vuLoginname;
    }
    public java.lang.String getVuPassword(){
        return vuPassword;
    }
    public void setVuPassword(java.lang.String _vuPassword){ 
        vuPassword = _vuPassword;
    }
    public java.lang.String getVuMainid(){
        return vuMainid;
    }
    public void setVuMainid(java.lang.String _vuMainid){ 
    	vuMainid = _vuMainid;
    }
}