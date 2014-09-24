/*
 * Created on 2004-12-31
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.icss.oa.workmeeting.dao;  

import java.sql.Connection;

import com.icss.j2ee.dao.DAO;
import com.icss.j2ee.dao.DAOException;

/**
 * @author Administrator
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class GongzuowjDAO extends DAO {

	private Integer wrNo;
	private String title;
	private Integer qs;
	private Integer totalQs;
	private String publicDate;
	private String edName;
	private String issueName;
	private Long inTime;
	private Integer attchId;
	private String scope1;
	private Integer classid;
	private Integer cflag;
	private Integer seqno;
	private String orguuid;
	public Integer getWrNo() {
		return wrNo;
	}
	public void setWrNo(Integer _wrNo) {
		firePropertyChange("wrNo", wrNo, _wrNo);
		wrNo = _wrNo;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String _title) {
		firePropertyChange("title", title, _title);
		title = _title;
	}
	public Integer getQs() {
		return qs;
	}
	public void setQs(Integer _qs) {
		firePropertyChange("qs", qs, _qs);
		qs = _qs;
	}
	public Integer getTotalQs() {
		return totalQs;
	}
	public void setTotalQs(Integer _totalQs) {
		firePropertyChange("totalQs", totalQs, _totalQs);
		totalQs = _totalQs;
	}
	public String getPublicDate() {
		return publicDate;
	}
	public void setPublicDate(String _publicDate) {
		firePropertyChange("publicDate", publicDate, _publicDate);
		publicDate = _publicDate;
	}
	public String getEdName() {
		return edName;
	}
	public void setEdName(String _edName) {
		firePropertyChange("edName", edName, _edName);
		edName = _edName;
	}
	public String getIssueName() {
		return issueName;
	}
	public void setIssueName(String _issueName) {
		firePropertyChange("issueName", issueName, _issueName);
		issueName = _issueName;
	}
	public Long getInTime() {
		return inTime;
	}
	public void setInTime(Long _inTime) {
		firePropertyChange("inTime", inTime, _inTime);
		inTime = _inTime;
	}
	public Integer getAttchId() {
		return attchId;
	}
	public void setAttchId(Integer _attchId) {
		firePropertyChange("attchId", attchId, _attchId);
		attchId = _attchId;
	}
	public String getScope1() {
		return scope1;
	}
	public void setScope1(String _scope1) {
		firePropertyChange("scope1", scope1, _scope1);
		scope1 = _scope1;
	}
	public Integer getClassid() {
		return classid;
	}
	public void setClassid(Integer _classid) {
		firePropertyChange("classid", classid, _classid);
		classid = _classid;
	}
	public Integer getCflag() {
		return cflag;
	}
	public void setCflag(Integer _cflag) {
		firePropertyChange("cflag", cflag, _cflag);
		cflag = _cflag;
	}
	public Integer getSeqno() {
		return seqno;
	}
	public void setSeqno(Integer _seqno) {
		firePropertyChange("seqno", seqno, _seqno);
		seqno = _seqno;
	}
	public String getOrguuid() {
		return orguuid;
	}
	public void setOrguuid(String _orguuid) {
		firePropertyChange("orguuid", orguuid, _orguuid);
		orguuid = _orguuid;
	}
	protected void setupFields() throws DAOException {
		addField("wrNo", "WR_NO");
		addField("title", "TITLE");
		addField("qs", "QS");
		addField("totalQs", "TOTAL_QS");
		addField("publicDate", "PUBLIC_DATE");
		addField("edName", "ED_NAME");
		addField("issueName", "ISSUE_NAME");
		addField("inTime", "IN_TIME");
		addField("attchId", "ATTCH_ID");
		addField("scope1", "SCOPE1");
		addField("classid", "CLASSID");
		addField("cflag", "CFLAG");
		addField("seqno", "SEQNO");
		addField("orguuid", "ORGUUID");
		setTableName("GONGZUOWJ");
		addKey("WR_NO");
		setAutoIncremented("WR_NO");
	}
	public GongzuowjDAO(Connection conn) {
		super(conn);
	}
	public GongzuowjDAO() {
		super();
	}
}
