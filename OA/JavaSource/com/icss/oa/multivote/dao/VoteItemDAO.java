package com.icss.oa.multivote.dao;

import java.sql.Connection;

import com.icss.j2ee.dao.DAO;
import com.icss.j2ee.dao.DAOException;

public class VoteItemDAO extends DAO {

	private Integer itemId;

	private Integer tableId;

	private String itemName;

	private String itemCode;

	private String itemDesc;

	public Integer getItemId() {
		return itemId;
	}

	public void setItemId(Integer _itemId) {
		firePropertyChange("itemId", itemId, _itemId);
		itemId = _itemId;
	}

	public Integer getTableId() {
		return tableId;
	}

	public void setTableId(Integer _tableId) {
		firePropertyChange("tableId", tableId, _tableId);
		tableId = _tableId;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String _itemName) {
		firePropertyChange("itemName", itemName, _itemName);
		itemName = _itemName;
	}

	public String getItemCode() {
		return itemCode;
	}

	public void setItemCode(String _itemCode) {
		firePropertyChange("itemCode", itemCode, _itemCode);
		itemCode = _itemCode;
	}

	public String getItemDesc() {
		return itemDesc;
	}

	public void setItemDesc(String _itemDesc) {
		firePropertyChange("itemDesc", itemDesc, _itemDesc);
		itemDesc = _itemDesc;
	}

	protected void setupFields() throws DAOException {
		addField("itemId", "ITEM_ID");
		addField("tableId", "TABLE_ID");
		addField("itemName", "ITEM_NAME");
		addField("itemCode", "ITEM_CODE");
		addField("itemDesc", "ITEM_DESC");
		setTableName("VOTE_ITEM");
		addKey("ITEM_ID");
		setAutoIncremented("ITEM_ID");
	}

	public VoteItemDAO(Connection conn) {
		super(conn);
	}

	public VoteItemDAO() {
		super();
	}

}
