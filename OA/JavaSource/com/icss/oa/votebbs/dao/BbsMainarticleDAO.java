package com.icss.oa.votebbs.dao;

import java.sql.Connection;

import com.icss.j2ee.dao.DAO;
import com.icss.j2ee.dao.DAOException;

public class BbsMainarticleDAO extends DAO {

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

    public BbsMainarticleDAO(){
        super();
    }

    public BbsMainarticleDAO(Connection conn){
        super(conn);
    }

    public java.lang.Integer getMainid(){
        return mainid;
    }
    public void setMainid(java.lang.Integer _mainid){ 
        firePropertyChange("mainid",mainid,_mainid);
        mainid = _mainid;
    }
    public java.lang.String getTitle(){
        return title;
    }
    public void setTitle(java.lang.String _title){ 
        firePropertyChange("title",title,_title);
        title = _title;
    }
    public java.lang.String getContext(){
        return context;
    }
    public void setContext(java.lang.String _context){ 
        firePropertyChange("context",context,_context);
        context = _context;
    }
    public java.lang.String getMOrder(){
        return mOrder;
    }
    public void setMOrder(java.lang.String _mOrder){ 
        firePropertyChange("mOrder",mOrder,_mOrder);
        mOrder = _mOrder;
    }
    public java.lang.String getUploader(){
        return uploader;
    }
    public void setUploader(java.lang.String _uploader){ 
        firePropertyChange("uploader",uploader,_uploader);
        uploader = _uploader;
    }
    public java.lang.String getUploaduuid(){
        return uploaduuid;
    }
    public void setUploaduuid(java.lang.String _uploaduuid){ 
        firePropertyChange("uploaduuid",uploaduuid,_uploaduuid);
        uploaduuid = _uploaduuid;
    }
    public java.lang.String getPublishtime(){
        return publishtime;
    }
    public void setPublishtime(java.lang.String _publishtime){ 
        firePropertyChange("publishtime",publishtime,_publishtime);
        publishtime = _publishtime;
    }
    public java.lang.String getModifytime(){
        return modifytime;
    }
    public void setModifytime(java.lang.String _modifytime){ 
        firePropertyChange("modifytime",modifytime,_modifytime);
        modifytime = _modifytime;
    }
    public java.lang.String getType(){
        return type;
    }
    public void setType(java.lang.String _type){ 
        firePropertyChange("type",type,_type);
        type = _type;
    }
    public java.lang.String getStatus(){
        return status;
    }
    public void setStatus(java.lang.String _status){ 
        firePropertyChange("status",status,_status);
        status = _status;
    }
    protected void setupFields() throws DAOException {
        addField("mainid","MAINID");
        addField("title","TITLE");
        addField("context","CONTEXT");
        addField("mOrder","M_ORDER");
        addField("uploader","UPLOADER");
        addField("uploaduuid","UPLOADUUID");
        addField("publishtime","PUBLISHTIME");
        addField("modifytime","MODIFYTIME");
        addField("type","TYPE");
        addField("status","STATUS");
        setTableName("BBS_MAINARTICLE");
        addKey("MAINID");
        setAutoIncremented("MAINID");
    }

}