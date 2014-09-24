/*
 * Created on 2004-3-31
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package com.icss.oa.netoffice.journal.dao;

import java.sql.Connection;

import com.icss.j2ee.dao.DAO;
import com.icss.j2ee.dao.DAOException;
/**
 * @author Administrator
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public class OfficeJournalDAO extends DAO {
	Integer rjId;
	Long rjDate;
	String rjHeadline;
	String rjContent;
	String rjWeather;
	String rjOwner;
	public Integer getRjId() {
		return rjId;
	}
	public void setRjId(Integer _rjId) {
		firePropertyChange("rjId", rjId, _rjId);
		rjId = _rjId;
	}
	public Long getRjDate() {
		return rjDate;
	}
	public void setRjDate(Long _rjDate) {
		firePropertyChange("rjDate", rjDate, _rjDate);
		rjDate = _rjDate;
	}
	public String getRjHeadline() {
		return rjHeadline;
	}
	public void setRjHeadline(String _rjHeadline) {
		firePropertyChange("rjHeadline", rjHeadline, _rjHeadline);
		rjHeadline = _rjHeadline;
	}
	public String getRjContent() {
		return rjContent;
	}
	public void setRjContent(String _rjContent) {
		firePropertyChange("rjContent", rjContent, _rjContent);
		rjContent = _rjContent;
	}
	public String getRjWeather() {
		return rjWeather;
	}
	public void setRjWeather(String _rjWeather) {
		firePropertyChange("rjWeather", rjWeather, _rjWeather);
		rjWeather = _rjWeather;
	}
	public String getRjOwner() {
		return rjOwner;
	}
	public void setRjOwner(String _rjOwner) {
		firePropertyChange("rjOwner", rjOwner, _rjOwner);
		rjOwner = _rjOwner;
	}
	protected void setupFields() throws DAOException {
		addField("rjId", "RJ_ID");
		addField("rjDate", "RJ_DATE");
		addField("rjHeadline", "RJ_HEADLINE");
		addField("rjContent", "RJ_CONTENT");
		addField("rjWeather", "RJ_WEATHER");
		addField("rjOwner", "RJ_OWNER");
		setTableName("OFFICE_JOURNAL");
		addKey("RJ_ID");
		setAutoIncremented("RJ_ID");
	}
	public OfficeJournalDAO(Connection conn) {
		super(conn);
	}
	public OfficeJournalDAO() {
		super();
	}
}
