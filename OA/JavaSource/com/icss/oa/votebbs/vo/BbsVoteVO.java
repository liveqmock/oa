package com.icss.oa.votebbs.vo;


import com.icss.j2ee.vo.ValueObject;

public class BbsVoteVO extends ValueObject {

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

    public java.lang.Integer getVtId(){
        return vtId;
    }
    public void setVtId(java.lang.Integer _vtId){ 
        vtId = _vtId;
    }
    public java.lang.Integer getMainid(){
        return mainid;
    }
    public void setMainid(java.lang.Integer _mainid){ 
        mainid = _mainid;
    }
    public java.lang.String getVtVoteruuid(){
        return vtVoteruuid;
    }
    public void setVtVoteruuid(java.lang.String _vtVoteruuid){ 
        vtVoteruuid = _vtVoteruuid;
    }
    public java.lang.String getVtVotername(){
        return vtVotername;
    }
    public void setVtVotername(java.lang.String _vtVotername){ 
        vtVotername = _vtVotername;
    }
    public java.lang.String getVtVoterip(){
        return vtVoterip;
    }
    public void setVtVoterip(java.lang.String _vtVoterip){ 
        vtVoterip = _vtVoterip;
    }
    public java.lang.String getVtVotetime(){
        return vtVotetime;
    }
    public void setVtVotetime(java.lang.String _vtVotetime){ 
        vtVotetime = _vtVotetime;
    }
    public java.lang.String getVtSinglenum(){
        return vtSinglenum;
    }
    public void setVtSinglenum(java.lang.String _vtSinglenum){ 
        vtSinglenum = _vtSinglenum;
    }
    public java.lang.String getVtMultinum(){
        return vtMultinum;
    }
    public void setVtMultinum(java.lang.String _vtMultinum){ 
        vtMultinum = _vtMultinum;
    }
    public java.lang.String getVtVuid(){
        return vtVuid;
    }
    public void setVtVuid(java.lang.String _vtVuid){ 
        vtVuid = _vtVuid;
    }
    public java.lang.Integer getVtOpid(){
        return vtOpid;
    }
    public void setVtOpid(java.lang.Integer _vtOpid){ 
        vtOpid = _vtOpid;
    }
    public java.lang.String getVtScore(){
        return vtScore;
    }
    public void setVtScore(java.lang.String _vtScore){ 

        vtScore = _vtScore;
    }
    public java.lang.String getVtWord(){
        return vtWord;
    }
    public void setVtWord(java.lang.String _vtWord){ 

        vtWord = _vtWord;
    }
}