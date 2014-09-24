/*
 * Created on 2007-6-26
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.icss.oa.fo.dao;

import java.sql.Connection;

import com.icss.j2ee.dao.DAO;
import com.icss.j2ee.dao.DAOException;

public class FoInputvoteDAO extends DAO {

	private Integer invoteId;
	private Integer planPlanid;

	private String invoteRecorder;

	private String invotePerson;

	private String invoteDate;

	private String invoteDesc;

	private Integer invotePersonnum;

	public Integer getInvoteId() {
		return invoteId;
	}

	public void setInvoteId(Integer _invoteId) {
		firePropertyChange("invoteId", invoteId, _invoteId);
		invoteId = _invoteId;
	}
	public Integer getPlanPlanid() {
		return planPlanid;
	}

	public void setPlanPlanid(Integer _planPlanid) {
		firePropertyChange("planPlanid", planPlanid, _planPlanid);
		planPlanid = _planPlanid;
	}
	public String getInvoteRecorder() {
		return invoteRecorder;
	}

	public void setInvoteRecorder(String _invoteRecorder) {
		firePropertyChange("invoteRecorder", invoteRecorder, _invoteRecorder);
		invoteRecorder = _invoteRecorder;
	}

	public String getInvotePerson() {
		return invotePerson;
	}

	public void setInvotePerson(String _invotePerson) {
		firePropertyChange("invotePerson", invotePerson, _invotePerson);
		invotePerson = _invotePerson;
	}

	public String getInvoteDate() {
		return invoteDate;
	}

	public void setInvoteDate(String _invoteDate) {
		firePropertyChange("invoteDate", invoteDate, _invoteDate);
		invoteDate = _invoteDate;
	}

	public String getInvoteDesc() {
		return invoteDesc;
	}

	public void setInvoteDesc(String _invoteDesc) {
		firePropertyChange("invoteDesc", invoteDesc, _invoteDesc);
		invoteDesc = _invoteDesc;
	}

	public Integer getInvotePersonnum() {
		return invotePersonnum;
	}

	public void setInvotePersonnum(Integer _invotePersonnum) {
		firePropertyChange("invotePersonnum", invotePersonnum, _invotePersonnum);
		invotePersonnum = _invotePersonnum;
	}

	protected void setupFields() throws DAOException {
		addField("invoteId", "INVOTE_ID");
		addField("planPlanid", "PLAN_PLANID");
		addField("invoteRecorder", "INVOTE_RECORDER");
		addField("invotePerson", "INVOTE_PERSON");
		addField("invoteDate", "INVOTE_DATE");
		addField("invoteDesc", "INVOTE_DESC");
		addField("invotePersonnum", "INVOTE_PERSONNUM");
		setTableName("FO_INPUTVOTE");
		addKey("INVOTE_ID");
		setAutoIncremented("INVOTE_ID");
	}

	public FoInputvoteDAO(Connection conn) {
		super(conn);
	}

	public FoInputvoteDAO() {
		super();
	}

}
