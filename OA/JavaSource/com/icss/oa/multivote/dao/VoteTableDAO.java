package com.icss.oa.multivote.dao;

import java.sql.Connection;

import com.icss.j2ee.dao.DAO;
import com.icss.j2ee.dao.DAOException;

public class VoteTableDAO extends DAO {

	private Integer tableId;

	private Integer planPlanid;

	private String tableTime;

	private String tableMemo;

	private String tableTitle;

	private String tableTail;

	private String tableDesc;

	private String tableRowname;

	private String tableColname;

	public Integer getTableId() {
		return tableId;
	}

	public void setTableId(Integer _tableId) {
		firePropertyChange("tableId", tableId, _tableId);
		tableId = _tableId;
	}

	public Integer getPlanPlanid() {
		return planPlanid;
	}

	public void setPlanPlanid(Integer _planPlanid) {
		firePropertyChange("planPlanid", planPlanid, _planPlanid);
		planPlanid = _planPlanid;
	}

	public String getTableTime() {
		return tableTime;
	}

	public void setTableTime(String _tableTime) {
		firePropertyChange("tableTime", tableTime, _tableTime);
		tableTime = _tableTime;
	}

	public String getTableMemo() {
		return tableMemo;
	}

	public void setTableMemo(String _tableMemo) {
		firePropertyChange("tableMemo", tableMemo, _tableMemo);
		tableMemo = _tableMemo;
	}

	public String getTableTitle() {
		return tableTitle;
	}

	public void setTableTitle(String _tableTitle) {
		firePropertyChange("tableTitle", tableTitle, _tableTitle);
		tableTitle = _tableTitle;
	}

	public String getTableTail() {
		return tableTail;
	}

	public void setTableTail(String _tableTail) {
		firePropertyChange("tableTail", tableTail, _tableTail);
		tableTail = _tableTail;
	}

	public String getTableDesc() {
		return tableDesc;
	}

	public void setTableDesc(String _tableDesc) {
		firePropertyChange("tableDesc", tableDesc, _tableDesc);
		tableDesc = _tableDesc;
	}

	public String getTableRowname() {
		return tableRowname;
	}

	public void setTableRowname(String _tableRowname) {
		firePropertyChange("tableRowname", tableRowname, _tableRowname);
		tableRowname = _tableRowname;
	}

	public String getTableColname() {
		return tableColname;
	}

	public void setTableColname(String _tableColname) {
		firePropertyChange("tableColname", tableColname, _tableColname);
		tableColname = _tableColname;
	}

	protected void setupFields() throws DAOException {
		addField("tableId", "TABLE_ID");
		addField("planPlanid", "PLAN_PLANID");
		addField("tableTime", "TABLE_TIME");
		addField("tableMemo", "TABLE_MEMO");
		addField("tableTitle", "TABLE_TITLE");
		addField("tableTail", "TABLE_TAIL");
		addField("tableDesc", "TABLE_DESC");
		addField("tableRowname", "TABLE_ROWNAME");
		addField("tableColname", "TABLE_COLNAME");
		setTableName("VOTE_TABLE");
		addKey("TABLE_ID");
		setAutoIncremented("TABLE_ID");
	}

	public VoteTableDAO(Connection conn) {
		super(conn);
	}

	public VoteTableDAO() {
		super();
	}

}
