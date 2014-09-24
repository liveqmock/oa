package com.icss.oa.multivote.vo;

import com.icss.j2ee.vo.ValueObject;

public class VoteTableVO extends ValueObject {

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
	
		tableId = _tableId;
	}

	public Integer getPlanPlanid() {
		return planPlanid;
	}

	public void setPlanPlanid(Integer _planPlanid) {
		
		planPlanid = _planPlanid;
	}

	public String getTableTime() {
		return tableTime;
	}

	public void setTableTime(String _tableTime) {
		
		tableTime = _tableTime;
	}

	public String getTableMemo() {
		return tableMemo;
	}

	public void setTableMemo(String _tableMemo) {
		
		tableMemo = _tableMemo;
	}

	public String getTableTitle() {
		return tableTitle;
	}

	public void setTableTitle(String _tableTitle) {
		
		tableTitle = _tableTitle;
	}

	public String getTableTail() {
		return tableTail;
	}

	public void setTableTail(String _tableTail) {
		
		tableTail = _tableTail;
	}

	public String getTableDesc() {
		return tableDesc;
	}

	public void setTableDesc(String _tableDesc) {
		
		tableDesc = _tableDesc;
	}

	public String getTableRowname() {
		return tableRowname;
	}

	public void setTableRowname(String _tableRowname) {
		
		tableRowname = _tableRowname;
	}

	public String getTableColname() {
		return tableColname;
	}

	public void setTableColname(String _tableColname) {
	
		tableColname = _tableColname;
	}

}