package com.icss.oa.votebbs.dao;

import java.sql.Connection;

import com.icss.j2ee.dao.DAO;
import com.icss.j2ee.dao.DAOException;

public class BbsVoteDAO extends DAO {

    private Integer vtId;
    private Integer mainid;
    private String vtVoteruuid;
    private String vtVotername;
    private String vtVoterip;
    private String vtVotetime;
    private String vtSinglenum;
    private String vtMultinum;
    private String vtVuid;
    private Integer vtOpid;
    private String	vtScore;
    private String	vtWord;

    public BbsVoteDAO(){
        super();
    }

    public BbsVoteDAO(Connection conn){
        super(conn);
    }

    public java.lang.Integer getVtId(){
        return vtId;
    }
    public void setVtId(java.lang.Integer _vtId){ 
        firePropertyChange("vtId",vtId,_vtId);
        vtId = _vtId;
    }
    public java.lang.Integer getMainid(){
        return mainid;
    }
    public void setMainid(java.lang.Integer _mainid){ 
        firePropertyChange("mainid",mainid,_mainid);
        mainid = _mainid;
    }
    public java.lang.String getVtVoteruuid(){
        return vtVoteruuid;
    }
    public void setVtVoteruuid(java.lang.String _vtVoteruuid){ 
        firePropertyChange("vtVoteruuid",vtVoteruuid,_vtVoteruuid);
        vtVoteruuid = _vtVoteruuid;
    }
    public java.lang.String getVtVotername(){
        return vtVotername;
    }
    public void setVtVotername(java.lang.String _vtVotername){ 
        firePropertyChange("vtVotername",vtVotername,_vtVotername);
        vtVotername = _vtVotername;
    }
    public java.lang.String getVtVoterip(){
        return vtVoterip;
    }
    public void setVtVoterip(java.lang.String _vtVoterip){ 
        firePropertyChange("vtVoterip",vtVoterip,_vtVoterip);
        vtVoterip = _vtVoterip;
    }
    public java.lang.String getVtVotetime(){
        return vtVotetime;
    }
    public void setVtVotetime(java.lang.String _vtVotetime){ 
        firePropertyChange("vtVotetime",vtVotetime,_vtVotetime);
        vtVotetime = _vtVotetime;
    }
    public java.lang.String getVtSinglenum(){
        return vtSinglenum;
    }
    public void setVtSinglenum(java.lang.String _vtSinglenum){ 
        firePropertyChange("vtSinglenum",vtSinglenum,_vtSinglenum);
        vtSinglenum = _vtSinglenum;
    }
    public java.lang.String getVtMultinum(){
        return vtMultinum;
    }
    public void setVtMultinum(java.lang.String _vtMultinum){ 
        firePropertyChange("vtMultinum",vtMultinum,_vtMultinum);
        vtMultinum = _vtMultinum;
    }
    public java.lang.String getVtVuid(){
        return vtVuid;
    }
    public void setVtVuid(java.lang.String _vtVuid){ 
        firePropertyChange("vtVuid",vtVuid,_vtVuid);
        vtVuid = _vtVuid;
    }
    public java.lang.Integer getVtOpid(){
        return vtOpid;
    }
    public void setVtOpid(java.lang.Integer _vtOpid){ 
        firePropertyChange("vtOpid",vtOpid,_vtOpid);
        vtOpid = _vtOpid;
    }
    public java.lang.String getVtScore(){
        return vtScore;
    }
    public void setVtScore(java.lang.String _vtScore){ 
        firePropertyChange("vtScore",vtScore,_vtScore);
        vtScore = _vtScore;
    }
    public java.lang.String getVtWord(){
        return vtWord;
    }
    public void setVtWord(java.lang.String _vtWord){ 
        firePropertyChange("vtWord",vtWord,_vtWord);
        vtWord = _vtWord;
    }
    protected void setupFields() throws DAOException {
        addField("vtId","VT_ID");
        addField("mainid","MAINID");
        addField("vtVoteruuid","VT_VOTERUUID");
        addField("vtVotername","VT_VOTERNAME");
        addField("vtVoterip","VT_VOTERIP");
        addField("vtVotetime","VT_VOTETIME");
        addField("vtSinglenum","VT_SINGLENUM");
        addField("vtMultinum","VT_MULTINUM");
        addField("vtVuid","VT_VUID");
        addField("vtOpid","VT_OPID");
        addField("vtWord","VT_WORD");
        addField("vtScore","VT_SCORE");
        setTableName("BBS_VOTE");
        addKey("VT_ID");
        setAutoIncremented("VT_ID");
    }

}