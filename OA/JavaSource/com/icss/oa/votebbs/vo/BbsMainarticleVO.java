package com.icss.oa.votebbs.vo;


import com.icss.j2ee.vo.ValueObject;

public class BbsMainarticleVO extends ValueObject {

    private Integer mainid;
    private String title;
    private String context;
    private String mOrder;
    private String uploader;
    private String uploaduuid;
    private String publishtime;
    private String modifytime;
    private String type;
    private String status;

    public java.lang.Integer getMainid(){
        return mainid;
    }
    public void setMainid(java.lang.Integer _mainid){ 
        mainid = _mainid;
    }
    public java.lang.String getTitle(){
        return title;
    }
    public void setTitle(java.lang.String _title){ 
        title = _title;
    }
    public java.lang.String getContext(){
        return context;
    }
    public void setContext(java.lang.String _context){ 
        context = _context;
    }
    public java.lang.String getMOrder(){
        return mOrder;
    }
    public void setMOrder(java.lang.String _mOrder){ 
        mOrder = _mOrder;
    }
    public java.lang.String getUploader(){
        return uploader;
    }
    public void setUploader(java.lang.String _uploader){ 
        uploader = _uploader;
    }
    public java.lang.String getUploaduuid(){
        return uploaduuid;
    }
    public void setUploaduuid(java.lang.String _uploaduuid){ 
        uploaduuid = _uploaduuid;
    }
    public java.lang.String getPublishtime(){
        return publishtime;
    }
    public void setPublishtime(java.lang.String _publishtime){ 
        publishtime = _publishtime;
    }
    public java.lang.String getModifytime(){
        return modifytime;
    }
    public void setModifytime(java.lang.String _modifytime){ 
        modifytime = _modifytime;
    }
    public java.lang.String getType(){
        return type;
    }
    public void setType(java.lang.String _type){ 
        type = _type;
    }
    public java.lang.String getStatus(){
        return status;
    }
    public void setStatus(java.lang.String _status){ 
        status = _status;
    }
}