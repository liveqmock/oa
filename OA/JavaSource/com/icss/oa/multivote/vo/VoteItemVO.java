package com.icss.oa.multivote.vo;

import com.icss.j2ee.vo.ValueObject;

public class VoteItemVO extends ValueObject {

	private Integer itemId;

	private Integer tableId;

	private String itemName;

	private String itemCode;

	private String itemDesc;

	public Integer getItemId() {
		return itemId;
	}

	public void setItemId(Integer _itemId) {
	
		itemId = _itemId;
	}

	public Integer getTableId() {
		return tableId;
	}

	public void setTableId(Integer _tableId) {
		
		tableId = _tableId;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String _itemName) {
		
		itemName = _itemName;
	}

	public String getItemCode() {
		return itemCode;
	}

	public void setItemCode(String _itemCode) {
	
		itemCode = _itemCode;
	}

	public String getItemDesc() {
		return itemDesc;
	}

	public void setItemDesc(String _itemDesc) {
	
		itemDesc = _itemDesc;
	}

}