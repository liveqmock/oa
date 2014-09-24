package com.icss.oa.multivote.dao;

import java.sql.Connection;

import com.icss.j2ee.dao.DAO;
import com.icss.j2ee.dao.DAOException;

public class VoteArticleDAO extends DAO {

	private Integer artId;

	private Integer tableId;

	private String artTitle;

	private String artContent;

	private String artWriter;

	private String artUploadusr;

	private String artUploadtime;

	private Integer artVotenum;

	private String artVotescore;

	private String artPlace;

	private String artTotalnum;

	private String artTypeid;

	private String artTypename;

	private String artOutvotenum;

	private String artInvotenum;

	private String artWordnum;

	private String artPubtime;

	private String artIstoolong;

	private String artUsenum;

	private String artStatnum;

	private String artMemo;

	private String artIsentry;

	private String artIsconsult;

	public Integer getArtId() {
		return artId;
	}

	public void setArtId(Integer _artId) {
		firePropertyChange("artId", artId, _artId);
		artId = _artId;
	}

	public Integer getTableId() {
		return tableId;
	}

	public void setTableId(Integer _tableId) {
		firePropertyChange("tableId", tableId, _tableId);
		tableId = _tableId;
	}

	public String getArtTitle() {
		return artTitle;
	}

	public void setArtTitle(String _artTitle) {
		firePropertyChange("artTitle", artTitle, _artTitle);
		artTitle = _artTitle;
	}

	public String getArtContent() {
		return artContent;
	}

	public void setArtContent(String _artContent) {
		firePropertyChange("artContent", artContent, _artContent);
		artContent = _artContent;
	}

	public String getArtWriter() {
		return artWriter;
	}

	public void setArtWriter(String _artWriter) {
		firePropertyChange("artWriter", artWriter, _artWriter);
		artWriter = _artWriter;
	}

	public String getArtUploadusr() {
		return artUploadusr;
	}

	public void setArtUploadusr(String _artUploadusr) {
		firePropertyChange("artUploadusr", artUploadusr, _artUploadusr);
		artUploadusr = _artUploadusr;
	}

	public String getArtUploadtime() {
		return artUploadtime;
	}

	public void setArtUploadtime(String _artUploadtime) {
		firePropertyChange("artUploadtime", artUploadtime, _artUploadtime);
		artUploadtime = _artUploadtime;
	}

	public Integer getArtVotenum() {
		return artVotenum;
	}

	public void setArtVotenum(Integer _artVotenum) {
		firePropertyChange("artVotenum", artVotenum, _artVotenum);
		artVotenum = _artVotenum;
	}

	public String getArtVotescore() {
		return artVotescore;
	}

	public void setArtVotescore(String _artVotescore) {
		firePropertyChange("artVotescore", artVotescore, _artVotescore);
		artVotescore = _artVotescore;
	}

	public String getArtPlace() {
		return artPlace;
	}

	public void setArtPlace(String _artPlace) {
		firePropertyChange("artPlace", artPlace, _artPlace);
		artPlace = _artPlace;
	}

	public String getArtTotalnum() {
		return artTotalnum;
	}

	public void setArtTotalnum(String _artTotalnum) {
		firePropertyChange("artTotalnum", artTotalnum, _artTotalnum);
		artTotalnum = _artTotalnum;
	}

	public String getArtTypeid() {
		return artTypeid;
	}

	public void setArtTypeid(String _artTypeid) {
		firePropertyChange("artTypeid", artTypeid, _artTypeid);
		artTypeid = _artTypeid;
	}

	public String getArtTypename() {
		return artTypename;
	}

	public void setArtTypename(String _artTypename) {
		firePropertyChange("artTypename", artTypename, _artTypename);
		artTypename = _artTypename;
	}

	public String getArtOutvotenum() {
		return artOutvotenum;
	}

	public void setArtOutvotenum(String _artOutvotenum) {
		firePropertyChange("artOutvotenum", artOutvotenum, _artOutvotenum);
		artOutvotenum = _artOutvotenum;
	}

	public String getArtInvotenum() {
		return artInvotenum;
	}

	public void setArtInvotenum(String _artInvotenum) {
		firePropertyChange("artInvotenum", artInvotenum, _artInvotenum);
		artInvotenum = _artInvotenum;
	}

	public String getArtWordnum() {
		return artWordnum;
	}

	public void setArtWordnum(String _artWordnum) {
		firePropertyChange("artWordnum", artWordnum, _artWordnum);
		artWordnum = _artWordnum;
	}

	public String getArtPubtime() {
		return artPubtime;
	}

	public void setArtPubtime(String _artPubtime) {
		firePropertyChange("artPubtime", artPubtime, _artPubtime);
		artPubtime = _artPubtime;
	}

	public String getArtIstoolong() {
		return artIstoolong;
	}

	public void setArtIstoolong(String _artIstoolong) {
		firePropertyChange("artIstoolong", artIstoolong, _artIstoolong);
		artIstoolong = _artIstoolong;
	}

	public String getArtUsenum() {
		return artUsenum;
	}

	public void setArtUsenum(String _artUsenum) {
		firePropertyChange("artUsenum", artUsenum, _artUsenum);
		artUsenum = _artUsenum;
	}

	public String getArtStatnum() {
		return artStatnum;
	}

	public void setArtStatnum(String _artStatnum) {
		firePropertyChange("artStatnum", artStatnum, _artStatnum);
		artStatnum = _artStatnum;
	}

	public String getArtMemo() {
		return artMemo;
	}

	public void setArtMemo(String _artMemo) {
		firePropertyChange("artMemo", artMemo, _artMemo);
		artMemo = _artMemo;
	}

	public String getArtIsentry() {
		return artIsentry;
	}

	public void setArtIsentry(String _artIsentry) {
		firePropertyChange("artIsentry", artIsentry, _artIsentry);
		artIsentry = _artIsentry;
	}

	public String getArtIsconsult() {
		return artIsconsult;
	}

	public void setArtIsconsult(String _artIsconsult) {
		firePropertyChange("artIsconsult", artIsconsult, _artIsconsult);
		artIsconsult = _artIsconsult;
	}

	protected void setupFields() throws DAOException {
		addField("artId", "ART_ID");
		addField("tableId", "TABLE_ID");
		addField("artTitle", "ART_TITLE");
		addField("artContent", "ART_CONTENT");
		addField("artWriter", "ART_WRITER");
		addField("artUploadusr", "ART_UPLOADUSR");
		addField("artUploadtime", "ART_UPLOADTIME");
		addField("artVotenum", "ART_VOTENUM");
		addField("artVotescore", "ART_VOTESCORE");
		addField("artPlace", "ART_PLACE");
		addField("artTotalnum", "ART_TOTALNUM");
		addField("artTypeid", "ART_TYPEID");
		addField("artTypename", "ART_TYPENAME");
		addField("artOutvotenum", "ART_OUTVOTENUM");
		addField("artInvotenum", "ART_INVOTENUM");
		addField("artWordnum", "ART_WORDNUM");
		addField("artPubtime", "ART_PUBTIME");
		addField("artIstoolong", "ART_ISTOOLONG");
		addField("artUsenum", "ART_USENUM");
		addField("artStatnum", "ART_STATNUM");
		addField("artMemo", "ART_MEMO");
		addField("artIsentry", "ART_ISENTRY");
		addField("artIsconsult", "ART_ISCONSULT");
		setTableName("VOTE_ARTICLE");
		addKey("ART_ID");
		setAutoIncremented("ART_ID");
	}

	public VoteArticleDAO(Connection conn) {
		super(conn);
	}

	public VoteArticleDAO() {
		super();
	}

}
